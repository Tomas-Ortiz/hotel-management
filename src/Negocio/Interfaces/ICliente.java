package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import com.toedter.calendar.JDateChooser;

public interface ICliente {

    void crearCliente(Cliente cliente);

    String validarCliente(String nombre, String apellido, String correo, String nroTelefono, String dni, JDateChooser fechaNacimiento);

    Cliente verificarExistenciaCliente(long dni);

    void modificarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Long id) throws NonexistentEntityException;
}
