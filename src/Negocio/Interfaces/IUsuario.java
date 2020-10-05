package Negocio.Interfaces;

import Negocio.Entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUsuario {

    public void crearUsuario(Usuario usuario);

    public List<Usuario> buscarUsuario(String usuario, String contraseña);

    public String validarRegistro(Usuario usuario);

    public String validarInicioSesion(String usuario, String contraseña);

    public String encriptarContraseña(String contraseña) throws UnsupportedEncodingException;
}
