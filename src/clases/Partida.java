/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import bibliotecas.Conexion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bik
 */
public class Partida {
    Conexion con = new Conexion();
    Connection conet;
    Statement st;
    ResultSet rs;
   
    
    
    public boolean crearPartida()
    {
        try {
            String sqlDelete = "Delete from partidas where partidaID = "+getPartidaID();
            String sqlInsert = "INSERT INTO Partidas (PartidaID, MapaID, SkinID) VALUES ("+getPartidaIDMas1()+",1,0)";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlDelete);
            rs = st.executeQuery(sqlInsert);
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getPartidaID()
    {
        try{
            String sqlGetPartidaId = "Select PartidaID from partidas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetPartidaId);
            if (rs.next()) 
            {
                return rs.getInt("PartidaID");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 1;
    }
    public int getPartidaIDMas1()
    {
        try{
            String sqlGetPartidaId = "Select PartidaID from partidas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetPartidaId);
            if (rs.next()) 
            {
                return rs.getInt("PartidaID")+1;
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 1;
    }
    public int getMapaID()
    {
        try{
            String sqlGetMapaId = "Select MapaID from partidas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetMapaId);
            if (rs.next()) 
            {
                return rs.getInt("MapaID");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 1;
    }
    public int getMapaIDmas1()
    {
        try{
            String sqlGetMapaId = "Select MapaID from partidas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetMapaId);
            if (rs.next()) 
            {
                return rs.getInt("MapaID")+1;
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 1;
    }
    public String getMapaName()
    {
        try{
            String sqlGetMapaName = "Select Nombre from Mapas m, Partidas p where m.MapaID = p.MapaID";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetMapaName);
            if (rs.next()) 
            {
                return rs.getString("Nombre");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return "";
    }
    public Boolean getSonido()
    {
        try{
            String sqlGetMusica = "Select Sonido from Configuracion";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetMusica);
            if (rs.next()) 
            {
                return rs.getBoolean("Sonido");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
    public int getSkinID()
    {
        try{
            String sqlGetSkinId = "Select SkinId from partidas";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetSkinId);
            if (rs.next()) 
            {
                return rs.getInt("SkinId");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }
    public void setSkinID(int Id)
    {
        try{
            String sqlSetSkinId = "UPDATE Partidas SET SkinID = "+Id;
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlSetSkinId);
            
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    public void setEstado(Boolean win)
    {
        try{
            String sqlSetSkinId = "UPDATE Mapas SET Estado = "+win + " where MapaId = "+getMapaID();
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlSetSkinId);
            
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public int getPuntuacionInfinito()
    {
        try{
            String sqlGetPuntuacionInfinito = "Select nivelinfinito from mapas where mapaID = 1";
            conet = con.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sqlGetPuntuacionInfinito);
            if (rs.next()) 
            {
                return rs.getInt("nivelinfinito");
            }
        }catch(SQLException e) 
        {
            e.printStackTrace();
        }
        return 1;
    }
    
    
    
   /*/pruebas
    
    public void saveGame(int partidaGuardadaID, int partidaID, int mapaID, int puntuacion, int pacmanX, int pacmanY, int[][] mapa, Connection connection) throws SQLException, IOException {
        String sql = "INSERT INTO PartidaGuardada (PartidaGuardadaID, PartidaID, MapaID, Puntuacion, PosicionPacManX, PosicionPacManY, MapaEstado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, partidaGuardadaID);
        preparedStatement.setInt(2, partidaID);
        preparedStatement.setInt(3, mapaID);
        preparedStatement.setInt(4, puntuacion);
        preparedStatement.setInt(5, pacmanX);
        preparedStatement.setInt(6, pacmanY);
        preparedStatement.setBytes(7, serializeMap(mapa));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public byte[] serializeMap(int[][] map) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(map);
        objectOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        objectOutputStream.close();
        return byteArray;
    }
    public int[][] deserializeMap(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        int[][] map = (int[][]) objectInputStream.readObject();
        objectInputStream.close();
        return map;
    }
    public int[][] loadGameMap(int partidaGuardadaID, Connection connection) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT MapaEstado FROM PartidaGuardada WHERE PartidaGuardadaID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, partidaGuardadaID);
        ResultSet resultSet = preparedStatement.executeQuery();
        int[][] mapa = null;
        if (resultSet.next()) {
            byte[] mapaBytes = resultSet.getBytes("MapaEstado");
            mapa = deserializeMap(mapaBytes);
        }
        resultSet.close();
        preparedStatement.close();
        return mapa;
    }*/
}

