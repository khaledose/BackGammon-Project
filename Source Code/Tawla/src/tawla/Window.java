package tawla;

import java.awt.*;

public class Window extends ImagePanel{
    private Point [] btnPositions;
    public ImagePanel btn1,btn2;
    public Window(int x, int y, int w, int h, String path) {
        super(x, y, w, h, path);
        this.setLayout(null);
        btnPositions = new Point[3];
        for(int i=0;i<3;i++)
            btnPositions[i]= new Point();
        btn1 = new ImagePanel(0,0,(int)(188*Dim.H_ratio), (int) (65*Dim.V_ratio),"images/replayBtn1.png");
        btn2 = new ImagePanel(0,0,(int)(188*Dim.H_ratio), (int) (65*Dim.V_ratio),"images/exitbtn1.png");
        btnPositions[0].x=(int) (63*Dim.H_ratio);
        btnPositions[0].y=(int) (245*Dim.V_ratio);
        btnPositions[1].x=(int) (333*Dim.H_ratio);
        btnPositions[1].y=(int) (245*Dim.V_ratio);
        btn1.setSize((int)(188*Dim.H_ratio), (int) (65*Dim.V_ratio));
        btn2.setSize((int)(188*Dim.H_ratio), (int) (65*Dim.V_ratio));
        btn1.setLocation(btnPositions[1]);
        btn2.setLocation(btnPositions[0]);
        
        this.add(btn1);
        this.add(btn2);
    }
    
}
