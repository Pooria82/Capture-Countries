public class FastCountry extends Country{
    int speed = 1000;
    FastCountry(Coord center){
        try {
            this.center = center;
            this.numberOfUnits = 12;
            this.HP = this.numberOfUnits;
            this.player = Game.defaultplayer;
        }catch (Exception ex){

        }
    }
    public synchronized void setClaimed(Player player){
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
                if (numberOfUnits < 12) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP++;
                    }
                }
                sleep(speed);
            }
            while(state.equals("claimed")){
                if (numberOfUnits < 50) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP++;
                    }

                }
                sleep(speed);
            }
        }catch (Exception ex){

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
