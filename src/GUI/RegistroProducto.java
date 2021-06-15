/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Producto;
import Source.Taller;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author JDiaz
 */
public class RegistroProducto extends javax.swing.JInternalFrame{

    /**
     * Creates new form RegistroProducto
     */
    private Taller taller;
    private boolean isActive;  
    
    public RegistroProducto(Taller taller) {
        this.taller = taller;
        initComponents();

        sombra.setVisible(false);

        //  Codigo para undercoder un jinternal Frame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);

        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTable.getTableHeader().setOpaque(false);
        jTable.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable.setRowHeight(25);

//         Modificar el ancho de la columna.
        jTable.getColumnModel().getColumn(1).setMinWidth(100);

        btnregistrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {

                    Producto producto = new Producto();
                    
                    if(codigo.getText().isEmpty() || nombre.getText().isEmpty() || costo.getText().isEmpty()){
                        throw new Exception("Por favor digite la información en los campos vacíos");
                    }

                    producto.setCodigo(Integer.parseInt(codigo.getText().trim()));
                    producto.setNombre(nombre.getText().trim());
                    producto.setCosto(Integer.parseInt(costo.getText()));


                    taller.AddProducto(producto);
                    sombra.setVisible(true);

                    JOptionPane.showMessageDialog(rootPane, "Producto registrado satisfactoriamente");
                    sombra.setVisible(false);
                    jTable.updateUI();

                    codigo.setText("");
                    nombre.setText("");
                    costo.setText("");

                } catch (NumberFormatException Nexc) {
                    JOptionPane.showMessageDialog(rootPane, "Tipo de dato no valido","Eror",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, Nexc);
                } catch (Exception ex) {
//                    Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        jTable.setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return taller.getProductos().size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columIndex) {

                switch (columIndex) {
                    case 0:
                        return taller.getProductos().get(rowIndex).getCodigo();
                    case 1:
                        return taller.getProductos().get(rowIndex).getNombre();
                    case 2:
                        return taller.getProductos().get(rowIndex).getCosto();
                    default:
                        return null;
                }

            }

            @Override
            public Class<?> getColumnClass(int i) {
                switch (i) {
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
            public String getColumnName(int i) {
                switch (i) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Costo";
                    default:
                        return "";
                }
            }

        });
        
        

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    int opcion = JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar el producto seleccionado?");

                    if (opcion == JOptionPane.YES_OPTION) {

                        Producto p = taller.BuscarProducto((int) jTable.getValueAt(jTable.getSelectedRow(), 0));
                        taller.RemoveProducto(p);
                        jTable.updateUI();
                        JOptionPane.showMessageDialog(rootPane, "Producto removido satisfactoriamente");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTable.updateUI();
            }
        });
        
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

    public void setIsActive(boolean isMinimized) {
        this.isActive = isMinimized;
    }
    
    
    

    /**
     *
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnRemover = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        sombra = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnregistrarProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(698, 580));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(244, 247, 249));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

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
        jTable.setSelectionBackground(new java.awt.Color(250, 76, 98));
        jScrollPane1.setViewportView(jTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(14, 50, 660, 293);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRODCUTOS REGISTRADOS");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 10, 681, 30);

        btnRemover.setText("Remover");
        jPanel1.add(btnRemover);
        btnRemover.setBounds(20, 360, 83, 25);

        btnActualizar.setText("Actualizar");
        jPanel1.add(btnActualizar);
        btnActualizar.setBounds(120, 360, 89, 25);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(5, 175, 690, 400);

        btnMinimize.setBackground(new java.awt.Color(255, 255, 255));
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
        jPanel2.add(btnMinimize);
        btnMinimize.setBounds(50, 10, 30, 30);

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
        jPanel2.add(btnClose);
        btnClose.setBounds(10, 10, 28, 28);

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(180, 60, 52, 24);
        jPanel2.add(codigo);
        codigo.setBounds(90, 60, 80, 22);

        jLabel5.setBackground(new java.awt.Color(0, 151, 246));
        jLabel5.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("REGISTRO DE PRODUCTO");
        jLabel5.setOpaque(true);
        jPanel2.add(jLabel5);
        jLabel5.setBounds(3, 3, 692, 45);

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Costo:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(520, 60, 38, 24);
        jPanel2.add(nombre);
        nombre.setBounds(240, 60, 260, 22);

        sombra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sombra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/registroProduct.gif"))); // NOI18N
        sombra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sombra.setPreferredSize(new java.awt.Dimension(354, 80));
        jPanel2.add(sombra);
        sombra.setBounds(280, 90, 150, 80);
        jPanel2.add(costo);
        costo.setBounds(570, 60, 90, 22);

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Codigo:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 60, 46, 24);

        btnregistrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/registroProduct.jpg"))); // NOI18N
        btnregistrarProducto.setBorder(null);
        btnregistrarProducto.setBorderPainted(false);
        btnregistrarProducto.setContentAreaFilled(false);
        btnregistrarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnregistrarProducto.setFocusPainted(false);
        jPanel2.add(btnregistrarProducto);
        btnregistrarProducto.setBounds(280, 90, 150, 80);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 698, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.gif")));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.jpg")));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
       
        this.hide();
        
        //Borrando el texto de todos los coponentes JTextField.
        JTextField caja;
        for(int i =0;i<jPanel2.getComponentCount();i++){
            if(jPanel2.getComponent(i).getClass().getName().equals("javax.swing.JTextField")){
                caja = (JTextField) jPanel2.getComponent(i);
                caja.setText("");
            }
        }
        
        isActive = false;
        
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked

    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize35pxOff.png")));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize35px.png")));
    }//GEN-LAST:event_btnMinimizeMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnregistrarProducto;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField costo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel sombra;
    // End of variables declaration//GEN-END:variables

}
