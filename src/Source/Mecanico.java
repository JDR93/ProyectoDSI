/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author JDiaz
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "pk")
public class Mecanico extends Persona {

    @Column(nullable = false, unique = true)
    private int codigo;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column
    private Disponibilidad disponiblidad;

    public Mecanico() {
    }

    public Mecanico(int codigo, String usuario, String password, String correo, Disponibilidad disponiblidad, long identificacion, String nombres, String apellidos, Genero gender, long telefono) {
        super(identificacion, nombres, apellidos, gender, telefono);
        this.codigo = codigo;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.disponiblidad = disponiblidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Disponibilidad getDisponiblidad() {
        return disponiblidad;
    }

    public void setDisponibilidad(Disponibilidad disponiblidad) {
        this.disponiblidad = disponiblidad;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] "+getNombres()+" "+getApellidos()+ " |Correo|: "+getCorreo()+" |Contacto|: "+getTelefono();
    }

}
