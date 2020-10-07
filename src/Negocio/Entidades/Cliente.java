package Negocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres, apellidos, nacionalidad, correo, fechaNacimiento;
    private Long dni, nroTelefono;

    public Cliente() {

    }

    public Cliente(String nombres, String apellidos, String nacionalidad, String correo, String fechaNacimiento, Long nroTelefono, Long dni) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.nroTelefono = nroTelefono;
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(Long nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", nacionalidad=" + nacionalidad + ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + ", nroTelefono=" + nroTelefono + '}';
    }

}
