import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private Dimension coordinates;
    private int value;

    public Tile(int column, int row){
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(25, 25));
        setMargin(new Insets(1, 1, 1, 1));

        coordinates = new Dimension(column, row);
        this.value = 0;
    }

    public Tile(int column, int row, int val){
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(25, 25));
        setMargin(new Insets(1, 1, 1, 1));

        coordinates = new Dimension(column, row);
        value = val;
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

    public Dimension getCoordinates() { return coordinates; }
}
