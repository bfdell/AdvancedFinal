package csis.dptw.CupPong;

import java.awt.Point;

import java.awt.*;


import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;

public class Line extends Animation {
    protected int d;
    public Line(Point start, Game game) {
        super(new Lin(game, start), game);
        d=-1;
    }

    @Override
    public void animation() {
        if(entity.position.x <= 300 || entity.position.x >= 500){
            d *= -1;
        }
        try {
            sleep(33);
        } catch (InterruptedException e) {
           
        }
        if(d == -1){
            entity.position.x -=5;
        }else{
            entity.position.x +=5;
        }
    }

    @Override
    public boolean done(){
        return false;
    }

    public static class Lin extends Entity{
        protected final int h = 20;
        public Lin(Game game, Point position) {
            super(game, position);
        }

        public void paint(Graphics2D g){
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.setColor(Color.black);
            g.drawLine(position.x, position.y, position.x, position.y-h);
            
        }
        
    }
    
}
