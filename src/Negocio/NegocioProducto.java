package Negocio;

import Datos.ProductoJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Producto;
import Negocio.Interfaces.IProducto;
import com.mysql.jdbc.StringUtils;
import java.util.List;

public class NegocioProducto implements IProducto {
    
    private final ProductoJpaController prodController = new ProductoJpaController();
    private static NegocioProducto negocioProducto;
    
    public static NegocioProducto getNegocioProducto() {
        if (negocioProducto == null) {
            return negocioProducto = new NegocioProducto();
        }
        return negocioProducto;
    }
    
    @Override
    public String validarProducto(String nombre, String marca, String proveedor, String precioCompra, String precioVenta) {
        String mensaje = "ok";
        
        if (StringUtils.isEmptyOrWhitespaceOnly(nombre) || StringUtils.isEmptyOrWhitespaceOnly(marca) || StringUtils.isEmptyOrWhitespaceOnly(proveedor)
                || StringUtils.isEmptyOrWhitespaceOnly(precioCompra) || StringUtils.isEmptyOrWhitespaceOnly(precioVenta)) {
            mensaje = "Debes completar todos los campos.";
        }
        return mensaje;
    }
    
    @Override
    public void crearProducto(Producto prod) {
        prodController.create(prod);
    }
    
    @Override
    public List<Producto> getProductos() {
        return prodController.findProductoEntities();
    }
    
    @Override
    public List<Producto> getProductosDisponibles() {
        return prodController.getProductosDisponiles();
    }
    
    public Producto encontrarProd(Long id) {
        return prodController.findProducto(id);
    }
    
    @Override
    public void editarProd(Producto prod) throws Exception {
        prodController.edit(prod);
    }
    
    @Override
    public void eliminarProd(Long id) throws NonexistentEntityException {
        prodController.destroy(id);
    }
    
    @Override
    public boolean verificarExistenciaProd(String nombre, String marca) {
        return prodController.findProdByName(nombre, marca) != null;
    }
    
    @Override
    public List<Producto> ordenarProductos(String campo) {
        return prodController.productosOrderBy(campo);
    }
    
    @Override
    public List<Producto> buscarProducto(String busqueda) {
        return prodController.buscarProducto(busqueda);
    }
    
    @Override
    public void actualizarStock(Producto prod) throws Exception {
        prodController.edit(prod);
    }
}
