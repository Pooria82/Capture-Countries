import javax.swing.*;

public class GameStarter extends Thread {
    public static MyFrame myf;
    public static CheckUnit cu;
    public static Ending end;
    public int number = 10;
    public static JLabel timer = new JLabel("timer");

    public GameStarter(int num) {
        number = num;
    }

    @Override
    public void run() {
        try {

            myf = new MyFrame(number);
            myf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Game(MenuFrame.fasts, MenuFrame.shoots, MenuFrame.nurms, MenuFrame.defs, MenuFrame.atts, (int) (3000 / Store.produceSpeed), Store.unitCount);
            cu = new CheckUnit();
            cu.start();
            end = new Ending();
            end.start();
            timer.setBounds(1300, 700, 200, 50);
            timer.setHorizontalAlignment(SwingConstants.CENTER);
            timer.setVerticalAlignment(SwingConstants.CENTER);
            myf.add(timer);
            myf.add(new CountryPanel(myf.points));
            myf.add(new Slider());
            myf.setVisible(true);
            while (true) {
                SwingUtilities.updateComponentTreeUI(myf);
            }
        } catch (Exception ex) {

        }
    }


}