import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Setting extends JFrame {
    private JComboBox<String> countryComboBox;
    private JSpinner attackerSpinner;
    private JSpinner defenderSpinner;
    private JSpinner shooterSpinner;
    private JSpinner normalSpinner,fastSpinner;
    private JComboBox<String> timerComboBox;
    private JButton colorButton;
    private JButton backgroundButton;
    private Color selectedColor;
    public static String filePath = "null";

    public Setting() {
        setTitle("Setting");
        setContentPane(new JLabel(new ImageIcon("14.jpg")));
        Image icon = Toolkit.getDefaultToolkit().getImage("settingIcon.png");
        setIconImage(icon);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        // Country ComboBox
        JLabel countryLabel = new JLabel("Country Type:");
        countryLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        countryComboBox = new JComboBox<>(new String[]{"Attacker", "Defender", "Shooter", "Normal","Barracks"});
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(countryLabel, gbc);
        gbc.gridx = 1;
        add(countryComboBox, gbc);

        // Attacker Spinner
        JLabel attackerLabel = new JLabel("Number of Attackers: ");
        attackerLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        attackerSpinner = new JSpinner();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(attackerLabel, gbc);
        gbc.gridx = 1;
        add(attackerSpinner, gbc);

        // Defender Spinner
        JLabel defenderLabel = new JLabel("Number of Defenders: ");
        defenderLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        defenderSpinner = new JSpinner();
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(defenderLabel, gbc);
        gbc.gridx = 1;
        add(defenderSpinner, gbc);

        // Shooter Spinner
        JLabel shooterLabel = new JLabel("Number of Shooters: ");
        shooterLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        shooterSpinner = new JSpinner();
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(shooterLabel, gbc);
        gbc.gridx = 1;
        add(shooterSpinner, gbc);

        // Normal Country
        JLabel normalLabel = new JLabel("Number of Normals: ");
        normalLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        normalSpinner = new JSpinner();
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(normalLabel, gbc);
        gbc.gridx = 1;
        add(normalSpinner, gbc);

        // Fast Country
        JLabel fastLabel = new JLabel("Number of Barracks: ");
        fastLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        fastSpinner = new JSpinner();
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(fastLabel, gbc);
        gbc.gridx = 1;
        add(fastSpinner, gbc);

        // Timer ComboBox
        JLabel timerLabel = new JLabel("Timer: ");
        timerLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        timerComboBox = new JComboBox<>(new String[]{"30 seconds", "1 minute", "2 minutes", "No limit"});
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(timerLabel, gbc);
        gbc.gridx = 1;
        add(timerComboBox, gbc);

        // Color Button
        colorButton = new JButton("Select Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(Setting.this, "Select a Color", selectedColor);
                if (selectedColor != null) {
                    colorButton.setBackground(selectedColor);
                    colorButton.setForeground(getContrastingColor(selectedColor));
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(colorButton, gbc);

        // Background Button
        backgroundButton = new JButton("Select Background");
        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Setting.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundButton, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryType = (String) countryComboBox.getSelectedItem();
                int attackerCount = (int) attackerSpinner.getValue();
                int defenderCount = (int) defenderSpinner.getValue();
                int shooterCount = (int) shooterSpinner.getValue();
                int normalCount = (int) normalSpinner.getValue();
                int fastCount = (int) fastSpinner.getValue();
                String timerSelection = (String) timerComboBox.getSelectedItem();
                if(countryType.equals("Barracks")){
                    countryType = "Fast";
                }
                // Perform necessary actions with the selected values
                MenuFrame.types = countryType;
                MenuFrame.fasts = fastCount;
                MenuFrame.colors = selectedColor;
                MenuFrame.atts = attackerCount;
                MenuFrame.backgroundPath = filePath;

                if (!countryType.equals("Normal")) {
                    MenuFrame.nurms = Math.max(normalCount, 2);
                } else {
                    MenuFrame.nurms = Math.max(normalCount, 3);
                }
                MenuFrame.shoots = shooterCount;
                MenuFrame.defs = defenderCount;
                MenuFrame.timemode = timerSelection;
                Setting.this.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Color getContrastingColor(Color color) {
        int luminance = (int) ((0.299 * color.getRed()) + (0.587 * color.getGreen()) + (0.114 * color.getBlue()));
        return luminance > 128 ? Color.BLACK : Color.WHITE;
    }
}
