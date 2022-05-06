package csis.dptw.engine;

import javax.swing.JComponent;

/**
 * Repaints the panel
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */

public class Repainter extends Thread {
    

    public final static int DELAY_TIME = 33 ;

    JComponent container;

    /**
     * initializes the container
     * @param container the JComponent being used
     */
    public Repainter(JComponent container){
        this.container = container;
    } 

    /**
     * allows the container to be repainted DELAY_TIME times per second
     */
    public void run(){
       while(true){
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }

            container.repaint();
        }
    }
}
