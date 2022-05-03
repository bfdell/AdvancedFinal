package csis.dptw.engine;

import javax.swing.JPanel;

import java.awt.*;

public class Map extends JPanel {
    
    public java.util.List<Entity> entities = new java.util.ArrayList<Entity>();

    public Map(LayoutManager layout, Dimension gameDimension) {
        super();
        this.setLayout(layout);
        this.setPreferredSize(gameDimension);
        setFocusable(true);
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
