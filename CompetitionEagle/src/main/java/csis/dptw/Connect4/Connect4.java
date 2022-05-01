package csis.dptw.Connect4;

import csis.dptw.engine.*;
import csis.dptw.engine.Event.EventType;

import java.awt.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Connect4 extends Game {

    boolean[][] spaces = new boolean[6][7];
    int first = 1;
    public Connect4() {
        super();//FIX TIHIS LINE BECCUSE GAMAE PANEL IS BEING INITIOALIZED IN ANONYMOUS INNER CLASS
        run();
    }

    @Override
    public void run() {
        super.run();
        
        
        addMouseEvent(EventType.MRELEASED, (e) -> myFirst(this), 2);
        gamePanel.addEntity(new ConnectChip(this, new Point(75, 75), Color.YELLOW));
        // gamePanel.addEntity(new ConnectChip(this, new Point(120, 51), Color.ORANGE));
        // gamePanel.addEntity(new ConnectChip(this, new Point(51, 120), Color.ORANGE));
        gamePanel.repaint();
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new FlowLayout(), 800, 800);
    }

    public void myFirst(Connect4 game) {
        System.out.println(game.first++ + ",");
    }
    public void startGame() {

    }
}
