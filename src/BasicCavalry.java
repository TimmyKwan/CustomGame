/**
 * Created by student on 6/1/18.
 */
public class BasicCavalry extends BasicTroop{
    public BasicCavalry(int x, int y, int turn){
        super(x, y, turn);
        setMoveRange(20);
        setMoveRange(10);
        setDmg(10);
        setHealth(4);
    }
}
