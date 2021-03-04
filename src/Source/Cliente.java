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
public class Cliente extends Persona {
    


    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(length = 150)
    private String direccion;

    public Cliente() {
        super();
    }

    public Cliente(String correo, String direccion, long identificacion, String nombres, String apellidos, Genero gender, long telefono) {
        super(identificacion, nombres, apellidos, gender, telefono);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "correo=" + correo + ", direccion=" + direccion + '}';
    }

    
    
    



}
