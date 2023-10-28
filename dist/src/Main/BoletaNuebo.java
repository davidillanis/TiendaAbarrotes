package Main;

import BaseDatos.Boleta;
import BaseDatos.Producto;
import BaseDatos.Empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BoletaNuebo extends Padre implements ActionListener{
    private JTextField field_nombre, field_total, field_codigo;
    private JTable tabla_produc;
    private JLabel label_txt, label_image;
    private JButton btn_genera_boleta;
    private final int b, a;
    private final PanelVenta venta;
    private final Empleado usuario=new Empleado();
    private final Boleta boleta=new Boleta();
    private final Producto producto=new Producto();
            
    public BoletaNuebo(PanelVenta venta) {
        super(600,750, new Color(148, 255, 195));
        b=this.getWidth();
        a=this.getHeight();
        this.venta=venta;
        this.setIconImage(new ImageIcon("src/Images/icono.png").getImage());
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JLabelIniciar();
        JTableIniciar();
        JTextFieldIniciar();
        JButtonIniciar();
    }
    private void JLabelIniciar(){
        super.setFontLetras("Cooper Black", 0, 20);
        super.JLabelTitulo("Boleta");
        label_txt=new JLabel("_______________________________");
        label_txt.setBounds(50*3,(50*4)+5,430,30);
        label_txt.setFont(new Font("Arial Balck",1,24));
        label_txt.setForeground(Color.GRAY);
        panel_prinsipal.add(label_txt);
        
        label_txt=new JLabel("Nombre:");
        label_txt.setBounds(50*1,(50*4)+5,150,30);
        label_txt.setFont(new Font("Rockwell",1,20));
        label_txt.setForeground(Color.BLUE);
        panel_prinsipal.add(label_txt);
        
        label_txt=new JLabel("R.U.C. 20602247458");
        label_txt.setBounds(50*7,50*1,250,30);
        label_txt.setFont(new Font("Rockwell",1,20));
        label_txt.setForeground(Color.BLUE);
        panel_prinsipal.add(label_txt);
        
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("EE '/' MM '/' yy",new Locale("es"));
        LocalDate fecha = LocalDate.now();
        label_txt=new JLabel(fecha.format(formato));
        label_txt.setBounds(50*7,50*2,250,30);
        label_txt.setFont(new Font("Rockwell",1,20));
        label_txt.setForeground(Color.BLUE);
        panel_prinsipal.add(label_txt);
        
        ImageIcon icon=new ImageIcon("src/Images/logo.png");
        label_image=new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH)));
        label_image.setBounds(50,0,200,200);
        label_image.setFont(new Font("Rockwell",1,20));
        label_image.setForeground(Color.BLUE);
        panel_prinsipal.add(label_image);
    }
    private void JTableIniciar(){
        tabla_produc=new JTable(venta.ModelTabla());
        tabla_produc.setForeground(Color.BLUE);
        tabla_produc.setRowHeight(20);
        tabla_produc.setFont(new Font("Times new Roman",0,18));
        tabla_produc.setDefaultEditor(Object.class, null);//celdas no editables
        tabla_produc.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        panel_prinsipal.add(tabla_produc);
        
        JScrollPane scroll=new JScrollPane(tabla_produc);
        scroll.setBounds(5,(a/2)-120,b-25,a/2);
        panel_prinsipal.add(scroll);
    }
    private void JTextFieldIniciar(){
        field_nombre=new JTextField();
        field_nombre.setBounds(50*3, 50*4, 400, 30);
        field_nombre.setFont(new Font("Book Antiqua",0,20));
        field_nombre.setForeground(Color.BLUE);
        field_nombre.setBackground(super.color_fondo);
        field_nombre.setBorder(null);
        panel_prinsipal.add(field_nombre);
        
        field_total=new JTextField(Double.toString(PrecioTotal()));
        field_total.setBounds(50*9, (50*13)-20, 130, 30);
        field_total.setFont(new Font("Book Antiqua",0,20));
        field_total.setForeground(Color.BLUE);
        field_total.setBorder(null);
        panel_prinsipal.add(field_total);
        
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy",new Locale("es"));
        LocalDate fecha = LocalDate.now();
        field_codigo=new JTextField(fecha.format(formato)+"-"+Aleatorio());
        field_codigo.setBounds(50*7, 5, 120, 30);
        field_codigo.setFont(new Font("Book Antiqua",0,20));
        field_codigo.setForeground(Color.BLUE);
        field_codigo.setBackground(super.color_fondo);
        //field_codigo.setBorder(null);
        panel_prinsipal.add(field_codigo);
    }
    private void JButtonIniciar(){
        btn_genera_boleta=new JButton("Nueba Boleta");
        btn_genera_boleta.setBounds(50*4, 50*13, 180, 30);
        btn_genera_boleta.addActionListener(this);
        btn_genera_boleta.setFont(new Font("Rockwell",0,18));
        btn_genera_boleta.setForeground(Color.blue);
        btn_genera_boleta.setBorder(null);
        panel_prinsipal.add(btn_genera_boleta);
    }
    private double PrecioTotal(){
        double precio_total=0;
        for(int i=0; i<tabla_produc.getRowCount(); i++){
            precio_total+=Double.parseDouble(tabla_produc.getValueAt(i, 4).toString());
        }
        BigDecimal number = new BigDecimal(Double.toString(precio_total));
        BigDecimal roundedNumber = number.setScale(2, RoundingMode.HALF_UP);
        
        return Double.parseDouble(roundedNumber.toString());
    }

    @Override public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_genera_boleta){
            BotonGenerarBoleta();
        }
    }
    private void BotonGenerarBoleta(){
        String codigo_boleta, nombre_cliente, dni_usuario;
        
        codigo_boleta=field_codigo.getText().trim();
        nombre_cliente=field_nombre.getText().trim();
        dni_usuario=usuario.getDNI(Login.user);
        if(!codigo_boleta.equals("")){
            if(!boleta.ExisteCodigoBoleta(codigo_boleta)){
                boleta.NewBoleta(codigo_boleta, nombre_cliente, dni_usuario);
                BoletaGenerarInformacion();
                venta.ActualizarTablaProducto();
                venta.EliminarTablaResivo();
                dispose();
                JOptionPane.showMessageDialog(null, "¡¡¡Registro Exitoso!!!");
            }else{JOptionPane.showMessageDialog(null, "El Codigo de Boleta ya existe");}
        }else{JOptionPane.showMessageDialog(null, "Llene correctamete los campos");}
    }
    private void BoletaGenerarInformacion(){
        String codigo_boleta, codigo_producto;
        int cantidad;
        codigo_boleta=field_codigo.getText().trim();
        
        for(int i=0; i<tabla_produc.getRowCount(); i++){
            cantidad=Integer.parseInt(tabla_produc.getValueAt(i, 1).toString());
            codigo_producto=tabla_produc.getValueAt(i, 0).toString();
            boleta.NewBoletaInformacion(codigo_boleta, cantidad, codigo_producto);
            producto.setStock(codigo_producto, producto.getStock(codigo_producto)-cantidad);
        }
    }
    private String Aleatorio(){
        String txt="";
        for(int i=0; i<4; i++){
            if((int)(Math.random()*20)%2==0){
                txt+=(int)(Math.random()*10);
            }else{
                txt+=(char)((int)(Math.random()*25+65));
            }
        }
        return txt.toUpperCase();
    }
}
