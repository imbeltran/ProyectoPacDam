package clases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import musicas.SClip;
import pacdam.PantallaEleccion;
import pacdam.PantallaFin;
import pacdam.PantallaJuego;
import pacdam.PantallaPausa;


public class PacMan extends javax.swing.JPanel {
    private int x = 0;
    private int y = 0;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    private boolean escape = false;
    final int velocidad = 10; 
    private boolean pausado = false;
    private Image imagenAbiertaW = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMw.png")).getImage();
    private Image imagenCerradaW = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMww.png")).getImage();
    private Image imagenAbiertaA = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMa.png")).getImage();
    private Image imagenCerradaA = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMaa.png")).getImage();
    private Image imagenAbiertaS = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMs.png")).getImage();
    private Image imagenCerradaS = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMss.png")).getImage();
    private Image imagenAbiertaD = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMd.png")).getImage();
    private Image imagenCerradaD = new ImageIcon(getClass().getResource("/imagenes/PacDam/PacDAMdd.png")).getImage();
    private Image imagenActual = imagenAbiertaD;
    private boolean bocaAbierta = true;
    private Mapa mapas;
    private int[][] mapa;
    private int puntuacion = 0;
    private PantallaJuego pantallaJuego;
    private final SClip sonidoMovimiento = new SClip("src/musicas/movimientoPacDam.wav");
    private boolean[][] visitado;
    private boolean juegoTerminado = false;
    private boolean musica;
    private boolean cambiandoDireccion = false;
    private Timer timer;
    private boolean win;
    
    //añadido nuevo 
    private boolean nuevaArriba = false;
    private boolean nuevaAbajo = false;
    private boolean nuevaIzquierda = false;
    private boolean nuevaDerecha = false;


    public PacMan(int[][] mapa, Mapa mapas, PantallaJuego pantallaJuego) {
        initComponents();
    }
    
    public PacMan(int[][] mapa, Mapa mapas, PantallaJuego pantallaJuego, boolean musica) {
        this.mapas = mapas;
        this.mapa = mapa;
        this.pantallaJuego = pantallaJuego;
        this.musica = musica;
        x = 51; y = 51;
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char currentKey = e.getKeyChar();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        pausado = !pausado;
                        if (pausado) {
                            pausarJuego();
                        }
                        break;
                    case KeyEvent.VK_W:
                        nuevaArriba = true;
                        nuevaAbajo = false;
                        nuevaIzquierda = false;
                        nuevaDerecha = false;
                        if(musica){
                            sonidoMovimiento.stop();
                            sonidoMovimiento.loop();
                        }
                        break;
                    case KeyEvent.VK_A:
                        nuevaArriba = false;
                        nuevaAbajo = false;
                        nuevaIzquierda = true;
                        nuevaDerecha = false;
                        if(musica){
                            sonidoMovimiento.stop();
                            sonidoMovimiento.loop();      
                        }
                        break;
                    case KeyEvent.VK_S:
                        nuevaArriba = false;
                        nuevaAbajo = true;
                        nuevaIzquierda = false;
                        nuevaDerecha = false;
                        if(musica){
                            sonidoMovimiento.stop();
                            sonidoMovimiento.loop(); 
                        }
                        break;
                    case KeyEvent.VK_D:    
                        nuevaArriba = false;
                        nuevaAbajo = false;
                        nuevaIzquierda = false;
                        nuevaDerecha = true;
                        if(musica){
                            sonidoMovimiento.stop();
                            sonidoMovimiento.loop();       
                        }
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
            // Intentar cambiar de dirección primero
            if (nuevaArriba && mapas.puedeMoverse(x / 50, (y - velocidad) / 50) && mapas.puedeMoverse((x + 47) / 50, (y - velocidad) / 50)) {
                arriba = true;
                abajo = false;
                izquierda = false;
                derecha = false;
            } else if (nuevaAbajo && mapas.puedeMoverse(x / 50, (y + velocidad + 47) / 50) && mapas.puedeMoverse((x + 47) / 50, (y + velocidad + 47) / 50)) {
                arriba = false;
                abajo = true;
                izquierda = false;
                derecha = false;
            } else if (nuevaIzquierda && mapas.puedeMoverse((x - velocidad) / 50, y / 50) && mapas.puedeMoverse((x - velocidad) / 50, (y + 47) / 50)) {
                arriba = false;
                abajo = false;
                izquierda = true;
                derecha = false;
            } else if (nuevaDerecha && mapas.puedeMoverse((x + velocidad + 47) / 50, y / 50) && mapas.puedeMoverse((x + velocidad + 47) / 50, (y + 47) / 50)) {
                arriba = false;
                abajo = false;
                izquierda = false;
                derecha = true;
            }

            // Intentar moverse en la dirección actual
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
            this.setBounds(x, y, this.getWidth(), this.getHeight());

            int matrizX = x / 50;
            int matrizY = y / 50;
            if (matrizX >= 0 && matrizX < visitado[0].length && matrizY >= 0 && matrizY < visitado.length) {
                if (!visitado[matrizY][matrizX]) {
                    visitado[matrizY][matrizX] = true;
                    sumarPuntuacion();
                    // Llama a pacmanPasaPor cuando Pacman visita una nueva casilla
                    pantallaJuego.pacmanPasaPor(matrizY, matrizX);
                }
            }

            if (puntuacion == mapas.getPuntuacionTotal()) {
                if (pantallaJuego.getModoInfinito()){
                    
                } else {
                    System.out.println("¡Felicidades! Te has pasado el nivel.");
                pantallaJuego.detenerTimers();
                pantallaJuego.borrarPaneles();
                pantallaJuego.dispose();
                win = true;
                PantallaFin pantallaFin = new PantallaFin(puntuacion, win, mapas, musica);
                pantallaFin.setVisible(true);
                PantallaEleccion.getInstancia().setVisible(true);
                puntuacion = 0;
                }               
            }

            comprobarImagen();
        }
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
    
    public void sumarPuntuacion() {
        puntuacion = puntuacion +1;
        pantallaJuego.actualizarPuntuacion(puntuacion);
    }
   
    public void pausarJuego() {
        sonidoMovimiento.stop();
        PantallaPausa p = new PantallaPausa(this, musica, pantallaJuego);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
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

    public boolean isMusica() {
        return musica;
    }

    public void setMusica(boolean musica) {
        this.musica = musica;
    }
    
    public void detenerTimer() {
        if (timer != null)
            timer.stop();     
    }
    
    public boolean chocaConFantasmaNaranja(FantasmaNaranja fantasma) {
        // Comprueba si las posiciones de Pacman y del fantasma están dentro de un cierto rango
        return !pantallaJuego.getIrBorracho() && Math.abs(this.getPosX() - fantasma.getPosX()) < 25 && Math.abs(this.getPosY() - fantasma.getPosY()) < 25;
    }

    public boolean chocaConFantasmaAzul(FantasmaAzul fantasma) {
        // Comprueba si las posiciones de Pacman y del fantasma están dentro de un cierto rango
        return !pantallaJuego.getIrBorracho() && Math.abs(this.getPosX() - fantasma.getPosX()) < 25 && Math.abs(this.getPosY() - fantasma.getPosY()) < 25;
    }

    public boolean chocaConFantasmaRojo(FantasmaRojo fantasma) {
        // Comprueba si las posiciones de Pacman y del fantasma están dentro de un cierto rango
        return !pantallaJuego.getIrBorracho() && Math.abs(this.getPosX() - fantasma.getPosX()) < 25 && Math.abs(this.getPosY() - fantasma.getPosY()) < 25;
    }
    
    public boolean chocaConFantasmaNaranjaInfinito(List<FantasmaNaranja> fantasmasNaranjas) {
        for (FantasmaNaranja fantasma : fantasmasNaranjas) {
            if (fantasma != null && !pantallaJuego.getIrBorracho() && Math.abs(this.getPosX() - fantasma.getPosX()) < 25 && Math.abs(this.getPosY() - fantasma.getPosY()) < 25) {
                // Aquí va tu código cuando Pacman choca con un FantasmaNaranja
                return true;
            }
        }
        return false;
    }



        
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 153));
        setMaximumSize(new java.awt.Dimension(48, 48));
        setOpaque(false);
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
