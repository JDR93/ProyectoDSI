package Source;

import Persistence.ClienteJpaController;
import Persistence.CompraJpaController;
import Persistence.ConsumoJpaController;
import Persistence.GerenteJpaController;
import Persistence.MantenimientoJpaController;

import Persistence.MecanicoJpaController;
import Persistence.ProductoJpaController;

import Persistence.SecretarioJpaController;
import Persistence.ServicioJpaController;
import Persistence.VehiculoJpaController;
import Persistence.exceptions.NonexistentEntityException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 * @author JulianDiaz
 */
public class Taller implements Serializable {

    private long nit;
    private String nombre;

    private File file;

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("WorkShopProyectPU");

    private ConsumoJpaController consumoJpaController = new ConsumoJpaController(factory);
    private ServicioJpaController servicioJpaController = new ServicioJpaController(factory);
    private ProductoJpaController productoJpaController = new ProductoJpaController(factory);
    private MantenimientoJpaController mantenimientoJpaController = new MantenimientoJpaController(factory);
    private VehiculoJpaController vehiculoJpaController = new VehiculoJpaController(factory);

    private ClienteJpaController clienteJpaController = new ClienteJpaController(factory);
    private MecanicoJpaController mecanicoJpaController = new MecanicoJpaController(factory);
    private SecretarioJpaController secretarioJpaController = new SecretarioJpaController(factory);
    private GerenteJpaController gerenteJpaController = new GerenteJpaController(factory);

    private CompraJpaController compraJpaController = new CompraJpaController(factory);

//    private ArrayList<Vehiculo> vehiculosAtendidos = new ArrayList<>();
//    private ArrayList<Mantenimiento> Mpendientes = new ArrayList<>();
//    private ArrayList<Mantenimiento> Mrealizados = new ArrayList<>();
//    private ArrayList<Persona> mecanicos = new ArrayList<>();
//    private ArrayList<Servicio> servicios = new ArrayList<>();
//    private ArrayList<Producto> productos = new ArrayList<>();
//    private ArrayList<Consumo> consumos = new ArrayList<>();
    public EntityManagerFactory getFactory() {
        return factory;
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Taller() {

    }

    public Taller(long nit, String nombre) throws Exception {

        if (nit <= 0) {
            throw new Exception("Numero nit no valido. tiene que ser mayor a cero.");
        }
        if (nombre == null || "".equals(nombre.trim())) {
            throw new Exception("Nombre nulo o cadena de texto vacia.");
        }

//        if (vehiculosAtendidos == null) {
//            throw new IllegalArgumentException("Lista de vehiculos nula");
//        }
//        if (Mpendientes == null) {
//            throw new IllegalArgumentException("Lista de mantenimientos pendientes nula");
//        }
//        if (Mrealizados == null) {
//            throw new IllegalArgumentException("Lista de de mantenimientos realizados nula");
//        }
//        if (servicios == null) {
//            throw new IllegalArgumentException("Lista de servicios nula");
//        }
//        if (productos == null) {
//            throw new IllegalArgumentException("Lista de productos nula");
//        }
//        if (mecanicos == null) {
//            throw new IllegalArgumentException("Lista de mecanicos nula");
//        }
//        if (consumos == null) {
//            throw new IllegalArgumentException("Lista de consumos nula");
//        }
        this.nit = nit;
        this.nombre = nombre;

    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) throws Exception {
        if (this.nit <= 0) {
            throw new Exception("Numero nit no valido. tiene que ser mayor a cero.");
        }
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (this.nombre == null || "".equals(nombre.trim())) {
            throw new Exception("Nombre nulo o cadena de texto vacia.");
        }
        this.nombre = nombre;
    }

    public List<Vehiculo> getVehiculosAtendidos() {
//        return vehiculosAtendidos;
        return vehiculoJpaController.findVehiculoEntities();
    }

//    public void setVehiculosAtendidos(ArrayList<Vehiculo> vehiculosAtendidos) throws IllegalArgumentException {
//        if (this.vehiculosAtendidos == null) {
//            throw new IllegalArgumentException("Lista de vehiculos nula");
//        }
//        this.vehiculosAtendidos = vehiculosAtendidos;
//    }
    public List<Mantenimiento> getMpendientes() {
//        return Mpendientes;
        return mantenimientoJpaController.findMantenimientoEntities();
    }

//    public void setMpendientes(ArrayList<Mantenimiento> Mpendientes) throws IllegalArgumentException {
//        if (this.Mpendientes == null) {
//            throw new IllegalArgumentException("Lista de mantenimientos pendientes nula");
//        }
//        this.Mpendientes = Mpendientes;
//    }
//    public List<Mantenimiento> getMrealizados() {
//        return Mrealizados;
////        return mantenimientoRJpaController.findMantenimientoEntities();
//    }
//
//    public void setMrealizados(ArrayList<Mantenimiento> Mrealizados) throws IllegalArgumentException {
//        if (this.Mrealizados == null) {
//            throw new IllegalArgumentException("Lista de de mantenimientos realizados nula");
//        }
//        this.Mrealizados = Mrealizados;
//    }
    public List<Servicio> getServicios() {
//        return servicios;
        return servicioJpaController.findServicioEntities();
    }

//    public void setServicios(ArrayList<Servicio> servicios) throws IllegalArgumentException {
//        if (this.servicios == null) {
//            throw new IllegalArgumentException("Lista de servicios nula");
//        }
//        this.servicios = servicios;
//    }
    public List<Producto> getProductos() {
//        return productos;
        return productoJpaController.findProductoEntities();
    }

//    public void setProductos(ArrayList<Producto> productos) throws IllegalArgumentException {
//        if (this.productos == null) {
//            throw new IllegalArgumentException("Lista de productos nula");
//        }
//        this.productos = productos;
//    }
    public List<Mecanico> getMecanicos() {
//        return mecanicos;
        return mecanicoJpaController.findMecanicoEntities();
    }

    public List<Gerente> getGerentes() {
//        return Mpendientes;
        return gerenteJpaController.findGerenteEntities();
    }

    public void addGerente(Gerente g) {
        gerenteJpaController.create(g);
    }

    public List<Secretario> getSecretarios() {
//        return Mpendientes;
        return secretarioJpaController.findSecretarioEntities();
    }

    public void addSecretario(Secretario s) {
        secretarioJpaController.create(s);
    }

    public void removeSecretario(Secretario s) {
        try {
            secretarioJpaController.destroy(s.getPk());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Secretario buscarSecretario(String usuario) throws Exception {
        try {
            for (Secretario s : getSecretarios()) {
                if (s.getUsuario().equals(usuario)) {
                    return s;
                }

            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
        throw new Exception("Usuario o contraseña incorrectos");
    }
    //    public void setConsumos(ArrayList<Consumo> consumos) {
    //        this.consumos = consumos;
    //    }
    //    public void setMecanicos(ArrayList<Persona> mecanicos) throws IllegalArgumentException {
    //        if (this.getMecanicos() == null) {
    //            throw new IllegalArgumentException("Lista de mecanicos nula");
    //        }
    //        this.mecanicos = mecanicos;
    //    }

    public void AddVehiculoAntendido(Vehiculo vehiculoA) throws Exception {
//        if (vehiculosAtendidos.contains(vehiculoA)) {
//            throw new Exception("Vehiculo ya se encuentra en el listado de vehiculos atendidos.");
//        }
        for (Vehiculo v : getVehiculosAtendidos()) {
            if (v.getPlaca().equals(vehiculoA.getPlaca())) {
                throw new Exception("Vehiculo con placa [" + v.getPlaca() + "] ya se encuentra en el registro de vehiculos atendidos.");
            }
        }

//        vehiculosAtendidos.add(vehiculoA);
        vehiculoJpaController.create(vehiculoA);
    }

    public void RemoveVehiculoAtendido(Vehiculo v) throws Exception {
//        if (vehiculosAtendidos.contains(v)) {
//            vehiculosAtendidos.remove(v);
//        } else {
//            throw new Exception("Vehiculo no encontrado.");
//        }
        vehiculoJpaController.destroy(v.getPlaca());
    }

    public void AddMantenimientoPendiente(Mantenimiento pendiente) throws Exception, IllegalAccessError {

        if (pendiente.getServicios().isEmpty()) {
            throw new Exception("Mantenimiento no puede ser ingresado sin servicios.");
        }
        if (pendiente.getVehiculo() == null) {
            throw new Exception("Mantenimiento no puede ser registrado sin vehiculo.");
        }

        for (Mantenimiento m : this.getMpendientes()) {
            if (m.getVehiculo().getPlaca().equals(pendiente.getVehiculo().getPlaca())) {
                throw new Exception("Vehiculo con placa [" + pendiente.getVehiculo().getPlaca() + "] ya se encuentra en el listado de mantenimientos pendientes.");
            }
        }

//        Mpendientes.add(pendiente);
        mantenimientoJpaController.create(pendiente);
        pendiente.setTipoMantenimiento(TipoMantenimiento.pendiente);
        mantenimientoJpaController.edit(pendiente);
    }

    public void RemoveMantenimientoPendiente(Mantenimiento Mpend) throws Exception {
//        if (this.getMpendientes().contains(Mpend)) {
//            this.getMpendientes().remove(Mpend);
//        } else {
//            throw new Exception("Mantenimiento pendiente no encontrado.");
//        }
        mantenimientoJpaController.destroy(Mpend.getPk());
    }

//    public void AddMantenimientoRealizado(Mantenimiento realizado) throws Exception {
//
//        if (Mrealizados.contains(realizado)) {
//            throw new Exception("Manteniemiento ya se encuentra en el listado de mantenimientos realizados.");
//        }
//        Mrealizados.add(realizado);
////        mantenimientoRJpaController.create(realizado);
//    }
//
//    public void RemoveMantenimientoRealizado(Mantenimiento Mreali) throws Exception {
//        if (Mrealizados.contains(Mreali)) {
//            Mrealizados.remove(Mreali);
//        } else {
//            throw new Exception("Mantenimiento realizado no encontrado.");
//        }
////        mantenimientoRJpaController.destroy(Mreali.getPk());
//    }
    public void AddServicio(Servicio servicio) throws Exception {

//        for (Servicio s : servicios) {
//            if (servicio.getCodigo().equals(s.getCodigo())) {
//                throw new Exception("Servicion con mismo codigo ya se encuentra registrado.");
//            }
//        }
//        if (servicio == null) {
//            throw new Exception("Servicio no puede ser nulo.");
//        }
//        servicios.add(servicio);
        servicioJpaController.create(servicio);

    }

    public void RemoveServicio(Servicio s) throws Exception {
//        if (servicios.contains(s)) {
//            servicios.remove(s);
//        } else {
//            throw new Exception("Servicio no encontrado.");
//        }
        servicioJpaController.destroy(s.getCodigo());
    }

    public void AddProducto(Producto producto) throws Exception {
//        if (productos.contains(producto)) {
//            throw new Exception("Producto ya se encuentra en el listado de productos registrados.");
//        }
//        productos.add(producto);

        productoJpaController.create(producto);

    }

    public void RemoveProducto(Producto p) throws Exception {
//        if (productos.contains(p)) {
//            productos.remove(p);
//        } else {
//            throw new Exception("Producto no encontrado.");
//        }
        productoJpaController.destroy(p.getCodigo());
    }

//    public void AddConsumo(Consumo consume) throws Exception {
////        if (consumos.contains(consume)) {
////            throw new Exception("Consumo ya se encuentra en el listado de consumos registrados.");
////        }
////        consumos.add(consume);
//        consumoJpaController.create(consume);
//    }
//    public void RemoveConsumo(Consumo c) throws Exception {
////        if (consumos.contains(consume)) {
////            consumos.remove(consume);
////        } else {
////            throw new Exception("Consumo no encontrado.");
////        }
//        consumoJpaController.destroy(c.getPk());
//
//    }
    public int BuscarConsumo(Mantenimiento m, int ident) throws Exception {
        for (int i = 0; i <= m.getConsumos().size(); i++) {
            if (ident == m.getConsumos().get(i).getIdentificacion()) {
                return ident;
            }
        }
        throw new Exception("No se encontro producto.");
    }

    public void AddMecanico(Mecanico mecanico) throws Exception {
//        if (mecanicos.contains(person)) {
//            throw new Exception("Mecanico ya se encuentra registrado en el taller.");
//        }
//        mecanicos.add(person);{
        mecanicoJpaController.create(mecanico);
    }

    public void RemoveMecanico(Mecanico mecanico) throws Exception {
//        if (mecanicos.contains(person)) {
//            mecanicos.remove(person);
//        } else {
//            throw new Exception("Mecanico no encontrado.");
//        }
        mecanicoJpaController.destroy((int) mecanico.getIdentificacion());
    }

    public ArrayList<Mantenimiento> ListMecanicosNoAsignados() {

        ArrayList<Mantenimiento> list = new ArrayList<>();

        for (int i = 0; i < this.getMpendientes().size(); i++) {
            if (this.getMpendientes().get(i).getMecanico() == null) {

                list.add(this.getMpendientes().get(i));
            }
        }
        return list;
    }

    public Vehiculo BuscarVEHMantenimientoPendiente(String placa) throws Exception {
        for (int i = 0; i < this.getMpendientes().size(); i++) {
            if (this.getMpendientes().get(i).getVehiculo().getPlaca().equals(placa)) {
                return this.getMpendientes().get(i).getVehiculo();
            }
        }
        throw new Exception("Vehiculo con placa " + placa + " no Encontrado.");
    }

    public Vehiculo BuscarVehAtendido(String placa) throws Exception {
        for (Vehiculo veh : getVehiculosAtendidos()) {
            if (veh.getPlaca().equalsIgnoreCase(placa)) {
                return veh;
            }
        }
        throw new Exception("Vehiculo con placa " + placa + " no Encontrado.");
    }

    public Mecanico BuscarMecanico(long nuid) throws Exception {
        for (Mecanico m : this.getMecanicos()) {
            if (m.getIdentificacion() == nuid) {
                return m;
            }
        }
        throw new Exception("No encontrado.");
    }

    public boolean TieneMantenimiento(Persona mecanico) {

        int acu = 0;

        for (int i = 0; i < this.getMpendientes().size(); i++) {
            if (this.getMpendientes().get(i).getMecanico() == mecanico) {
                acu++;
            }

        }
        if (acu == 1) {
            return true;
        }
        return false;
    }

    public List<Mecanico> MecanicosDisponibles() {
        List MecanicosDis = new ArrayList();

        for (Mecanico m : this.getMecanicos()) {
            if (m.getDisponiblidad() == Disponibilidad.disponible) {
                MecanicosDis.add(m);
            };

        }
        return MecanicosDis;
    }

    // Verifica si la placa del vehiculo como parametro es encontrado en algun mantenimiento
    // en el caso de encontrarlo devuelve el mantenimiento que se encuentra en el
    // listado de mantenimientos pendientes.
    public Mantenimiento BuscarMantenimientoPend(String band) throws ExceptionInInitializerError {
        for (Mantenimiento m : this.getMpendientes()) {
            if (m.getVehiculo().getPlaca().equalsIgnoreCase(band)) {
                return m;
            }
        }
        throw new ExceptionInInitializerError("Vehiculo con placa " + band + " no encontrado.");
    }

    public List<Mantenimiento> mantenimientosPendNoMecanico() {
        List mantenimientos = new ArrayList();

        for (Mantenimiento m : this.getMpendientes()) {
            if (m.getMecanico() == null) {
                mantenimientos.add(m);
            }
        }
        return mantenimientos;
    }

    public List<Mantenimiento> mantenimientosPendSiMecanico() {
        List mantenimientos = new ArrayList();

        for (Mantenimiento m : this.getMpendientes()) {
            if (m.getMecanico() != null) {
                mantenimientos.add(m);
            }
        }
        return mantenimientos;
    }

    public Producto BuscarProducto(int codigo) throws Exception {
        for (int i = 0; i < this.getProductos().size(); i++) {
            if (this.getProductos().get(i).getCodigo() == codigo) {
                return this.getProductos().get(i);
            }
        }
        throw new Exception("Producto no encontrado.");
    }

    public Gerente buscarGerente(String usuario) throws Exception {
        for (int i = 0; i < this.getGerentes().size(); i++) {
            if (this.getGerentes().get(i).getUsuario().equals(usuario)) {
                return this.getGerentes().get(i);
            }
        }
        throw new Exception("No se encontreo gerente");
    }

    public List<Consumo> getConsumos() {
        return consumoJpaController.findConsumoEntities();
    }

    public void EditMantenimiento(Mantenimiento mantenimiento) throws Exception {
        mantenimiento.getMecanico().setDisponibilidad(Disponibilidad.ocupado);
        mantenimientoJpaController.edit(mantenimiento);

        mecanicoJpaController.edit(mantenimiento.getMecanico());

    }

    public void AddMantenimientoRealizado(Mantenimiento mantenimiento) throws Exception {
        mantenimiento.setTipoMantenimiento(TipoMantenimiento.realizado);
        mantenimiento.getMecanico().setDisponibilidad(Disponibilidad.disponible);
        mecanicoJpaController.edit(mantenimiento.getMecanico());
        mantenimientoJpaController.edit(mantenimiento);
    }

    String data = null;

    public void Reporte() {

        BufferedWriter bw = null;
        FileWriter fw = null;

        Calendar fecha = new GregorianCalendar();

        try {
            data = "Hola stackoverflow.com...";
            file = new File(fecha.get(Calendar.HOUR_OF_DAY) + "."
                    + fecha.get(Calendar.MINUTE) + "."
                    + fecha.get(Calendar.SECOND) + "."
                    + fecha.get(Calendar.MILLISECOND) + ".txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();

            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            System.out.println("información agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void EditConsumoMantenimiento(Mantenimiento mant) throws Exception {
        mantenimientoJpaController.edit(mant);
    }
    
    
    public void AgregarCompra(Compra c) throws Exception{
        compraJpaController.create(c);
    }
    
    

//    public void AddConsumoMantenimiento(Mantenimiento m, Consumo c) throws Exception {
//        m.AddConsumo(c);
//        mantenimientoJpaController.edit(m);
//    }
//    public void ModificarReporte() {
//        
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//        
//        if (!file.exists()) {
//                file.createNewFile();
//            }
//            // flag true, indica adjuntar información al archivo.
//            fw = new FileWriter(file.getAbsoluteFile(), true);
//            bw = new BufferedWriter(fw);
//            bw.write(data);
//            System.out.println("información agregada!");
//
//    }
//    public void CleanMantenimiento(Mantenimiento mantenimiento) throws Exception {
//        if (mantenimiento.getTipoMantenimiento() == TipoMantenimiento.realizado) {
//            mantenimiento.setConsumos(null);
//            mantenimiento.setServicios(null);
//            mantenimiento.setFecha(null);
//            mantenimiento.setMecanico(null);
//            
//            mantenimientoJpaController.edit(mantenimiento);  
//        }
//
//    }
}
