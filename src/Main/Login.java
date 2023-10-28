package Main;

import BaseDatos.Empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends Padre implements ActionListener{
    private JLabel txt, label_fondo, label_lineas;
    private JPanel panel_secion;
    private JTextField field_user;
    private JPasswordField field_pass;
    private JButton boton_login;
    protected static String user, pass;
    private final Empleado usuario=new Empleado();
    
    public Login(Color fondo) {
        super(fondo);
        this.setIconImage(new ImageIcon("src/Images/icono.png").getImage());
        IniciarComponentes();
    }public Login(int base, int altura) {
        super(base, altura);
        IniciarComponentes();
    }public Login(int base, int altura, Color fondo) {
        super(base, altura, fondo);
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/Images/icono.png").getImage());
        this.setTitle("Login");
        JPanelIniciar();
        JLabelIniciar();
        JTextFieldIniciar();
        JButtonIniciar();
    }
    private void JLabelIniciar(){
        super.setFontLetras("Cooper Black", 1, 24);
        super.JLabelTitulo("Login");
        
        ImageIcon icon_fondo=new ImageIcon("src/Images/fondo.png");
        label_fondo=new JLabel(icon_fondo);
        label_fondo.setIcon(new ImageIcon(icon_fondo.getImage().getScaledInstance(base, altura, Image.SCALE_SMOOTH)));
        label_fondo.setBounds(0, 0, base, altura);
        panel_prinsipal.add(label_fondo);
        
        txt=new JLabel("Usuario");
        txt.setBounds(50, 0, 300, 30);
        txt.setFont(new Font("Bell MT",1,24));
        //txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.RED);
        panel_secion.add(txt);
        
        txt=new JLabel("Contraseña");
        txt.setBounds(50, 100, 300, 30);
        txt.setFont(new Font("Bell MT",1,24));
        //txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.RED);
        panel_secion.add(txt);
        
        label_lineas=new JLabel("_________________");
        label_lineas.setBounds(50, 30, 300, 30);
        label_lineas.setFont(new Font("Arial Balck",1,24));
        label_lineas.setForeground(Color.GRAY);
        panel_secion.add(label_lineas);
        
        label_lineas=new JLabel("_________________");
        label_lineas.setBounds(50, 130, 300, 30);
        label_lineas.setFont(new Font("Arial Balck",1,24));
        label_lineas.setForeground(Color.GRAY);
        panel_secion.add(label_lineas);
    }
    private void JPanelIniciar(){
        panel_secion=new JPanel();
        panel_secion.setBounds(80, 200, 300, 300);
        panel_secion.setOpaque(false);
        panel_secion.setLayout(null);
        panel_prinsipal.add(panel_secion);
    }
    private void JTextFieldIniciar(){
        field_user=new JTextField();
        field_user.setBounds(50,30,200,30);
        field_user.setFont(new Font("Times new Roman",0,20));
        field_user.setOpaque(false);
        field_user.setBorder(null);
        field_user.setForeground(Color.WHITE);
        field_user.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){BotonLogin();}
                if(e.getKeyCode()==KeyEvent.VK_F1){field_user.setText("root");field_pass.setText("1234");}
            }
        });
        panel_secion.add(field_user);
        
        field_pass=new JPasswordField();
        field_pass.setBounds(50,130,200,30);
        field_pass.setFont(new Font("Times new Roman",0,20));
        field_pass.setOpaque(false);
        field_pass.setBorder(null);
        field_pass.setForeground(Color.WHITE);
        field_pass.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){BotonLogin();}
                if(e.getKeyCode()==KeyEvent.VK_F1){field_user.setText("root");field_pass.setText("1234");}
            }
        });
        panel_secion.add(field_pass);
    }
    private void JButtonIniciar(){
        boton_login=new JButton("Login");
        boton_login.setBounds(80,230,150,30);
        boton_login.setFont(new Font("Arial Black",0,20));
        boton_login.setContentAreaFilled(false);
        boton_login.setBorder(null);
        boton_login.addActionListener(this);
        boton_login.setToolTipText("Press. Enter");
        panel_secion.add(boton_login);
    }
    
    @Override public void actionPerformed(ActionEvent e){
        if(e.getSource()==boton_login){
            BotonLogin();
        }
    }
    private void BotonLogin(){
        String dni;
        pass=field_pass.getText().trim();
            user=field_user.getText().trim();
            if(!pass.equals("") && !user.equals("")){
                dni=usuario.getDNI(user);
                if(usuario.ExisteUsuario(user) && usuario.getPassWord(dni).equals(pass) && usuario.getEstado(dni).equals("Activo")){
                    dispose();
                    new InterfasTienda().setVisible(true);
                }else{JOptionPane.showMessageDialog(null, "El Usuario o contraseña es incorrecta");}
            }else{JOptionPane.showMessageDialog(null, "LLene los campos correctamente");}
    }
    
    public static void main(String[] args) {
        Login p = new Login(500,600);
        p.setVisible(true);
    }
}
