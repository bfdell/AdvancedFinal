package csis.dptw.engine;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.AbstractButton;

import csis.dptw.engine.Event.EventType;

/**
 * This class represents input sources for a Game, which includes MouseEvents
 * and KeyEvents.
 * It also implements many functionality for passing in lambda funcions to be
 * executed by events.
 * It organizes events into different PriorityQueues for each type of event,
 * So the order that the lambda functions get executed in can be assigned.
 * 
 * @author Brian Dell
 * @version Spring 2022
 */
public abstract class GameInput implements MouseMotionListener, MouseListener, KeyListener, ActionListener, PropertyChangeListener {
    // MAKE anonymous threads that carry out every event type at the same time?
    // ^^^ Probably not because only one input event can be done at a time mostly.

    // PriorityQueues for event types
    private PriorityQueue<Event<MouseEvent>> mPressedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mDraggedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mMovedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mReleasedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mClickedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mEnteredQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<MouseEvent>> mExitedQueue = new PriorityQueue<Event<MouseEvent>>();
    private PriorityQueue<Event<KeyEvent>> kPressedQueue = new PriorityQueue<Event<KeyEvent>>();
    private PriorityQueue<Event<KeyEvent>> kTypedQueue = new PriorityQueue<Event<KeyEvent>>();
    private PriorityQueue<Event<KeyEvent>> kReleasedQueue = new PriorityQueue<Event<KeyEvent>>();
    private PriorityQueue<Event<ActionEvent>> actionEventQueue = new PriorityQueue<Event<ActionEvent>>();
    private volatile PriorityQueue<Event<PropertyChangeEvent>> propertyEventQueue = new PriorityQueue<Event<PropertyChangeEvent>>();

    public GameInput() {
    }

    // Different methods for different restrictions or no restrictions
    public void addMouseEvent(EventType eventType, EventFunction<MouseEvent> function, int priorityNum) {
        Event<MouseEvent> eventToAdd = new Event<MouseEvent>(eventType, function, priorityNum);

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

    //ADD GENERICS FOR EVENT RESTRICTION
    public void addKeyEvent(EventType eventType, EventFunction<KeyEvent> function, int priorityNum,
            int keyRestriction) {

        Event<KeyEvent> eventToAdd = new Event<KeyEvent>(eventType, function, priorityNum,
                i -> ((KeyEvent) i).getKeyCode() == keyRestriction);

        switch (eventType) {
            case KPRESSED:
                kPressedQueue.add(eventToAdd);
                break;
            case KTYPED:
                // IF OUR EVENT IS A KEY_TYPE, ALLOW METHOD TO EXECUTE IF THE CHAR CODE
                // MATCHES THE UPPER OR LOWERCASE LETTER
                char charRestriction = (char) keyRestriction;
                EventRestriction keyTypeRestriction = i -> ((KeyEvent) i).getKeyChar() == charRestriction
                        || ((KeyEvent) i).getKeyChar() == (charRestriction | 0x20);
                Event<KeyEvent> keyTypeEvent = new Event<KeyEvent>(eventType, function, priorityNum,
                        keyTypeRestriction);
                kTypedQueue.add(keyTypeEvent);
                break;
            case KRELEASED:
                kReleasedQueue.add(eventToAdd);
                break;
            default:
                break;
        }
    }

    public void addKeyEvent(EventType eventType, EventFunction<KeyEvent> function, int priorityNum,
            java.util.List<Integer> keyRestrictions) {

        Event<KeyEvent> eventToAdd = new Event<KeyEvent>(eventType, function, priorityNum,
                i -> keyRestrictions.contains(((KeyEvent) i).getKeyCode()));

        switch (eventType) {
            case KPRESSED:
                kPressedQueue.add(eventToAdd);
                break;
            case KTYPED:
                // IF OUR EVENT IS A KEY_TYPE, ALLOW METHOD TO EXECUTE IF THE CHAR CODE
                // MATCHES THE UPPER OR LOWERCASE LETTER
                EventRestriction keyTypeRestriction = i -> keyRestrictions.stream()
                        .anyMatch(k -> ((KeyEvent) i).getKeyChar() == (char) (int) k
                                || ((KeyEvent) i).getKeyChar() == ((char) (int) k | 0x20));
                Event<KeyEvent> keyTypeEvent = new Event<KeyEvent>(eventType, function, priorityNum,
                        keyTypeRestriction);
                kTypedQueue.add(keyTypeEvent);
                break;
            case KRELEASED:
                kReleasedQueue.add(eventToAdd);
                break;
            default:
                break;
        }
    }

    // ALL ACTION EVENTS
    public void addActionEventUnattached(EventFunction<ActionEvent> function, int priorityNum) {
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum, i -> true);
        actionEventQueue.add(actionEvent);
    }

    public void addActionEvent(EventFunction<ActionEvent> function, int priorityNum, AbstractButton button) {
        button.addActionListener(this);
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum,
                e -> ((ActionEvent) e).getSource() == button);
        actionEventQueue.add(actionEvent);

    }

    public void addActionEvent(EventFunction<ActionEvent> function, int priorityNum, AbstractButton button,
            EventRestriction restriction) {
        button.addActionListener(this);
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum, restriction);
        actionEventQueue.add(actionEvent);
    }

    public void addPropertyEvent(EventFunction<PropertyChangeEvent> function, int priorityNum, Entity entity, String propertyName) {
        entity.addPropertyChangeListener(this, propertyName);
        Event<PropertyChangeEvent> propertyEvent = new Event<PropertyChangeEvent>(EventType.PROPERTYCHANGE, function, priorityNum);
        propertyEventQueue.add(propertyEvent);
    }

    public void removePropertyEvent(Entity entity, String propertyName, int priorityNum) {
        entity.removePropertyChangeListener(this, propertyName);
        propertyEventQueue.removeIf(pe -> pe.priorityNum == priorityNum);
    }

    //OVERRIDEN EVENT METHODS THAT EXECUTE ALL LAMBDA METHODS
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        propertyEventQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mDraggedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mMovedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        mClickedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mEnteredQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mExitedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mPressedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mReleasedQueue.forEach(event -> event.execute(e));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("CALLING");
        kPressedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        kReleasedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        kTypedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }
}
