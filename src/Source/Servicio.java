package Source;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author JulianDiaz
 */

@Entity
public class Servicio implements Serializable{
    
    @Id
    private int codigo;
    
    @Column(nullable = false,length = 80,unique = true)
    private String nombres;
    
    @Column(nullable = false)
    private int costo;

    public Servicio() {
    }

    public Servicio(int codigo, String nombres, int costo) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.costo = costo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return codigo+" - " + nombres + " - Costo: " + costo+ "\n";
    }
    
    
    
    
}
