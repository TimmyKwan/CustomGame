/**
 * Created by student on 6/4/18.
 */
public class Platmium {

    private int x, y, amount;


    public Platmium(int x, int y) {
        this.x = x;
        this.y = y;
        amount = 200;
    }

    public void mine() {
        amount--;
    }



}
