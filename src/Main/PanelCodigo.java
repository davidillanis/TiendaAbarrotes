package Main;

import BaseDatos.BaseDatos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/*@author LENOVO::DAVID ABEL*/
public class PanelCodigo extends JPanel{
    private JLabel label_title;
    private JTextPane text_codigo;
    private JButton btn_ejecutar;
    private JPopupMenu popup_menu;
    private JMenuItem item_ejecutar;
    
    public PanelCodigo() {
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
        setSize(900,700);
        JLabelIniciar();
        JTextPaneIniciar();
        JButtonInciar();
        JPopupMenuIniciar();
    }
    private void JLabelIniciar(){
        label_title=new JLabel("Codigo SQL");
        label_title.setFont(new Font("Rockwell",0,20));
        label_title.setForeground(Color.WHITE);
        label_title.setHorizontalAlignment(SwingConstants.CENTER);
        add(label_title,BorderLayout.NORTH);
    }
    private void JTextPaneIniciar(){
        text_codigo=new JTextPane();
        text_codigo.setFont(new Font("Rockwell",0,16));
        text_codigo.setForeground(new Color(20, 251, 255));
        text_codigo.setBackground(new Color(48,56,65));
        add(text_codigo,BorderLayout.CENTER);
    }
    private void JButtonInciar(){
        btn_ejecutar=new JButton("Ejecutar");
        btn_ejecutar.setBounds(0, 0, 100, 30);
        btn_ejecutar.setFont(new Font("Rockwell",0,18));
        btn_ejecutar.setBackground(Color.DARK_GRAY);
        btn_ejecutar.setForeground(Color.CYAN);
        btn_ejecutar.addActionListener((ActionEvent)->{BotonEjecutar();});
        add(btn_ejecutar,BorderLayout.SOUTH);
    }
    private void JPopupMenuIniciar(){
        popup_menu=new JPopupMenu();
        text_codigo.setComponentPopupMenu(popup_menu);
        
        item_ejecutar=new JMenuItem("Ejecutar");
        item_ejecutar.addActionListener((ActionEvent)->{BotonEjecutar();});
        popup_menu.add(item_ejecutar);
    }
    
    private void BotonEjecutar(){
        String txt="";
        txt+=text_codigo.getSelectedText();//getSelectedText:obtener el texto selecionado por el cursor
        if(txt.equals("null")){
            txt=text_codigo.getText();
        }
        try{
            BaseDatos bd=new BaseDatos();
            bd.EjecutarCodigo(txt);
            JOptionPane.showMessageDialog(null,"Ejecucion correcta");
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Algo fallo en el codigo");};
    }
}

