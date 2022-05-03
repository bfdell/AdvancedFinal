package csis.dptw.CupPong;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

import java.awt.*;

public class BallAnimation extends Animation {
    public static final int TOTAL_FRAMES = 400;
    public int currentFrame =  1;
    Point destination;
    public static final int BALL_SPEED = 7;

    public final int TOP_DIFFERENCE = 20;
    public Point abovePoint;
    public final int MAX_SIZE = 20;
    public final int MIN_SIZE = 5;
    public final int GROWTH_RATE = 2;
    Ball ball;
    boolean descending = false;

    int dx1;
    int dy1;
    public BallAnimation(Entity entity, Game game, Point destination) {
        super(entity, game);
        this.destination = destination;    
        ball = (Ball) entity;
        abovePoint = new Point(destination.x + TOP_DIFFERENCE, destination.y + TOP_DIFFERENCE);
        
        int[] riseOverRun = calculateSlope(ball.position, abovePoint);
        dx1 = riseOverRun[0];
        dy1 = riseOverRun[1];
    }  

    @Override
    public void animation(){
        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {
        }
        if(!descending) {
            ball.size += 2;
            ball.position.x += dx1;
            ball.position.y += dy1;

            if(ball.size > MAX_SIZE) {
                descending = true;
            }
        } else {
            if() {
            
            }
        }

        currentFrame++;
    }

    @Override
    public boolean done(){
       return currentFrame < TOTAL_FRAMES;
    }

    public static int[] calculateSlope(Point p1, Point p2) {
        int rise = p2.x - p1.x;
        int run = p2.y - p2.y;

        return new int[] {rise, run};
    }

    public int nextX() {
        return 0;
    }

    public int nextY() {
        return 0;
    }
    
}
