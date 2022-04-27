package csis.dptw.CupPong;

import javax.swing.*;

import csis.dptw.*;

import java.awt.event.*;

import java.awt.*;
import java.awt.geom.*;

public class CupPong extends Game {

    public CupPong() {
        super();//FIX TIHIS LINE BECCUSE GAMAE PANEL IS BEING INITIOALIZED IN ANONYMOUS INNER CLASS
        run();
    }

     public final int LANE_WIDTH = 500;
    // ///////////////////////
    // public double boardYPercent = .3;
    // public double boardXPercent = .3;
    // public double triangleYPercent = .35;
    // public double triangleXPercent = .15;

    @Override
    public void run() {
        gamePanel = new PongMap(new FlowLayout());
        
        gamePanel.setPreferredSize(new Dimension(LANE_WIDTH, LANE_WIDTH));
        addCups();
    }

    public void addCups(){
        int x = 155;
        int y = 100;
        for(int i = 0; i < 10; i++){
           if(i == 4){
               y += 35;
               x = 175;
           }else if(i == 7){
            y += 35;
            x = 195;
           }else if(i == 9){
               y += 35;
               x = 215;
           }

            gamePanel.addEntity(new Cup(this, new Point(x, y), "CompetitionEagle/src/main/java/csis/dptw/BeFunky-photo.png"));
        
            x += 43;
        }
    }

   

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new CupPong());
    }
}
