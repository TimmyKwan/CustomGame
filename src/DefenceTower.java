import java.awt.*;
import java.util.ArrayList;

public class DefenceTower {
    private int x,y,turn;
    private int diameter, damage, health,hits;
    private double distFromCenter;

    public DefenceTower(int x, int y, int turn){
        this.x = x;
        this.y = y;
        diameter = 350;
        distFromCenter = Math.PI * diameter;
        this.turn = turn;
        hits = 0;
    }

    public void draw(Graphics2D g2){
        if (turn%2 == 0) {
            g2.setColor(new Color(0, 182, 255, 75));
        }
        else
            g2.setColor(new Color(255, 52, 8, 75));

        g2.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
        g2.setColor(new Color(111, 111, 111));
        g2.fillRect(x - 25, y - 25, 50, 50);
    }

    public boolean checkIn(int x, int y){
        int dist = (int)Math.sqrt(Math.abs(((this.x-x) * (this.x-x)) + ((this.y-y) * (this.y-y))));
        if (dist <= diameter/2)
            return true;
        return false;
    }

    public int getHits(){
        return hits;
    }

    public void hit(){
        hits++;
    }

}
