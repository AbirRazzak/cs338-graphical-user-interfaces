import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    public Game(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create board
        Board board = new Board(8, 8, 10);

        // Create the top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        // Create reset button
        JButton resetButton = new JButton( new AbstractAction("Reset"){
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetBoard();
            }
        });
        resetButton.setText("R");
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
        JFrame frame = new JFrame("Minesweeper Reborn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(4*100, 4*100));
        frame.setResizable(false);

        Game game = new Game();
        frame.add(game);

        frame.pack();
        frame.setVisible(true);
    }
}
