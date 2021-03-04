
import GUI.MainWindow;
import Source.Cliente;
import Source.Disponibilidad;
import Source.Genero;
import Source.Gerente;
import Source.Mecanico;
import Source.Producto;
import Source.Secretario;
import Source.Servicio;
import Source.Taller;
import Source.TipoCargo;
import Source.TipoVehiculo;
import Source.Vehiculo;

/**
 * @author JulianDiaz
 */
public class Main {

    public static void main(String args[]) throws Exception {
        
        Taller taller = new Taller(8990123L, "JDMOTOS.SA");

        Cliente c1 = new Cliente("julian-3001@hotmail.com","Diagonal 25 T6-91",1006341244L, "Julian Andres", "Diaz",Genero.Masculino, 287789L);
        Cliente c2  = new Cliente("jaime2009@mail.com","Calle Simpre viva",1006341245L, "Jaime", "Gomez Urrutia",Genero.Masculino, 287783L);
        Cliente c3 = new Cliente("alejandra2000@kail.com","Calle logica",1006341246L, "Alejandra Sofia", "Ortiz",Genero.Femenino, 247782L);

        Mecanico mecanico1 = new Mecanico(1001,"josePelo93","jose1123","jose3001@hotmail.com",Disponibilidad.disponible,1006341247L, "Jose Angel", "Pelo",Genero.Masculino, 247583L);
        Mecanico mecanico2 = new Mecanico(1002,"lucasRo89","luca123","luca2001@hotmail.com",Disponibilidad.disponible,298764L, "Lucas Rosero", "Guevara",Genero.Masculino, 247583L);
        Mecanico mecanico3 = new Mecanico(1003,"simpson98","homero123","homero1993@hotmail.com",Disponibilidad.disponible,1006341248L, "Homero", "Simpson",Genero.Masculino, 247583L);

        Gerente g1 = new Gerente("julian.3001@hotmail.com", "jdiaz93", "jhoan_93", 1006341244, "Julian Andres", "Diaz", Genero.Masculino, 2873123);
        
        Vehiculo veh1 = new Vehiculo("RXT-540", "Mazda", "RX-7", (short) 2016, TipoVehiculo.Automovil, c1);
        Vehiculo veh2 = new Vehiculo("WDR-123", "Chevrolet", "Spark GT", (short) 2008, TipoVehiculo.Motocarro, c2);
        Vehiculo veh3 = new Vehiculo("HOW-931", "Ford", "Explorer", (short) 2012, TipoVehiculo.Automovil, c3);

        Servicio servicio1 = new Servicio(1, "Revision del funcionamiento del sistema eléctrico", 12800);
        Servicio servicio2 = new Servicio(2, "Cambio de liquido de frenos delantero y trasero", 13800);
        Servicio servicio3 = new Servicio(3, "Cambio de filtro de aire", 32500);

        Producto producto1 = new Producto(1001, "Aceite para carburador", 22700);
        Producto producto2 = new Producto(1002, "Liquido para frenos", 52400);
        Producto producto3 = new Producto(1003, "Filtro de aire nuevo", 120000);

//        Consumo consumo1 = new Consumo(1, servicio1, producto1);
//        Consumo consumo2 = new Consumo(2, servicio2, producto2);
//        Consumo consumo3 = new Consumo(3, servicio3, producto3);
        
        Secretario s = new Secretario("jaimesito.diaz@hotmail.com", "jaime02", "123", TipoCargo.Inventario, 1006542211, "Jaime", "Diaz", Genero.Masculino, 290831);
        Secretario s1 = new Secretario("laura.gonza@hotmail.com", "laura02", "123", TipoCargo.Ingresos, 1006542212, "Laura", "Gonzales", Genero.Femenino, 290832);
////
//        taller.addSecretario(s1);        
//        taller.addGerente(g1);

         
//        taller.AddVehiculoAntendido(veh1);
//        taller.AddVehiculoAntendido(veh2);
//        taller.AddVehiculoAntendido(veh3);
//
//        taller.AddMecanico(mecanico1);
//        taller.AddMecanico(mecanico2);
//        taller.AddMecanico(mecanico3);
//
//        taller.AddProducto(producto1);
//        taller.AddProducto(producto2);
//        taller.AddProducto(producto3);
//
//        taller.AddServicio(servicio1);
//        taller.AddServicio(servicio2);
//        taller.AddServicio(servicio3);
        

        

        MainWindow main = new MainWindow(taller);
        main.setVisible(true);
        main.setLocationRelativeTo(null);

        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//
    }
}
