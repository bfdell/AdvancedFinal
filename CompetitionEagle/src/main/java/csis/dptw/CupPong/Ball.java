package csis.dptw.CupPong;

import java.awt.Point;

import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.util.PaintHelper;

import java.awt.*;

public class Ball extends Entity{

    public Ball(Game game, Point position) {
        super(game, position);
    }
    
    public void paint(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        PaintHelper.drawCircleFromMiddle(position, 10,  g);
        
        g.setColor(Color.WHITE);
        PaintHelper.fillCircleFromMiddle(position,  10,  g);
    }
}
