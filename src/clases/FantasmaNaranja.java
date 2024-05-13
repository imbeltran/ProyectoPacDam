package clases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import musicas.SClip;
import pacdam.PantallaEleccion;
import pacdam.PantallaJuego;
import pacdam.PantallaPausa;


public class FantasmaNaranja extends javax.swing.JPanel {
    private int x = 0;
    private int y = 0;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    private boolean escape = false;
    final int velocidad = 10; 
    private boolean pausado = false;
    private Image imagenAbiertaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaA.png")).getImage();
    private Image imagenCerradaW = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaAA.png")).getImage();
    private Image imagenAbiertaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaS.png")).getImage();
    private Image imagenCerradaA = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaSS.png")).getImage();
    private Image imagenAbiertaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaW.png")).getImage();
    private Image imagenCerradaS = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/NaranjaWW.png")).getImage();
    private Image imagenAbiertaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/Naranjad.png")).getImage();
    private Image imagenCerradaD = new ImageIcon(getClass().getResource("/imagenes/FantasmaNaranja/Naranjadd.png")).getImage();
    private Image imagenActual = imagenAbiertaD;
    private boolean bocaAbierta = true;
    private Mapa mapas;
    private int[][] mapa;
    private int puntuacion = 0;
    private PantallaJuego pantallaJuego;
    private final SClip sonidoMovimiento = new SClip("src/musicas/movimientoPacDam.wav");
    private boolean[][] visitado;
    private boolean juegoTerminado = false;



    public FantasmaNaranja(int[][] mapa, Mapa mapas, PantallaJuego pantallaJuego) {
        this.mapas = mapas;
        this.mapa = mapa;
        this.pantallaJuego = pantallaJuego;
        x = 51; y = 51;
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        pausado = !pausado;
                        if (pausado) {
                            pausarJuego();
                        }
                        break;
                    case KeyEvent.VK_W:
                        arriba = true;
                        abajo = false;
                        izquierda = false;
                        derecha = false;
                        break;
                    case KeyEvent.VK_A:
                        arriba = false;
                        abajo = false;
                        izquierda = true;
                        derecha = false;
                        break;
                    case KeyEvent.VK_S:
                        arriba = false;
                        abajo = true;
                        izquierda = false;
                        derecha = false;
                        break;
                    case KeyEvent.VK_D:
                        arriba = false;
                        abajo = false;
                        izquierda = false;
                        derecha = true;
                        break;     
                }
            }
        });
        visitado = new boolean[mapa.length][mapa[0].length];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                visitado[i][j] = false;
            }
        }
        this.setFocusable(true);  
        initComponents();

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenActual, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void mover() {
        int nuevaX = x;
        int nuevaY = y;
        if (escape && !pausado) {
            pausarJuego();
        }
        if (!pausado) {
            if (arriba && mapas.puedeMoverse(x / 50, (y - velocidad) / 50) && mapas.puedeMoverse((x + 47) / 50, (y - velocidad) / 50)) {
                nuevaY -= velocidad;
            } else if (abajo && mapas.puedeMoverse(x / 50, (y + velocidad + 47) / 50) && mapas.puedeMoverse((x + 47) / 50, (y + velocidad + 47) / 50)) {
                nuevaY += velocidad;
            } else if (izquierda && mapas.puedeMoverse((x - velocidad) / 50, y / 50) && mapas.puedeMoverse((x - velocidad) / 50, (y + 47) / 50)) {
                nuevaX -= velocidad;
            } else if (derecha && mapas.puedeMoverse((x + velocidad + 47) / 50, y / 50) && mapas.puedeMoverse((x + velocidad + 47) / 50, (y + 47) / 50)) {
                nuevaX += velocidad;
            }
            x = nuevaX;
            y = nuevaY;
            this.setBounds(x , y , this.getWidth(), this.getHeight());
            
            int matrizX = x / 50;
            int matrizY = y / 50;
            if (!visitado[matrizX][matrizY]) {
                visitado[matrizX][matrizY] = true;
                sumarPuntuacion();
            }
            if (puntuacion == mapas.getPuntuacionTotal()) {
                System.out.println("¡Felicidades! Te has pasado el nivel.");
                pantallaJuego.dispose();
                PantallaEleccion.getInstancia().setVisible(true);
            }
            comprobarImagen();
        }
        System.out.println("Posicion de PacMan en el mapa: (" + (x / 50) + ", " + (y / 50) + ")");
        System.out.println("Puntuacion: "+puntuacion);
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
    
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
    
    public boolean getPausado() {
        return this.pausado;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
   
    public void pausarJuego() {
        sonidoMovimiento.stop();
        //PantallaPausa p = new PantallaPausa(this, musica);
        //p.setVisible(true);
    } 
    
    public void sumarPuntuacion() {
        puntuacion = puntuacion +1;
    }
    
        public void reiniciar() {
        x = 0;
        y = 0;

        puntuacion = 0;

        for (int i = 0; i < visitado.length; i++) {
            for (int j = 0; j < visitado[i].length; j++) {
                visitado[i][j] = false;
            }
        }
    }

        
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(238, 238, 238));
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
