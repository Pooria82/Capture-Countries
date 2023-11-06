import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MenuFrame extends JFrame {
    private JButton offlineGameButton;
    private JButton onlineGameButton;
    private JButton storeButton;
    private JButton showRecordsButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JCheckBox backgroundMusicCheckBox; // Add a checkbox for background music
    private Clip clip;
    public static Color colors = Color.blue;
    public static GameStarter m;
    public static int nurms = 3, atts = 2, shoots = 3, defs = 2,fasts = 1;
    public static String types = "Normal", timemode = "No limit" , backgroundPath = "6.jpg";

    public MenuFrame() {
        setTitle("Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("9.jpg"))); // Set the photo background
        Image icon = Toolkit.getDefaultToolkit().getImage("menuIcon.png");
        setIconImage(icon);
        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize buttons
        offlineGameButton = new JButton("Offline Game");
        onlineGameButton = new JButton("Online Game");
        storeButton = new JButton("Store");
        showRecordsButton = new JButton("Show Records");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");
        backgroundMusicCheckBox = new JCheckBox("Background Music"); // Create the checkbox

        // Add buttons and checkbox to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(offlineGameButton, gbc);

        gbc.gridy = 1;
        add(onlineGameButton, gbc);

        gbc.gridy = 2;
        add(storeButton, gbc);

        gbc.gridy = 3;
        add(showRecordsButton, gbc);

        gbc.gridy = 4;
        add(settingsButton, gbc);

        gbc.gridy = 5;
        add(exitButton, gbc);

        gbc.gridy = 6;
        add(backgroundMusicCheckBox, gbc); // Add the checkbox to the frame

        // Add action listeners to the buttons
        offlineGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for offline game button
                // For example, start the offline game
                if (atts + nurms + defs + shoots+fasts > 3) {
                    m = new GameStarter(atts + nurms + defs + shoots+fasts);
                    m.start();
                } else {
                    JOptionPane.showMessageDialog(null, "not enough countries");
                }

            }
        });

        onlineGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for online game button
                // For example, connect to online server
            }
        });

        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for store button
                // For example, open the in-game store
                new Store();
            }
        });

        showRecordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for show records button
                // For example, display high scores or records
                Scoreboard s = new Scoreboard();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for settings button
                // For example, open the game settings menu
                new Setting();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions for exit button
                System.exit(0); // Close the application
            }
        });

        // Add action listener to the checkbox
        backgroundMusicCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (backgroundMusicCheckBox.isSelected()) {
                    playBackgroundMusic(); // Play the background music if checkbox is selected
                } else {
                    stopBackgroundMusic(); // Stop the background music if checkbox is deselected
                }
            }
        });

        // Load the background music
        loadBackgroundMusic();

        pack();
        setLocationRelativeTo(null);
    }

    private void loadBackgroundMusic() {
        try {
            File audioFile = new File("pirate-of-caribbean.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playBackgroundMusic() {
        try {
            if (!clip.isRunning()) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        try {
            if (clip.isRunning()) {
                clip.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuFrame frame = new MenuFrame();
                frame.setVisible(true);
            }
        });
    }
}
