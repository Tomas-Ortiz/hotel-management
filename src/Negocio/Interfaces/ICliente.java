package Negocio.Interfaces;

import Negocio.Entidades.Cliente;
import com.toedter.calendar.JDateChooser;

public interface ICliente {

    void crearCliente(Cliente cliente);

    String validarCliente(String nombre, String apellido, String pais, String correo, String nroTelefono, String dni, JDateChooser fechaNacimiento);

    Cliente verificarExistenciaCliente(long dni);
}
