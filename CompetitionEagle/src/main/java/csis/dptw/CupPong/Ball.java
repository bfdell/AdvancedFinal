package csis.dptw.CupPong;

import java.awt.geom.*;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.PaintHelper;

import java.awt.*;

public class Ball extends Entity{

    Point2D.Double ballPos;
    public double size = 10;
    public Ball(Game game, Point position) {
        super(game, position);
        ballPos = new Point2D.Double(position.x, position.y);
    }
    
    public void paint(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        PaintHelper.drawCircleFromMiddleDouble(getPosition(), size,  g);
        g.setColor(Color.WHITE);
        PaintHelper.fillCircleFromMiddleDouble(getPosition(),  size,  g);
    }

    @Override
    public Point2D.Double getPosition() {
        return ballPos;
    }

}
