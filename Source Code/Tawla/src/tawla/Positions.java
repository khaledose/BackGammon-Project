package tawla;

import java.awt.*;

public class Positions {
    public static Point [][] Pos;
    public static int [][]sortedOshat;
    public static boolean [] availablePos;
    public Positions(){
        Pos = new Point[26][16];
        sortedOshat = new int[26][15];
        for(int i=0;i<26;i++)
            for(int j=0;j<16;j++)
                Pos[i][j] = new Point();
        for(int i=0;i<26;i++)
            for(int j=0;j<15;j++){
                if(i==0)
                    sortedOshat[i][j] = 14-j;
                else if(i==23)
                    sortedOshat[i][j] = 29-j;
                else if(i>0||i<26)
                    sortedOshat[i][j] = -1;
            }
        setupPositions();
        availablePos = new boolean[26];
        for(int i=0;i<26;i++)
            availablePos[i] = false;
    }
        public void setupPositions(){
    int n=902;
    for(int j=0;j<6;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (395*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[0][i-1].y+15*Dim.V_ratio);
        }
            n-=149;
    }
    n=406;
    for(int j=6;j<12;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (395*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[6][i-1].y+15*Dim.V_ratio);
        }
            n-=149;
    }
    n=33;
    for(int j=12;j<18;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (234*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[12][i-1].y-15*Dim.V_ratio);
        }
            n+=149;
    }
    n=530;
    for(int j=18;j<24;j+=2){
            Pos[j][0].x=(int) (n*Dim.H_ratio);
            Pos[j][0].y=(int) (234*Dim.V_ratio);
            for(int i=1;i<16;i++){
                Pos[j][i].x=(int) (n*Dim.H_ratio);
                Pos[j][i].y=(int) (Pos[18][i-1].y-15*Dim.V_ratio);
        }
            n+=149;
    }
    n=22;
    for(int j=1;j<24;j+=2){
        for(int i=0;i<16;i++){
            Pos[j][i].x=Pos[n][i].x;
            Pos[j][i].y=Pos[j-1][i].y;
        }
        n-=2;
    }
    Pos[24][0].x=(int) (468*Dim.H_ratio);
    Pos[24][0].y=(int) (391*Dim.V_ratio);
    for(int i=1;i<15;i++){
        Pos[24][i].x=(int) (468*Dim.H_ratio);
        Pos[24][i].y=(int) (Pos[24][0].y+15*Dim.V_ratio*i);
    }
    Pos[25][0].x=(int) (468*Dim.H_ratio);
    Pos[25][0].y=(int) (235*Dim.V_ratio);
    for(int i=1;i<15;i++){
        Pos[25][i].x=(int) (468*Dim.H_ratio);
        Pos[25][i].y=(int) (Pos[25][0].y-15*Dim.V_ratio*i);
    }
}

}
