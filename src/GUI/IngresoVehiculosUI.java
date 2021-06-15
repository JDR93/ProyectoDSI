/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Cliente;
import Source.Genero;
import Source.Mantenimiento;
import Source.Report;
import Source.Secretario;
import Source.Servicio;
import Source.Taller;
import Source.TipoVehiculo;
import Source.Vehiculo;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author julia
 */
public class IngresoVehiculosUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Secretario secretario;
    private Vehiculo vehicle;
    private Mantenimiento mantenimiento;
    private Servicio service;
    private Report reporte;

    private boolean isActive;

    public IngresoVehiculosUI(Taller taller, Secretario secretario, Report reporte) {
        this.taller = taller;
        this.secretario = secretario;
        this.vehicle = new Vehiculo();
        this.mantenimiento = new Mantenimiento();
        this.reporte = reporte;

        initComponents();
        
        removerServicio.setVisible(false);

        //  Codigo para undercoder(Quitar marco de ventana) un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        BuscarVehicleListener buscarVEH = new BuscarVehicleListener();
        buscarVeh.addActionListener(buscarVEH);
        placa.addActionListener(buscarVEH);

        genero.setBackground(Color.GREEN);
        genero.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(new Color(0, 148, 174));
                } else {
                    c.setBackground(Color.white);
                }

                return c;
            }
        });

        jBox_tipo.setBackground(Color.GREEN);
        jBox_tipo.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(new Color(0, 148, 174));
                } else {
                    c.setBackground(Color.white);
                }

                return c;
            }
        });

        JcomboServ.setOpaque(true);

        this.JcomboServ.setModel(new ComboBoxModel<Servicio>() {

            private Servicio seleccionado = null;

            @Override
            public void setSelectedItem(Object serItem) {

                try {

                    this.seleccionado = (Servicio) serItem;
                } catch (ClassCastException iexc) {

                }
            }

            @Override
            public Object getSelectedItem() {
                return this.seleccionado;
            }

            @Override
            public int getSize() {
                return taller.getServicios().size();
            }

            @Override
            public Servicio getElementAt(int i) {
                return taller.getServicios().get(i);
            }

            @Override
            public void addListDataListener(ListDataListener ll) {
            }

            @Override
            public void removeListDataListener(ListDataListener ll) {
            }

            @Override
            public String toString() {
                return seleccionado.toString();
            }

        });

        this.agregarServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {

                    service = (Servicio) JcomboServ.getSelectedItem();

                    mantenimiento.AddServicio(service);

                    JOptionPane.showMessageDialog(rootPane, "Servicio añadido satisfactoriamente");

                    modeloList.addElement(service.toString());
                    jListServ.setModel(modeloList);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LimpiarCampos();
                modeloList.clear();
                placa.setText("");
                EsEditable(false);
                mantenimiento = new Mantenimiento();
                vehicle = new Vehiculo();

                agregarServicio.setEnabled(false);

            }
        });

        this.ingresarVeh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    if (vehicle == null) {
                        throw new Exception("Mantenimiento sin vehiculo no puede ser agreagado.");
                    }

                    mantenimiento.setVehiculo(vehicle);
                    mantenimiento.setFecha(LocalDate.now());

                    taller.AddMantenimientoPendiente(mantenimiento);

                    JOptionPane.showMessageDialog(null, "Mantenimiento registrado satisfactoriamente.");

//                    modeloList.clear();
//                    jListServ.updateUI();
                    agregarServicio.setEnabled(false);
                    placa.setText("");
                    LimpiarCampos();
                    modeloList.clear();
                    mantenimiento = new Mantenimiento();
                    vehicle = new Vehiculo();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Mantenimiento no puede ser ingresado sin vehiculo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(IngresoVehiculosUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(IngresoVehiculosUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

        this.registrarVeh.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                try {

                    Cliente cliente = new Cliente(correo.getText().trim(), direccion.getText().trim(), Long.parseLong(identificacion.getText()), nombres.getText().trim(),
                            apellidos.getText().trim(), (Genero) genero.getSelectedItem(), Long.parseLong(telefono.getText()));

                    vehicle.setPlaca(placa.getText().trim());
                    vehicle.setMarca(marca.getText().trim());
                    vehicle.setLinea(linea.getText().trim());
                    vehicle.setAnio(Short.parseShort(modelo.getText()));
                    vehicle.setTipo((TipoVehiculo) jBox_tipo.getSelectedItem());
                    vehicle.setCliente(cliente);

                    taller.AddVehiculoAntendido(vehicle);

                    JOptionPane.showMessageDialog(rootPane, "Vehiculo añadido satisfatoriamente.");
                    HabilitarBtnRegistrarVeh();

                    EsEditable(false);
                    registrarVeh.setEnabled(true);

                    taller.Reporte();

                } catch (NumberFormatException nfe) {

                    JOptionPane.showMessageDialog(rootPane, nfe.getMessage());
//                    Logger.getLogger(IngresoVehiculosUI.class.getName()).log(Level.SEVERE, null, nfe);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
//                    Logger.getLogger(IngresoVehiculosUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        placa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        linea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        modelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        buscarVeh = new javax.swing.JButton();
        marca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jBox_tipo = new JComboBox<>(TipoVehiculo.values());
        ;
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        identificacion = new javax.swing.JTextField();
        nombres = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        genero = new JComboBox<>(Genero.values());
        jPanel1 = new javax.swing.JPanel();
        JcomboServ = new javax.swing.JComboBox<Servicio>();
        agregarServicio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListServ = new javax.swing.JList<>();
        removerServicio = new javax.swing.JButton();
        ingresarVeh = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        registrarVeh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(762, 680));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize35px.png"))); // NOI18N
        btnMinimize.setBorderPainted(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.setFocusPainted(false);
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });
        jPanel4.add(btnMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 15, 28, 28));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Placa:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 38, -1, -1));

        placa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 35, 190, -1));

        jLabel2.setText("Marca:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 67, -1, -1));

        linea.setEditable(false);
        linea.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        linea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lineaKeyReleased(evt);
            }
        });
        jPanel2.add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 93, 246, -1));

        jLabel4.setText("Linea:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 96, -1, -1));

        modelo.setEditable(false);
        modelo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        modelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modeloKeyReleased(evt);
            }
        });
        jPanel2.add(modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 122, 246, -1));

        jLabel5.setText("Modelo:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 125, -1, -1));

        buscarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searchVector22px.png"))); // NOI18N
        buscarVeh.setBorder(null);
        buscarVeh.setBorderPainted(false);
        buscarVeh.setContentAreaFilled(false);
        buscarVeh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscarVeh.setFocusPainted(false);
        jPanel2.add(buscarVeh, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 35, 38, -1));

        marca.setEditable(false);
        marca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                marcaKeyReleased(evt);
            }
        });
        jPanel2.add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 64, 246, -1));

        jLabel3.setText("Tipo:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 154, -1, -1));

        jBox_tipo.setBackground(new java.awt.Color(0, 0, 0));
        jBox_tipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBox_tipo.setForeground(new java.awt.Color(102, 102, 102));
        jBox_tipo.setBorder(null);
        jBox_tipo.setEnabled(false);
        jBox_tipo.setFocusable(false);
        jPanel2.add(jBox_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 151, 246, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 350, 190));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Identificacion:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 38, -1, -1));

        identificacion.setEditable(false);
        identificacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        identificacion.setMinimumSize(new java.awt.Dimension(31, 22));
        identificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                identificacionKeyReleased(evt);
            }
        });
        jPanel3.add(identificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 35, 200, -1));

        nombres.setEditable(false);
        nombres.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nombres.setMinimumSize(new java.awt.Dimension(31, 22));
        nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombresKeyReleased(evt);
            }
        });
        jPanel3.add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 67, 200, -1));

        jLabel7.setText("Nombre(s):");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 70, 80, -1));

        jLabel8.setText("Apellido(s):");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 102, 80, -1));

        telefono.setEditable(false);
        telefono.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        telefono.setMinimumSize(new java.awt.Dimension(31, 22));
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoKeyReleased(evt);
            }
        });
        jPanel3.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 163, 200, -1));

        jLabel9.setText("Telefono:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 166, 80, -1));

        apellidos.setEditable(false);
        apellidos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        apellidos.setMinimumSize(new java.awt.Dimension(31, 22));
        apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apellidosKeyReleased(evt);
            }
        });
        jPanel3.add(apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 99, 200, -1));

        jLabel10.setText("Correo:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 198, 80, -1));

        correo.setEditable(false);
        correo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        correo.setMinimumSize(new java.awt.Dimension(31, 22));
        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });
        correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correoKeyReleased(evt);
            }
        });
        jPanel3.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 195, 200, -1));

        jLabel11.setText("Direccion:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 230, 80, -1));

        direccion.setEditable(false);
        direccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        direccion.setMinimumSize(new java.awt.Dimension(31, 22));
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
        });
        jPanel3.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 227, 200, -1));

        jLabel12.setText("Genero:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 134, 80, -1));

        genero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        genero.setForeground(new java.awt.Color(102, 102, 102));
        genero.setBorder(null);
        genero.setEnabled(false);
        genero.setFocusable(false);
        genero.setPreferredSize(new java.awt.Dimension(6, 22));
        jPanel3.add(genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 131, 200, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 350, 270));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicio(s) Solicitado(s)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        JcomboServ.setEditable(true);
        JcomboServ.setForeground(new java.awt.Color(102, 102, 102));
        JcomboServ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JcomboServKeyReleased(evt);
            }
        });

        agregarServicio.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        agregarServicio.setForeground(new java.awt.Color(255, 255, 255));
        agregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg"))); // NOI18N
        agregarServicio.setText("Agregar");
        agregarServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarServicio.setEnabled(false);
        agregarServicio.setFocusPainted(false);
        agregarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                agregarServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                agregarServicioMouseExited(evt);
            }
        });

        jListServ.setSelectionBackground(new java.awt.Color(32, 136, 203));
        jScrollPane1.setViewportView(jListServ);

        removerServicio.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        removerServicio.setForeground(new java.awt.Color(255, 255, 255));
        removerServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnRemoveDegradado.png"))); // NOI18N
        removerServicio.setText("Remover Servicio");
        removerServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removerServicio.setEnabled(false);
        removerServicio.setFocusPainted(false);
        removerServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removerServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removerServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removerServicioMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JcomboServ, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removerServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcomboServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(removerServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 710, 230));

        ingresarVeh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ingresarVeh.setForeground(new java.awt.Color(255, 255, 255));
        ingresarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg"))); // NOI18N
        ingresarVeh.setText("Ingresar Vehiculo");
        ingresarVeh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingresarVeh.setFocusPainted(false);
        ingresarVeh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ingresarVeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ingresarVehMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ingresarVehMouseExited(evt);
            }
        });
        ingresarVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarVehActionPerformed(evt);
            }
        });
        jPanel4.add(ingresarVeh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 496, 24));

        cancelar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueCancelDegradado.jpg"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelarMouseExited(evt);
            }
        });
        jPanel4.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 610, 210, 24));

        registrarVeh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        registrarVeh.setForeground(new java.awt.Color(255, 255, 255));
        registrarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg"))); // NOI18N
        registrarVeh.setText("Registrar Vehiculo");
        registrarVeh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarVeh.setEnabled(false);
        registrarVeh.setFocusPainted(false);
        registrarVeh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrarVeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarVehMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrarVehMouseExited(evt);
            }
        });
        jPanel4.add(registrarVeh, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 350, 24));

        jPanel5.setLayout(null);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg"))); // NOI18N
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });
        jPanel5.add(btnClose);
        btnClose.setBounds(10, 6, 28, 28);

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("INGRESO DE VEHICULO");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel13);
        jLabel13.setBounds(0, 3, 706, 40);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 710, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marcaKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_marcaKeyReleased

    private void lineaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lineaKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_lineaKeyReleased

    private void modeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modeloKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_modeloKeyReleased

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_direccionKeyReleased

    private void correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_correoKeyReleased

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed

    private void apellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidosKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_apellidosKeyReleased

    private void telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_telefonoKeyReleased

    private void nombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_nombresKeyReleased

    private void identificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_identificacionKeyReleased
        HabilitarBtnRegistrarVeh();
    }//GEN-LAST:event_identificacionKeyReleased

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.hide();
        isActive = false;
        LimpiandoVentana();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.gif")));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg")));
    }//GEN-LAST:event_btnCloseMouseExited

    private void ingresarVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarVehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresarVehActionPerformed

    private void ingresarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarVehMouseEntered
        ingresarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradadoInvert.jpg")));

    }//GEN-LAST:event_ingresarVehMouseEntered

    private void ingresarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarVehMouseExited
        ingresarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg")));

    }//GEN-LAST:event_ingresarVehMouseExited

    private void cancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseEntered
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueCancelDegradadoInvert.jpg")));
    }//GEN-LAST:event_cancelarMouseEntered

    private void cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseExited
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueCancelDegradado.jpg")));
    }//GEN-LAST:event_cancelarMouseExited

    private void registrarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarVehMouseEntered
        registrarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradado.jpg")));
    }//GEN-LAST:event_registrarVehMouseEntered

    private void registrarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarVehMouseExited
        registrarVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg")));
    }//GEN-LAST:event_registrarVehMouseExited

    private void agregarServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarServicioMouseEntered
        agregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradadoInvert.jpg")));
    }//GEN-LAST:event_agregarServicioMouseEntered

    private void agregarServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarServicioMouseExited
        agregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg")));
    }//GEN-LAST:event_agregarServicioMouseExited

    private void JcomboServKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JcomboServKeyReleased

    }//GEN-LAST:event_JcomboServKeyReleased

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        isActive = true;
        setVisible(false);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize35pxOff.png")));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize35px.png")));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void removerServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removerServicioMouseEntered
        removerServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnRemoveDegradadoInvert.png")));
    }//GEN-LAST:event_removerServicioMouseEntered

    private void removerServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removerServicioMouseExited
        removerServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnRemoveDegradado.png")));
    }//GEN-LAST:event_removerServicioMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Servicio> JcomboServ;
    private javax.swing.JButton agregarServicio;
    private javax.swing.JTextField apellidos;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton buscarVeh;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private javax.swing.JComboBox<Genero> genero;
    private javax.swing.JTextField identificacion;
    private javax.swing.JButton ingresarVeh;
    private javax.swing.JComboBox<TipoVehiculo> jBox_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<Servicio> jListServ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField linea;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField nombres;
    private javax.swing.JTextField placa;
    private javax.swing.JButton registrarVeh;
    private javax.swing.JButton removerServicio;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables

    public void EsEditable(boolean opcion) {
        marca.setEditable(opcion);
        jBox_tipo.setEnabled(opcion);
        linea.setEditable(opcion);
        modelo.setEditable(opcion);
        identificacion.setEditable(opcion);
        nombres.setEditable(opcion);
        apellidos.setEditable(opcion);
        telefono.setEditable(opcion);
        registrarVeh.setEnabled(opcion);
        direccion.setEditable(opcion);
        correo.setEditable(opcion);
        genero.setEnabled(opcion);
    }

    public void LimpiarCampos() {
        LimpiandoLosJTextField(jPanel1);
        LimpiandoLosJTextField(jPanel2);
        LimpiandoLosJTextField(jPanel3);

    }

    public void HabilitarBtnAgregarServ(boolean opcion) {

        if ((opcion == true) && (vehicle != null)) {
            agregarServicio.setEnabled(true);
            removerServicio.setEnabled(true);
            removerServicio.setEnabled(true);
        } else if (opcion == false) {
            agregarServicio.setEnabled(true);
            removerServicio.setEnabled(true);

        } else {
            agregarServicio.setEnabled(false);
            removerServicio.setEnabled(false);
        }
    }

    public void HabilitarBtnRegistrarVeh() {
        if (!placa.getText().isEmpty()
                && !marca.getText().isEmpty()
                && !linea.getText().isEmpty()
                && !modelo.getText().isEmpty()
                && !identificacion.getText().isEmpty()
                && !nombres.getText().isEmpty()
                && !apellidos.getText().isEmpty()
                && !telefono.getText().isEmpty()
                && !direccion.getText().isEmpty()
                && !correo.getText().isEmpty()) {

            registrarVeh.setEnabled(true);
        } else {
            registrarVeh.setEnabled(false);

        }

        agregarServicio.setEnabled(true);
    }
    DefaultListModel modeloList = new DefaultListModel();

    public class BuscarVehicleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            modeloList.clear();
            mantenimiento = new Mantenimiento();

            try {

                if (placa.getText().equals("")) {
                    LimpiarCampos();
                    vehicle = null;
                    HabilitarBtnAgregarServ(true);
                    throw new IllegalArgumentException("Digite la placa del vehiculo.");
                }

                String band = placa.getText().trim();

                vehicle = taller.BuscarVehAtendido(band);

//
//                
//                vehicle = taller.BuscarVehAtendido(band);
                //Habilitar o inhabilitar para asi no agregar servicios a la nada.
                HabilitarBtnAgregarServ(true);

//                mantenimiento.setVehiculo(vehicle);
//                if (taller.BuscarVehAtendido(band) != null) {
//                    ingresarVeh.setEnabled(false);
//                }

                placa.setText(vehicle.getPlaca().trim());
                marca.setText(vehicle.getMarca().trim());
                linea.setText(vehicle.getLinea().trim());
                modelo.setText(Short.toString(vehicle.getAnio()));
                jBox_tipo.setSelectedItem(vehicle.getTipo());
                identificacion.setText(Long.toString(vehicle.getCliente().getIdentificacion()).trim());
                nombres.setText(vehicle.getCliente().getNombres().trim());
                apellidos.setText(vehicle.getCliente().getApellidos().trim());
                telefono.setText(Long.toString(vehicle.getCliente().getTelefono()).trim());
                genero.setSelectedItem(vehicle.getCliente().getGender());
                telefono.setText(Long.toString(vehicle.getCliente().getTelefono()));
                direccion.setText(vehicle.getCliente().getDireccion());
                correo.setText(vehicle.getCliente().getCorreo());

//                try {
//                    if (taller.BuscarMantenimientoPend(band).getVehiculo() == vehicle) {
//
                for (int i = 0; i < taller.BuscarMantenimientoPend(band).getServicios().size(); i++) {
                    modeloList.addElement(taller.BuscarMantenimientoPend(band).getServicios().get(i));
                    jListServ.updateUI();
                }
//                      
                mantenimiento = taller.BuscarMantenimientoPend(band);
                jListServ.setModel(modeloList);

            } catch (ExceptionInInitializerError eixc) {

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            } catch (Exception exc) {
//                Logger.getLogger(IngresoVehiculosUI.class.getName()).log(Level.SEVERE, null, exc);
//                LimpiarCampos();
                int opcion = JOptionPane.showConfirmDialog(rootPane, exc.getMessage() + "\n¿Desea registrar vehiculo?");
                if (opcion == JOptionPane.YES_OPTION) {

                    String band = placa.getText();

                    mantenimiento = new Mantenimiento();
                    vehicle = new Vehiculo();
                    modeloList.clear();
                    LimpiarCampos();
                    EsEditable(true);

                    placa.setText(band);

                    HabilitarBtnRegistrarVeh();

                }
                if (opcion == JOptionPane.NO_OPTION) {
                    placa.setText("");
                    LimpiarCampos();
                    mantenimiento = new Mantenimiento();
                    vehicle = new Vehiculo();
                }
            }
        }

    }

    public void LimpiandoVentana() {
        LimpiarCampos();
        modeloList.clear();
        EsEditable(false);
        mantenimiento = new Mantenimiento();
        vehicle = new Vehiculo();
        agregarServicio.setEnabled(false);
        

    }

    //Borrando el texto de todos los coponentes JTextField.
    public void LimpiandoLosJTextField(JPanel panel) {
        JTextField caja;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i).getClass().getName().equals("javax.swing.JTextField")) {
                caja = (JTextField) panel.getComponent(i);
                caja.setText("");
            }
        }
    }

}
