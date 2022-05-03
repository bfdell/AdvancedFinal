package csis.dptw.engine;

import java.util.*;
import java.util.function.Predicate;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Event<T> implements Comparable<Event<T>> {
    EventFunction<T> function;
    EventRestriction restriction;

    public EventType eventType;
    public int priorityNum; //GETTERS AND SETTERS FOR PRIORITY NUM

    public enum EventType {
        Action,
        MPRESSED,
        MDRAGGED,
        MMOVED,
        MRELEASED,
        MCLICKED, //CLICKED MEANS IT WASN'T MOVED BETWEEN BEING PRESSED AND REMOVED
        MENTERED,
        MEXITED,
        //KEY EVENTS EXECUTE IN ORDER: PRESSED, TYPED, RELEASED
        KPRESSED,
        KTYPED,  //getKeyCode doesnty workd for type, must use getKeyChar
        KRELEASED;

        public final String NAME;

        private EventType() {
            NAME = toString();
        }
    }

    public Event(EventType eventType, EventFunction<T> function, int priorityNum) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
    }

    public Event(EventType eventType, EventFunction<T> function, int priorityNum, EventRestriction restriction) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
        this.restriction = restriction;
    }

    public void execute(T event) {
        function.call(event);
    }

    // public boolean validate(Object t) {
    //    return function.isValid(t);
    // }

    @Override
    public int compareTo(Event<T> event) {
        return priorityNum - event.priorityNum;
    }
}
