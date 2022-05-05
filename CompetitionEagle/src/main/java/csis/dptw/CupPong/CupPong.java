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

    //////////////////////////
    final String CUP = "Cup";
    final String PONG = "Pong";
    private JButton play;
    private JButton mainMenu;
    DirectionMeter m;
    Powerbar pb;

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

        // addCups();
        // addEntity(ball);
        //outlineCups();
        addKeyEvent(EventType.KPRESSED, this::adjustShot, 4, KeyEvent.VK_SPACE);
        addMouseEvent(EventType.MPRESSED, this::clickInCup, 1);
        addActionEvent(this::startGame, 1, play);
    }

    public void adjustShot(KeyEvent e) {
        System.out.println("ACTIVATING");
        if (m != null) {
            m.interrupt();
            m.isDirectonSet = true;
            // removeEntity(m.meter);
            m = null;
            pb = new Powerbar(this);
            pb.start();
        } else if (pb != null) {
            pb.interrupt();
            pb.powerSpecified = true;
            // removeEntity(pb.bar);
            pb = null;
            System.out.println("removeing powerbar");
        }
    }

    public void outlineCups(int x, int y) {
        // addEntity(new OutlineOfCups(this, new Point(x, y)));
    }

    public void addCups() {
        int x = 318;
        int y = 200;
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                y += 35;
                x = 342;
            } else if (i == 7) {
                y += 35;
                x = 365;
            } else if (i == 9) {
                y += 35;
                x = 389;
            }

            addEntity(new Cup(this, new Point(x, y), "CompetitionEagle/src/main/java/csis/dptw/BeFunky-photo.png"));
            outlineCups(x, y);
            x += 48;
        }
    }

    @Override
    public void initializeMap() {
        gamePanel = new PongMap(new FlowLayout(), LANE_WIDTH, LANE_WIDTH);

        play = new JButton("Play");
        mainMenu = new JButton("Main Menu");

        gamePanel.add(play);
        gamePanel.add(mainMenu);

        play.setVisible(true);
        mainMenu.setVisible(true);
    }

    public void clickInCup(MouseEvent e) {
        Point cur = e.getPoint();
        for (Entity cup : gamePanel.entities) {
            if (cup instanceof Cup) {
                if (cup != ball && ((Cup) cup).colidesWith(cur)) {
                    System.out.println("col·li·sion");
                    moveCup((Cup) cup, (CupPong) this);
                }
            }
        }
    }

    public void moveCup(Cup cup, CupPong game) {
        CupAnimation animation = new CupAnimation(cup, game);
        animation.start();
        // if(Cup.colidesWith(ball.position)) {
        //     ball.position.y -= dy1;
        // }
    }

    public void startGame(ActionEvent e) {
        ((PongMap) gamePanel).playing = true;
        ////////////////REMOVE ALL BUTTONS AND SHIT
        ((PongMap) gamePanel).remove(play);
        ((PongMap) gamePanel).remove(mainMenu);
        addCups();
        addEntity(ball);
        m = new DirectionMeter(ball.position, this);
        m.start();
        // pb = new Powerbar(this);
        // pb.start();

        System.out.println("SETTING");
        gamePanel.requestFocus();
        ;
    }
}
