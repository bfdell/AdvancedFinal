package csis.dptw.engine;

/**
 * Functional interface used to create lambdas that determine whether or not
 * an event is allowed to execute. This is achieved throught the isValid() method
 * That returns a boolean that represents the validity of the event.
 * @author Brian Dell
 * @version Spring 2022
 */
@FunctionalInterface
public interface EventRestriction {
    /**
     * Method that determines whether or not an event is allowed to execute
     * 
     * @param param paramater passed to method that will help validate event. 
     * This is of type object because we will be passing many different object to this lambda,
     * and it will be up to the user to cast to the class they want to use, 
     * or make their implementation of isvalid take whatever class they want to
     * @return True or false depending on if the event is allowed to execute
     */
    public boolean isValid(Object param);
}
