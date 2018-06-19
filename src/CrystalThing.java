import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CrystalThing {
    private int x,y,color;
    private BufferedImage greenPic,redPic;

    public CrystalThing(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        try {
            this.greenPic = ImageIO.read(new File("./res/" + "GreenCrystal.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.redPic = ImageIO.read(new File("./res/" + "RedCrystal.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(new Color(151,151,151));
        g2.fillRect(x,y,100,100);
        if (color == 0)
            g2.drawImage(greenPic,x,y,null);
        else
            g2.drawImage(redPic,x,y,null);

    }



}
