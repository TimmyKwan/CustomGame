import java.awt.*;

public class BasicTroop {
    private int x,y,vx,vy, moveRange;
    private Point destin;

    public BasicTroop(int x,int y){
        this.x = x;
        this.y = y;
        vx = 5;
        vy = 5;
        
        destin = new Point(x,y);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(x,y,10,10);
    }

    public void move(){
        if (Math.abs(destin.getX()-x)>5 && x < destin.getX())
            x += vx;
        if (Math.abs(destin.getX()-x)>5 && x > destin.getX())
            x -= vx;
        if (Math.abs(destin.getY()-y)>5 && y < destin.getY())
            y += vy;
        if (Math.abs(destin.getY()-y)>5 && y > destin.getY())
            y -= vy;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    public int getX(){ return x;}
    public int getY(){return y;}
    public void setDestin(int x,int y) {
        destin.setLocation(x, y);
    }
}
