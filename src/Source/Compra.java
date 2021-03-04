/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author JDiaz
 */

@Entity
public class Compra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numeroFactura;
    
    @OneToOne
    private Vehiculo vehiculo;
    
    @OneToOne
    private Mecanico mecanico;
    
    @OneToOne
    private Secretario secretario;
    
    @OneToMany
    private List<Consumo> consumos = new ArrayList<>();
    
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<Servicio> servicios = new ArrayList<>();
    
    private long costoTotal=0;
    private long costoManoObr=0;
    private long costoProducto=0;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private LocalDate fecha;

    public Compra() {
    }

    
    
    
    public Compra(String numeroFactura, Vehiculo vehiculo, Mecanico mecanico, Secretario secretario, LocalDate fecha) {
        this.numeroFactura = numeroFactura;
        this.vehiculo = vehiculo;
        this.mecanico = mecanico;
        this.secretario = secretario;
        this.fecha = fecha;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Secretario getSecretario() {
        return secretario;
    }

    public void setSecretario(Secretario secretario) {
        this.secretario = secretario;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(long costoTotal) {
        this.costoTotal = costoTotal;
    }

    public long getCostoManoObr() {
        return costoManoObr;
    }

    public void setCostoManoObr(long costoManoObr) {
        this.costoManoObr = costoManoObr;
    }

    public long getCostoProducto() {
        return costoProducto;
    }

    public void setCostoProducto(long costoProducto) {
        this.costoProducto = costoProducto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

   
    
}
