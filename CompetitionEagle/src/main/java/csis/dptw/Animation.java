package csis.dptw;

public class Animation extends Thread{
    public final int REFRESH_RATE = 30;
    private Entity entity;
    private Game game;


    private boolean done = false;

    public Animation(Entity entity, Game game) {
        this.entity = entity;
        this.game = game;
    }



    @Override
    public void run() {
        while(!done) {

        }
    }

    public void animation(){
        
    }
}
