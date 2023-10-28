package Main;

import BaseDatos.Empleado;
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
import javax.swing.JComboBox;
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

public class PanelUsuarios extends JPanel implements ActionListener, MouseListener{
    private JLabel label_titulo, label_txt;
    private final int base;
    private JPanel panel_comp, panel_abajo;
    private JTextField field_dni,field_user,field_pass,field_nombre,field_telefono,field_email,field_salario;
    private JComboBox combo_estado,combo_nivel;
    private JButton btn_nuebo,btn_editar,btn_eliminar,btn_limpiar;
    private JTable tabla_user;
    private Empleado usuarios=new Empleado();
    
    public PanelUsuarios(int base) {
        setLayout(null);
        setBackground(Color.WHITE);
        this.base=base;
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JButtonIniciar();
        JTextFieldIniciar();
        JComboBoxIniciar();
        JTableIniciar();
    }
    private void JPanelIniciar(){
        panel_comp=new JPanel();
        panel_comp.setBounds(0,50,base,200);
        panel_comp.setBackground(Color.BLUE);
        panel_comp.setLayout(null);
        add(panel_comp);
        
        panel_abajo=new JPanel();
        panel_abajo.setBounds(0,250,base,650);
        panel_abajo.setBackground(new Color(200,200,255));
        panel_abajo.setLayout(null);
        add(panel_abajo);
    }
    private void JLabelIniciar(){
        int x=10, y=10;
        label_titulo=new JLabel("Empleados");
        label_titulo.setBounds(0, 10, base, 30);
        label_titulo.setFont(new Font("Cooper Black",0,26));
        label_titulo.setLayout(null);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setForeground(Color.red);
        add(label_titulo);
        
        label_txt=new JLabel("DNI");
        label_txt.setBounds(x+50*0,y+50*0,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Usuario");
        label_txt.setBounds(x+50*4,y+50*0,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Contraseña");
        label_txt.setBounds(x+50*8,y+50*0,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Nivel");
        label_txt.setBounds(x+50*13,y+50*0,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Nombre");
        label_txt.setBounds(x+50*0,y+50*2,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Telefono");
        label_txt.setBounds(x+50*4,y+50*2,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Email");
        label_txt.setBounds(x+50*8,y+50*2,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Salario");
        label_txt.setBounds(x+50*12,y+50*2,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
        
        label_txt=new JLabel("Estado");
        label_txt.setBounds(x+50*15,y+50*2,150,30);
        label_txt.setFont(new Font("Rockwell",0,18));
        label_txt.setForeground(Color.WHITE);
        panel_comp.add(label_txt);
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
        panel_abajo.add(btn_nuebo);
        
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
    }
    private void JTextFieldIniciar(){
        int x=10, y=40;
        
        field_dni=new JTextField();
        field_dni.setBounds(x+50*0,y+50*0,150,30);
        field_dni.setFont(new Font("Rockwell",0,19));
        field_dni.setForeground(Color.BLUE);
        field_dni.setBorder(null);
        panel_comp.add(field_dni);
        
        field_user=new JTextField();
        field_user.setBounds(x+50*4,y+50*0,150,30);
        field_user.setFont(new Font("Rockwell",0,19));
        field_user.setForeground(Color.BLUE);
        field_user.setBorder(null);
        panel_comp.add(field_user);
        
        field_pass=new JTextField();
        field_pass.setBounds(x+50*8,y+50*0,150,30);
        field_pass.setFont(new Font("Rockwell",0,19));
        field_pass.setForeground(Color.BLUE);
        field_pass.setBorder(null);
        panel_comp.add(field_pass);
        
        field_nombre=new JTextField();
        field_nombre.setBounds(x+50*0,y+50*2,150,30);
        field_nombre.setFont(new Font("Rockwell",0,19));
        field_nombre.setForeground(Color.BLUE);
        field_nombre.setBorder(null);
        panel_comp.add(field_nombre);
        
        field_telefono=new JTextField();
        field_telefono.setBounds(x+50*4,y+50*2,150,30);
        field_telefono.setFont(new Font("Rockwell",0,19));
        field_telefono.setForeground(Color.BLUE);
        field_telefono.setBorder(null);
        panel_comp.add(field_telefono);
        
        field_email=new JTextField();
        field_email.setBounds(x+50*8,y+50*2,150,30);
        field_email.setFont(new Font("Rockwell",0,19));
        field_email.setForeground(Color.BLUE);
        field_email.setBorder(null);
        panel_comp.add(field_email);
        
        field_salario=new JTextField();
        field_salario.setBounds(x+50*12,y+50*2,100,30);
        field_salario.setFont(new Font("Rockwell",0,19));
        field_salario.setForeground(Color.BLUE);
        field_salario.setBorder(null);
        panel_comp.add(field_salario);
    }
    private void JComboBoxIniciar(){
        int x=10, y=40;
        
        combo_nivel=new JComboBox();
        combo_nivel.setBounds(x+50*13,y+50*0,150,30);
        combo_nivel.setFont(new Font("Rockwell",0,19));
        combo_nivel.setForeground(Color.BLUE);
        combo_nivel.setBorder(null);
        combo_nivel.addItem("Vendedor");
        combo_nivel.addItem("Dueño");
        panel_comp.add(combo_nivel);
        
        combo_estado=new JComboBox();
        combo_estado.setBounds(x+50*15,y+50*2,100,30);
        combo_estado.setFont(new Font("Rockwell",0,19));
        combo_estado.setForeground(Color.BLUE);
        combo_estado.setBorder(null);
        combo_estado.addItem("Activo");
        combo_estado.addItem("Inactivo");
        panel_comp.add(combo_estado);
    }
    private void JTableIniciar(){
        int x=20, y=20;
        DefaultTableModel model=new DefaultTableModel();
        String []columna={"DNI","User","Estado","Nivel","Nombre","Telefono","Email","Salario"};
        for(String c:columna){
            model.addColumn(c);
        }
        
        tabla_user=new JTable(model);
        tabla_user.setBackground(new Color(182,255,159));
        tabla_user.setFont(new Font("Times new Roman",0,20));
        tabla_user.setForeground(Color.BLUE);
        tabla_user.setRowHeight(22);
        tabla_user.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_user.setDefaultEditor(Object.class, null);//no se pueda editar el txt de las celdas
        tabla_user.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_user.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_user.addMouseListener(this);
        panel_abajo.add(tabla_user);
        
        JScrollPane scroll=new JScrollPane(tabla_user);
        scroll.setBounds(0, y+50*2, base, 480);
        panel_abajo.add(scroll);
        
        ActualizarTablaUsuarios();
    }
    
    @Override public void mousePressed(MouseEvent e) {} @Override public void mouseReleased(MouseEvent e){} 
    @Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tabla_user){
            String dni,user,pass,nivel,nombre,telefono,email, estado;
            double salario;
            dni=tabla_user.getValueAt(tabla_user.getSelectedRow(), 0).toString();
            user=usuarios.getUserName(dni);
            pass=usuarios.getPassWord(dni);
            nivel=usuarios.getNivel(dni);
            nombre=usuarios.getNombre(dni);
            telefono=usuarios.getTelefono(dni);
            email=usuarios.getEmail(dni);
            salario=usuarios.getSalario(dni);
            estado=usuarios.getEstado(dni);
            field_dni.setText(dni);
            field_user.setText(user);
            field_pass.setText(pass);
            combo_nivel.setSelectedItem(nivel);
            field_nombre.setText(nombre);
            field_telefono.setText(telefono);
            field_email.setText(email);
            field_salario.setText(Double.toString(salario));
            combo_estado.setSelectedItem(estado);
        }
    }
    @Override public void actionPerformed(ActionEvent e){
        if(e.getSource()==btn_nuebo){
            BotonNuebo();
        }if(e.getSource()==btn_editar){
            BotonEditar();
        }if(e.getSource()==btn_eliminar){
            BotonEliminar();
        }if(e.getSource()==btn_limpiar){
            BotonLimpiar();
        }
    }
    
    private void ActualizarTablaUsuarios(){
        TableModel mode=tabla_user.getModel();
        ((DefaultTableModel)mode).setRowCount(0);
        
        DefaultTableModel model=(DefaultTableModel)tabla_user.getModel();
        String dni, user, estado, nivel, nombre, telefono, email;
        double salario;
        try {
            ResultSet rs=usuarios.getUsuario();
            while(rs.next()){
                dni=rs.getString(1);
                user=rs.getString(2);
                estado=rs.getString(4);
                nivel=rs.getString(5);
                nombre=rs.getString(6);
                telefono=rs.getString(7);
                email=rs.getString(8);
                salario=rs.getDouble(9);
                model.addRow(new Object[]{dni,user,estado,nivel,nombre,telefono,email,salario});
            }
        } catch (SQLException e) {System.out.println("Error PanelProveedor->ActualizarTablaProveedor");}
        tabla_user.revalidate();
    }    
    private void BotonLimpiar(){
        field_dni.setText("");
        field_user.setText("");
        field_pass.setText("");
        field_nombre.setText("");
        field_telefono.setText("");
        field_email.setText("");
        field_salario.setText("");
        combo_estado.setSelectedItem("Activo");
        combo_nivel.setSelectedItem("Vendedor");
    }
    private void BotonNuebo(){
        String dni, user, pass, estado, nivel, nombre, telefono, email;
        double salario;
        try{
            dni=field_dni.getText().trim();
            user=field_user.getText().trim();
            if(!usuarios.ExisteDNI(dni) && !usuarios.ExisteUsuario(user)){
                pass=field_pass.getText().trim();
                estado=combo_estado.getSelectedItem().toString();
                nivel=combo_nivel.getSelectedItem().toString();
                nombre=field_nombre.getText().trim();
                telefono=field_telefono.getText().trim();
                email=field_email.getText().trim();
                salario=Double.parseDouble(field_salario.getText().trim());
                usuarios.NewUsuario(dni, user, pass, estado, nivel, nombre, telefono, email, salario);
                ActualizarTablaUsuarios();
                JOptionPane.showMessageDialog(null, "Registro Exitoso¡¡¡");
            }else{JOptionPane.showMessageDialog(null, "El usuario o DNI ya existe");}
        }catch(Exception e){JOptionPane.showMessageDialog(null, "LLene correctamente los datos");}
    }
    private void BotonEditar(){
        String dni, user, pass, estado, nivel, nombre, telefono, email;
        double salario;
        try{
            dni=field_dni.getText().trim();
            if(usuarios.ExisteDNI(dni)){
                user=field_user.getText().trim();
                pass=field_pass.getText().trim();
                estado=combo_estado.getSelectedItem().toString();
                nivel=combo_nivel.getSelectedItem().toString();
                nombre=field_nombre.getText().trim();
                telefono=field_telefono.getText().trim();
                email=field_email.getText().trim();
                salario=Double.parseDouble(field_salario.getText().trim());
                usuarios.Editar(dni, user, pass, estado, nivel, nombre, telefono, email, salario);
                ActualizarTablaUsuarios();
                JOptionPane.showMessageDialog(null, "Edicion Exitosa¡¡¡");
            }else{JOptionPane.showMessageDialog(null, "DNI no valido");}
        }catch(Exception e){JOptionPane.showMessageDialog(null, "LLene correctamente los datos");}
    }
    private void BotonEliminar(){
        String dni=field_dni.getText().trim();
        int i=JOptionPane.showConfirmDialog(null, "Estas Seguro?");
        if(usuarios.ExisteDNI(dni) && i==0){
            usuarios.EliminarUsuario(dni);
            ActualizarTablaUsuarios();
            BotonLimpiar();
            JOptionPane.showMessageDialog(null, "Operacion Exitosa!!!");
        }else if(!usuarios.ExisteDNI(dni)){JOptionPane.showMessageDialog(null, "DNI no valido");}
    }
}
