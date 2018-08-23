import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private int x, y, value;

    public Tile(int column, int row){
        setPreferredSize(new Dimension(30, 30));
        setMargin(new Insets(1, 1, 1, 1));
        this.x = column;
        this.y = row;
    }
}
