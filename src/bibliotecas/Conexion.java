package bibliotecas;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author diurno
 */
public class Conexion {
    Connection con;
    public Conexion()
    {
        try
        {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "SA", "");
        }
        catch(Exception e)
        {
            System.out.println("No funciona "+e);
        }
    }
    public Connection getConnection()
    {
        return con;
    }
}