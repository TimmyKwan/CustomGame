import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bullet {
    private BufferedImage pic;
    private int x,y;


    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        try {
            this.pic = ImageIO.read(new File("./res/" + "Arrow.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
            g2.setColor(Color.red);
            g2.drawOval(x, y, 100, 100);

            g2.drawImage(pic, x + (int) (Math.random() * 75), y + (int) (Math.random() * 75), null);
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
