/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Compra;
import Source.Disponibilidad;
import Source.Mantenimiento;
import Source.Secretario;
import Source.Servicio;
import Source.Taller;
import Source.TipoVehiculo;
import Source.Vehiculo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author julia
 */
public class FacturacionServicioUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Compra compra;
    private Secretario secretario;
    private Mantenimiento mantenimiento = new Mantenimiento();
    private Vehiculo veh;
    private ArrayList<Servicio> servicios;

    private boolean isActive;

    public Timer timerButton;

    public FacturacionServicioUI(Taller taller, Secretario secretario) throws Exception {
        this.taller = taller;
        this.compra = new Compra();

        initComponents();

        //      Codigo para undercoder un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        serviciosTbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        serviciosTbl.getTableHeader().setOpaque(false);
        serviciosTbl.getTableHeader().setBackground(new Color(32, 136, 203));
        serviciosTbl.getTableHeader().setForeground(new Color(255, 255, 255));
        serviciosTbl.setRowHeight(25);

        consumosTbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        consumosTbl.getTableHeader().setOpaque(false);
        consumosTbl.getTableHeader().setBackground(new Color(32, 136, 203));
        consumosTbl.getTableHeader().setForeground(new Color(255, 255, 255));
        consumosTbl.setRowHeight(25);

        placaTxt.addActionListener(new BuscarVehiculoListener());
        serviciosTbl.setModel(new ServiciosTablaListener());

        facturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    mantenimiento.getMecanico().setDisponibilidad(Disponibilidad.disponible);
                    taller.AddMantenimientoRealizado(mantenimiento);

                    compra.setFecha(LocalDate.now());
                    compra.setConsumos(mantenimiento.getConsumos());
                    compra.setServicios(mantenimiento.getServicios());
                    compra.setCostoManoObr(mantenimiento.getManodeObra());
                    compra.setCostoTotal(mantenimiento.getCostoTotal());
                    compra.setCostoProducto(mantenimiento.getCostoProductos());
                    compra.setMecanico(mantenimiento.getMecanico());
                    compra.setVehiculo(mantenimiento.getVehiculo());
                    compra.setSecretario(secretario);

                    taller.AgregarCompra(compra);

                    JOptionPane.showMessageDialog(rootPane, "Factura realizada con éxito");
                    mantenimiento.getMecanico().setDisponibilidad(Disponibilidad.disponible);

                    taller.RemoveMantenimientoPendiente(mantenimiento);
                    mantenimiento = new Mantenimiento();

                    placaTxt.setText("");
                    marca.setText("");
                    tipo.setText("");
                    linea.setText("");
                    costoTxt.setText("");
                    costoProductos.setText("");
                    valorIva.setText("");
                    total.setText("");

                    compra = new Compra();

                    serviciosTbl.updateUI();
                    consumosTbl.updateUI();
                } catch (NullPointerException exc) {
                    JOptionPane.showMessageDialog(rootPane, "Digite la placa del vehiculo.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    Logger.getLogger(FacturacionServicioUI.class.getName()).log(Level.SEVERE, null, exc);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    Logger.getLogger(FacturacionServicioUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.consumosTbl.setModel(new AbstractTableModel() {

            @Override
            public int getRowCount() {

                return mantenimiento.getConsumos().size();
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Vlr. Unitario";
                    case 3:
                        return "Cantidad";
                    case 4:
                        return "Costo";
                    default:
                        return "";
                }

            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                switch (columnIndex) {
                    case 0:
                        return mantenimiento.getConsumos().get(rowIndex).getProducto().getCodigo();
                    case 1:
                        return mantenimiento.getConsumos().get(rowIndex).getProducto().getNombre();
                    case 2:
                        return mantenimiento.getConsumos().get(rowIndex).getProducto().getCosto();
                    case 3:
                        return mantenimiento.getConsumos().get(rowIndex).getCantidad();
                    case 4:
                        return mantenimiento.getConsumos().get(rowIndex).getCantidad()
                                * mantenimiento.getConsumos().get(rowIndex).getProducto().getCosto();
                }
                return null;

            }
        });

        timerButton = new Timer(1000, (ActionListener) timerButton);

        timerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonFacturar.jpg")));
                timerButton.stop();
            }
        });
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

        jPanel5 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelFacturar = new javax.swing.JLabel();
        facturar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        costoTxt = new javax.swing.JTextField();
        costoProductos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        valorIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consumosTbl = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviciosTbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        placaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        linea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(660, 683));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

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
        jPanel5.add(btnMinimize);
        btnMinimize.setBounds(60, 26, 28, 28);

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
        btnClose.setBounds(28, 26, 28, 28);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FACTURACION DEL SERVICIO");
        jLabel1.setOpaque(true);
        jPanel5.add(jLabel1);
        jLabel1.setBounds(20, 20, 609, 40);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabelFacturar.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        jLabelFacturar.setForeground(new java.awt.Color(32, 136, 203));
        jLabelFacturar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFacturar.setText("FACTURAR");
        jLabelFacturar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelFacturar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabelFacturar);
        jLabelFacturar.setBounds(480, 100, 92, 20);

        facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonFacturar.jpg"))); // NOI18N
        facturar.setContentAreaFilled(false);
        facturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        facturar.setFocusPainted(false);
        facturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        facturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                facturarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                facturarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                facturarMousePressed(evt);
            }
        });
        facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarActionPerformed(evt);
            }
        });
        jPanel4.add(facturar);
        facturar.setBounds(480, -10, 90, 110);

        jLabel6.setText("Costo Mano de Obra: ....................");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(15, 10, 206, 16);

        costoTxt.setEditable(false);
        costoTxt.setBackground(new java.awt.Color(255, 255, 255));
        costoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(costoTxt);
        costoTxt.setBounds(270, 10, 170, 22);

        costoProductos.setEditable(false);
        costoProductos.setBackground(new java.awt.Color(255, 255, 255));
        costoProductos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(costoProductos);
        costoProductos.setBounds(270, 40, 172, 22);

        jLabel7.setText("Costo Productos Requeridos: .........");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(15, 45, 205, 16);

        valorIva.setEditable(false);
        valorIva.setBackground(new java.awt.Color(255, 255, 255));
        valorIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(valorIva);
        valorIva.setBounds(270, 70, 170, 22);

        jLabel8.setText("Valor IVA (16%): ..........................");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(15, 80, 207, 16);

        total.setEditable(false);
        total.setBackground(new java.awt.Color(255, 255, 255));
        total.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(total);
        total.setBounds(270, 100, 170, 22);

        jLabel9.setText("Total: ..........................................");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(15, 110, 207, 16);

        jPanel5.add(jPanel4);
        jPanel4.setBounds(20, 170, 610, 130);

        consumosTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        consumosTbl.setGridColor(new java.awt.Color(255, 255, 255));
        consumosTbl.setPreferredSize(null);
        consumosTbl.setSelectionBackground(new java.awt.Color(250, 76, 98));
        consumosTbl.setShowVerticalLines(false);
        consumosTbl.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(consumosTbl);

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(20, 510, 609, 144);

        serviciosTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        serviciosTbl.setGridColor(new java.awt.Color(255, 255, 255));
        serviciosTbl.setPreferredSize(null);
        serviciosTbl.setSelectionBackground(new java.awt.Color(250, 76, 98));
        serviciosTbl.setShowVerticalLines(false);
        serviciosTbl.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(serviciosTbl);

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(20, 330, 609, 144);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehiculo"));

        jLabel2.setText("Placa");

        placaTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setText("Marca");

        marca.setEditable(false);

        jLabel4.setText("Tipo");

        tipo.setEditable(false);

        linea.setEditable(false);
        linea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineaActionPerformed(evt);
            }
        });

        jLabel5.setText("Linea");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel1);
        jPanel1.setBounds(20, 70, 609, 96);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setText("CONSUMOS REGISTRADOS");
        jPanel5.add(jLabel10);
        jLabel10.setBounds(30, 490, 200, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel11.setText("SERVICIOS SOLICITADOS");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(30, 310, 170, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lineaActionPerformed

    private void facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facturarActionPerformed


    private void facturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturarMouseClicked


    }//GEN-LAST:event_facturarMouseClicked

    private void facturarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturarMouseEntered
        jLabelFacturar.setForeground(new Color(250, 76, 98));
    }//GEN-LAST:event_facturarMouseEntered

    private void facturarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturarMouseExited
        jLabelFacturar.setForeground(new Color(32, 136, 203));
    }//GEN-LAST:event_facturarMouseExited

    private void facturarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturarMousePressed
        facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buttonFacturar.gif")));
        timerButton.start();
    }//GEN-LAST:event_facturarMousePressed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JTable consumosTbl;
    private javax.swing.JTextField costoProductos;
    private javax.swing.JTextField costoTxt;
    private javax.swing.JButton facturar;
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
    private javax.swing.JLabel jLabelFacturar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField linea;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField placaTxt;
    private javax.swing.JTable serviciosTbl;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField total;
    private javax.swing.JTextField valorIva;
    // End of variables declaration//GEN-END:variables

    public class BuscarVehiculoListener implements ActionListener {

        private int iva = 0;
        private int valTotal = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String placa = placaTxt.getText().trim();
                mantenimiento = taller.BuscarMantenimientoPend(placa);

                veh = mantenimiento.getVehiculo();
                TipoVehiculo tipVeh = veh.getTipo();

                placaTxt.setText(veh.getPlaca());
                marca.setText(veh.getMarca());
                tipo.setText(tipVeh.name());
                linea.setText(veh.getLinea());

                costoTxt.setText(Integer.toString((int) mantenimiento.getManodeObra()));
                costoProductos.setText(Integer.toString((int) mantenimiento.costoProductos));

                iva = (int) ((mantenimiento.costoProductos + mantenimiento.manodeObra) * 0.16);
                valTotal = (int) (mantenimiento.getCostoTotal() + iva);

                valorIva.setText(Integer.toString(iva));
                total.setText(Integer.toString(valTotal));

                System.out.println(taller.BuscarMantenimientoPend(mantenimiento.getVehiculo().getPlaca()).getServicios());

                //Comienzo de proceso para traspasar la informacion del mantenimiento pendiente a
                //mantenimiento realizado.
//                Mantenimiento man = taller.BuscarMantenimientoPend(placa);
//                List<Servicio> services = man.getServicios();
//                mantenimiento.setServicios((List<Servicio>) services);
                serviciosTbl.updateUI();

//                List<Consumo> consumos = man.getConsumos();
//                mantenimiento.setConsumos((List<Consumo>) consumos);
                consumosTbl.updateUI();
                facturar.setEnabled(true);

            } catch (ExceptionInInitializerError exc) {
                JOptionPane.showMessageDialog(rootPane, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FacturacionServicioUI.this, ex.getMessage());
                Logger.getLogger(FacturacionServicioUI.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public class ServiciosTablaListener implements TableModel {

        Servicio servicio;

        @Override
        public int getRowCount() {
            return mantenimiento.getServicios().size();

        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) {
                return "Codigo";
            }
            if (columnIndex == 1) {
                return "Nombre";
            }
            if (columnIndex == 2) {
                return "Vlr unitario";
            }
            return null;

        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                case 1:
                    return String.class;
                case 2:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override

        public Object getValueAt(int rowIndex, int columnIndex) {
//            mantenimiento.getConsumos();
            Servicio servicio = mantenimiento.getServicios().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return servicio.getCodigo();
                case 1:
                    return servicio.getNombres();
                case 2:
                    return servicio.getCosto();

            }
            serviciosTbl.updateUI();
            return null;

        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Servicio servicio = taller.getServicios().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    servicio.setCodigo((int) aValue);
                case 1:
                    servicio.setNombres((String) aValue);
                case 2:
                    servicio.setCosto((int) aValue);
            }
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
        }
    }

    public void LimpiandoVentana() {
        LimpiarCampos();
        mantenimiento = new Mantenimiento();
        this.hide();
        serviciosTbl.updateUI();
        consumosTbl.updateUI();

    }

    public void LimpiarCampos() {
        LimpiandoLosJTextField(jPanel5);
        LimpiandoLosJTextField(jPanel4);
        LimpiandoLosJTextField(jPanel1);
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

//    
