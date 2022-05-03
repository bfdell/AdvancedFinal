package csis.dptw.Connect4;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import java.awt.*;

public class ChipFalling extends Animation{

    public static final int CHIP_SPEED = 10;
    Point destination;
    
    public ChipFalling(ConnectChip chip, Connect4 game, Point destination) {
        super(chip, game);
        this.destination = destination;
    }   

    @Override
    public void animation(){
            entity.position.y += CHIP_SPEED;
    }

    @Override
    public boolean done(){
        // checking if the next spot in matrix is false or true
        if(entity.position.y <= destination.y){
            done = true;
        }
        return done;
    }


}
