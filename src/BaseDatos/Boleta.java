package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Boleta {
    private BaseDatos bd=new BaseDatos();
    private Empleado usuario=new Empleado();
    private Producto producto=new Producto();
    public String nombre_bd="boleta", name_bd_inf="boleta_informacion";
    
    public Boleta() {
        try{
            Connection cn=bd.Conexion();
            String codigo_sql="CREATE TABLE IF NOT EXISTS "+bd.nombre+"."+nombre_bd+"(\n" +
                            "   Codigo_boleta varchar(10) not null,\n" +
                            "   Fecha_venta Date not null,\n" +
                            "   nombre_cliente varchar(30) not null,\n" +
                            "   DNI_empleado varchar(8) not null,\n" +
                            "   PRIMARY KEY(Codigo_boleta),\n" +
                    "FOREIGN KEY(DNI_empleado) REFERENCES "+bd.nombre+"."+usuario.nombre_bd+"(DNI_empleado)\n"+
                            ");";
            //System.out.println(codigo_sql);
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
            BoletaInformacion();
        }catch(SQLException e){System.out.println("Error Boleta->constructor");}
    }
    public void BoletaInformacion() {
        try{
            Connection cn=bd.Conexion();
            String codigo_sql="CREATE TABLE IF NOT EXISTS "+bd.nombre+"."+name_bd_inf+"(\n" +
                            "   Codigo_boleta varchar(10) not null,\n" +
                            "   Cantidad int not null,\n" +
                            "   Codigo_producto varchar(30) not null,\n" +
                    "FOREIGN KEY(Codigo_boleta) REFERENCES "+bd.nombre+"."+nombre_bd+"(Codigo_boleta),\n"+
                    "FOREIGN KEY(Codigo_producto) REFERENCES "+bd.nombre+"."+producto.nombre_bd+"(Codigo_producto)\n"+
                            ");";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        }catch(SQLException e){System.out.println("Error Boleta->BoletaInformacion");}
    }
    
    public void NewBoleta(String codigo, String nombre_cliente, String dni_vendedor){
        try {
            String codigo_sql="INSERT into "+bd.nombre+"."+nombre_bd+" VALUES(?,?,?,?)";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            pst.setString(3, nombre_cliente);
            pst.setString(4, dni_vendedor);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e){System.out.println("Error Proveedor->NewBoleta"+e);}
    }
    public void NewBoletaInformacion(String codigo_boleta, int cantidad, String codigo_producto){
        try {
            String codigo_sql="INSERT into "+bd.nombre+"."+name_bd_inf+" VALUES(?,?,?)";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo_boleta);
            pst.setInt(2, cantidad);
            pst.setString(3, codigo_producto);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e){System.out.println("Error Boleta->NewBoletaInformacion"+e);}
    }
    
    public String getFechaVenta(String codigo) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_boleta=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Fecha_venta");}
            pst.close();cn.close(); rs.close();
        }catch(SQLException e){System.out.println("Error Boleta->getFechaVenta"+e);}
        return txt;
    }
    public String getNombre(String codigo) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_boleta=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("nombre_cliente");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Boleta->getNombre"+e);}
        return txt;
    }
    public void setNombre(String codigo, String new_nombre){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set nombre_cliente='"+new_nombre+"' WHERE Codigo_boleta=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setNombre"+e);}
    }
    public String getDNIEmpleado(String codigo){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_boleta=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("DNI_empleado");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Boleta->getDNIUsuario"+e);}
        return txt;
    }
    
    public ResultSet getBoletaInformacion(String codigo){
        try {
            String codigo_sql="select *from "+name_bd_inf+" where Codigo_boleta='"+codigo+"'";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            return rs;
        } catch (SQLException e) {System.out.println("Error Boleta->getBoletaInformacion");}
        return null;
    }
    public ResultSet getBoleta(){
        try {
            String codigo_sql="select *from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            return rs;
        } catch (SQLException e) {System.out.println("Error Boleta->getBoleta");}
        return null;
    }
    
    public boolean ExisteCodigoBoleta(String codigo){
        boolean bandera=false; 
        try {
            String codigo_sql="select Codigo_boleta from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                if(codigo.equals(rs.getString("Codigo_boleta"))){
                    bandera=true;
                    break;
                }
            }
            pst.close(); cn.close(); rs.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->ExisteDNI");}
        return bandera;
    }
}
