package csis.dptw.engine;

import javax.swing.JComponent;

public class Repainter extends Thread {
    

    public final static int DELAY_TIME = 33 ;

    JComponent container;

    public Repainter(JComponent container){
        this.container = container;
    } 
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
