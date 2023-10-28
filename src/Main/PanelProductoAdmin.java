package Main;

import BaseDatos.Imagen;
import BaseDatos.Producto;
import BaseDatos.Proveedor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

public class PanelProductoAdmin extends JPanel implements ActionListener, MouseListener{
    private JLabel label_titulo, label_txt, label_img;
    private JTable tabla_productos, tabla_provedor;
    private JTextField field_codigo, field_marca, field_nombre, field_stock, field_precio_compra, field_precio_venta;
    private JComboBox combo_almacenado, combo_dni_provedor;
    private JPanel panel_formulario, panel_abajo;
    private JButton btn_nuevo, btn_editar, btn_eliminar, btn_limpiar, btn_img;
    private File dir_img;
    private int base;
    private final Proveedor provedor=new Proveedor();
    private final Producto producto=new Producto();
    private final Imagen imagen=new Imagen();
    
    public PanelProductoAdmin(int base) {
        setLayout(null);
        setBackground(new Color(159,255,184));
        this.base=base;
        this.dir_img=null;
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JTableIniciar();
        JTextFieldIniciar();
        JComboBoxIniciar();
        JButtonIniciar();
    }
    private void JPanelIniciar(){
        panel_formulario=new JPanel();
        panel_formulario.setBounds(0,50,base,350);
        panel_formulario.setBackground(Color.BLUE);
        panel_formulario.setLayout(null);
        add(panel_formulario);
        
        panel_abajo=new JPanel();
        panel_abajo.setBounds(0,400,base,600);
        panel_abajo.setOpaque(true);
        panel_abajo.setBackground(new Color(177,208,254));
        panel_abajo.setLayout(null);
        add(panel_abajo);
    }
    private void JLabelIniciar(){
        int x=10, y=10;
        label_titulo=new JLabel("Administrar Producto");
        label_titulo.setBounds(0,10,base,30);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setFont(new Font("Cooper Black",0,26));
        label_titulo.setForeground(Color.red);
        add(label_titulo);
        
        label_txt=new JLabel("Codigo");
        label_txt.setBounds(x+50*0, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Marca");
        label_txt.setBounds(x+50*4, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
    
        label_txt=new JLabel("Nombre");
        label_txt.setBounds(x+50*8, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Stock");
        label_txt.setBounds(x+50*12, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_img=new JLabel();
        label_img.setBounds(x+50*15, y+50*0, 110, 100);
        panel_formulario.add(label_img);
        
        label_txt=new JLabel("Almacenado");
        label_txt.setBounds(x+50*0, y+50*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Precio compra");
        label_txt.setBounds(x+50*4, y+50*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Precio Venta");
        label_txt.setBounds(x+50*8, y+50*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Proveedor");
        label_txt.setBounds(x+50*12, y+50*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
    }
    private void JTableIniciar(){
        DefaultTableModel model1=new DefaultTableModel();
        String []co1={"codigo","precio venta", "precio compra", "categoria", "stock", "nombre", "almacendao en","Proveedor"};
        for(String c:co1){model1.addColumn(c);}
        tabla_productos=new JTable(model1);
        tabla_productos.setBackground(new Color(74,209,140));
        tabla_productos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//solo una celda
        tabla_productos.setColumnSelectionAllowed(false);//solo filas
        tabla_productos.setDefaultEditor(Object.class, null);//no editar celdad
        tabla_productos.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_productos.setRowHeight(20);
        tabla_productos.setForeground(Color.BLUE);
        tabla_productos.setFont(new Font("Baskerville Old Face",0,18));
        tabla_productos.setToolTipText("Tabla Productos");
        tabla_productos.addMouseListener(this);
        panel_abajo.add(tabla_productos);
        JScrollPane scroll1=new JScrollPane(tabla_productos);
        scroll1.setBounds(0, 100, base, 350);
        panel_abajo.add(scroll1);
        
        DefaultTableModel model2=new DefaultTableModel();
        String []co2={"DNI","Nombre", "Telefono", "Email", "Direccion"};
        for(String c:co2){model2.addColumn(c);}
        tabla_provedor=new JTable(model2);
        tabla_provedor.setBackground(new Color(74,209,140));
        tabla_provedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//solo una celda
        tabla_provedor.setColumnSelectionAllowed(false);//solo filas
        tabla_provedor.setDefaultEditor(Object.class, null);//no editar celdad
        tabla_provedor.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_provedor.setRowHeight(20);
        tabla_provedor.setForeground(Color.BLUE);
        tabla_provedor.setFont(new Font("Baskerville Old Face",0,18));
        tabla_provedor.setToolTipText("Tabla Proveedores");
        tabla_provedor.addMouseListener(this);
        panel_abajo.add(tabla_provedor);
        JScrollPane scroll2=new JScrollPane(tabla_provedor);
        scroll2.setBounds(0, 200, base, 150);
        panel_formulario.add(scroll2);
        ActualizarTablaProducto();
        ActualizarTablaProvedor();
    }
    private void JTextFieldIniciar(){
        int x=10, y=10;
        
        field_codigo=new JTextField();
        field_codigo.setBounds(x+50*0, y+30+50*0, 150, 30);
        field_codigo.setFont(new Font("Rockwell",0,18));
        field_codigo.setForeground(Color.BLUE);
        field_codigo.setBorder(null);
        panel_formulario.add(field_codigo);
        
        field_marca=new JTextField();
        field_marca.setBounds(x+50*4, y+30+50*0, 150, 30);
        field_marca.setFont(new Font("Rockwell",0,18));
        field_marca.setForeground(Color.BLUE);
        field_marca.setBorder(null);
        panel_formulario.add(field_marca);
        
        field_nombre=new JTextField();
        field_nombre.setBounds(x+50*8, y+30+50*0, 150, 30);
        field_nombre.setFont(new Font("Rockwell",0,18));
        field_nombre.setForeground(Color.BLUE);
        field_nombre.setBorder(null);
        panel_formulario.add(field_nombre);
        
        field_stock=new JTextField();
        field_stock.setBounds(x+50*12, y+30+50*0, 150, 30);
        field_stock.setFont(new Font("Rockwell",0,18));
        field_stock.setForeground(Color.BLUE);
        field_stock.setBorder(null);
        panel_formulario.add(field_stock);
        
        field_precio_compra=new JTextField();
        field_precio_compra.setBounds(x+50*4, y+30+50*2, 150, 30);
        field_precio_compra.setFont(new Font("Rockwell",0,18));
        field_precio_compra.setForeground(Color.BLUE);
        field_precio_compra.setBorder(null);
        panel_formulario.add(field_precio_compra);
        
        field_precio_venta=new JTextField();
        field_precio_venta.setBounds(x+50*8, y+30+50*2, 150, 30);
        field_precio_venta.setFont(new Font("Rockwell",0,18));
        field_precio_venta.setForeground(Color.BLUE);
        field_precio_venta.setBorder(null);
        panel_formulario.add(field_precio_venta);
    }
    private void JComboBoxIniciar(){
        int x=10, y=10;
        String []almacenamiento={"Unidades","Kilos","Litros"};
        combo_almacenado=new JComboBox();
        combo_almacenado.setBounds(x+50*0, y+30+50*2, 150, 30);
        combo_almacenado.setFont(new Font("Rockwell",0,18));
        combo_almacenado.setForeground(Color.BLUE);
        for(String a:almacenamiento){combo_almacenado.addItem(a);}
        combo_almacenado.setBorder(null);
        panel_formulario.add(combo_almacenado);
        
        combo_dni_provedor=new JComboBox();
        ResultSet rs=provedor.getProveedor();
        try {while(rs.next()){combo_dni_provedor.addItem(rs.getString(1));}} catch (Exception e) {}
        combo_dni_provedor.setBounds(x+50*12, y+30+50*2, 150, 30);
        combo_dni_provedor.setFont(new Font("Rockwell",0,18));
        combo_dni_provedor.setForeground(Color.BLUE);
        combo_dni_provedor.setBorder(null);
        combo_dni_provedor.addMouseListener(this);
        panel_formulario.add(combo_dni_provedor);
    }
    private void JButtonIniciar(){
        int x=20, y=20;
        
        ImageIcon icon=new ImageIcon("src/Images/nuebo.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_nuevo=new JButton("Nuevo",icon);
        btn_nuevo.setBounds(x+50*1, y, 150, 60);
        btn_nuevo.setFont(new Font("Arial",1,14));
        btn_nuevo.setForeground(Color.BLACK);
        btn_nuevo.addActionListener(this);
        panel_abajo.add(btn_nuevo);
        
        icon=new ImageIcon("src/Images/editar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_editar=new JButton("Editar",icon);
        btn_editar.setBounds(x+50*5, y, 150, 60);
        btn_editar.setFont(new Font("Arial",1,14));
        btn_editar.setForeground(Color.BLACK);
        btn_editar.addActionListener(this);
        panel_abajo.add(btn_editar);
        
        icon=new ImageIcon("src/Images/eliminar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_eliminar=new JButton("Eliminar",icon);
        btn_eliminar.setBounds(x+50*9, y, 150, 60);
        btn_eliminar.setFont(new Font("Arial",1,14));
        btn_eliminar.setForeground(Color.BLACK);
        btn_eliminar.addActionListener(this);
        panel_abajo.add(btn_eliminar);
        
        icon=new ImageIcon("src/Images/limpiar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_limpiar=new JButton("Limpiar",icon);
        btn_limpiar.setBounds(x+50*13, y, 150, 60);
        btn_limpiar.setFont(new Font("Arial",1,14));
        btn_limpiar.setForeground(Color.BLACK);
        btn_limpiar.addActionListener(this);
        panel_abajo.add(btn_limpiar);
        
        btn_img=new JButton("Img.");
        btn_img.setBounds(x+51*15, y+20+50*2, 70, 30);
        btn_img.setFont(new Font("Arial",1,14));
        btn_img.setForeground(Color.BLACK);
        btn_img.addActionListener(this);
        panel_formulario.add(btn_img);
    }

    @Override public void mouseReleased(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}@Override public void mouseEntered(MouseEvent e){}    
    @Override public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tabla_productos && e.getClickCount()==3){
            new ProductoInfoVer(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 0).toString()).setVisible(true);
        }
    }
    @Override public void mousePressed(MouseEvent e){
        if(e.getSource()==tabla_provedor){
            combo_dni_provedor.setSelectedItem(tabla_provedor.getValueAt(tabla_provedor.getSelectedRow(),0));
        }if(e.getSource()==tabla_productos){
            String codigo, marca, nombre, almacenado, dni_prov;
            int stock;
            double precio_compra, precio_venta;
            codigo=tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 0).toString();
            marca=producto.getMarca(codigo);
            nombre=producto.getNombre(codigo);
            stock=producto.getStock(codigo);
            almacenado=producto.getAlmacenado(codigo);
            precio_compra=producto.getPrecio_compra(codigo);
            precio_venta=producto.getPrecio_venta(codigo);
            dni_prov=producto.getDniProvedor(codigo);
            
            field_codigo.setText(codigo);
            field_marca.setText(marca);
            field_nombre.setText(nombre);
            field_stock.setText(Integer.toString(stock));
            combo_almacenado.setSelectedItem(almacenado);
            field_precio_compra.setText(Double.toString(precio_compra));
            field_precio_venta.setText(Double.toString(precio_venta));
            combo_dni_provedor.setSelectedItem(dni_prov);
        }
    } 
    @Override public void actionPerformed(ActionEvent e){
        if(e.getSource()==btn_editar){
            BotonEditar();
        }if(e.getSource()==btn_eliminar){
            BotonEliminar();
        }if(e.getSource()==btn_nuevo){
            BotonNuebo();
        }if(e.getSource()==btn_limpiar){
            BotonLimpiar();
        }if(e.getSource()==btn_img){
            JFileChooser chooser=new JFileChooser(new File("src/Images"));
            if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
                dir_img=chooser.getSelectedFile();
                label_img.setIcon(new ImageIcon(new ImageIcon(dir_img.getPath()).getImage().
                        getScaledInstance(label_img.getWidth(), label_img.getHeight(), Image.SCALE_SMOOTH)));
            }
        }
    }

    private void BotonEditar(){
        try {
            String codigo, marca, nombre, almacenado, dni_prov;
            int stock;
            double precio_compra, precio_venta;
            codigo=field_codigo.getText().trim();
            if(producto.ExisteCodigo(codigo)){
                marca=field_marca.getText().trim();
                nombre=field_nombre.getText().trim();
                stock=Integer.parseInt(field_stock.getText().trim());
                almacenado=combo_almacenado.getSelectedItem().toString();
                precio_compra=Double.parseDouble(field_precio_compra.getText().trim());
                precio_venta=Double.parseDouble(field_precio_venta.getText().trim());
                dni_prov=combo_dni_provedor.getSelectedItem().toString();
                producto.Editar(codigo, marca, nombre, stock, almacenado, precio_compra, precio_venta,dni_prov);
                ActualizarTablaProducto();
                JOptionPane.showMessageDialog(null,"Cambios guardados!!!");
            }else{JOptionPane.showMessageDialog(null,"Codigo no valido");}
        }catch(Exception e){JOptionPane.showMessageDialog(null,"LLene correctamente los recuadros");}
    }
    private void BotonEliminar(){
        try {
            String codigo_producto;
            dir_img=null;
            label_img.setIcon(new ImageIcon());
            codigo_producto=field_codigo.getText().trim();
            if(producto.ExisteCodigo(codigo_producto)){
                imagen.EliminarImagen(imagen.getCodigoImagen(codigo_producto));
                producto.EliminarProducto(codigo_producto);
                ActualizarTablaProducto();
                BotonLimpiar();
                JOptionPane.showMessageDialog(null,"Se elimino un producto");
            }else{JOptionPane.showMessageDialog(null,"Codigo no valido");}
        }catch(Exception e){JOptionPane.showMessageDialog(null,"LLene correctamente los recuadros");}
    }
    private void BotonNuebo(){
        try {
            String codigo, marca, nombre, almacenado, dni_prov;
            int stock;
            double precio_compra, precio_venta;
            label_img.setIcon(new ImageIcon());
            codigo=field_codigo.getText().trim();
            if(!producto.ExisteCodigo(codigo)){
                marca=field_marca.getText().trim();
                nombre=field_nombre.getText().trim();
                stock=Integer.parseInt(field_stock.getText().trim());
                almacenado=combo_almacenado.getSelectedItem().toString();
                precio_compra=Double.parseDouble(field_precio_compra.getText().trim());
                precio_venta=Double.parseDouble(field_precio_venta.getText().trim());
                dni_prov=combo_dni_provedor.getSelectedItem().toString();

                producto.NewProducto(codigo, marca, nombre, stock, almacenado, precio_compra, precio_venta,dni_prov);
                imagen.NewImage(Aleatorio(5), codigo, dir_img);
                dir_img=null;
                ActualizarTablaProducto();
                JOptionPane.showMessageDialog(null, "!!!Registro exitoso¡¡¡");
                BotonLimpiar();
            }else{JOptionPane.showMessageDialog(null,"El codigo ya existe");}
        }catch(Exception e){JOptionPane.showMessageDialog(null,"LLene correctamente los recuadros");}
    }
    private void BotonLimpiar(){
        dir_img=null;
        label_img.setIcon(new ImageIcon());
        field_codigo.setText("");
        field_marca.setText("");
        field_nombre.setText("");
        field_stock.setText("");
        field_precio_compra.setText("");
        field_precio_venta.setText("");
    }
    public void ActualizarTablaProducto(){
        String codigo, marca, nombre, almacendo, dni_prov;
        int stock;
        double precio_compra, precio_venta;
        
        TableModel model1=tabla_productos.getModel();
        ((DefaultTableModel)model1).setRowCount(0);
        
        DefaultTableModel model=(DefaultTableModel)tabla_productos.getModel();
        try {
            ResultSet rs=producto.getProducto();
            while(rs.next()){
                codigo=rs.getString("Codigo_producto");
                marca=rs.getString("Marca");
                nombre=rs.getString("Nombre");
                stock=rs.getInt("Stock");
                almacendo=rs.getString("Almacenado");
                precio_compra=rs.getDouble("Precio_compra");
                precio_venta=rs.getDouble("Precio_venta");
                dni_prov=rs.getString("DNI_proveedor");
                model.addRow(new Object[]{codigo,marca,nombre,stock,almacendo,precio_compra,precio_venta,dni_prov});
            }
            tabla_productos.revalidate();
        } catch (Exception e) {System.err.print("Error PanelProductoNuebo->ActualizarTablaProducto");}
        
    }
    public void ActualizarTablaProvedor(){
        String dni, nombre, telefono, email, direccion;
        
        TableModel model1=tabla_provedor.getModel();
        ((DefaultTableModel)model1).setRowCount(0);
        
        DefaultTableModel model=(DefaultTableModel)tabla_provedor.getModel();
        try {
            ResultSet rs=provedor.getProveedor();
            while(rs.next()){
                dni=rs.getString("DNI_proveedor");
                nombre=rs.getString("Nombre");
                telefono=rs.getString("Telefono");
                email=rs.getString("Email");
                direccion=rs.getString("Direccion");
                model.addRow(new Object[]{dni, nombre, telefono, email, direccion});
            }
            tabla_provedor.revalidate();
        } catch (Exception e) {}
        
    }
    private String Aleatorio(int c){
        String txt="";
        for(int i=0; i<c; i++){
            if((int)(Math.random()*2)==0){
                txt+=(int)(Math.random()*10);
            }else{
                txt+=(char)((int)(Math.random()*25+65));
            }
        }
        return txt;
    }
}
