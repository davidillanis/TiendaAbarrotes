package Main;

import BaseDatos.Imagen;
import java.awt.Color;
import BaseDatos.Producto;
import JButtonStyles.ModernButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/*@author Abel123*/
public class ProductoInfoVer extends Padre{
    private final String codigo_producto;
    private final Producto produto;
    private Imagen imagen;
    private JScrollPane scroll;
    private JToolBar tool_producto;
    
    public ProductoInfoVer(String codigo_producto) {
        super(800,600);
        this.codigo_producto=codigo_producto;
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/Images/icon.png").getImage());
        panel_prinsipal.setLayout(new BorderLayout());
        produto=new Producto();
        imagen=new Imagen();
        JCrollPaneIniciar();
        JToolBarIniciar();
        JLabelIniciar();
        JButtonIniciar();
        JTextFieldIniciar();
    }
    private void JCrollPaneIniciar(){
        scroll=new JScrollPane();
        panel_prinsipal.add(scroll,BorderLayout.CENTER);
    }
    private void JToolBarIniciar(){
        tool_producto=new JToolBar();
        tool_producto.setBackground(new Color(60,78,120));
        tool_producto.setOrientation(SwingConstants.VERTICAL);
        panel_prinsipal.add(tool_producto,BorderLayout.WEST);
    }
    private void JLabelIniciar(){
        try{
        tool_producto.add(MiLabel(produto.getNombre(codigo_producto)+"-"+codigo_producto, Color.WHITE, new Font("Rockwell",0,18)));

        ImageIcon icon=imagen.getImage(imagen.getCodigoImagen(codigo_producto));
        if(icon!=null){icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));}
        tool_producto.add(new JLabel(icon));
        
        tool_producto.add(MiLabel("Marca:    "+produto.getMarca(codigo_producto), Color.WHITE, new Font("Rockwell",1,14)));
        tool_producto.add(MiLabel("Vendido en:"+produto.getAlmacenado(codigo_producto), Color.WHITE, new Font("Rockwell",1,14)));
        tool_producto.add(MiLabel("precio  s/"+produto.getPrecio_venta(codigo_producto), Color.WHITE, new Font("Rockwell",1,14)));
        }catch(Exception e){}
    }
    private void JButtonIniciar(){
        ModernButton btn=new ModernButton("Estadistica",new Font("Rockwell",0,18));
        //btn.setButtonSong(ButtonSong.song.DISCORD_1);
        tool_producto.add(btn);
    }
    private void JTextFieldIniciar(){
        tool_producto.add(new JTextFieldStyles.BorderTitleTexField(1, Color.BLUE, "xd"));
    }
    private JLabel MiLabel(String txt, Color c, Font f){
        JLabel label=new JLabel(txt);
        label.setForeground(c);
        label.setFont(f);
        return label;
    }    
    
    public static void main(String[] args) {
        new ProductoInfoVer("").setVisible(true);
    }
}
