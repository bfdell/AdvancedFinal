package csis.dptw.CupPong;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

import csis.dptw.App;
import csis.dptw.engine.Game;
import csis.dptw.engine.Map;
import java.awt.event.*;

public class CupPongMenu extends Map implements ActionListener, Runnable{
    final String CUP = "Cup";
    final String PONG = "Pong";
    private JButton play;
    private JButton mainMenu;

    public CupPongMenu(LayoutManager layout, int width, int height) {
        super(new FlowLayout(), App.gameDimension);
    }
    
    @Override
    public void run() {
        play = new JButton("Play");
        mainMenu = new JButton("Main Menu");

        add(play);
        add(mainMenu);

        play.addActionListener(this);
        mainMenu.addActionListener(this);

        play.setVisible(true);
        mainMenu.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        Font newFont = new Font("SansSerif", Font.BOLD, 30);
        g.setFont(newFont);

        g.setColor(Color.RED);
        g.drawString(CUP, (getWidth() - g.getFontMetrics().stringWidth(CUP + " " + PONG))/2, 50);

        g.setColor(Color.BLACK);
        g.drawString(" "+ PONG, (getWidth() - g.getFontMetrics().stringWidth(CUP + " " + PONG))/2 + (g.getFontMetrics().stringWidth(CUP)), 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            Game playGame = new CupPong();
            // this = new PongMap(new FlowLayout(), LANE_WIDTH, LANE_WIDTH);
        }
    }
}
