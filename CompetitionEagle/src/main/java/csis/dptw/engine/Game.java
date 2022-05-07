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

    public final LayoutManager MAP_LAYOUT;

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

    public Map getMap() {
        return gamePanel;
    }
}
