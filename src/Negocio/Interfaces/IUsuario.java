package Negocio.Interfaces;

import Negocio.Entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUsuario {

    void crearUsuario(Usuario usuario);

    List<Usuario> buscarUsuario(String usuario, String contraseña);

    String validarRegistro(Usuario usuario);

    String validarInicioSesion(String usuario, String contraseña);

    String encriptarContraseña(String contraseña) throws UnsupportedEncodingException;
}
