package csis.dptw.engine;

import javax.swing.*;

import csis.dptw.engine.Event.EventType;

import java.awt.*;
import java.awt.event.*;

//MAYBE CHANGE TO ABSTRACT CLASS OR INTERFACE

//TO SAVE DATA: MAKE A GAME DATA CLASS THAT IS SERIALIZABLE AND SAVE DATA BEFORE GAME HAS ENDED
public abstract class Game extends GameInput implements Runnable {
    public Map gamePanel;
    // public Repainter refresher = new Repainter();

    ////////////////////////////////////////////////////////////////
    String test = "STRING";
    int i;
    String k = "SMH,";
    ////////////////////////////////

    public final LayoutManager MAP_LAYOUT;
    //NO NEED FOR MAP LAYOUT

    public Game() {
        MAP_LAYOUT = new FlowLayout();
    }

    public Game(LayoutManager layout) {
        MAP_LAYOUT = layout;
    }

    @Override
    public void run() {
        initializeMap();
        addInputListeners();

        addMouseEvent(EventType.MRELEASED, e -> testEvent(this, (MouseEvent) e), 2);
        addMouseEvent(EventType.MPRESSED, e -> testEvent2(this, (MouseEvent) e), 1);
        addMouseEvent(EventType.MCLICKED, e -> testEvent2(this, (MouseEvent) e), 1);
        addKeyEvent(EventType.KTYPED, e -> keyTest(this, (KeyEvent) e), 1, KeyEvent.VK_C);
        addKeyEvent(EventType.KPRESSED, e -> keyTest2(this, (KeyEvent) e), 1, KeyEvent.VK_C);
        addKeyEvent(EventType.KRELEASED, e -> keyTest3(this, (KeyEvent) e), 1, KeyEvent.VK_C);
    }

    public abstract void initializeMap();

    // public abstract void addAllMouseEvents();
    // public abstract void addAllKeyEvents();
    //TESTING PURPOSES
    public static void testEvent(Game game, MouseEvent e) {
        System.out.println(game.test);
        game.test += game.i++ + ",";
    }

    //TESTING PURPOSES
    public static void testEvent2(Game game, MouseEvent e) {
        System.out.println(game.i * 50 + "------------");
        System.out.println(e.getPoint());
    }

    public void keyTest(Game game, KeyEvent e) {
        System.out.println("KEY TYPE ACTIVATED");
    }

    public void keyTest2(Game game, KeyEvent e) {
        System.out.println("KEY PRESS ACTIVATED");
    }

    public void keyTest3(Game game, KeyEvent e) {
        System.out.println("KEY RELEASE ACTIVATED");
    }

    /**
     * This method adds a MouseListener, MouseMotionListener, and KeyListener, to the gamePanel.
     * The listener for all of thses events is the game class itslef.
     */
    private void addInputListeners() {
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
        gamePanel.addKeyListener(this);
    }
}
