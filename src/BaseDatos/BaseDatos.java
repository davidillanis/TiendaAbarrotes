package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {
    public String nombre="tienda";
    
    public BaseDatos() {
        try {
            String codigo_sql="CREATE DATABASE if NOT EXISTS "+nombre;
            
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost","root","");
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close();
            cn.close();
        } catch (SQLException e) {System.out.println("Error BaseDatos->constructor"+e);}
    }
    public Connection Conexion() throws SQLException{
        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/"+nombre,"root","");
        return cn;
    }
    public void EjecutarCodigo(String codigo_sql) throws SQLException {
        Connection cn=Conexion();
        PreparedStatement pst=cn.prepareStatement(codigo_sql);
        pst.execute();
        pst.close(); cn.close();
    }
    
    private void NumConection(){
        try {
            int num;
            String codigo_sql="SHOW STATUS LIKE 'Threads_connected';";
            Connection cn=Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                num=rs.getInt("Value");
                System.out.println(num);
            }
            cn.close();
            pst.close();
        } catch (SQLException e) {System.out.println("Error BaseDatos->NumConection");}
    }
}
