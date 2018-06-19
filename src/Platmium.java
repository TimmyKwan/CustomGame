import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by student on 6/4/18.
 */
public class Platmium {

    private int x, y, amount;
    private BufferedImage pic;


    public Platmium() {
        this.x = (int)(Math.random()*(1465));
        this.y = (int)(Math.random()*(825));
        amount = 200;
        try {
            this.pic = ImageIO.read(new File("./res/" + "Crystalhehehe.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mine(int howMuch) {
        if (amount-howMuch>=0)
            amount-=howMuch;
    }

    public void draw(Graphics2D g2){
        if (amount > 0) {
            g2.drawImage(pic, x, y, null);
            g2.setColor(Color.black);
            g2.drawString(Integer.toString(amount), x, y);
        }
    }

    public int getX(){return x+12;}
    public int getY(){return y+12;}
    public int getAmount(){return amount;}

}
