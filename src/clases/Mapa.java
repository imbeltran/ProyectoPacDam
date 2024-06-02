package clases;

import bibliotecas.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    Conexion con = new Conexion();
    Connection conet;
    Statement st;
    ResultSet rs;
            
    private List<int[][]> mapas;
    private int indiceMapaActual;
    Partida p = new Partida();
    
    public Mapa(){
        mapas = new ArrayList<>();
        indiceMapaActual = p.getMapaID();
        RecuperarMapa rp = new RecuperarMapa();
        
        //mapas.add(rp.RecuperarMapa(indiceMapaActual));
        try{
            String sqlGetSkinId = "Select mapaID from mapas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetSkinId);
            while (rs.next()) {
                int idMapa = rs.getInt("mapaID");
                mapas.add(rp.recuperarMapas(idMapa));
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }

    }
    
    public int[][] getMapa(int indice){
        if (indice >= 0 && indice < mapas.size()) {
            return mapas.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de límites: " + indice);
        }
    }
    
    public int getIndiceMapaActual() {
        return indiceMapaActual;
    }

    public void setIndiceMapaActual(int indice) {
        indiceMapaActual = indice;
    }
    
    
    public boolean puedeMoverse(int x, int y){
        if (mapas == null) {
            //System.out.println("nulo");
            return false;
        }
        int[][] mapaActual = mapas.get(indiceMapaActual);
        if (mapaActual == null) {
            //System.out.println("nulo2");
            return false;
        }
        if (y < 0 || y >= mapaActual[0].length || x < 0 || x >= mapaActual[0].length) {
            //System.out.println("mapa fuera de rango");
            return false;
        }
        if (mapaActual[y][x] == 1){
            //System.out.println("muro");
            return false;
        }
        return true;
    }
  
    public void siguienteMapa(){
        indiceMapaActual = (indiceMapaActual + 1)% mapas.size();
    }
    
    public int getPuntuacionTotal() {
        int total = 0;
        int[][] mapaActual = mapas.get(indiceMapaActual);
        for (int i = 0; i < mapaActual.length; i++) {
            for (int j = 0; j < mapaActual[i].length; j++) {
                if (mapaActual[i][j] == 0) {
                    total++;
                }
            }
        }
        return total;
    }

}
