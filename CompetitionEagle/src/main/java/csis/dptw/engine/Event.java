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
public class Event implements Comparable<Event> {
    EventFunction function;
    EventRestriction restriction;

    public EventType eventType;
    public int priorityNum; //GETTERS AND SETTERS FOR PRIORITY NUM

    public enum EventType {
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

    public Event(EventType eventType, EventFunction function, int priorityNum) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
    }

    public Event(EventType eventType, EventFunction function, int priorityNum, EventRestriction restriction) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
        this.restriction = restriction;
    }

    public void execute(InputEvent e) {
        function.call(e);
    }

    // public boolean validate(Object t) {
    //    return function.isValid(t);
    // }

    @Override
    public int compareTo(Event event) {
        return priorityNum - event.priorityNum;
    }
}
