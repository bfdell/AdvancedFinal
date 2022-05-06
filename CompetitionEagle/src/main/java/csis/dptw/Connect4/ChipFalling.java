package csis.dptw.Connect4;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

import java.awt.*;

/**
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 */

public class ChipFalling extends Animation {

    public static final int CHIP_SPEED = 40;
    Point destination;

    /**
     * Calls the super constructor Animation to create a new chip in the game
     * 
     * @param chip        the entity that will be animated in connect4 (chip)
     * @param game        that is being accessed
     * @param destination where the chip will be placed
     */
    public ChipFalling(ConnectChip chip, Connect4 game, Point destination) {
        super(chip, game);
        this.destination = destination;
    }

    /**
     * Animates the chip to fall in the matrix
     */
    @Override
    public synchronized void animation() {
        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entity.position.y += CHIP_SPEED;
    }

    /**
     * Checks to see if the next spot in the matrix is available. If so, then
     * setLanded is called to fill this spot
     */
    @Override
    public synchronized boolean done() {
        // checking if the next spot in matrix is false or true
        if (entity.position.y >= destination.y) {
            entity.position.y = destination.y;
            done = true;
            ((ConnectChip) entity).setLanded(true);
        }
        return done;
    }

}
