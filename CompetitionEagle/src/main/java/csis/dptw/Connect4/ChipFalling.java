package csis.dptw.Connect4;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

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
            try {
                sleep(Repainter.DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            entity.position.y += CHIP_SPEED;
    }

    @Override
    public boolean done(){
        // checking if the next spot in matrix is false or true
        if(entity.position.y >= destination.y){
            entity.position.y = destination.y;
            done = true;
            ((ConnectChip)entity).setLanded(true);
            // notifyAll();
        }
        return done;
    }


}
