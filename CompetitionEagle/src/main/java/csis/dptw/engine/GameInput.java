package csis.dptw.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.PriorityQueue;

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
public abstract class GameInput
        implements MouseMotionListener, MouseListener, KeyListener, ActionListener, PropertyChangeListener {

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

    /**
     * Adds a MouseEvent to class that executes passed in function when the key passed in as a parameter
     * has been pressed.
     * @param eventType The type of the event being passed in
     * @param function The function that the event executes
     * @param priorityNum The order that the event is executed in compared to other events of the same type
     */
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

    /**
    * Adds a KeyEvent to class that executes passed in function when the key passed in as a parameter
    * has been pressed.
    * @param eventType The type of the event being passed in
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    * @param keyRestriction The key that must be pressed for the event to be executed
    */
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

    /**
    * Adds a KeyEvent to class that executes passed in function when any of the keys passed in as a list has been pressed
    * @param eventType The type of the event being passed in
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    * @param keyRestrictions The list of keys that must be pressed for the event to be executed
    */
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

    /**
    * Adds a ActionEvent to class that executes passed in function when any button is pressed,
    * or any actionEvent is produced (unattached to a button)
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    */
    public void addActionEventUnattached(EventFunction<ActionEvent> function, int priorityNum) {
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum, i -> true);
        actionEventQueue.add(actionEvent);
    }

    /**
    * Adds a ActionEvent to class that executes passed in function when the button passed in has been used/pressed
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    * @param button The button that can activate this event
    */
    public void addActionEvent(EventFunction<ActionEvent> function, int priorityNum, AbstractButton button) {
        button.addActionListener(this);
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum,
                e -> ((ActionEvent) e).getSource() == button);
        actionEventQueue.add(actionEvent);

    }

    /**
    * Adds a ActionEvent to class that executes passed in function when the button passed in has been used/pressed
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    * @param button The button that can activate this event
    * @param restriction The boolean condition that must be true for the event to be executed
    */
    public void addActionEvent(EventFunction<ActionEvent> function, int priorityNum, AbstractButton button,
            EventRestriction restriction) {
        button.addActionListener(this);
        Event<ActionEvent> actionEvent = new Event<ActionEvent>(EventType.ACTION, function, priorityNum, restriction);
        actionEventQueue.add(actionEvent);
    }

    /**
    * Adds a PropertyChangeEvent to class that executes passed in function when the button passed in has been used/pressed
    * @param function The function that the event executes
    * @param priorityNum The order that the event is executed in compared to other events of the same type
    * @param entity The entity whos propery being changed executes the property change event
    * @param propertyName Name of the property/instance variable that will activate the event when changed.
    */
    public void addPropertyEvent(EventFunction<PropertyChangeEvent> function, int priorityNum, Entity entity,
            String propertyName) {
        entity.addPropertyChangeListener(this, propertyName);
        Event<PropertyChangeEvent> propertyEvent = new Event<PropertyChangeEvent>(EventType.PROPERTYCHANGE, function,
                priorityNum);
        propertyEventQueue.add(propertyEvent);
    }

    /**
    * removes a PropertyChangeEvent from class
    * @param entity The entity whos PropertyChangeEvent is being removed
    * @param propertyName Name of the property/instance variable that activates the event that is being removed
    * @param priorityNum The priority order of the event to be removed
    */
    public void removePropertyEvent(Entity entity, String propertyName, int priorityNum) {
        entity.removePropertyChangeListener(this, propertyName);
        propertyEventQueue.removeIf(pe -> pe.priorityNum == priorityNum);
    }

    //OVERRIDEN EVENT METHODS THAT EXECUTE ALL LAMBDA METHODS
    
    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        propertyEventQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mDraggedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mMovedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mClickedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        mEnteredQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseExited(MouseEvent e) {
        mExitedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mPressedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mReleasedQueue.forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("CALLING");
        kPressedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void keyReleased(KeyEvent e) {
        kReleasedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }

    /**
     * loops through every event in this priority queue and executes them if applicable,
     * or valid to execute
     */
    @Override
    public void keyTyped(KeyEvent e) {
        kTypedQueue.stream().filter(event -> event.restriction.isValid(e))
                .forEach(event -> event.execute(e));
    }
}
