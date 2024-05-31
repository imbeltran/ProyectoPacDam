package pacdam;

import bibliotecas.Conexion;
import clases.Mapa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

public class PantallaEleccion extends javax.swing.JFrame {

    private Mapa mapa;
    private static PantallaEleccion instancia;
    private boolean musica;
    private boolean modoInfinito = true;
    Conexion con = new Conexion();
    Connection conet;
    Statement st;
    ResultSet rs2;
    ResultSet rs3;
    ResultSet rs4;
    ResultSet rs5;
    
    String sqlMapa2 = "SELECT estado FROM Mapas WHERE MapaID = 1";
    String sqlMapa3 = "SELECT estado FROM Mapas WHERE MapaID = 2";
    String sqlMapa4 = "SELECT estado FROM Mapas WHERE MapaID = 3";
    String sqlMapa5 = "SELECT estado FROM Mapas WHERE MapaID = 4";
    
    public PantallaEleccion() {
        this.mapa = new Mapa();
        initComponents();
    }
    public PantallaEleccion(boolean musica) {
        initComponents();
        this.mapa = new Mapa();
        this.musica = musica;
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        try {
            conet = con.getConnection();
            st = conet.createStatement();
            rs2 = st.executeQuery(sqlMapa2);
            if (rs2.next()) {
                boolean estado = rs2.getBoolean("estado");
                if(estado)
                    botonNivel2.setEnabled(true);
                else
                    botonNivel2.setEnabled(false);
            }
            rs3 = st.executeQuery(sqlMapa3);
            if (rs3.next()) {
                boolean estado = rs3.getBoolean("estado");
                if(estado)
                    botonNivel3.setEnabled(true);
                else
                    botonNivel3.setEnabled(false);
            }
            rs4 = st.executeQuery(sqlMapa4);
            if (rs4.next()) {
                boolean estado = rs4.getBoolean("estado");
                if(estado)
                    botonNivel4.setEnabled(true);
                else
                    botonNivel4.setEnabled(false);
            }
            rs5 = st.executeQuery(sqlMapa5);
            if (rs5.next()) {
                boolean estado = rs5.getBoolean("estado");
                if(estado)
                    botonNivel5.setEnabled(true);
                else
                    botonNivel5.setEnabled(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static PantallaEleccion getInstancia() {
        if (instancia == null) {
            instancia = new PantallaEleccion();
        }
        return instancia;
    }
    
    public void cerrar() {
        this.dispose();
        instancia = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botonNivel2 = new javax.swing.JButton();
        botonNivel1 = new javax.swing.JButton();
        botonNivel3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        botonNivel4 = new javax.swing.JButton();
        botonNivel5 = new javax.swing.JButton();
        botonNivelInfinito = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecciona el nivel que desees jugar");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        botonNivel2.setText("Nivel 2");
        botonNivel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel2ActionPerformed(evt);
            }
        });

        botonNivel1.setText("Nivel 1");
        botonNivel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel1ActionPerformed(evt);
            }
        });

        botonNivel3.setText("Nivel 3");
        botonNivel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(botonNivel1)
                .addGap(89, 89, 89)
                .addComponent(botonNivel2)
                .addGap(90, 90, 90)
                .addComponent(botonNivel3)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonNivel1, botonNivel2, botonNivel3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNivel1)
                    .addComponent(botonNivel2)
                    .addComponent(botonNivel3))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        botonNivel4.setText("Nivel 4");
        botonNivel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel4ActionPerformed(evt);
            }
        });

        botonNivel5.setText("Nivel 5");
        botonNivel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel5ActionPerformed(evt);
            }
        });

        botonNivelInfinito.setText("Infinito");
        botonNivelInfinito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivelInfinitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(botonNivel4)
                .addGap(89, 89, 89)
                .addComponent(botonNivel5)
                .addGap(90, 90, 90)
                .addComponent(botonNivelInfinito)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNivel4)
                    .addComponent(botonNivel5)
                    .addComponent(botonNivelInfinito))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNivel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel1ActionPerformed
        mapa.setIndiceMapaActual(0); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa, musica);
        pantallaJuego.setSize(1516, 789); // Establece las dimensiones deseadas
        pantallaJuego.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        pantallaJuego.setVisible(true); 
        PantallaEleccion.getInstancia().cerrar();
    }//GEN-LAST:event_botonNivel1ActionPerformed

    private void botonNivel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel2ActionPerformed
        mapa.setIndiceMapaActual(1); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa, musica);
        pantallaJuego.setSize(1500, 750); // Establece las dimensiones deseadas
        pantallaJuego.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        pantallaJuego.setVisible(true);
        PantallaEleccion.getInstancia().cerrar();
    }//GEN-LAST:event_botonNivel2ActionPerformed

    private void botonNivel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel3ActionPerformed
        mapa.setIndiceMapaActual(2); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa, musica);
        pantallaJuego.setSize(1500, 750); // Establece las dimensiones deseadas
        pantallaJuego.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        pantallaJuego.setVisible(true);
        PantallaEleccion.getInstancia().cerrar();
    }//GEN-LAST:event_botonNivel3ActionPerformed

    private void botonNivel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel4ActionPerformed
        
    }//GEN-LAST:event_botonNivel4ActionPerformed

    private void botonNivel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonNivel5ActionPerformed

    private void botonNivelInfinitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivelInfinitoActionPerformed
        mapa.setIndiceMapaActual(0); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa, musica, modoInfinito);
        pantallaJuego.setSize(1516, 789); // Establece las dimensiones deseadas
        pantallaJuego.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        pantallaJuego.setVisible(true); 
        PantallaEleccion.getInstancia().cerrar();
    }//GEN-LAST:event_botonNivelInfinitoActionPerformed


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
            java.util.logging.Logger.getLogger(PantallaEleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaEleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaEleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaEleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaEleccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonNivel1;
    private javax.swing.JButton botonNivel2;
    private javax.swing.JButton botonNivel3;
    private javax.swing.JButton botonNivel4;
    private javax.swing.JButton botonNivel5;
    private javax.swing.JButton botonNivelInfinito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
