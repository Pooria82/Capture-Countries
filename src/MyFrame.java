import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyFrame extends JFrame{
    public ArrayList<Point> points;
    public MyFrame(int number) {
        try {
            this.setSize(1500, 800);       //frameSize
            this.setLayout(null);
            //sizeset...
            points = new GetRandomPoints(number+1, this.getWidth(), this.getHeight()-100).getPoints();       //number : tedad keshvarha
            Game.setGamePoints(points);
            this.setLocationRelativeTo(null);
        }catch (Exception ex){

        }

    }


}
