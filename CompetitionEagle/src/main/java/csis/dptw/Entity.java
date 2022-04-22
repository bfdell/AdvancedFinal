package csis.dptw;

import java.beans.*;
import java.io.Serializable;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Entity implements Serializable { 
    private PropertyChangeSupport eventHelper = new PropertyChangeSupport(this);
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image entityImage;
    private Game game;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.eventHelper.removePropertyChangeListener(listener);
    }


    public Entity(Game game) {
        this.game = game;
    }

    public Entity(String imagePath, Game game) {
        entityImage = toolkit.getImage(imagePath);
        this.game = game;
    }

    public void paint(Graphics2D g) {
        g.drawImage(entityImage, 50, 50, game.gamePanel);
    }
}