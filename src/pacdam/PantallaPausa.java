package pacdam;

import clases.FantasmaAzul;
import clases.FantasmaNaranja;
import clases.FantasmaRojo;
import clases.FantasmaRosa;
import clases.PacMan;
import clases.Partida;
import clases.RecuperarMapa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

public class PantallaPausa extends javax.swing.JFrame {
    
    private PacMan pacman;
    private PantallaJuego pantallaJuego;
    private boolean musica;
    private boolean[][] pasado;
    private FantasmaNaranja naranja;
    private FantasmaAzul azul;
    private FantasmaRojo rojo;
    private FantasmaRosa rosa;
    Partida p = new Partida();
    
    public PantallaPausa(PacMan pacman, PantallaJuego pantallaJuego, boolean infinito, boolean[][] pasado) {
        this.pacman = pacman;
        this.musica = p.getSonido();
        this.pantallaJuego=pantallaJuego;
        this.pasado = pasado;
        this.naranja = naranja;
        this.azul = azul;
        this.rojo = rojo;
        //this.rosa = rosa
        //p.setSonido(true);
        if(musica)
        {
            musicaCB.setSelected(true);
        }
        
        initComponents();
        botonGuardarPartida.setEnabled(!infinito);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonContinuar = new javax.swing.JButton();
        botonGuardarPartida = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        musicaCB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pausa");

        botonContinuar.setText("Continuar");
        botonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonContinuarActionPerformed(evt);
            }
        });

        botonGuardarPartida.setText("Guardar Partida");
        botonGuardarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarPartidaActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        musicaCB.setText("Activar musica");
        musicaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicaCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(botonContinuar)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musicaCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonGuardarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(botonSalir)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonContinuar)
                    .addComponent(botonGuardarPartida)
                    .addComponent(botonSalir))
                .addGap(52, 52, 52)
                .addComponent(musicaCB)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContinuarActionPerformed
        pacman.setPausado(false);
        this.dispose(); 
    }//GEN-LAST:event_botonContinuarActionPerformed

    private void botonGuardarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarPartidaActionPerformed
        RecuperarMapa rm = new RecuperarMapa();
        String pacmanPasaPor = rm.booleanArrayToString(pasado);
        pacman.getPosX();
        pacman.getPosY();
        naranja.getPosX();
        naranja.getPosY();
        azul.getPosX();
        azul.getPosY();
        rojo.getPosX();
        rojo.getPosY();
        rosa.getPosX();
        rosa.getPosY();
        
        System.out.println(pacmanPasaPor);
    }//GEN-LAST:event_botonGuardarPartidaActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        pacman.detenerTimer();
        if(pantallaJuego.getModoInfinito()){
            pantallaJuego.borrarPanelesInfinito();
        } else {
            pantallaJuego.borrarPaneles();
        }
        
        pantallaJuego.cerrarVentana();
        this.dispose();
        PantallaMenu p = new PantallaMenu();
        p.setSize(1500, 750);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void musicaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicaCBActionPerformed
        p.setSonido(this.musica);
    }//GEN-LAST:event_musicaCBActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaPausa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPausa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPausa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPausa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonContinuar;
    private javax.swing.JButton botonGuardarPartida;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox musicaCB;
    // End of variables declaration//GEN-END:variables
}
