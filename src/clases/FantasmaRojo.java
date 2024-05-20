package clases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import musicas.SClip;
import pacdam.PantallaEleccion;
import pacdam.PantallaJuego;
import pacdam.PantallaPausa;


public class FantasmaRojo extends javax.swing.JPanel {
    private int x = 0;
    private int y = 0;
    private int dx, dy;
    private int lastDx, lastDy;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    final int velocidad = 7; 
    private boolean pausado = false;
    private Image imagenAbiertaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoW.png")).getImage();
    private Image imagenCerradaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoWW.png")).getImage();
    private Image imagenAbiertaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoA.png")).getImage();
    private Image imagenCerradaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoAA.png")).getImage();
    private Image imagenAbiertaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoS.png")).getImage();
    private Image imagenCerradaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoSS.png")).getImage();
    private Image imagenAbiertaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoD.png")).getImage();
    private Image imagenCerradaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaRojo/RojoDD.png")).getImage();
    private Image imagenActual = imagenAbiertaD;
    private boolean bocaAbierta = true;
    private Mapa mapas;
    private int[][] mapa;
    private int puntuacion = 0;
    private PantallaJuego pantallaJuego;
    private boolean[][] visitado2;
    private boolean juegoTerminado = false;
    private int randomizer;
    private long lastDirectionChangeTime;
    private Random random = new Random();
    private Timer timer;
    private boolean movedBackwards = false;
    private PacMan pacman;
    
    
    
    public FantasmaRojo(int[][] mapa, Mapa mapas, PantallaJuego pantallaJuego, PacMan pacman) {
        this.mapas = mapas;
        this.mapa = mapa;
        this.pantallaJuego = pantallaJuego;
        this.pacman = pacman;
        lastDirectionChangeTime = System.currentTimeMillis();
        x = 651; y = 451;
        
        lastDx = dx;
        lastDy = dy;
        this.setFocusable(true);  
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenActual, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    private void cambiarDireccion() {
        // Obtiene la posición de Pacman
        int pacmanX = pacman.getPosX();
        int pacmanY = pacman.getPosY();

        // Calcula la dirección hacia Pacman
        int dx = (pacmanX - x) / Math.abs(pacmanX - x);
        int dy = (pacmanY - y) / Math.abs(pacmanY - y);

        // Comprueba si el fantasma puede moverse en la dirección de Pacman
        if (mapas.puedeMoverse((x + dx * 50) / 50, (y + dy * 50) / 50)) {
            this.dx = dx * 50;
            this.dy = dy * 50;
        } else {
            // Si no puede moverse directamente hacia Pacman, intenta moverse en una dirección perpendicular
            if (mapas.puedeMoverse((x + dy * 50) / 50, (y - dx * 50) / 50)) {
                this.dx = dy * 50;
                this.dy = -dx * 50;
            } else if (mapas.puedeMoverse((x - dy * 50) / 50, (y + dx * 50) / 50)) {
                this.dx = -dy * 50;
                this.dy = dx * 50;
            }
        }
        lastDx = dx;
        lastDy = dy;
    }

    public void mover() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDirectionChangeTime >= 1000 || !mapas.puedeMoverse((x + dx) / 50, (y + dy) / 50)) {
            cambiarDireccion();
            lastDirectionChangeTime = currentTime;
        }
        x += dx;
        y += dy;

        // Actualiza la posición del panel del fantasma
        this.setBounds(x, y, this.getWidth(), this.getHeight());

        comprobarImagen();
        repaint();
        System.out.println("Posicion de fantasmaRojo en el mapa es: (" + (x / 50) + ", " + (y / 50) + ")");
    }



    
    public void comprobarImagen(){
        if (bocaAbierta && arriba) {
            imagenActual = imagenCerradaW;
        } else {
            if (!bocaAbierta && arriba) 
                imagenActual = imagenAbiertaW;
        }
        if (bocaAbierta && abajo) {
            imagenActual = imagenCerradaS;
        } else {
            if (!bocaAbierta && abajo) 
                imagenActual = imagenAbiertaS;
        }
        if (bocaAbierta && derecha) {
            imagenActual = imagenCerradaD;
        } else {
            if (!bocaAbierta && derecha) 
                imagenActual = imagenAbiertaD;
        }
        if (bocaAbierta && izquierda) {
            imagenActual = imagenCerradaA;
        } else {
            if (!bocaAbierta && izquierda) 
                imagenActual = imagenAbiertaA;
        }
        bocaAbierta = !bocaAbierta;
        repaint();
    }
    
    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }
    
    /*public void pausarJuego() {
        PantallaPausa p = new PantallaPausa(this);
        p.setVisible(true);
    } */
    
    
    public void reiniciar() {
        x = 0;
        y = 0;

        for (int i = 0; i < visitado2.length; i++) {
            for (int j = 0; j < visitado2[i].length; j++) {
                visitado2[i][j] = false;
            }
        }
    }
    
    public void detenerTimer() {
        if (timer != null)
            timer.stop();     
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 153));
        setMaximumSize(new java.awt.Dimension(48, 48));
        setPreferredSize(new java.awt.Dimension(48, 48));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
