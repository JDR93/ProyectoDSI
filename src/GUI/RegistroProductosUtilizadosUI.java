/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Consumo;
import Source.Mantenimiento;
import Source.Producto;
import Source.Servicio;
import Source.Taller;
import Source.Vehiculo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author julia
 */
public class RegistroProductosUtilizadosUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Vehiculo vehicle;
    private Producto producto;
    private Consumo consumo = new Consumo();
    private Mantenimiento mantenimiento = new Mantenimiento();

    public RegistroProductosUtilizadosUI(Taller taller) throws Exception {

        this.taller = taller;
        initComponents();

        jSpinnerCantidad.setValue(1);

        jTableConsumos.setForeground(Color.GRAY);

        BuscarVehiculoListener buscarVeh = new BuscarVehiculoListener();
        this.buscar.addActionListener(buscarVeh);
        this.placa.addActionListener(buscarVeh);

        BuscarProductoListener buscarPro = new BuscarProductoListener();
        this.buscarProd.addActionListener(buscarPro);
        this.codigo.addActionListener(buscarPro);

        this.jTableConsumos.setModel(new AbstractTableModel() {

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
                        return "Identificacion";
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
                        return Long.class;
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
                        return mantenimiento.getConsumos().get(rowIndex).getIdentificacion();
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

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                jTableConsumos.updateUI();

            }
        });

        btnGuardarCons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    
                    taller.EditConsumoMantenimiento(mantenimiento);
                    JOptionPane.showMessageDialog(rootPane, "Consumos registrados satisfactoriamente.");
                    btnGuardarCons.setEnabled(false);
                    btnGuardarCons.setText("Consumos Guardados");
                    jTableConsumos.setForeground(Color.white);
                    jTableConsumos.setBackground(new Color(143,212,0));
                } catch (Exception ex) {
                    Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        btnRemoveConsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    long ident = (Long) jTableConsumos.getValueAt(jTableConsumos.getSelectedRow(), 0);
                    Consumo consumo = mantenimiento.BuscarConsumo(ident);

                    int opcion = JOptionPane.showConfirmDialog(rootPane, "\n¿Desea eliminar el consumo?");

                    if (opcion == JOptionPane.YES_OPTION) {

                        mantenimiento.RemoveConsumo(consumo);
                       jTableConsumos.updateUI();
                        JOptionPane.showMessageDialog(rootPane, "Consumo eliminado satisfactoriamente.");
                        btnGuardarCons.setEnabled(true);
//                        jTableConsumos.updateUI();

                    }

//                    System.out.println(mantenimiento.getConsumos());
//                    Consumo c = mantenimiento.getConsumos().get((int) jTableConsumos.getValueAt(jTableConsumos.getSelectedColumn(),jTableConsumos.getSelectedRow()));
//                    System.out.println(c.getProducto());
//                    
                } catch (Exception ex) {
                    Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnAgregarConsumo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                try {

                    consumo = new Consumo();

                    if (jlistServices.getSelectedValue() == null) {
                        throw new Exception("Se debe seleccionar el servicio .");
                    }

                    int cantidad = (Integer) jSpinnerCantidad.getValue();
                    Servicio servicio = (Servicio) jlistServices.getSelectedValue();

                    Calendar c = Calendar.getInstance();

                    consumo.setIdentificacion((long) (c.getTimeInMillis() % 1000));
                    consumo.setCantidad(cantidad);
                    consumo.setServicio(servicio);
                    consumo.setProducto(producto);

                    mantenimiento.AddConsumo(consumo);
                    btnGuardarCons.setEnabled(true);
                    JOptionPane.showMessageDialog(rootPane, "Consumo añadido satisfactoriamente.");
                    jTableConsumos.updateUI();
                    jTableConsumos.setBackground(Color.white);
                    jTableConsumos.setForeground(Color.black);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        placa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        linea = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistServices = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        mecanicoAsginado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSpinnerCantidad = new javax.swing.JSpinner();
        btnAgregarConsumo = new javax.swing.JButton();
        buscarProd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsumos = new javax.swing.JTable();
        btnRemoveConsumo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnGuardarCons = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(692, 673));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro de Productos Utilizados");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehiculo"));

        jLabel3.setText("Marca");

        marca.setEditable(false);

        jLabel4.setText("Tipo");

        tipo.setEditable(false);

        jLabel5.setText("Linea");

        linea.setEditable(false);

        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searchVector22px.png"))); // NOI18N
        buscar.setBorderPainted(false);
        buscar.setContentAreaFilled(false);
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setText("Placa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(placa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(marca))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicios Solicitados"));

        jScrollPane1.setViewportView(jlistServices);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("Mecánico Asignado:");

        mecanicoAsginado.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(mecanicoAsginado, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mecanicoAsginado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto a Registrar"));

        jLabel8.setText("Código");

        jLabel9.setText("Nombre");

        nombre.setEditable(false);

        jLabel10.setText("Costo");

        costo.setEditable(false);

        jLabel11.setText("Cantidad");

        btnAgregarConsumo.setText("Agregar Consumo");

        buscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searchVector22px.png"))); // NOI18N
        buscarProd.setBorderPainted(false);
        buscarProd.setContentAreaFilled(false);
        buscarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(buscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(btnAgregarConsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel9)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGap(7, 7, 7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(buscarProd))
                .addGap(6, 6, 6)
                .addComponent(btnAgregarConsumo))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumos Registrados"));

        jTableConsumos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableConsumos);

        btnRemoveConsumo.setText("Remover Consumo");

        btnActualizar.setText("Actualizar");

        btnGuardarCons.setText("Guardar Consumos");
        btnGuardarCons.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRemoveConsumo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarCons)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveConsumo)
                    .addComponent(btnActualizar)
                    .addComponent(btnGuardarCons))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarConsumo;
    private javax.swing.JButton btnGuardarCons;
    private javax.swing.JButton btnRemoveConsumo;
    private javax.swing.JButton buscar;
    private javax.swing.JButton buscarProd;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField costo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JTable jTableConsumos;
    private javax.swing.JList jlistServices;
    private javax.swing.JTextField linea;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField mecanicoAsginado;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField placa;
    private javax.swing.JTextField tipo;
    // End of variables declaration//GEN-END:variables

    DefaultListModel modeloList = new DefaultListModel();

    public class BuscarVehiculoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            modeloList.clear();
            marca.setText("");
            tipo.setText("");
            linea.setText("");
            mecanicoAsginado.setText("");
            codigo.setText("");
            nombre.setText("");
            costo.setText("");
            jTableConsumos.updateUI();

            try {

                mantenimiento = taller.BuscarMantenimientoPend(placa.getText());

                if (mantenimiento.getMecanico() == null) {

                    throw new Exception("No se le puede asignar consumo de productos \na un mantenimiento sin"
                            + " haberse asignado un mecánico.");
                }

                placa.setText(mantenimiento.getVehiculo().getPlaca());
                marca.setText(mantenimiento.getVehiculo().getMarca());
                tipo.setText(mantenimiento.getVehiculo().getTipo().toString());
                linea.setText(mantenimiento.getVehiculo().getLinea());

                mecanicoAsginado.setText(mantenimiento.getMecanico().getNombres() + " "
                        + mantenimiento.getMecanico().getApellidos()
                        + " CC: " + mantenimiento.getMecanico().getIdentificacion()
                        + " Contacto: " + mantenimiento.getMecanico().getTelefono());

                for (int i = 0; i < mantenimiento.getServicios().size(); i++) {
                    modeloList.addElement(mantenimiento.getServicios().get(i));
                }

                jlistServices.setModel(modeloList);

            } catch (ExceptionInInitializerError exc) {
                JOptionPane.showMessageDialog(rootPane, exc.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                jTableConsumos.updateUI();
            } catch (Exception ex) {
                modeloList.clear();
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
                jTableConsumos.updateUI();
            }

        }

    }

    public class BuscarProductoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {

                producto = taller.BuscarProducto(Integer.parseInt(codigo.getText()));
                nombre.setText(producto.getNombre());
                costo.setText(Integer.toString(producto.getCosto()));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
