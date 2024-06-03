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
import clases.Partida;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
    private int[][] datosMapaOriginal;
    private JLabel puntuacionLabel;
    private int puntuacion;
    private int puntuacionTotal;
    //private Image criptoMoneda = new ImageIcon(getClass().getResource("/imagenes/Bitcoin.png")).getImage();
    private JPanel[][] panelesMapa;
    private boolean win;
    private boolean irBorracho = false;
    private int nivel;
    private List<FantasmaNaranja> fantasmasNaranjas;
    private List<Timer> timers;
    private boolean modoInfinito;
    Partida p = new Partida();
    private List<Timer> timersFantasmas;
    private Timer timerMovimientoPacMan;
    private Timer timerMovimientoPacManInfinito;
    private Timer timerMovimientoFantasmas;
    private Timer timerMovimientoFantasmaRojo;
    private Timer timerMovimientoFantasmasInfinito;

    public PantallaJuego(Mapa mapa) {
        this.mapa = mapa;
        this.pantallaJuego = pantallaJuego;
        this.setTitle(p.getMapaName());
        //this.labelNivel.setText("Nivel: "+p.getMapaName()); hay que mirar por que no va
        datosMapa = mapa.getMapa(p.getMapaID()-1);
        System.out.println(datosMapa);
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
        movimientoFantasmas();
        movimientoFantasmaRojo();
    }
    
    public PantallaJuego(Mapa mapa, boolean modoInfinito) {
        this.setSize(1500, 750); // Establece las dimensiones deseadas
        this.setLocationRelativeTo(null);
        this.mapa = mapa;
        datosMapa = mapa.getMapa(mapa.getIndiceMapaActual());
        datosMapaOriginal = new int[datosMapa.length][];
        for (int i = 0; i < datosMapa.length; i++) {
            datosMapaOriginal[i] = datosMapa[i].clone();
        }
        this.pantallaJuego = pantallaJuego;
        this.nivel = p.getModoInfinito();
        this.fantasmasNaranjas = new ArrayList<>();
        this.timersFantasmas = new ArrayList<>();
        this.timers = new ArrayList<>();
        this.modoInfinito = true;

        panelPacMan = new PacMan(datosMapa, mapa, this);
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
        timerMovimientoPacMan = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover(irBorracho);
                    int posX = panelPacMan.getPosX() / 50;
                    int posY = panelPacMan.getPosY() / 50;
                    // Comprueba si Pacman está en una celda con una cerveza
                    if (datosMapa[posY][posX] == 2) {
                        irBorracho = true;
                        panelPacMan.mover(irBorracho);
                        // Inicia un temporizador para desactivar la cerveza después de 5 segundos
                        new Timer(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                irBorracho = false;
                                panelPacMan.mover(irBorracho);
                            }
                        }).start();
                        // Cambia el valor en el mapa para que la cerveza no se vuelva a activar
                        datosMapa[posY][posX] = 0;
                    }
                    if (irBorracho) {
                        // Si la cerveza está activa, Pacman puede "comer" a los fantasmas
                        if (panelPacMan.chocaConFantasmaNaranja(panelFantasmaNaranja)) {
                            panelFantasmaNaranja.setBounds(651, 451, 48, 48);
                            panelFantasmaNaranja.setPosX(651);
                            panelFantasmaNaranja.setPosY(451);
                        } else if (panelPacMan.chocaConFantasmaAzul(panelFantasmaAzul)) {
                            panelFantasmaAzul.setBounds(651, 451, 48, 48);
                            panelFantasmaAzul.setPosX(651);
                            panelFantasmaAzul.setPosY(451);
                        } else if (panelPacMan.chocaConFantasmaRojo(panelFantasmaRojo)) {
                            panelFantasmaRojo.setBounds(651, 451, 48, 48);
                            panelFantasmaRojo.setPosX(651);
                            panelFantasmaRojo.setPosY(451);
                        }   
                    } else {
                        // Si la cerveza no está activa, los fantasmas pueden "comer" a Pacman
                        if (panelPacMan.chocaConFantasmaNaranja(panelFantasmaNaranja)||panelPacMan.chocaConFantasmaAzul(panelFantasmaAzul)||panelPacMan.chocaConFantasmaRojo(panelFantasmaRojo)) {
                            // Pausa el juego
                            //panelPacMan.setPausado(true);
                            detenerTodosLosTimers();
                            win = false;
                            // Abre la pantalla de fin de juego
                            
                            PantallaFinLose pantallaFin = new PantallaFinLose(puntuacion, win, mapa, false);
                            pantallaFin.setVisible(true);
                            cerrarVentana();
                        }   
                    }
                }
            }
        });
        timerMovimientoPacMan.start();
    }
    
    public void movimientoPacManInfinito(){
        timerMovimientoPacManInfinito = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelPacMan.mover(irBorracho);         
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
                                panelPacMan.mover(irBorracho);
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
                                fantasmaNaranja.setPosX(651);
                                fantasmaNaranja.setPosY(451);
                            }
                        }
                    } else {
                        // Si la cerveza no está activa, los fantasmas pueden "comer" a Pacman
                        for (FantasmaNaranja fantasmaNaranja : fantasmasNaranjas) {
                            if (panelPacMan.chocaConFantasmaNaranja(fantasmaNaranja)) {
                                // Pausa el juego
                                panelPacMan.setPausado(true);
                                win = false;
                                p.setModoInfinito(1);
                                detenerTodosLosTimers();
                                borrarPaneles();
                                // Abre la pantalla de fin de juego
                                PantallaFinLoseInfi pantallaFin = new PantallaFinLoseInfi(puntuacion, win, mapa, true);
                                pantallaFin.setVisible(true);
                                cerrarVentana();
                            }
                        }
                        // ... (repite para los otros tipos de fantasmas)
                    }
                }
            }
        });
        timerMovimientoPacManInfinito.start();
    }



    public void movimientoFantasmas(){
        timerMovimientoFantasmas = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelFantasmaNaranja.mover(irBorracho);
                    panelFantasmaAzul.mover(irBorracho);
                }
            }
        });
        timerMovimientoFantasmas.start();
    }
    
    public void movimientoFantasmaRojo(){
        timerMovimientoFantasmaRojo = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    panelFantasmaRojo.mover(irBorracho);
                }
            }
        });
        timerMovimientoFantasmaRojo.start();
    }
    
    public void movimientoFantasmasInfinito(){
        timerMovimientoFantasmasInfinito = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelPacMan.getPausado()) {
                    for (FantasmaNaranja fantasmaNaranja : fantasmasNaranjas) {
                        fantasmaNaranja.mover(irBorracho);
                    }
                }
            }
        });
        timerMovimientoFantasmasInfinito.start();
        timersFantasmas.add(timerMovimientoFantasmasInfinito);
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
                    int skin = p.getSkinID();
                    switch (skin) {
                    case 1:
                        ImageIcon iconMachete = new ImageIcon(getClass().getResource("/imagenes/machete.png"));
                        JLabel Machete = new JLabel(iconMachete);
                        Machete.setBounds(20, 20, 20, 20);
                        panel.add(Machete);
                        break;
                    case 2:
                        break;
                    default: 
                        ImageIcon iconCerveza = new ImageIcon(getClass().getResource("/imagenes/cerveza.png"));
                        JLabel cerveza = new JLabel(iconCerveza);
                        cerveza.setBounds(20, 20, 20, 20);
                        panel.add(cerveza);
                        break;
                    }
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
    
    public void borrarPaneles() {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    
    public void borrarPanelesInfinito() {
        for (FantasmaNaranja fantasma : fantasmasNaranjas) {
            remove(fantasma);
        }
        getContentPane().revalidate();
        repaint();
    }
    
    /*
    public void detenerTimers() {
        panelPacMan.detenerTimer();
        panelFantasmaNaranja.detenerTimer();
        panelFantasmaRojo.detenerTimer();
        panelFantasmaAzul.detenerTimer();
    
    }
    */
    
    public void detenerTodosLosTimers() {
        if (timerMovimientoPacMan != null) {
            timerMovimientoPacMan.stop();
        }
        if (timerMovimientoPacManInfinito != null) {
            timerMovimientoPacManInfinito.stop();
        }
        if (timerMovimientoFantasmas != null) {
            timerMovimientoFantasmas.stop();
        }
        if (timerMovimientoFantasmaRojo != null) {
            timerMovimientoFantasmaRojo.stop();
        }  
        if(timersFantasmas != null){
            for(Timer timer: timersFantasmas){
                timer.stop();
            }       
        }
    }
    
    
    public void cerrarVentana(){
        this.dispose();
    }
    
    public boolean getIrBorracho(){
        return irBorracho;
    }
    
    
    
    public boolean getModoInfinito(){
        return modoInfinito;
    }
    
    public void finalizarJuegoInfinito() {
        if (!getModoInfinito()) {
            detenerTodosLosTimers();
            this.borrarPanelesInfinito();
            this.dispose();
            win = true;
            int nivel = p.getMapaID();
            PantallaFinWin pantallaFin = new PantallaFinWin(puntuacionTotal, win, mapa, modoInfinito, nivel);
            pantallaFin.setVisible(true);
            PantallaEleccion.getInstancia().setVisible(true);
        }
    }
    
    public void finalizarJuego() {
        detenerTodosLosTimers();
        this.borrarPaneles();
        this.dispose();
        win = true;
        p.setEstado(win);
        int nivel = p.getMapaID();
        PantallaFinWin pantallaFin = new PantallaFinWin(puntuacionTotal, win, mapa, modoInfinito, nivel);
        pantallaFin.setSize(1516, 789); // Establece las dimensiones deseadas
        pantallaFin.setLocationRelativeTo(null);
        pantallaFin.setVisible(true);
        //PantallaEleccion.getInstancia().setVisible(true);
    }

    
    public void reiniciarJuegoModoInfinito() {
        //panelPacMan.setPausado(true);     
        detenerTodosLosTimers();
        for (int i = 0; i < datosMapa.length; i++) {
            datosMapa[i] = datosMapaOriginal[i].clone();
        }
        PantallaJuego nuevaPartida = new PantallaJuego(mapa, modoInfinito);       
        nuevaPartida.setSize(1500, 750);
        nuevaPartida.setLocationRelativeTo(null);
        nuevaPartida.setVisible(true);
        this.dispose();
    }



    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapaPanel = new javax.swing.JPanel();
        panelPuntuacion = new javax.swing.JPanel();
        labelPuntuacion = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        mapaPanel.setBackground(new java.awt.Color(0, 0, 153));

        panelPuntuacion.setBackground(new java.awt.Color(0, 0, 0));

        labelPuntuacion.setForeground(new java.awt.Color(255, 255, 0));
        labelPuntuacion.setText("Puntuación: ");

        labelNivel.setText("jLabel1");

        javax.swing.GroupLayout panelPuntuacionLayout = new javax.swing.GroupLayout(panelPuntuacion);
        panelPuntuacion.setLayout(panelPuntuacionLayout);
        panelPuntuacionLayout.setHorizontalGroup(
            panelPuntuacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntuacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelPuntuacionLayout.setVerticalGroup(
            panelPuntuacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntuacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPuntuacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPuntuacion)
                    .addComponent(labelNivel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mapaPanelLayout = new javax.swing.GroupLayout(mapaPanel);
        mapaPanel.setLayout(mapaPanelLayout);
        mapaPanelLayout.setHorizontalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapaPanelLayout.createSequentialGroup()
                .addComponent(panelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 256, Short.MAX_VALUE))
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
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelPuntuacion;
    private javax.swing.JPanel mapaPanel;
    private javax.swing.JPanel panelPuntuacion;
    // End of variables declaration//GEN-END:variables
}
