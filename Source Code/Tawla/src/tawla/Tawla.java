package tawla;
import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class Dim{
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double V_ratio = screenSize.height/686.0;
    public static double H_ratio = screenSize.width/1200.0;
}
class TawlaFrame extends JFrame{
    public TawlaBoard  board;
    public StartMenu sm;
    public Dimension screenSize;
    private javax.swing.Timer t;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private static Save sg;
    public TawlaFrame(){
        init();
    }
    public void init(){
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tawla");
        this.setBounds(0,0,screenSize.width,screenSize.height);
        this.setUndecorated(true);
        Container c=this.getContentPane();
        sm=new StartMenu(0,0,screenSize.width,screenSize.height,"images/bg.png");
        sm.setBounds(0, 0, screenSize.width, screenSize.height);
        c.add(sm);
        c.setLayout(null);   
        t = new javax.swing.Timer(100,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(sm.newGamePressed){
                    try {
                        board=new TawlaBoard(0,0,screenSize.width,screenSize.height,"images/tawla.png",false);
                        board.setBounds(0,0,screenSize.width,screenSize.height);
                        board.setLayout(null);
                        c.add(board);
                        board.setVisible(true);
                        sm.setVisible(false);
                        t.stop();
                    } catch (Exception ex) {
                    }
                }
                if(sm.loadGamePressed){
                    try {
                        board=new TawlaBoard(0,0,screenSize.width,screenSize.height,"images/tawla.png",true);
                        board.setBounds(0,0,screenSize.width,screenSize.height);
                        board.setLayout(null);
                        c.add(board);
                        board.setVisible(true);
                        sm.setVisible(false);
                        t.stop();
                    } catch (Exception ex) {
                    }
                }
            }
        });
        t.start();
    }
}
public class Tawla {
    public static void main(String[] args) {
        TawlaFrame f = new TawlaFrame();
        f.setVisible(true);
    }
    
}
