package Main;

import BaseDatos.Boleta;
import BaseDatos.Empleado;
import BaseDatos.Producto;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class BoletaVer extends Padre{
    private JTextField field_nombre, field_total, field_codigo;
    private JTable tabla_produc;
    private JLabel label_txt, label_image;
    private final Boleta boleta=new Boleta();
    private final Producto producto=new Producto();
    private final Empleado empleado=new Empleado();
    private final int b, a;
    private String codigo_boleta, dni_empleado;
            
    public BoletaVer(String codigo_boleta) {
        super(600,750, new Color(148, 255, 195));
        dni_empleado=boleta.getDNIEmpleado(codigo_boleta);
        this.setTitle("Boleta girado por "+empleado.getNombre(dni_empleado)+"-"+empleado.getNivel(dni_empleado));
        b=this.getWidth();
        a=this.getHeight();
        this.codigo_boleta=codigo_boleta;
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("src/Images/icono.png").getImage());
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JLabelIniciar();
        JTextFieldIniciar();
        JTableIniciar();
        
    }
    private void JLabelIniciar(){
        super.setFontLetras("Cooper Black", 0, 20);
        super.JLabelTitulo("Boleta:");
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
        
        label_txt=new JLabel(boleta.getFechaVenta(codigo_boleta));
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
        DefaultTableModel model=new DefaultTableModel();
        String []columnas={"Cod.pro","cant","Descripcion","p. UNIT","Importe"};
        for(String c:columnas){model.addColumn(c);}
        
        tabla_produc=new JTable(model);
        tabla_produc.setBackground(new Color(182,255,159));
        tabla_produc.setForeground(Color.BLUE);
        tabla_produc.setRowHeight(20);
        tabla_produc.setFont(new Font("Times new Roman",0,18));
        tabla_produc.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_produc.setDefaultEditor(Object.class, null);//no se pueda editar el txt de las celdas
        panel_prinsipal.add(tabla_produc);
        
        JScrollPane scroll=new JScrollPane(tabla_produc);
        scroll.setBounds(5,(a/2)-120,b-25,a/2);
        panel_prinsipal.add(scroll);
        LLenarTablaProduc();
    }
    private void JTextFieldIniciar(){
        field_nombre=new JTextField(boleta.getNombre(codigo_boleta));
        field_nombre.setBounds(50*3, 51*4, 400, 30);
        field_nombre.setFont(new Font("Book Antiqua",0,20));
        field_nombre.setForeground(Color.BLUE);
        field_nombre.setBackground(super.color_fondo);
        field_nombre.setOpaque(false);
        field_nombre.setEditable(false);
        field_nombre.setBorder(null);
        panel_prinsipal.add(field_nombre);
        
        field_total=new JTextField();
        field_total.setBounds(50*9, (50*13)-20, 130, 30);
        field_total.setFont(new Font("Book Antiqua",0,20));
        field_total.setForeground(Color.BLUE);
        field_total.setEditable(false);
        field_total.setBorder(null);
        panel_prinsipal.add(field_total);
        
        field_codigo=new JTextField(codigo_boleta);
        field_codigo.setBounds(50*7, 5, 120, 30);
        field_codigo.setFont(new Font("Rockwell",0,20));
        field_codigo.setForeground(Color.BLUE);
        field_codigo.setBackground(super.color_fondo);
        field_codigo.setEditable(false);
        field_codigo.setBorder(null);
        panel_prinsipal.add(field_codigo);
    }
    private void LLenarTablaProduc(){
        int cant;
        String descrip, codigo_pro;
        double precio_unita, importe, precio_final=0;
        TableModel E_model=tabla_produc.getModel();
        ((DefaultTableModel)E_model).setRowCount(0);
        
        DefaultTableModel model=(DefaultTableModel)tabla_produc.getModel();
        ResultSet rs=boleta.getBoletaInformacion(codigo_boleta);
        try {
            while(rs.next()){
                codigo_pro=rs.getString(3);
                cant=rs.getInt(2);
                descrip=producto.getNombre(codigo_pro);
                precio_unita=producto.getPrecio_venta(codigo_pro);
                importe=precio_unita*cant;
                precio_final+=importe;
                model.addRow(new Object[]{codigo_pro, cant, descrip, precio_unita, importe});
            }
        } catch (Exception e) {}
        
        BigDecimal number = new BigDecimal(Double.toString(precio_final));
        BigDecimal roundedNumber = number.setScale(2, RoundingMode.HALF_UP);
        field_total.setText(roundedNumber.toString());
    }
}
