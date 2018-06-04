import java.awt.*;

public class CrystalThing {
    private int x,y;

    public CrystalThing(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2){
        g2.setColor(new Color(151,151,151));
        g2.fillRect(x,y,100,100);
    }



}
