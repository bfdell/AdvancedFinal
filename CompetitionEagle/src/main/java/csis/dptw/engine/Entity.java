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


public class Entity implements Serializable { 
    protected PropertyChangeSupport eventHelper = new PropertyChangeSupport(this);
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image entityImage;
    private Game game;
    public Point position;

    //MAKE LISTENER AS A FUNCTINAL INTERFACE MAYBE
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener, String propertyName) {
        this.eventHelper.addPropertyChangeListener(propertyName, listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener, String propertyName) {
        this.eventHelper.removePropertyChangeListener(propertyName, listener);
    }


    public Point2D getPosition() {
        return position;
    }
    

    /**
     * Constructor to be used with anonymous class that overrides makeEntityImage
     * @param game
     */
    public Entity(Game game, Point position) {
        this.game = game;
        makeEntityImage();
        this.position = position;
    }
    public Entity(Game game, Point position, String imagePath) {
        // System.out.println("SKLFSLDFj");
        // try {
        //     String currentPath;
        //     currentPath = new java.io.File(".").getCanonicalPath();
        //     System.out.println("Current dir:" + currentPath);
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        entityImage = toolkit.getImage(imagePath);
        // try {
        //     entityImage = ImageIO.read(new File(imagePath));
        // JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        this.game = game;
        this.position = position;
    }
    // public Entity(Game game, Point position, String imagePath, int size) {
    //     System.out.println("SKLFSLDFj");
    //     try {
    //         String currentPath;
    //         currentPath = new java.io.File(".").getCanonicalPath();
    //         System.out.println("Current dir:" + currentPath);
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     entityImage = toolkit.getImage(imagePath);
    //     // try {
    //     //     entityImage = ImageIO.read(new File(imagePath));
    //     // JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    //     // } catch (IOException e) {
    //     //     e.printStackTrace();
    //     // }
    //     this.game = game;
    //     this.position = position;
    // }
    //SAVE GRAPHICS INFO IN LAMBDA COMMANDS?
    //MAYBE MAKE CLASS THAT SAVES GRAPHICS INFORMATON

    //OR HAVE GRAPHICS LINES IN HERE AND CALL THIS WHEN PAINTING ENTITY
    private BufferedImage makeEntityImage() {
        return null;
    }


    //DEFAULT PAINT METHOD ONLY WORKS FOR IMAGES
    public void paint(Graphics2D g) {
        g.drawImage(entityImage, position.x, position.y, game.gamePanel);
    }
}