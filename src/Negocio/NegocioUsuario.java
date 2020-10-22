package Negocio;

import Datos.UsuarioJpaController;
import Negocio.Entidades.Usuario;
import Negocio.Interfaces.IUsuario;
import com.mysql.jdbc.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

public class NegocioUsuario implements IUsuario {

    private final UsuarioJpaController usuarioController;

    public NegocioUsuario() {
        usuarioController = new UsuarioJpaController();
    }

    @Override
    public void crearUsuario(Usuario usuario) {

        if (usuario != null) {
            usuarioController.create(usuario);
        }
    }

    @Override
    public String validarRegistro(Usuario usuario) {
        String mensaje = "ok";

        if (StringUtils.isEmptyOrWhitespaceOnly(usuario.getNombre()) || StringUtils.isEmptyOrWhitespaceOnly(usuario.getApellido())
                || StringUtils.isEmptyOrWhitespaceOnly(usuario.getEmail()) || StringUtils.isEmptyOrWhitespaceOnly(usuario.getUsuario())
                || StringUtils.isEmptyOrWhitespaceOnly(usuario.getContraseña()) || StringUtils.isEmptyOrWhitespaceOnly(usuario.getConfirmacionContraseña())) {
            mensaje = "Debes completar todos los campos.";
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

    @Override
    public String validarInicioSesion(String usuario, String contraseña) {
        String mensaje = "ok";
        if (StringUtils.isEmptyOrWhitespaceOnly(usuario) || StringUtils.isEmptyOrWhitespaceOnly(contraseña)) {
            mensaje = "Debe completar todos los campos.";
        }
        return mensaje;
    }

    @Override
    public List<Usuario> buscarUsuario(String usuario, String contraseña) {
        return usuarioController.findUsuario(usuario, contraseña);
    }

    @Override
    public String encriptarContraseña(String contraseña) throws UnsupportedEncodingException {

        return Base64.getEncoder().encodeToString(contraseña.getBytes("utf-8"));

    }

}
