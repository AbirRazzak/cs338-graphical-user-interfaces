import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Board extends JPanel implements ActionListener, MouseListener {

    private int height, width;
    private int minesCount;
    private Tile[][] grid;

    public Board(int x, int y, int mines){
        GridLayout layout= new GridLayout(x,y);
        layout.setVgap(0);
        setLayout(layout);
        this.width = x;
        this.height = y;
        this.minesCount = mines;

        this.grid = new Tile[this.width][this.height];
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                Tile t = new Tile(j, i);
                t.addActionListener(this);
                t.addMouseListener(this);
                this.grid[j][i] = t;
                add(this.grid[j][i]);
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
        this.removeAll();
        this.revalidate();
        this.repaint();
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
                t.setText("");
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

        t.setEnabled(false);
        t.revealTile();
    }

    /**
     * RevealAdjacentNumbers - Reveals all number tiles adjacent to the tile given
     * @param t - a empty tile that has been called with RevealTile()
     */
    private void RevealAdjacentNumbers(Tile t){
        Dimension tileCoordinates = t.getCoordinates();
        int x = tileCoordinates.width;
        int y = tileCoordinates.height;
        Tile n;

        // Reveals number tiles to the left of empty tile
        if(x > 0){
            if(y > 0){
                n = this.grid[x-1][y-1];
                if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
            }

            n = this.grid[x-1][y];
            if(!n.isRevealed() && n.isNumber()) DecodeTile(n);

            if(y < this.height - 1){
                n = this.grid[x-1][y+1];
                if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
            }
        }

        // Reveals number tiles to the right of empty tile
        if(x < this.width - 1){
            if(y > 0){
                n = this.grid[x+1][y-1];
                if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
            }

            n = this.grid[x+1][y];
            if(!n.isRevealed() && n.isNumber()) DecodeTile(n);

            if(y < this.height - 1){
                n = this.grid[x+1][y+1];
                if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
            }
        }

        // Reveals number tiles above and below empty tile
        if(y > 0){
            n = this.grid[x][y-1];
            if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
        }
        if(y < this.height - 1){
            n = this.grid[x][y+1];
            if(!n.isRevealed() && n.isNumber()) DecodeTile(n);
        }
    }

    /**
     * RevealTile - Reveals a tile, and all adjacent empty tiles
     * @param t - a number or empty tile, that the user has clicked on
     */
    private void RevealTile(Tile t){
        DecodeTile(t);
        if(t.isEmpty()) RevealAdjacentNumbers(t);

        Dimension tileCoordinates = t.getCoordinates();
        int x = tileCoordinates.width;
        int y = tileCoordinates.height;
        Tile e;

        // Reveals adjacent empty tiles to the left of tile
        if(x > 0){
            if(y > 0){
                e = this.grid[x-1][y-1];
                if(e.isEmpty() && !e.isRevealed()){
                    RevealTile(e);
                }
            }

            e = this.grid[x-1][y];
            if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);

            if(y < this.height - 1){
                e = this.grid[x-1][y+1];
                if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);
            }
        }

        // Reveals adjacent empty tiles to the right of tile
        if(x < this.width - 1){
            if(y > 0){
                e = this.grid[x+1][y-1];
                if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);
            }

            e = this.grid[x+1][y];
            if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);

            if(y < this.height - 1){
                e = this.grid[x+1][y+1];
                if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);
            }
        }

        // Reveals adjacent empty tiles above and below tile
        if(y > 0){
            e = this.grid[x][y-1];
            if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);
        }
        if(y < this.height - 1){
            e = this.grid[x][y+1];
            if(e.isEmpty()&& !e.isRevealed()) RevealTile(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tile t = (Tile)e.getSource();
        System.out.println("User clicked on tile: (" + t.getCoordinates().width + ", " + t.getCoordinates().height + ")");
        if(t.isBomb()){
            DecodeTile(t);
        }
        else {
            t.setIcon(null);
            RevealTile(t);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }

    @Override
    public void mouseClicked(MouseEvent e){
        Tile t = (Tile)e.getSource();

        if(e.getButton() == MouseEvent.BUTTON3){
            if(t.getIcon() == null){
                Image flag = null;
                try {
                    flag = ImageIO.read(getClass().getResource("./flag.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                t.setIcon(new ImageIcon(flag));
                System.out.println("User flagged tile: (" + t.getCoordinates().width + ", " + t.getCoordinates().height + ")");
            }
            else{
                System.out.println("User unflagged tile: (" + t.getCoordinates().width + ", " + t.getCoordinates().height + ")");
                t.setIcon(null);
            }
        }
    }
}
