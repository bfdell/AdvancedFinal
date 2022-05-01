package csis.dptw.engine;

public class Animation extends Thread{
    public final int REFRESH_RATE = 30;
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

    public void animation(){
        
    }

    public boolean done() {
        return true;
    }
}
