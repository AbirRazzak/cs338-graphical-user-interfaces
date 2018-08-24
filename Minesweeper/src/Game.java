import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game(){
        super("Minesweeper Reborn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new Board(15, 15));

        setPreferredSize(new Dimension(4*100, 4*100));
        setResizable(false);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new Game();
    }
}
