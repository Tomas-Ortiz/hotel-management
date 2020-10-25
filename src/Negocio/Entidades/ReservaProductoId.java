package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservaProductoId implements Serializable {

    @Column(name = "reserva_id")
    private Long reservaId;
    @Column(name = "producto_id")
    private Long productoId;

    private ReservaProductoId() {
    }

    public ReservaProductoId(Long reservaId, Long productoId) {
        this.reservaId = reservaId;
        this.productoId = productoId;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "ReservaProductoId{" + "reservaId=" + reservaId + ", productoId=" + productoId + '}';
    }
}
