import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class ConnectFour extends MouseAdapter implements Runnable {

    @Override
    public void mouseClicked(MouseEvent e) {

        super.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        super.mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        super.mouseReleased(e);
    }

    @Override
    public void run() {

        
    }
    
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new ConnectFour());
    }
}
