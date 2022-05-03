package csis.dptw.CupPong;

import csis.dptw.App;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Repainter;

public class CupAnimation extends Animation{
    
    public static final int TOP = 158;
    private boolean wentUp = false;
    public static int UP_SPEED = 10;
    public static int SIDE_SPEED = 25;

    boolean waited = false;
    public static int STAY_AT_TOP = 150;
    
    public CupAnimation(Entity entity, Game game) {
        super(entity, game);  
    }

    @Override
    public void animation(){
        try {
            sleep(Repainter.DELAY_TIME);
        } catch (InterruptedException e) {
        }

        if(!wentUp){
            while(entity.position.y > TOP){
                try {
                    sleep(Repainter.DELAY_TIME);
                } catch (InterruptedException e) {
                }
                entity.position.y -= UP_SPEED;
            }
            wentUp = true;
        }

        if(!waited) {
            try {
                sleep(STAY_AT_TOP);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            waited = true;
        }
        entity.position.x += SIDE_SPEED;
    }
 
    @Override
    public boolean done(){
        //checking if cup is off the screen
        if(entity.position.x >= App.gameDimension.width){
            done = true;
        }
        return done;
    }
}
