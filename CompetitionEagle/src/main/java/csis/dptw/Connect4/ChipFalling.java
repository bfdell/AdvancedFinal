package csis.dptw.Connect4;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import java.awt.*;

public class ChipFalling extends Animation{

    Point destination;
    
    public ChipFalling(Entity entity, Game game, Point destination) {
        super(entity, game);
        this.destination = destination;
    }   

    @Override
    public void animation(){
        
            entity.position.y++;
    }

    @Override
    public boolean done(){
        // checking if the next spot in matrix is false or true
        if(entity.position.y == destination.y){
            done = true;
        }
        return done;
    }


}
