package csis.dptw.CupPong;

import csis.dptw.engine.Player;
import java.awt.*;

public class PongPlayer extends Player {
    public static final Color DARK_RED = null;
    public static final Color DARK_YELLOW = null;

    final Color COLOR;
    final Color DARKER_COLOR;
    public PongPlayer(int playerNum, Color color) {
        super(playerNum);
        COLOR = color;
        DARKER_COLOR = COLOR.equals(Color.RED) ? DARK_RED : DARK_YELLOW; 
        
    }
    
}
