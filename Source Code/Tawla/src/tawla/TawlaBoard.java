package tawla;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
enum Player{black,white};
public class TawlaBoard extends ImagePanel{
    public int d1=0,d2=0,d3=0,d4=0,altD1=0,altD2=0,altD3=0,altD4=0;
    public static int activeOshat;
    private ImagePanel rollBtn,dice1,dice2,dice1Gif,dice2Gif,exit,replay,save;
    private ImagePanel [][] activatedOshat;
    private JLabel player1,player2,score1,score2;
    private Font f1;
    private Random rand;
    private javax.swing.Timer t;
    private Oshat [] blackOshat ,whiteOshat;
    private Positions ps;
    private static Player p;
    private boolean isEqual=false;
    private boolean firstBlack = false, firstWhite = false,fbm=false,fwm=false;
    private Save so;
    int firstRollWhite = 0, firstRollBlack=0,fbo=-1,fwo=-1;
    static int rolls = 0;
    private int [] blackEnd,whiteEnd;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private File f;
    private int totalBlack=15,totalWhite=15;
    private ArrayList states;
    private int state=0;
    private Window win1,win2;
public TawlaBoard(int x , int y,int w, int h, String path,boolean load) throws Exception{
    super(x,y,w,h,path);
    this.setLayout(null);
    ps = new Positions();
    f = new File("save.kh");
    exit = new ImagePanel(0,0,(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio),"images/exit1.png");
    replay = new ImagePanel(0,0,(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio),"images/replay1.png");
    save = new ImagePanel(0,0,(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio),"images/save1.png");
    rollBtn = new ImagePanel(0,0, (int) (90*Dim.H_ratio), (int) (90*Dim.V_ratio),"images/roll.png");
    dice1 = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice1.png");
    dice2 = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice1.png");
    dice1Gif = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice1.gif");
    dice2Gif = new ImagePanel(0,0,(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio),"images/dice2.gif");
    win1 = new Window(0,0,(int)(583*Dim.H_ratio),(int)(338*Dim.V_ratio),"images/p1win.png");
    win2 = new Window(0,0,(int)(583*Dim.H_ratio),(int)(338*Dim.V_ratio),"images/p2win.png");
    states = new ArrayList();
    states.add(new GameStates(d1,d2,d3,d4,Positions.sortedOshat,state));
    dice1Gif.setOpaque(false);
    activatedOshat = new ImagePanel[26][15];
    blackEnd = new int[6];
    whiteEnd = new int[6];
    for(int i=0;i<6;i++){
        blackEnd[i]=0;
        whiteEnd[i]=0;
    }
    for(int i=0;i<activatedOshat.length;i++)
        for(int j=0;j<activatedOshat[i].length;j++)
            activatedOshat[i][j] = new ImagePanel(0,0,(int)(63.0*Dim.H_ratio),(int)(64.0*Dim.V_ratio),"images/activatedOshat.png");
    for(int i=0;i<activatedOshat.length;i++)
        for(int j=0;j<activatedOshat[i].length;j++)
            activatedOshat[i][j].setBounds(Positions.Pos[i][j].x,Positions.Pos[i][j].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    for(int i=0;i<activatedOshat.length;i++)
        for(int j=0;j<activatedOshat[i].length;j++)
            activatedOshat[i][j].setVisible(false);
    blackOshat = new Oshat[15];
    whiteOshat = new Oshat[15];
    for(int i=0;i<blackOshat.length;i++)
        blackOshat[i] = new Oshat(0,0,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio),"images/2oshat_eswd.png","B",i);
    for(int i=0;i<whiteOshat.length;i++)
        whiteOshat[i] = new Oshat(0,0,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio),"images/2oshat_abyd.png","W",i);
    player1 = new JLabel("PLAYER 1");
    player2 = new JLabel("PLAYER 2");
    score1 = new JLabel("0");
    score2 = new JLabel("0");
    rand=new Random();
    f1 = new Font(player1.getText(),Font.BOLD,40);
    win1.setBounds((int) (301*Dim.H_ratio), (int) (171*Dim.V_ratio), (int)(583*Dim.H_ratio),(int)(338*Dim.V_ratio));
    win2.setBounds((int) (301*Dim.H_ratio), (int) (171*Dim.V_ratio), (int)(583*Dim.H_ratio),(int)(338*Dim.V_ratio));
    win1.setVisible(false);
    win2.setVisible(false);
    player1.setFont(f1);
    player2.setFont(f1);
    score1.setFont(f1);
    score2.setFont(f1);
    player1.setBounds((int)(1025.0*Dim.H_ratio),(int)(240.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    score1.setBounds((int)(1078.0*Dim.H_ratio),(int)(285.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    player2.setBounds((int)(1025.0*Dim.H_ratio),(int)(340.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    score2.setBounds((int)(1078.0*Dim.H_ratio),(int)(385.0*Dim.V_ratio),(int)(174.0*Dim.H_ratio),(int)(44.0*Dim.V_ratio));
    player1.setForeground(new Color(225,178,124));
    score1.setForeground(new Color(225,178,124));
    player2.setForeground(new Color(78,49,7));
    score2.setForeground(new Color(78,49,7));
    rollBtn.setBounds((int)(1047.0*Dim.H_ratio), (int) (120.0*Dim.V_ratio), (int) (90*Dim.H_ratio), (int) (90*Dim.V_ratio));
    dice1.setBounds((int)(1000*Dim.H_ratio),(int)(25*Dim.V_ratio),(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio));
    dice2.setBounds((int)(1095*Dim.H_ratio),(int)(25*Dim.V_ratio),(int)(90.0*Dim.H_ratio),(int)(90.0*Dim.V_ratio));
    exit.setBounds((int)(1123*Dim.H_ratio),(int)(598.0*Dim.V_ratio),(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio));
    replay.setBounds((int)(1061*Dim.H_ratio),(int)(598.0*Dim.V_ratio),(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio));
    save.setBounds((int)(990*Dim.H_ratio),(int)(598.0*Dim.V_ratio),(int)(71.0*Dim.H_ratio),(int)(67.0*Dim.V_ratio));
    for(int i=0;i<blackOshat.length;i++)
        blackOshat[i].setBounds(Positions.Pos[0][i].x,Positions.Pos[0][i].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    for(int i=0;i<whiteOshat.length;i++)
        whiteOshat[i].setBounds(Positions.Pos[23][i].x,Positions.Pos[23][i].y,(int)(63*Dim.H_ratio),(int)(64*Dim.V_ratio));
    exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(exit,"images/exit3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible()){
            repaint(exit,"images/exit2.png");
            System.exit(0);
            }
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(exit,"images/exit2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(exit,"images/exit1.png");
        }
    });
    replay.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(replay,"images/replay3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(replay,"images/replay2.png");
            replayGame();
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(replay,"images/replay2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(replay,"images/replay1.png");
        }
    });
    save.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(save,"images/save3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible()){
            repaint(save,"images/save2.png");
            try {
                saveGame();
            } catch (Exception ex) {
            }
            }
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(save,"images/save2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(save,"images/save1.png");
        }
    });
    win1.btn1.addMouseListener(new MouseAdapter(){
    @Override
        public void mousePressed(MouseEvent me) {
            repaint(win1.btn1,"images/replayBtn3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(win1.btn1,"images/replayBtn2.png");
            replayGame();
            win1.setVisible(false);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(win1.btn1,"images/replayBtn2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(win1.btn1,"images/replayBtn1.png");
        }
    });
    win1.btn2.addMouseListener(new MouseAdapter(){
    @Override
        public void mousePressed(MouseEvent me) {
            repaint(win1.btn2,"images/exitbtn3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(win1.btn2,"images/exitbtn2.png");
            System.exit(0);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(win1.btn2,"images/exitbtn2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(win1.btn2,"images/exitbtn1.png");
        }
    });
    win2.btn1.addMouseListener(new MouseAdapter(){
    @Override
        public void mousePressed(MouseEvent me) {
            repaint(win2.btn1,"images/replayBtn3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(win2.btn1,"images/replayBtn2.png");
            replayGame();
            win2.setVisible(false);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(win2.btn1,"images/replayBtn2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(win2.btn1,"images/replayBtn1.png");
        }
    });
    win2.btn2.addMouseListener(new MouseAdapter(){
    @Override
        public void mousePressed(MouseEvent me) {
            repaint(win2.btn2,"images/exitbtn3.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            repaint(win2.btn2,"images/exitbtn2.png");
            System.exit(0);
        }
        @Override
        public void mouseEntered(MouseEvent me) {
            repaint(win2.btn2,"images/exitbtn2.png");
        }
        @Override
        public void mouseExited(MouseEvent me) {
            repaint(win2.btn2,"images/exitbtn1.png");
        }
    });
    rollBtn.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent me){
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(rollBtn,"images/roll2.png");
        }
        @Override
        public void mouseExited(MouseEvent me){
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(rollBtn,"images/roll.png");
        }
        @Override
        public void mousePressed(MouseEvent me){
            if(!win1.isVisible()&&!win2.isVisible())
            repaint(rollBtn,"images/roll1.png");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            if(!win1.isVisible()&&!win2.isVisible()){
                repaint(rollBtn,"images/roll2.png");
            int sum1 = 0,sum2=0;
            for(int i=0;i<6;i++){
                sum1+=whiteEnd[i];
                sum2+=blackEnd[i];
            }
            if(sum2!=totalBlack&&p==Player.black){
                checkNoMove(blackOshat);
            }
            else if(sum1!=totalWhite&&p==Player.white)
                checkNoMove(whiteOshat);
            if(d1==0&&d2==0&&d3==0&&d4==0){
                rolls++;
            if(isEqual)
                isEqual=false;
            for(int i=0;i<Positions.availablePos.length;i++)
                Positions.availablePos[i]=false;
            for(int i=0;i<activatedOshat.length;i++)
                for(int j=0;j<activatedOshat[i].length;j++)
                    activatedOshat[i][j].setVisible(false);
            
            repaint(dice1,"images/dice1.gif");
            repaint(dice2,"images/dice2.gif");
            if(t==null)
            t = new javax.swing.Timer(1000,new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    d1=rand.nextInt(6)+1;
                    d2=rand.nextInt(6)+1;
                    altD1=d1;altD2=d2;altD3=d3;altD4=d4;
                    t.stop();                    
                    repaint(dice1,"images/dice"+d1+".png");
                    repaint(dice2,"images/dice"+d2+".png");
                    if(d1==d2){
                        isEqual=true;
                        d3=d1;
                        d4=d1;
                    }
                    if(rolls==1){
                        switchPlayer(1);
                        firstRollBlack = d1+d2;
                        d1=0;
                        d2=0;
                        d3=0;
                        d4=0;
                    }
                    else if(rolls==2){
                        switchPlayer(0);
                        firstRollWhite = d1+d2;
                        
                        d1=0;
                        d2=0;
                        d3=0;
                        d4=0;
                        if(firstRollBlack==firstRollWhite){
                            rolls=0;
                            firstRollBlack=0;
                            firstRollWhite=0;
                        }
                    }
                    else if(rolls==3){
                        if(firstRollBlack>firstRollWhite)
                            switchPlayer(1);
                        else
                            switchPlayer(0);
                    }
                    else if(rolls>3){
                        if(p==Player.black){
                            switchPlayer(0);
                        }
                        else if(p==Player.white){
                            switchPlayer(1);
                        }
                    }
                    if(p==Player.white){
                        d1*=-1;
                        d2*=-1;
                        d3*=-1;
                        d4*=-1;
                    }
                }            
            });
            t.start();
            } 
        }
        }
    });
    
    for(int i=0;i<blackOshat.length;i++){
        final int n = i;
        blackOshat[i].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){
                if(p==Player.black&&(d1!=0||d2!=0||d3!=0||d4!=0))
                    repaint(blackOshat[n],"images/HoveredOshat.png");
            }
            @Override
            public void mouseExited(MouseEvent me){
                if(p==Player.black)
                    repaint(blackOshat[n],"images/2oshat_eswd.png");
            }
            @Override
            public void mouseClicked(MouseEvent me) {
                int sum=0;
                for(int i=0;i<6;i++)
                    sum+=blackEnd[i];
                if(sum==totalBlack)
                    collectOshat(n);
                else{
                if(p==Player.black&&!firstBlack&&n==0&&(d1!=0||d2!=0||d3!=0||d4!=0)){
                    repaint(blackOshat[n],"images/HoveredOshat.png");
                    configureOshat(blackOshat,n);
                }
                else if(p==Player.black&&firstBlack&&(d1!=0||d2!=0||d3!=0||d4!=0)){
                    repaint(blackOshat[n],"images/HoveredOshat.png");
                    configureOshat(blackOshat,n);
                }
                }
            }
        });
    }
        for(int i=0;i<whiteOshat.length;i++){
            final int n = i;
        whiteOshat[i].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){
                if(p==Player.white&&(d1!=0||d2!=0||d3!=0||d4!=0))
                    repaint(whiteOshat[n],"images/HoveredOshat.png");
            }
            @Override
            public void mouseExited(MouseEvent me){
                if(p==Player.white)
                    repaint(whiteOshat[n],"images/2oshat_abyd.png");
            }
            @Override
            public void mouseClicked(MouseEvent me) {
                int sum = 0;
                for(int i=0;i<6;i++)
                    sum+=whiteEnd[i];
                if(sum==totalWhite)
                    collectOshat(n);
                else{
                if(p==Player.white&&!firstWhite&&n==0&&(d1!=0||d2!=0||d3!=0||d4!=0)){
                    repaint(whiteOshat[n],"images/HoveredOshat.png");
                    configureOshat(whiteOshat,n);
                }
                else if(p==Player.white&&firstWhite&&(d1!=0||d2!=0||d3!=0||d4!=0)){
                    repaint(whiteOshat[n],"images/HoveredOshat.png");
                    configureOshat(whiteOshat,n);
                }
                }
            }
        });
        }
    this.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent me) {
            deactivateOshat();
        }
    });
    for(int i=0;i<activatedOshat.length;i++){
        for(int j=0;j<activatedOshat[i].length;j++){
        final int n = i, m = j;
        activatedOshat[i][j].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                if(activatedOshat[n][m].isVisible()){
                    if(p==Player.black){
                        limitMovement(blackOshat[activeOshat],n,m);
                        if(blackOshat[0].currentPosition.x>17)
                            firstBlack=true;
                    }
                    else if(p==Player.white){
                        limitMovement(whiteOshat[activeOshat],n,m);    
                        if(whiteOshat[0].currentPosition.x<6)
                            firstWhite=true;
                    }
                }     
        }
        });
        }
    }
    this.add(rollBtn);
    this.add(dice1);
    this.add(dice2);
    this.add(exit);
    this.add(replay);
    this.add(save);
    this.add(player1);
    this.add(player2);
    this.add(score1);
    this.add(score2);
    this.add(win1);
    this.add(win2);
    for(int i=0;i<activatedOshat.length;i++)
        for(int j=0;j<activatedOshat[i].length;j++)
            this.add(activatedOshat[i][j]);
    for(int i=0;i<15;i++){
        this.add(blackOshat[i]);
        this.add(whiteOshat[i]);
    }
    if(load){
        loadGame();
    }
} 
public void repaint(ImagePanel pnl ,String path){
    pnl.setImage(path);
    pnl.repaint();
}
public void activateOshat(int n){
    this.activeOshat=n;
}
public void deactivateOshat(){
    for(int i=0;i<activatedOshat.length;i++)
        for(int j=0;j<activatedOshat[i].length;j++)
            activatedOshat[i][j].setVisible(false);
}
public void limitMovement(Oshat o,int n,int m){
    Positions.sortedOshat[o.currentPosition.x][o.currentPosition.y] = -1;
    int bx = o.currentPosition.x;
    if(isEqual){
        if(n==o.currentPosition.x+d1){
            if(d4==0&&d3==0&&d2==0)
                d1=0;
            if(d4==0&&d3==0)
                d2=0;
            if(d4==0)
                d3=0;
            d4=0;
        }
    else if(n==o.currentPosition.x+(d1+d2)){
        if(d3==0&&d4==0){
            d2=0;
            d1=0;
        }
        if(d4==0){
            d3=0;
            d2=0;
        }
        d3=0;
        d4=0;
    }
    else if(n==o.currentPosition.x+(d1+d2+d3)){
        if(d4==0){
            d1=0;
            d2=0;
            d3=0;
        }
        d4=0;
        d3=0;
        d2=0;
    }
    else if(n==o.currentPosition.x+(d1+d2+d3+d4)){
            d1=0;
            d2=0;
            d3=0;
            d4=0;
    }
    }
    else if(!isEqual){
        if(n==o.currentPosition.x+d1){
            d1=0;
        }
        if(n==o.currentPosition.x+d2)
            d2=0;
        if(n==o.currentPosition.x+(d1+d2)){
            d1=0;
            d2=0;
        }
    }
        o.currentPosition.y=m;
    o.setLocation(Positions.Pos[n][m].x,Positions.Pos[n][m].y);
    o.currentPosition.x+=n-o.currentPosition.x;
    Positions.sortedOshat[o.currentPosition.x][o.currentPosition.y] = o.ID;
    sortOshat();
    for(int i=0;i<6;i++){
        blackEnd[i]=0;
        whiteEnd[i]=0;
    }
    for(int i=0;i<6;i++)
        for(int j=0;j<15;j++){
            if(Positions.sortedOshat[i][j]>14)
                whiteEnd[i]++;
            if(Positions.sortedOshat[23-i][j]>-1&&Positions.sortedOshat[23-i][j]<15)
                blackEnd[i]++;
        }
    deactivateOshat();
    for(int i=0;i<26;i++)
        Positions.availablePos[i]=false;
    Positions.availablePos[n]=false;
    
}
public boolean checkLocation(int a,int b){
        if(a+b>23||a+b<0)
            return false;
        return true;
}
public boolean check(int n , int a){
    if(n==0)
        return false;
    if(p==Player.black){
        if(blackOshat[a].currentPosition.x+n>23)
            return false;
        for(int i=0;i<whiteOshat.length;i++)
            if(whiteOshat[i].currentPosition.x==blackOshat[a].currentPosition.x+n)
            return false;
        return true;
    }
    else if(p==Player.white){
        if(whiteOshat[a].currentPosition.x+n<0)
            return false;
        for(int i=0;i<blackOshat.length;i++)
            if(blackOshat[i].currentPosition.x==whiteOshat[a].currentPosition.x+n)
            return false;
        return true;
    }
    return true;
}

public void configureOshat(Oshat [] o,int n){
                deactivateOshat();
                activateOshat(n);
                int a=-1,b=-1,c=-1;
                int a1=-1,b1=-1,c1=-1,e1=-1;
                int bx = o[activeOshat].currentPosition.x;
                if(d1!=0||d2!=0||d3!=0||d4!=0){
                    if(checkLocation(bx,d1)&&d1!=0)
                        Positions.availablePos[bx+d1]=check(d1,activeOshat);
                    if(checkLocation(bx,d2)&&d2!=0)
                        Positions.availablePos[bx+d2]=check(d2,activeOshat);
                    if(checkLocation(bx,d2+d1)&&d1!=0&&d2!=0&&(check(d2,activeOshat)||check(d1,activeOshat)))
                        Positions.availablePos[bx+d1+d2]=check(d1+d2,activeOshat);
                    for(int i=14;i>=0;i--){
                        if(d1!=0&&checkLocation(bx,d1)&&Positions.sortedOshat[bx+d1][i]==-1&&a==-1)
                            a=i;
                        if(d2!=0&&checkLocation(bx,d2)&&Positions.sortedOshat[bx+d2][i]==-1&&b==-1)
                            b=i;
                        if(d1+d2!=0&&checkLocation(bx,d1+d2)&&Positions.sortedOshat[bx+d1+d2][i]==-1&&c==-1)
                            c=i;
                        if(a!=-1&&b!=-1&&c!=-1)
                            break;
                    }
                    if(isEqual){
                        if(checkLocation(bx,d1)&&d1!=0)
                            Positions.availablePos[bx+d1]=check(d1,activeOshat);
                        if(checkLocation(bx,d1+d2)&&d2!=0&&check(d1,activeOshat))
                            Positions.availablePos[bx+d1+d2]=check(d1+d2,activeOshat);
                        if(checkLocation(bx,d1+d2+d3)&&d3!=0&&check(d1,activeOshat)&&check(d2,activeOshat))
                            Positions.availablePos[bx+d1+d2+d3]=check(d1+d2+d3,activeOshat);
                        if(checkLocation(bx,d1+d2+d3+d4)&&d4!=0&&check(d1,activeOshat)&&check(d2,activeOshat)&&check(d3,activeOshat))
                            Positions.availablePos[bx+d1+d2+d3+d4]=check(d1+d2+d3+d4,activeOshat);
                        for(int i=14;i>=0;i--){
                            if(d1!=0&&checkLocation(bx,d1)&&Positions.sortedOshat[bx+d1][i]==-1&&a1==-1)
                                a1=i;
                            if(d1!=0&&d2!=0&&checkLocation(bx,d1+d2)&&Positions.sortedOshat[bx+d1+d2][i]==-1&&b1==-1)
                                b1=i;
                            if(d1!=0&&d2!=0&&d3!=0&&checkLocation(bx,d1+d2+d3)&&Positions.sortedOshat[bx+d1+d2+d3][i]==-1&&c1==-1)
                                c1=i;
                            if(d4!=0&&d1!=0&&d2!=0&&d3!=0&&checkLocation(bx,d4+d1+d2+d3)&&Positions.sortedOshat[bx+d4+d1+d2+d3][i]==-1&&e1==-1)
                                e1=i;
                            if(a1!=-1&&b1!=-1&&c1!=-1&&e1!=-1)
                                break;
                        }
                        if(e1!=-1&&bx+d1+d2+d3+d4!=bx&&check(d1,activeOshat)&&check(d1+d2,activeOshat)&&check(d1+d2+d3,activeOshat)&&check(d1+d2+d3+d4,activeOshat)&&checkLocation(bx,d1+d2+d3+d4)&&Positions.availablePos[bx+d1+d2+d3+d4])
                            activatedOshat[bx+d1+d2+d3+d4][e1].setVisible(true); 
                        if(c1!=-1&&bx+d1+d2+d3!=bx&&check(d1,activeOshat)&&check(d1+d2,activeOshat)&&check(d1+d2+d3,activeOshat)&&checkLocation(bx,d1+d2+d3)&&Positions.availablePos[bx+d1+d2+d3])
                            activatedOshat[bx+d1+d2+d3][c1].setVisible(true); 
                        if(b1!=-1&&bx+d1+d2!=bx&&check(d1,activeOshat)&&check(d1+d2,activeOshat)&&checkLocation(bx,d1+d2)&&Positions.availablePos[bx+d1+d2])
                            activatedOshat[bx+d1+d2][b1].setVisible(true); 
                        if(a1!=-1&&bx+d1!=bx&&checkLocation(bx,d1)&&Positions.availablePos[bx+d1])
                            activatedOshat[bx+d1][a1].setVisible(true);    
                    }
                    else if(!isEqual){
                    if(a!=-1&&bx+d1!=bx&&check(d1,activeOshat)&&checkLocation(bx,d1)&&Positions.availablePos[bx+d1]){
                        activatedOshat[bx+d1][a].setVisible(true);        
                    }
                    if(b!=-1&&bx+d2!=bx&&check(d2,activeOshat)&&checkLocation(bx,d2)&&Positions.availablePos[bx+d2]){
                        activatedOshat[bx+d2][b].setVisible(true);        
                    }
                    if(c!=-1&&bx+d1+d2!=bx&&check(d1+d2,activeOshat)&&checkLocation(bx,d1+d2)&&Positions.availablePos[bx+d1+d2]){
                        activatedOshat[bx+d1+d2][c].setVisible(true);        
                    }
                }
                }
    
}
public void checkNoMove(Oshat [] o){
    
    deactivateOshat();
    int bx;
    if(!firstBlack&&p==Player.black){
        bx=blackOshat[0].currentPosition.x;
        if(checkLocation(d1,bx)&&checkLocation(d2,bx)&&(check(d1,0)||check(d2,0)))
            return;
        else{
            d1=0;
            d2=0;
            d3=0;
            d4=0;
        }
    }
    else if(!firstWhite&&p==Player.white){
        bx=whiteOshat[0].currentPosition.x;
        if(checkLocation(d1,bx)&&checkLocation(d2,bx)&&(check(d1,0)||check(d2,0))) 
            return;
        else{
            d1=0;
            d2=0;
            d3=0;
            d4=0;
        }
    }
    else if((firstWhite&&p==Player.white)||(firstBlack&&p==Player.black)){
    for(int i=0;i<o.length;i++){
        bx = o[i].currentPosition.x;
         if(checkLocation(d1,bx)&&checkLocation(d2,bx)&&(check(d1,i)||check(d2,i)))
             return;
         else
             continue;
    }
    d1=0;
    d2=0;
    d3=0;
    d4=0;
    }
}
public void switchPlayer(int set){
    if(set==0){
        player2.setForeground(new Color(225,178,124));
        score2.setForeground(new Color(225,178,124));
        player1.setForeground(new Color(78,49,7));
        score1.setForeground(new Color(78,49,7));
        p=Player.white;
    }
    else if(set==1){
        player1.setForeground(new Color(225,178,124));
        score1.setForeground(new Color(225,178,124));
        player2.setForeground(new Color(78,49,7));
        score2.setForeground(new Color(78,49,7));
        p=Player.black;
    }
}
public void sortOshat(){
    for(int i=0;i<26;i++){
        Arrays.sort(Positions.sortedOshat[i]);
    }
    for(int i=0;i<26;i++){
        for(int j=0;j<blackOshat.length;j++){
            if(blackOshat[j].currentPosition.x==i){
                for(int k=0;k<15;k++){
                    if(blackOshat[j].ID==Positions.sortedOshat[i][k]){
                        blackOshat[j].currentPosition.y=k;
                        blackOshat[j].setLocation(Positions.Pos[blackOshat[j].currentPosition.x][blackOshat[j].currentPosition.y].x,Positions.Pos[blackOshat[j].currentPosition.x][blackOshat[j].currentPosition.y].y);
                    }
                }
            }
        }
        for(int j=0;j<whiteOshat.length;j++){
            if(whiteOshat[j].currentPosition.x==i){
                for(int k=0;k<15;k++){
                    if(whiteOshat[j].ID==Positions.sortedOshat[i][k]){
                        whiteOshat[j].currentPosition.y=k;
                        whiteOshat[j].setLocation(Positions.Pos[whiteOshat[j].currentPosition.x][whiteOshat[j].currentPosition.y].x,Positions.Pos[whiteOshat[j].currentPosition.x][whiteOshat[j].currentPosition.y].y);
                    }
                }
            }
        }
    }
}
public void collectOshat(int n){
        if(p==Player.black){
            
            if(d4!=0&&blackOshat[n].currentPosition.x==23-(d4-1)&&blackEnd[d4-1]>0){
                collect1(d4-1,n,blackEnd,blackOshat[n]);
                d4=0;
            }
            else if(d3!=0&&blackOshat[n].currentPosition.x==23-(d3-1)&&blackEnd[d3-1]>0){
                collect1(d3-1,n,blackEnd,blackOshat[n]);
                d3=0;
            }
            else if(d2!=0&&blackOshat[n].currentPosition.x==23-(d2-1)&&blackEnd[d2-1]>0){
                collect1(d2-1,n,blackEnd,blackOshat[n]);
                d2=0;
            }
            else if(d1!=0&&blackOshat[n].currentPosition.x==23-(d1-1)&&blackEnd[d1-1]>0){
                collect1(d1-1,n,blackEnd,blackOshat[n]);
                d1=0;
            }
            else if(d4!=0&&collect2(blackEnd,d4-1)>=0){
                checkNoMove(blackOshat);
                configureOshat(blackOshat,n);
                
            }
            else if(d3!=0&&collect2(blackEnd,d3-1)>=0){
                checkNoMove(blackOshat);
                configureOshat(blackOshat,n);
            }
            else if(d2!=0&&collect2(blackEnd,d2-1)>=0){
                checkNoMove(blackOshat);
                configureOshat(blackOshat,n);
            }
            else if(d1!=0&&collect2(blackEnd,d1-1)>=0){
                checkNoMove(blackOshat);
                configureOshat(blackOshat,n);
            }
            else if(d4!=0&&collect2(blackEnd,d4-1)==-1&&collect3(blackEnd,d4-1)==23-blackOshat[n].currentPosition.x){
                collect1(collect3(blackEnd,d4-1)+1,n,blackEnd,blackOshat[n]);
                d4=0;
            }
            else if(d3!=0&&collect2(blackEnd,d3-1)==-1&&collect3(blackEnd,d3-1)==23-blackOshat[n].currentPosition.x){
                collect1(collect3(blackEnd,d3-1)+1,n,blackEnd,blackOshat[n]);
                d3=0;
            }
            else if(d2!=0&&collect2(blackEnd,d2-1)==-1&&collect3(blackEnd,d2-1)==23-blackOshat[n].currentPosition.x){
                collect1(collect3(blackEnd,d2-1)+1,n,blackEnd,blackOshat[n]);
                d2=0;
            }
            else if(d1!=0&&collect2(blackEnd,d1-1)==-1&&collect3(blackEnd,d1-1)==23-blackOshat[n].currentPosition.x){
                collect1(collect3(blackEnd,d1-1)+1,n,blackEnd,blackOshat[n]);
                d1=0;
            }  
        }
        else if(p==Player.white){
            if(d4!=0&&whiteOshat[n].currentPosition.x==(d4*-1)-1&&whiteEnd[d4*-1-1]>0){
                collect1(d4*-1-1,n,whiteEnd,whiteOshat[n]);
                d4=0;
            }
            else if(d3!=0&&whiteOshat[n].currentPosition.x==(d3*-1)-1&&whiteEnd[d3*-1-1]>0){
                collect1(d3*-1-1,n,whiteEnd,whiteOshat[n]);
                d3=0;
            }
            else if(d2!=0&&whiteOshat[n].currentPosition.x==(d2*-1)-1&&whiteEnd[d2*-1-1]>0){
                collect1(d2*-1-1,n,whiteEnd,whiteOshat[n]);
                d2=0;
            }
            else if(d1!=0&&whiteOshat[n].currentPosition.x==(d1*-1)-1&&whiteEnd[d1*-1-1]>0){
                collect1(d1*-1-1,n,whiteEnd,whiteOshat[n]);
                d1=0;
            }
            else if(d4!=0&&collect2(whiteEnd,d4*-1-1)>=0){
                checkNoMove(whiteOshat);
                configureOshat(whiteOshat,n);
            }
            else if(d3!=0&&collect2(whiteEnd,d3*-1-1)>=0){
                checkNoMove(whiteOshat);
                configureOshat(whiteOshat,n);
            }
            else if(d2!=0&&collect2(whiteEnd,d2*-1-1)>=0){
                checkNoMove(whiteOshat);
                configureOshat(whiteOshat,n);
            }
            else if(d1!=0&&collect2(whiteEnd,d1*-1-1)>=0){
                checkNoMove(whiteOshat);
                configureOshat(whiteOshat,n);
            }
            else if(d4!=0&&collect2(whiteEnd,d4*-1-1)==-1&&collect3(whiteEnd,d4*-1-1)==whiteOshat[n].currentPosition.x){
                collect1(collect3(whiteEnd,d4*-1-1)+1,n,whiteEnd,whiteOshat[n]);
                d4=0;
            }
            else if(d3!=0&&collect2(whiteEnd,d3*-1-1)==-1&&collect3(whiteEnd,d3*-1-1)==whiteOshat[n].currentPosition.x){
                collect1(collect3(whiteEnd,d3*-1-1)+1,n,whiteEnd,whiteOshat[n]);
                d3=0;
            }
            else if(d2!=0&&collect2(whiteEnd,d2*-1-1)==-1&&collect3(whiteEnd,d2*-1-1)==whiteOshat[n].currentPosition.x){
                collect1(collect3(whiteEnd,d2*-1-1)+1,n,whiteEnd,whiteOshat[n]);
                d2=0;
            }
            else if(d1!=0&&collect2(whiteEnd,d1*-1-1)==-1&&collect3(whiteEnd,d1*-1-1)==whiteOshat[n].currentPosition.x){
                collect1(collect3(whiteEnd,d1*-1-1)+1,n,whiteEnd,whiteOshat[n]);
                d1=0;
            }      
    }
}
public void collect1(int d,int n,int [] End,Oshat o){
                killOshat(d,n,End,o);
                if(p==Player.black){
                    totalBlack--;
                    score1.setText((15-totalBlack)+"");
                    if(totalBlack==0)
                        win1.setVisible(true);
                }
                else{
                    totalWhite--;
                    score2.setText((15-totalWhite)+""); 
                    if(totalWhite==0)
                        win2.setVisible(true);
                }
}
public int collect2(int [] End,int x){
    for(int i=x;i<6;i++){
        if(End[i]!=0)
            return i;
    }
    return -1;
}
public int collect3(int [] End,int x){
    for(int i=x;i>=0;i--){
        if(End[i]!=0)
            return i;
    }
    return -1;
}
public void killOshat(int d,int x,int []end,Oshat o){
            end[d]--;
            for(int i=0;i<15;i++)
                if(Positions.sortedOshat[o.currentPosition.x][i]==o.ID){
                    Positions.sortedOshat[o.currentPosition.x][i]=-1;
                    break;
                }
            sortOshat();
            int a=-1;
            for(int i=14;i>=0;i--){
                if(p==Player.black&&Positions.sortedOshat[24][i]==-1&&a==-1){
                    a=i;
                    break;
                }
                else if(p==Player.white&&Positions.sortedOshat[25][i]==-1&&a==-1){
                    a=i;
                    break;
                }
            }
            if(p==Player.black){
                o.currentPosition.x=24;
                o.currentPosition.y=a;
                Positions.sortedOshat[24][a]=o.ID;
                o.setLocation(Positions.Pos[o.currentPosition.x][o.currentPosition.y]);
                sortOshat();
                a=-1;
                //blackOshat = removeElements(blackOshat,o);
            }
            else if(p==Player.white){
                o.currentPosition.x=25;
                o.currentPosition.y=a;
                Positions.sortedOshat[25][a]=o.ID;
                o.setLocation(Positions.Pos[o.currentPosition.x][o.currentPosition.y]);
                sortOshat();
                a=-1;
                //whiteOshat = removeElements(whiteOshat,o);
            }
            for(int i=0;i<6;i++){
                blackEnd[i]=0;
                whiteEnd[i]=0;
            }
            for(int i=0;i<6;i++)
                for(int j=0;j<15;j++){
                    if(Positions.sortedOshat[i][j]>14)
                        whiteEnd[i]++;
                    if(Positions.sortedOshat[23-i][j]>-1&&Positions.sortedOshat[23-i][j]<15)
                        blackEnd[i]++;
                }
}
public static Oshat[] removeElements(Oshat[] input, Oshat deleteMe) {
    java.util.List result = new LinkedList();

    for(Oshat item : input)
        if(!deleteMe.equals(item))
            result.add(item);

    return (Oshat[]) result.toArray(input);
}
public void saveGame() throws Exception{
    deactivateOshat();
    fos = new FileOutputStream(f);
    oos = new ObjectOutputStream(fos);
    so = new Save(Positions.sortedOshat,rolls,firstBlack,firstWhite,firstRollBlack,firstRollWhite,dice1.getPath(),dice2.getPath(),totalBlack,totalWhite,blackEnd,whiteEnd);
    if(rolls<3){
        so.d1=altD1;so.d2=altD2;so.d3=altD3;so.d4=altD4;
    }
    else if(rolls>=3){
        so.d1=d1;so.d2=d2;so.d3=d3;so.d4=d4;
    }
    if(p==Player.black)
        so.lastPlayer="B";
    else if(p==Player.white)
        so.lastPlayer="W";
    for(int i=0;i<whiteOshat.length;i++)
        so.saveWhitePositions(whiteOshat[i].currentPosition.x, whiteOshat[i].currentPosition.y, i);   
    for(int i=0;i<blackOshat.length;i++)
        so.saveBlackPositions(blackOshat[i].currentPosition.x, blackOshat[i].currentPosition.y, i);
    oos.writeObject(so);
    oos.close();
    fos.close();
}
public void loadGame() throws Exception{
    fis = new FileInputStream(f);
    ois = new ObjectInputStream(fis);
    Save lg = (Save)ois.readObject();
    ois.close();
    fis.close();
    totalBlack = lg.totalBlack;
    totalWhite = lg.totalWhite;
    blackEnd = lg.blackEnd;
    whiteEnd = lg.whiteEnd;
    firstBlack = lg.firstBlack;
    firstWhite = lg.firstWhite;
    firstRollBlack = lg.firstRollBlack;
    firstRollWhite = lg.firstRollWhite;
    rolls=lg.rolls;
    score1.setText((15-totalBlack)+"");
    score2.setText((15-totalWhite)+"");
    for(int i=0;i<26;i++)
        for(int j=0;j<15;j++)
            Positions.sortedOshat[i][j]=lg.sortedOshat[i][j];
    repaint(dice1,lg.dice1);
    repaint(dice2,lg.dice2);
    p=null;
    d1=lg.d1;d2=lg.d2;d3=lg.d3;d4=lg.d4;
    if(lg.lastPlayer.equals("B"))
        switchPlayer(1);
    else if(lg.lastPlayer.equals("W"))
        switchPlayer(0);
    for(int i=0;i<blackOshat.length;i++){
        blackOshat[i].setLocation(Positions.Pos[lg.posBlack[i].x][lg.posBlack[i].y]);
        blackOshat[i].currentPosition = lg.posBlack[i];        
    }
    for(int i=0;i<whiteOshat.length;i++){
        whiteOshat[i].setLocation(Positions.Pos[lg.posWhite[i].x][lg.posWhite[i].y]);
        whiteOshat[i].currentPosition = lg.posWhite[i];
    }
    if(rolls<3){
        d1=0;d2=0;d3=0;d4=0;
    }
     sortOshat();
}
public void replayGame(){
    deactivateOshat();
    firstBlack=false;firstWhite=false;isEqual=false;
    for(int i=0;i<6;i++){
        blackEnd[i]=0;
        whiteEnd[i]=0;
    }
    score1.setText("0");
    score2.setText("0");
    player1.setForeground(new Color(225,178,124));
    score1.setForeground(new Color(225,178,124));
    player2.setForeground(new Color(78,49,7));
    score2.setForeground(new Color(78,49,7));
    for(int i=0;i<26;i++)
            for(int j=0;j<15;j++){
                if(i==0)
                    Positions.sortedOshat[i][j] = 14-j;
                else if(i==23)
                    Positions.sortedOshat[i][j] = 14-j;
                else if(i>0||i<26)
                    Positions.sortedOshat[i][j] = -1;
            }
    rolls=0;
    p=null;
    for(int i=0;i<blackOshat.length;i++){
        blackOshat[i].currentPosition.x=0;
        blackOshat[i].currentPosition.y=i;
        blackOshat[i].setLocation(Positions.Pos[blackOshat[i].currentPosition.x][blackOshat[i].currentPosition.y]);
        
    }
    for(int i=0;i<whiteOshat.length;i++){
        whiteOshat[i].currentPosition.x=23;
        whiteOshat[i].currentPosition.y=i;
        whiteOshat[i].setLocation(Positions.Pos[whiteOshat[i].currentPosition.x][whiteOshat[i].currentPosition.y]);
    }
    d1=0;d2=0;d3=0;d4=0;
    repaint(dice1,"images/dice1.png");
    repaint(dice2,"images/dice1.png");
        
}
}