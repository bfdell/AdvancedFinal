package csis.dptw.CupPong;

import java.awt.*;
import java.awt.geom.*;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;
import csis.dptw.util.PaintHelper;

/**
 * Creates a direction meter that aims the ball object
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class DirectionMeter extends Animation {
    Object lock = new Object();
    public final Point FULCRUM_POINT;

    public static final int THINKNESS = 4;
    public static final BasicStroke DASHED_LINE = new BasicStroke(THINKNESS, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_BEVEL, 1.0f);
    public final static int METER_HEIGHT = 70;
    boolean isDirectonSet = false;

    public static final int METER_WIDTH = (int) (App.gameDimension.width * PongMap.BOARD_X_PERCENT);
    public int direction = 1;
    Meter meter;
    public static final double LIMIT = (Math.PI / 6);
    public double currentRotationAmount = 0;

    private int moveCount;

    /**
     * Calls the super class animation constructor, adds an entity to game,
     * initializes the meter and fulcrum point
     * 
     * @param fulcrumPoint, the point in which the meter is roating around
     * @param game, the game in which the meter is created
     */
    public DirectionMeter(Point fulcrumPoint, Game game) {
        super(new Meter(game, fulcrumPoint, new Point(fulcrumPoint.x, fulcrumPoint.y - METER_HEIGHT)), game);
        game.addEntity(entity);
        meter = (Meter) entity;
        FULCRUM_POINT = fulcrumPoint;
    }

    /**
     * Creates a line that pivots on an arc to display aiming positions
     */
    @Override
    public void animation() {

        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {

        }

        System.out.println("moved: " + moveCount++);
        meter.rotate(direction);
        currentRotationAmount += Meter.ROTATION;
        // System.out.println(Meter.MAX_X + " " + Meter.MIN_X);
        // System.out.println(meter.endPoint.getX());

        if (meter.endPoint.getX() > Meter.MAX_X) {
            direction *= -1;
            currentRotationAmount = 0;
            // System.out.println("cahnged directions");
        }

        if (meter.endPoint.getX() < Meter.MIN_X) {
            direction *= -1;
            currentRotationAmount = 0;
            // System.out.println("cahnged directions");
        }
        // chnagne direaction if needed

    }

    /**
     * Returns true if the direction is set
     */
    @Override
    public boolean done() {
        return isDirectonSet;
    }

    static class Meter extends Entity {
        AffineTransform transformer;
        Point2D endPoint;
        Point2D topPoint;
        Arc2D.Double fullMeter;

        public static final double ROTATION = (Math.PI / 120);
        public static double MIN_X;
        public static double MAX_X;

        /**
         * Calls the super constructor Entity, initializes endpoint, fullMeter,
         * transformer, top point, testPoint, and sets the rotation of the transformer
         * to intialize MIN_X and MIN_Y
         * 
         * @param game     the current game being accessed
         * @param position starting position of the meter
         * @param endPoint the position of the end of the aiming line
         */
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

        /**
         * Draws the meter line
         * 
         * @param g graphics2d object
         */
        @Override
        public void paint(Graphics2D g) {

            g.setColor(Color.BLACK);
            g.setStroke(DASHED_LINE);
            g.drawLine(position.x, position.y, (int) topPoint.getX(), (int) topPoint.getY());
            g.drawLine(position.x, position.y, (int) endPoint.getX(), (int) endPoint.getY());
        }

        /**
         * Rotates the line pivoting at start position and saves the end point
         * 
         * @param direction of the lines rotation
         */
        public void rotate(int direction) {
            transformer.setToRotation(direction * ROTATION, position.x, position.y);
            endPoint = transformer.transform(topPoint, endPoint);
        }
    }
}
