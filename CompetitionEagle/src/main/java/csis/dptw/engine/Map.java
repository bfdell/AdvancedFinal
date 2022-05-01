package csis.dptw.engine;

import javax.swing.JPanel;

import java.awt.*;

public class Map extends JPanel {
    
    private java.util.List<Entity> entities = new java.util.ArrayList<Entity>();

    public Map(LayoutManager layout, Dimension gameDimension) {
        super();
        this.setLayout(layout);
        this.setPreferredSize(gameDimension);
        setFocusable(true);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public boolean removeEntity(Entity entity) {
        return entities.remove(entity);
    }

    @Override
    public void paintComponent(Graphics g) {
        paintBackground((Graphics2D) g);
        paintEntities((Graphics2D) g);
    }

    public void paintBackground(Graphics2D g) {
    }

    public void paintEntities(Graphics2D g) {
        for (Entity entity : entities) {
            entity.paint(g);
        }
    }
}
