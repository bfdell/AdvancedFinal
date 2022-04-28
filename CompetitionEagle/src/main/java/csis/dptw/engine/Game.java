package csis.dptw.engine;
import javax.swing.*;

import java.awt.*;

public class Game extends GameInput implements Runnable {
    public Map gamePanel;
    public Repainter refresher = new Repainter();
    
    public java.util.List<Object> events = new java.util.ArrayList<Object>();
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
