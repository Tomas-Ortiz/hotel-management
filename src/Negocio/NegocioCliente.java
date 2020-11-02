package Negocio;

import Datos.ClienteJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import Negocio.Interfaces.ICliente;
import com.mysql.jdbc.StringUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JTable;

public class NegocioCliente implements ICliente {

    private final ClienteJpaController clienteController = new ClienteJpaController();
    private static NegocioCliente negocioCliente;

    public static NegocioCliente getNegocioCliente() {
        if (negocioCliente == null) {
            return negocioCliente = new NegocioCliente();
        }
        return negocioCliente;
    }

    @Override
    public void crearCliente(Cliente cliente) {
        if (cliente != null) {
            clienteController.create(cliente);
        }
    }

    @Override
    public String validarCliente(String nombre, String apellido, String correo, String nroTelefono, String dni, JDateChooser fechaNacimiento) {
        String mensaje = "ok";

        if (StringUtils.isEmptyOrWhitespaceOnly(nombre) || StringUtils.isEmptyOrWhitespaceOnly(apellido)
                || StringUtils.isEmptyOrWhitespaceOnly(correo) || StringUtils.isEmptyOrWhitespaceOnly(nroTelefono)
                || StringUtils.isEmptyOrWhitespaceOnly(dni) || fechaNacimiento.getDate() == null) {
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

    @Override
    public void eliminarCliente(Long id) throws NonexistentEntityException {
        clienteController.destroy(id);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteController.findClienteEntities();
    }

    @Override
    public List<Cliente> ordenarClientes(String campo) {
        return clienteController.clientesOrderBy(campo);
    }

    @Override
    public List<Cliente> buscarClientes(String busqueda) {
        return clienteController.buscarClientes(busqueda);
    }

    @Override
    public Cliente encontrarCliente(Long id) {
        return clienteController.findCliente(id);
    }

    @Override
    public void impirmirClientes(JTable jtbClientes) throws PrinterException {
        MessageFormat mensajeHeader = new MessageFormat("Reporte de clientes (" + jtbClientes.getRowCount() + ")");
        jtbClientes.print(JTable.PrintMode.NORMAL, mensajeHeader, null);
    }
}
