package csis.dptw.Connect4;

import java.awt.*;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.*;
/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */

public class ConnectChip extends Entity{
    public final static int radius = 41;
    public static final Color DARK_YELLOW = new Color(200, 200, 0);
    // public static final Color; //DARK COLOR FOR SECOND CHIP GOES HERE
    Color color;
    Color ringColor;

    public ConnectChip(Game game, Point position, Color color) {
        super(game, position);
        this.color = color;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        PaintHelper.fillCircleFromMiddle(position, radius, g);
        g.setColor(new Color(200, 200, 0));
        // g.setColor(ringColor);
        g.setStroke(new BasicStroke(8));
        PaintHelper.drawCircleFromMiddle(position, radius, g);
    }
}
