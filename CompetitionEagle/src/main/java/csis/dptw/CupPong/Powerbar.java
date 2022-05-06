package csis.dptw.CupPong;

import java.awt.*;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

/**
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class Powerbar extends Animation {
    public static final int MAX_HIEGHT = 150;
    public static final Point BAR_LOCATION = new Point(500, 700);
    public boolean powerSpecified = false;

    public Bar bar;

    public int direction = 1;

    /**
     * Calls the super class constructor Animation and intializes bar and adds an
     * entity to the game
     * 
     * @param game that is being accessed
     */
    public Powerbar(Game game) {
        super(new Bar(game, new Point(BAR_LOCATION.x, BAR_LOCATION.y)), game);
        bar = (Bar) entity;

        game.addEntity(entity);
    }

    /**
     * Animates the power bar to increase and decrease in size unil a key event
     * occurs
     */
    @Override
    public void animation() {
        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {

        }

        bar.currentHeight += direction * Bar.GROWTH_RATE;
        // bar.position.y -= direction * Bar.GROWTH_RATE;

        if (bar.currentHeight > MAX_HIEGHT || bar.currentHeight < 0) {
            direction *= -1;
            System.out.println("direction changed");
        }

    }

    /**
     * returns true when the power of the meter has been specified
     */
    @Override
    public boolean done() {
        return powerSpecified;
    }

    static class Bar extends Entity {

        public static final int BAR_WIDTH = 30;
        public static final Color BAR_COLOR = Color.RED;
        public static final int GROWTH_RATE = 5;
        public int currentHeight = 0;

        /**
         * Calls the super class constructor Entity
         * 
         * @param game
         * @param position
         */
        public Bar(Game game, Point position) {
            super(game, position);

        }

        /**
         * Draws the meter bar and sets the shape and color
         */
        @Override
        public void paint(Graphics2D g) {
            g.setColor(BAR_COLOR);
            g.fillRect(Powerbar.BAR_LOCATION.x, Powerbar.BAR_LOCATION.y, BAR_WIDTH, -1 * currentHeight);

        }

    }
}
