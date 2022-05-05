package csis.dptw.CupPong;

import java.awt.*;
import java.awt.geom.*;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.PaintHelper;

public class Cup extends Entity {
    // Point pOCup;
    // int centerX;
    final static int WIDTH = 40;
    final static int HEIGHT = 30;
    // int centerY;
    Arc2D.Double cupColision;
    public Cup(Game game, Point position, String imagePath) {
        super(game, position, imagePath);
        // cupColision = PaintHelper.makeArcOvalFromMiddle(position, WIDTH, HEIGHT, 0.0, 360.0, Arc2D.PIE);
        cupColision = new Arc2D.Double(position.x + 12, position.y + 2, WIDTH, HEIGHT, 0.0, 360.0, Arc2D.PIE);
    }

    public boolean colidesWith(Point point) {
        //return cupColision.point();
        return false;                  
    }

    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        //g.draw(cupColision);
    }

    // public boolean iCup(Point p){

    //         double squaredA = Math.pow(p.x + 5 - centerX, 2);
    //         double squaredB = Math.pow(p.y + 5 - centerY, 2);

    //         double part1 = squaredA /( Math.pow(25, 2));
    //         double part2 = squaredB/(Math.pow(20, 2));
    //         double result = part1 + part2;
            
    //         if(result <= 1){
    //             System.out.println("Within cup");
    //             return true;
    //         }
    //         return false;
    // }
}
