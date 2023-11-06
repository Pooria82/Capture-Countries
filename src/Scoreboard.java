import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Scoreboard extends JFrame{
    JTextPane board;
    JScrollPane Board;
    StyledDocument doc;
    String text;
    public Scoreboard() {
        //icons
        BufferedImage img4 = null;
        try {
            img4 = ImageIO.read(new File("S.jfif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg4 = img4;
        //----text geration
        text = loadScore();
        //----- board
        doc =new DefaultStyledDocument();
        board = new JTextPane(doc);
        setText(text);
        board.setEditable(false);
        board.setBackground(Color.black);
        JPanel nowarp = new JPanel(new BorderLayout());
        nowarp.add(board);
        Board = new JScrollPane(nowarp);

        //------ frame
        this.setVisible(true);
        this.setTitle("ScoreBoard");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(true);
        this.setIconImage(dimg4);
        this.getContentPane().setBackground(Color.white);
        this.add(Board);
//        this.setLocationRelativeTo(null);
        this.pack();
        board.setHighlighter(null);
    }
    public void setText(String T) {
        board.setText(T);
        SimpleAttributeSet set = new SimpleAttributeSet();
        int fontSize = 18;//change
        Random rand = new Random();
        Color[] c = {Color.red , Color.blue, Color.orange, Color.cyan, Color.lightGray, Color.pink, Color.green, Color.magenta};
        StyleConstants.setForeground(set, Color.yellow);
        StyleConstants.setBold(set, true);
        for(int i = 0; i < board.getDocument().getLength(); i++) {
            StyleConstants.setBackground(set, Color.black);
            StyleConstants.setFontSize(set, fontSize);
            if(T.charAt(i) == '\n') {
                StyleConstants.setBold(set, false);
                StyleConstants.setForeground(set, c[rand.nextInt(c.length)]);
            }

            doc.setCharacterAttributes(i, 1, set, true);
        }
    }
    public String loadScore() {
        String ans = "            << ScoreBoard >>            \n";
        try {
            File myFile = new File("scoreboard.txt");
            if(!myFile.exists()) {
                this.dispose();
            }
            Scanner fReader = new Scanner(myFile);
            while (fReader.hasNextLine()) {
                String data = fReader.nextLine();
                ans += (" \u25C8 " + data + '\n');
            }
            ans = ans.substring(0,ans.length() - 1);
            fReader.close();

        }
        catch (IOException ex) {
            this.dispose();
        }
        return ans;
    }
}
