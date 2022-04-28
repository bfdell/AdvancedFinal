/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
package csis.dptw.Connect4;


import csis.dptw.engine.Game;
import csis.dptw.engine.Map;

import java.awt.*;

public class Connect4 extends Game {
    
    boolean[][] spaces  = new boolean[6][7];
    public Connect4() {
        super();//FIX TIHIS LINE BECCUSE GAMAE PANEL IS BEING INITIOALIZED IN ANONYMOUS INNER CLASS
        run();
    }



    @Override
    public void run() {
        gamePanel = new ConnectBoard(new FlowLayout());
        gamePanel.setPreferredSize(new Dimension(800, 800));

        gamePanel.addEntity(new ConnectChip(this, new Point(75, 75), Color.YELLOW));
        // gamePanel.addEntity(new ConnectChip(this, new Point(120, 51), Color.ORANGE));
        // gamePanel.addEntity(new ConnectChip(this, new Point(51, 120), Color.ORANGE));
        gamePanel.repaint();
    }

    public void startGame() {

    }
}
