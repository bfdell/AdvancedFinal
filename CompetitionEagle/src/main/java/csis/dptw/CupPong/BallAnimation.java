package csis.dptw.CupPong;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import java.awt.*;

public class BallAnimation extends Animation {
    Point destination;

    public BallAnimation(Entity entity, Game game, Point destination) {
        super(entity, game);
        this.destination = destination;    
    }

    @Override
    public void animation(){
      
    }

    @Override
    public boolean done(){
        if(entity.position == destination){
            done = true;
        }
        return done;
    }
    
}
