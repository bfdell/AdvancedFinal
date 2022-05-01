package csis.dptw.Connect4;

import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;

import csis.dptw.App;
import csis.dptw.engine.Map;
import csis.dptw.util.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */

//HAVE BOARD SIZE PERCENT CONSTANT THAT COMPARES TO ALL GAME PANEL SIZE
public class ConnectBoard extends Map {
    public final int MARGIN_X;
    public final int MARGIN_Y;
    public final int NUM_ROWS;
    public final int NUM_COLS;
    public final int RING_WIDTH = 8;
    public final int CHIP_RADIUS = 41;
    public final int CHIP_DIAMETER = CHIP_RADIUS * 2;
    public final int CHIP_PADDING = 10;
    public final int BOARD_WIDTH;
    public final int BOARD_HEIGHT;
    public final Area BOARD_AREA;
    public Point[][] circlePoints;

    public ConnectBoard(LayoutManager layout, Point[][] circlePoints, int boardRows,
            int boardCols) {
        super(layout, App.gameDimension);
        this.circlePoints = circlePoints;
        NUM_ROWS = boardRows;
        NUM_COLS = boardCols;
        BOARD_WIDTH = CHIP_PADDING + (CHIP_DIAMETER + CHIP_PADDING) * NUM_COLS; //Roughly 660
        BOARD_HEIGHT = CHIP_PADDING + (CHIP_DIAMETER + CHIP_PADDING) * NUM_ROWS; //Roughly 568
        MARGIN_X = ((int) App.gameDimension.getWidth() - BOARD_WIDTH) / 2;
        MARGIN_Y = (int) App.gameDimension.getHeight() - BOARD_HEIGHT;

        initializeBoard();
        BOARD_AREA = createBoardArea(
                new Rectangle2D.Double(0, 0, (int) App.gameDimension.getWidth(), (int) App.gameDimension.getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        // g.setColor(Color.ORANGE);
        // g.fillRect(5, 700, 600, 10);

        ////////////////////////////////
        g.setColor(new Color(43, 132, 255));
        paintBackground((Graphics2D) g);

        g.setClip(BOARD_AREA);
        // g.setColor(Color.BLUE);
        g.setColor(new Color(0, 0, 145));
        paintBackground((Graphics2D) g);

        paintEmptySpots((Graphics2D) g);
        paintEntities((Graphics2D) g);
        paintBoard((Graphics2D) g);

        ////////////////////////////////////////////////////////////////
        // g.setColor(Color.ORANGE);
        // g.drawLine(5, 0, 5, getHeight());
        // g.setColor(Color.RED);
        // g.fillRect(0, 650, 660, 10);
        // g.drawLine(660, 0, 660, 568);

    }

    private void paintEmptySpots(Graphics2D g) {
        g.setColor(Color.WHITE);
        Arrays.stream(circlePoints)
                .forEach(pa -> Arrays.stream(pa).forEach(p -> PaintHelper.fillCircleFromMiddle(p, CHIP_RADIUS, g)));
    }

    private void paintBoard(Graphics2D g) {
        g.setColor(new Color(0, 0, 145));
        //DARK BLUE^^^
        // g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(RING_WIDTH));
        Arrays.stream(circlePoints)
                .forEach(pa -> Arrays.stream(pa).forEach(p -> PaintHelper.drawCircleFromMiddle(p, CHIP_RADIUS, g)));
        g.setStroke(new BasicStroke(1));
    }

    private void initializeBoard() {
        int firstChipMiddleX = MARGIN_X + CHIP_PADDING + CHIP_RADIUS;
        int chipDistance = CHIP_PADDING + CHIP_DIAMETER;

        int x = firstChipMiddleX;
        int y = MARGIN_Y + CHIP_PADDING + CHIP_RADIUS;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                circlePoints[row][col] = new Point(x, y);
                x += chipDistance;
            }
            y += chipDistance;
            x = firstChipMiddleX;
        }
    }

    public Area createBoardArea(Rectangle2D.Double baseBoard) {
        Area baseArea = new Area(baseBoard);
        Area boardArea = new Area(baseBoard);

        Arc2D.Double chip = PaintHelper.makeArcFromMiddle(new Point(0, 0), CHIP_RADIUS + RING_WIDTH / 2, 0.0, 360.0,
                Arc2D.PIE);
        for (Point[] points : circlePoints) {
            for (Point point : points) {
                PaintHelper.changeArcMiddleLocation(chip, point);

                Area chipArea = new Area(chip);
                boardArea.subtract(chipArea);
            }
        }

        //ExclusiveOr changes area to only the individual chip spaces on the board
        boardArea.exclusiveOr(baseArea);
        return boardArea;
    }

    @Override
    public void paintBackground(Graphics2D g) {
        g.fillRect(MARGIN_X, MARGIN_Y, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
