package Source;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author JulianDiaz
 */

@Entity
public class Producto implements Serializable{
    
    @Id
    private int codigo;
    
    @Column(nullable = false,length = 80,unique = true)
    private String nombre;
    
    @Column(nullable = false)
    private int costo;

    public Producto() {
    }

    public Producto(int codigo, String nombre, int costo) throws Exception {
        
        if(codigo <= 0){
            throw new Exception("Numero de codigo no valido.");
        }
        
        
        if(costo <= 0){
            throw new Exception("Costo de producto no valido.");
        }
        
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws Exception {   
        
        if(codigo <= 0){
            throw new Exception("Numero de codigo no valido.");
        }
        
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }
    
    public void setCosto(int costo) throws Exception {
        
        if(costo <= 0){
            throw new Exception("Costo de producto no valido.");
        }
        
        this.costo = costo;
    }

    @Override
    public String toString() {
        
        return "\nCodigo:" + codigo + " - nombre: " + nombre + " - costo: " + costo;
    }
    
    
    
    
}
