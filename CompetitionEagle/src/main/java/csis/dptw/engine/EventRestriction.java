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
    public boolean isValid(Object param);
}
