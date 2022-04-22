package csis.dptw;

import javax.swing.JFrame;
import java.awt.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();


        Game testGame = new Game(new FlowLayout());
        
        frame.add(testGame.gamePanel);
        frame.pack();
        testGame.gamePanel.addEntity(new Entity("CUP.png", testGame));
        frame.setVisible( true );

    }
}
