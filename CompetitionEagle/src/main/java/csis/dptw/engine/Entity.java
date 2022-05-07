package csis.dptw.engine;

import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import csis.dptw.Connect4.Connect4;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 *Creates an entity that can be used to create objects for the specified game
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */
public class Entity implements Serializable { 
    protected PropertyChangeSupport eventHelper = new PropertyChangeSupport(this);
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image entityImage;
    private Game game;
    public Point position;

   /**
    * Adds a listener to a property of entity
    * @param listener that is responding to the property change
    */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.addPropertyChangeListener(listener);
    }
    /**
    * removes a listener to a property of entity
    * @param listener that is responding to the property change
    */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.removePropertyChangeListener(listener);
    }

    /**
     * Adds a listener to a property of entity
     * @param listener that is responding to the property change
     * @param propertyName name of the instance variable
     */
    public void addPropertyChangeListener(PropertyChangeListener listener, String propertyName) {
        this.eventHelper.addPropertyChangeListener(propertyName, listener);
    }

     /**
     * Removes a listener to a property of entity
     * @param listener that is responding to the property change
     * @param propertyName name of the instance variable
     */
    public void removePropertyChangeListener(PropertyChangeListener listener, String propertyName) {
        this.eventHelper.removePropertyChangeListener(propertyName, listener);
    }


    /**
     * 
     * @return position as a point2d object
     */
    public Point2D getPosition() {
        return position;
    }
    

    /**
     * Initializes game and position
     * @param game that is being played
     * @param position of the entity
     */
    public Entity(Game game, Point position) {
        this.game = game;
        this.position = position;
    }
    /**
     * Initializes game, position and imagePath
     * @param game that is being played
     * @param position of the entity
     * @param imagePath to the image of the entity
     */
    public Entity(Game game, Point position, String imagePath) {
       
        entityImage = toolkit.getImage(imagePath);
        
        this.game = game;
        this.position = position;
    }
    
  


    //DEFAULT PAINT METHOD ONLY WORKS FOR IMAGES
    /**
     * paints the entity
     * @param g graphics2d object
     */
    public void paint(Graphics2D g) {
        g.drawImage(entityImage, position.x, position.y, game.gamePanel);
    }
}