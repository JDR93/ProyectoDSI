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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author julia
 */
public class RegistroProductosUtilizadosUI extends javax.swing.JInternalFrame {

    private Taller taller;
    private Vehiculo vehicle;
    private Producto producto;
    private Consumo c = new Consumo();
    private Mantenimiento mantenimiento = new Mantenimiento();

    private boolean isActive;

    public RegistroProductosUtilizadosUI(Taller taller) throws Exception {

        this.taller = taller;
        initComponents();

        //      Codigo para undercoder un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        jSpinnerCantidad.setValue(1);

        jTableConsumos.setForeground(Color.GRAY);

        BuscarVehiculoListener buscarVeh = new BuscarVehiculoListener();
        this.buscar.addActionListener(buscarVeh);
        this.placa.addActionListener(buscarVeh);

        BuscarProductoListener buscarPro = new BuscarProductoListener();
        this.buscarProd.addActionListener(buscarPro);
        this.codigo.addActionListener(buscarPro);

        jTableConsumos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTableConsumos.getTableHeader().setOpaque(false);
        jTableConsumos.getTableHeader().setBackground(new Color(32, 136, 203));
        jTableConsumos.getTableHeader().setForeground(new Color(255, 255, 255));
        jTableConsumos.setRowHeight(25);

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
                        return mantenimiento.getConsumos().get(rowIndex).getPk();
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

        btnRemoveConsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    long pk = (Long) jTableConsumos.getValueAt(jTableConsumos.getSelectedRow(), 0);
                    c = mantenimiento.BuscarConsumo(pk);
//
                    int opcion = JOptionPane.showConfirmDialog(rootPane, "\n¿Desea eliminar el consumo?");

                    if (opcion == JOptionPane.YES_OPTION) {
//
                        mantenimiento.RemoveConsumo(c);
                        taller.EditMantenimientoCons(mantenimiento);
                        taller.RemoveConsumo(c);
                        jTableConsumos.updateUI();
                        JOptionPane.showMessageDialog(rootPane, "Consumo eliminado satisfactoriamente.");
//
                    }
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
                    Servicio s = (Servicio) jlistServices.getSelectedValue();

                    Consumo c = new Consumo((int) jSpinnerCantidad.getValue(), (Servicio) s, producto);
                    if (c == null) {
                        throw new Exception("Porfavor selecciones el servicio.");
                    }

                    mantenimiento.AddConsumo(c);
                    taller.AddConsumo(c);
                    taller.EditMantenimientoCons(mantenimiento);
                    JOptionPane.showMessageDialog(rootPane, "Consumo añadido satisfactoriamente.");
                    jTableConsumos.updateUI();
                    System.out.println(c);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel5 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
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
        jLabel6 = new javax.swing.JLabel();
        mecanicoAsginado = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(684, 677));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });
        jPanel5.add(btnClose);
        btnClose.setBounds(28, 16, 28, 28);

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
        btnMinimize.setBounds(60, 14, 30, 30);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE PRODUCTOS SOLICITADOS");
        jLabel1.setOpaque(true);
        jPanel5.add(jLabel1);
        jLabel1.setBounds(20, 10, 630, 40);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehiculo"));

        placa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setText("Marca");

        marca.setEditable(false);
        marca.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel4.setText("Tipo");

        tipo.setEditable(false);
        tipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel5.setText("Linea");

        linea.setEditable(false);
        linea.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

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
                    .addComponent(jLabel3)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel5.add(jPanel1);
        jPanel1.setBounds(20, 60, 630, 81);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicios Solicitados"));

        jScrollPane1.setViewportView(jlistServices);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(jPanel4);
        jPanel4.setBounds(20, 180, 630, 143);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto a Registrar"));

        jLabel8.setText("Código");

        codigo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel9.setText("Nombre");

        nombre.setEditable(false);
        nombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel10.setText("Costo");

        costo.setEditable(false);
        costo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel11.setText("Cantidad");

        jSpinnerCantidad.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btnAgregarConsumo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnAgregarConsumo.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg"))); // NOI18N
        btnAgregarConsumo.setText("Agregar Consumo");
        btnAgregarConsumo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarConsumo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarConsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarConsumoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarConsumoMouseExited(evt);
            }
        });

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
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAgregarConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
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
                        .addComponent(nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscarProd)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addComponent(btnAgregarConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel2);
        jPanel2.setBounds(20, 330, 630, 104);

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
        jTableConsumos.setGridColor(new java.awt.Color(255, 255, 255));
        jTableConsumos.setOpaque(false);
        jTableConsumos.setSelectionBackground(new java.awt.Color(250, 76, 98));
        jTableConsumos.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(jTableConsumos);

        btnRemoveConsumo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnRemoveConsumo.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnRemoveDegradado.png"))); // NOI18N
        btnRemoveConsumo.setText("Remover Consumo");
        btnRemoveConsumo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoveConsumo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemoveConsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoveConsumoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoveConsumoMouseExited(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRemoveConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel3);
        jPanel3.setBounds(20, 450, 630, 180);

        jLabel6.setText("Mecánico Asignado:");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(40, 150, 114, 16);

        mecanicoAsginado.setEditable(false);
        mecanicoAsginado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPanel5.add(mecanicoAsginado);
        mecanicoAsginado.setBounds(180, 150, 470, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarConsumoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarConsumoMouseEntered
        btnAgregarConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradadoInvert.jpg")));
    }//GEN-LAST:event_btnAgregarConsumoMouseEntered

    private void btnAgregarConsumoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarConsumoMouseExited
        btnAgregarConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueDegradado.jpg")));
    }//GEN-LAST:event_btnAgregarConsumoMouseExited

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

    private void btnRemoveConsumoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveConsumoMouseEntered
        btnRemoveConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueCancelDegradadoInvert.jpg")));
    }//GEN-LAST:event_btnRemoveConsumoMouseEntered

    private void btnRemoveConsumoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveConsumoMouseExited
        btnRemoveConsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBlueCancelDegradado.jpg")));
    }//GEN-LAST:event_btnRemoveConsumoMouseExited

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradado.jpg")));
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGreenDegradadoInvert.jpg")));
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed

    }//GEN-LAST:event_btnCloseMousePressed

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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarConsumo;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
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
    private javax.swing.JPanel jPanel5;
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
//                Logger.getLogger(RegistroProductosUtilizadosUI.class.getName()).log(Level.SEVERE, null, ex);
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

    public void LimpiandoVentana() {
        LimpiarCampos();
        modeloList.clear();
        c = new Consumo();
        this.hide();
        mantenimiento = new Mantenimiento();
        jTableConsumos.updateUI();

    }

    public void LimpiarCampos() {
        LimpiandoLosJTextField(jPanel5);
        LimpiandoLosJTextField(jPanel1);
        LimpiandoLosJTextField(jPanel2);
        LimpiandoLosJTextField(jPanel3);
        LimpiandoLosJTextField(jPanel4);
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
