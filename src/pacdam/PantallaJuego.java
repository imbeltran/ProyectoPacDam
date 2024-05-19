package pacdam;

import clases.FantasmaNaranja;
import clases.Mapa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import clases.PacMan;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PantallaJuego extends javax.swing.JFrame { 
    PacMan panelPacMan;
    FantasmaNaranja panelFantasmaNaranja;
    FantasmaNaranja panelFantasmaNaranja1;
    FantasmaNaranja panelFantasmaNaranja2;
    FantasmaNaranja panelFantasmaNaranja3;
    private Timer timer;
    private Mapa mapa;
    public int[][] datosMapa;
    private JLabel puntuacionLabel;
    private int puntuacion;
    private boolean musica;
    private Image criptoMoneda = new ImageIcon(getClass().getResource("/imagenes/cerveza.png")).getImage();
    private JPanel[][] panelesMapa;
    private boolean win;

    public PantallaJuego(Mapa mapa) {
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        panelPacMan = new PacMan(datosMapa, mapa, this);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);
        
        panelFantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja);
        panelFantasmaNaranja.setBounds(panelFantasmaNaranja.getPosX(), panelFantasmaNaranja.getPosY(), 48, 48);
        
        panelFantasmaNaranja1 = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja1);
        panelFantasmaNaranja1.setBounds(panelFantasmaNaranja1.getPosX(), panelFantasmaNaranja1.getPosY(), 48, 48);
        
        crearMapa();
        initComponents();
        movimientoPacMan();
    }
    
    public PantallaJuego(Mapa mapa, boolean musica) {
        this.mapa = mapa;
        this.musica = musica;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        panelPacMan = new PacMan(datosMapa, mapa, this, musica);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);
        
        panelFantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja);
        panelFantasmaNaranja.setBounds(panelFantasmaNaranja.getPosX(), panelFantasmaNaranja.getPosY(), 48, 48);
        
        panelFantasmaNaranja1 = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja1);
        panelFantasmaNaranja1.setBounds(panelFantasmaNaranja1.getPosX(), panelFantasmaNaranja1.getPosY(), 48, 48);
        
        panelFantasmaNaranja2 = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja2);
        panelFantasmaNaranja2.setBounds(panelFantasmaNaranja2.getPosX(), panelFantasmaNaranja2.getPosY(), 48, 48);
        
        panelFantasmaNaranja3 = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja3);
        panelFantasmaNaranja3.setBounds(panelFantasmaNaranja3.getPosX(), panelFantasmaNaranja3.getPosY(), 48, 48);
        
        crearMapa();          
        initComponents();    
        movimientoPacMan();
        movimientoFantasmaNaranja();
    }
    
    public void movimientoPacMan(){
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover();
                    if (panelPacMan.chocaConFantasma(panelFantasmaNaranja)||panelPacMan.chocaConFantasma(panelFantasmaNaranja1)||panelPacMan.chocaConFantasma(panelFantasmaNaranja2)||panelPacMan.chocaConFantasma(panelFantasmaNaranja3)) {
                    // Pausa el juego
                    panelPacMan.setPausado(true);
                    win = false;
                    // Abre la pantalla de fin de juego
                    PantallaFin pantallaFin = new PantallaFin(puntuacion, win);
                    pantallaFin.setVisible(true);
                }
                }
            }
        });
        timer.start();
    }
    
    public void movimientoFantasmaNaranja(){
        timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelFantasmaNaranja.mover();
                    panelFantasmaNaranja1.mover();
                    panelFantasmaNaranja2.mover();
                    panelFantasmaNaranja3.mover();
                }
            }
        });
        timer.start();
    }
    
    public void crearMapa() {
        this.setLayout(null); // No usamos un layout manager
        panelesMapa = new JPanel[datosMapa.length][datosMapa[0].length];
        for (int i = 0; i < datosMapa.length; i++) {
            for (int j = 0; j < datosMapa[i].length; j++) {
                JPanel panel = new JPanel();
                panel.setLayout(null);
                if (i == 0 && j < 3) {
                    continue;
                }
                if (datosMapa[i][j] == 1) {
                    panel.setBackground(Color.BLACK);
                } else {
                    panel.setBackground(new Color(0, 0, 153));
                    JPanel cerveza = new JPanel();
                    cerveza.setBackground(RED);
                    cerveza.setBounds(20, 20, 10, 10);
                    panel.add(cerveza);
                }
                panel.setBounds(j * 50, i * 50, 50, 50); // Establecemos la posición y el tamaño del panel
                this.add(panel);
                panelesMapa[i][j] = panel; // Almacenamos el panel en la matriz
            }
        }
        this.revalidate(); // Para asegurar que los cambios en el layout se apliquen
    }
  
    public void actualizarPuntuacion(int puntuacion){
        labelPuntuacion.setText("Puntuacion: " + puntuacion);
        this.puntuacion = puntuacion;
    }
    
    public void pacmanPasaPor(int i, int j) {
        JPanel panel = panelesMapa[i][j];
        if (datosMapa[i][j] != 1) { // Si la casilla no es un muro
            panel.removeAll(); // Elimina la cerveza
            panel.setBackground(new Color(0, 0, 153)); // Repinta el panel
            panel.revalidate();
            panel.repaint();
        }
    }
    
    public void borrarPaneles() {
        this.getContentPane().remove(panelPacMan);
        this.getContentPane().remove(panelFantasmaNaranja);
        this.getContentPane().remove(panelFantasmaNaranja1);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    
    public void detenerTimers() {
        panelPacMan.detenerTimer();
        panelFantasmaNaranja.detenerTimer();
    }
    
    public void cerrarVentana(){
        this.dispose();
    }


    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapaPanel = new javax.swing.JPanel();
        panelPuntuacion = new javax.swing.JPanel();
        labelPuntuacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        mapaPanel.setBackground(new java.awt.Color(0, 0, 153));

        panelPuntuacion.setBackground(new java.awt.Color(0, 0, 0));

        labelPuntuacion.setForeground(new java.awt.Color(255, 255, 0));
        labelPuntuacion.setText("Puntuación: ");

        javax.swing.GroupLayout panelPuntuacionLayout = new javax.swing.GroupLayout(panelPuntuacion);
        panelPuntuacion.setLayout(panelPuntuacionLayout);
        panelPuntuacionLayout.setHorizontalGroup(
            panelPuntuacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntuacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelPuntuacionLayout.setVerticalGroup(
            panelPuntuacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntuacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPuntuacion)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mapaPanelLayout = new javax.swing.GroupLayout(mapaPanel);
        mapaPanel.setLayout(mapaPanelLayout);
        mapaPanelLayout.setHorizontalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapaPanelLayout.createSequentialGroup()
                .addComponent(panelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 404, Short.MAX_VALUE))
        );
        mapaPanelLayout.setVerticalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapaPanelLayout.createSequentialGroup()
                .addComponent(panelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
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
    private javax.swing.JLabel labelPuntuacion;
    private javax.swing.JPanel mapaPanel;
    private javax.swing.JPanel panelPuntuacion;
    // End of variables declaration//GEN-END:variables
}
