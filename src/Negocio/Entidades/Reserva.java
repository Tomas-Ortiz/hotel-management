package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaEntrada, fechaSalida, tipoPago, horaEntrada, horaSalida;
    private float precioTotal;

    enum enumEstado {

        Cobrado,
        Pendiente
    }

    @Enumerated(EnumType.STRING)
    private enumEstado estado;

    // Nullabe = la clave foránea no es anulable
    // Updatable = La columna que contiene la clave foránea no está incluida en UPDATE
    // CascadeType.ALL = si se elimina la entidad Reserva también se eliminan en cascada todas
    //las entidades relacionadas (habitacion y cliente)
    //FecthType. EAGER = se recupera la entidad (cliente) en el momento en que se recupera su entidad padre (reserva)
    @JoinColumn(name = "fk_cliente", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;

    @JoinColumn(name = "fk_habitacion", updatable = false, nullable = false)
    @OneToOne
    private Habitacion habitacion;

    public Reserva() {
    }

    public Reserva(String fechaEntrada, String fechaSalida, String tipoPago, String horaEntrada, String horaSalida, float precioTotal, String estado, Cliente cliente, Habitacion habitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipoPago = tipoPago;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.precioTotal = precioTotal;
        this.estado = enumEstado.valueOf(estado);
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getEstado() {
        return estado.name();
    }

    public void setEstado(String estado) {
        this.estado = enumEstado.valueOf(estado);
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", tipoPago=" + tipoPago + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", precioTotal=" + precioTotal + ", estado=" + estado + ", cliente=" + cliente + ", habitacion=" + habitacion + '}';
    }

}
