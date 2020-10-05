package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalles;

    enum enumTipo {

        Individual,
        Doble,
        Matrimonial
    }

    enum enumEstado {

        Disponible,
        Ocupada,
        Limpieza,
        Reparación
    }

    @Enumerated(EnumType.STRING)
    private enumEstado estado;
    private int numero;
    private float precioDia;
    @Enumerated(EnumType.STRING)
    private enumTipo tipo;

    public Habitacion() {
    }

    public Habitacion(int numero, String tipo, String estado, String detalles, float precioDia) {
        this.numero = numero;
        this.tipo = enumTipo.valueOf(tipo);
        this.estado = enumEstado.valueOf(estado);
        this.detalles = detalles;
        this.precioDia = precioDia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public float getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(float precioDia) {
        this.precioDia = precioDia;
    }

    public String getEstado() {
        return estado.name();
    }

    public void setEstado(String estado) {
        this.estado = enumEstado.valueOf(estado);
    }

    public String getTipo() {
        return tipo.name();
    }

    public void setTipo(String tipo) {
        this.tipo = enumTipo.valueOf(tipo);
    }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", detalles=" + detalles + ", precioDia=" + precioDia + ", estado=" + estado.name() + ", tipo=" + tipo.name() + '}';
    }
}
