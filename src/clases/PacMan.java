package clases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import pacdam.PantallaPausa;


public class PacMan extends javax.swing.JPanel {
    private int x = 0;
    private int y = 0;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    private boolean escape = false;
    final int velocidad = 20; 
    private boolean pausado = false;
    private Image imagenAbierta = new ImageIcon(getClass().getResource("/imagenes/PacDAMA.png")).getImage();
    private Image imagenCerrada = new ImageIcon(getClass().getResource("/imagenes/PacDAMC.png")).getImage();
    private Image imagenActual = imagenAbierta;
    private boolean bocaAbierta = true;
    private Mapa mapas;
    private int[][] mapa;
    private int vidas = 3;
    private int puntuacion = 0;

    public PacMan(int[][] mapa, Mapa mapas) {
        this.mapas = mapas;
        this.mapa = mapa;
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
            if (arriba && mapas.puedeMoverse(x / 50, (y - velocidad + 49) / 50)) {
                nuevaY -= velocidad;
            } else if (abajo && mapas.puedeMoverse(x / 50, (y + velocidad + 49) / 50)) {
                nuevaY += velocidad;
            } else if (izquierda && mapas.puedeMoverse((x - velocidad + 49) / 50, y / 50)) {
                nuevaX -= velocidad;
            } else if (derecha && mapas.puedeMoverse((x + velocidad + 49) / 50, y / 50)) {
                nuevaX += velocidad;
            }
            x = nuevaX;
            y = nuevaY;

            this.setBounds(x , y , this.getWidth(), this.getHeight());
            comprobarImagen();    
        }
        System.out.println("Posición de PacMan en el mapa: (" + (x / 50) + ", " + (y / 50) + ")");
    }

    
    public void comprobarImagen(){
        if (bocaAbierta) {
            imagenActual = imagenCerrada;
        } else {
            imagenActual = imagenAbierta;
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

    
    public void pausarJuego() {
        PantallaPausa p = new PantallaPausa(this);
        p.setVisible(true);
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 255));
        setMaximumSize(new java.awt.Dimension(50, 50));
        setPreferredSize(new java.awt.Dimension(50, 50));

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
