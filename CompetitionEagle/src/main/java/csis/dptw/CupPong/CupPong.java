package csis.dptw.CupPong;

import javax.swing.*;

import csis.dptw.*;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;
import csis.dptw.engine.Event.EventType;

import java.awt.event.*;

import java.awt.*;
import java.awt.geom.*;


public class CupPong extends Game {

    public static final Point BALL_START = new Point(420, 700);
    public Ball ball = new Ball(this, BALL_START);
    public CupPong() {
        super();//FIX TIHIS LINE BECCUSE GAMAE PANEL IS BEING INITIOALIZED IN ANONYMOUS INNER CLASS
        run();
    }

     public final int LANE_WIDTH = 500;
    // ///////////////////////
    // public double boardYPercent = .3;
    // public double boardXPercent = .3;
    // public double triangleYPercent = .35;
    // public double triangleXPercent = .15;

    @Override
    public void run() {
        super.run();
        
        addCups();
        gamePanel.addEntity(ball);
        //outlineCups();
        addMouseEvent(EventType.MPRESSED, e -> clickInCup(this, (MouseEvent) e), 1);
    }

    public void outlineCups(int x, int y){
        // gamePanel.addEntity(new OutlineOfCups(this, new Point(x, y)));
    }

    public void addCups(){
        int x = 318;
        int y = 200;
        for(int i = 0; i < 10; i++){
           if(i == 4){
               y += 35;
               x = 342;
            }
        else if(i == 7){
               y += 35;
               x = 365;
            }
        else if(i == 9){
               y += 35;
               x = 389;
           }

            gamePanel.addEntity(new Cup(this, new Point(x, y), "CompetitionEagle/src/main/java/csis/dptw/BeFunky-photo.png"));
            outlineCups(x, y);
            x += 48;
        }
    }

    @Override
    public void initializeMap() {
        gamePanel = new PongMap(new FlowLayout(), LANE_WIDTH, LANE_WIDTH);
       //gamePanel = new cupPongM(new FlowLayout(), LANE_WIDTH, LANE_WIDTH);
        
    }

    public static void clickInCup(CupPong game, MouseEvent e) {
        Point cur = e.getPoint();
        for(Entity cup : game.gamePanel.entities) {
            if(cup != game.ball && ((Cup)cup).colidesWith(cur)) {
                System.out.println("Collieds");
                game.moveCup((Cup) cup,(CupPong)game);
            }
        }
    }

    public void moveCup(Cup cup, CupPong game) {
        CupAnimation animation = new CupAnimation(cup, game);
        animation.start();
    }
}
