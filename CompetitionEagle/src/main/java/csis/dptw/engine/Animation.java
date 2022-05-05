package csis.dptw.engine;

public abstract class Animation extends Thread{
    protected Entity entity;
    protected Game game;


    protected boolean done = false;

    public Animation(Entity entity, Game game) {
        this.entity = entity;
        this.game = game;
    }



    @Override
    public void run() {
        while(!done()) {
            animation();
        }
    }

    public abstract void animation();
    
    public boolean done() {
        return true;
    }
}
