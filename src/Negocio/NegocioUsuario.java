package Negocio;

import Datos.UsuarioJpaController;
import Negocio.Entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

public class NegocioUsuario {

    private UsuarioJpaController usuarioController;

    public NegocioUsuario() {
        usuarioController = new UsuarioJpaController();
    }

    public void crearUsuario(Usuario usuario) {

        if (usuario != null) {
            usuarioController.create(usuario);
        }
    }

    public String validarRegistro(Usuario usuario) {

        String mensaje = "ok";

        if (usuario.getNombre().equals("") || usuario.getApellido().equals("") || usuario.getEmail().equals("")
                || usuario.getUsuario().equals("") || usuario.getContraseña().equals("")
                || usuario.getConfirmacionContraseña().equals("")) {
            mensaje = "Debe completar todos los campos.";
        } else if (!usuario.getContraseña().equals(usuario.getConfirmacionContraseña())) {

            mensaje = "Las contraseñas no coinciden.";

        } else if (usuario.getContraseña().length() < 8) {
            mensaje = "La contraseña debe tener al menos 8 caracteres.";

        } else if (UtilidadGeneral.esNumerico(usuario.getNombre())
                || UtilidadGeneral.esNumerico(usuario.getApellido())) {

            mensaje = "El nombre o apellido no pueden contener números.";
        }

        return mensaje;
    }

    public String validarInicioSesion(String usuario, String contraseña) {

        String mensaje = "ok";
        if (usuario.equals("") || contraseña.equals("")) {
            mensaje = "Debe completar todos los campos.";
        }
        return mensaje;
    }

    public List<Usuario> buscarUsuario(String usuario, String contraseña) {
        return usuarioController.findUsuario(usuario, contraseña);
    }

    public String encriptarContraseñaUsuario(String contraseña) throws UnsupportedEncodingException {

        return Base64.getEncoder().encodeToString(contraseña.getBytes("utf-8"));

    }

}
