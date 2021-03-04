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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author julia
 */
public class AsignacionMecanicoUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Mantenimiento mantenimiento;

    public AsignacionMecanicoUI(Taller taller) {

        this.taller = taller;
        mantenimiento = new Mantenimiento();

        initComponents();

        actualizar.addActionListener(new ActualizarJtableMant());

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

                switch (columnIndex) {
                    case 0:
                        return taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getPlaca();
                    case 1:
                        return taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getMarca() + " "
                                + taller.mantenimientosPendNoMecanico().get(rowIndex).getVehiculo().getLinea();
                    case 2:
                        return taller.mantenimientosPendNoMecanico().get(rowIndex).getServicios();
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
                            Logger.getLogger(AsignacionMecanicoUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

//                    resetJcombo();   
                } catch (RuntimeException rexc) {
                    jTableMantConMec.updateUI();
                    jTableMantSinMec.updateUI();
                    jCBoxMDis.removeAllItems();
                    Logger.getLogger(AsignacionMecanicoUI.class.getName()).log(Level.SEVERE, null, rexc);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlistServMantconMec = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableMantConMec = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCBoxMDis = new javax.swing.JComboBox<Persona>();
        asignar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistServMantSinMec = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMantSinMec = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(990, 519));
        setVisible(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(1022, 589));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 153, 153)), "Mantenimientos con mecanico Asignado", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        jlistServMantconMec.setOpaque(false);
        jScrollPane5.setViewportView(jlistServMantconMec);

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
        jTableMantConMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMantConMecMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableMantConMec);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asignacion de Mecánico");

        jLabel3.setText("Mecanicos:");

        jCBoxMDis.setEditable(true);

        asignar.setText("Asignar Mecánico");

        actualizar.setText("Actualizar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 51, 102)), "Mantenimientos con mecanico no Asignado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 153, 204));

        jlistServMantSinMec.setOpaque(false);
        jScrollPane2.setViewportView(jlistServMantSinMec);

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
        jTableMantSinMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMantSinMecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMantSinMec);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBoxMDis, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(asignar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCBoxMDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizar)
                    .addComponent(asignar))
                .addGap(10, 10, 10))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1020, 483));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton asignar;
    private javax.swing.JComboBox<Persona> jCBoxMDis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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

}
