import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ending extends Thread{
    public static int time = -10000;
    String score;
    Ending(){
        if(MenuFrame.timemode.equals("30 seconds")){
            time = 30;
        }
        if(MenuFrame.timemode.equals("1 minute")){
            time = 60;
        }
        if(MenuFrame.timemode.equals("2 minutes")){
            time = 120;
        }
    }
    public void file(){
        String name = JOptionPane.showInputDialog(null,"your name:");
        if(name != null && !name.equals("")) {
            //nothing for now
        }
        else {
            name = "Nameless";
        }
        saveScore(name,score);
        savemoney(1000+Store.moneyearned);
    }
    public void savemoney(int money) {
        try {
            File myFile = new File("money.txt");
            if(myFile.createNewFile()) {

            }
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write(Integer.toString(money));
            myWriter.close();
        }
        catch (IOException ex) {

        }
    }
    public void saveScore(String uName,String uScore) {
        try {
            File myFile = new File("scoreboard.txt");
            if(myFile.createNewFile()) {

            }
            FileWriter myWriter = new FileWriter(myFile,true);
            myWriter.write(uName + " :" + uScore +"\n");
            myWriter.close();
        }
        catch (IOException ex) {

        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                ArrayList<Country> tempc;
                synchronized (Game.countries) {
                    tempc = Game.countries;
                }
                boolean ended = true;
                for (Country cc : tempc) {
                    if (cc.player.color.equals(MenuFrame.colors)) {
                        ended = false;
                    }
                }
                if (ended) {
                    score = "lost";
                    file();
                    //file
                    System.exit(0);

                }
                ended = true;
                for (Country cc : tempc) {
                    if (!(cc.player.color.equals(MenuFrame.colors) || cc.player.color.equals(Game.defaultplayer.color))) {
                        ended = false;
                    }
                }
                if (ended) {
                    score = "won";
                    file();
                    //file
                    System.exit(0);
                }
                sleep(1000);
                if(time != -10000){
                    time--;
                    GameStarter.timer.setForeground(Color.black);
                    GameStarter.timer.setText(String.valueOf(time));
                    if(time == 0){
                        score = "lost(time's up)";
                        file();
                        //file
                        System.exit(0);
                    }
                }else{
                    GameStarter.timer.setForeground(Color.black);
                    GameStarter.timer.setText(" ");
                }

            }
        }catch (Exception ex){

        }
    }
}
