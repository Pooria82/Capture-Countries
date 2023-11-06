import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Slider extends JPanel {
    Slider() {
        this.setBounds(0, 700, 1300, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            int length = this.getWidth();
            int cp1 = 0, cp2 = 0, cp3 = 0, cp4 = 0, total = 0;
            ArrayList<Country> temp;
            synchronized (Game.countries) {
                temp = Game.countries;
            }
            for (Country i : temp) {
                if (i.player.color.equals(Game.p1.color)) {
                    cp1 += i.numberOfUnits;
                    total += i.numberOfUnits;
                }
                if (i.player.color.equals(Game.p2.color)) {
                    cp2 += i.numberOfUnits;
                    total += i.numberOfUnits;
                }
                if (i.player.color.equals(Game.p3.color)) {
                    cp3 += i.numberOfUnits;
                    total += i.numberOfUnits;
                }
                if (i.player.color.equals(Game.defaultplayer.color)) {
                    cp4 += i.numberOfUnits;
                    total += i.numberOfUnits;
                }
            }
            g.setColor(Game.p1.color);
            int l1 = (int) (length * ((double) (cp1) / (total)));
            g.fillRect(0, 0, l1, this.getHeight());
            g.setColor(Game.p2.color);
            int l2 = (int) (length * ((double) (cp2) / (total)));
            g.fillRect(l1, 0, l2, this.getHeight());
            g.setColor(Game.p3.color);
            int l3 = (int) (length * ((double) (cp3) / (total)));
            g.fillRect(l1 + l2, 0, l3, this.getHeight());
            g.setColor(Game.defaultplayer.color);
            int l4 = (int) (length * ((double) (cp4) / (total)));
            g.fillRect(l1 + l2 + l3, 0, l4, this.getHeight());
        } catch (Exception ex) {

        }
    }
}
