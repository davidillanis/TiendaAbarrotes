package Main;

import BaseDatos.Empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class InterfasTienda extends Padre implements ActionListener, MouseListener{
    private JLabel label_fondo, label_logo;
    protected static JPanel panel_obc, panel_producto;
    private JScrollPane scroll_paneles;
    private JButton btn_main, btn_venta, btn_producto, btn_boleta, btn_vendedor, btn_provedor, btn_codigo, 
            btn_admin_user, btn_admin_producto, btn_avastecer_producto;
    private final boolean es_admin;
    private final Empleado usuario=new Empleado();
    //Dimension dim=new Dimension(1200,900);
    
    public InterfasTienda() {
        super(1200,900);
        this.setTitle("Secion de "+Login.user+"-"+usuario.getNivel(usuario.getDNI(Login.user)));
        this.setResizable(false);
        this.scroll_paneles=new JScrollPane();
        this.setIconImage(new ImageIcon("src/Images/icono.png").getImage());
        String dni=usuario.getDNI(Login.user);
        this.es_admin = usuario.getNivel(dni).equals("Dueño");
        
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JPanelIniciar();
        JLabelIniciar();
        JButtonIniciar();
        scroll_paneles.setViewportView(new PanelPrincipal(scroll_paneles.getWidth(),this));
    }
    private void JLabelIniciar(){
        ImageIcon icon_fondo=new ImageIcon("src/Images/logo.png");
        label_logo=new JLabel();
        label_logo.setIcon(new ImageIcon(icon_fondo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        label_logo.setBounds(50, -10, 200, 200);
        panel_prinsipal.add(label_logo);
        
        icon_fondo=new ImageIcon("src/Images/fondo.png");
        label_fondo=new JLabel();
        label_fondo.setIcon(new ImageIcon(icon_fondo.getImage().getScaledInstance(base, altura, Image.SCALE_SMOOTH)));
        label_fondo.setBounds(0, 0, base, altura);
        panel_prinsipal.add(label_fondo);
    }
    private void JButtonIniciar(){
        int dist=70;
        Color color_letra=Color.WHITE;
        
        btn_main=new JButton("Principal");
        btn_main.setBounds(0, dist*0, panel_obc.getWidth(), dist);
        btn_main.setBorder(null);
        btn_main.setFont(new Font("Rockwell",0,18));
        btn_main.setBackground(color_fondo);
        btn_main.setForeground(color_letra);
        btn_main.setContentAreaFilled(false);
        btn_main.addActionListener(this);
        btn_main.addMouseListener(this);
        panel_obc.add(btn_main);
        
        btn_venta=new JButton("Vender");
        btn_venta.setBounds(0, dist*1, panel_obc.getWidth(), dist);
        btn_venta.setBorder(null);
        btn_venta.setFont(new Font("Rockwell",0,18));
        btn_venta.setBackground(color_fondo);
        btn_venta.setForeground(color_letra);
        btn_venta.setContentAreaFilled(false);
        btn_venta.addActionListener(this);
        btn_venta.addMouseListener(this);
        panel_obc.add(btn_venta);
        
        
        btn_boleta=new JButton("Ver Boletas");
        btn_boleta.setBounds(0, dist*2, panel_obc.getWidth(), dist);
        btn_boleta.setBorder(null);
        btn_boleta.setFont(new Font("Rockwell",0,18));
        btn_boleta.setBackground(color_fondo);
        btn_boleta.setContentAreaFilled(false);
        btn_boleta.setForeground(color_letra);
        btn_boleta.addActionListener(this);
        btn_boleta.addMouseListener(this);
        panel_obc.add(btn_boleta);
        
        //exclusivo
        btn_producto=new JButton("Producto");
        btn_producto.setBounds(0, dist*3, panel_obc.getWidth(), dist);
        btn_producto.setBorder(null);
        btn_producto.setFont(new Font("Rockwell",0,18));
        btn_producto.setBackground(color_fondo);
        btn_producto.setContentAreaFilled(false);
        btn_producto.setForeground(color_letra);
        btn_producto.addMouseListener(this);
        btn_producto.addActionListener(this);
        panel_obc.add(btn_producto);
        
        btn_vendedor=new JButton("Vendedores");
        btn_vendedor.setBounds(0, dist*4, panel_obc.getWidth(), dist);
        btn_vendedor.setBorder(null);
        btn_vendedor.setFont(new Font("Rockwell",0,18));
        btn_vendedor.setBackground(color_fondo);
        btn_vendedor.setContentAreaFilled(false);
        btn_vendedor.setForeground(color_letra);
        btn_vendedor.addActionListener(this);
        btn_vendedor.addMouseListener(this);
        btn_vendedor.setVisible(es_admin);
        panel_obc.add(btn_vendedor);
        
        btn_provedor=new JButton("Proveedores");
        btn_provedor.setBounds(0, dist*5, panel_obc.getWidth(), dist);
        btn_provedor.setBorder(null);
        btn_provedor.setFont(new Font("Rockwell",0,18));
        btn_provedor.setBackground(color_fondo);
        btn_provedor.setContentAreaFilled(false);
        btn_provedor.setForeground(color_letra);
        btn_provedor.addActionListener(this);
        btn_provedor.addMouseListener(this);
        btn_provedor.setVisible(es_admin);
        panel_obc.add(btn_provedor);
        
        btn_admin_user=new JButton("Admin. Usarios");
        btn_admin_user.setBounds(0, dist*6, panel_obc.getWidth(), dist);
        btn_admin_user.setBorder(null);
        btn_admin_user.setFont(new Font("Rockwell",0,18));
        btn_admin_user.setBackground(color_fondo);
        btn_admin_user.setContentAreaFilled(false);
        btn_admin_user.setForeground(color_letra);
        btn_admin_user.addActionListener(this);
        btn_admin_user.addMouseListener(this);
        btn_admin_user.setVisible(es_admin);
        panel_obc.add(btn_admin_user);
        
        btn_admin_producto=new JButton("Administrar");
        btn_admin_producto.setBounds(0, 0, panel_producto.getWidth(), panel_producto.getHeight()/2);
        btn_admin_producto.setBorder(null);
        btn_admin_producto.setFont(new Font("Rockwell",0,18));
        btn_admin_producto.setBackground(color_fondo);
        btn_admin_producto.setContentAreaFilled(false);
        btn_admin_producto.setForeground(color_letra);
        btn_admin_producto.addActionListener(this);
        btn_admin_producto.addMouseListener(this);
        btn_admin_producto.setVisible(es_admin);
        btn_admin_producto.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        panel_producto.add(btn_admin_producto);
        
        btn_avastecer_producto=new JButton("abastecer");
        btn_avastecer_producto.setBounds(0,  panel_producto.getHeight()/2, panel_producto.getWidth(), panel_producto.getHeight()/2);
        btn_avastecer_producto.setBorder(null);
        btn_avastecer_producto.setFont(new Font("Rockwell",0,18));
        btn_avastecer_producto.setBackground(color_fondo);
        btn_avastecer_producto.setContentAreaFilled(false);
        btn_avastecer_producto.setForeground(color_letra);
        btn_avastecer_producto.addActionListener(this);
        btn_avastecer_producto.addMouseListener(this);
        btn_avastecer_producto.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        panel_producto.add(btn_avastecer_producto);
        
        btn_codigo=new JButton("Codigo");
        btn_codigo.setBounds(0, dist*7, panel_obc.getWidth(), dist);
        btn_codigo.setBorder(null);
        btn_codigo.setFont(new Font("Rockwell",0,18));
        btn_codigo.setBackground(color_fondo);
        btn_codigo.setContentAreaFilled(false);
        btn_codigo.setForeground(color_letra);
        btn_codigo.addActionListener(this);
        btn_codigo.addMouseListener(this);
        btn_codigo.setVisible(es_admin);
        panel_obc.add(btn_codigo);
    }
    private void JPanelIniciar(){
        int posx, posy, p_base, p_altura;
        posx=0; posy=(base/7);
        p_base=base/4; p_altura=altura-(50+posy+102);//102 es el estpacio inferior
        
        panel_producto=new JPanel();
        panel_producto.setBounds(300, 380, 140, 70);
        panel_producto.setLayout(null);
        panel_producto.setBackground(new Color(22,100,189));
        panel_producto.setOpaque(true);
        panel_producto.setVisible(false);
        panel_producto.addMouseListener(this);
        panel_prinsipal.add(panel_producto);
        
        panel_obc=new JPanel();
        panel_obc.setBounds(posx, posy, p_base, p_altura);
        panel_obc.setLayout(null);
        panel_obc.setBackground(new Color(22,100,189));
        panel_obc.setOpaque(true);
        panel_prinsipal.add(panel_obc);
        
        posx=p_base; posy=0;
        p_base=base-(p_base+18); p_altura=altura-(47+posy);
        scroll_paneles=new JScrollPane();
        scroll_paneles.setBounds(posx, posy, p_base, p_altura);
        scroll_paneles.addMouseListener(this);
        panel_prinsipal.add(scroll_paneles);
    }
    @Override public void actionPerformed(ActionEvent e) {
        int base_panel=scroll_paneles.getWidth();
        if(e.getSource()==btn_main){
            scroll_paneles.setViewportView(new PanelPrincipal(base_panel,this));
        }if(e.getSource()==btn_venta){
            scroll_paneles.setViewportView(new PanelVenta(base_panel));
        }if(e.getSource()==btn_avastecer_producto){
            panel_producto.setVisible(false);
            scroll_paneles.setViewportView(new PanelProductoAbastecer(base_panel));
        }if(e.getSource()==btn_admin_producto){
            panel_producto.setVisible(false);
            scroll_paneles.setViewportView(new PanelProductoAdmin(base_panel));
        }if(e.getSource()==btn_boleta){
            scroll_paneles.setViewportView(new PanelBoleta(base_panel));
        }if(e.getSource()==btn_vendedor){
            scroll_paneles.setViewportView(new PanelVendedor(base_panel));
        }if(e.getSource()==btn_provedor){
            scroll_paneles.setViewportView(new PanelProvedor(base_panel));
        }if(e.getSource()==btn_admin_user){
            scroll_paneles.setViewportView(new PanelUsuarios(base_panel));
        }if(e.getSource()==btn_codigo){
            scroll_paneles.setViewportView(new PanelCodigo());
        }
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){
        boolean bandera=true;
        if(e.getSource()==btn_main){
            btn_main.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_venta){
            btn_venta.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_producto){
            panel_producto.setVisible(bandera);
            btn_producto.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_admin_producto){
            panel_producto.setVisible(bandera);
            btn_admin_producto.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_avastecer_producto){
            panel_producto.setVisible(bandera);
            btn_avastecer_producto.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_boleta){
            btn_boleta.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_vendedor){
            btn_vendedor.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_provedor){
            btn_provedor.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_admin_user){
            btn_admin_user.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_codigo){
            btn_codigo.setContentAreaFilled(bandera);
        }
    }
    @Override public void mouseExited(MouseEvent e) {
        boolean bandera=false;
        if(e.getSource()==btn_main){
            btn_main.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_venta){
            btn_venta.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_producto){
            panel_producto.setVisible(bandera);
            btn_producto.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_admin_producto){
            btn_admin_producto.setContentAreaFilled(bandera);
            panel_producto.setVisible(bandera);
        }if(e.getSource()==btn_avastecer_producto){
            btn_avastecer_producto.setContentAreaFilled(bandera);
            panel_producto.setVisible(bandera);
        }if(e.getSource()==btn_boleta){
            btn_boleta.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_vendedor){
            btn_vendedor.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_provedor){
            btn_provedor.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_admin_user){
            btn_admin_user.setContentAreaFilled(bandera);
        }if(e.getSource()==btn_codigo){
            btn_codigo.setContentAreaFilled(bandera);
        }
    }
}
