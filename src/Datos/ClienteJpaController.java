package Datos;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class ClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Gestion_HoteleraPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findClienteByDni(Long dni) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM clientes WHERE "
                    + "dni = ?", Cliente.class);

            nativeQuery.setParameter(1, dni);

            try {
                return (Cliente) nativeQuery.getSingleResult();

            } catch (NoResultException nre) {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<Cliente> clientesOrderBy(String campo) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM clientes ORDER BY " + campo, Cliente.class);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Cliente> buscarClientes(String busqueda) {

        EntityManager em = getEntityManager();

        try {

            String consulta = "SELECT * FROM clientes WHERE id LIKE ? OR nombres LIKE ? OR apellidos LIKE ? OR "
                    + "dni LIKE ? OR nacionalidad LIKE ? OR correo LIKE ? OR nroTelefono LIKE ? OR fechaNacimiento LIKE ?";

            Query nativeQuery = em.createNativeQuery(consulta, Cliente.class);

            try {
                for (int i = 1; i < 9; i++) {
                    nativeQuery.setParameter(i, busqueda + "%");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }
}
