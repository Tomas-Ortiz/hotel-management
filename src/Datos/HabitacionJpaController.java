package Datos;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class HabitacionJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public HabitacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Gestion_HoteleraPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            habitacion = em.merge(habitacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = habitacion.getId();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCountHabitacionesByState(String estado) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT COUNT(*) FROM habitaciones WHERE estado = ?");

            nativeQuery.setParameter(1, estado);

            return ((Number) nativeQuery.getSingleResult()).intValue();

        } finally {
            em.close();
        }
    }

    public List<Habitacion> habitacionesOrderBy(String campo) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM habitaciones ORDER BY " + campo, Habitacion.class);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Habitacion> getHabitacionesByValorEstado(String valor) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM habitaciones WHERE estado = ?", Habitacion.class);

            nativeQuery.setParameter(1, valor);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Habitacion> buscarHabitacion(String busqueda) {

        EntityManager em = getEntityManager();

        try {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM habitaciones WHERE id LIKE ? OR numero LIKE ? "
                    + "OR tipo LIKE ? OR estado LIKE ? OR detalles LIKE ? OR precioDia LIKE ?", Habitacion.class);

            try {
                for (int i = 1; i < 7; i++) {
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

    public Habitacion findHabitacionByNum(int num) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM habitaciones WHERE "
                    + "numero = ?", Habitacion.class);

            nativeQuery.setParameter(1, num);

            try {
                return (Habitacion) nativeQuery.getSingleResult();

            } catch (NoResultException nre) {
                return null;
            }
        } finally {
            em.close();
        }
    }
}
