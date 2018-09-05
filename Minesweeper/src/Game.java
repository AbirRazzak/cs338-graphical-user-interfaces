import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Game extends JPanel {
    /**
     * Game - Creates a game, which is a panel with panels inside of it added to the main frame of the application
     */
    public Game(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /* Create the board */
        Board board = new Board(8, 8, 10);

        /* Create the top panel */
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        // Create reset button
        JButton resetButton = new JButton( new AbstractAction(""){
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetBoard();
            }
        });
        Image smilely = null;
        try {
            smilely = ImageIO.read(getClass().getResource("./smile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetButton.setIcon(new ImageIcon(smilely));
        resetButton.setPreferredSize(new Dimension(30, 30));
        resetButton.setMinimumSize(new Dimension(25, 25));
        resetButton.setMargin(new Insets(0, 0, 0, 0));
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(resetButton);

        // Add panels to the game
        add(topPanel);
        add(board);

        setVisible(true);
    }

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
