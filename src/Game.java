import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game{
    public static ArrayList<Country> countries = new ArrayList<>();//todo x>3 , y >3 x < size-3 y , size-3//coord of countries
    public static ArrayList<Unit> units = new ArrayList<>();//coords of units;
    public static ArrayList<Point> gamePoints = new ArrayList<>();
    public static Player defaultplayer = new Player("default",Color.gray);
    public static Player p1 = new Player("p1", MenuFrame.colors);
    public static Player p2;
    public static Player p3;
    Game(int fast,int shoot , int norm , int def , int att,int urspeed ,int urnumber){
        try {
            Random rand = new Random();

            if(MenuFrame.colors.equals(Color.red)){
                p2 = new Player("bot", Color.green);
                p3 = new Player("bot", Color.blue);
            }
            else if(MenuFrame.colors.equals(Color.blue)){
                p2 = new Player("bot", Color.green);
                p3 = new Player("bot", Color.red);
            }else if(MenuFrame.colors.equals(Color.green)){
                p2 = new Player("bot", Color.red);
                p3 = new Player("bot", Color.blue);
            }
            else{
                p2 = new Player("bot", Color.green);
                p3 = new Player("bot", Color.blue);
            }
            int j = 0;
            for (int i = 0; i < norm; i++, j++) {
                Country x = new NormalCountry(new Coord(gamePoints.get(j)));
                x.start();
                countries.add(x);
            }
            int me;
            if(MenuFrame.types.equals("Normal")){
                me=rand.nextInt(norm);
            }else if(MenuFrame.types.equals("Attacker")){
                 me=rand.nextInt(norm+shoot+def , norm + shoot+def+att);
            }
            else if(MenuFrame.types.equals("Defender")){
                 me=rand.nextInt(norm+shoot,norm+shoot+def);
            }
            else if(MenuFrame.types.equals("Shooter")){
                 me=rand.nextInt(norm , norm + shoot);
            }else if(MenuFrame.types.equals("Fast")){
                me=rand.nextInt(norm+shoot+def+att , norm + shoot+def+att+fast);
            }else{
                 me=rand.nextInt(norm);
            }
            for (int i = 0; i < shoot; i++, j++) {
                Country x = new ShooterCountry(new Coord(gamePoints.get(j)));
                x.start();
                countries.add(x);
            }
            for (int i = 0; i < def; i++, j++) {
                Country x = new DefenderCountry(new Coord(gamePoints.get(j)));
                x.start();
                countries.add(x);
            }

            for (int i = 0; i < att; i++, j++) {
                Country x = new AttackerCountry(new Coord(gamePoints.get(j)));
                x.start();
                countries.add(x);
            }
            for (int i = 0; i <fast; i++, j++) {
                Country x = new FastCountry(new Coord(gamePoints.get(j)));
                x.start();
                countries.add(x);
            }
            countries.get(me).setClaimed(p1);
            if(urnumber !=-1) {
                countries.get(me).numberOfUnits = urnumber;
            }
            if(urspeed !=-1) {
                countries.get(me).speed = urspeed;
            }
            int bot,bot2;
            do{
                 bot = rand.nextInt(norm);
            }while(bot == me);
            countries.get(bot).setClaimed(p2);
            countries.get(bot).numberOfUnits = rand.nextInt(15,30);
            do{
                bot2 = rand.nextInt(norm);
            }while(bot2 == me || bot2 == bot);
            countries.get(bot2).setClaimed(p3);
            countries.get(bot2).numberOfUnits = rand.nextInt(20,35);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void setGamePoints(ArrayList<Point> p){
        try {
            gamePoints = p;
        }catch (Exception ex){

        }
    }

}
