package Source;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author JulianDiaz
 */

@Entity
public class Vehiculo implements Serializable{
    
    @Id
    private String placa;
    
    @Column(nullable = false,length = 80)
    private String marca;
    
    @Column(nullable = false,length = 80)
    private String linea;
    
    @Column(nullable = false)
    private short anio;
    
    @Column
    private TipoVehiculo tipo;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nuid")
    private Cliente cliente;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, String linea, short anio, TipoVehiculo tipo, Cliente cliente) {

        this.placa = placa;
        this.marca = marca;
        this.linea = linea;
        this.anio = anio;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", marca=" + marca + ", linea=" + linea + ", anio=" + anio + ", tipo=" + tipo + ", cliente=" + cliente + '}';
    }
    
    
    
    
    
}
