package Main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelPrincipal extends JPanel implements ActionListener{
    private JPanel panel_titulo, panel_desc;
    private JLabel label_titulo, label_fecha, label_image, label_copyright;
    private JTextArea area_descr;
    private JButton btn_login;
    private int base;
    private InterfasTienda main;

    public PanelPrincipal(int base, InterfasTienda main) {
        setLayout(null);
        setBackground(Color.WHITE);
        this.base=base;
        this.main=main;
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JTexAreaIniciar();
        JButtonIniciar();
    }
    private void JTexAreaIniciar(){
        area_descr=new JTextArea();
        area_descr.setEditable(false);
        area_descr.setFont(new Font("Times new Roman",0,18));
        area_descr.setText("Sistema de gestíon de la tienda de abarrotes. Controle y administre\n" +
                            "de forma óptima la tienda de abarrotes.\n" +
                            "\n" +
                            "Ademas de que proporcionara datos actualizados sobre los \n" +
                            "productos, la informacion de los vendedores, y los proveedores.\n" +
                            "\n" +
                            "Esta heramienta se especializa en:\n" +
                            "\n" +
                            "-Ventas.\n" +
                            "-Visualizar los productos disponibles y su abastecimiento.\n" +
                            "-Visualisar las boletas y generarlas al hacer una venta.\n" +
                            "-Administrar la lista de proveedores.\n" +
                            "-Y administrar los usuarios: vendedor y administrador.");
        panel_desc.add(area_descr);
        
        JScrollPane scroll=new JScrollPane(area_descr);
        scroll.setBounds(20, 100, 500, 400);
        scroll.setBorder(null);
        panel_desc.add(scroll);
    }
    private void JLabelIniciar(){
        label_titulo=new JLabel("Administracion/ gestion/ Tienda de Abarrotes");
        label_titulo.setFont(new Font("Cooper Black",0,16));
        label_titulo.setForeground(Color.WHITE);
        label_titulo.setBounds(70,30,500,30);
        panel_titulo.add(label_titulo);
        
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("EEEE 'de' MMMM 'de' yyyy",new Locale("es"));
        LocalDate fecha = LocalDate.now();
        label_fecha=new JLabel("Hoy es "+fecha.format(formato));
        label_fecha.setFont(new Font("Calibri Light",0,29));
        label_fecha.setForeground(Color.WHITE);
        label_fecha.setBounds(70,80,500,50);
        panel_titulo.add(label_fecha);
        
        label_titulo=new JLabel("Bienvenido");
        label_titulo.setFont(new Font("Arial",0,26));
        label_titulo.setForeground(Color.BLACK);
        label_titulo.setBounds(20,50,500,30);
        panel_desc.add(label_titulo);
        
        ImageIcon icon_producto=new ImageIcon("src/Images/producto.png");
        label_image=new JLabel();
        label_image.setIcon(new ImageIcon(icon_producto.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        label_image.setBounds(500,250,250,250);
        panel_desc.add(label_image);
        
        label_copyright=new JLabel("Todos los derechos a davidillanis ©");
        label_copyright.setBounds(0,780,900,30);
        label_copyright.setHorizontalAlignment(SwingConstants.CENTER);
        label_copyright.setFont(new Font("Times new Roman",0,14));
        label_copyright.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/david-abel-81645a1b5/"));
                }catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override public void mouseEntered(MouseEvent e) {
                label_copyright.setForeground(Color.BLUE);
                label_copyright.setFont(new Font("Times new Roman",0,16));
                label_copyright.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override public void mouseExited(MouseEvent e) {
                label_copyright.setFont(new Font("Times new Roman",0,14));
                label_copyright.setForeground(Color.BLACK);
                label_copyright.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        add(label_copyright);
    }
    private void JPanelIniciar(){
        panel_titulo=new JPanel();
        panel_titulo.setBounds(0, 50, base, 150);
        panel_titulo.setLayout(null);
        panel_titulo.setBackground(new Color(22,100,250));
        add(panel_titulo);
        
        panel_desc=new JPanel();
        panel_desc.setBounds(0, 50+panel_titulo.getHeight(), base, 550);
        panel_desc.setLayout(null);
        panel_desc.setBackground(Color.WHITE);
        add(panel_desc);
    }
    private void JButtonIniciar(){
        btn_login=new JButton("Login");
        btn_login.setBounds(0,500,150,30);
        btn_login.setBorder(null);
        btn_login.setFont(new Font("Rockwell",0,20));
        btn_login.setForeground(Color.BLUE);
        btn_login.setBackground(Color.WHITE);
        btn_login.addActionListener(this);
        panel_desc.add(btn_login);
    }

    @Override public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_login){
            main.dispose();
            new Login(500,600).setVisible(true);
        }
    }
}
