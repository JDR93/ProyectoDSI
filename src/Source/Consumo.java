package Source;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author JulianDiaz
 */
@Entity
public class Consumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private int cantidad;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER)
    private Servicio servicio;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne (fetch = FetchType.EAGER)
    private Producto producto;

    public Consumo() {
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }
    
    

    public Consumo(int cantidad, Servicio servicio, Producto producto) throws IllegalArgumentException, Exception {

        if (cantidad < 0) {
            throw new Exception("Cantidad tiene que ser mayor o igual a cero.");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Servicio no puede ser nulo.");
        }
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        }

        this.cantidad = cantidad;
        this.servicio = servicio;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws Exception {
        if (this.cantidad < 0) {
            throw new Exception("Cantidad tiene que ser mayor o igual a cero.");
        }
        this.cantidad = cantidad;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) throws IllegalArgumentException {
        this.servicio = servicio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) throws IllegalArgumentException {
        this.producto = producto;
    }

    @Override
    public String toString() {

        return "\nCONSUMO " + "\nProducto utilizado: " + producto + "  - cantidad: " + cantidad + "\n";
    }

}
