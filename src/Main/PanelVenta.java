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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PanelVenta extends JPanel implements MouseListener, KeyListener, ActionListener{
    private final int base;
    private final int x=0, y=50, altura=350;//tabla
    private JLabel label_txt;
    private JTable tabla_productos, tabla_resivo;
    private JPanel panel_obc;
    private JTextField field_id, field_producto, field_precio, field_cantidad;
    private JButton boton_aniadir, boton_fin, boton_eliminar;
    private Producto producto=new Producto();
    
    public PanelVenta(int base) {
        setLayout(null);
        setBackground(new Color(184, 217, 255));
        this.base=base;
        
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JTableIniciar();
        JTextFieldIniciar();
        JButtonIniciar();
    }
    private void JLabelIniciar(){
        int sep=50, posx=20;
        label_txt=new JLabel("Productos Disponibles");
        label_txt.setFont(new Font("Rockwell",0,20));
        label_txt.setBounds(posx+20,20,300,30);
        label_txt.setForeground(Color.red);
        add(label_txt);
        
        label_txt=new JLabel("Codigo producto(ID)");
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setBounds(posx,20+sep*0,300,30);
        label_txt.setForeground(Color.red);
        panel_obc.add(label_txt);
        
        label_txt=new JLabel("Cantidad");
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setBounds(posx,20+sep*2,200,30);
        label_txt.setForeground(Color.red);
        panel_obc.add(label_txt);
        
        label_txt=new JLabel("Precio total");
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setBounds(posx*8,20+sep*2,200,30);
        label_txt.setForeground(Color.red);
        panel_obc.add(label_txt);
    }
    private void JTableIniciar(){
        DefaultTableModel model_productos=new DefaultTableModel();
        String []columnas={"Codigo","Marca","Nombre","stock","venta en","precio"};
        for(String c:columnas){model_productos.addColumn(c);}
        tabla_productos=new JTable(model_productos);
        tabla_productos.setFont(new Font("Baskerville Old Face",0,20));
        tabla_productos.setRowHeight(20);
        tabla_productos.setBackground(new Color(240,255,240));
        tabla_productos.setForeground(Color.BLUE);
        tabla_productos.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_productos.setDefaultEditor(Object.class, null);//celdas no editables
        tabla_productos.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_productos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_productos.addMouseListener(this);
        add(tabla_productos);
        JScrollPane scroll_productos=new JScrollPane(tabla_productos);
        scroll_productos.setBounds(x, y, base, altura);
        add(scroll_productos);
        
        
        DefaultTableModel model_resivo=new DefaultTableModel();
        String []object={"codigo","Nombre","Cantidad","Precio"};
        for(String e:object){
            model_resivo.addColumn(e);
        }
        tabla_resivo=new JTable(model_resivo);
        tabla_resivo.setFont(new Font("Baskerville Old Face",0,14));
        tabla_resivo.setRowHeight(16);
        tabla_resivo.setBackground(new Color(240,255,240));
        tabla_resivo.setDefaultEditor(Object.class, null);//celdas no editables
        tabla_resivo.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_resivo.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_resivo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_resivo.setForeground(Color.BLUE);
        panel_obc.add(tabla_resivo);
        JScrollPane scroll_resivo=new JScrollPane(tabla_resivo);
        scroll_resivo.setBounds((base/2)-100, 20, 350, 400);
        panel_obc.add(scroll_resivo);
        
        ActualizarTablaProducto();
    }
    private void JPanelIniciar(){
        panel_obc=new JPanel();
        panel_obc.setBounds(x, altura+y, base, 450);
        panel_obc.setOpaque(false);
        panel_obc.setLayout(null);
        add(panel_obc);
    }
    private void JTextFieldIniciar(){
        int sep=50, posx=20;
        field_id=new JTextField();
        field_id.setBounds(posx, sep*1, 100, 30);
        field_id.setFont(new Font("Book Antiqua",0,20));
        field_id.setForeground(Color.BLUE);
        field_id.addKeyListener(this);
        panel_obc.add(field_id);
        
        field_producto=new JTextField("Elegiste a...");
        field_producto.setBounds(posx*7, sep*1, 150, 30);
        field_producto.setFont(new Font("Book Antiqua",0,20));
        field_producto.setForeground(Color.BLUE);
        field_producto.setEditable(false);
        field_producto.addKeyListener(this);
        panel_obc.add(field_producto);
        
        field_cantidad=new JTextField();
        field_cantidad.setBounds(posx, sep*3, 100, 30);
        field_cantidad.setFont(new Font("Book Antiqua",0,20));
        field_cantidad.addKeyListener(this);
        panel_obc.add(field_cantidad);
        
        field_precio=new JTextField("0");
        field_precio.setBounds(posx*8, sep*3, 100, 30);
        field_precio.setFont(new Font("Book Antiqua",0,20));
        field_precio.setForeground(Color.BLUE);
        field_precio.setEditable(false);
        field_precio.addKeyListener(this);
        panel_obc.add(field_precio);
    }
    private void JButtonIniciar(){
        boton_aniadir=new JButton("Añadir");
        boton_aniadir.setBounds(20, 200, 100, 30);
        boton_aniadir.addActionListener(this);
        boton_aniadir.setFont(new Font("Rockwell",0,16));
        boton_aniadir.setForeground(Color.blue);
        panel_obc.add(boton_aniadir);
        
        boton_fin=new JButton("Finalizar");
        boton_fin.setBounds(20*35, 200, 100, 30);
        boton_fin.addActionListener(this);
        boton_fin.setFont(new Font("Rockwell",0,16));
        boton_fin.setForeground(Color.blue);
        panel_obc.add(boton_fin);
        
        boton_eliminar=new JButton("Quitar");
        boton_eliminar.setBounds(20*35, 150, 100, 30);
        boton_eliminar.addActionListener(this);
        boton_eliminar.setFont(new Font("Rockwell",0,16));
        boton_eliminar.setBackground(Color.red);
        boton_eliminar.setForeground(Color.WHITE);
        panel_obc.add(boton_eliminar);
    }

    @Override public void mousePressed(MouseEvent e){} @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){} @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseClicked(MouseEvent e){
        int f=tabla_productos.getSelectedRow();
        field_id.setText(tabla_productos.getValueAt(f, 0).toString());
        field_producto.setText(tabla_productos.getValueAt(f, 1).toString());
        EventoFieldCantidad();
    }

    @Override public void keyTyped(KeyEvent e){} @Override public void keyPressed(KeyEvent e){} 
    @Override public void keyReleased(KeyEvent e){
        if(field_id==e.getSource()){
            EventoFieldID();
            EventoFieldCantidad();
        }if(field_cantidad==e.getSource()){
            EventoFieldCantidad();
        }
    }

    @Override public void actionPerformed(ActionEvent e){
        if(e.getSource()==boton_aniadir){
            BotonAniadir();
        }if(e.getSource()==boton_eliminar){
            BotonEliminar();
        }if(e.getSource()==boton_fin){
            BotonFin();
        }
    }
    //funcionalidad de los field, sus dinamicas
    private void EventoFieldID(){
        String id_txt=field_id.getText().trim().toLowerCase();
        String cod_txt;
        for(int i=0; i<tabla_productos.getRowCount(); i++){
            cod_txt=tabla_productos.getValueAt(i, 0).toString().trim().toLowerCase();
            if(id_txt.equals(cod_txt)){
                tabla_productos.setRowSelectionInterval(i, i);//selecionar una fila
                field_producto.setText(tabla_productos.getValueAt(i, 1).toString());
                field_precio.setText(Double.toString(1));
                break;
            }else{
                field_producto.setText("Elegiste a...");
                tabla_productos.clearSelection();//quitar la fila seleccionadas
            }
        }
    }
    private void EventoFieldCantidad(){
        String codigo;
        int cantidad;
        double precio, p_total;
        try{
            codigo=field_id.getText().trim();
            if(producto.ExisteCodigo(codigo)){
                precio=producto.getPrecio_venta(codigo);
                cantidad=Integer.parseInt(field_cantidad.getText());
                p_total=cantidad*precio;
                //Redondeo
                BigDecimal number = new BigDecimal(Double.toString(p_total));
                BigDecimal roundedNumber = number.setScale(2, RoundingMode.HALF_UP);
                p_total = roundedNumber.doubleValue();
                field_precio.setText(Double.toString(p_total));
            }
        }catch (Exception e){field_precio.setText("");}
    }
    private void BotonAniadir(){
        try {
            String codigo, nombre;
            double precio, p_total;
            int cantidad, stock;;
            codigo=field_id.getText().trim();
            nombre=producto.getNombre(codigo);
            precio=producto.getPrecio_venta(codigo);
            cantidad=Integer.parseInt(field_cantidad.getText());
            stock=Integer.parseInt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 3).toString());
            p_total=precio*cantidad;
            BigDecimal number = new BigDecimal(Double.toString(p_total));
            BigDecimal roundedNumber = number.setScale(2, RoundingMode.HALF_UP);
            if(cantidad<=stock){//controlar la contidad
                DefaultTableModel model=(DefaultTableModel)tabla_resivo.getModel();
                model.addRow(new Object[]{codigo, nombre, cantidad, roundedNumber});
                tabla_productos.setValueAt(stock-cantidad, tabla_productos.getSelectedRow(), 3);
                tabla_productos.revalidate();
                tabla_resivo.revalidate();
            }else{JOptionPane.showMessageDialog(null, "Cantidad no disponible");}
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Complete los cuadros correctamente");
        }
    }
    private void BotonEliminar(){
        try {
            int cant=Integer.parseInt(tabla_resivo.getValueAt(tabla_resivo.getSelectedRow(), 2).toString());
            String codigo=tabla_resivo.getValueAt(tabla_resivo.getSelectedRow(), 0).toString().trim();

            DefaultTableModel model=(DefaultTableModel)tabla_resivo.getModel();
            model.removeRow(tabla_resivo.getSelectedRow());
            model.fireTableDataChanged();
            
            for(int i=0; i<tabla_productos.getRowCount(); i++){
                if(codigo.equals(tabla_productos.getValueAt(i, 0))){
                    cant+=Integer.parseInt(tabla_productos.getValueAt(i, 3).toString());
                    tabla_productos.setValueAt(cant, i, 3);
                    break;
                }
            }
            tabla_resivo.revalidate(); tabla_productos.revalidate();
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "Selecciona un producto");}
    }
    private void BotonFin(){
        if(tabla_resivo.getRowCount()>0){
            new BoletaNuebo(this).setVisible(true);
        }else{JOptionPane.showMessageDialog(null,"Sin productos");}
    }
    protected void ActualizarTablaProducto(){
        String codigo, marca, nombre, almcenado;
        int stock;
        double precio_venta;
        TableModel model_elim=tabla_productos.getModel();
        ((DefaultTableModel)model_elim).setRowCount(0);
        DefaultTableModel model=(DefaultTableModel)tabla_productos.getModel();
        try {
            ResultSet rs=producto.getProducto();
            while(rs.next()){
                codigo=rs.getString(1);
                marca=rs.getString(2);
                nombre=rs.getString(3);
                stock=rs.getInt(4);
                almcenado=rs.getString(5);
                precio_venta=rs.getDouble(7);
                if(stock>0){model.addRow(new Object[]{codigo,marca,nombre,stock,almcenado,precio_venta});}
            }
            tabla_productos.revalidate();
        } catch (Exception e) {System.out.println(e);}
    }
    protected void EliminarTablaResivo(){
        TableModel model=tabla_resivo.getModel();
        ((DefaultTableModel)model).setRowCount(0);
    }
    protected DefaultTableModel ModelTabla(){
        String codigo, descrip;
        int cant;
        double precio_unita;
        DefaultTableModel model=new DefaultTableModel();
        String []columnas={"codigo","cant","Descripcion","p. UNIT","Importe"};
        for(String c:columnas){model.addColumn(c);}
        
        for(int i=0; i<tabla_resivo.getRowCount(); i++){
            codigo=tabla_resivo.getValueAt(i, 0).toString();
            cant=Integer.parseInt(tabla_resivo.getValueAt(i, 2).toString());
            descrip=producto.getNombre(codigo);
            precio_unita=producto.getPrecio_compra(codigo);
            model.addRow(new Object[]{codigo,cant,descrip,precio_unita,cant*precio_unita});
        }
        return model;
    }
    
}
