package csis.dptw.Connect4;

import java.awt.Color;

import csis.dptw.engine.Player;

public class ConnectPlayer extends Player {
    public static final Color DARK_RED = new Color(175, 0, 0);
    public static final Color DARK_YELLOW = new Color(200, 200, 0);;
    final Color COLOR;
    final Color DARKER_COLOR;
    final String NAME;

    //MAKE SET NAMES METHOD
    public ConnectPlayer(int playerNum, Color color, String name) {
        super(playerNum);
        COLOR = color;
        DARKER_COLOR = COLOR.equals(Color.RED) ? DARK_RED : DARK_YELLOW; 
        NAME = name;
    }
    
}
