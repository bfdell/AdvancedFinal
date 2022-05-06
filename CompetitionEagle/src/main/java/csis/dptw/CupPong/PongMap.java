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
    public static final Color PROMPT_COLOR = Color.RED;
    public static final Font TITLE_FONT = new Font("SignPainter", Font.BOLD, 100);
    public static final String PROMPT_MESSAGE = "Press space to start your shot";   
    public static final Font PROMPT_FONT = new Font("Comic Sans MS", Font.BOLD, 30);
    ///////////////////////
    public static final double BOARD_Y_PERCENT = .3;
    public static final double BOARD_X_PERCENT = .3;
    boolean playerShooting = false;

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

        if(!playerShooting && playing) {
            g.setColor(PROMPT_COLOR);
            g.setFont(PROMPT_FONT);
            g.drawString(PROMPT_MESSAGE, (getWidth() - g.getFontMetrics().stringWidth(PROMPT_MESSAGE))/2, (int) (App.gameDimension.getHeight() / 2));
        }

    }
}
