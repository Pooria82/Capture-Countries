import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Thread {
    Color c;
    Bot(Color color){
        c= color;

    }
    @Override
    public void run() {
        try {
            while (true) {
                try {

                    //System.out.println(1 +" "+ 2);
                    Random rand = new Random();
                    sleep(rand.nextInt(10000,20000));
                    Country target, send;
                    int tindex = 0, sindex = 0;
                    ArrayList<Country> temp2, temp3 = new ArrayList<>();
                    synchronized (Game.countries) {
                        temp2 = Game.countries;
                    }
                    for (Country cc : temp2) {
                        if (cc.player.color.equals(c)) {
                            temp3.add(cc);

                        }
                    }

                    send = temp3.get(rand.nextInt(temp3.size()));
                    for (int i = 0; i < temp2.size(); i++) {
                        if (temp2.get(i).center.equals(send.center)) {
                            sindex = i;
                        }
                    }
                    do {
                        int index = rand.nextInt(temp2.size());
                        target = temp2.get(index);
                        tindex = index;
                    } while (sindex == tindex);


                    int num = 0;
                    synchronized (Game.countries) {
                        num = Game.countries.get(sindex).numberOfUnits;
                    }
                    for (int j = 0; j < num; j++) {
                        Unit u = send.sendUnit(target.center);
                        u.start();
                        synchronized (Game.units) {
                            Game.units.add(u);
                        }
                    }
                    send = null;
                    target = null;
                    sindex = 0;
                    tindex = 0;

                }catch (Exception exx){

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
