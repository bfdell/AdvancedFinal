package csis.dptw.Connect4;

import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;

import csis.dptw.Map;
import csis.dptw.util.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */

//HAVE BOARD SIZE PERCENT CONSTANT THAT COMPARES TO ALL GAME PANEL SIZE
public class ConnectBoard extends Map {

    Point[][] circlePoints;
    int radius;
    Shape s;
    public ConnectBoard(LayoutManager layout) {
        super(layout);
        initializeGrid();
       
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(43, 132, 255));
        paintBackground((Graphics2D) g);
        Area conn =  createGrid(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        g.setClip(conn);
        // g.setColor(Color.BLUE);
        g.setColor(new Color(0, 0, 145));
        paintBackground((Graphics2D) g);

        // ((Graphics2D) g).fill(s);
        // paintEmptySpots((Graphics2D) g);
        paintEntities((Graphics2D) g);
        paintGrid((Graphics2D) g);

        g.setColor(Color.PINK);
        // g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
    }

    private void paintEmptySpots(Graphics2D g) {
        g.setColor(Color.WHITE);
        Arrays.stream(circlePoints)
                .forEach(pa -> Arrays.stream(pa).forEach(p -> PaintHelper.fillCircleFromMiddle(p, radius, g)));
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.BLUE);
        int dimension = 600;
        int padding = 10;

        int boardSize = dimension - padding * 2;

        int diam = boardSize / 7;
        int radius = diam / 2;

        // g.setColor(new Color(0, 0, 145));
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(8));
        Arrays.stream(circlePoints)
                .forEach(pa -> Arrays.stream(pa).forEach(p -> PaintHelper.drawCircleFromMiddle(p, radius, g)));
        g.setStroke(new BasicStroke(1));

    }

    private void initializeGrid() {
        int dimension = 600;
        int padding = 10;

        int boardSize = dimension - padding * 2;

        int diam = boardSize / 7;
        radius = diam / 2;
        int x = padding + radius;
        int y = padding + radius;
        circlePoints = new Point[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                circlePoints[i][j] = new Point(x, y);
                x += diam + padding;
            }
            y += diam + padding;
            x = padding + radius;
        }
    }

    public Area createGrid(Rectangle2D.Double baseBoard) {
        Area baseArea = new Area(baseBoard);

        Arc2D.Double chip = PaintHelper.makeArcFromMiddle(new Point(0, 0), radius + 4, 0.0, 360.0, Arc2D.PIE);
        for(Point[] points : circlePoints) {
            for(Point point : points) {
                PaintHelper.changeArcMiddleLocation(chip, point);
                
                Area chipArea = new Area(chip);
                baseArea.subtract(chipArea);
            }
        } 
        baseArea.exclusiveOr(new Area(baseBoard));
        return baseArea;
    }

    @Override
    public void paintBackground(Graphics2D g) {
        // g.setColor(Color.BLUE);
        int dimension = 600;
        int padding = 10;

        int boardSize = dimension - padding * 2;

        int diam = boardSize / 7;

        g.fillRect(0, 0, dimension + (padding * 6), dimension - diam + (padding * 5));
        
        // Arc2D.Double arc = new Arc2D.Double(circlePoints[0][0].x - radius - 4, circlePoints[0][1].y - radius - 4, radius * 2  + 8, radius * 2 + 8, 0, 360, Arc2D.PIE);
        // g.clip(arc);
        // s = g.getClip();
        g.setColor(Color.ORANGE);
        // g.fillRect(0, 0, dimension + (padding * 6), dimension - diam + (padding * 5));
    }
}
