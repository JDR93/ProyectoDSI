/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author JDiaz
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "pk")
public class Secretario extends Persona {

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 50)
    private String password;

    @Column
    private TipoCargo tipoDeCargo;

    public Secretario() {
    }

    public Secretario(String correo, String usuario, String password, TipoCargo tipoDeCargo, long identificacion, String nombres, String apellidos, Genero gender, long telefono) {
        super(identificacion, nombres, apellidos, gender, telefono);
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.tipoDeCargo = tipoDeCargo;
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

    public TipoCargo getTipoDeCargo() {
        return tipoDeCargo;
    }

    public void setTipoDeCargo(TipoCargo tipoDeCargo) {
        this.tipoDeCargo = tipoDeCargo;
    }

    @Override
    public String toString() {
        return "Secretario{" + "correo=" + correo + ", usuario=" + usuario + ", password=" + password + ", tipoDeCargo=" + tipoDeCargo + '}';
    }

}
