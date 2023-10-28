
package BaseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/*@author Abel123*/
public class Imagen {
    private final BaseDatos bd=new BaseDatos();
    private final Producto producto=new Producto();
    private String nombre="imagen";
    private final File carpeta;
    
    public Imagen() {
        try {
            Connection cn=bd.Conexion();
            String codigo_sql="create table if not exists "+bd.nombre+"."+nombre+"("
                    + " codigo_img varchar(10) not null,"
                    + " codigo_producto varchar(10) not null,"
                    + " imagen LONGBLOB,"
                    + " primary key(codigo_img),"
                    + " FOREIGN KEY(codigo_producto) REFERENCES "+bd.nombre+"."+producto.nombre_bd+"(Codigo_producto) ON DELETE CASCADE\n"
                    + ");";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            cn.close(); pst.close();
        } catch (SQLException e) {System.out.println("Error Imagen->constructor "+e);}
        finally{
            carpeta=new File("src/Images/ImgProd/");
            if(!carpeta.exists()){carpeta.mkdir();}
        }
    }
    public void NewImage(String codigo_img, String cod_producto, File img){
        try {
            byte[] data_img=null;
            if(img!=null){
                data_img=BytesImage(img);
            }
            Connection cn=bd.Conexion();
            String codigo_sql="insert into "+bd.nombre+"."+nombre+" values(?,?,?)";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setString(1, codigo_img);
            pst.setString(2,cod_producto);
            pst.setBytes(3, data_img);
            pst.execute();
            pst.close();cn.close();
        } catch (SQLException e) {System.out.println("Error Imagen->NewImage "+e);}
    }
    public ImageIcon getImage(String codigo_img){
        File file=new File(carpeta.getPath()+"/"+codigo_img+".png");
        if(!file.exists()){
            try {
                byte[] data_img = null;
                Connection cn=bd.Conexion();
                String codigo_sql="select *from "+bd.nombre+"."+nombre+" where codigo_img='"+codigo_img+"'";
                PreparedStatement pst=cn.prepareStatement(codigo_sql);
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    data_img=rs.getBytes(3);
                }
                if(data_img==null){return null;}
                FileOutputStream salidia=new FileOutputStream(file,false);
                salidia.write(data_img);
                salidia.close(); pst.close(); cn.close();
            } catch (IOException | SQLException e) {System.out.println("Error Imagen->getImage "+e);}
        }
        return new ImageIcon(file.getPath());
    }
    public void setImage(String codigo_img, File img){
        try {
            new File(carpeta.getPath()+"/"+codigo_img+".png").delete();
            byte[] data_img=null;
            if(img!=null){data_img=BytesImage(img);}
            Connection cn=bd.Conexion();
            String codigo_sql="update "+bd.nombre+"."+nombre+" set imagen=? where codigo_img='"+codigo_img+"'";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.setBytes(1, data_img);
            pst.execute();
            pst.close();cn.close();
        } catch ( SQLException e) {System.out.println("Error Imagen->setImage"+e);}
    }
    public String getCodigoProducto(String codigo_img){
        String txt="";
        try {
            Connection cn=bd.Conexion();
            String codigo_sql="select *from "+bd.nombre+"."+nombre+" where codigo_img='"+codigo_img+"'";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString(2);}
            rs.close(); pst.close(); cn.close();
            return txt;
        } catch (SQLException e) {System.out.println("Error Imagen->getCodigoProducto"+e);}
        return txt;
    }
    public String getCodigoImagen(String codigo_producto){
        String txt="";
        try {
            Connection cn=bd.Conexion();
            String codigo_sql="select *from "+bd.nombre+"."+nombre+" where codigo_producto='"+codigo_producto+"'";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){txt=rs.getString(1);}
            rs.close(); pst.close(); cn.close();
            return txt;
        } catch (SQLException e) {System.out.println("Error Imagen->getCodigoProducto"+e);}
        return txt;
    }
    public void EliminarImagen(String codigo_img){
        try {
            System.out.println(codigo_img);
            File file=new File(carpeta.getPath()+"/"+codigo_img+".png");
            Connection cn=bd.Conexion();
            String codigo_sql="delete from "+bd.nombre+"."+nombre+" where codigo_img='"+codigo_img+"'";
            PreparedStatement pst=cn.prepareStatement(codigo_sql);
            pst.execute();
            if(file.exists()){file.delete();}
            cn.close(); pst.close();
        } catch (SQLException e) {System.out.println("Error Imagen->EliminarImagen"+e);}
    }
    
    private byte[] BytesImage(File img){
         byte[] data_img = null;
        try{
            FileInputStream entrada=new FileInputStream(img);
            data_img=new byte[entrada.available()];
            entrada.read(data_img);
            entrada.close();
        }catch(IOException e){System.out.println("Error Imagen->BytesImage"+e);}
        return data_img;
    } 
    public static void main(String[] args) throws IOException{
        Imagen img=new Imagen();
        //img.NewImage("12345", "ABX16", new File("src/Images/editar.png"));
        //System.out.println(img.getImage("SK589"));
        img.EliminarImagen("U7K1S");
    }
}
