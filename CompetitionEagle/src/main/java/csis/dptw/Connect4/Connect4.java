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
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLS = 7;
    boolean[][] spaces = new boolean[6][7];
    Point[][] circlePoints = new Point[6][7];
    int first = 1;
    // final Player PLAYER1;
    // final Player PLAYER2;

    int turnCounter = 1;

    final ConnectPlayer[] PLAYERS = new ConnectPlayer[2];

    public Connect4() {
        super();
        PLAYERS[0] = new ConnectPlayer(1, Color.RED);
        PLAYERS[1] = new ConnectPlayer(2, Color.YELLOW);
        run();
    }

    @Override
    public void run() {
        super.run();

        addMouseEvent(EventType.MRELEASED, (e) -> myFirst(this), 2);
        gamePanel.addEntity(new ConnectChip(this, new Point(175, 175), Color.RED, PLAYERS[turnCounter % 2]));
        // gamePanel.addEntity(new ConnectChip(this, new Point(120, 51), Color.ORANGE));
        // gamePanel.addEntity(new ConnectChip(this, new Point(51, 120), Color.ORANGE));
        gamePanel.repaint();
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new FlowLayout(), circlePoints, NUM_ROWS, NUM_COLS);
    }

    public void myFirst(Connect4 game) {
        System.out.println(game.first++ + ",");
    }

    public void startGame() {

    }
}
