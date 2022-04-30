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
        KPRESSED,
        KRELEASED,
        KTYPED;

        public final String NAME;

        private EventType() {
            NAME = toString();
        }
    }

    public Event(EventFunction function, EventType eventType, int priorityNum) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
    }

    public Event(EventFunction function, EventType eventType, int priorityNum, EventRestriction restriction) {
        this.function = function;
        this.eventType = eventType;
        this.priorityNum = priorityNum;
        this.restriction = restriction;
    }

    @Override
    public int compareTo(Event event) {
        return priorityNum - event.priorityNum;
    }
}
