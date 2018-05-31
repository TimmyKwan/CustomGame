import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {

    public Main(int w,int h){
        setSize(w,h);
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
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Background b = new Background();
        CrystalThing c1 = new CrystalThing(75,350);
        CrystalThing c2 = new CrystalThing(1265,350);

        b.draw(g2);
        c1.draw(g2);
        c2.draw(g2);
    }
}
