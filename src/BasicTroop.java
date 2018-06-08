import java.awt.*;

public class BasicTroop {

    private int health, dmg, moveRange, troopSize;
    double x,y, vx, vy, distTravelled;
    private Point initial, destin;
    private boolean isMoveTurn;
    private int storage;

    public BasicTroop(int x, int y){
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        destin = new Point(x,y);
        storage = 0;
        initial = new Point(x,y);

        troopSize = 10;
        health = 6;
        dmg = 2;
        moveRange = 80;
        distTravelled = 0;

        //for testing purposes, is on true, should be initially false in actual game
        isMoveTurn = true;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.setColor(Color.black);
        g2.fillRect((int)x - troopSize/2,(int)y - troopSize/2,troopSize,troopSize);


        //draws movement range circle
        if(isMoveTurn) {
            g2.setColor(new Color(255, 237, 0, 100));
            g2.fillOval((int) x + (int) distTravelled - moveRange, (int) y + (int) distTravelled - moveRange, 2 * (moveRange - (int) distTravelled), 2 * (moveRange - (int) distTravelled));
        }
    }

    public void adStorage(int amt, Platmium plat){
        while(storage > 5) {
            plat.mine();
            storage++;
        }
    }

    public void move(){

        if(distTravelled <= moveRange){

            //checks to see if the BasicTroop is moving or not, if not, distTravelled will not increase
                //has a margin of error implemented
            if(isMoveTurn && (destin.x > (int)x+1 || destin.x < (int)x-1) && (destin.y > (int)y+1 || destin.y < (int)y-1)){

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
            setDestin((int)x,(int)y);
            distTravelled = 0;
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
    public void setTroopSize(int troopSize){
        this.troopSize = troopSize;
    }

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
}
