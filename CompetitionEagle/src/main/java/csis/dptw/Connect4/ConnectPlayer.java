package csis.dptw.Connect4;

import java.awt.Color;

import csis.dptw.engine.Player;

/**
 *
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class ConnectPlayer extends Player {
    public static final Color DARK_RED = new Color(175, 0, 0);
    public static final Color DARK_YELLOW = new Color(210, 210, 0);;
    final Color COLOR;
    final Color DARKER_COLOR;
    final String NAME;

    /**
     * Constructs a player for Connect4
     * @param playerNum, The number of the player
     * @param color, The color of the players chip
     * @param name, The name of the player.
     * 
     */
    public ConnectPlayer(int playerNum, Color color, String name) {
        super(playerNum);
        COLOR = color;
        DARKER_COLOR = COLOR.equals(Color.RED) ? DARK_RED : DARK_YELLOW; 
        NAME = name;
    }
}
