package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "reservas_productos")
public class ReservaProducto implements Serializable {

    // Indica una clase con clave primaria compuesta
    @EmbeddedId
    private ReservaProductoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reservaId")
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId")
    private Producto producto;

    private int cantProducto;
    private float precioTotal;

    private ReservaProducto() {
    }

    public ReservaProducto(Reserva reserva, Producto producto, int cantProducto, float precioTotal) {
        this.reserva = reserva;
        this.producto = producto;
        this.cantProducto = cantProducto;
        this.precioTotal = precioTotal;
        this.id = new ReservaProductoId(reserva.getId(), producto.getId());
    }

    public ReservaProductoId getId() {
        return id;
    }

    public void setId(ReservaProductoId id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Reserva_Producto{" + "id=" + id + ", reserva=" + reserva + ", producto=" + producto + ", cantProducto=" + cantProducto + ", precioTotal=" + precioTotal + '}';
    }
}
