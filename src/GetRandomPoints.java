import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GetRandomPoints {
    private ArrayList<Point> points = new ArrayList<>();
    private int number;
    private int width;
    private int height;
    private Random random = new Random();

    public GetRandomPoints(int number, int width, int height) {
        try {
            this.number = number;   //tedad keshvarha
            this.width = width;
            this.height = height;
            long start = System.currentTimeMillis();    //for thinkinging and try again
            for (int i = 0; i < number; i++) {
                long live = System.currentTimeMillis();
                Point point = getRandom();
                if (checkPoint(point))
                    points.add(point);
                else
                    i--;
                if (live - start > 500) {       //after 0.2 second
                    start = live;   //new start time
                    //                System.out.println("clear");
                    points.clear();
                    i = 0;
                    System.out.println("shit");
                }
            }
        } catch (Exception ex) {

        }

    }

    public boolean checkPoint(Point point) {
        try {
            for (Point point1 : points) {
                int distance = (int) Math.sqrt(Math.pow(point1.x - point.x, 2) + Math.pow(point1.y - point.y, 2));
                if (distance < 165)          //hadeaghal fasele Keshvarha
                    return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Point getRandom() {     //fasele az gooshe ha
        try {
            int x = random.nextInt(width - 100 - 100) + 100;
            int y = random.nextInt(height - 100 - 100) + 100;
            return new Point(x, y);
        } catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<Point> getPoints() {
        try {
            return points;
        } catch (Exception ex) {
            return null;
        }
    }
}
