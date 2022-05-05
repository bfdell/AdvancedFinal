package csis.dptw;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import csis.dptw.CupPong.*;
import csis.dptw.engine.Game;
import csis.dptw.Connect4.*;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;

//POSSIBLY USE DATA TRANSFER TO TRANSFER DATA
/**
 * Hello world!
 *
 */
public class App implements ActionListener, Runnable {
    public static Dimension gameDimension;
    JFrame frame;

    //////
    JButton startConnect4;
    JButton startCupPong;
    JLabel select;
    JLabel title;

    Game testGame;
    JPanel app;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new App());
    }

    @Override
    public void run() {
        initFrame();
        makeMenu();

        // Game testGame = new Connect4();
         //Game testGame = new CupPong();
         //Thread gameThread = new Thread(testGame);
         //gameThread.start();

        // ASK TERESCO QUESTIONS ABOUT RUNNABLE AND NOT USING INVOKE LATER ETC.....
        // testGame.gamePanel.addEntity(new Entity(testGame, new Point(1, 1),
        // "competitioneagle/src/main/java/csis/dptw/TCUP.png"));

        frame.setVisible(true);
    }

    private void displayGame(Game testGame) {
        frame.add(testGame.gamePanel);
        frame.setVisible(true);
    }

    private void initFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Competition Eagle ");
        frame.setPreferredSize(new Dimension(850, 850));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        gameDimension = new Dimension(850, 850 - frame.getInsets().top - frame.getInsets().bottom);
    }

    public void makeMenu() {
        app = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;


        title = new JLabel("Competition Eagle");
        Font titleFont = new Font("SansSerif", Font.BOLD, 50);
        title.setFont(titleFont);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.PAGE_START;
        c.gridx = 1;
        c.gridy = 0;
        app.add(title,c);

        select = new JLabel("Select A Game");
        Font selectFont = new Font("SansSerif", Font.BOLD, 20);
        select.setFont(selectFont);
        c.fill = GridBagConstraints.CENTER;
        c .weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;    
        app.add(select,c);

        startConnect4 = new JButton("Connect4");
        c.fill = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        app.add(startConnect4,c);
        startConnect4.addActionListener(this);

        startCupPong = new JButton("Cup Pong");
        c.fill = GridBagConstraints.LINE_END;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        app.add(startCupPong,c);
        startCupPong.addActionListener(this);

        frame.add(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startConnect4) {
            testGame = new Connect4();
        } else if (e.getSource() == startCupPong) {
            testGame = new CupPong();
        }

        // app.remove(c4);
        // app.remove(cPong);
        // app.remove(select);
        // app.remove(title);
        frame.remove(app);
        displayGame(testGame);

    }
}
