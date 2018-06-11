import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel{

    private Timer timer,timer1, timer2;
    private static ArrayList<BasicTroop> troopsp1;
    private static ArrayList<BasicTroop> troopsp2;
    private static ArrayList<Bullet> bullets1, bullets2;
    private static ArrayList<DefenceTower> defs1;
    private static ArrayList<DefenceTower> defs2;
    private int turn;

    public Main(int w,int h){
        setSize(w,h);
        setUpTimer(1000/60);
        setUpMouseListener();
        setUpKeyListener();
        troopsp1 = new ArrayList<>();
        troopsp2 = new ArrayList<>();
        defs1 = new ArrayList<>();
        defs2 = new ArrayList<>();
        bullets1 = new ArrayList<>();
        bullets2 = new ArrayList<>();
        troopsp1.add(new BasicTroop(100,100));
        troopsp2.add(new BasicTroop(200,100));
        turn = 0;

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("CustomGame");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        int width = 1440;
        int height = 800;
        frame.setPreferredSize(new Dimension(width, height + 24));

        JPanel panel = new Main(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);


    }


    public void setUpTimer(int delay){
        if(timer == null) {
            timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //this code executes each frame!

                        for (BasicTroop bt : troopsp1) {
                            bt.move();
                        }

                        for (BasicTroop bt : troopsp2) {
                            bt.move();
                        }


                    repaint();
                }
            });
        }

        timer.start();
    }

    public void setUpTimer2(int delay){
        if(timer2 == null) {
            timer2 = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //this code executes each frame!

                    if (bullets2.size() >= 1)
                        bullets2.remove(0);

                    repaint();
                }
            });
        }

        timer2.start();
    }

    public void setUpTimer1(int delay){
        if(timer1 == null) {
            timer1 = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //this code executes each frame!

                    if (bullets1.size() >= 1)
                        bullets1.remove(0);

                    repaint();
                }
            });
        }

        timer1.start();
    }

    public void setUpMouseListener(){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3 && turn%2 == 0){
                    defs1.add(new DefenceTower(e.getX(),e.getY(),turn));
                }

                if (e.getButton() == MouseEvent.BUTTON3 && turn%2 == 1){
                    defs2.add(new DefenceTower(e.getX(),e.getY(),turn));
                }

                if (e.getButton() == MouseEvent.BUTTON1 ) {

                    if (turn%2 == 0) {
                        for (BasicTroop bt : troopsp1) {
                            bt.setDestin(e.getX(), e.getY());
                        }
                    }
                    else{
                        for (BasicTroop bt : troopsp2) {
                            bt.setDestin(e.getX(), e.getY());
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void setUpKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_SPACE){
                    turn++;
                    if (turn%2 == 0){
                        for (BasicTroop bt : troopsp1) {
                            bt.setMoveTurn(true);
                        }
                        for (BasicTroop bt : troopsp2) {
                            bt.setMoveTurn(false);
                        }

                    }
                    else{
                        for (BasicTroop bt : troopsp2) {
                            bt.setMoveTurn(true);
                        }   for (BasicTroop bt : troopsp1) {
                            bt.setMoveTurn(false);
                        }
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        Background b = new Background();
        CrystalThing c1 = new CrystalThing(75,350);
        CrystalThing c2 = new CrystalThing(1265,350);

        b.draw(g2);
        c1.draw(g2);
        c2.draw(g2);
        for (DefenceTower df : defs2) {
            df.draw(g2);
            for (BasicTroop bt :troopsp1) {
                if (df.checkIn(bt.getX(),bt.getY()) && bullets2.size() < 1){
                    Bullet bullet = new Bullet(bt.getX()-50,bt.getY()-50);
                    bullets2.add(bullet);
                }
//                for (Bullet bul:bullets2) {
//                    if (df.checkIn(bt.getX(),bt.getY()) )
//                }
                if (!df.checkIn(bt.getX(),bt.getY()) && bullets2.size() >= 1)
                    bullets2.remove(0);
                
            }
        }

        for (DefenceTower df : defs1) {
            df.draw(g2);
            for (BasicTroop bt :troopsp2) {
                if (df.checkIn(bt.getX(),bt.getY()) && bullets1.size() < 1){
                    Bullet bullet = new Bullet(bt.getX()-50,bt.getY()-50);
                    bullets1.add(bullet);
                }
                if (!df.checkIn(bt.getX(),bt.getY()) && bullets1.size() >= 1)
                    bullets1.remove(0);
            }
        }

        for (BasicTroop bt : troopsp1) {
            bt.draw(g2);
        }
        for (BasicTroop bt : troopsp2) {
            bt.draw(g2);
        }
        for (Bullet bul : bullets1) {
            bul.draw(g2);
        }
        for (Bullet bul : bullets2) {
            bul.draw(g2);
        }
    }




}
