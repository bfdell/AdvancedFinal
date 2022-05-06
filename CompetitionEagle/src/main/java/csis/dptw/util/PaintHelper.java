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
    public static void fillCircleFromMiddleDouble(Point2D middle, double radius, Graphics2D g) {
        double upperLeftX = middle.getX() - radius;
        double upperLeftY = middle.getY() - radius;
        
       Arc2D.Double myArc =  makeArcFromMiddle(middle, radius, 0.0, 360.0, Arc2D.PIE);
       g.fill(myArc);
        // g.fillOval(upperLeftX, upperLeftY, radius * 2, radius * 2);
    }
    
    public static void drawCircleFromMiddleDouble(Point2D middle, double radius, Graphics2D g) {
        double upperLeftX = middle.getX() - radius;
        double upperLeftY = middle.getY() - radius;
        
        Arc2D.Double myArc =  makeArcFromMiddle(middle, radius, 0.0, 360.0, Arc2D.PIE);
        g.draw(myArc);
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

    public static Arc2D.Double makeArcFromMiddle(Point2D middle, double radius, double start, double extent, int type) {
        double upperLeftX = middle.getX() - radius;
        double upperLeftY = middle.getY() - radius;
        
        return new Arc2D.Double(upperLeftX, upperLeftY, radius * 2.0, radius * 2.0,start, extent, type);
    }
    public static Arc2D.Double makeArcOvalFromMiddle(Point middle, int widthRadius, int heightRadius, double start, double extent, int type) {
        int upperLeftX = middle.x - widthRadius;
        int upperLeftY = middle.y -heightRadius;
        
        return new Arc2D.Double(upperLeftX, upperLeftY, heightRadius * 2.0, widthRadius * 2.0, start, extent, type);
    }

    public static void changeArcMiddleLocation(Arc2D.Double arc, Point newLocation) {
        double widthRadius = arc.getWidth() / 2.0;
        double heightRadius = arc.getHeight() / 2.0;
        arc.x = newLocation.x - widthRadius;
        arc.y = newLocation.y - heightRadius;
    }
}
