import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private Dimension coordinates;
    private int value;
    private boolean revealed;

    /**
     * Creates a "tile" which is basically a button with a hidden value underneath, core to the game's functionality
     * @param x - x coordinate of the tile with relation to the board
     * @param y - y coordinate of the tile with relation to the board
     */
    public Tile(int x, int y){
        setSize(new Dimension(30, 30));
        setPreferredSize(new Dimension(30, 30));
        setMaximumSize(new Dimension(30, 30));
        setMargin(new Insets(0, 0, 0, 0));

        this.coordinates = new Dimension(x, y); // I have no idea why, but the coordinates were being flipped
        this.value = 0;
        this.revealed = false;
    }

    /**
     * @return the tile's value (1-9: number, 0: empty, -1: bomb)
     */
    public int getValue() {
        return value;
    }

    /**
     * setValue - sets a value to the tile
     * @param value value to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * increaseValue - increments the value of the tile by 1, used when updating board after placing bombs
     */
    public void increaseValue(){
        if(this.value != -1){
            this.value++;
        }
    }

    /**
     * @return if the tile's value is 0 (empty) or not
     */
    public boolean isEmpty(){ return this.value == 0; }

    /**
     * @return if the tile's value is -1 (bomb) or not
     */
    public boolean isBomb(){ return this.value == -1; }

    /**
     * @return if the tile's value is 1-9 (number) or not
     */
    public boolean isNumber(){ return this.value != 0 && this.value != -1; }

    /**
     * @return if the tile has already been revealed to the player or not
     */
    public boolean isRevealed(){ return this.revealed; }

    /**
     * revealTile - sets the revealed attribute to true, used when the game reveals the tile to the player
     */
    public void revealTile(){ this.revealed = true; }

    /**
     * @return the coordinates of the tile in relation to the board
     */
    public Dimension getCoordinates() { return this.coordinates; }
}
