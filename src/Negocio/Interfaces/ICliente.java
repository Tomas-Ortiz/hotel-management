package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import com.toedter.calendar.JDateChooser;
import java.awt.print.PrinterException;
import java.util.List;
import javax.swing.JTable;

public interface ICliente {

    void crearCliente(Cliente cliente);

    String validarCliente(String nombre, String apellido, String correo, String nroTelefono, String dni, JDateChooser fechaNacimiento);

    Cliente verificarExistenciaCliente(long dni);

    void modificarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Long id) throws NonexistentEntityException;

    List<Cliente> getClientes();

    List<Cliente> ordenarClientes(String campo);

    List<Cliente> buscarClientes(String busqueda);

    Cliente encontrarCliente(Long id);
    
    void impirmirClientes(JTable jtbClientes) throws PrinterException;
}
