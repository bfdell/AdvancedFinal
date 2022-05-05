package csis.dptw.CupPong;

import java.awt.*;
import java.awt.geom.*;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;
import csis.dptw.util.PaintHelper;

public class DirectionMeter extends Animation {

    public final Point FULCRUM_POINT;
    
    public static final int THINKNESS = 4;
    public static final BasicStroke DASHED_LINE = new BasicStroke(THINKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 1.0f);
    public final static int METER_HEIGHT = 70;
    boolean directonSet;

    // Chechk later
    public static final int METER_WIDTH = (int) (App.gameDimension.width * PongMap.BOARD_X_PERCENT);
    public boolean moovingLeft = true;

    public DirectionMeter(Point fulcrumPoint, Game game) {
        super(new Meter(game, fulcrumPoint, new Point(fulcrumPoint.x, fulcrumPoint.y - METER_HEIGHT)), game);
        game.addEntity(entity);
        FULCRUM_POINT = fulcrumPoint;
    }

    @Override
    public void animation() {
        try {
            wait(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public boolean done() {
        return directonSet;
    }

    private static class Meter extends Entity {
        Point endPoint;
        Arc2D.Double fullMeter;
        Path2D.Double path;
        PathIterator pi;
        Point2D p1;
        Point2D p2;

        public Meter(Game game, Point position, Point endPoint) {
            super(game, position);
            this.endPoint = endPoint;
            ///////
            p1 = endPoint;
            fullMeter = PaintHelper.makeArcFromMiddle(position, METER_WIDTH, 55.0, 70.0, Arc2D.PIE);
            path = new Path2D.Double(fullMeter);
            pi = path.getPathIterator(new AffineTransform());
            AffineTransform af = new AffineTransform();
            af.setToRotation(-1 * (Math.PI / 4), position.x, position.y);
            p2 = af.transform(p1, p2);
            pi.next();
        }

        @Override
        public void paint(Graphics2D g) {
            // g.setStroke(DASHED_LINE);
            // g.drawLine(position.x, position.y, endPoint.x, endPoint.y);
            // g.setStroke(new BasicStroke(1));
            // g.fill(fullMeter);
            // g.fill(path);
            g.setColor(Color.BLACK);
            g.drawLine(position.x, position.y, (int) p1.getX(), (int) p1.getY());
            g.drawLine(position.x, position.y, (int) p2.getX(), (int) p2.getY());
            
        }
    }
}
