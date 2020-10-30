package Presentacion;

import Negocio.Entidades.Producto;
import Negocio.NegocioProducto;
import Negocio.NegocioReserva;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmProducto extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final NegocioProducto negocioProd;
    private final NegocioReserva negocioReserva;

    private Producto productoModificado;
    private boolean modificarProducto = false;

    public frmProducto() {
        initComponents();
        mostrarIconos();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Producto", this);
        negocioProd = NegocioProducto.getNegocioProducto();
        negocioReserva = NegocioReserva.getNegocioReserva();
    }

    private void mostrarIconos() {
        UtilidadIcono.cargarIconoFrame(this);
        Image iconoProd = new ImageIcon("Recursos\\Iconos\\iconoProducto2.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoProd, lblIconoProducto, 50, 50);

        Image iconoGuardar = new ImageIcon("Recursos\\Iconos\\iconoGuardar.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoGuardar, jbtnGuardarProd, 43, 43);

        Image iconoSalir = new ImageIcon("Recursos\\Iconos\\iconoSalir2.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoSalir, jtbnCancelarProd, 43, 43);
    }

    public void mostrarProd(Producto prod) {
        modificarProducto = true;
        productoModificado = prod;

        jtfNombre.setText(prod.getNombre());
        jtfMarca.setText(prod.getMarca());
        jcbCategoria.setSelectedItem(prod.getCategoria());
        jspStock.setValue(prod.getStock());
        jtfProveedor.setText(prod.getProveedor());
        jtfPrecioCompra.setText(String.valueOf(prod.getPrecioCompra()));
        jtfPrecioVenta.setText(String.valueOf(prod.getPrecioVenta()));
    }

    private void limpiarCamposProducto() {
        jtfNombre.setText("");
        jtfMarca.setText("");
        jcbCategoria.setSelectedItem(0);
        jspStock.setValue(1);
        jtfProveedor.setText("");
        jtfPrecioCompra.setText("");
        jtfPrecioVenta.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbCategoria = new javax.swing.JComboBox();
        lblStock = new javax.swing.JLabel();
        jtbnCancelarProd = new javax.swing.JButton();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblIconoProducto = new javax.swing.JLabel();
        jbtnGuardarProd = new javax.swing.JButton();
        lblProveedor = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jtfMarca = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        jtfPrecioCompra = new javax.swing.JTextField();
        lblPrecioCompra = new javax.swing.JLabel();
        jtfPrecioVenta = new javax.swing.JTextField();
        lblPrecioVenta = new javax.swing.JLabel();
        jtfProveedor = new javax.swing.JTextField();
        jtfNombre = new javax.swing.JTextField();
        jspStock = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 255));

        jcbCategoria.setBackground(new java.awt.Color(102, 204, 255));
        jcbCategoria.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alimento", "Bebida" }));

        lblStock.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblStock.setText("Stock");

        jtbnCancelarProd.setBackground(new java.awt.Color(153, 0, 51));
        jtbnCancelarProd.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnCancelarProd.setForeground(new java.awt.Color(255, 255, 255));
        jtbnCancelarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCancelarProdActionPerformed(evt);
            }
        });

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Producto");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(605, 605, 605)
                .addComponent(lblTituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(540, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloPrincipal)
                    .addComponent(lblIconoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jbtnGuardarProd.setBackground(new java.awt.Color(0, 102, 204));
        jbtnGuardarProd.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnGuardarProd.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGuardarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarProdActionPerformed(evt);
            }
        });

        lblProveedor.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblProveedor.setText("Proveedor");

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNombre.setText("Nombre");

        lblMarca.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblMarca.setText("Marca");

        lblCategoria.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblCategoria.setText("Categoria");

        lblPrecioCompra.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblPrecioCompra.setText("Precio de compra");

        lblPrecioVenta.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblPrecioVenta.setText("Precio de venta");

        jspStock.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(104, 104, 104)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMarca)
                        .addGap(120, 120, 120)
                        .addComponent(jtfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCategoria)
                        .addGap(93, 93, 93)
                        .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStock)
                        .addGap(128, 128, 128)
                        .addComponent(jspStock, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProveedor)
                        .addGap(86, 86, 86)
                        .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecioCompra)
                        .addGap(31, 31, 31)
                        .addComponent(jtfPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecioVenta)
                        .addGap(47, 47, 47)
                        .addComponent(jtfPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnGuardarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jtbnCancelarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarca))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStock))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProveedor))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioCompra))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnGuardarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnCancelarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnCancelarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCancelarProdActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbnCancelarProdActionPerformed

    private void jbtnGuardarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarProdActionPerformed
        String nombre = "", marca = "", categoria = "", proveedor = "", mensaje = "", titulo = "";
        int stock = -1;
        float precioCompra = -1, precioVenta = -1, precioVentaOriginal = -1;
        boolean datosValidos = true, prodExiste = false;

        try {
            mensaje = negocioProd.validarProducto(jtfNombre.getText(), jtfMarca.getText(), jtfProveedor.getText(), jtfPrecioCompra.getText(), jtfPrecioVenta.getText());
            if (!mensaje.equals("ok")) {
                datosValidos = false;
                titulo = "Error";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            } else {
                nombre = jtfNombre.getText();
                marca = jtfMarca.getText();
                proveedor = jtfProveedor.getText();
                categoria = (String) jcbCategoria.getSelectedItem();
                stock = (int) jspStock.getValue();
                precioCompra = Float.parseFloat(jtfPrecioCompra.getText());
                precioVenta = Float.parseFloat(jtfPrecioVenta.getText());
            }
        } catch (NumberFormatException nfe) {
            datosValidos = false;
            mensaje = "Verifica de haber ingresado el formato correcto.";
            titulo = "Error";
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
        }
        if (datosValidos) {
            if (modificarProducto) {
                if (!productoModificado.getNombre().equals(nombre) && !productoModificado.getMarca().equals(marca)) {
                    prodExiste = negocioProd.verificarExistenciaProd(nombre, marca);
                }
                if (prodExiste) {
                    mensaje = "El nombre y marca del producto ingresado ya existe.";
                    titulo = "Error";
                    UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
                    mensaje = "";
                } else {
                    mensaje = "¿Estás seguro que deseas actualizar el producto?";
                    titulo = "Confirmar actualización";
                    int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);
                    if (confirmado == JOptionPane.YES_OPTION) {
                        productoModificado.setNombre(nombre);
                        productoModificado.setMarca(marca);
                        productoModificado.setCategoria(categoria);
                        productoModificado.setStock(stock);
                        productoModificado.setProveedor(proveedor);
                        productoModificado.setPrecioCompra(precioCompra);
                        precioVentaOriginal = productoModificado.getPrecioVenta();
                        productoModificado.setPrecioVenta(precioVenta);
                        try {
                            negocioProd.editarProd(productoModificado);
                            // Si se modificó el precio de venta del producto
                            if (precioVentaOriginal != precioVenta) {
                                negocioReserva.actualizarPrecioTotalProdReserva(productoModificado);
                            }
                            mensaje = "¡Producto actualizado exitosamente!";
                        } catch (Exception ex) {
                            mensaje = "Error al editar el producto. " + ex.getMessage();
                        }
                    } else {
                        mensaje = "";
                    }
                }
            } else {
                prodExiste = negocioProd.verificarExistenciaProd(nombre, marca);
                if (prodExiste) {
                    mensaje = "El nombre y marca del producto ingresado ya existe.";
                    titulo = "Error";
                    UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
                    mensaje = "";
                } else {
                    mensaje = "¿Estás seguro que deseas crear el producto?";
                    titulo = "Confirmar creación";
                    int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);
                    if (confirmado == JOptionPane.YES_OPTION) {
                        Producto producto = new Producto(nombre, marca, categoria, stock, precioCompra, proveedor, precioVenta);
                        negocioProd.crearProducto(producto);
                        mensaje = "¡Producto registrado exitosamente!";
                    } else {
                        mensaje = "";
                    }
                }
            }
            if (!mensaje.equals("")) {
                titulo = "Producto";
                UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                limpiarCamposProducto();
                this.dispose();
            }
        }
    }//GEN-LAST:event_jbtnGuardarProdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnGuardarProd;
    private javax.swing.JComboBox jcbCategoria;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JSpinner jspStock;
    private javax.swing.JButton jtbnCancelarProd;
    private javax.swing.JTextField jtfMarca;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfPrecioCompra;
    private javax.swing.JTextField jtfPrecioVenta;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblIconoProducto;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecioCompra;
    private javax.swing.JLabel lblPrecioVenta;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
