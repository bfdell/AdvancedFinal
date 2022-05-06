package csis.dptw.engine;

/**
 * Animates the entities within the games
 * @author Spencer Caramanna, Brian Dell, Madelyn Papa, David Tang, Jaclyn Wirth
 * @version Spring 2022
 */
public abstract class Animation extends Thread {
    public Entity entity;
    protected Game game;

    protected boolean done = false;

    /**
     * Initializes entity and game
     * 
     * @param entity the entity that is being animated
     * @param game   that is currently being played
     */
    public Animation(Entity entity, Game game) {
        this.entity = entity;
        this.game = game;
    }

    /**
     * While the condition for the animation done is not fulfilled, animate the
     * entity
     */
    @Override
    public void run() {
        while (!done()) {
            animation();
        }
    }

    /**
     * Modifies Entity's values for animation
     */
    public abstract void animation();

    /**
     * @return if animation is done
     */
    public boolean done() {
        return true;
    }

    /**
     * @return entity that is being animated
     */
    public Entity getEntity() {
        return entity;
    }
}
