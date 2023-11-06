public abstract class Country extends Thread{
    public Coord center;
    public Player player;
    int speed;
    public Integer numberOfUnits,HP;

    public Coord getCenter() {
        return center;
    }
    public abstract void setClaimed(Player player);

    public void setCenter(Coord center) {
        this.center = center;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }


    public String getCountryState() {
        return state;
    }

    public void setCountryState(String state) {
        this.state = state;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String state = "unclaimed";
    public int radius = 100;        //just for shooter
    public abstract Unit sendUnit(Coord end);
    public abstract void GetUnit(Unit uu);
}
