import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JPanel panel1;
    private JCheckBox checkBox1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GameScreen");
        frame.setContentPane(new GameScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}