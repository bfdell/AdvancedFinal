package csis.dptw.CupPong;

import java.awt.geom.*;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.PaintHelper;

import java.awt.*;

/**
 *
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class Ball extends Entity {

    Point2D.Double ballPos;
    public double size = 10;

    /**
     * Creates entity Ball with call to super constructor Entity and initializes
     * ballPos
     * 
     * @param game, the game in which the entity is being created
     * @param position, the position of the entity
     */
    public Ball(Game game, Point position) {
        super(game, position);
        ballPos = new Point2D.Double(position.x, position.y);
    }

    /**
     * Paints the Ball
     * @param g, the graphics object
     */
    public void paint(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        PaintHelper.drawCircleFromMiddleDouble(getPosition(), size, g);
        g.setColor(Color.WHITE);
        PaintHelper.fillCircleFromMiddleDouble(getPosition(), size, g);
    }

    /**
     * returns the position of the ball as a Point2D object
     * @return the position as a Point2D object
     */
    @Override
    public Point2D.Double getPosition() {
        return ballPos;
    }

}
