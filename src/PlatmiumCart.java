import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlatmiumCart extends BasicTroop{

    private int moveTurn;
    private BufferedImage greenPic,redPic;
    private boolean isMoveTurn;

    PlatmiumCart(int x, int y, int moveTurn){
        super(x,y,moveTurn);
        this.moveTurn = moveTurn;
        moveRange = 160;
        isMoveTurn = true;
        try {
            this.greenPic = ImageIO.read(new File("./res/" + "GreenTruck.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.redPic = ImageIO.read(new File("./res/" + "RedTruck.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics2D g2){
        if (moveTurn%2 == 0) {
            g2.drawImage(greenPic,(int) x - 15, (int) y - 15, null);
        }
        else if (moveTurn%2 == 1){
            g2.drawImage(redPic,(int) x - 15, (int) y - 15, null);
        }

        if(isMoveTurn && isSelect) {
            g2.setColor(new Color(255, 237, 0, 100));
            g2.fillOval((int) x + (int) distTravelled - moveRange, (int) y + (int) distTravelled - moveRange, 2 * (moveRange - (int) distTravelled), 2 * (moveRange - (int) distTravelled));
        }

        if (isSelect){
            g2.setColor(Color.CYAN);
            g2.drawRect((int)x - redPic.getWidth()/2,(int)y - redPic.getHeight()/2,30,30);
        }

    }

    @Override
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

    @Override
    public void setMoveTurn(boolean isMoveTurn){
        this.isMoveTurn = isMoveTurn;

    }
}
