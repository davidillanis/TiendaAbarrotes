package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
    private BaseDatos bd=new BaseDatos();
    private Proveedor proveedor=new Proveedor();
    protected String nombre_bd="producto";

    public Producto() {
        try{
            Connection cn=bd.Conexion();
            String codigo_sql="CREATE TABLE IF NOT EXISTS "+bd.nombre+"."+nombre_bd+"(\n" +
                            "   Codigo_producto varchar(10) not null,\n" +
                            "   Marca varchar(30) not null,"+
                            "   Nombre varchar(30) not null,\n" +
                            "   Stock int not null,\n" +
                            "   Almacenado varchar(30) not null,\n" +
                            "   Precio_compra double not null,\n" +
                            "   Precio_venta double not null,\n" +
                            "   DNI_proveedor varchar(8) not null,\n"+
                            "   PRIMARY KEY(Codigo_producto),\n" +
                    "   FOREIGN KEY(DNI_proveedor) REFERENCES "+bd.nombre+"."+proveedor.nombre_bd+"(DNI_proveedor) ON DELETE CASCADE\n"+
                            ");";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        }catch(SQLException e){System.out.println("Error Producto->constructor");}
    }
    
    public void NewProducto(String codigo, String marca, String nombre,int stock,String almacenado,double precio_compra,double precio_venta, String dni_proveedor){
        try {
            String codigo_sql="INSERT into "+bd.nombre+"."+nombre_bd+" VALUES(?,?,?,?,?,?,?,?)";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo.toUpperCase());
            pst.setString(2, marca);
            pst.setString(3, nombre);
            pst.setInt   (4, stock);
            pst.setString(5, almacenado);
            pst.setDouble(6, precio_compra);
            pst.setDouble(7, precio_venta);
            pst.setString(8, dni_proveedor);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e){System.out.println("Error Producto->NewProducto");}
    }
    
    public String getMarca(String codigo) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Marca");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getNombre"+e);}
        return txt;
    }
    public void setMarca(String codigo, String marca){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Marca='"+marca+"' WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setMarca");}
    }
    public String getNombre(String codigo) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Nombre");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getNombre"+e);}
        return txt;
    }
    public void setNombre(String codigo, String new_nombre){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Nombre='"+new_nombre+"' WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setNombre");}
    }
    public Integer getStock(String codigo) {
        Integer txt=null;
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getInt("Stock");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getStock"+e);}
        return txt;
    }
    public void setStock(String codigo, int stock) {
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Stock="+stock+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setNombre");}
    }
    public String getAlmacenado(String codigo){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Almacenado");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getAlmacenado"+e);}
        return txt;
    }
    public void setAlmacenado(String codigo, String almacendo){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Almacenado='"+almacendo+"' WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setAlmacenado");}
    }
    public double getPrecio_compra(String codigo) {
        Double txt=null;
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getDouble("Precio_compra");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->Precio_compra"+e);}
        return txt;
    }
    public void setPrecio_compra(String codigo, double precio_compra){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Precio_compra="+precio_compra+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setPrecio_compra");}
    }
    public double getPrecio_venta(String codigo) {
        Double txt=null;
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getDouble("Precio_venta");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getPrecio_venta"+e);}
        return txt;
    }
    public void setPrecio_venta(String codigo, double precio_venta) {
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Precio_venta="+precio_venta+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->setPrecio_venta");}
    }
    public ResultSet getProducto(){
        try {
            String codigo_sql="select *from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            return rs;
        } catch (SQLException e) {System.out.println("Error Producto->getProducto"+e);}
        return null;
    }
    public String getDniProvedor(String codigo){
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("DNI_proveedor");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->getAlmacenado"+e);}
        return txt;
    }
    public void Editar(String codigo,String marca,String nombre,int stock,String almacenado,double precio_compra,double precio_venta, String DNI_proveedor){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Marca='"+marca+"',Nombre='"+nombre+"', Stock="+stock+", Almacenado='"
+almacenado+"', Precio_compra="+precio_compra+", Precio_venta="+precio_venta+", DNI_proveedor='"+DNI_proveedor+"'  WHERE Codigo_producto=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Producto->Editar"+e);}
    }
    public boolean ExisteCodigo(String codigo){
        boolean bandera=false; codigo=codigo.trim();
        try {
            String codigo_sql="select Codigo_producto from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                if(codigo.equals(rs.getString("Codigo_producto"))){
                    bandera=true;
                    break;
                }
            }
            pst.close(); cn.close(); rs.close();
        } catch (SQLException e) {System.out.println("Error Producto->ExisteCodigo");}
        return bandera;
    }
    public void EliminarProducto(String codigo){
        try {
            String codigo_sql="DELETE FROM "+nombre_bd+" WHERE Codigo_producto='"+codigo+"'";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        } catch (SQLException e) {System.out.println("Error Producto->EliminarProducto");}
    }
}
