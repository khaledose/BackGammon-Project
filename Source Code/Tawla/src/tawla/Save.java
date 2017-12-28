package tawla;

import java.awt.*;
import java.io.*;

public class Save implements Serializable{
    public Point [] posBlack,posWhite;
    public int [][] sortedOshat;
    public String lastPlayer,dice1,dice2;
    public int rolls;
    public boolean firstBlack,firstWhite;
    public int firstRollBlack,firstRollWhite,d1,d2,d3,d4,totalBlack,totalWhite;
    public int [] blackEnd,whiteEnd;
    public Save(int [][] sortedOshat,int rolls,boolean fb,boolean fw,int frb,int frw,String dice1,String dice2,int tw,int tb,int [] be,int []we){
        this.blackEnd = be;
        this.whiteEnd = we;
        this.totalBlack=tb;
        this.totalWhite=tw;
        this.dice1 = dice1;
        this.dice2 = dice2;
        firstBlack = fb;
        firstWhite = fw;
        firstRollBlack = frb;
        firstRollWhite = frw;
        this.posBlack = new Point[15];
        this.posWhite = new Point[15];
        for(int i=0;i<15;i++){
            this.posBlack[i] = new Point();
            this.posWhite[i] = new Point();
        }
        this.sortedOshat = new int[26][15];
        for(int i=0;i<26;i++)
            for(int j=0;j<15;j++)
                this.sortedOshat[i][j] = sortedOshat[i][j];
        this.lastPlayer=lastPlayer;
        this.rolls=rolls;
    }
    public void saveBlackPositions(int x, int y, int i){
        this.posBlack[i].x=x;
        this.posBlack[i].y=y;
    }
    public void saveWhitePositions(int x, int y, int i){
        this.posWhite[i].x=x;
        this.posWhite[i].y=y;
    }
}
