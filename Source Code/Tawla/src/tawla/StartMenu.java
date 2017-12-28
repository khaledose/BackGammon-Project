package tawla;

import java.awt.event.*;
public class StartMenu extends ImagePanel{
    private ImagePanel newGame,loadGame,exit;
    public boolean newGamePressed=false,loadGamePressed=false,exitPressed=false;
    public StartMenu(int x , int y,int w, int h, String path){
        super(x,y,w,h,path);
        newGame = new ImagePanel(0,0,(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio),"images/newgame1.png");
        loadGame = new ImagePanel(0,0,(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio),"images/loadgame1.png");
        exit = new ImagePanel(0,0,(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio),"images/exitbtn1.png");
        newGame.setBounds((int)(446*Dim.H_ratio),(int)(221.0*Dim.V_ratio),(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio));
        loadGame.setBounds((int)(446*Dim.H_ratio),(int)(311.0*Dim.V_ratio),(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio));
        exit.setBounds((int)(446*Dim.H_ratio),(int)(401.0*Dim.V_ratio),(int)(314.0*Dim.H_ratio),(int)(108.0*Dim.V_ratio));
        this.setLayout(null);
        this.add(exit);
        this.add(loadGame);
        this.add(newGame);
        newGame.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            repaint(newGame,"images/newgame3.png");
            newGamePressed=true;
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(newGame,"images/newgame2.png");
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(newGame,"images/newgame2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(newGame,"images/newgame1.png");
        }
    });
        loadGame.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            repaint(loadGame,"images/loadgame3.png");
            loadGamePressed=true;
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(loadGame,"images/loadgame2.png");
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(loadGame,"images/loadgame2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(loadGame,"images/loadgame1.png");
        }
    });
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            repaint(exit,"images/exitbtn3.png");
            exitPressed=true;
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(exit,"images/exitbtn2.png");
            System.exit(0);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(exit,"images/exitbtn2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(exit,"images/exitbtn1.png");
        }
    });
    }
    public void repaint(ImagePanel pnl ,String path){
    pnl.setImage(path);
    pnl.repaint();
}
}
