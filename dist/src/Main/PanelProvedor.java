package Main;

import BaseDatos.Proveedor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
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

public class PanelProvedor extends JPanel implements ActionListener, MouseListener{
    private JLabel label_titulo, label_txt;
    private JTextField field_dni, field_nombre, field_telefono, field_email, field_direc;
    private JButton btn_nuebo, btn_editar, btn_eliminar, btn_limpiar;
    private JPanel panel_formulario, panel_admin;
    private JTable tabla_proveedores;
    private Proveedor provedor_bd=new Proveedor();
    private int base;

    public PanelProvedor(int base) {
        setLayout(null);
        setBackground(Color.WHITE);
        this.base=base;
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JTextFieldIniciar();
        JButtonIniciar();
        JTableIniciar();
    }
    private void JLabelIniciar(){
        int x=20, y=20;
        label_titulo=new JLabel("Proveedores");
        label_titulo.setBounds(0, 10, base, 40);
        label_titulo.setFont(new Font("Cooper Black",0,26));
        label_titulo.setForeground(Color.RED);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(label_titulo);
        
        label_txt=new JLabel("DNI");
        label_txt.setBounds(x+50*0, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Nombre");
        label_txt.setBounds(x+50*5, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Telefono");
        label_txt.setBounds(x+50*10, y+50*0, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Email");
        label_txt.setBounds(x+50*0, y+50*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        
        label_txt=new JLabel("Direccion");
        label_txt.setBounds(x+50*7, y+52*2, 150, 30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_formulario.add(label_txt);
        panel_formulario.add(label_txt);
    }
    private void JPanelIniciar(){
        panel_formulario=new JPanel();
        panel_formulario.setBounds(0,50,base,200);
        panel_formulario.setLayout(null);
        panel_formulario.setBackground(new Color(32,95,255));
        add(panel_formulario);
        
        panel_admin=new JPanel();
        panel_admin.setBounds(0,200+50,base,600);
        panel_admin.setLayout(null);
        panel_admin.setBackground(new Color(157, 185, 255));
        add(panel_admin);
    }
    private void JTextFieldIniciar(){
        int x=20, y=20;
        
        field_dni=new JTextField();
        field_dni.setBounds(x+50*0, y+30+50*0, 150, 30);
        field_dni.setFont(new Font("Rockwell",0,18));
        field_dni.setForeground(Color.BLUE);
        field_dni.setBorder(null);
        panel_formulario.add(field_dni);
        
        field_nombre=new JTextField();
        field_nombre.setBounds(x+50*5, y+30+50*0, 150, 30);
        field_nombre.setFont(new Font("Rockwell",0,18));
        field_nombre.setForeground(Color.BLUE);
        field_nombre.setBorder(null);
        panel_formulario.add(field_nombre);
        
        field_telefono=new JTextField();
        field_telefono.setBounds(x+50*10, y+30+50*0, 150, 30);
        field_telefono.setFont(new Font("Rockwell",0,18));
        field_telefono.setForeground(Color.BLUE);
        field_telefono.setBorder(null);
        panel_formulario.add(field_telefono);
        
        field_email=new JTextField();
        field_email.setBounds(x+50*0, y+30+50*2, 200, 30);
        field_email.setFont(new Font("Rockwell",0,18));
        field_email.setForeground(Color.BLUE);
        field_email.setBorder(null);
        panel_formulario.add(field_email);
        
        field_direc=new JTextField();
        field_direc.setBounds(x+50*7, y+30+50*2, 200, 30);
        field_direc.setFont(new Font("Rockwell",0,18));
        field_direc.setForeground(Color.BLUE);
        field_direc.setBorder(null);
        panel_formulario.add(field_direc);
    }
    private void JButtonIniciar(){
        int x=20, y=20;
        
        ImageIcon icon=new ImageIcon("src/Images/nuebo.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_nuebo=new JButton("Nuebo",icon);
        btn_nuebo.setBounds(x+50*1, y, 150, 60);
        btn_nuebo.setFont(new Font("Arial",1,14));
        btn_nuebo.setForeground(Color.BLACK);
        btn_nuebo.addActionListener(this);
        panel_admin.add(btn_nuebo);
        
        icon=new ImageIcon("src/Images/editar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_editar=new JButton("Editar",icon);
        btn_editar.setBounds(x+50*5, y, 150, 60);
        btn_editar.setFont(new Font("Arial",1,14));
        btn_editar.setForeground(Color.BLACK);
        btn_editar.addActionListener(this);
        panel_admin.add(btn_editar);
        
        icon=new ImageIcon("src/Images/eliminar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_eliminar=new JButton("Eliminar",icon);
        btn_eliminar.setBounds(x+50*9, y, 150, 60);
        btn_eliminar.setFont(new Font("Arial",1,14));
        btn_eliminar.setForeground(Color.BLACK);
        btn_eliminar.addActionListener(this);
        panel_admin.add(btn_eliminar);
        
        icon=new ImageIcon("src/Images/limpiar.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        btn_limpiar=new JButton("Limpiar",icon);
        btn_limpiar.setBounds(x+50*13, y, 150, 60);
        btn_limpiar.setFont(new Font("Arial",1,14));
        btn_limpiar.setForeground(Color.BLACK);
        btn_limpiar.addActionListener(this);
        panel_admin.add(btn_limpiar);
    }
    private void JTableIniciar(){
        int x=20, y=20;
        DefaultTableModel model=new DefaultTableModel();
        String []columna={"DNI","Nombre","Telefono","Email","Direccion"};
        for(String c:columna){
            model.addColumn(c);
        }
        
        tabla_proveedores=new JTable(model);
        tabla_proveedores.setBackground(new Color(182,255,159));
        tabla_proveedores.setFont(new Font("Times new Roman",0,20));
        tabla_proveedores.setForeground(Color.BLUE);
        tabla_proveedores.setRowHeight(22);
        tabla_proveedores.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_proveedores.setDefaultEditor(Object.class, null);//no se pueda editar el txt de las celdas
        tabla_proveedores.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_proveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_proveedores.addMouseListener(this);
        panel_admin.add(tabla_proveedores);
        
        JScrollPane scroll=new JScrollPane(tabla_proveedores);
        scroll.setBounds(0, y+50*2, base, 480);
        panel_admin.add(scroll);
        
        ActualizarTablaProveedor();
    }
    @Override public void actionPerformed(ActionEvent e){
        String dni, nombre, telefono, email, direc;
        dni=field_dni.getText().trim();
        nombre=field_nombre.getText().trim();
        telefono=field_telefono.getText().trim();
        email=field_email.getText().trim();
        direc=field_direc.getText().trim();
        
        if(e.getSource()==btn_nuebo){
            BotonNuebo(dni, nombre, telefono, email, direc);
        }if(e.getSource()==btn_editar){
            BotonEditar(dni, nombre, telefono, email, direc);
        }if(e.getSource()==btn_eliminar){
            BotonEliminar(dni);
        }if(e.getSource()==btn_limpiar){
            BotonLimpiar();
        }
    }
    @Override public void mousePressed(MouseEvent e){} @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){} @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseClicked(MouseEvent e){
        String dni;
        if(e.getSource()==tabla_proveedores){
            dni=tabla_proveedores.getValueAt(tabla_proveedores.getSelectedRow(), 0).toString();
            field_dni.setText(dni);
            field_nombre.setText(provedor_bd.getNombre(dni));
            field_telefono.setText(provedor_bd.getTelefono(dni));
            field_email.setText(provedor_bd.getEmail(dni));
            field_direc.setText(provedor_bd.getDireccion(dni));
        }
    }
    private void ActualizarTablaProveedor(){
        ElimarDatosTablaProveedor();
        DefaultTableModel model=(DefaultTableModel)tabla_proveedores.getModel();
        String dni, nombre, telefono, email, direccion;
        try {
            ResultSet rs=provedor_bd.getProveedor();
            while(rs.next()){
                dni=rs.getString(1);
                nombre=rs.getString(2);
                telefono=rs.getString(3);
                email=rs.getString(4);
                direccion=rs.getString(5);
                model.addRow(new Object[]{dni,nombre,telefono,email,direccion});
            }
        } catch (SQLException e) {System.out.println("Error PanelProveedor->ActualizarTablaProveedor"+e);}
        tabla_proveedores.revalidate();
    }
    private void ElimarDatosTablaProveedor(){
        TableModel model=tabla_proveedores.getModel();
        ((DefaultTableModel)model).setRowCount(0);
    }
    private void BotonNuebo(String dni,String nombre,String telefono,String email,String direc){
        if(!dni.equals("") && !nombre.equals("") && !telefono.equals("") && !provedor_bd.ExisteDNI(dni)){
            provedor_bd.NewProveedor(dni, nombre, telefono, email, direc);
            ActualizarTablaProveedor();
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
            field_dni.setText("");
            field_nombre.setText("");
            field_telefono.setText("");
            field_email.setText("");
            field_direc.setText("");
        }else if(provedor_bd.ExisteDNI(dni)){
            JOptionPane.showMessageDialog(null, "El DNI ya existe, pruebe otro");
        }else{
            JOptionPane.showMessageDialog(null, "Llene correctamente los datos");
        }
    }
    private void BotonEditar(String dni,String nombre,String telefono,String email,String direc){
        if(provedor_bd.ExisteDNI(dni)){
            provedor_bd.Editar(dni, nombre, telefono, email, direc);
            ActualizarTablaProveedor();
            JOptionPane.showMessageDialog(null, "Edicion Exitosa");
        }else{
            JOptionPane.showMessageDialog(null, "DNI no encontrado");
        }
    }
    private void BotonEliminar(String dni){
        int i;
        if(provedor_bd.ExisteDNI(dni)){
            i=JOptionPane.showConfirmDialog(null, "Esta seguro, se eliminaran los datos relacionados");
            if(i==0){
                provedor_bd.EliminarProveedor(dni);
                ActualizarTablaProveedor();
                JOptionPane.showMessageDialog(null, "Operacion Exitosa!!!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "DNI no valido");
        }
    }
    private void BotonLimpiar(){
        field_dni.setText("");
        field_nombre.setText("");
        field_telefono.setText("");
        field_email.setText("");
        field_direc.setText("");
    }
}
