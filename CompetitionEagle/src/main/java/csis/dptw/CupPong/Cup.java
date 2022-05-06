package csis.dptw.CupPong;

import java.awt.*;
import java.awt.geom.*;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.PaintHelper;

/**
 * Creates the entity cup that will be used to create cupPong
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class Cup extends Entity {
    // Point pOCup;
    // int centerX;
    final static int WIDTH = 40;
    final static int HEIGHT = 30;
    // int centerY;
    Arc2D.Double cupColision;

    /**
     * Creates entity Cup with call to super class Entity constructor
     * 
     * @param game,      the game in which the entity resides
     * @param position,  the position of the entity
     * @param imagePath, the path to the image of the entity
     */
    public Cup(Game game, Point position, String imagePath) {
        super(game, position, imagePath);
        // cupColision = PaintHelper.makeArcOvalFromMiddle(position, WIDTH, HEIGHT, 0.0,
        // 360.0, Arc2D.PIE);
        cupColision = new Arc2D.Double(position.x + 12, position.y + 2, WIDTH, HEIGHT, 0.0, 360.0, Arc2D.PIE);
    }
   
    /**
     * Determines wether a point has colided with a cup
     * 
     * @param point, the point to check if it collided with the cup entity
     * @return whether the point is collided with the cup entity
     */
    public boolean colidesWith(Point2D point) {
        return cupColision.contains(point);
    }

    /**
     * Paint the cup entity
     * @param g, the graphics object
     */
    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        // g.draw(cupColision);
    }

    // public boolean iCup(Point p){

    // double squaredA = Math.pow(p.x + 5 - centerX, 2);
    // double squaredB = Math.pow(p.y + 5 - centerY, 2);

    // double part1 = squaredA /( Math.pow(25, 2));
    // double part2 = squaredB/(Math.pow(20, 2));
    // double result = part1 + part2;

    // if(result <= 1){
    // System.out.println("Within cup");
    // return true;
    // }
    // return false;
    // }
}
