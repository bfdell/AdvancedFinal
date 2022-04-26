package csis.dptw;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
//LIST OF LAMBDAS FOR EACH EVENT IN A LIST AND THEY ARE ALL ORDERED IN TERMS OF HOW THEY SHOULD BE EXECUTED
public abstract class GameInput implements MouseMotionListener, MouseListener, KeyListener {
    //USE FUNCTIONS FOR ALREADY MADE INTERFACES
    //USE HASHATABLE WITH SPECIALIZED KEYS FOR SPECIFIC EVENTS
    ArrayList<Integer> specialKeys = new ArrayList<Integer>();
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {        
    }

    @Override
    public void keyReleased(KeyEvent e) {        
    }

    @Override
    public void keyTyped(KeyEvent e) {        
    }
    
    
}
