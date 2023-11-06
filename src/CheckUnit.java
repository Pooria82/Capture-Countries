import java.util.ArrayList;

public class CheckUnit extends Thread{
    @Override
    public void run() {
        try {
            while (true) {
                ArrayList<Unit> temp;
                synchronized (Game.units) {
                    temp = Game.units;
                }
                for (int i = temp.size() - 1; i >= 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (!temp.get(i).player.color.equals(temp.get(j).player.color)) {
                            if (Math.sqrt(Math.pow(Math.abs(temp.get(j).now.x - temp.get(i).now.x), 2) + Math.pow((Math.abs(temp.get(j).now.y - temp.get(i).now.y)), 2)) < 30) {
                                synchronized (Game.units) {
                                    temp.get(i).interrupt();
                                    temp.get(j).interrupt();
                                    Game.units.remove(temp.get(i));
                                    Game.units.remove(temp.get(j));

                                }
                                i--;
                                synchronized (Game.units) {
                                    temp = Game.units;
                                }
                                break;

                            }
                        }
                    }

                }
                try {
                    sleep(5);
                } catch (InterruptedException e) {

                }
            }
        }catch (Exception ex){

        }

    }
}
