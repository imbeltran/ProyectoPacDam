package pacdam;

import clases.FantasmaAzul;
import clases.FantasmaNaranja;
import clases.FantasmaRojo;
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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PantallaJuego extends javax.swing.JFrame { 
    PacMan panelPacMan;
    FantasmaNaranja panelFantasmaNaranja;
    FantasmaRojo panelFantasmaRojo;
    FantasmaAzul panelFantasmaAzul;
    PantallaJuego pantallaJuego;
    private Timer timer;
    private Mapa mapa;
    public int[][] datosMapa;
    private JLabel puntuacionLabel;
    private int puntuacion;
    private int puntuacionTotal;
    private boolean musica;
    //private Image criptoMoneda = new ImageIcon(getClass().getResource("/imagenes/Bitcoin.png")).getImage();
    private JPanel[][] panelesMapa;
    private boolean win;
    private boolean irBorracho = false;
    private int nivel;
    private List<FantasmaNaranja> fantasmasNaranjas;
    private List<Timer> timers;
    private boolean modoInfinito;

    public PantallaJuego(Mapa mapa) {
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        this.pantallaJuego = pantallaJuego;
        
        panelPacMan = new PacMan(datosMapa, mapa, this);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);
        
        panelFantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja);
        panelFantasmaNaranja.setBounds(panelFantasmaNaranja.getPosX(), panelFantasmaNaranja.getPosY(), 48, 48);
        
        panelFantasmaRojo = new FantasmaRojo(datosMapa, mapa, this, panelPacMan);
        this.add(panelFantasmaRojo);
        panelFantasmaRojo.setBounds(panelFantasmaRojo.getPosX(), panelFantasmaRojo.getPosY(), 48, 48);
        
        panelFantasmaAzul = new FantasmaAzul(datosMapa, mapa, this);
        this.add(panelFantasmaAzul);
        panelFantasmaAzul.setBounds(panelFantasmaAzul.getPosX(), panelFantasmaAzul.getPosY(), 48, 48);
        
        
        crearMapa();
        initComponents();
        movimientoPacMan();
    }
    
    public PantallaJuego(Mapa mapa, boolean musica) {
        this.mapa = mapa;
        this.musica = musica;
        this.pantallaJuego = pantallaJuego;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        panelPacMan = new PacMan(datosMapa, mapa, this, musica);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);
        
        panelFantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
        this.add(panelFantasmaNaranja);
        panelFantasmaNaranja.setBounds(panelFantasmaNaranja.getPosX(), panelFantasmaNaranja.getPosY(), 48, 48);
        
        panelFantasmaRojo = new FantasmaRojo(datosMapa, mapa, this, panelPacMan);
        this.add(panelFantasmaRojo);
        panelFantasmaRojo.setBounds(panelFantasmaRojo.getPosX(), panelFantasmaRojo.getPosY(), 48, 48);
        
        panelFantasmaAzul = new FantasmaAzul(datosMapa, mapa, this);
        this.add(panelFantasmaAzul);
        panelFantasmaAzul.setBounds(panelFantasmaAzul.getPosX(), panelFantasmaAzul.getPosY(), 48, 48);
        
        
        crearMapa();          
        initComponents();    
        movimientoPacMan();
        movimientoFantasmas();
        movimientoFantasmaRojo();
    }
    
    public PantallaJuego(Mapa mapa, boolean musica, boolean modoInfinito) {
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        this.pantallaJuego = pantallaJuego;
        this.nivel = 1;
        this.fantasmasNaranjas = new ArrayList<>();
        this.timers = new ArrayList<>();

        panelPacMan = new PacMan(datosMapa, mapa, this, musica);
        this.add(panelPacMan);
        panelPacMan.setBounds(panelPacMan.getPosX(), panelPacMan.getPosY(), 48, 48);

        for (int i = 0; i < 4 + 2 * (nivel - 1); i++) {
            FantasmaNaranja fantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
            this.add(fantasmaNaranja);
            fantasmaNaranja.setBounds(fantasmaNaranja.getPosX(), fantasmaNaranja.getPosY(), 48, 48);
            fantasmasNaranjas.add(fantasmaNaranja);
        }

        crearMapa();
        initComponents();
        movimientoPacManInfinito();
        movimientoFantasmasInfinito();
    }
    
    
    
    /*
    public void movimientoPacMan(){
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover();
                    if (panelPacMan.chocaConFantasmaNaranja(panelFantasmaNaranja)||panelPacMan.chocaConFantasmaAzul(panelFantasmaAzul)||panelPacMan.chocaConFantasmaRojo(panelFantasmaRojo)) {
                        // Pausa el juego
                        panelPacMan.setPausado(true);
                        win = false;
                        // Abre la pantalla de fin de juego
                        PantallaFin pantallaFin = new PantallaFin(puntuacion, win, mapa, musica);
                        pantallaFin.setVisible(true);
                        cerrarVentana();
                    }   
                }
            }
        });
        timer.start();
    }*/
    
    public void movimientoPacMan(){
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover();
                    int posX = panelPacMan.getPosX() / 50;
                    int posY = panelPacMan.getPosY() / 50;
                    // Comprueba si Pacman está en una celda con una cerveza
                    if (datosMapa[posY][posX] == 2) {
                        irBorracho = true;
                        // Inicia un temporizador para desactivar la cerveza después de 5 segundos
                        new Timer(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                irBorracho = false;
                            }
                        }).start();
                        // Cambia el valor en el mapa para que la cerveza no se vuelva a activar
                        datosMapa[posY][posX] = 0;
                    }
                    if (irBorracho) {
                        // Si la cerveza está activa, Pacman puede "comer" a los fantasmas
                        if (panelPacMan.chocaConFantasmaNaranja(panelFantasmaNaranja)) {
                            panelFantasmaNaranja.setBounds(651, 451, 48, 48);
                        } else if (panelPacMan.chocaConFantasmaAzul(panelFantasmaAzul)) {
                            panelFantasmaAzul.setBounds(651, 451, 48, 48);
                        } else if (panelPacMan.chocaConFantasmaRojo(panelFantasmaRojo)) {
                            panelFantasmaRojo.setBounds(651, 451, 48, 48);
                        }   
                    } else {
                        // Si la cerveza no está activa, los fantasmas pueden "comer" a Pacman
                        if (panelPacMan.chocaConFantasmaNaranja(panelFantasmaNaranja)||panelPacMan.chocaConFantasmaAzul(panelFantasmaAzul)||panelPacMan.chocaConFantasmaRojo(panelFantasmaRojo)) {
                            // Pausa el juego
                            panelPacMan.setPausado(true);
                            win = false;
                            // Abre la pantalla de fin de juego
                            PantallaFin pantallaFin = new PantallaFin(puntuacion, win, mapa, musica);
                            pantallaFin.setVisible(true);
                            cerrarVentana();
                        }   
                    }
                }
            }
        });
        timer.start();
    }
    
    public void movimientoPacManInfinito(){
    timer = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!panelPacMan.getPausado()) {
                panelPacMan.mover();
                verificarPuntos();
                int posX = panelPacMan.getPosX() / 50;
                int posY = panelPacMan.getPosY() / 50;
                // Comprueba si Pacman está en una celda con una cerveza
                if (datosMapa[posY][posX] == 2) {
                    irBorracho = true;
                    // Inicia un temporizador para desactivar la cerveza después de 5 segundos
                    new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            irBorracho = false;
                        }
                    }).start();
                    // Cambia el valor en el mapa para que la cerveza no se vuelva a activar
                    datosMapa[posY][posX] = 0;
                }
                if (irBorracho) {
                    // Si la cerveza está activa, Pacman puede "comer" a los fantasmas
                    for (FantasmaNaranja fantasmaNaranja : fantasmasNaranjas) {
                        if (panelPacMan.chocaConFantasmaNaranja(fantasmaNaranja)) {
                            fantasmaNaranja.setBounds(651, 451, 48, 48);
                        }
                    }
                    // ... (repite para los otros tipos de fantasmas)
                } else {
                    // Si la cerveza no está activa, los fantasmas pueden "comer" a Pacman
                    for (FantasmaNaranja fantasmaNaranja : fantasmasNaranjas) {
                        if (panelPacMan.chocaConFantasmaNaranja(fantasmaNaranja)) {
                            // Pausa el juego
                            panelPacMan.setPausado(true);
                            win = false;
                            // Abre la pantalla de fin de juego
                            PantallaFin pantallaFin = new PantallaFin(puntuacion, win, mapa, musica);
                            pantallaFin.setVisible(true);
                            cerrarVentana();
                        }
                    }
                    // ... (repite para los otros tipos de fantasmas)
                }
            }
        }
    });
    timer.start();
}



    public void movimientoFantasmas(){
        timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelFantasmaNaranja.mover();
                    panelFantasmaAzul.mover();
                }
            }
        });
        timer.start();
    }
    
    public void movimientoFantasmaRojo(){
        timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelFantasmaRojo.mover();
                }
            }
        });
        timer.start();
    }
    
    public void movimientoFantasmasInfinito(){
        Timer timerFantasmas = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    for (FantasmaNaranja fantasmaNaranja : fantasmasNaranjas) {
                        fantasmaNaranja.mover();
                    }
                }
            }
        });
        timerFantasmas.start();
        timers.add(timerFantasmas);
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
                } else if (datosMapa[i][j] == 2) {
                    panel.setBackground(new Color(0, 0, 153));
                    ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/cerveza.png"));
                    JLabel cerveza = new JLabel(icon);
                    cerveza.setBounds(20, 20, 20, 20);
                    panel.add(cerveza);
                } else {
                    panel.setBackground(new Color(0, 0, 153));
                    ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/Bitcoin.png"));
                    JLabel bitcoin = new JLabel(icon);
                    bitcoin.setBounds(20, 20, 20, 20);
                    panel.add(bitcoin);
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
    
    /*public void borrarPaneles() {
        this.getContentPane().remove(panelPacMan);
        this.getContentPane().remove(panelFantasmaNaranja);
        this.getContentPane().remove(panelFantasmaRojo);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }*/
    
    public void borrarPaneles() {
        for (Component comp : this.getContentPane().getComponents()) {
            if (comp != null) {
                this.getContentPane().remove(comp);
            }
        }
        this.getContentPane().repaint();
    }
    
    public void detenerTimers() {
        panelPacMan.detenerTimer();
        //panelFantasmaNaranja.detenerTimer();
    }
    
    public void cerrarVentana(){
        this.dispose();
    }
    
    public boolean getIrBorracho(){
        return irBorracho;
    }
    
    public void verificarPuntos() {
        boolean todosLosPuntosRecogidos = true;
        for (int i = 0; i < datosMapa.length; i++) {
            for (int j = 0; j < datosMapa[i].length; j++) {
                if (datosMapa[i][j] == 0) { // Asume que 1 representa un punto en tu mapa
                    todosLosPuntosRecogidos = false;
                    break;
                }
            }
            if (!todosLosPuntosRecogidos) {
                break;
            }
        }

        if (todosLosPuntosRecogidos) {
            nivel++;
            for (int i = 0; i < 2; i++) {
                FantasmaNaranja fantasmaNaranja = new FantasmaNaranja(datosMapa, mapa, this);
                this.add(fantasmaNaranja);
                fantasmaNaranja.setBounds(fantasmaNaranja.getPosX(), fantasmaNaranja.getPosY(), 48, 48);
                fantasmasNaranjas.add(fantasmaNaranja);
            }
            for (Timer timer : timers) {
                timer.stop();
            }
            // Suma la puntuación a puntuacionTotal
            puntuacionTotal += panelPacMan.getPuntuacion();
            // Reinicia la puntuación de Pacman
            panelPacMan.setPuntuacion(0);
            // Reinicia la partida
            this.dispose(); // Cierra la ventana actual
            PantallaJuego nuevaPartida = new PantallaJuego(mapa, musica, true); // Abre una nueva partida
            nuevaPartida.setVisible(true);
        }
    }
    
    public boolean getModoInfinito(){
        return modoInfinito;
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
