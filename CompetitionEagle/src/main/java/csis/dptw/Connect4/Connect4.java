package csis.dptw.Connect4;

import csis.dptw.CupPong.PongPlayer;
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
    Point[][] circlePoints;
    int first = 1;
    // final Player PLAYER1;
    // final Player PLAYER2;

    int turnCounter = 1;

    final Player[] PLAYERS;
    public Connect4() {
        super();
        for(int i = 0; i < 2; i++) {
            
        }
        run();
    }

    @Override
    public void run() {
        super.run();
        PLAYERS = null;
        
        addMouseEvent(EventType.MRELEASED, (e) -> myFirst(this), 2);
        gamePanel.addEntity(new ConnectChip(this, new Point(75, 75), Color.YELLOW, PLAYERS[turnCounter & 2]));
        // gamePanel.addEntity(new ConnectChip(this, new Point(120, 51), Color.ORANGE));
        // gamePanel.addEntity(new ConnectChip(this, new Point(51, 120), Color.ORANGE));
        gamePanel.repaint();
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new FlowLayout(), 800, 800, circlePoints);
    }

    public void myFirst(Connect4 game) {
        System.out.println(game.first++ + ",");
    }
    public void startGame() {

    }
}
