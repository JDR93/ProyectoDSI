/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Taller;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JDiaz
 */
public class HistorialMantenimientos extends javax.swing.JInternalFrame {

    private Taller taller;
    private boolean isActive;

    private boolean isMinimized;

    public HistorialMantenimientos(Taller taller) {
        this.taller = taller;

        initComponents();

        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTable.getTableHeader().setOpaque(false);
        jTable.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable.setRowHeight(25);

//         Modificar el ancho de la columna.
        jTable.getColumnModel().getColumn(1).setMinWidth(100);

//        Codigo para undercoder un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        jTable.setModel(
                new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return taller.getCompras().size();
            }

            @Override
            public int getColumnCount() {
                return 8;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex
            ) {
                switch (columnIndex) {
                    case 0:
                        return taller.getCompras().get(rowIndex).getNumeroFactura();
                    case 1:
                        return taller.getCompras().get(rowIndex).getVehiculo().getPlaca();
                    case 2:
                        return taller.getCompras().get(rowIndex).getVehiculo().getCliente().getIdentificacion();
                    case 3:
                        return taller.getCompras().get(rowIndex).getServicios().size();
                    case 4:
                        return taller.getCompras().get(rowIndex).getConsumos().size();
                    case 5:
                        return taller.getCompras().get(rowIndex).getMecanico().getIdentificacion();
                    case 6:
                        return taller.getCompras().get(rowIndex).getFecha();
                    case 7:
                        return taller.getCompras().get(rowIndex).getCostoTotal();
//                    
                    default:
                        return null;
                }
            }

            @Override
            public Class<?> getColumnClass(int i
            ) {
                switch (i) {
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
                    case 5:
                        return Integer.class;

                    case 6:
                        return LocalDate.class;
                    case 7:
                        return Integer.class;
//                    
                    default:
                        return String.class;
                }
            }

            @Override
            public String getColumnName(int i
            ) {
                switch (i) {
                    case 0:
                        return "ID FACTURA";
                    case 1:
                        return "PLACA";
                    case 2:
                        return "NUID PROPIETARIO";
                    case 3:
                        return "CANT SERVICIOS";
                    case 4:
                        return "CANT CONSUMOS";
                    case 5:
                        return "NUID MECANICO";
                    case 6:
                        return "FECHA";
                    case 7:
                        return "PRECIO TOTAL";
                    default:
                        return "";

                }
            }

        }
        );

        btnMinimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                isActive = true;
            }
        });

    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isIsActive) {
        this.isActive = isIsActive;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFiltro_cedula = new javax.swing.JTextField();
        jTextFiltro_placa = new javax.swing.JTextField();
        jTextFiltro_nFactura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setText("Numero Factura:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 70, 97, 16);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg"))); // NOI18N
        btnClose.setContentAreaFilled(false);
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
        jPanel1.add(btnClose);
        btnClose.setBounds(15, 16, 28, 28);

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
        jPanel1.add(btnMinimize);
        btnMinimize.setBounds(50, 14, 30, 30);

        jLabel2.setText("Placa Vehiculo");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(280, 70, 82, 16);

        jLabel3.setText("Cedula propietario:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(500, 70, 110, 16);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("HISTORIAL DE MANTENIMIENTOS");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 10, 780, 40);

        jTextFiltro_cedula.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jTextFiltro_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFiltro_cedulaKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFiltro_cedula);
        jTextFiltro_cedula.setBounds(630, 70, 110, 24);

        jTextFiltro_placa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jTextFiltro_placa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFiltro_placaKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFiltro_placa);
        jTextFiltro_placa.setBounds(380, 70, 110, 24);

        jTextFiltro_nFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jTextFiltro_nFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFiltro_nFacturaKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFiltro_nFactura);
        jTextFiltro_nFactura.setBounds(150, 70, 110, 24);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(555, 402));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable.setGridColor(new java.awt.Color(255, 255, 255));
        jTable.setPreferredSize(null);
        jTable.setSelectionBackground(new java.awt.Color(250, 76, 98));
        jTable.setShowVerticalLines(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 760, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    TableRowSorter trs;

    private void jTextFiltro_nFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFiltro_nFacturaKeyTyped
        jTextFiltro_nFactura.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(jTextFiltro_nFactura.getText(), 0));
            }
        });

        trs = new TableRowSorter((AbstractTableModel) jTable.getModel());
        jTable.setRowSorter(trs);
    }//GEN-LAST:event_jTextFiltro_nFacturaKeyTyped

    private void jTextFiltro_placaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFiltro_placaKeyTyped
        jTextFiltro_placa.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(jTextFiltro_placa.getText(), 1));
            }
        });

        trs = new TableRowSorter((AbstractTableModel) jTable.getModel());
        jTable.setRowSorter(trs);
    }//GEN-LAST:event_jTextFiltro_placaKeyTyped


    private void jTextFiltro_cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFiltro_cedulaKeyTyped
        jTextFiltro_cedula.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(jTextFiltro_cedula.getText(), 2));
            }
        });

        trs = new TableRowSorter((AbstractTableModel) jTable.getModel());
        jTable.setRowSorter(trs);


    }//GEN-LAST:event_jTextFiltro_cedulaKeyTyped

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.hide();
        isActive = false;

        //Borrando el texto de todos los coponentes JTextField.
        JTextField caja;
        for (int i = 0; i < jPanel1.getComponentCount(); i++) {
            if (jPanel1.getComponent(i).getClass().getName().equals("javax.swing.JTextField")) {
                caja = (JTextField) jPanel1.getComponent(i);
                caja.setText("");
            }
        }
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.gif")));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg")));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFiltro_cedula;
    private javax.swing.JTextField jTextFiltro_nFactura;
    private javax.swing.JTextField jTextFiltro_placa;
    // End of variables declaration//GEN-END:variables


    public void LimpiandoVentana() {

        LimpiarCampos();

    }
    
    public void LimpiarCampos(){
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
    
    public void ActualizarComponentes(){
        jTable.updateUI();
    }

    
}
