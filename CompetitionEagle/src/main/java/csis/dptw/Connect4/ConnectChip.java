package csis.dptw.Connect4;

import java.awt.*;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Player;
import csis.dptw.util.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class ConnectChip extends Entity {
    public static final int RING_WIDTH = 8;
    ConnectPlayer player;
    public final static int radius = 40;
    Color ringColor;
    public final ConnectPlayer PLAYER;

    public boolean landed = false;
    public boolean moving = false;

    public ConnectChip(Game game, Point position, ConnectPlayer player) {
        super(game, position);
        PLAYER = player;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(PLAYER.COLOR);
        PaintHelper.fillCircleFromMiddle(position, radius, g);
        g.setColor(PLAYER.DARKER_COLOR);
        g.setStroke(new BasicStroke(RING_WIDTH));
        PaintHelper.drawCircleFromMiddle(position, radius, g);
    }

    public synchronized void setLanded(boolean newLanded) {
        boolean oldLanded = landed;
        landed = newLanded;
        eventHelper.firePropertyChange("landed", oldLanded, true);
    }
}
