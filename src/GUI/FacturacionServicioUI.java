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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author julia
 */
public class FacturacionServicioUI extends javax.swing.JInternalFrame {

    Taller taller;
    Compra compra;
    Secretario secretario;
    Mantenimiento mantenimiento = new Mantenimiento();
    Vehiculo veh;
    ArrayList<Servicio> servicios;

    public FacturacionServicioUI(Taller taller, Secretario secretario) throws Exception {
        this.taller = taller;
        this.compra = new Compra();

        initComponents();
        this.taller = taller;
//        this.mantenimiento = taller.getMpendientes().get(WIDTH );

        initComponents();

        placaTxt.addActionListener(new BuscarVehiculoListener());
        serviciosTbl.setModel(new ServiciosTablaListener());

        facturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    System.out.println(mantenimiento.getVehiculo().toString());

//                    reporte.setContenido(reporte.getTitulo()+"\n\n\n"+mantenimiento.getVehiculo().getCliente().toString()+"\n"
//                            +""+mantenimiento.getVehiculo().toString()+"\n\n"
//                            +"CONSUMOS FACTURADOS\n"
//                            +""+mantenimiento.getConsumos().toString()+"\n\n");
//                    reporte.HacerReporte();
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

                    JOptionPane.showMessageDialog(rootPane, "Facturacion realizada con exito");
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

                    serviciosTbl.updateUI();
                    consumosTbl.updateUI();

                } catch (NullPointerException exc) {
                    JOptionPane.showMessageDialog(rootPane, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        placaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        linea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        costoTxt = new javax.swing.JTextField();
        costoProductos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        valorIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        facturar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviciosTbl = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consumosTbl = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(630, 636));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Facturación del Servicio");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehiculo"));

        jLabel2.setText("Placa");

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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
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

        jLabel6.setText("Costo Mano de Obra: ");

        costoTxt.setEditable(false);
        costoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        costoProductos.setEditable(false);
        costoProductos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("Costo Productos Requeridos:");

        valorIva.setEditable(false);
        valorIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setText("Valor IVA (16%)");

        total.setEditable(false);
        total.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel9.setText("Total:");

        facturar.setText("FACTURAR");
        facturar.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicio(s) Solicitado(s)"));

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
        serviciosTbl.setPreferredSize(new java.awt.Dimension(180, 64));
        jScrollPane2.setViewportView(serviciosTbl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumos Registrados"));

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
        consumosTbl.setPreferredSize(new java.awt.Dimension(180, 64));
        jScrollPane1.setViewportView(consumosTbl);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(54, 54, 54)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(54, 54, 54)
                                .addComponent(valorIva, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(54, 54, 54)
                                .addComponent(costoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(costoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(facturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(costoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(costoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(valorIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(facturar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lineaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable consumosTbl;
    private javax.swing.JTextField costoProductos;
    private javax.swing.JTextField costoTxt;
    private javax.swing.JButton facturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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

}

//    
