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


public class FantasmaAzul extends javax.swing.JPanel {
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
    private Image imagenAbiertaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulW.png")).getImage();
    private Image imagenCerradaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulWW.png")).getImage();
    private Image imagenAbiertaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulA.png")).getImage();
    private Image imagenCerradaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulAA.png")).getImage();
    private Image imagenAbiertaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulS.png")).getImage();
    private Image imagenCerradaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulSS.png")).getImage();
    private Image imagenAbiertaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulD.png")).getImage();
    private Image imagenCerradaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaAzul/azulDD.png")).getImage();
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
    private int origenX, origenY;
    
    
    
    public FantasmaAzul(int[][] mapa, Mapa mapas, PantallaJuego pantallaJuego) {
        this.mapas = mapas;
        this.mapa = mapa;
        this.pantallaJuego = pantallaJuego;
        lastDirectionChangeTime = System.currentTimeMillis();
        x = 651; y = 451;
        origenX = 651; origenY = 451;
        
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
        List<int[]> possibleDirections = new ArrayList<>();
        // Comprueba las cuatro direcciones posibles
        int[][] directions = {{0, -50}, {0, 50}, {-50, 0}, {50, 0}};
        for (int[] direction : directions) {
            int newDx = direction[0];
            int newDy = direction[1];
            // Si el fantasma puede moverse en esta dirección, añade esta dirección a la lista de direcciones posibles
            if (mapas.puedeMoverse((x + newDx) / 50, (y + newDy) / 50)) {
                possibleDirections.add(direction);
            }
        }
        // Elige una dirección aleatoria de la lista de direcciones posibles
        int[] newDirection = possibleDirections.get(random.nextInt(possibleDirections.size()));
        dx = newDirection[0];
        dy = newDirection[1];
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
        System.out.println("Posicion de fantasmaNaranja en el mapa es: (" + (x / 50) + ", " + (y / 50) + ")");
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
    
    public void setPosX(int x) {
        this.x = x;
    }

    public void setPosY(int y) {
        this.y = y;
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
    
    public void volverAlOrigen() {
        this.setPosX(origenX);
        this.setPosY(origenY);
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