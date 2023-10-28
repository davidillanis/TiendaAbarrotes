package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Padre extends JFrame {

    protected final int base, altura;
    protected final Color color_fondo;
    private Color color_letras = Color.RED;
    protected JLabel label_titulo;
    protected JPanel panel_prinsipal;
    private Font font_letras = new Font("Times new Roman", 0, 20);

    public Padre() {
        this.base = 900;
        this.altura = 600;
        this.color_fondo = Color.LIGHT_GRAY;
        IniciarComponentes();
    }

    public Padre(Color fondo) {
        this.base = 900;
        this.altura = 600;
        this.color_fondo = fondo;
        IniciarComponentes();
    }

    public Padre(int base, int altura) {
        this.base = base;
        this.altura = altura;
        this.color_fondo = Color.LIGHT_GRAY;
        IniciarComponentes();
    }

    public Padre(int base, int altura, Color fondo) {
        this.base = base;
        this.altura = altura;
        this.color_fondo = fondo;
        IniciarComponentes();
    }
    public Padre(Dimension dimencion){
        this.base = (int)(dimencion.getWidth());
        this.altura = (int)(dimencion.getHeight());
        this.color_fondo = Color.LIGHT_GRAY;
        IniciarComponentes();
    }

    private void IniciarComponentes() {
        this.setSize(base, altura);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanelIniciar();
    }
    protected JLabel getLabelTitulo(){
        return label_titulo;
    }
    protected void JLabelTitulo(String titulo) {
        label_titulo = new JLabel(titulo);
        label_titulo.setBounds(0, 0, base, 40);
        label_titulo.setHorizontalAlignment(JLabel.CENTER);
        label_titulo.setFont(font_letras);
        label_titulo.setForeground(color_letras);
        panel_prinsipal.add(label_titulo);
    }

    protected void setFontLetras(String letra, int stilo, int tamanio) {
        font_letras = new Font(letra, stilo, tamanio);
    }

    protected void setFontLetras(String letra) {
        letra=letra.trim();
        font_letras = new Font(letra, 0, 20);
    }

    protected void setFontLetras(int stilo) {
        font_letras = new Font("Times new Roman", stilo, 20);
    }

    protected void setFontLetras(double tamanio) {
        font_letras = new Font("Times new Roman", 0, (int) (tamanio));
    }
    
    protected void setColorLetras(Color color){
        color_letras=color;
    }
    protected void setColorLetras(int r, int g, int b){
        color_letras=new Color(r, g, b);
    }

    protected void setFondo(Color color){
        label_titulo.setOpaque(true);
        label_titulo.setBackground(color);
    }
    
    private void JPanelIniciar() {
        panel_prinsipal = new JPanel();
        this.getContentPane().add(panel_prinsipal);
        panel_prinsipal.setOpaque(true);
        panel_prinsipal.setLayout(null);
        panel_prinsipal.setBackground(color_fondo);
    }
}
