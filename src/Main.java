import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {

    private Timer timer, timer2;
    private static ArrayList<BasicTroop> troopsp1;
    private static ArrayList<BasicTroop> troopsp2;
    private static ArrayList<Bullet> bullets;
    private static ArrayList<DefenceTower> defs;
    private int turn;

    public Main(int w,int h){
        setSize(w,h);
        setUpTimer(1000/60);
        setUpMouseListener();
        troopsp1 = new ArrayList<>();
        defs = new ArrayList<>();
        bullets = new ArrayList<>();
        troopsp1.add(new BasicTroop(100,100));
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

//                    for (BasicTroop bt :
//                            troops) {
//                        bt.move();
//                    }


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

                    if (bullets.size() >= 1)
                        bullets.remove(0);

                    repaint();
                }
            });
        }

        timer2.start();
    }

    public void setUpMouseListener(){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3){
                    defs.add(new DefenceTower(e.getX(),e.getY()));
                }

                if (e.getButton() == MouseEvent.BUTTON1 ) {
                    for (BasicTroop bt : troopsp1) {
                        bt.setDestin(e.getX(), e.getY());
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

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        Background b = new Background();
        CrystalThing c1 = new CrystalThing(75,350);
        CrystalThing c2 = new CrystalThing(1265,350);

        b.draw(g2);
        c1.draw(g2);
        c2.draw(g2);
        for (DefenceTower df : defs) {
            df.draw(g2);
            for (BasicTroop bt :troopsp1) {
                if (df.checkIn(bt.getX(),bt.getY()) && bullets.size() < 1){
                    Bullet bullet = new Bullet(bt.getX()-50,bt.getY()-50);
                    bullets.add(bullet);
                    setUpTimer2(2000);
                }
            }
        }
        for (BasicTroop bt : troopsp1) {
            bt.draw(g2);
        }
        for (Bullet bul : bullets) {
            bul.draw(g2);
        }
    }




}
