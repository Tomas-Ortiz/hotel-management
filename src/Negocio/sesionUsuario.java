package Negocio;

import Negocio.Entidades.Usuario;

public class sesionUsuario {

    private Usuario usuario;

    private static sesionUsuario sesionUsuario;

    private sesionUsuario() {
    }

    public static sesionUsuario getSesionUsuario() {
        if (sesionUsuario == null) {
            sesionUsuario = new sesionUsuario();
        }
        return sesionUsuario;
    }

    public sesionUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "sesionUsuario{" + "usuario=" + usuario + '}';
    }

}
