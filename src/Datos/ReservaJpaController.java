package Datos;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Reserva;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ReservaJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ReservaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Gestion_HoteleraPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reserva = em.merge(reserva);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reserva.getId();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public List<Reserva> reservasOrderBy(String campo) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM reservas ORDER BY " + campo, Reserva.class);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Reserva> reservasOrderByInOtherTable(String campo) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT res.id, res.fechaEntrada, res.fechaSalida, res.fk_cliente, res.fk_habitacion, \n"
                    + "res.horaEntrada, res.horaSalida, res.precioTotal, res.tipoPago, res.estado FROM habitaciones hab, reservas res, clientes cli\n"
                    + "WHERE res.fk_cliente = cli.id AND res.fk_habitacion = hab.id ORDER BY " + campo, Reserva.class);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    public int getCountReservasByState(String estado) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT COUNT(*) FROM reservas WHERE estado = ?");

            nativeQuery.setParameter(1, estado);

            return ((Number) nativeQuery.getSingleResult()).intValue();

        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Reserva> buscarReserva(String busqueda) {

        EntityManager em = getEntityManager();

        try {

            String consulta = "SELECT DISTINCT res.id, res.fechaEntrada, res.fechaSalida, res.fk_cliente, res.fk_habitacion, \n"
                    + "res.horaEntrada, res.horaSalida, res.precioTotal, res.tipoPago, res.estado FROM habitaciones hab, reservas res, clientes cli\n"
                    + "WHERE res.fk_cliente = cli.id AND res.fk_habitacion = hab.id AND (res.id LIKE ? OR cli.nombres LIKE ? OR cli.apellidos LIKE ? "
                    + "OR cli.dni LIKE ? OR hab.numero LIKE ? OR res.fechaEntrada LIKE ? OR res.fechaSalida LIKE ? OR res.horaEntrada LIKE ? "
                    + "OR res.horaSalida LIKE ? OR res.estado LIKE ? OR res.precioTotal LIKE ?)";

            Query nativeQuery = em.createNativeQuery(consulta, Reserva.class);

            try {
                for (int i = 1; i < 12; i++) {
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
