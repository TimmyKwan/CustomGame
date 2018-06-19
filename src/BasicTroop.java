import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BasicTroop {

    private int health, dmg,moveTurn;
    public double x,y, vx, vy ;
    public Point initial, destin;
    private boolean isMoveTurn;
    private int storage;
    private BufferedImage greenPic,redPic;
    public int moveRange;
    public double distTravelled;
    public boolean isSelect;

    public BasicTroop(int x, int y, int moveTurn){
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        isSelect = false;
        destin = new Point(x,y);
        storage = 0;
        initial = new Point(x,y);

        this.moveTurn = moveTurn;

        health = 6;
        dmg = 2;
        moveRange = 80;
        distTravelled = 0;

        //for testing purposes, is on true, should be initially false in actual game
        isMoveTurn = true;

        try {
            this.greenPic = ImageIO.read(new File("./res/" + "GreenSoldier.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.redPic = ImageIO.read(new File("./res/" + "RedSoldier.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        if (moveTurn%2 == 0){
            g2.drawImage(greenPic, (int)x - greenPic.getWidth()/2,(int)y - greenPic.getHeight()/2, null);
        }
        else
            g2.drawImage(redPic, (int)x - redPic.getWidth()/2,(int)y - redPic.getHeight()/2, null);

        //draws movement range circle
        if(isMoveTurn && isSelect) {
            g2.setColor(new Color(255, 237, 0, 100));
            g2.fillOval((int) x + (int) distTravelled - moveRange, (int) y + (int) distTravelled - moveRange, 2 * (moveRange - (int) distTravelled), 2 * (moveRange - (int) distTravelled));
        }

        if (isSelect){
            g2.setColor(Color.CYAN);
            g2.drawRect((int)x - redPic.getWidth()/2,(int)y - redPic.getHeight()/2,30,30);
        }
    }

    public void move(){

        if(isMoveTurn && distTravelled <= moveRange){

            //checks to see if the BasicTroop is moving or not, if not, distTravelled will not increase
                //has a margin of error implemented
            if((destin.x > (int)x+1 || destin.x < (int)x-1) && (destin.y > (int)y+1 || destin.y < (int)y-1)){

                //maybe make a recursion statement to lower vx and vy
                vx = Math.abs(destin.x - initial.x) / 60.0;
                vy = Math.abs(destin.y - initial.y) / 60.0;

                if (x < destin.x)
                    x += vx;
                if (x > destin.x)
                    x -= vx;
                if (y < destin.y)
                    y += vy;
                if (y > destin.y)
                    y -= vy;

                distTravelled += Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
            }
        }
        else{
            setInitial();
            setDestin((int)x,(int)y);
            distTravelled = 0.0;
            isMoveTurn = false;
        }
    }

    //use in Main class, sets the initial position of the troop(where it had started to move)
    public void setInitial(){
        initial.setLocation((int)x,(int)y);
    }

    //use in Main class with MousePressed -- sets where the troop is headed to
    public void setDestin(int x,int y) {
        destin.setLocation(x, y);
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setDmg(int dmg){
        this.dmg = dmg;
    }
    public void setMoveRange(int moveRange){
        this.moveRange = moveRange;
    }
    //just in case you want to change troop size, although, with images, this wont be necessary

    //use setMoveTurn to set isMoveTurn to true when it is this troops turn
    public void setMoveTurn(boolean isMoveTurn){
        this.isMoveTurn = isMoveTurn;

    }

    public void takeDmg(int dmgTaken){
        health -= dmgTaken;
    }

    public boolean isAlive(){
        if(health > 0){
            return true;
        }
        return false;
    }

    public int getX(){
        return (int)(x);
    }

    public int getY(){
        return (int)(y);
    }

    public boolean checkIn(int x, int y){
        int dist = (int)Math.sqrt(Math.abs(((this.x-x) * (this.x-x)) + ((this.y-y) * (this.y-y))));
        if (dist <= 15)
            return true;
        return false;
    }

    public void setIsSelect(boolean select){
        isSelect = select;
    }
    public boolean getIsSelect(){return isSelect;}
}
