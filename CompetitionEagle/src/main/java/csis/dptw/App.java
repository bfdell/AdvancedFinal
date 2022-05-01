package csis.dptw;
import javax.swing.JFrame;
import javax.swing.JPanel;

import csis.dptw.CupPong.*;
import csis.dptw.engine.Game;
import csis.dptw.Connect4.*;

import java.awt.*;
import java.io.IOException;
import java.util.*;



//POSSIBLY USE DATA TRANSFER TO TRANSFER DATA
/**
 * Hello world!
 *
 */
public class App implements Runnable {
    public static Dimension gameDimension;
    JFrame frame;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new App());     

        //Lambda Example
        // java.util.List<Integer> test = Arrays.asList(4, 4, 6, 8, 3, 5, 8, 9, 4, 1, 6, 9, 5, 3, 7, 4);
        // test.forEach(i -> System.out.print(i * 4 + ", "));
        // Collections.sort(test, (s1, s2) -> s2.compareTo(s1));
        // System.out.println("\n" + test);
        // test.forEach(i -> {
        //     if (i % 2 == 0) {
        //         System.out.print(i + ", ");
        //     }
        // });
    }

    @Override
    public void run() {
        initFrame();
        Game testGame = new Connect4();
        // Game testGame = new CupPong();
        // Thread gameThread = new Thread(testGame);
        // gameThread.start();

        //ASK TERESCO QUESTIONS ABOUT RUNNABLE AND NOT USING INVOKE LATER ETC.....
        // testGame.gamePanel.addEntity(new Entity(testGame, new Point(1, 1), "competitioneagle/src/main/java/csis/dptw/TCUP.png"));
        displayGame(testGame);
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
        gameDimension = new Dimension(850, 850 - frame.getInsets().top);
    }
}
