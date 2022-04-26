package csis.dptw.util;
import java.awt.geom.*;
import java.awt.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */

public class PaintHelper {

    //COLOR AS PARAMETER FOR ALL OF THESE
    
    public static void fillOvalFromMiddle(Point middle, int heightRadius, int widthRadius, Graphics2D g) {
        int upperLeftX = middle.x - widthRadius;
        int upperLeftY = middle.y - heightRadius;
    
        g.fillOval(upperLeftX, upperLeftY, widthRadius * 2, heightRadius * 2);
    }
    
    public static void fillCircleFromMiddle(Point middle, int radius, Graphics2D g) {
        int upperLeftX = middle.x - radius;
        int upperLeftY = middle.y - radius;
        
        g.fillOval(upperLeftX, upperLeftY, radius * 2, radius * 2);
    }
    
    public static void drawCircleFromMiddle(Point middle, int radius, Graphics2D g) {
        int upperLeftX = middle.x - radius;
        int upperLeftY = middle.y - radius;
        
        g.drawOval(upperLeftX, upperLeftY, radius * 2, radius * 2);
    }

    public static void drawCircleFromMiddle(Point middle, int radius, Graphics2D g, int circleWidth) {
        int upperLeftX = middle.x - radius;
        int upperLeftY = middle.y - radius;
        
        g.setStroke(new BasicStroke(circleWidth));
        g.drawOval(upperLeftX, upperLeftY, radius * 2, radius * 2);
        g.setStroke(new BasicStroke(1));
    }
    
    public static void drawOvalFromMiddle(Point middle, int heightRadius, int widthRadius, Graphics2D g) {
        int upperLeftX = middle.x - widthRadius;
        int upperLeftY = middle.y - heightRadius;
    
        g.drawOval(upperLeftX, upperLeftY, widthRadius * 2, heightRadius * 2);
    }

    public static void drawOvalFromMiddle(Point middle, int heightRadius, int widthRadius, Graphics2D g, int ovalWidth) {
        int upperLeftX = middle.x - widthRadius;
        int upperLeftY = middle.y - heightRadius;
    
        g.setStroke(new BasicStroke(ovalWidth));
        g.drawOval(upperLeftX, upperLeftY, widthRadius * 2, heightRadius * 2);
        g.setStroke(new BasicStroke(1));
    }
}
