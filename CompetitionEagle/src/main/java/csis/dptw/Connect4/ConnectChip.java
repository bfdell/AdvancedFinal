package csis.dptw.Connect4;

import java.awt.*;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.*;

/**
 * Creates the entity chips for connect4
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */
public class ConnectChip extends Entity {
    ConnectPlayer player;
    Color ringColor;
    public final ConnectPlayer PLAYER;

    public boolean landed = false;
    public boolean moving = false;

    /**
     * Calls the super class for entity and initializes player.
     * 
     * @param game,the  game in which the entity is being created
     * @param position, the position of where the entity is being created
     * @param player,   player in connect 4 game
     */
    public ConnectChip(Game game, Point position, ConnectPlayer player) {
        super(game, position);
        PLAYER = player;
    }

    /**
     * Paints the chip
     * 
     * @param g, the graphics object
     */
    @Override
    public void paint(Graphics2D g) {
        g.setColor(PLAYER.COLOR);
        PaintHelper.fillCircleFromMiddle(position, Connect4.CHIP_RADIUS, g);
        g.setColor(PLAYER.DARKER_COLOR);
        g.setStroke(new BasicStroke(Connect4.RING_WIDTH));
        PaintHelper.drawCircleFromMiddle(position, Connect4.CHIP_RADIUS, g);
    }

    /**
     * Setter method for landed instance variable that also fires property change
     * listners
     * 
     * @param newLanded, The new value landed is being set to
     */
    public synchronized void setLanded(boolean newLanded) {
        boolean oldLanded = landed;
        landed = newLanded;
        eventHelper.firePropertyChange("landed", oldLanded, true);
    }
}
