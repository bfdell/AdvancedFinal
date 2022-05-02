package csis.dptw.engine;
import java.awt.event.*;
import java.awt.*;


/**
 * This is a functional interface for an event that can occur in game. 
 * It is used to create lambda functions that inplement the call() 
 * method to achieve their desired functionality.
 * @author Brina Dell
 * @version Spring 2022
 */
@FunctionalInterface
public interface EventFunction {

    /**
     * Calls lambda function that was implemented to create instance of interface.
     */
    public void call(AWTEvent e);
}
