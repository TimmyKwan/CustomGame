import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    public Main(int w,int h){
        setSize(w,h);
    }

    public static void main(String[] args) {
        System.out.println("plz");


        JFrame frame = new JFrame("CustomGame");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        int width = 1598;
        int height = 800;
        frame.setPreferredSize(new Dimension(width, height + 24));

        JPanel panel = new Main(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Background b = new Background();

        b.draw(g2);
    }
}
