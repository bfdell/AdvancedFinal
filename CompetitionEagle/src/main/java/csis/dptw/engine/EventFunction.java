package csis.dptw.engine;

/**
 * This is a functional interface for an event that can occur in game. 
 * It is used to create lambda functions that inplement the execute() 
 * method to achieve their desired functionality.
 * @author Brina Dell
 * @version Spring 2022
 */
@FunctionalInterface
public interface EventFunction {
    public void execute(Game game);
}
