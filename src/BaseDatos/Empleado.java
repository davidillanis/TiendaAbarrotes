package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado {
    //nombre, dni, telefono, email, dir
    private BaseDatos bd=new BaseDatos();
    public String nombre_bd="Empleado";

    public Empleado() {
        try{
            Connection cn=bd.Conexion();
            String codigo_sql="CREATE TABLE IF NOT EXISTS "+bd.nombre+"."+nombre_bd+"(\n" +
                            "   DNI_empleado varchar(8) not null,\n" +
                            "   user_name varchar(10) not null,\n" +
                            "   pass_word varchar(30) not null,\n" +
                            "   Estado varchar(10) not null,\n" +
                            "   Nivel varchar(15) not null,\n" +
                            "   Nombre varchar(30) not null,\n" +
                            "   Telefono varchar(9) not null,\n" +
                            "   Email varchar(30) not null,\n" +
                            "   Salario Double not null,\n" +
                            "   PRIMARY KEY(DNI_empleado)\n" +
                            ");";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();cn.close();
            if(!ExisteDNI("1111")){
                NewUsuario("1111","root","1234","Activo","Dueño","","","",0);
            }
        }catch(SQLException e){System.out.println("Error Usuarios->constructor");}
    }
    public void NewUsuario(String dni,String user_name,String pass_word,String estado,String nivel, String nombre,String telefono,String email, double salrio){
        try {
            String codigo_sql="INSERT into "+bd.nombre+"."+nombre_bd+" VALUES(?,?,?,?,?,?,?,?,?)";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni.toUpperCase());
            pst.setString(2, user_name);
            pst.setString(3, pass_word);
            pst.setString(4, estado);
            pst.setString(5, nivel);
            pst.setString(6, nombre);
            pst.setString(7, telefono);
            pst.setString(8, email);
            pst.setDouble(9, salrio);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e){System.out.println("Error Usuarios->NewUsuario "+e);}
    }

    public String getUserName(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("user_name");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->getUserName"+e);}
        return txt;
    }
    public void setUserName(String dni, String new_nombre){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set user_name='"+new_nombre+"' WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->setUserName");}
    }    
    public String getPassWord(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("pass_word");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->getPassWord"+e);}
        return txt;
    }
    public void setPassWord(String dni, String pass_word){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set pass_word='"+pass_word+"' WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->setPassWord");}
    }
    public String getEstado(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Estado");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->getEstado"+e);}
        return txt;
    }
    public void setEstado(String dni, String estado){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Estado='"+estado+"' WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->setEstado");}
    }
    public String getNivel(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString("Nivel");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->getNivel"+e);}
        return txt;
    }
    public void setNivel(String dni, String nivel){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Nivel='"+nivel+"' WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->setNivel");}
    }
    public String getNombre(String dni) {
        String txt="";
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
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
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Nombre='"+new_nombre+"' WHERE DNI_empleado=?";
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
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
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
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Telefono='"+telefono+"' WHERE DNI_empleado=?";
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
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
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
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Email='"+email+"' WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Proveedor->setEmail");}
    }
    public Double getSalario(String dni){
        Double dato=null;
        try {
            String codigo_sql="SELECT *FROM "+bd.nombre+"."+nombre_bd+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next()){dato=rs.getDouble("Salario");}
            rs.close();pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuario->getSalario");}
        return dato;
    }
    public void setSalario(String dni, double salario){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set Salario="+salario+" WHERE DNI_empleado=?";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, dni);
            pst.execute();
            pst.close();cn.close();
        }catch(SQLException e){System.out.println("Error Usuarios->setSalario");}
    }
    public ResultSet getUsuario(){
        try {
            String codigo_sql="select *from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            return rs;
        } catch (SQLException e) {System.out.println("Error Usarios->getUsuario"+e);}
        return null;
    }
    public void Editar(String dni,String user_name,String pass_word,String estado,String nivel,String nombre,String telefono,String email,double salario){
        try {
            String codigo_sql="UPDATE "+bd.nombre+"."+nombre_bd+" set user_name='"+user_name+"', "
                    + "pass_word='"+pass_word+"', Estado='"+estado+"', Nivel='"+nivel+"', Nombre='"+nombre
                    +"', Telefono='"+telefono+"',"+ "Email='"+email+"', Salario="+salario+" WHERE DNI_empleado=?";
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
            String codigo_sql="select DNI_empleado from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                if(dni.equals(rs.getString("DNI_empleado"))){
                    bandera=true;
                    break;
                }
            }
            pst.close(); cn.close(); rs.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->ExisteDNI");}
        return bandera;
    }
    public boolean ExisteUsuario (String user){
        boolean bandera=false; user=user.trim();
        try {
            String codigo_sql="select user_name from "+nombre_bd;
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                if(user.equals(rs.getString("user_name"))){
                    bandera=true;
                    break;
                }
            }
            pst.close(); cn.close(); rs.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->ExisteUsuario");}
        return bandera;
    }
    public void EliminarUsuario(String dni){
        try {
            String codigo_sql="DELETE FROM "+nombre_bd+" WHERE DNI_empleado='"+dni+"'";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            pst.close(); cn.close();
        } catch (SQLException e) {System.out.println("Error Proveedor->EliminarProveedor");}
    }
    public String getDNI(String user){
        String dni="";
        try {
            String codigo_sql="select *from "+bd.nombre+"."+nombre_bd+" where user_name='"+user+"'";
            Connection cn=bd.Conexion();
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                dni=rs.getString("DNI_empleado");
            }
            pst.close();cn.close();
        } catch (Exception e) { }
        return dni;
    }
}
