package csis.dptw;

import javax.swing.JPanel;
import java.awt.*;

public class Map extends JPanel {

    private java.util.List<Entity> entities = new java.util.ArrayList<Entity>();
    public Map(LayoutManager layout) {
        super();
        this.setLayout(layout);
    }

    public void addEntity(Entity entity) {
        entity.paint((Graphics2D) this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g) {

        paintEntities((Graphics2D) g);

    }

    public void paintEntities(Graphics2D g) {

        for(Entity entity : entities) {
            entity.paint(g);
        }
    }
}
