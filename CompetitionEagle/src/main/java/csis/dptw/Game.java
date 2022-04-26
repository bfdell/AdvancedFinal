package csis.dptw;
import javax.swing.*;
import java.awt.*;

public class Game extends GameInput implements Runnable {
    public Map gamePanel;
    public Repainter refresher = new Repainter();
    
    public Game() {
        gamePanel = new Map(new FlowLayout());
        // javax.swing.SwingUtilities.invokeLater(new Game(new FlowLayout()));
    }

    public Game(LayoutManager layout) {
        gamePanel = new Map(layout);
    }

    @Override
    public void run() {
    }
}
