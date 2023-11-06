import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Coord extends Point {

    Coord(Point p){
        try {
            this.x = p.x;
            this.y = p.y;
        }catch (Exception ex){

        }
    }
    public Coord randomizer(){
        try {
            Random rand = new Random();
            int xx = rand.nextInt(41) + (this.x - 20);
            int yy = rand.nextInt(41) + (this.y - 20);
            Point temp = new Point();
            temp.x = xx;
            temp.y = yy;
            Coord ans = new Coord(temp);
            ans.x = xx;
            ans.y = yy;
            return ans;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (x == ((Coord) obj).x && y == ((Coord) obj).y) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
