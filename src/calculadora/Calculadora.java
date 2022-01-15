package calculadora;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tania Fariña González
 */
public class Calculadora extends JFrame {
    LogicaCalculadora lc = new LogicaCalculadora();
    
    private JPanel buttonsPanel;
    private JPanel textPanel;
    
    private JTextField text;
    private JButton[] boton;
    
    private final String[] buttonStringNumbers = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "Clear", ",", "(", ")", "+", "-", "/", "*", "=", "+/-"};
    private String nest = "";
    
    
    private final int Height = 50;
    private final int Width = 80;
        
    public Calculadora() throws IOException{
        //super();
        ventana();
        panelText();
        paneslButtons();
        this.pack();
        this.setVisible(true);
    }
    
    private void ventana() throws IOException{
       this.setTitle("Calcular el Factorial de un número");
       int widthCenter = Toolkit.getDefaultToolkit().getScreenSize().width/3;
       int heightCenter = Toolkit.getDefaultToolkit().getScreenSize().height/3;
       this.setLocation(widthCenter, heightCenter);
       
       //El icono no funcionara en otro ordenador, pero no consigo que funcionen las rutas relativas en netbeasn windows
       BufferedImage img = ImageIO.read(new File("C:\\Users\\Usuario\\Documents\\CalculadoraDAD\\src\\icon\\descarga.png"));
       this.setIconImage(img);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    private void panelText(){
        this.textPanel = new JPanel();
        this.textPanel.setLayout(new GridLayout());
        this.text = new JTextField();
        this.text.setPreferredSize(new Dimension(Width*3, Height));
        this.textPanel.add(this.text);
        this.add(this.textPanel, BorderLayout.CENTER);
    }
    
    private void paneslButtons(){ 
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new GridLayout(4, 5, 5, 5));
        
        boton = new JButton[this.buttonStringNumbers.length];
        int count = 0;
        
        for(String i : this.buttonStringNumbers){
            this.boton[count] = new JButton(i);
            this.boton[count].setPreferredSize(new Dimension(Width, Height));
            this.buttonsPanel.add(boton[count]);
            
            ActionListener textListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if(i.equals("=")){ 
                        nest = lc.resultado(nest);
                        text.setText(lc.resultado(nest));
                    }
                    else if(i.equals("Clear")){
                        text.setText(null);
                        nest = "";
                    }
                    else if(i.equals("+/-")){
                        lc.setText(nest);
                        nest = lc.masMenos(nest);
                        text.setText(nest);
                        nest = "";
                    }
                    else if(i.equals(",")){
                        nest += ".";
                        text.setText(nest);
                    }
                    else{
                        nest += i;
                        text.setText(nest);
                    }
                }
            };
            
            this.boton[count].addActionListener(textListener);
            count++;
        }
        this.add(this.buttonsPanel,BorderLayout.SOUTH);
        
    }
    
    
}
