public class DefenderCountry extends Country{
    int speed = 2000;
    DefenderCountry(Coord center){
        try {
            this.center = center;
            this.numberOfUnits = 15;
            this.HP = this.numberOfUnits;
            this.player = Game.defaultplayer;
        }catch (Exception ex){

        }
    }
    public synchronized void setClaimed(Player player){
        try{
        this.state = "claimed";
        this.player = player;
        this.HP = this.numberOfUnits * 3;
        }catch (Exception ex){

        }
    }

    @Override
    public void run() {
        //super.run();
        try {
            //super.run();
            while (state.equals("unclaimed")) {
                if (numberOfUnits < 15) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP ++;
                    }

                }
                sleep(speed);
            }
            while(state.equals("claimed")){
                if (numberOfUnits < 50) {
                    synchronized (numberOfUnits) {
                        this.numberOfUnits++;
                        HP +=3;
                    }

                }
                sleep(speed);
            }
        }catch (Exception ex){

        }
    }
    @Override
    public Unit sendUnit(Coord end) {
        try{
        synchronized (numberOfUnits) {
            this.numberOfUnits--;
            HP -=3;
        }
        Unit u = new Unit(player,center.randomizer(),end);
        u.setPower(1);
        return u;
        }catch (Exception ex){
            return null;
        }
    }
    @Override
    public synchronized void GetUnit(Unit uu) {
        try{
            if(player.equals(Game.defaultplayer)){
                HP -= uu.power;
                if(HP <= 0){
                    setClaimed(uu.player);
                }
                numberOfUnits = HP;
                Game.units.remove(uu);
            }
            else if(uu.player.equals(player)){
                numberOfUnits++;
                HP+=3;
                Game.units.remove(uu);
            }
            else{
                HP -= uu.power;
                if(HP <= 0){
                    HP = 0;
                    setClaimed(uu.player);
                    if(uu.player.color.equals(MenuFrame.colors)) {
                        Ending.time += 10;
                        Store.moneyearned += 100;
                    }
                }
                numberOfUnits = HP/3;
                Game.units.remove(uu);
            }
        }catch (Exception ex){

        }
    }
}
