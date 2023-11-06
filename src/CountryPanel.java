import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class CountryPanel extends JPanel implements MouseListener {
    private ArrayList<Point> points;
    private Random random = new Random();

    public Country selected, target;
    public int selectindex;

    public CountryPanel(ArrayList<Point> points) {
        try {
            this.setBounds(0, 0, 1500, 700);
            this.points = points;
            this.addMouseListener(this);

        } catch (Exception ex) {

        }
    }

    public void paintComponent(Graphics g) {
        try {
            Image backgroundImage;
            try {
                if(Setting.filePath.equals("null")){
                    throw new Exception();
                }
                backgroundImage = Toolkit.getDefaultToolkit().getImage(Setting.filePath);
            }catch (Exception exx){
                backgroundImage = Toolkit.getDefaultToolkit().getImage("6.jpg");
            }
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            ArrayList<Unit> temp;
            synchronized (Game.units) {
                temp = Game.units;
            }
            ArrayList<Country> temp2;
            synchronized (Game.countries) {
                temp2 = Game.countries;
            }

            Image icon = Toolkit.getDefaultToolkit().getImage("menuIcon.png");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setIconImage(icon);
            frame.setTitle("Game Board");
//
            Image normalIcon = Toolkit.getDefaultToolkit().getImage("Normal.png");
            Image attackerIcon = Toolkit.getDefaultToolkit().getImage("Attacker.png");
            Image defenderIcon = Toolkit.getDefaultToolkit().getImage("Defender.png");
            Image shooterIcon = Toolkit.getDefaultToolkit().getImage("Shooter2.jpg");
            Image barracksIcon = Toolkit.getDefaultToolkit().getImage("Fast1.png");

            for (Country cc : temp2) {
                if (cc instanceof ShooterCountry) {
                    g.setColor(Color.darkGray);
                    g.drawOval(cc.center.x-100,cc.center.y-100,200,200);
                }
                //g.setColor(new Color(a1, a2, a3));
                g.setColor(Color.black);
                g.fillOval(cc.center.x - 85, cc.center.y - 85, 170, 170);
                g.setColor(cc.player.color);
                g.fillOval(cc.center.x - 80, cc.center.y - 80, 160, 160);
                g.setColor(Color.black);
                g.drawString("" + cc.numberOfUnits, cc.center.x, cc.center.y - 5);
                g.fillOval(cc.center.x - 5, cc.center.y - 5, 10, 10);
                //point.x/y - (half of width & height)
//                String type = "";
                if (cc instanceof NormalCountry) {
//                    type = "Normal";
                    int iconWidth = 46; // عرض آیکون
                    int iconHeight = 66; // ارتفاع آیکون
                    g.drawImage(normalIcon, cc.center.x - iconWidth, cc.center.y - iconHeight, iconWidth, iconHeight, this);
                } else if (cc instanceof AttackerCountry) {
//                    type = "attacker";
                    int iconWidth = 66; // عرض آیکون
                    int iconHeight = 64; // ارتفاع آیکون
                    g.drawImage(attackerIcon, cc.center.x - iconWidth, (int) (cc.center.y - iconHeight/1.5), iconWidth, iconHeight, this);
                } else if (cc instanceof DefenderCountry) {
//                    type = "defender";
                    int iconWidth = 140; // عرض آیکون
                    int iconHeight = 94; // ارتفاع آیکون
                    g.drawImage(defenderIcon, (int) (cc.center.x - iconWidth/1.4), (int) (cc.center.y - iconHeight/1.5), iconWidth, iconHeight, this);
                } else if (cc instanceof ShooterCountry) {
//                    type = "shooter";
                    int iconWidth = 90; // عرض آیکون
                    int iconHeight = 50; // ارتفاع آیکون
                    g.drawImage(shooterIcon, (int) (cc.center.x - iconWidth/1.9), (int) (cc.center.y - iconHeight/1.5), iconWidth, iconHeight, this);
                }
                else if (cc instanceof FastCountry) {
//                    type = "fast";
                    int iconWidth = 75; // عرض آیکون
                    int iconHeight = 75; // ارتفاع آیکون
                    g.drawImage(barracksIcon, (int) (cc.center.x - iconWidth), (int) (cc.center.y - iconHeight/1.5), iconWidth, iconHeight, this);
                }
//                g.drawString(type, cc.center.x + 7, cc.center.y + 5);

            }

            for (Unit unit : temp) {
                g.setColor(Color.black);
                g.fillOval(unit.now.x - 6, unit.now.y - 6, 12, 12);
                g.setColor(unit.player.color);
                g.fillOval(unit.now.x - 5, unit.now.y - 5, 10, 10);
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            Point m = getMousePosition();

            ArrayList<Country> temp2;
            synchronized (Game.countries) {
                temp2 = Game.countries;
            }
            selectindex = 0;
            for (int i = 0; i < temp2.size(); i++) {
                if (Math.sqrt(Math.pow(Math.abs(temp2.get(i).center.x - m.x), 2) + Math.pow((Math.abs(temp2.get(i).center.y - m.y)), 2)) < 50) {
                    synchronized (Game.countries) {
                        selected = Game.countries.get(i);
                        selectindex = i;
                        //Game.countries.get(i).player.color = Color.GREEN;
                    }
                    break;
                }
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Point m = getMousePosition();

            ArrayList<Country> temp2;
            synchronized (Game.countries) {
                temp2 = Game.countries;
            }

            for (int i = 0; i < temp2.size(); i++) {
                if (Math.sqrt(Math.pow(Math.abs(temp2.get(i).center.x - m.x), 2) + Math.pow((Math.abs(temp2.get(i).center.y - m.y)), 2)) < 50) {
                    synchronized (Game.countries) {
                        target = Game.countries.get(i);
                        //Game.countries.get(i).player.color = Color.GREEN;
                    }
                    break;
                }
            }
            if (!target.center.equals(selected.center) && !selected.player.color.equals(Color.gray) && !selected.player.role.equals("bot")) {
                int num = 0;
                synchronized (Game.countries) {
                    num = Game.countries.get(selectindex).numberOfUnits;
                }
                for (int j = 0; j < num; j++) {
                    Unit u = selected.sendUnit(target.center);
                    u.start();
                    synchronized (Game.units) {
                        Game.units.add(u);
                    }
                }
            }
            selectindex = 0;
            selected = null;
            target = null;
        } catch (Exception ex) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
