package Source;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 * @author JulianDiaz
 */
@Entity
public class Mantenimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    @OneToOne
    @JoinColumn(name = "placa")
    private Vehiculo vehiculo;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nuip",unique = true)
    private Mecanico mecanico;

    @Temporal(javax.persistence.TemporalType.DATE)
    private LocalDate fecha;
    
    @OneToMany
    private List<Consumo> consumos = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Servicio> servicios = new ArrayList<>();
    
    @Column
    private TipoMantenimiento tipoMantenimiento;
    
    
    public long costoTotal = 0;
    public long manodeObra = 0;
    public long costoProductos = 0;

    public Mantenimiento(Vehiculo vehiculo, Mecanico mecanico, LocalDate fecha) throws IllegalArgumentException {

//        if (vehiculo == null) {
//            throw new IllegalArgumentException("El vehiculo del mantenimiento no puede ser nulo.");
//        }
//        if (fecha == null) {
//            throw new IllegalArgumentException("Fecha no puede ser nula.");
//        }
//        if (consumos == null) {
//            throw new IllegalArgumentException("Lista de consumos no puede ser nula.");
//        }
//        if (servicios == null) {
//            throw new IllegalArgumentException("lista de servicios no puede ser nula.");
//        }

        this.vehiculo = vehiculo;
        this.mecanico = mecanico;
        this.fecha = fecha;
        tipoMantenimiento = TipoMantenimiento.pendiente;
    }

    public TipoMantenimiento getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(TipoMantenimiento tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }
    
    

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }
    
    

    public Mantenimiento() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) throws Exception {
        this.vehiculo = vehiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

public void setMecanico(Mecanico mecanico) throws IllegalArgumentException {          
        this.mecanico = mecanico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) throws IllegalArgumentException {
        this.fecha = fecha;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) throws IllegalArgumentException {
        if (this.consumos == null) {
            throw new IllegalArgumentException("Lista de consumos no puede ser nula.");
        }
        this.consumos = consumos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) throws IllegalArgumentException {
        if (this.servicios == null) {
            throw new IllegalArgumentException("lista de servicios no puede ser nula.");
        }
        this.servicios = servicios;
    }

    public void AddServicio(Servicio servicio) throws Exception {
        for (Servicio s : servicios) {
            if (servicio.getCodigo() == (s.getCodigo())) {
                throw new Exception("Servicio con mismo codigo ya se encuentra registrado.");
            }
        }

        if (servicio == null) {
            throw new Exception("Servicio no puede ser nulo. Seleccione el servicio.");
        }
        
        costoTotal += servicio.getCosto();
        manodeObra += servicio.getCosto();
        
        servicios.add(servicio);
    }
    
    public void RemoveConsumo(Consumo consumo){
        consumos.remove(consumo);
    }

    public void AddConsumo(Consumo consumo) {
        
        costoTotal += consumo.getProducto().getCosto();
        costoProductos += consumo.getProducto().getCosto();
        
        consumos.add(consumo);
    }

    @Override
    public String toString() {

        if (mecanico == null) {
            return "Fecha del mantenimiento: " + fecha
                    + "\nMecanico del mantenimiento: No existe"
                    + "\nPropietario del vehiculo con placa ["+vehiculo.getPlaca()+"]: \n" + vehiculo.getCliente().getNombres()
                    + "" + vehiculo.getCliente().getApellidos() + " Numero de identificacion C.C: " + vehiculo.getCliente().getIdentificacion()
                    + "\nServicios del mantenimiento: \n"+ servicios
                    + "\nConsumos del mantenimiento a realizar: \n"+ consumos;
        }

        return "Fecha del mantenimiento: " + fecha
                + "\nMecanico del mantenimiento: " + mecanico.getNombres() + " " + mecanico.getApellidos()
                + "\nPropietario del vehiculo con placa ["+vehiculo.getPlaca()+"]: \n" + vehiculo.getCliente().getNombres()
                + " " + vehiculo.getCliente().getApellidos() + " C.C: " + vehiculo.getCliente().getIdentificacion()
                + "\nServicios del mantenimiento: \n"+ servicios
                + "\nConsumos del mantenimiento a realizar: \n"+ consumos;
    }

    public Servicio BuscarServicio(int codigo) {
        for (Servicio service : servicios) {
            if (service.getCodigo() == codigo){
                return service;
            }

        }
        return null;
    }
    
    public Consumo BuscarConsumo(long ident){
        for(Consumo c : consumos){
            if(c.getIdentificacion()==ident){
                return c;
            }
        }
        return null;
    }
    
    public long getCostoTotal() {
        return costoTotal;
    }

    public long getManodeObra() {
        return manodeObra;
    }

    public long getCostoProductos() {
        return costoProductos;
    }
    
}
