package Datos;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Producto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class ProductoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ProductoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Gestion_HoteleraPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto = em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public Producto findProdByName(String nombre, String marca) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM productos WHERE "
                    + "nombre = ? AND marca = ?", Producto.class);

            nativeQuery.setParameter(1, nombre);
            nativeQuery.setParameter(2, marca);

            try {
                return (Producto) nativeQuery.getSingleResult();

            } catch (NoResultException nre) {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<Producto> productosOrderBy(String campo) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM productos ORDER BY " + campo, Producto.class);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Producto> buscarProducto(String busqueda) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM productos WHERE id LIKE ? OR nombre LIKE ? "
                    + "OR marca LIKE ? OR categoria LIKE ? OR stock LIKE ? OR proveedor LIKE ? OR precioCompra LIKE ? "
                    + "OR precioVenta LIKE ?", Producto.class);

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

    public List<Producto> getProductosDisponiles() {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM productos WHERE stock > 0", Producto.class);
            return nativeQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return null;
    }
}
