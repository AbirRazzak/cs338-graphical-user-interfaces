import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private int height, width;
    private int minesCount;
    private Tile[][] grid;

    public Board(int x, int y, int mines){
        GridLayout layout= new GridLayout(x, y, 1, 1);
        setLayout(layout);
        this.width = x;
        this.height = y;
        this.minesCount = mines;

        this.grid = new Tile[this.width][this.height];
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Tile t = new Tile(i, j);
                t.addActionListener(this);
                this.grid[i][j] = t;
                add(this.grid[i][j]);
            }
        }

        insertBombs();
        //activateCheatMode();
        setVisible(true);
    }

    /**
     * InsertBombs - Adds bombs to the board
     */
    private void insertBombs(){
        int minesRemaining = minesCount;
        while(minesRemaining != 0){

            // Select random coordinates
            int x = (int)(Math.random()*((this.width - 1) +1));
            int y = (int)(Math.random()*((this.height - 1) +1));

            // Check if random coordinate is a bomb, if not, make it a bomb
            if(this.grid[x][y].getValue() != -1){
                Tile b = this.grid[x][y];
                b.setValue(-1);
                minesRemaining--;
                updateSurroundingTiles(b);
            }
        }
    }

    /**
     * UpdateSurroundingTile - Updates the values of all the tiles surrounding a bomb
     * ( Does not update tile numbers if tile is a bomb (-1). )
     * @param b - New bomb that needs to update surround tiles
     */
    private void updateSurroundingTiles(Tile b){
        Dimension bombCoordinates = b.getCoordinates();
        int x = bombCoordinates.width;
        int y = bombCoordinates.height;
        System.out.println("Bomb coordinates: (" + x + ", " + y + ")");
        Tile t;

        // Updates adjacent tiles to the left of bomb
        if(x > 0){
            if(y > 0){
                t = this.grid[x-1][y-1];
                t.increaseValue();
            }

            t = this.grid[x-1][y];
            t.increaseValue();

            if(y < this.height - 1){
                t = this.grid[x-1][y+1];
                t.increaseValue();
            }
        }

        // Updates adjacent tiles to the right of bomb
        if(x < this.width - 1){
            if(y > 0){
                t = this.grid[x+1][y-1];
                t.increaseValue();
            }

            t = this.grid[x+1][y];
            t.increaseValue();

            if(y < this.height - 1){
                t = this.grid[x+1][y+1];
                t.increaseValue();
            }
        }

        // Update tiles above and below bomb
        if(y > 0){
            t = this.grid[x][y-1];
            t.increaseValue();
        }
        if(y < this.height - 1){
            t = this.grid[x][y+1];
            t.increaseValue();
        }
    }

    public void resetBoard(){
        this.grid = new Tile[this.width][this.height];
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Tile t = new Tile(i, j);
                t.addActionListener(this);
                this.grid[i][j] = t;
                add(this.grid[i][j]);
            }
        }
        insertBombs();
    }


    /**
     * ActivateCheatMode - Used for testing, reveals values of all tiles
     */
    private void activateCheatMode(){
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Tile t = this.grid[i][j];
                DecodeTile(t);
            }
        }
    }


    /**
     * DecodeTile - Interprets current tile and changes it's text to match its value
     * @param t - Minesweeper Tile to decode
     */
    private void DecodeTile(Tile t){
        int val = t.getValue();

        switch (val){
            case 0:
                // Empty/blank tile
                t.setText("E");
                break;
            case -1:
                // Bomb tile
                t.setText("B");
                break;
            default:
                // Number tile
                t.setText(Integer.toString(val));
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tile t = (Tile)e.getSource();
        DecodeTile(t);
    }
}
