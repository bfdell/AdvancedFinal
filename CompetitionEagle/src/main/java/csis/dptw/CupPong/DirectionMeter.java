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
    Object lock = new Object();
    public final Point FULCRUM_POINT;

    public static final int THINKNESS = 4;
    public static final BasicStroke DASHED_LINE = new BasicStroke(THINKNESS, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_BEVEL, 1.0f);
    public final static int METER_HEIGHT = 70;
    boolean isDirectonSet = false;

    // Chechk later
    public static final int METER_WIDTH = (int) (App.gameDimension.width * PongMap.BOARD_X_PERCENT);
    // public boolean moovingLeft = true;
    public int direction = 1;
    Meter meter = (Meter) entity;
    public static final double LIMIT = (Math.PI / 6);
    public double currentRotationAmount = 0;

    private int moveCount;
    public DirectionMeter(Point fulcrumPoint, Game game) {
        super(new Meter(game, fulcrumPoint, new Point(fulcrumPoint.x, fulcrumPoint.y - METER_HEIGHT)), game);
        game.addEntity(entity);
        FULCRUM_POINT = fulcrumPoint;
    }

    @Override
    public void animation() {
        synchronized (lock) {

            try {
                sleep(Repainter.DELAY_TIME);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("moved: " + moveCount++);
        meter.rotate(direction);
        currentRotationAmount += Meter.ROTATION;
        System.out.println(Meter.MAX_X + " " + Meter.MIN_X);
        System.out.println(meter.endPoint.getX());
        
        if (meter.endPoint.getX() > Meter.MAX_X) {
            direction *= -1;
            currentRotationAmount = 0;
            System.out.println("cahnged directions");
        }

        if (meter.endPoint.getX() < Meter.MIN_X) {
            direction *= -1;
            currentRotationAmount = 0;
            System.out.println("cahnged directions");
        }
        // chnagne direaction if needed

    }

    @Override
    public boolean done() {
        return isDirectonSet;
    }

    private static class Meter extends Entity {
        AffineTransform transformer;
        Point2D endPoint;
        Point2D topPoint;
        Arc2D.Double fullMeter;

        public static final double ROTATION = (Math.PI / 120);
        public static double MIN_X;
        public static double MAX_X;

        public Meter(Game game, Point position, Point endPoint) {
            super(game, position);
            this.endPoint = endPoint;
            ///////
            fullMeter = PaintHelper.makeArcFromMiddle(position, METER_WIDTH, 55.0, 70.0, Arc2D.PIE);
            transformer = new AffineTransform();
            // path = new Path2D.Double(fullMeter);

            topPoint = endPoint;
            Point2D testPoint = new Point();
            transformer.setToRotation(-1 * (Math.PI / 4), position.x, position.y);
            MIN_X = ((Point) transformer.transform(topPoint, testPoint)).getX();
            transformer.setToRotation((Math.PI / 4), position.x, position.y);
            MAX_X = ((Point) transformer.transform(topPoint, testPoint)).getX();
            
            System.out.println(MAX_X + "__________" + MIN_X);
            transformer.setToRotation(ROTATION, position.x, position.y);
            topPoint = transformer.transform(endPoint, topPoint);
        }

        @Override
        public void paint(Graphics2D g) {
            // g.setStroke(DASHED_LINE);
            // g.drawLine(position.x, position.y, endPoint.x, endPoint.y);
            // g.setStroke(new BasicStroke(1));
            // g.fill(fullMeter);
            // g.fill(path);
            g.setColor(Color.BLACK);
            g.setStroke(DASHED_LINE);
            g.drawLine(position.x, position.y, (int) topPoint.getX(), (int) topPoint.getY());
            g.drawLine(position.x, position.y, (int) endPoint.getX(), (int) endPoint.getY());
        }

        public void rotate(int direction) {
            transformer.setToRotation(direction * ROTATION, position.x, position.y);
            endPoint = transformer.transform(topPoint, endPoint);
        }
    }
}
