
package clases;

import bibliotecas.Conexion;
import java.sql.*;

public class RecuperarMapa {
    Conexion con = new Conexion();
    Connection conet;
    Statement st;
    ResultSet rs;
    
    String sql;

    public int[][] recuperarMapas(int mapaID) {
        sql = "SELECT DatosMapa FROM Mapas WHERE MapaID = " + mapaID;
        try {
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String datosMapaString = rs.getString("DatosMapa");
                int[][] mapa = stringToArray(datosMapaString);
                return mapa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int[][] stringToArray(String datosMapaString) {
        // Suponiendo que los datos están almacenados como una cadena con filas separadas por ";" y columnas por ","
        String[] filas = datosMapaString.split(":");
        int[][] mapa = new int[filas.length][];
        
        for (int i = 0; i < filas.length; i++) {
            String[] columnas = filas[i].split(",");
            mapa[i] = new int[columnas.length];
            for (int j = 0; j < columnas.length; j++) {
                mapa[i][j] = Integer.parseInt(columnas[j]);
            }
        }
        return mapa;
    }
}
