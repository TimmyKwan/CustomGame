import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel{

    private Timer timer,timer1, timer2;
    private static ArrayList<BasicTroop> troopsp1;
    private static ArrayList<BasicTroop> troopsp2;
    private static ArrayList<DefenceTower> defs1;
    private static ArrayList<DefenceTower> defs2;
    private static ArrayList<Platmium> plats;
    private int turn, platmiumsc1, platmiumsc2;

    public Main(int w,int h){
        setSize(w,h);
        setUpTimer(1000/60);
        setUpMouseListener();
        setUpKeyListener();
        troopsp1 = new ArrayList<>();
        troopsp2 = new ArrayList<>();
        defs1 = new ArrayList<>();
        defs2 = new ArrayList<>();
        plats = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Platmium pt = new Platmium();
            plats.add(pt);
        }
        turn = 0;
        platmiumsc1 = 50;
        platmiumsc2 = 50;

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

    public void setUpMouseListener(){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3 && turn%2 == 0 && defs1.size() <2){
                    if (e.getX() < 720) {
                        if (platmiumsc1 - 500 >= 0) {
                            defs1.add(new DefenceTower(e.getX(), e.getY(), turn));
                            platmiumsc1 -= 500;
                        }
                    }
                }

                if (e.getButton() == MouseEvent.BUTTON3 && turn%2 == 1 && defs2.size() <2){
                    if (e.getX()>720) {
                        if (platmiumsc2 - 300 >= 0) {
                            defs2.add(new DefenceTower(e.getX(), e.getY(), turn));
                            platmiumsc2 -= 300;
                        }
                    }
                }

                if (e.getButton() == MouseEvent.BUTTON1 ) {

                    if (turn%2 == 0) {
                        for (BasicTroop bt : troopsp1) {
                            if (bt.getIsSelect()) {
                                bt.setDestin(e.getX(), e.getY());
                            }
                        }
                    }
                    else{
                        for (BasicTroop bt : troopsp2) {
                            if (bt.getIsSelect()) {
                                bt.setDestin(e.getX(), e.getY());
                            }
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
                            for (Platmium pt:plats) {
                                if (bt.checkIn(pt.getX(),pt.getY()) && pt.getAmount() > 0){
                                    if (bt instanceof PlatmiumCart) {
                                        pt.mine(200);
                                        platmiumsc2 += 200;
                                    }
                                    else {
                                        pt.mine(100);
                                        platmiumsc2 += 100;
                                    }
                                }
                            }
                            bt.setIsSelect(false);
                        }
                        for (DefenceTower df : defs1) {
                            for (BasicTroop bt :troopsp2) {
                                if (df.checkIn(bt.getX(),bt.getY())){
                                    troopsp2.remove(bt);
                                    df.hit();
                                }
                                if (df.getHits() >= 2){
                                    defs1.remove(df);
                                }
                            }
                        }

                    }
                    else{
                        for (BasicTroop bt : troopsp2) {
                            bt.setMoveTurn(true);
                        }
                        for (BasicTroop bt : troopsp1) {
                            bt.setMoveTurn(false);
                            for (Platmium pt:plats) {
                                if (bt.checkIn(pt.getX(),pt.getY()) && pt.getAmount() > 0){
                                    if (bt instanceof PlatmiumCart) {
                                        pt.mine(200);
                                        platmiumsc1 += 200;
                                    }
                                    else{
                                        pt.mine(100);
                                        platmiumsc1 += 100;
                                    }
                                }
                            }
                            bt.setIsSelect(false);
                        }
                        for (DefenceTower df : defs2) {
                            for (BasicTroop bt :troopsp1) {
                                if (df.checkIn(bt.getX(),bt.getY())){
                                    troopsp1.remove(bt);
                                    df.hit();
                                }
                                if (df.getHits() >= 2){
                                    defs2.remove(df);
                                }
                            }
                        }
                    }


                }
                if (e.getKeyChar() == KeyEvent.VK_1){
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    if (turn%2 == 0 && p.getX() < 720){
                        if (platmiumsc1 - 100 >= 0) {
                            troopsp1.add(new BasicTroop((int) p.getX(), (int) p.getY() - 50, turn));
                            platmiumsc1 -= 100;
                        }
                    }
                    else if (turn%2 == 1 && p.getX() > 720){
                        if (platmiumsc2 - 100 >= 0) {
                            troopsp2.add(new BasicTroop((int) p.getX(), (int) p.getY() - 50, turn));
                            platmiumsc2 -= 100;
                        }
                    }
                }
                if (e.getKeyChar() == KeyEvent.VK_2){
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    if (turn%2 == 0 && p.getX() < 720){
                        if (platmiumsc1 - 50 >= 0) {
                            troopsp1.add(new PlatmiumCart((int) p.getX(), (int) p.getY() - 50, turn));
                            platmiumsc1 -= 50;
                        }
                    }
                    else if (turn%2 == 1 && p.getX() > 720){
                        if (platmiumsc2 - 50 >= 0) {
                            troopsp2.add(new PlatmiumCart((int) p.getX(), (int) p.getY() - 50, turn));
                            platmiumsc2 -= 50;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_E){
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    System.out.println("e");
                    if (turn%2 == 0){
                        for (BasicTroop bt : troopsp1) {
                            if (bt.checkIn((int)p.getX(),(int)p.getY()) && !bt.getIsSelect()){
                                bt.setIsSelect(true);
                            }
                            else if (bt.checkIn((int)p.getX(),(int)p.getY()) && bt.getIsSelect()){
                                bt.setIsSelect(false);
                            }
                        }
                    }
                    if (turn%2 == 1){
                        for (BasicTroop bt : troopsp2) {
                            if (bt.checkIn((int)p.getX(),(int)p.getY()) && !bt.getIsSelect()){
                                bt.setIsSelect(true);
                            }
                            else if (bt.checkIn((int)p.getX(),(int)p.getY()) && bt.getIsSelect()){
                                bt.setIsSelect(false);
                            }
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

        CrystalThing c1 = new CrystalThing(75,350,0);
        CrystalThing c2 = new CrystalThing(1265,350,1);
        b.draw(g2);
        c1.draw(g2);
        c2.draw(g2);

        g2.setColor(Color.MAGENTA);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(720,0,720,800);
        g2.setStroke(new BasicStroke(1));

        for (Platmium pt : plats) {
            pt.draw(g2);
        }

        for (DefenceTower df :defs1) {
            df.draw(g2);
        }
        for (DefenceTower df :defs2) {
            df.draw(g2);
        }

        for (BasicTroop bt : troopsp1) {
            bt.draw(g2);
        }
        for (BasicTroop bt : troopsp2) {
            bt.draw(g2);
        }

        g2.setColor(Color.BLACK);
        g2.drawString("PLAYER 1 PLATMIUM: " + Integer.toString(platmiumsc1),10,12);
        g2.drawString("PLAYER 2 PLATMIUM: " + Integer.toString(platmiumsc2),1250,10);
    }




}
