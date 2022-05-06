package csis.dptw.CupPong;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

import java.awt.*;
import java.awt.geom.*;

public class BallAnimation extends Animation {
    public static final int PONG_DELAY = 10;
    public static final int MAX_SIZE = 20;
    public static final int MIN_SIZE = 5;
    public final double GROWTH_RATE;
    public final double SHRINKING_RATE;
    public static final int BALL_SPEED = 7;
    public final int TOP_DIFFERENCE = 80;
    public static final int TOTAL_FRAMES = 90;

    public int currentFrame = 1;
    Point2D destination;

    public Point2D abovePoint;
    Ball ball;
    boolean descending = false;

    double slope;
    int currentX = 0;
    double direction;
    double amountIterations;

    double dx; 
    double dy;

    public BallAnimation(Entity entity, Game game, Point2D destination, double slope, double totalDy) {
        super(entity, game);
        this.destination = destination;
        
        this.slope = slope;
        ball = (Ball) entity;
        direction = destination.getX() < ball.getPosition().getX() ? -1 : 1;
        this.slope = destination.getX() < ball.getPosition().getX() ? -1 * slope : slope;

         dy = TOTAL_FRAMES / slope; 
        //  dx = TOTAL_FRAMES / 

        amountIterations = -1 * (totalDy + TOP_DIFFERENCE) / this.slope;
        System.out.println(amountIterations);
        abovePoint = new Point2D.Double(destination.getX() + TOP_DIFFERENCE, destination.getY() - TOP_DIFFERENCE);
        
        // GROWTH_RATE = (MAX_SIZE - ball.size) / amountIterations;
        GROWTH_RATE = .1;

        SHRINKING_RATE = GROWTH_RATE * 5;
        
    }

    @Override
    public Ball getEntity() {
        return (Ball) entity;
    }

    @Override
    public void animation() {
        try {
            sleep(PONG_DELAY);
        } catch (InterruptedException e) {
        }

        if (!descending) {
            ball.size += GROWTH_RATE;

            ball.getPosition().setLocation(ball.getPosition().getX() + direction, ball.getPosition().getY() + slope);

            System.out.println(ball.getPosition());
            if (abovePoint.getY() > ball.getPosition().getY()) {
                descending = true;
                direction = (direction * -1)/2;
            } 
        } else {
            ball.size -= SHRINKING_RATE;
            ball.getPosition().setLocation(ball.getPosition().getX() - direction, ball.getPosition().getY() - slope);
        }

        currentFrame++;
    }

    @Override
    public boolean done() {
        if (descending && ball.getPosition().getY() >= destination.getY()) {
            ball.setLanded(true);
            return true;
        }
        return false;
    }

    public static double[] calculateSlope(Point2D p1, Point2D p2) {
        double totalRun = p1.getX() - p2.getX();
        double totalRise = -1 * Math.abs(p1.getY() - p2.getY());

        double rise = totalRise / TOTAL_FRAMES;
        double run = totalRun / TOTAL_FRAMES;

        return new double[] { rise, run };
    }

    public int nextX() {
        return 0;
    }

    public int nextY() {
        return 0;
    }
}
