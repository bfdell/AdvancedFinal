package csis.dptw.engine;

import java.util.*;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Event implements Comparable<Event> {
    public EventFunction function;
    public EventType eventType;
    public int priorityNum;
    public Integer keyStroke = null;

    public enum EventType {
        MPRESSED,
        MDRAGGED,
        MMOVED,
        MRELEASED,
        MCLICKED,
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
    public Event(EventFunction function, EventType eventType, int keyStroke, int priorityNum) {
        this.function = function;
        this.eventType = eventType;
        this.keyStroke = keyStroke;
        this.priorityNum = priorityNum;
    }


    @Override
    public int compareTo(Event event) {
        int nameComparason = eventType.NAME.compareTo(event.eventType.NAME);

        if(nameComparason != 0) {
            return nameComparason;
        } else {
            return priorityNum - event.priorityNum;
        }
    }
}
