package csis.dptw.engine;
import javax.swing.*;

import csis.dptw.engine.Event.EventType;

import java.awt.*;

//MAYBE CHANGE TO ABSTRACT CLASS OR INTERFACE
public class Game extends GameInput implements Runnable {
    public Map gamePanel;
    public Repainter refresher = new Repainter();

    public java.util.List<Object> events = new java.util.ArrayList<Object>();
////////////////////////////////////////////////////////////////
      String test = "STRING";
     int i;
    ////////////////////////////////
    public Game() {
        // gamePanel = new Map(new FlowLayout());
        //RUN
        // javax.swing.SwingUtilities.invokeLater(new Game(new FlowLayout()));
    }

    public Game(LayoutManager layout) {
        gamePanel = new Map(layout);
    }

    @Override
    public void run() {
        addInputListeners();

        addMouseEvent(EventType.MRELEASED, i -> testEvent(i), 2);
        addMouseEvent(EventType.MPRESSED, i -> testEvent2(i), 1);
        addMouseEvent(EventType.MCLICKED, i -> testEvent2(i), 1);
    }



    //TESTING PURPOSES
    public static void testEvent(Game game) {
        System.out.println(game.test);
        game.test += game.i++ + ",";
    }

    //TESTING PURPOSES
    public static void testEvent2(Game game) {
        System.out.println(game.i * 50 + "------------");
    }

    public void addInputListeners() {
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
        gamePanel.addKeyListener(this);
    }
}
