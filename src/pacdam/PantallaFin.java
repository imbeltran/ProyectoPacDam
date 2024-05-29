package pacdam;

import clases.Mapa;
import clases.PacMan;

public class PantallaFin extends javax.swing.JFrame {
    
    private Mapa mapa;
    private boolean musica;
    
    public PantallaFin() {
        initComponents();
    }
    
    public PantallaFin(int puntuacion, boolean win, Mapa mapa, boolean musica) {
        this.mapa = mapa;
        this.musica = musica;
        initComponents();
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        if(win)
        {
            PanelWin.setVisible(true);
        }else{
            PanelWin.setVisible(false);
        }
        if(!win)
        {
            PanelLose.setVisible(true);
        }else{
            PanelLose.setVisible(false);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelWin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();
        PanelLose = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botonSalir1 = new javax.swing.JButton();
        botonReintentar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡Felicidades, has ganado!");

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelWinLayout = new javax.swing.GroupLayout(PanelWin);
        PanelWin.setLayout(PanelWinLayout);
        PanelWinLayout.setHorizontalGroup(
            PanelWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1482, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelWinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonSalir)
                .addGap(694, 694, 694))
        );
        PanelWinLayout.setVerticalGroup(
            PanelWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(botonSalir)
                .addContainerGap(466, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡Has perdido!");

        botonSalir1.setText("Salir");
        botonSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir1ActionPerformed(evt);
            }
        });

        botonReintentar.setText("Reintentar");
        botonReintentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReintentarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLoseLayout = new javax.swing.GroupLayout(PanelLose);
        PanelLose.setLayout(PanelLoseLayout);
        PanelLoseLayout.setHorizontalGroup(
            PanelLoseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1482, Short.MAX_VALUE))
            .addGroup(PanelLoseLayout.createSequentialGroup()
                .addGap(700, 700, 700)
                .addGroup(PanelLoseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonReintentar)
                    .addComponent(botonSalir1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelLoseLayout.setVerticalGroup(
            PanelLoseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(botonReintentar)
                .addGap(56, 56, 56)
                .addComponent(botonSalir1)
                .addContainerGap(469, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelWin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelLose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelWin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelLose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.dispose();
        PantallaMenu p = new PantallaMenu();
        p.setSize(1500, 750);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir1ActionPerformed
        this.dispose();
        PantallaMenu p = new PantallaMenu();
        p.setSize(1500, 750);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }//GEN-LAST:event_botonSalir1ActionPerformed

    private void botonReintentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReintentarActionPerformed
        this.dispose();
        PantallaJuego p = new PantallaJuego(mapa, musica);
        p.setSize(1500, 750);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }//GEN-LAST:event_botonReintentarActionPerformed

    
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
            java.util.logging.Logger.getLogger(PantallaFin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaFin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaFin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaFin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaFin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLose;
    private javax.swing.JPanel PanelWin;
    private javax.swing.JButton botonReintentar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
