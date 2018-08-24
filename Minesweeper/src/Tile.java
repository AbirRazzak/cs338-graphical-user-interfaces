import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private int x;
    private int y;
    private int value;

    public Tile(int column, int row){
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(25, 25));
        setMargin(new Insets(1, 1, 1, 1));
        this.x = column;
        this.y = row;
    }

    public int getValue() {
        return value;
    }
}
