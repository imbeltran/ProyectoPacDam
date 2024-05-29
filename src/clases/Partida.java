/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import bibliotecas.Conexion;
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
            String sqlInsert = "INSERT INTO Partidas (PartidaID, MapaID, SkinID) VALUES ("+getPartidaIDMas1()+",1,1)";
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
}

