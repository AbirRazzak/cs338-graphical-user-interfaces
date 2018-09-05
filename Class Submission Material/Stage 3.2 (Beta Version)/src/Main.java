import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        // Create the application frame
        JFrame frame = new JFrame("Minesweeper Reborn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(9*30, 10*30));
        frame.setResizable(false);

        // Add the game to the frame
        Game game = new Game();
        frame.add(game);

        // Pack and show
        frame.pack();
        frame.setVisible(true);

    }
}
