package Main;

import BaseDatos.Boleta;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PanelBoleta extends JPanel implements MouseListener{
    private JTable tabla_boleta;
    private JLabel label_titulo;
    private final Boleta boleta=new Boleta();
    private int base;
    
    public PanelBoleta(int base) {
        setLayout(null);
        setBackground(Color.BLUE);
        this.base=base;
        
        IniciarComponentes();
    }
    private void IniciarComponentes(){
        JLabelIniciar();
        JTableIniciar();
    }
    private void JLabelIniciar(){
        label_titulo=new JLabel("Visualizar Boletas");
        label_titulo.setFont(new Font("Cooper Black",0,26));
        label_titulo.setForeground(Color.WHITE);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0,30,base,30);
        add(label_titulo);
    }
    private void JTableIniciar(){
        int y=20, r, g, b;
        r=(int)(Math.random()*100+155);g=(int)(Math.random()*100+155);b=(int)(Math.random()*100+155);
        DefaultTableModel model=new DefaultTableModel();
        String []columna={"Codigo","Fecha Venta","Nombre Cliente","Vendido por"};
        for(String c:columna){
            model.addColumn(c);
        }
        
        tabla_boleta=new JTable(model);
        tabla_boleta.setFont(new Font("Times new Roman",0,20));
        tabla_boleta.setBackground(new Color(r,g,b));
        tabla_boleta.setForeground(Color.BLUE);
        tabla_boleta.setRowHeight(22);
        tabla_boleta.getTableHeader().setReorderingAllowed(false);//columnas estaticas
        tabla_boleta.setDefaultEditor(Object.class, null);//no se pueda editar el txt de las celdas
        tabla_boleta.setColumnSelectionAllowed(false);//Selecionar solo filas
        tabla_boleta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleccionar solo un datos
        tabla_boleta.addMouseListener(this);
        add(tabla_boleta);
        
        JScrollPane scroll=new JScrollPane(tabla_boleta);
        scroll.setBounds(0, y+50*2, base, 500);
        add(scroll);
        ActualizarTablaBoleta();
    }
    
    @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tabla_boleta){
            String codigo=tabla_boleta.getValueAt(tabla_boleta.getSelectedRow(), 0).toString();
            new BoletaVer(codigo).setVisible(true);
        }
    }
    
    private void ActualizarTablaBoleta(){
        String codigo, fecha, nombre, dni_usuario;
        TableModel m_eliminar=tabla_boleta.getModel();
        ((DefaultTableModel)m_eliminar).setRowCount(0);
        
        DefaultTableModel model=(DefaultTableModel)tabla_boleta.getModel();
        try {
            ResultSet rs=boleta.getBoleta();
            while(rs.next()){
                codigo=rs.getString(1);
                fecha=rs.getString(2);
                nombre=rs.getString(3);
                dni_usuario=rs.getString(4);
                model.addRow(new Object[]{codigo,fecha,nombre,dni_usuario});
            }
            tabla_boleta.revalidate();
        } catch (SQLException e) {}
    }
}
