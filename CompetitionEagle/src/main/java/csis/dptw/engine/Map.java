package csis.dptw.engine;

import javax.swing.JPanel;

import java.awt.*;

/**
 * Sets map of the layout for the panel
 * @author Brian Dell
 * @version Spring 2022
 */

public class Map extends JPanel {
    
    public java.util.List<Entity> entities = new java.util.ArrayList<Entity>();

    /**
     * Calls the super class constructor, sets the layout and gameDimension
     * @param layout of the panel
     * @param gameDimension size of panel
     */
    public Map(LayoutManager layout, Dimension gameDimension) {
        super();
        this.setLayout(layout);
        this.setPreferredSize(gameDimension);
        setFocusable(true);
    }

    /**
     * Paints the entities and background while calling the super class paintComponent
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground((Graphics2D) g);
        paintEntities((Graphics2D) g);
    }

    public void paintBackground(Graphics2D g) {
    }

    /**
     * Paints the entities
     * @param g graphics2d object
     */
    public void paintEntities(Graphics2D g) {
        for (Entity entity : entities) {
            entity.paint(g);
        }
    }
}
