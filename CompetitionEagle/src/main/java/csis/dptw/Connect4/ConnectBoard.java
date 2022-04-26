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
        paintBackground((Graphics2D) g);
        paintEmptySpots((Graphics2D) g);
        paintEntities((Graphics2D) g);
        paintGrid((Graphics2D) g);
        g.setColor(Color.PINK);
        g.fillRect(0, 0, getWidth(), getHeight());
        // g.setColor(Color.RED);
        // ((Graphics2D) g).fill(s);

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

        g.setColor(new Color(0, 0, 145));
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

    @Override
    public void paintBackground(Graphics2D g) {
        g.setColor(Color.BLUE);
        int dimension = 600;
        int padding = 10;

        int boardSize = dimension - padding * 2;

        int diam = boardSize / 7;

        // g.fillRect(0, 0, dimension + (padding * 6), dimension - diam + (padding * 5));
        
        Arc2D.Double arc = new Arc2D.Double(circlePoints[0][0].x - radius - 4, circlePoints[0][1].y - radius - 4, radius * 2  + 8, radius * 2 + 8, 0, 360, Arc2D.PIE);
        g.clip(arc);
        s = g.getClip();
        // g.setColor(Color.ORANGE);
        // g.fillRect(0, 0, dimension + (padding * 6), dimension - diam + (padding * 5));
    }
}
