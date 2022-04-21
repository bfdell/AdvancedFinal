import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.awt.geom.*;

public class CupPong extends MouseAdapter implements Runnable {
 JPanel mainPanel; 

 public final int LANE_WIDTH = 500;
 public double boardYPercent = .3; 
 public double boardXPercent = .3;


 ///////////////////////
 public double triangleYPercent = .35;
 public double triangleXPercent = .15;
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Cup Pong!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 600));
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            public void paintComponent(Graphics g){
                //g.fillRect(x, y, width, height);
                drawBoard(g);
            }
        };
        frame.setResizable(false);

        //Makes scoreboard that tracks gameProgress and adds it to main panel
        //mainPanel.add(createScoreboard(), BorderLayout.EAST);

        //Makes gamePanel and adds it to main panel
        //gamePanel = new SkiPanel();
        // gamePanel.setPreferredSize(new Dimension(LANE_WIDTH, LANE_HEIGHT));
        // mainPanel.add(gamePanel, BorderLayout.CENTER);
        // gamePanel.addMouseListener(this);
        // gamePanel.addMouseMotionListener(this);

        //Display the window we've created
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void drawBoard(Graphics g){
        // g.fillPolygon();
        int endX = mainPanel.getWidth();
        int height = mainPanel.getHeight();

        int topY = (int)(height * boardYPercent);

        int xOffset = (int)( endX * boardXPercent);
        int leftX = xOffset;
        int rightX = endX - xOffset;

        int[] boardXS = {0, endX, rightX, leftX};
        int[] boardYS = {height, height, topY, topY};


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
        int[] outlineXS = {outlineLeftX, outlineRightX, endX / 2};
        int[] outlineYS = {topY, topY, cupTip};

        Polygon cupOutline = new Polygon(outlineXS, outlineYS, outlineXS.length);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(8));
        g.drawPolygon(cupOutline);

    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new CupPong());
    }
}
