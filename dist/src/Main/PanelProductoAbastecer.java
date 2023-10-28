package Main;

import BaseDatos.Producto;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PanelProductoAbastecer extends JPanel implements KeyListener, ActionListener, MouseListener{
    private JLabel label_titulo, label_txt;
    private int base;
    private JTable tabla_productos;
    private JTextField field_codigo, field_cantidad, field_seleciono;
    private JButton btn_aumentar;
    private Producto producto=new Producto();
    
    public PanelProductoAbastecer(int base) {
        setLayout(null);
        setBackground(new Color(212, 218, 255));
        this.base=base;
        
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JLabelIniciar();
        JTableIniciar();
        JTextFieldIniciar();
        JButtonIniciar();
    }
    private void JLabelIniciar(){
        label_titulo=new JLabel("Avastecer Productos");
        label_titulo.setBounds(0,10,base,30);
        label_titulo.setForeground(Color.red);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setFont(new Font("Cooper Black",0,26));
        add(label_titulo);
        
        label_txt=new JLabel("Codigo del producto(ID)");
        label_txt.setBounds(50*2, 50*8, 250, 30);
        label_txt.setFont(new Font("Rockwell",0,20));
        label_txt.setForeground(Color.RED);
        add(label_txt);
        
        label_txt=new JLabel("Cantidad a aumentar");
        label_txt.setBounds(50*2, 50*10, 250, 30);
        label_txt.setFont(new Font("Rockwell",0,20));
        label_txt.setForeground(Color.RED);
        add(label_txt);
    }
    private void JTableIniciar(){
        DefaultTableModel model=new DefaultTableModel();
        String []co={"codigo","Marca","nombre","stock","almacenado","precio compra","precio venta", "DNI"};
        for(String c:co){model.addColumn(c);}
        
        tabla_productos=new JTable(model);
        tabla_productos.setBounds(0, 50, base, 300);
        tabla_productos.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_productos.setDefaultEditor(Object.class, null);//no se pueda editar el txt de las celdas
        tabla_productos.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_productos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_productos.setBackground(new Color(74,209,140));
        tabla_productos.setRowHeight(20);
        tabla_productos.setFont(new Font("Baskerville Old Face",0,18));
        tabla_productos.addMouseListener(this);
        add(tabla_productos);
        
        JScrollPane scroll=new JScrollPane(tabla_productos);
        scroll.setBounds(0, 50, base, 350);
        add(scroll);
        ActualizarTablaProducto();
    }
    private void JTextFieldIniciar(){
        field_codigo=new JTextField();
        field_codigo.setBounds(50*2, 30+50*8, 150, 30);
        field_codigo.setFont(new Font("Rockwell",0,18));
        field_codigo.setForeground(Color.BLUE);
        field_codigo.addKeyListener(this);
        field_codigo.setBorder(null);
        add(field_codigo);
        
        field_cantidad=new JTextField();
        field_cantidad.setBounds(50*2, 30+50*10, 150, 30);
        field_cantidad.setFont(new Font("Rockwell",0,18));
        field_cantidad.setForeground(Color.BLUE);
        field_cantidad.setBorder(null);
        add(field_cantidad);
        
        field_seleciono=new JTextField("Seleccionaste a..");
        field_seleciono.setBounds(50*8, 30+50*8, 200, 30);
        field_seleciono.setFont(new Font("Rockwell",0,18));
        field_seleciono.setEnabled(false);
        field_seleciono.setForeground(Color.BLUE);
        field_seleciono.setBorder(null);
        add(field_seleciono);
    }
    private void JButtonIniciar(){
        btn_aumentar=new JButton("Aumentar");
        btn_aumentar.setBounds(50*6,30+50*10,120,30);
        btn_aumentar.setBackground(Color.BLUE);
        btn_aumentar.setForeground(Color.WHITE);
        btn_aumentar.setFont(new Font("Arial",1,18));
        btn_aumentar.addActionListener(this);
        add(btn_aumentar);
    }
    
    @Override public void keyTyped(KeyEvent e){} @Override public void keyPressed(KeyEvent e){}
    @Override public void keyReleased(KeyEvent e){
        if(e.getSource()==field_codigo){
            String codigo=field_codigo.getText().trim();
            if(producto.ExisteCodigo(codigo)){
                for(int i=0; i<tabla_productos.getRowCount(); i++){
                    if(codigo.equals(tabla_productos.getValueAt(i, 0))){
                        tabla_productos.setRowSelectionInterval(i, i);//selecionar una fila
                        field_seleciono.setText(producto.getNombre(codigo));
                        break;
                    }
                }
            }else if(!field_seleciono.getText().equals("Seleccionaste a..")){
                field_seleciono.setText("Seleccionaste a..");
                tabla_productos.clearSelection();
            }
        }
    }

    @Override public void actionPerformed(ActionEvent e){
        if(e.getSource()==btn_aumentar){
            BotonAumentar();
        }
    }
    private void ActualizarTablaProducto(){
        TableModel model=tabla_productos.getModel();
        ((DefaultTableModel)model).setRowCount(0);
        
        DefaultTableModel model2=(DefaultTableModel)tabla_productos.getModel();
        String codigo,marca, nombre, almacenado, dni_prov;
        int stock;
        double precio_compra, precio_venta;
        try {
            ResultSet rs=producto.getProducto();
            while(rs.next()){
                codigo=rs.getString(1);
                marca=rs.getString(2);
                nombre=rs.getString(3);
                stock=rs.getInt(4);
                almacenado=rs.getString(5);
                precio_compra=rs.getDouble(6);
                precio_venta=rs.getDouble(7);
                dni_prov=rs.getString(8);
                model2.addRow(new Object[]{codigo,marca,nombre,stock,almacenado,precio_compra,precio_venta,dni_prov});
            }
        } catch (SQLException e) {System.out.println("Error PanelProveedor->ActualizarTablaProveedor");}
        tabla_productos.revalidate();
    }

    @Override public void mousePressed(MouseEvent e) {} @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e){} @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e){
        if(e.getSource()==tabla_productos){
            String codigo=tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 0).toString();
            String nombre=tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 2).toString();
            field_codigo.setText(codigo);
            field_seleciono.setText(nombre);
        }
    }
    private void BotonAumentar(){
        try {
            String codigo=field_codigo.getText().trim();
            int cant=Integer.parseInt(field_cantidad.getText().trim());
            if(producto.ExisteCodigo(codigo)){
                producto.setStock(codigo, producto.getStock(codigo)+cant);
                ActualizarTablaProducto();
                JOptionPane.showMessageDialog(null, "Operacion Exitosa¡¡¡");
            }else{JOptionPane.showMessageDialog(null, "Codigo no valido");}
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "Llene correctamente los cuadros");}
    }
}
