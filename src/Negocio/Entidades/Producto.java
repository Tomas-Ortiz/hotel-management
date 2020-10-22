package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "productos")
@XmlRootElement
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, marca, proveedor;
    private int stock;
    private float precioCompra;
    private float precioVenta;

    enum enumCategoria {
        Alimento,
        Bebida
    }
    @Enumerated(EnumType.STRING)
    private enumCategoria categoria;

    public Producto() {
    }

    public Producto(String nombre, String marca, String categoria, int stock, float precioCompra, String proveedor, float precioVenta) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = enumCategoria.valueOf(categoria);
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
        this.precioVenta = precioVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria.name();
    }

    public void setCategoria(String categoria) {
        this.categoria = enumCategoria.valueOf(categoria);
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", proveedor=" + proveedor + ", stock=" + stock + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", categoria=" + categoria + '}';
    }

}
