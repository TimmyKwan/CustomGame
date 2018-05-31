import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {

    private static BasicTroop bt;
    private Timer timer;

    public Main(int w,int h){
        setSize(w,h);
        setUpTimer(1000/60);
        bt = new BasicTroop(100,100);
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

                    bt.move();

                    repaint();
                }
            });
        }

        timer.start();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Background b = new Background();
        CrystalThing c1 = new CrystalThing(75,350);
        CrystalThing c2 = new CrystalThing(1265,350);

        b.draw(g2);
        c1.draw(g2);
        c2.draw(g2);
        bt.draw(g2);
    }


}
