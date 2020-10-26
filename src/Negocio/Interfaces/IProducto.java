package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Producto;
import java.util.List;

public interface IProducto {

    void crearProducto(Producto prod);

    List<Producto> getProductos();

    void eliminarProd(Long id) throws NonexistentEntityException;

    void editarProd(Producto prod) throws Exception;

    boolean verificarExistenciaProd(String nombre, String marca);

    String validarProducto(String nombre, String marca, String proveedor, String precioCompra, String precioVenta);

    List<Producto> ordenarProductos(String campo);

    List<Producto> buscarProducto(String busqueda);

    List<Producto> getProductosDisponibles();

    void actualizarStock(Producto prod) throws Exception;
}
