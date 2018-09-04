import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private Dimension coordinates;
    private int value;
    private boolean revealed;

    public Tile(int x, int y){
        setSize(new Dimension(30, 30));
        setPreferredSize(new Dimension(30, 30));
        setMaximumSize(new Dimension(30, 30));
        setMargin(new Insets(0, 0, 0, 0));

        this.coordinates = new Dimension(x, y); // I have no idea why, but the coordinates were being flipped
        this.value = 0;
        this.revealed = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValue(){
        if(this.value != -1){
            this.value++;
        }
    }

    public boolean isEmpty(){ return this.value == 0; }

    public boolean isBomb(){ return this.value == -1; }

    public boolean isNumber(){ return this.value != 0 && this.value != -1; }

    public boolean isRevealed(){ return this.revealed; }

    public void revealTile(){ this.revealed = true; }

    public Dimension getCoordinates() { return this.coordinates; }
}
