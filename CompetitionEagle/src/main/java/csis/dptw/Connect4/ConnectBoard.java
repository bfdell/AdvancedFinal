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
public class ConnectBoard extends Map {
    public final static Color LIGHT_BLUE = new Color(43, 132, 255);
    public final static Color DARK_BLUE = new Color(0, 0, 145);
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLS = 7;
    public final int MARGIN_X;
    public final int MARGIN_Y;
    public final int RING_WIDTH;
    public final int CHIP_RADIUS;
    public final int CHIP_PADDING;
    public final int CHIP_DIAMETER;
    public final int BOARD_WIDTH;
    public final int BOARD_HEIGHT;
    public final Area BOARD_AREA;
    public Point[][] circlePoints;

    public ConnectBoard(LayoutManager layout, Point[][] circlePoints, int chipRadius, int chipPadding, int ringWidth) {
        super(layout, App.gameDimension);
        this.circlePoints = circlePoints;
        RING_WIDTH = ringWidth;
        CHIP_RADIUS = chipRadius;
        CHIP_PADDING = chipPadding;
        CHIP_DIAMETER = CHIP_RADIUS * 2;
        BOARD_WIDTH = CHIP_PADDING + (CHIP_DIAMETER + CHIP_PADDING) * NUM_COLS;
        BOARD_HEIGHT = CHIP_PADDING + (CHIP_DIAMETER + CHIP_PADDING) * NUM_ROWS; 
        MARGIN_X = ((int) App.gameDimension.getWidth() - BOARD_WIDTH) / 2;
        MARGIN_Y = (int) App.gameDimension.getHeight() - BOARD_HEIGHT;

        initializeBoard();
        BOARD_AREA = createBoardArea(
                new Rectangle2D.Double(0, 0, (int) App.gameDimension.getWidth(), (int) App.gameDimension.getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(LIGHT_BLUE);
        paintBackground((Graphics2D) g);
        g.setClip(BOARD_AREA);
        g.setColor(DARK_BLUE);
        paintEmptySpots((Graphics2D) g);
        paintEntities((Graphics2D) g);
        // paintRings((Graphics2D) g);
    }

    private void paintEmptySpots(Graphics2D g) {
        g.setColor(Color.WHITE);
        Arrays.stream(circlePoints)
                .forEach(pa -> Arrays.stream(pa).forEach(p -> PaintHelper.fillCircleFromMiddle(p, CHIP_RADIUS, g)));
    }

    private void paintRings(Graphics2D g) {
        g.setColor(DARK_BLUE);
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

    private Area createBoardArea(Rectangle2D.Double baseBoard) {
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
        //Adds all outside area thats not the board to the boardArea
        int[] boardXS = {MARGIN_X, MARGIN_X, MARGIN_X + BOARD_WIDTH, MARGIN_X + BOARD_WIDTH};
        int[] boardYS = {MARGIN_Y, MARGIN_Y + BOARD_HEIGHT, MARGIN_Y + BOARD_HEIGHT, MARGIN_Y};
        Polygon Box = new Polygon(boardXS, boardYS, 4);
        Area nonBoardArea = new Area(Box);
        nonBoardArea.exclusiveOr(baseArea);
        boardArea.add(nonBoardArea);

        return boardArea;
    }

    @Override
    public void paintBackground(Graphics2D g) {
        g.fillRect(MARGIN_X, MARGIN_Y, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
