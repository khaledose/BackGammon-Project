package tawla;

public class GameStates {
    public int d1,d2,d3,d4;
    public int [][] sortedOshat;
    public int state;
    public GameStates(int a,int b, int c, int d, int [][]so, int st){
        d1=a;
        d2=b;
        d3=c;
        d4=d;
        sortedOshat=so;
        state=st;
    }
}
