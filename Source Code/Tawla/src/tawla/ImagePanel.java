package tawla;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class ImagePanel extends JPanel{
    public int x,y,w,h;
        private Image img=null;
        private String path;

        public ImagePanel(int x , int y,int w, int h, String path){
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.path=path;
            this.setOpaque(false);
        }
    @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            this.setSize(w,h);   
            if(img==null)
                img=getImage(path);
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(img,x,y,w,h,this);
        }

        public Image getImage(String path){
            Image temp=null;
            try{
                URL ImageURL = ImagePanel.class.getResource(path);
                temp=Toolkit.getDefaultToolkit().getImage(ImageURL);
            }
            catch(Exception e){
                System.out.println("error");
            }
            return temp;
        }
        public void setImage(String Path){
            img = null;
            this.path=Path;
        }
        public String getPath(){
            return path;
        }

}
