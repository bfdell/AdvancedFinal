package csis.dptw.engine;

import java.util.*;
import java.util.function.Predicate;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represent an event in java that can be any one of the event types specified in the EventType enumeration.
 * 
 * It is comparable so we can organize event into priority queues of execution.
 * @author Brian Dell
 * @version Spring 2022
 */
public class Event<T> implements Comparable<Event<T>> {
    EventFunction<T> function;
    EventRestriction restriction;

    public EventType eventType;
    public int priorityNum; //GETTERS AND SETTERS FOR PRIORITY NUM

    public enum EventType {
        ACTION,
        PROPERTYCHANGE,
        MPRESSED,
        MDRAGGED,
        MMOVED,
        MRELEASED,
        MCLICKED, //CLICKED MEANS IT WASN'T MOVED BETWEEN BEING PRESSED AND REMOVED
        MENTERED,
        MEXITED,
        KPRESSED,
        KTYPED, //getKeyCode doesnty workd for type, must use getKeyChar
        KRELEASED;

        public final String NAME;

        private EventType() {
            NAME = toString();
        }
    }

    /**
     * Constructs an event 
     * @param eventType The type of event
     * @param function The function the event is executing
     * @param priorityNum The order in which the event is exeuted in comparason to other events of the same type.
     */
    public Event(EventType eventType, EventFunction<T> function, int priorityNum) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
    }

    /**
     * Constructs an event 
     * @param eventType The type of event
     * @param function The function the event is executing
     * @param priorityNum The order in which the event is exeuted in comparason to other events of the same type.
     * @param restriction Restriction that dictates whether or not event is executed.
     */
    public Event(EventType eventType, EventFunction<T> function, int priorityNum, EventRestriction restriction) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
        this.restriction = restriction;
    }

    /**
     * Executes event by calling call() method of function instance varible
     * @param event
     */
    public void execute(T event) {
        function.call(event);
    }

    @Override
    public int compareTo(Event<T> event) {
        return priorityNum - event.priorityNum;
    }
}
