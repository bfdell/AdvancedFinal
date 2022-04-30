package csis.dptw.engine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import csis.dptw.engine.Event.EventType;

/**
 * This class represents input sources for a Game, which includes MouseEvents and KeyEvents.
 * It also implements many functionality for passing in lambda funcions to be executed by events.
 * It organizes events into different PriorityQueues for each type of event, 
 * So the order that the lambda functions get executed in can be assigned. 
 * 
 * @author Brian Dell
 * @version Spring 2022
 */
public abstract class GameInput implements MouseMotionListener, MouseListener, KeyListener {
    //MAKE anonymous threads that carry out every event type at the same time?
    //^^^ Probably not because only one input event can be done at a time mostly.

    //PriorityQueues for event types
    private PriorityQueue<Event> mPressedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mDraggedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mMovedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mReleasedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mClickedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mEnteredQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> mExitedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> kPressedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> kReleasedQueue = new PriorityQueue<Event>();
    private PriorityQueue<Event> kTypedQueue = new PriorityQueue<Event>();

    public GameInput() {
    }

    public void addMouseEvent(EventType eventType, EventFunction function, int priorityNum) {
        Event eventToAdd = new Event(function, eventType, priorityNum);

        switch (eventType) {
            case MPRESSED:
                mPressedQueue.add(eventToAdd);
                break;
            case MRELEASED:
                mReleasedQueue.add(eventToAdd);
                break;
            case MDRAGGED:
                mDraggedQueue.add(eventToAdd);
                break;
            case MCLICKED:
                mClickedQueue.add(eventToAdd);
                break;
            case MMOVED:
                mMovedQueue.add(eventToAdd);
                break;
            case MENTERED:
                mEnteredQueue.add(eventToAdd);
                break;
            case MEXITED:
                mExitedQueue.add(eventToAdd);
                break;
            default:
                break;
        }
    }

    public void addKeyEvent(EventType eventType, EventFunction function, int priorityNum,
            EventRestriction keyRestriction) {
        Event eventToAdd = new Event(function, eventType, priorityNum);

        switch (eventType) {
            case MPRESSED:
                kPressedQueue.add(eventToAdd);
                break;
            case MRELEASED:
                kReleasedQueue.add(eventToAdd);
                break;
            case MDRAGGED:
                kTypedQueue.add(eventToAdd);
                break;
            default:
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
        kPressedQueue.stream().filter(event -> event.restriction.isValid(e.getKeyCode()))
                .forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        kReleasedQueue.stream().filter(event -> event.restriction.isValid(e.getKeyCode()))
                .forEach(event -> event.function.execute((Game) this));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        kTypedQueue.stream().filter(event -> event.restriction.isValid(e.getKeyCode()))
                .forEach(event -> event.function.execute((Game) this));
    }
}
