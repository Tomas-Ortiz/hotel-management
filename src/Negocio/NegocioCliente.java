package Negocio;

import Datos.ClienteJpaController;
import Negocio.Entidades.Cliente;
import Negocio.Interfaces.ICliente;
import com.toedter.calendar.JDateChooser;

public class NegocioCliente implements ICliente {
    
    private final ClienteJpaController clienteController;
    
    public NegocioCliente() {
        clienteController = new ClienteJpaController();
    }
    
    @Override
    public void crearCliente(Cliente cliente) {
        if (cliente != null) {
            clienteController.create(cliente);
        }
    }
    
    @Override
    public String validarCliente(String nombre, String apellido, String pais, String correo, String nroTelefono, String dni, JDateChooser fechaNacimiento) {
        
        String mensaje = "ok";
        
        if (nombre.equals("") || apellido.equals("") || pais.equals("") || correo.equals("") || nroTelefono.equals("")
                || dni.equals("") || fechaNacimiento.getDate() == null) {
            mensaje = "Debes completar todos los campos.";
        }
        
        return mensaje;
    }
    
    @Override
    public Cliente verificarExistenciaCliente(long dni) {
        return clienteController.findClienteByDni(dni);
    }
    
    @Override
    public void modificarCliente(Cliente cliente) throws Exception {
        clienteController.edit(cliente);
    }
}
