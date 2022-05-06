package csis.dptw.CupPong;

import java.awt.*;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

public class Powerbar extends Animation {
    public static final int MAX_HIEGHT = 150;
    public static final Point BAR_LOCATION = new Point(500, 700);
    public boolean powerSpecified = false;

    public Bar bar;

    public int direction = 1;


    public Powerbar(Game game) {
        super(new Bar(game,  new Point(BAR_LOCATION.x, BAR_LOCATION.y)), game);
        bar = (Bar) entity;
    
        game.addEntity(entity);
    }

    @Override
    public void animation() {
        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {
    
        }

        bar.currentHeight += direction * Bar.GROWTH_RATE;
        // bar.position.y -= direction * Bar.GROWTH_RATE;
 
        if(bar.currentHeight > MAX_HIEGHT || bar.currentHeight < 0) {
            direction *= -1;
            System.out.println("direction changed");
        }


    }

    @Override
    public boolean done() {
        return powerSpecified;
    }

    private static class Bar extends Entity {

        public static final int BAR_WIDTH = 30;
        public static final Color BAR_COLOR = Color.RED;
        public static final int GROWTH_RATE = 5;
        public int currentHeight = 0;

        public Bar(Game game, Point position) {
            super(game, position);

        }

        @Override
        public void paint(Graphics2D g) {
            g.setColor(BAR_COLOR);
            g.fillRect(Powerbar.BAR_LOCATION.x, Powerbar.BAR_LOCATION.y, BAR_WIDTH, -1 * currentHeight);
            // g.fillRect(Powerbar.BAR_LOCATION.x, Powerbar.BAR_LOCATION.y, BAR_WIDTH, currentHeight);
        }

    }
}
