package csis.dptw.CupPong;

import java.awt.*;

import csis.dptw.engine.Map;

public class PongMap extends Map{
    public final int LANE_WIDTH = 500;
    ///////////////////////
    public double boardYPercent = .3;
    public double boardXPercent = .3;
    public double triangleYPercent = .35;
    public double triangleXPercent = .15;

    public PongMap(LayoutManager layout) {
        super(layout);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void paintBackground(Graphics2D g) {
        int endX = getWidth();
        int height = getHeight();

        int topY = (int) (height * boardYPercent);

        int xOffset = (int) (endX * boardXPercent);
        int leftX = xOffset;
        int rightX = endX - xOffset;

        int[] boardXS = { 0, endX, rightX, leftX };
        int[] boardYS = { height, height, topY, topY };

        Polygon board = new Polygon(boardXS, boardYS, boardXS.length);
        g.setColor(Color.green);
        g.fillPolygon(board);
        g.setColor(Color.WHITE);
        ((Graphics2D) g).setStroke(new BasicStroke(10));
        g.drawPolygon(board);
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.drawLine(endX / 2, topY, endX / 2, height);

        //////////////////////
        int boardHeight = height - topY;
        int boardWidth = rightX - leftX;

        int triangleOffset = (int) (boardWidth * triangleXPercent);
        int outlineLeftX = (int) leftX + triangleOffset;
        int outlineRightX = (int) rightX - triangleOffset;

        int cupTip = (int) (boardHeight * triangleYPercent) + topY;
        int[] outlineXS = { outlineLeftX, outlineRightX, endX / 2 };
        int[] outlineYS = { topY, topY, cupTip };

        Polygon cupOutline = new Polygon(outlineXS, outlineYS, outlineXS.length);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(8));
        g.drawPolygon(cupOutline);
    }
    
}