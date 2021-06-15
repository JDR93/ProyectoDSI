/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Gerente;
import Source.Report;
import Source.Secretario;
import Source.Taller;
import Source.TipoCargo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import rojerusan.RSAnimation;

/**
 *
 * @author julia
 */
public class MainWindow extends javax.swing.JFrame {

    private Taller taller;
    private Secretario secretario;
    private Gerente gerente;
    private Report reporte;
    private int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    private String VariabledeCambio;

    private RegistroProducto registroP = null;
    private IngresoVehiculosUI ingresoVH = null;
    private AsignacionMecanicoUI asignacion = null;
    private RegistroProductosUtilizadosUI registroPro = null;

    private boolean signalCloseButton;
    public Timer timerRegistrarPdt;
    public Timer timerHistory;
    public Timer timerIngresoVeh;
    public Timer timerAsigMec;
    public Timer timerRegiPdtUtilizados;
    public Timer timerFacturacionServ;
    public Timer timerRegServicio;

    public MainWindow(Taller taller) {

        this.taller = taller;
        this.secretario = secretario;
        this.reporte = reporte;

        initComponents();

        // Cambiando icono del programa.
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/logo180px.png")).getImage());

        btnlogOut.setVisible(false);

        IngresoVehiculoListener ingresoVeh = new IngresoVehiculoListener();
        AsignacionMecanicoListener asigMeca = new AsignacionMecanicoListener();
        RegistroProductosUtilizadosListener regPUtilizados = new RegistroProductosUtilizadosListener();
        RegistroProductoListener regProducto = new RegistroProductoListener();
        FacturacionListener facturacion = new FacturacionListener();
        HistorialMantListener historial = new HistorialMantListener();

        this.IngresodeVehiculo.addActionListener(ingresoVeh);
        this.asignacionMecanico.addActionListener(asigMeca);
        this.registroProductos.addActionListener(regPUtilizados);
        this.facturacionSrevicio.addActionListener(facturacion);

        this.btnIngresodeVehiculo.addActionListener(ingresoVeh);
        this.btnAsigMecanico.addActionListener(asigMeca);
        this.btnFacturacionServicio.addActionListener(facturacion);
        this.btnRegistroPUtilizado.addActionListener(regPUtilizados);
        this.btnRegistrodeProductos.addActionListener(regProducto);
        this.btnHistorial.addActionListener(historial);
//        this.btnRegistroServicios.addActionListener(new Re);

        this.acerca.addActionListener(new AboutUIListener());
        this.historialM.addActionListener(historial);

        OcultarMostrarJPanel("ocultar");

        usuarioGerente.addActionListener(new AccesoGerente());
        usuarioSecretario.addActionListener(new AccesoSecretario());

        passGerente.addActionListener(new AccesoGerente());
        passSecretario.addActionListener(new AccesoSecretario());

        this.entrarGerente.addActionListener(new AccesoGerente());
        this.entrarSecretario.addActionListener(new AccesoSecretario());

        this.regProducto.addActionListener(new RegistroProductoListener());

//        Login loginWindow = new Login(taller);
//        jDesktopPane1.add(loginWindow);
//        loginWindow.setVisible(true);
//        loginWindow.setEnabled(false);
        btnlogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                secretario = null;
                nombreSession.setVisible(false);
                btnlogOut.setVisible(false);
                btnSecretario.setVisible(true);
                btnGerente.setVisible(true);
                jlabel.setVisible(true);
                jlabel2.setVisible(true);

                DesactivarComponenetes();
                signalCloseButton = true;
                VariabledeCambio = "";

                try {
                    timerIngresoVeh.stop();
                    timerAsigMec.stop();
                    timerRegiPdtUtilizados.stop();
                    timerFacturacionServ.stop();
                    timerRegistrarPdt.stop();
                    timerRegServicio.stop();
                    timerHistory.stop();
                } catch (Exception exc) {

                }

            }
        });

        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnRegistroServicios = new javax.swing.JButton();
        btnRegistroPUtilizado = new javax.swing.JButton();
        btnRegistrodeProductos = new javax.swing.JButton();
        btnAsigMecanico = new javax.swing.JButton();
        btnIngresodeVehiculo = new javax.swing.JButton();
        btnFacturacionServicio = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jpanel_mesa = new javax.swing.JPanel();
        btnlogOut = new javax.swing.JButton();
        btnSecretario = new javax.swing.JButton();
        jlabel = new javax.swing.JLabel();
        jlabel2 = new javax.swing.JLabel();
        btnGerente = new javax.swing.JButton();
        nombreSession = new javax.swing.JLabel();
        jPanelSecretario = new javax.swing.JPanel();
        btnCloseSecretario = new javax.swing.JButton();
        entrarSecretario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        usuarioSecretario = new javax.swing.JTextField();
        passSecretario = new javax.swing.JPasswordField();
        jlabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelGerente = new javax.swing.JPanel();
        btnCloseGerente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        usuarioGerente = new javax.swing.JTextField();
        entrarGerente = new javax.swing.JButton();
        jlabel4 = new javax.swing.JLabel();
        passGerente = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSolicitudes = new javax.swing.JMenu();
        IngresodeVehiculo = new javax.swing.JMenuItem();
        jMenuMantenimiento = new javax.swing.JMenu();
        asignacionMecanico = new javax.swing.JMenuItem();
        jMenuFacturacion = new javax.swing.JMenu();
        registroProductos = new javax.swing.JMenuItem();
        facturacionSrevicio = new javax.swing.JMenuItem();
        jMenuRegistros = new javax.swing.JMenu();
        regProducto = new javax.swing.JMenuItem();
        regServicio = new javax.swing.JMenuItem();
        regMeacanico = new javax.swing.JMenuItem();
        regSecretarioInv = new javax.swing.JMenuItem();
        regSecretarioIng = new javax.swing.JMenuItem();
        jMenuhistorial = new javax.swing.JMenu();
        historialM = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        acerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setBackground(new java.awt.Color(102, 102, 102));
        jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setEnabled(false);
        jPanel1.setLayout(null);

        btnRegistroServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoServicioOff.png"))); // NOI18N
        btnRegistroServicios.setText("    Registro de Servicio           ");
        btnRegistroServicios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnRegistroServicios.setContentAreaFilled(false);
        btnRegistroServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistroServicios.setEnabled(false);
        btnRegistroServicios.setFocusPainted(false);
        btnRegistroServicios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRegistroServicios.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRegistroServicios.setPreferredSize(new java.awt.Dimension(117, 19));
        btnRegistroServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistroServiciosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistroServiciosMouseExited(evt);
            }
        });
        jPanel1.add(btnRegistroServicios);
        btnRegistroServicios.setBounds(12, 404, 242, 50);

        btnRegistroPUtilizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regputilizadosOff.png"))); // NOI18N
        btnRegistroPUtilizado.setText("    Registro Pdts Utilizados      ");
        btnRegistroPUtilizado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnRegistroPUtilizado.setContentAreaFilled(false);
        btnRegistroPUtilizado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistroPUtilizado.setEnabled(false);
        btnRegistroPUtilizado.setFocusPainted(false);
        btnRegistroPUtilizado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRegistroPUtilizado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRegistroPUtilizado.setPreferredSize(new java.awt.Dimension(167, 19));
        btnRegistroPUtilizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistroPUtilizadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistroPUtilizadoMouseExited(evt);
            }
        });
        jPanel1.add(btnRegistroPUtilizado);
        btnRegistroPUtilizado.setBounds(12, 168, 242, 50);

        btnRegistrodeProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoProductoOff.png"))); // NOI18N
        btnRegistrodeProductos.setText("    Registro de Producto           ");
        btnRegistrodeProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnRegistrodeProductos.setContentAreaFilled(false);
        btnRegistrodeProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrodeProductos.setEnabled(false);
        btnRegistrodeProductos.setFocusPainted(false);
        btnRegistrodeProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRegistrodeProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRegistrodeProductos.setPreferredSize(new java.awt.Dimension(145, 19));
        btnRegistrodeProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrodeProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrodeProductosMouseExited(evt);
            }
        });
        jPanel1.add(btnRegistrodeProductos);
        btnRegistrodeProductos.setBounds(12, 336, 242, 50);

        btnAsigMecanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AsigMecanico.png"))); // NOI18N
        btnAsigMecanico.setText("    Asignar Mecanico            ");
        btnAsigMecanico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnAsigMecanico.setContentAreaFilled(false);
        btnAsigMecanico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsigMecanico.setDisabledIcon(null);
        btnAsigMecanico.setEnabled(false);
        btnAsigMecanico.setFocusPainted(false);
        btnAsigMecanico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAsigMecanico.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAsigMecanico.setPreferredSize(new java.awt.Dimension(197, 35));
        btnAsigMecanico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAsigMecanicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAsigMecanicoMouseExited(evt);
            }
        });
        jPanel1.add(btnAsigMecanico);
        btnAsigMecanico.setBounds(12, 100, 242, 50);

        btnIngresodeVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ingresoVeh.png"))); // NOI18N
        btnIngresodeVehiculo.setText("    Ingreso de vehiculo         ");
        btnIngresodeVehiculo.setAutoscrolls(true);
        btnIngresodeVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnIngresodeVehiculo.setContentAreaFilled(false);
        btnIngresodeVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresodeVehiculo.setDisabledIcon(null);
        btnIngresodeVehiculo.setEnabled(false);
        btnIngresodeVehiculo.setFocusPainted(false);
        btnIngresodeVehiculo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnIngresodeVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnIngresodeVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresodeVehiculoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresodeVehiculoMouseExited(evt);
            }
        });
        jPanel1.add(btnIngresodeVehiculo);
        btnIngresodeVehiculo.setBounds(12, 32, 242, 50);

        btnFacturacionServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnFacturaOff.png"))); // NOI18N
        btnFacturacionServicio.setText("    Facturacion del Servicio      ");
        btnFacturacionServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnFacturacionServicio.setContentAreaFilled(false);
        btnFacturacionServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturacionServicio.setEnabled(false);
        btnFacturacionServicio.setFocusPainted(false);
        btnFacturacionServicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFacturacionServicio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFacturacionServicio.setPreferredSize(new java.awt.Dimension(139, 19));
        btnFacturacionServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFacturacionServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFacturacionServicioMouseExited(evt);
            }
        });
        jPanel1.add(btnFacturacionServicio);
        btnFacturacionServicio.setBounds(12, 236, 242, 50);

        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/historyOff.png"))); // NOI18N
        btnHistorial.setText("    Hitorial de Mantenimientos  ");
        btnHistorial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistorial.setEnabled(false);
        btnHistorial.setFocusPainted(false);
        btnHistorial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHistorial.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnHistorial.setPreferredSize(new java.awt.Dimension(117, 19));
        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorialMouseExited(evt);
            }
        });
        jPanel1.add(btnHistorial);
        btnHistorial.setBounds(12, 472, 242, 50);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradado.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(12, 472, 242, 50);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradado.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(12, 404, 242, 50);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradado.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(12, 336, 242, 50);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradadoOther.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(12, 236, 242, 50);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradadoOther.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(12, 168, 242, 50);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradadoOther.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(12, 100, 242, 50);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnDegradadoOther.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(12, 32, 242, 50);

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 700));
        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menu.png"))); // NOI18N
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 50, 50));

        jpanel_mesa.setLayout(null);

        btnlogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout100px.png"))); // NOI18N
        btnlogOut.setAlignmentX(0.5F);
        btnlogOut.setBorderPainted(false);
        btnlogOut.setContentAreaFilled(false);
        btnlogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogOut.setFocusPainted(false);
        btnlogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlogOutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnlogOutMousePressed(evt);
            }
        });
        jpanel_mesa.add(btnlogOut);
        btnlogOut.setBounds(804, 0, 120, 31);

        btnSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoSecretario.png"))); // NOI18N
        btnSecretario.setBorderPainted(false);
        btnSecretario.setContentAreaFilled(false);
        btnSecretario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSecretario.setFocusPainted(false);
        btnSecretario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSecretarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSecretarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSecretarioMouseExited(evt);
            }
        });
        jpanel_mesa.add(btnSecretario);
        btnSecretario.setBounds(560, 212, 202, 188);

        jlabel.setBackground(new java.awt.Color(102, 102, 102));
        jlabel.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jlabel.setForeground(new java.awt.Color(255, 255, 255));
        jlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabel.setText("Gerente");
        jlabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlabel.setOpaque(true);
        jpanel_mesa.add(jlabel);
        jlabel.setBounds(220, 412, 202, 27);

        jlabel2.setBackground(new java.awt.Color(102, 102, 102));
        jlabel2.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jlabel2.setForeground(new java.awt.Color(255, 255, 255));
        jlabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabel2.setText("Secretario");
        jlabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlabel2.setOpaque(true);
        jpanel_mesa.add(jlabel2);
        jlabel2.setBounds(560, 412, 202, 27);

        btnGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoGerente.png"))); // NOI18N
        btnGerente.setBorderPainted(false);
        btnGerente.setContentAreaFilled(false);
        btnGerente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGerente.setFocusPainted(false);
        btnGerente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGerente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGerenteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGerenteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGerenteMouseExited(evt);
            }
        });
        jpanel_mesa.add(btnGerente);
        btnGerente.setBounds(220, 216, 203, 188);

        nombreSession.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nombreSession.setEnabled(false);
        jpanel_mesa.add(nombreSession);
        nombreSession.setBounds(350, 0, 432, 31);

        jPanelSecretario.setEnabled(false);
        jPanelSecretario.setPreferredSize(new java.awt.Dimension(780, 281));
        jPanelSecretario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCloseSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg"))); // NOI18N
        btnCloseSecretario.setContentAreaFilled(false);
        btnCloseSecretario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCloseSecretario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseSecretarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseSecretarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseSecretarioMouseExited(evt);
            }
        });
        jPanelSecretario.add(btnCloseSecretario, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 16, 28, 28));

        entrarSecretario.setText("Entrar");
        entrarSecretario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entrarSecretario.setFocusPainted(false);
        jPanelSecretario.add(entrarSecretario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 300, -1));

        jLabel1.setText("Usuario:");
        jPanelSecretario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 106, 22));
        jPanelSecretario.add(usuarioSecretario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 179, -1));
        jPanelSecretario.add(passSecretario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 179, -1));

        jlabel3.setText("Contraseña:");
        jPanelSecretario.add(jlabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 106, 22));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login_Gerente.png"))); // NOI18N
        jPanelSecretario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, -1));

        jpanel_mesa.add(jPanelSecretario);
        jPanelSecretario.setBounds(95, 180, 780, 300);

        jPanelGerente.setEnabled(false);
        jPanelGerente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCloseGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg"))); // NOI18N
        btnCloseGerente.setContentAreaFilled(false);
        btnCloseGerente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCloseGerente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseGerenteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseGerenteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseGerenteMouseExited(evt);
            }
        });
        jPanelGerente.add(btnCloseGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 16, 28, 28));

        jLabel3.setText("Usuario:");
        jPanelGerente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 106, 22));
        jPanelGerente.add(usuarioGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 179, -1));

        entrarGerente.setText("Entrar");
        entrarGerente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelGerente.add(entrarGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jlabel4.setText("Contraseña:");
        jPanelGerente.add(jlabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 106, 22));
        jPanelGerente.add(passGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 179, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login_Secretario.png"))); // NOI18N
        jPanelGerente.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, -1));

        jpanel_mesa.add(jPanelGerente);
        jPanelGerente.setBounds(95, 180, 780, 261);

        jPanel4.add(jpanel_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 960, 700));

        jDesktopPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(1200, 26));

        jMenuSolicitudes.setText("Solicitudes");
        jMenuSolicitudes.setEnabled(false);
        jMenuSolicitudes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        IngresodeVehiculo.setText("Ingreso de vehiculos");
        IngresodeVehiculo.setEnabled(false);
        jMenuSolicitudes.add(IngresodeVehiculo);

        jMenuBar1.add(jMenuSolicitudes);

        jMenuMantenimiento.setText("Mantenimiento");
        jMenuMantenimiento.setEnabled(false);
        jMenuMantenimiento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        asignacionMecanico.setText("Asignacion de Mecanico");
        asignacionMecanico.setEnabled(false);
        jMenuMantenimiento.add(asignacionMecanico);

        jMenuBar1.add(jMenuMantenimiento);

        jMenuFacturacion.setText("Facturacion");
        jMenuFacturacion.setEnabled(false);
        jMenuFacturacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        registroProductos.setText("Registro de productos utilizados");
        registroProductos.setEnabled(false);
        jMenuFacturacion.add(registroProductos);

        facturacionSrevicio.setText("Facturacion del Servicio");
        facturacionSrevicio.setEnabled(false);
        jMenuFacturacion.add(facturacionSrevicio);

        jMenuBar1.add(jMenuFacturacion);

        jMenuRegistros.setText("Registros");
        jMenuRegistros.setEnabled(false);
        jMenuRegistros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        regProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoProducto.png"))); // NOI18N
        regProducto.setText("Registro de Producto");
        regProducto.setEnabled(false);
        regProducto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuRegistros.add(regProducto);

        regServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoServicio.png"))); // NOI18N
        regServicio.setText("Registro de Servicio");
        regServicio.setContentAreaFilled(false);
        regServicio.setEnabled(false);
        jMenuRegistros.add(regServicio);

        regMeacanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoMecanico.png"))); // NOI18N
        regMeacanico.setText("Registro de Mecanico");
        regMeacanico.setEnabled(false);
        jMenuRegistros.add(regMeacanico);

        regSecretarioInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoSecInv.png"))); // NOI18N
        regSecretarioInv.setText("Registro de Sercretario de inventario");
        regSecretarioInv.setContentAreaFilled(false);
        regSecretarioInv.setEnabled(false);
        jMenuRegistros.add(regSecretarioInv);

        regSecretarioIng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoSecreIngresos.png"))); // NOI18N
        regSecretarioIng.setText("Registro de Secretario de ingresos");
        regSecretarioIng.setEnabled(false);
        jMenuRegistros.add(regSecretarioIng);

        jMenuBar1.add(jMenuRegistros);

        jMenuhistorial.setText("Historial");
        jMenuhistorial.setEnabled(false);
        jMenuhistorial.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        historialM.setText("Historial de mantenimientos");
        historialM.setEnabled(false);
        jMenuhistorial.add(historialM);

        jMenuBar1.add(jMenuhistorial);

        jMenu4.setText("Ayuda");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        acerca.setText("Acerca de");
        jMenu4.add(acerca);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed

        int posicion = btnMenu.getX();
        if (posicion > 5) {
            RSAnimation.setMoverIzquierda(279, 0, 2, 2, btnMenu);
            RSAnimation.setMoverIzquierda(5, -280, 2, 2, jPanel1);

        } else {
            RSAnimation.setMoverDerecha(5, 270, 2, 2, btnMenu);
            RSAnimation.setMoverDerecha(-270, 0, 2, 2, jPanel1);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnIngresodeVehiculoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresodeVehiculoMouseExited
        try {

            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel11.setVisible(false);
                btnIngresodeVehiculo.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnIngresodeVehiculoMouseExited

    private void btnIngresodeVehiculoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresodeVehiculoMouseEntered
        try {

            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel11.setVisible(true);
                btnIngresodeVehiculo.setForeground(Color.white);
            }
        } catch (Exception exc) {
        }
    }//GEN-LAST:event_btnIngresodeVehiculoMouseEntered

    private void btnAsigMecanicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsigMecanicoMouseExited
        try {
            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel10.setVisible(false);
                btnAsigMecanico.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnAsigMecanicoMouseExited

    private void btnAsigMecanicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsigMecanicoMouseEntered
        try {

            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel10.setVisible(true);
                btnAsigMecanico.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnAsigMecanicoMouseEntered

    private void btnGerenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerenteMouseEntered
        btnGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gerenteAumento.png")));
    }//GEN-LAST:event_btnGerenteMouseEntered

    private void btnGerenteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerenteMouseExited
        btnGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoGerente.png")));
    }//GEN-LAST:event_btnGerenteMouseExited

    private void btnSecretarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSecretarioMouseEntered
        btnSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SecretarioAumento.png")));
    }//GEN-LAST:event_btnSecretarioMouseEntered

    private void btnSecretarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSecretarioMouseExited
        btnSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoSecretario.png")));
    }//GEN-LAST:event_btnSecretarioMouseExited

    private void btnGerenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerenteMouseClicked
        jPanelGerente.setVisible(true);

        btnGerente.setVisible(false);
        btnSecretario.setVisible(false);

        jlabel.setVisible(false);
        jlabel2.setVisible(false);
    }//GEN-LAST:event_btnGerenteMouseClicked

    private void btnSecretarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSecretarioMouseClicked
        OcultarMostrarJPanel("ocultar");
        jPanelSecretario.setVisible(true);

        btnGerente.setVisible(false);
        btnSecretario.setVisible(false);

        jlabel.setVisible(false);
        jlabel2.setVisible(false);
    }//GEN-LAST:event_btnSecretarioMouseClicked

    private void btnlogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogOutMouseEntered
        btnlogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout110px.png")));
    }//GEN-LAST:event_btnlogOutMouseEntered

    private void btnlogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogOutMouseExited
        btnlogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout100px.png")));
    }//GEN-LAST:event_btnlogOutMouseExited

    private void btnRegistroPUtilizadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroPUtilizadoMouseEntered
        try {
            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel9.setVisible(true);
                btnRegistroPUtilizado.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnRegistroPUtilizadoMouseEntered

    private void btnRegistroPUtilizadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroPUtilizadoMouseExited
        try {
            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel9.setVisible(false);
                btnRegistroPUtilizado.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnRegistroPUtilizadoMouseExited

    private void btnRegistroServiciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroServiciosMouseEntered
        try {
            if (VariabledeCambio.equals("S.Inventario_Enable")) {
                jLabel6.setVisible(true);
                btnRegistroServicios.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnRegistroServiciosMouseEntered

    private void btnRegistroServiciosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroServiciosMouseExited
        try {

            if (VariabledeCambio.equals("S.Inventario_Enable")) {
                jLabel6.setVisible(false);
                btnRegistroServicios.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnRegistroServiciosMouseExited


    private void btnHistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseEntered
        try {
            if (gerente != null) {
                jLabel5.setVisible(true);
                btnHistorial.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnHistorialMouseEntered

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited
        try {
            if (gerente != null) {
                jLabel5.setVisible(false);
                btnHistorial.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnCloseSecretarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseSecretarioMouseClicked
        OcultarMostrarJPanel("ocultar");
        jPanelGerente.setVisible(false);

        btnGerente.setVisible(true);
        btnSecretario.setVisible(true);

        jlabel.setVisible(true);
        jlabel2.setVisible(true);

        usuarioGerente.setText("");
        passGerente.setText("");

        //Borrando el texto de todos los coponentes JTextField.
        JTextField caja;
        for (int i = 0; i < jPanelSecretario.getComponentCount(); i++) {
            if (jPanelSecretario.getComponent(i).getClass().getName().equals("javax.swing.JTextField")) {
                caja = (JTextField) jPanelSecretario.getComponent(i);
                caja.setText("");
            }
        }

    }//GEN-LAST:event_btnCloseSecretarioMouseClicked

    private void btnCloseSecretarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseSecretarioMouseEntered
        btnCloseSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.gif")));
    }//GEN-LAST:event_btnCloseSecretarioMouseEntered

    private void btnCloseSecretarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseSecretarioMouseExited
        btnCloseSecretario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg")));
    }//GEN-LAST:event_btnCloseSecretarioMouseExited

    private void btnCloseGerenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseGerenteMouseClicked
        OcultarMostrarJPanel("ocultar");
        jPanelGerente.setVisible(false);

        btnGerente.setVisible(true);
        btnSecretario.setVisible(true);

        jlabel.setVisible(true);
        jlabel2.setVisible(true);

        usuarioGerente.setText("");
        passGerente.setText("");
    }//GEN-LAST:event_btnCloseGerenteMouseClicked

    private void btnCloseGerenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseGerenteMouseEntered
        btnCloseGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.gif")));
    }//GEN-LAST:event_btnCloseGerenteMouseEntered

    private void btnCloseGerenteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseGerenteMouseExited
        btnCloseGerente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg")));
    }//GEN-LAST:event_btnCloseGerenteMouseExited

    private void btnFacturacionServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturacionServicioMouseEntered
        try {
            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel8.setVisible(true);
                btnFacturacionServicio.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnFacturacionServicioMouseEntered

    private void btnFacturacionServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturacionServicioMouseExited
        try {
            if (VariabledeCambio.equals("S.Ingresos_Enable")) {
                jLabel8.setVisible(false);
                btnFacturacionServicio.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnFacturacionServicioMouseExited

    private void btnRegistrodeProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrodeProductosMouseEntered
        try {
            if (VariabledeCambio.equals("S.Inventario_Enable")) {
                jLabel7.setVisible(true);
                btnRegistrodeProductos.setForeground(Color.white);
            }
        } catch (Exception exc) {

        }

    }//GEN-LAST:event_btnRegistrodeProductosMouseEntered

    private void btnRegistrodeProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrodeProductosMouseExited
        try {
            if (VariabledeCambio.equals("S.Inventario_Enable")) {
                jLabel7.setVisible(false);

                btnRegistrodeProductos.setForeground(Color.black);
            }
        } catch (Exception exc) {

        }
    }//GEN-LAST:event_btnRegistrodeProductosMouseExited

    private void btnlogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogOutMouseClicked

    }//GEN-LAST:event_btnlogOutMouseClicked

    private void btnlogOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogOutMousePressed
        try {
            ingresoVH.setVisible(false);
            registroPro.setVisible(false);
            asignacion.setVisible(false);
            
        }catch(Exception exc){
            
        }
        
        JOptionPane.showMessageDialog(rootPane, "Session Cerrada");

    }//GEN-LAST:event_btnlogOutMousePressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem IngresodeVehiculo;
    private javax.swing.JMenuItem acerca;
    private javax.swing.JMenuItem asignacionMecanico;
    private javax.swing.JButton btnAsigMecanico;
    private javax.swing.JButton btnCloseGerente;
    private javax.swing.JButton btnCloseSecretario;
    private javax.swing.JButton btnFacturacionServicio;
    private javax.swing.JButton btnGerente;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnIngresodeVehiculo;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnRegistroPUtilizado;
    private javax.swing.JButton btnRegistroServicios;
    private javax.swing.JButton btnRegistrodeProductos;
    private javax.swing.JButton btnSecretario;
    private javax.swing.JButton btnlogOut;
    private javax.swing.JButton entrarGerente;
    private javax.swing.JButton entrarSecretario;
    private javax.swing.JMenuItem facturacionSrevicio;
    private javax.swing.JMenuItem historialM;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFacturacion;
    private javax.swing.JMenu jMenuMantenimiento;
    private javax.swing.JMenu jMenuRegistros;
    private javax.swing.JMenu jMenuSolicitudes;
    private javax.swing.JMenu jMenuhistorial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelGerente;
    private javax.swing.JPanel jPanelSecretario;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel2;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel jlabel4;
    private javax.swing.JPanel jpanel_mesa;
    private javax.swing.JLabel nombreSession;
    private javax.swing.JPasswordField passGerente;
    private javax.swing.JPasswordField passSecretario;
    private javax.swing.JMenuItem regMeacanico;
    private javax.swing.JMenuItem regProducto;
    private javax.swing.JMenuItem regSecretarioIng;
    private javax.swing.JMenuItem regSecretarioInv;
    private javax.swing.JMenuItem regServicio;
    private javax.swing.JMenuItem registroProductos;
    private javax.swing.JTextField usuarioGerente;
    private javax.swing.JTextField usuarioSecretario;
    // End of variables declaration//GEN-END:variables

    public class IngresoVehiculoListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {

            if (ingresoVH == null) {
                try {

                    ingresoVH = new IngresoVehiculosUI(taller, secretario, reporte);

                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(ingresoVH);

                timerIngresoVeh = new Timer(100, (ActionListener) timerIngresoVeh);
                timerIngresoVeh.start();

                timerIngresoVeh.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (ingresoVH.getIsActive() == true) {
                            btnIngresodeVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ingresoVehOn.png")));
                        } else {
                            btnIngresodeVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ingresoVeh.png")));
                        }

                        if (signalCloseButton == true) {
                            ingresoVH.LimpiandoVentana();
                            ingresoVH.hide();

                        }
                    }
                });

            }

            ingresoVH.setLocation(((jDesktopPane1.getWidth() - ingresoVH.getWidth()) / 2) + 140,
                    ((jDesktopPane1.getHeight() - ingresoVH.getHeight()) / 2) + 30);
            ingresoVH.setVisible(true);
        }
    }

    public class AsignacionMecanicoListener implements ActionListener {

        private AsignacionMecanicoUI asignacion = null;

        public void actionPerformed(ActionEvent evt) {

            if (this.asignacion == null) {
                try {

                    asignacion = new AsignacionMecanicoUI(taller);

                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(asignacion);

                timerAsigMec = new Timer(100, (ActionListener) timerAsigMec);
                timerAsigMec.start();

                System.out.println(true);

                timerAsigMec.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (asignacion.getIsActive() == true) {
                            btnAsigMecanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AsigMecanicoON.png")));
                        } else {
                            btnAsigMecanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AsigMecanico.png")));
                        }

                        if (signalCloseButton == true) {
                            asignacion.LimpiandoVentana();
                        }
                        asignacion.ActualizarComponentes();

                    }
                });
            }
            this.asignacion.setLocation(((jDesktopPane1.getWidth() - asignacion.getWidth()) / 2),
                    (jDesktopPane1.getHeight() - asignacion.getHeight()) / 2);
            asignacion.setVisible(true);
            RSAnimation.setMoverIzquierda(279, 0, 2, 2, btnMenu);
            RSAnimation.setMoverIzquierda(5, -280, 2, 2, jPanel1);

        }
    }

    public class RegistroProductosUtilizadosListener implements ActionListener {

        private RegistroProductosUtilizadosUI registroPro = null;

        public void actionPerformed(ActionEvent evt) {

            if (this.registroPro == null) {
                try {
                    registroPro = new RegistroProductosUtilizadosUI(taller);
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(registroPro);

                timerRegiPdtUtilizados = new Timer(100, (ActionListener) timerRegiPdtUtilizados);
                timerRegiPdtUtilizados.start();

                timerRegiPdtUtilizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (registroPro.getIsActive() == true) {
                            btnRegistroPUtilizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regputilizadosOn.png")));
                        } else {
                            btnRegistroPUtilizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regputilizadosOff.png")));
                        }

                        if (signalCloseButton == true) {
                            registroPro.LimpiandoVentana();
                            registroPro.dispose();
                        }
                    }
                });

            }

            this.registroPro.setLocation(((jDesktopPane1.getWidth() - this.registroPro.getWidth()) / 2) + 120,
                    (jDesktopPane1.getHeight() - this.registroPro.getHeight()) / 2);
            registroPro.setVisible(true);
        }
    }

    public class FacturacionListener implements ActionListener {

        private FacturacionServicioUI facturacion = null;

        public void actionPerformed(ActionEvent evt) {

            if (this.facturacion == null) {
                try {
                    facturacion = new FacturacionServicioUI(taller, secretario);
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(facturacion);

                timerFacturacionServ = new Timer(100, (ActionListener) timerFacturacionServ);
                timerFacturacionServ.start();

                timerFacturacionServ.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (facturacion.getIsActive() == true) {
                            btnFacturacionServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnFactura.png")));
                        } else {
                            btnFacturacionServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnFacturaOff.png")));
                        }
                    }
                });

            }

            this.facturacion.setLocation(((jDesktopPane1.getWidth() - this.facturacion.getWidth()) / 2) + 120,
                    (jDesktopPane1.getHeight() - this.facturacion.getHeight()) / 2);
            this.facturacion.setVisible(true);

        }
    }

    public class AboutUIListener implements ActionListener {

        private AboutUI about = null;

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (this.about == null) {
                this.about = new AboutUI();
                jDesktopPane1.add(this.about);
                this.about.setLocation(85, 30);
            }
            this.about.setLocation(360, 520);
            this.about.setVisible(true);
        }

    }

    public void OcultarMostrarJPanel(String opcion) {
        if (opcion.equals("mostrar")) {
            jPanelSecretario.setVisible(true);
            jPanelGerente.setVisible(true);
        } else if (opcion.equals("ocultar")) {
            jPanelSecretario.setVisible(false);
            jPanelGerente.setVisible(false);

        }
    }

    public class AccesoGerente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            for (Gerente g : taller.getGerentes()) {
                if (usuarioGerente.getText().trim().equals(g.getUsuario()) && passGerente.getText().trim().equals(g.getPassword())) {

                    gerente = g;

                    usuarioGerente.setText("");
                    passGerente.setText("");

                    OcultarMostrarJPanel("ocultar");
                    nombreSession.setText("Gerente: " + g.getNombres() + g.getApellidos());
                    btnlogOut.setVisible(true);
                    nombreSession.setVisible(true);

                    jMenuRegistros.setEnabled(true);
                    regMeacanico.setEnabled(true);
                    regSecretarioIng.setEnabled(true);
                    regSecretarioInv.setEnabled(true);

                    jMenuhistorial.setEnabled(true);
                    historialM.setEnabled(true);
                    btnHistorial.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuario o contraseña incorrectas.");
                }
            }

        }

    }

    public class AccesoSecretario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {

                secretario = taller.buscarSecretario(usuarioSecretario.getText().trim(), passSecretario.getText().trim());

                if (secretario.getUsuario().equals(usuarioSecretario.getText())
                        && secretario.getPassword().equals(passSecretario.getText())) {

                    usuarioSecretario.setText("");
                    passSecretario.setText("");

                    OcultarMostrarJPanel("ocultar");
                    nombreSession.setText("Secretario(a) de " + secretario.getTipoDeCargo() + ": " + secretario.getNombres()
                            + " " + secretario.getApellidos());

                    if (secretario.getTipoDeCargo() == TipoCargo.Inventario) {

                        VariabledeCambio = "S.Inventario_Enable";

                        jMenuRegistros.setEnabled(true);
                        btnRegistroServicios.setEnabled(true);
                        btnRegistrodeProductos.setEnabled(true);
                        regProducto.setEnabled(true);
                        regServicio.setEnabled(true);

                    } else if (secretario.getTipoDeCargo() == TipoCargo.Ingresos) {

                        VariabledeCambio = "S.Ingresos_Enable";

                        btnIngresodeVehiculo.setEnabled(true);
                        btnAsigMecanico.setEnabled(true);
                        btnRegistroPUtilizado.setEnabled(true);
                        btnFacturacionServicio.setEnabled(true);

                        jMenuSolicitudes.setEnabled(true);
                        jMenuMantenimiento.setEnabled(true);
                        jMenuFacturacion.setEnabled(true);

                        IngresodeVehiculo.setEnabled(true);
                        asignacionMecanico.setEnabled(true);
                        facturacionSrevicio.setEnabled(true);
                        registroProductos.setEnabled(true);
                    }

                    nombreSession.setVisible(true);
                    btnlogOut.setVisible(true);

                }

            } catch (Exception ex) {
//                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }

    public void DesactivarComponenetes() {

        btnIngresodeVehiculo.setEnabled(false);
        btnAsigMecanico.setEnabled(false);
        btnRegistroPUtilizado.setEnabled(false);
        btnRegistrodeProductos.setEnabled(false);
        btnRegistroServicios.setEnabled(false);
        btnFacturacionServicio.setEnabled(false);
        btnHistorial.setEnabled(false);

        jMenuSolicitudes.setEnabled(false);
        jMenuMantenimiento.setEnabled(false);
        jMenuFacturacion.setEnabled(false);
        jMenuRegistros.setEnabled(false);
        jMenuhistorial.setEnabled(false);
        historialM.setEnabled(false);

        IngresodeVehiculo.setEnabled(false);
        asignacionMecanico.setEnabled(false);
        registroProductos.setEnabled(false);
        regProducto.setEnabled(false);
        regServicio.setEnabled(false);
        regSecretarioIng.setEnabled(false);
        regSecretarioInv.setEnabled(false);
        regMeacanico.setEnabled(false);

//        jLabel5.setEnabled(false);
//        jLabel6.setEnabled(false);
//        jLabel7.setEnabled(false);
//        jLabel8.setEnabled(false);
//        jLabel9.setEnabled(false);
//        jLabel10.setEnabled(false);
//        jLabel11.setEnabled(false);
    }

    public class RegistroProductoListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {

            if (registroP == null) {
                try {
                    registroP = new RegistroProducto(taller);

                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(registroP);

                timerRegistrarPdt = new Timer(100, (ActionListener) timerRegistrarPdt);
                timerRegistrarPdt.start();

                timerRegistrarPdt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (registroP.getIsActive() == true) {
                            btnRegistrodeProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoProductoOn.png")));
                        } else {
                            btnRegistrodeProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoProductoOff.png")));
                        }
                    }
                });

            }

            registroP.setLocation(((jDesktopPane1.getWidth() - registroP.getWidth()) / 2) + 120,
                    (jDesktopPane1.getHeight() - registroP.getHeight()) / 2 + 30);
            registroP.setVisible(true);

        }

    }

    public class HistorialMantListener implements ActionListener {

        private HistorialMantenimientos historialM = null;

        public void actionPerformed(ActionEvent evt) {

            if (this.historialM == null) {
                try {
                    historialM = new HistorialMantenimientos(taller);
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDesktopPane1.add(historialM);

                timerHistory = new Timer(100, (ActionListener) timerHistory);
                timerHistory.start();

                timerHistory.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (historialM.getIsActive() == true) {
                            btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/historyOn.png")));
                        } else {
                            btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/historyOff.png")));
                        }

                        if (signalCloseButton == true) {
                            historialM.LimpiandoVentana();
                            historialM.hide();

                        }

                        historialM.ActualizarComponentes();

                    }
                });

            }

            this.historialM.setLocation(((jDesktopPane1.getWidth() - this.historialM.getWidth()) / 2) + 120,
                    (jDesktopPane1.getHeight() - this.historialM.getHeight()) / 2);
            this.historialM.setVisible(true);

        }
    }

}
