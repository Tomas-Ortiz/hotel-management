package Negocio.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private float precioAlojamiento, precioProductos, precioTotal;

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

    @JoinColumn(name = "fk_habitacion", nullable = false)
    @OneToOne
    private Habitacion habitacion;

    // Si se elimina una reserva, se eliminarán en cascada todos los productos asociados a la misma
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaProducto> productos = new ArrayList<>();

    public Reserva() {
    }

    public Reserva(String fechaEntrada, String fechaSalida, String tipoPago, String horaEntrada, String horaSalida, String estado, Cliente cliente, Habitacion habitacion, float precioAlojamiento, float precioProductos, float precioTotal) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipoPago = tipoPago;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.precioAlojamiento = precioAlojamiento;
        this.precioProductos = precioProductos;
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

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public float getPrecioAlojamiento() {
        return precioAlojamiento;
    }

    public void setPrecioAlojamiento(float precioAlojamiento) {
        this.precioAlojamiento = precioAlojamiento;
    }

    public float getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(float precioProductos) {
        this.precioProductos = precioProductos;
    }

    public List<ReservaProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<ReservaProducto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto, int cantProd, float precioTotal) {
        ReservaProducto reservaProducto = new ReservaProducto(this, producto, cantProd, precioTotal);
        // Asociacion bidireccional
        productos.add(reservaProducto);
        producto.getReservas().add(reservaProducto);
    }

    public void removeProducto(Producto producto) {
        for (Iterator<ReservaProducto> iterator = productos.iterator();
                iterator.hasNext();) {
            ReservaProducto reservaProducto = iterator.next();
            if (reservaProducto.getReserva().equals(this) && reservaProducto.getProducto().equals(producto)) {
                iterator.remove();
                reservaProducto.getProducto().getReservas().remove(reservaProducto);
                reservaProducto.setReserva(null);
                reservaProducto.setProducto(null);
            }
        }
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", tipoPago=" + tipoPago + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", precioAlojamiento=" + precioAlojamiento + ", precioProductos=" + precioProductos + ", precioTotal=" + precioTotal + ", estado=" + estado.name() + ", cliente=" + cliente + ", habitacion=" + habitacion + ", productos=" + productos + '}';
    }
}
