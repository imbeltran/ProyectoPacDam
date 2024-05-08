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
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PantallaJuego extends javax.swing.JFrame { 
    PacMan panelPacMan;
    private Timer timer;
    private Mapa mapa;
    public int[][] datosMapa;
    private JLabel puntuacionLabel;
    


    public PantallaJuego(Mapa mapa) {
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        panelPacMan = new PacMan(datosMapa, mapa, this);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);
        
        puntuacionLabel = new JLabel("Puntuación: " + panelPacMan.getPuntuacion());
        puntuacionLabel.setBounds(this.getWidth() - 110, 10, 100, 20);
        puntuacionLabel.setForeground(Color.WHITE);
        this.add(puntuacionLabel);
        
        initComponents();    
        movimientoPacMan();
    }

    
    public void movimientoPacMan(){
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover();
                    puntuacionLabel.setText("Puntuación: " + panelPacMan.getPuntuacion());
                }
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
                    g.setColor(Color.BLACK);
                    g.fillRect(j * 50 + getInsets().left, i * 50 + getInsets().top, 50, 50);
                } else {
                    g.setColor(new Color(238, 238, 238));
                    g.fillRect(j * 50 + getInsets().left, i * 50 + getInsets().top, 50, 50);
                    g.setColor(Color.RED);
                    g.fillOval(j * 50 + getInsets().left + 20, i * 50 + getInsets().top + 20, 10, 10);
                }
            }
        }      
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapaPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mapaPanelLayout = new javax.swing.GroupLayout(mapaPanel);
        mapaPanel.setLayout(mapaPanelLayout);
        mapaPanelLayout.setHorizontalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        mapaPanelLayout.setVerticalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JPanel mapaPanel;
    // End of variables declaration//GEN-END:variables
}
