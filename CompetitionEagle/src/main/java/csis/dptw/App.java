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
    JButton c4;
    JButton cPong;
    JLabel mainMenu;
    Game testGame;
    JPanel app; 

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new App());     
    }

    @Override
    public void run() {
        
        initFrame();
        makeMenu();
        
        //Game testGame = new Connect4();
        //  Game testGame = new CupPong();
        // Thread gameThread = new Thread(testGame);
        // gameThread.start();

        //ASK TERESCO QUESTIONS ABOUT RUNNABLE AND NOT USING INVOKE LATER ETC.....
        // testGame.gamePanel.addEntity(new Entity(testGame, new Point(1, 1), "competitioneagle/src/main/java/csis/dptw/TCUP.png"));
        if(testGame != null){
          
            ////////////////REMOVE ALL BUTTONS AND SHIT
            gamePanel.remove(c4);
            gamePanel.remove(mainMenu);
            addCups();
                displayGame(testGame);
        }
    }

    private void displayGame(Game testGame) {
        frame.add(testGame.gamePanel);
        frame.setVisible( true );
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
        app = new JPanel();

        c4 = new JButton("Connect4");
        cPong = new JButton("Cup Pong");
        
        c4.addActionListener(this);   
        cPong.addActionListener(this);   

        app.add(c4);
        app.add(cPong);
        
        frame.add(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == c4){
            testGame = new Connect4();
        }else if(e.getSource() == cPong){
            testGame = new CupPong();
        }
    }
}
