package pacdam;

import clases.Mapa;
import javax.swing.JFrame;

public class PantallaEleccion extends javax.swing.JFrame {

    private Mapa mapa;
    
    public PantallaEleccion() {
        this.mapa = new Mapa();
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonNivel1 = new javax.swing.JButton();
        botonNivel2 = new javax.swing.JButton();
        botonNivel3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Selecciona el nivel que desees jugar");

        botonNivel1.setText("Nivel 1");
        botonNivel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel1ActionPerformed(evt);
            }
        });

        botonNivel2.setText("Nivel 2");
        botonNivel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel2ActionPerformed(evt);
            }
        });

        botonNivel3.setText("Nivel 3");
        botonNivel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNivel3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(botonNivel1)
                        .addGap(107, 107, 107)
                        .addComponent(botonNivel2)
                        .addGap(99, 99, 99)
                        .addComponent(botonNivel3)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNivel1)
                    .addComponent(botonNivel2)
                    .addComponent(botonNivel3))
                .addContainerGap(239, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNivel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel1ActionPerformed
        mapa.setIndiceMapaActual(0); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa);
        pantallaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pantallaJuego.setVisible(true);        
    }//GEN-LAST:event_botonNivel1ActionPerformed

    private void botonNivel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel2ActionPerformed
        mapa.setIndiceMapaActual(1); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa);
        pantallaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pantallaJuego.setVisible(true);
    }//GEN-LAST:event_botonNivel2ActionPerformed

    private void botonNivel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNivel3ActionPerformed
        mapa.setIndiceMapaActual(2); 
        this.dispose();
        PantallaJuego pantallaJuego = new PantallaJuego(mapa);
        pantallaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pantallaJuego.setVisible(true);
    }//GEN-LAST:event_botonNivel3ActionPerformed


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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
