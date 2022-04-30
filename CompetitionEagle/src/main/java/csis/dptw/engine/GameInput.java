package csis.dptw.engine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import csis.dptw.engine.Event.EventType;

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
    private HashMap<EventType, PriorityQueue<EventFunction>> events;
    //MAKE anonymous threads that carry out every event type at the same time?

    //PriorityQueues for events
    PriorityQueue<Event> mPressedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mDraggedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mMovedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mReleasedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mClickedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mEnteredQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> mExitedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> kPressedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> kReleasedQueue = new PriorityQueue<Event>();
    PriorityQueue<Event> kTypedQueue = new PriorityQueue<Event>();

    public GameInput() {
        // EnumSet.allOf(EventType.class).forEach(eventType -> events.put(eventType, new PriorityQueue<EventFunction>()));
    }

    public void addMouseEvent(EventType eventType, EventFunction function, int priorityNum) {
        String eventName = eventType.NAME;
        Event eventToAdd = new Event(function, eventType, priorityNum);

        switch (eventName) {
            case "MPRESSED":
                mPressedQueue.add(eventToAdd);
                break;
            case "MRELEASED":
                mReleasedQueue.add(eventToAdd);
                break;
            case "MDRAGGED":
                mDraggedQueue.add(eventToAdd);
                break;
            case "MCLICKED":
                mClickedQueue.add(eventToAdd);
                break;
            case "MMOVED":
                mMovedQueue.add(eventToAdd);
                break;
            case "MENTERED":
                mEnteredQueue.add(eventToAdd);
                break;
            case "MEXITED":
                mExitedQueue.add(eventToAdd);
                break;
        }
    }

    public void addKeyEvent(EventType eventType, EventFunction function, int priorityNum) {
        String eventName = eventType.NAME;
        Event eventToAdd = new Event(function, eventType, priorityNum);

        switch (eventName) {
            case "MPRESSED":
                kPressedQueue.add(eventToAdd);
                break;
            case "MRELEASED":
                kReleasedQueue.add(eventToAdd);
                break;
            case "MDRAGGED":
                kTypedQueue.add(eventToAdd);
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mDraggedQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mMovedQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        mClickedQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mEnteredQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mExitedQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mPressedQueue.forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mReleasedQueue.forEach(event -> event.function.execute((Game) this));
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
