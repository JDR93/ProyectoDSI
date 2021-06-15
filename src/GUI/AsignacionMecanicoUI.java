/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Mantenimiento;
import Source.Mecanico;
import Source.Persona;
import Source.Servicio;
import Source.Taller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author julia
 */
public class AsignacionMecanicoUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Mantenimiento mantenimiento;
    private boolean isActive;

    public AsignacionMecanicoUI(Taller taller) {

        this.taller = taller;
        mantenimiento = new Mantenimiento();
        
        

        initComponents();
        
        actualizar.addActionListener(new ActualizarJtableMant());

        // Personalizando colores de JcomboBox
        jCBoxMDis.setBackground(Color.WHITE);
        jCBoxMDis.setRenderer(new DefaultListCellRenderer() {
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

        jTableMantConMec.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTableMantConMec.getTableHeader().setOpaque(false);
        jTableMantConMec.getTableHeader().setBackground(new Color(32, 136, 203));
        jTableMantConMec.getTableHeader().setForeground(new Color(255, 255, 255));
        jTableMantConMec.setRowHeight(25);

        jTableMantSinMec.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTableMantSinMec.getTableHeader().setOpaque(false);
        jTableMantSinMec.getTableHeader().setBackground(new Color(32, 136, 203));
        jTableMantSinMec.getTableHeader().setForeground(new Color(255, 255, 255));
        jTableMantSinMec.setRowHeight(25);

//      Codigo para undercoder un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        

        this.jTableMantSinMec.setModel(new AbstractTableModel() {

            @Override
            public int getRowCount() {
                return taller.mantenimientosPendNoMecanico().size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return "Placa";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Servicios";
                    default:
                        return "";
                }

            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Object.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                try {

                    switch (columnIndex) {
                        case 0:
                            return taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getPlaca();
                        case 1:
                            return taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getMarca() + " "
                                    + taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getLinea();
                        case 2:
                            return taller.mantenimientosPendNoMecanico().get(rowIndex).getServicios();
                    }

                } catch (Exception exc) {

                }
                return null;

            }
        });

        this.jTableMantConMec.setModel(new AbstractTableModel() {

            @Override
            public int getRowCount() {
                return taller.mantenimientosPendSiMecanico().size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return "Placa";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Servicios";
                    case 3:
                        return "Mecanico Asginado";
                    default:
                        return "";
                }

            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Object.class;
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                switch (columnIndex) {
                    case 0:
                        return taller.mantenimientosPendSiMecanico().get(rowIndex).getVehiculo().getPlaca();
                    case 1:
                        return taller.mantenimientosPendSiMecanico().get(rowIndex).getVehiculo().getMarca() + " "
                                + taller.mantenimientosPendSiMecanico().get(rowIndex).getVehiculo().getLinea();
                    case 2:
                        return taller.mantenimientosPendSiMecanico().get(rowIndex).getServicios();
                    case 3:
                        return taller.mantenimientosPendSiMecanico().get(rowIndex).getMecanico().getNombres() + ""
                                + taller.mantenimientosPendSiMecanico().get(rowIndex).getMecanico().getApellidos();
                }
                return null;

            }
        });

        this.jCBoxMDis.setModel(new DefaultComboBoxModel<Persona>() {

            private Persona seleccionado = null;

            @Override
            public Persona getElementAt(int i) {
                return taller.MecanicosDisponibles().get(i);
            }

            @Override
            public int getSize() {
                return taller.MecanicosDisponibles().size();
            }

            @Override
            public Object getSelectedItem() {
                return this.seleccionado;
            }

            @Override
            public void setSelectedItem(Object o) {
                try {
                    this.seleccionado = (Persona) o;
                } catch (ClassCastException ciexc) {

                }

            }

            public void removeAll() {
                asignar.removeAll();
            }

        });

        this.asignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {

                    if (jCBoxMDis.getSelectedItem() == null) {
                        throw new IllegalArgumentException("Se debe seleccionar el mecánico a asignar.");
                    }
                    if (jTableMantSinMec.getValueAt(jTableMantSinMec.getSelectedRow(), 0) == null) {
                        throw new IllegalArgumentException("Se debe seleccionar el mantenimiento a asignar.");
                    }

                    Mecanico mecanico = (Mecanico) jCBoxMDis.getSelectedItem();

                    int opcion = JOptionPane.showConfirmDialog(rootPane, "¿Desea registrar mecanico al mantenimiento seleccionado?");
                    if (opcion == JOptionPane.YES_OPTION) {
                        try {
                            String band = (String) jTableMantSinMec.getValueAt(jTableMantSinMec.getSelectedRow(), 0);
                            mantenimiento = taller.BuscarMantenimientoPend(band);
                            mantenimiento.setMecanico(mecanico);

                            taller.EditMantenimiento(mantenimiento);

//                            getManteJTable().setMecanico(mecanico);
                            JOptionPane.showMessageDialog(null, "Mecanico registrado a mantenimientos exitosamente");

                            if (jCBoxMDis.getItemCount() == 0) {
                                JOptionPane.showMessageDialog(rootPane, "NO HAY MECANICOS DISPONIBLES.");
                            }

                            try {
                                jCBoxMDis.setSelectedIndex(0);
                            } catch (IllegalArgumentException ia) {

                            }

                            jCBoxMDis.updateUI();
                            jTableMantConMec.updateUI();
                            jTableMantSinMec.updateUI();

                            modeloList.clear();

                        } catch (Exception ex) {
//                            Logger.getLogger(AsignacionMecanicoUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

//                    resetJcombo();   
                } catch (RuntimeException rexc) {
                    jTableMantConMec.updateUI();
                    jTableMantSinMec.updateUI();
                    jCBoxMDis.removeAllItems();
//                    Logger.getLogger(AsignacionMecanicoUI.class.getName()).log(Level.SEVERE, null, rexc);
                    JOptionPane.showMessageDialog(rootPane, rexc.getMessage());

                } catch (Exception exc) {
                    Logger.getLogger(AsignacionMecanicoUI.class.getName()).log(Level.SEVERE, null, exc);
                }

            }

//            private void resetJcombo() throws Exception {
//
//                if (jCBoxMDis.getSelectedIndex() == 0) {
//                    throw new Exception("Lista de mecanicos vacia");
//                }
//
//                jCBoxMDis.setSelectedIndex(0);
//
//            }
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

        jPanel3 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCBoxMDis = new javax.swing.JComboBox<Persona>();
        asignar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMantSinMec = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistServMantSinMec = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableMantConMec = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlistServMantconMec = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(1016, 582));
        setVisible(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel3.setPreferredSize(new java.awt.Dimension(1022, 589));
        jPanel3.setLayout(null);

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
        jPanel3.add(btnMinimize);
        btnMinimize.setBounds(80, 35, 30, 30);

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
        jPanel3.add(btnClose);
        btnClose.setBounds(48, 36, 28, 28);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ASIGNACION DE MECACANICO");
        jLabel1.setOpaque(true);
        jPanel3.add(jLabel1);
        jLabel1.setBounds(40, 30, 920, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Mecanicos:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(50, 490, 77, 23);

        jCBoxMDis.setBackground(new java.awt.Color(153, 153, 153));
        jCBoxMDis.setEditable(true);
        jCBoxMDis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCBoxMDis.setForeground(new java.awt.Color(102, 102, 102));
        jCBoxMDis.setBorder(null);
        jCBoxMDis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCBoxMDis.setFocusable(false);
        jCBoxMDis.setOpaque(false);
        jPanel3.add(jCBoxMDis);
        jCBoxMDis.setBounds(130, 490, 510, 20);

        asignar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        asignar.setForeground(new java.awt.Color(255, 255, 255));
        asignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg"))); // NOI18N
        asignar.setText("Asignar Mecánico");
        asignar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        asignar.setFocusPainted(false);
        asignar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        asignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                asignarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                asignarMouseExited(evt);
            }
        });
        jPanel3.add(asignar);
        asignar.setBounds(790, 490, 170, 24);

        actualizar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        actualizar.setForeground(new java.awt.Color(255, 255, 255));
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.setFocusPainted(false);
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actualizarMouseExited(evt);
            }
        });
        jPanel3.add(actualizar);
        actualizar.setBounds(650, 490, 134, 24);

        jTableMantSinMec.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableMantSinMec.setGridColor(new java.awt.Color(255, 255, 255));
        jTableMantSinMec.setSelectionBackground(new java.awt.Color(250, 76, 98));
        jTableMantSinMec.setShowVerticalLines(false);
        jTableMantSinMec.getTableHeader().setResizingAllowed(false);
        jTableMantSinMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMantSinMecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMantSinMec);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(40, 120, 452, 200);

        jScrollPane2.setViewportView(jlistServMantSinMec);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(40, 330, 450, 130);

        jTableMantConMec.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableMantConMec.setGridColor(new java.awt.Color(255, 255, 255));
        jTableMantConMec.setSelectionBackground(new java.awt.Color(56, 177, 221));
        jTableMantConMec.setShowVerticalLines(false);
        jTableMantConMec.getTableHeader().setResizingAllowed(false);
        jTableMantConMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMantConMecMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableMantConMec);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(510, 120, 452, 200);

        jScrollPane5.setViewportView(jlistServMantconMec);

        jPanel3.add(jScrollPane5);
        jScrollPane5.setBounds(510, 330, 450, 130);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 76, 98));
        jLabel2.setText("                               SIN MECANICO");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(50, 90, 450, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("MANTENIMIENTOS                             ASIGNADO");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(40, 90, 450, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(56, 178, 223));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("                         CON MECANICO  ");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(440, 90, 450, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("MANTENIMIENTOS                              ASIGNADO");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(510, 90, 450, 20);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents
DefaultListModel modeloList = new DefaultListModel();
    private void jTableMantSinMecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMantSinMecMouseClicked

        modeloList.clear();

        Mantenimiento select = null;
        select = taller.BuscarMantenimientoPend((String) jTableMantSinMec.getValueAt(jTableMantSinMec.getSelectedRow(), 0));

        modeloList.addElement("\n" + select.getVehiculo().getMarca() + " " + select.getVehiculo().getLinea() + "        |" + select.getVehiculo().getPlaca().toUpperCase() + "|");

        for (Servicio s : select.getServicios()) {
            modeloList.addElement(s);
            jlistServMantSinMec.setModel(modeloList);

        }

    }//GEN-LAST:event_jTableMantSinMecMouseClicked

    DefaultListModel modeloList2 = new DefaultListModel();


    private void jTableMantConMecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMantConMecMouseClicked
        modeloList2.clear();

        Mantenimiento select = null;
        select = taller.BuscarMantenimientoPend((String) jTableMantConMec.getValueAt(jTableMantConMec.getSelectedRow(), 0));

        modeloList2.addElement("\n" + select.getVehiculo().getMarca() + " " + select.getVehiculo().getLinea() + "        |" + select.getVehiculo().getPlaca().toUpperCase() + "|"
                + "            | Mecanico: " + select.getMecanico().getApellidos() + " " + select.getMecanico().getNombres() + " |");

        for (Servicio s : select.getServicios()) {
            modeloList2.addElement(s);
            jlistServMantconMec.setModel(modeloList2);

        }
    }//GEN-LAST:event_jTableMantConMecMouseClicked

    private void actualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseEntered
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradado.jpg")));
    }//GEN-LAST:event_actualizarMouseEntered

    private void actualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseExited
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg")));
    }//GEN-LAST:event_actualizarMouseExited

    private void asignarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asignarMouseEntered
        asignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradadoInvert.jpg")));
    }//GEN-LAST:event_asignarMouseEntered

    private void asignarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asignarMouseExited
        asignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg")));
    }//GEN-LAST:event_asignarMouseExited

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.hide();
        LimpiandoVentana();
        isActive = false;

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
    private javax.swing.JButton actualizar;
    private javax.swing.JButton asignar;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JComboBox<Persona> jCBoxMDis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableMantConMec;
    private javax.swing.JTable jTableMantSinMec;
    private javax.swing.JList<String> jlistServMantSinMec;
    private javax.swing.JList<String> jlistServMantconMec;
    // End of variables declaration//GEN-END:variables

//    public Mantenimiento getManteJTable() throws ArrayIndexOutOfBoundsException, Exception {
//
//        if (jTableMant.getValueAt(jTableMant.getSelectedRow(), 0) == null) {
//            throw new ArrayIndexOutOfBoundsException("se debe seleccionar el mantenimiento a asignar.");
//        }
//
//        String band = (String) jTableMant.getValueAt(jTableMant.getSelectedRow(), 0);
//        return taller.BuscarMantenimientoPend(band);
//    }
    public class ActualizarJtableMant implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            jTableMantSinMec.updateUI();
            jTableMantConMec.updateUI();
            jCBoxMDis.updateUI();

        }
    }

    public void LimpiandoVentana() {
        jTableMantConMec.clearSelection();
        jTableMantSinMec.clearSelection();
        modeloList.clear();
        modeloList2.clear();
        this.hide();
    }

    public void ActualizarComponentes() {
        jTableMantConMec.updateUI();
        jTableMantSinMec.updateUI();
    }

}
