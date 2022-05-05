package csis.dptw.CupPong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Polygon;

import csis.dptw.App;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Map;

public class PongMap extends Map{
    public final Color DARK_GREEN = new Color(30, 112, 52);
    public static final Color CUP_COLOR = Color.RED;
    public static final Color PONG_COLOR = Color.BLACK;
    public static final Font TITLE_FONT = new Font("SignPainter", Font.BOLD, 100);
    ///////////////////////
    public static final double BOARD_Y_PERCENT = .3;
    public static final double BOARD_X_PERCENT = .3;

    public boolean playing = false;

    final String CUP = "Cup";
    final String PONG = "Pong";

    public PongMap(LayoutManager layout) {
        super(layout, App.gameDimension);
        
    }
    
    @Override
    public void paintBackground(Graphics2D g) {
        if (playing) {
             Image img = Entity.toolkit.getImage("CompetitionEagle/src/main/java/csis/dptw/pongbg.png");
             g.drawImage(img, -500, 0, this);
            int height = getHeight();
            int endX = getWidth();
            int topY = (int) (height * BOARD_Y_PERCENT);

            int xOffset = (int) (endX * BOARD_X_PERCENT);
            int leftX = xOffset;
            int rightX = endX - xOffset;

            int[] boardXS = { 0, endX, rightX, leftX };
            int[] boardYS = { height, height, topY, topY };

            Polygon board = new Polygon(boardXS, boardYS, boardXS.length);
            g.setColor(DARK_GREEN);
            g.fillPolygon(board);
            g.setColor(Color.WHITE);
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawPolygon(board);
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawLine(endX / 2, topY, endX / 2, height);
        } else {
            g.setFont(TITLE_FONT);
    
            g.setColor(Color.RED);
            g.drawString(CUP, (getWidth() - g.getFontMetrics().stringWidth(CUP + " " + PONG))/2, g.getFontMetrics().getHeight());
    
            g.setColor(Color.BLACK);
            g.drawString(" "+ PONG, (getWidth() - g.getFontMetrics().stringWidth(CUP + " " + PONG))/2 + (g.getFontMetrics().stringWidth(CUP)), g.getFontMetrics().getHeight());
        }
        

        // g.setColor(Color.BLACK);
        // g.drawOval(389, 305, 50, 50);
        
        //////////////////////
        // int boardHeight = height - topY;
        // int boardWidth = rightX - leftX;

        // int triangleOffset = (int) (boardWidth * triangleXPercent);
        // int outlineLeftX = (int) leftX + triangleOffset;
        // int outlineRightX = (int) rightX - triangleOffset;

        // int cupTip = (int) (boardHeight * triangleYPercent) + topY;
        // int[] outlineXS = { outlineLeftX, outlineRightX, endX / 2 };
        // int[] outlineYS = { topY, topY, cupTip };

        // Polygon cupOutline = new Polygon(outlineXS, outlineYS, outlineXS.length);
        // g.setColor(Color.BLACK);
        // ((Graphics2D) g).setStroke(new BasicStroke(8));
        // g.drawPolygon(cupOutline);
    }

    // @Override
    // public void paintEntities(Graphics2D g) {

    // }

}
