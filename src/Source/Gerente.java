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
public class Gerente extends Persona {

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 50)
    private String password;

    public Gerente() {
    }

    public Gerente(String correo, String usuario, String password, long identificacion, String nombres, String apellidos, Genero gender, long telefono) {
        super(identificacion, nombres, apellidos, gender, telefono);
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Override
    public String toString() {
        return "Gerente{" + "correo=" + correo + ", usuario=" + usuario + ", password=" + password + '}';
    }

}
