package csis.dptw;
import javax.swing.*;
import java.awt.*;

public class Game {
    public Map gamePanel;
    public Repainter refresher = new Repainter();
    
    public Game(LayoutManager layout) {
        gamePanel = new Map(layout);




    }
}
