package pacdam;

import clases.Mapa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import clases.PacMan;
import java.awt.Color;
import java.awt.Graphics;

public class PantallaJuego extends javax.swing.JFrame { 
    PacMan panelPacMan;
    private Timer timer;
    private Mapa mapa;
    private int[][] datosMapa;
      
    public PantallaJuego(Mapa mapa) {
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        panelPacMan = new PacMan(datosMapa, mapa);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 49, 49);
        initComponents();
         
        movimientoPacMan();
    }
    
    
    public void movimientoPacMan(){
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover();
                }
                repaint();
            }
        });
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < datosMapa.length; i++) {
            for (int j = 0; j < datosMapa[i].length; j++) {
                if (datosMapa[i][j] == 1) {
                    g.setColor(Color.BLACK); // Color de los muros
                } else {
                    g.setColor(Color.BLUE); // Color de los caminos
                }
                g.fillRect(j * 50 + getInsets().left, i * 50 + getInsets().top, 50, 50); // Pinta un cuadrado en la posición correspondiente
            }
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mapa mapa = new Mapa();
                new PantallaJuego(mapa).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
