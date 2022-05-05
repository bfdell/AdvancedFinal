package csis.dptw.CupPong;

import java.awt.*;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

public class DirectionMeter extends Animation{
    
    public final Point FULCRUM_POINT;
    public final int METER_HEIGHT = 20;
    public final int METER_WIDTH;

    public DirectionMeter(Point fulcrumPoint, Entity entity, Game game) {
        super(new Meter(game, fulcrumPoint, ), game);
        FULCRUM_POINT = fulcrumPoint;  
        //Chechk later
        METER_WIDTH = (int) (App.gameDimension.width * PongMap.BOARD_X_PERCENT);
    }

    @Override
    public void animation(){
    @Override
    public boolean done(){
        //checking if cup is off the screen
        if(entity.position.x >= App.gameDimension.width){
            done = true;
        }
        return done;
    }


    private static class Meter extends Entity {
        Point endPoint;

        public Meter(Game game, Point position, Point endPoint) {
            super(game, position);
            this.endPoint = endPoint;
        }


        @Override
        public void paint(Graphics2D g) {

        }

    }
}
