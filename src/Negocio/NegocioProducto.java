package Negocio;

import Datos.ProductoJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Producto;
import Negocio.Interfaces.IProducto;
import com.mysql.jdbc.StringUtils;
import java.util.List;

public class NegocioProducto implements IProducto {
    
    private final ProductoJpaController prodController;
    
    public NegocioProducto() {
        prodController = new ProductoJpaController();
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
}
