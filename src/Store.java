import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.Font;
import java.util.Scanner;

public class Store extends JFrame {
    public static int totalMoney = 1000;
    public static int moneyearned = 0;
    public static  int unitCount = 10;
    public static  double produceSpeed = 1.0;

    private JLabel coinLabel;
    private JLabel unitLabel;
    private JLabel speedLabel;

    public Store() {
        try {
            File myFile = new File("money.txt");
            if(!myFile.exists()) {

            }
            Scanner fReader = new Scanner(myFile);
            while (fReader.hasNextLine()) {
                String data = fReader.nextLine();
                totalMoney = Integer.parseInt(data);
                break;
            }

            fReader.close();

        }
        catch (IOException ex) {
            totalMoney = 1000;
        }
        setTitle("Store");
        setResizable(false);
        // Background image
        setContentPane(new JLabel(new ImageIcon("7.jpg")));
        Image icon = Toolkit.getDefaultToolkit().getImage("storeIcon.png");
        setIconImage(icon);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Money label
        coinLabel = new JLabel("Coins: " + totalMoney + "$");
        coinLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        coinLabel.setForeground(Color.GREEN);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(coinLabel, constraints);

        add(new JLabel(), constraints);  // Empty label

        // Start Units label
        unitLabel = new JLabel("Start Units: " + unitCount);
        unitLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        unitLabel.setForeground(Color.BLUE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(unitLabel, constraints);

        // Unit button
        JButton unitButton = new JButton("100$");
        unitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalMoney >= 100) {
                    unitCount++;
                    unitLabel.setText("Start Units: " + unitCount);
                    totalMoney -= 100;
                    coinLabel.setText("Coins: " + totalMoney + "$");
                }
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(unitButton, constraints);

        // Produce speed label
        speedLabel = new JLabel("Produce speed (unit per second): " + formatDecimal(produceSpeed));
        speedLabel.setFont(new Font("Georgia" , Font.BOLD , 24));
        speedLabel.setForeground(Color.RED);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(speedLabel, constraints);

        // Speed button
        JButton speedButton = new JButton("100$");
        speedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalMoney >= 100) {
                    produceSpeed += 0.08;
                    speedLabel.setText("Produce speed (unit per second): " + formatDecimal(produceSpeed));
                    totalMoney -= 100;
                    coinLabel.setText("Coins: " + totalMoney + "$");
                }
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(speedButton, constraints);

        pack();

        // Center the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);

        setVisible(true);
    }

    private String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }
}
