package tawla;

import java.awt.*;

public class Oshat extends ImagePanel{
    public String oshatType;
    public Point currentPosition;
    public int ID;
    public boolean killed = false;
    public Oshat(int x, int y, int w, int h, String path, String oshatType, int ID) {
        super(x, y, w, h, path);
        this.oshatType = oshatType;
        this.ID = ID;        
        currentPosition = new Point();
        if(oshatType=="B"){
            currentPosition.x = 0;
            currentPosition.y = 14-this.ID;
        }
        else if(oshatType=="W"){
            currentPosition.x = 23;
            currentPosition.y = 14-this.ID;
            this.ID=ID+15;
        }
           
    }
}
