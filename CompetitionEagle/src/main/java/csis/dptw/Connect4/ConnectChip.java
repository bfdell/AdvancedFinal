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
    public final static int radius = 41;
    Color color;
    Color ringColor;
    // final int PLAYER_NUM;
    public final ConnectPlayer PLAYER;

    public ConnectChip(Game game, Point position, Color color, ConnectPlayer player) {
        super(game, position);
        this.color = color;
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
}
