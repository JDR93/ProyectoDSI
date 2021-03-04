package Source;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author JulianDiaz
 */
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pk;

    @Column
    private long identificacion;

    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column
    private Genero gender;

    @Column(nullable = false)
    private long telefono;

    

    public Persona(long identificacion, String nombres, String apellidos, Genero gender, long telefono) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.gender = gender;
        this.telefono = telefono;
    }

    public Persona() {
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
    
    
    
    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
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

    public Genero getGender() {
        return gender;
    }

    public void setGender(Genero gender) {
        this.gender = gender;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {

        return "CC: "+identificacion+" Nombre: " + nombres + " " + apellidos + " -- Telefono: " + telefono;

    }

}
