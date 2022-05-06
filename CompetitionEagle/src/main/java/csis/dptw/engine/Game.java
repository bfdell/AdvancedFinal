package csis.dptw.engine;

import javax.swing.*;

import csis.dptw.engine.Event.EventType;

import java.awt.*;
import java.awt.event.*;

/**
 * Creates the game that will be accessed
 * 
 * @author Brian Dell
 * @version Spring 2022
 */

// TO SAVE DATA: MAKE A GAME DATA CLASS THAT IS SERIALIZABLE AND SAVE DATA
// BEFORE GAME HAS ENDED
public abstract class Game extends GameInput implements Runnable {
    public Map gamePanel;
    public Repainter refresher;

    ////////////////////////////////////////////////////////////////
    String test = "STRING";
    int i;
    String k = "SMH,";
    ////////////////////////////////

    public final LayoutManager MAP_LAYOUT;
    // NO NEED FOR MAP LAYOUT

    /**
     * Creates layout of the game panel
     */
    public Game() {
        MAP_LAYOUT = new FlowLayout();
    }

    /**
     * sets map layout to the current layout
     * 
     * @param layout of the game panel
     */
    public Game(LayoutManager layout) {
        MAP_LAYOUT = layout;
    }

    /**
     * Calls the method intializeMap(), repaints the game and starts the animation.
     * This method also calls addInputListeners and addMouseEvent for clicked and
     * pressed events
     */
    @Override
    public void run() {
        initializeMap();
        refresher = new Repainter(gamePanel);
        refresher.start();
        addInputListeners();

        // addMouseEvent(EventType.MRELEASED, e -> testEvent(this, (MouseEvent) e), 2);
        // addMouseEvent(EventType.MPRESSED, e -> testEvent2(this, (MouseEvent) e), 1);
        addMouseEvent(EventType.MCLICKED, e -> testEvent2(this, (MouseEvent) e), 1);
        // addKeyEvent(EventType.KTYPED, e -> keyTest(this, (KeyEvent) e), 1,
        // KeyEvent.VK_C);
        // addKeyEvent(EventType.KPRESSED, e -> keyTest2(this, (KeyEvent) e), 1,
        // KeyEvent.VK_C);
        // addKeyEvent(EventType.KRELEASED, e -> keyTest3(this, (KeyEvent) e), 1,
        // KeyEvent.VK_C);
        addMouseEvent(EventType.MPRESSED, this::mRef, 1);
        AWTEvent event = new MouseEvent(gamePanel, i, i, i, i, i, i, false);
    }

    /**
     * Adds an entity to the game
     * 
     * @param entity the entity in the game that can be accessed
     */
    public void addEntity(Entity entity) {
        gamePanel.entities.add(entity);
    }

    /**
     * removes an entity to the game
     * 
     * @param entity the entity in the game that can be accessed
     */
    public boolean removeEntity(Entity entity) {
        return gamePanel.entities.remove(entity);
    }

    public abstract void initializeMap();

    // public abstract void addAllMouseEvents();
    // public abstract void addAllKeyEvents();
    // TESTING PURPOSES
    /**
     * Tests the game
     * 
     * @param game being accessed
     * @param e    the event in which the mouse has been pressed/released, etc.
     */
    public static void testEvent(Game game, MouseEvent e) {
        System.out.println(game.test);
        game.test += game.i++ + ",";
    }

    public void mRef(MouseEvent e) {
        System.out.println("ITS WORKING  " + i++);
    }

    // TESTING PURPOSES
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
     * This method adds a MouseListener, MouseMotionListener, and KeyListener, to
     * the gamePanel.
     * The listener for all of thses events is the game class itslef.
     */
    private void addInputListeners() {
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
        gamePanel.addKeyListener(this);
    }

    /**
     * Calls the super class actionPerformed and checks to make sure that keys can
     * be accessed after pressing a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        gamePanel.requestFocus();
    }
}
