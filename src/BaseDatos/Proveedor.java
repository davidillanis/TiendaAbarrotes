package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proveedor {
    //nombre, dni, telefono, email, dir
    private BaseDatos bd=new BaseDatos();
    public String nombre_bd="proveedor";

    public Proveedor() {
        try{
            Connection cn=bd.Conexion();
            String codigo_sql="CREATE TABLE IF NOT EXISTS "+bd.nombre+"."+nombre_bd+"(\n" +
                            "   DNI_proveedor varchar(8) not null,\n" +
                            "   Nombre varchar(30) not null,\n" +
                            "   Telefono varchar(9) not null,\n" +
                            "   Email varchar(30) not null,\n" +
                            "   Direccion varchar(30) not null,\n" +
                            "   PRIMARY KEY(DNI_proveedor)\n" +
                            ");";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        }catch(SQLException e){System.out.println("Error Producto->constructor");}
    }
    public void NewProveedor(String dni, String nombre, String telefono, String email, String direccion){
        try {
            String codigo_sql="INSERT into "+bd.nombre+"."+nombre_bd+" VALUES(?,?,?,?,?)";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni.toUpperCase());
            pst.setString(2, nombre);
            pst.setString(3, telefono);
            pst.setString(4, email);
            pst.setString(5, direccion);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e){System.out.println("Error Proveedor->NewProducto");}
    }
    
    public String getNombre(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Nombre");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->getNombre"+e);}
        return txt;
    }
    public void setNombre(String dni, String new_nombre){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Nombre='"+new_nombre+"' WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setNombre");}
    }
    public String getTelefono(String dni){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Telefono");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->getTelefono"+e);}
        return txt;
    }
    public void setTelefono(String dni, String telefono){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Telefono='"+telefono+"' WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setTelefono");}
    }
    public String getEmail(String dni){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Email");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->getEmail");}
        return txt;
    }
    public void setEmail(String dni, String email){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Email='"+email+"' WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setEmail");}
    }
    public String getDireccion(String dni){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Direccion");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->getDireccion");}
        return txt;
    }
    public void setDireccion(String dni, String dir){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Direccion='"+dir+"' WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setDireccion");}
    }
    public ResultSet getProveedor(){
        try {
            String codigo_sql="select *from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            return rs;
        } catch (SQLException e) {System.out.println("Error Proveedor->getProveedor");}
        return null;
    }
    public void Editar(String dni, String nombre, String telefono, String email, String direccion){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Nombre='"+nombre+"', Telefono='"+telefono+"',"
            + "Email='"+email+"', Direccion='"+direccion+"' WHERE DNI_proveedor=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->Editar");}
    } 
    public boolean ExisteDNI(String dni){
        boolean bandera=false; dni=dni.trim();
        try {
            String codigo_sql="select DNI_proveedor from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                if(dni.equals(rs.getString("DNI_proveedor"))){
                    bandera=true;
                    break;
                }
            }
            pst.close(); cn.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->ExisteDNI "+e);}
        return bandera;
    }
    public void EliminarProveedor(String dni){
        try {
            String codigo_sql="DELETE FROM "+nombre_bd+" WHERE DNI_proveedor='"+dni+"'";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->EliminarProveedor");}
    }
}
