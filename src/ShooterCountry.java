import java.util.ArrayList;

public class ShooterCountry extends Country {
    int speed = 1500;
    int shootspeed = 2000;
    ShooterCountry(Coord center) {
        try {
            this.center = center;
            this.numberOfUnits = 15;
            this.HP = this.numberOfUnits;
            this.player = Game.defaultplayer;
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        sleep(shootspeed);
                        //System.out.println("");
                        if (state.equals("claimed")) {
                            ArrayList<Unit> units2;
                            synchronized (Game.units) {
                                units2 = Game.units;
                            }
                            //System.out.println(units2.size());
                            for (int i = units2.size() - 1; i >= 0; i--) {
                               // System.out.println("ppp");
                                if (!units2.get(i).player.color.equals(this.player.color)) {
                                    //System.out.println(Math.sqrt(Math.pow(Math.abs(center.x - units2.get(i).now.x), 2) + Math.pow((Math.abs(center.y - units2.get(i).now.y)), 2)) <= radius);
                                    if (Math.sqrt(Math.pow(Math.abs(center.x - units2.get(i).now.x), 2) + Math.pow((Math.abs(center.y - units2.get(i).now.y)), 2)) <= radius) {
                                        synchronized (Game.units) {
                                            units2.get(i).interrupt();
                                          //  Game.units.get(i).interrupt();
                                            Game.units.remove(units2.get(i));
                                           // System.out.println("...");

                                        }
                                        break;
                                    }
                                }
                            }

                        }
                    }
                } catch (Exception exxx) {

                }
            });
            t.start();
        }catch (Exception exx){
            System.out.println(exx.getMessage());
        }
    }

    public synchronized void setClaimed(Player player) {
        try {
            this.state = "claimed";
            this.player = player;
        }catch (Exception ex){

        }
    }

    @Override
    public void run() {
        try {
            //super.run();

            while (state.equals("unclaimed")) {
                if (numberOfUnits < 15) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP++;
                    }

                }
                sleep(speed);
            }
            while (state.equals("claimed")) {

                if (numberOfUnits < 50) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP++;
                    }

                }
                sleep(speed);
            }
        } catch (Exception ex) {

        }
    }
    @Override
    public Unit sendUnit(Coord end) {
        try {
            synchronized (numberOfUnits) {
                this.numberOfUnits--;
                HP--;
            }
            Unit u = new Unit(player, center.randomizer(), end);
            u.setPower(1);
            return u;
        }catch (Exception ex){
            return null;
        }
    }
    @Override
    public synchronized void GetUnit(Unit uu) {
        try {
            if (uu.player.equals(player)) {
                numberOfUnits++;
                HP++;
                Game.units.remove(uu);
            } else {
                HP -= uu.power;
                if (HP <= 0) {
                    HP = 0;
                    setClaimed(uu.player);
                    if(uu.player.color.equals(MenuFrame.colors)) {
                        Ending.time += 10;
                        Store.moneyearned += 100;
                    }
                }
                numberOfUnits = HP;
                Game.units.remove(uu);
            }
        }catch (Exception ex){

        }
    }
}
