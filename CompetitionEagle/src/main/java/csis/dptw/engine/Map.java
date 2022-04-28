package csis.dptw.engine;

import javax.swing.JPanel;

import java.awt.*;

public class Map extends JPanel {
   // private Object Background;
    private java.util.List<Entity> entities = new java.util.ArrayList<Entity>();
    public Map(LayoutManager layout) {
        super();
        this.setLayout(layout);
    }

    public void addEntity(Entity entity) {
       entities.add(entity);
    }

    @Override
    public void paintComponent(Graphics g) {
        paintBackground((Graphics2D) g);
        paintEntities((Graphics2D) g);

    }

    public void paintEntities(Graphics2D g) {

        for(Entity entity : entities) {
            entity.paint(g);
        }
    }

    public void paintBackground(Graphics2D g) {

    }
}
