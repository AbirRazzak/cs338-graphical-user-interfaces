import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private int height, width;
    private int numMines;
    private Tile[][] grid;

    public Board(int x, int y){
        GridLayout layout= new GridLayout(x, y, 1, 1);
        setLayout(layout);
        this.width = x;
        this.height = y;

        this.grid = new Tile[this.width][this.height];
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Tile t = new Tile(i, j);
                t.addActionListener(this);
                this.grid[i][j] = t;
                add(this.grid[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
