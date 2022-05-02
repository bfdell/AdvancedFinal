package csis.dptw.CupPong;

import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;

public class CupAnimation extends Animation{
    
    private boolean wentUp = false;
    
    public CupAnimation(Entity entity, Game game) {
        super(entity, game);
    }

    @Override
    public void animation(){
        if(!wentUp){
            int add = 5;
            int total = 0;
            while(total < 35){
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                }
                entity.position.y += add;
                total+= add;
            }
            wentUp = true;
        }
        entity.position.x++;
        
    }
 
    @Override
    public boolean done(){
        //checking if cup is off the screen
        if(entity.position.x >= 850){
            done = true;
        }
        return done;
    }
}
