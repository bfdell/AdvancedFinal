package csis.dptw.Connect4;

import csis.dptw.engine.*;
import csis.dptw.engine.Event.EventType;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Connect4 extends Game {
    public static final String CONNECT4 = "Connect 4!!";
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLS = 7;
    boolean[][] spaces = new boolean[6][7];
    Point[][] circlePoints = new Point[6][7];
    int first = 1;
    // final Player PLAYER1;
    // final Player PLAYER2;

    private boolean gameStarted = false;
    int round = 1;
    JPanel top;

    JButton restart = new JButton("Restart Game");

JButton start;
    final ConnectPlayer[] PLAYERS = new ConnectPlayer[2];

    public Connect4() {
        super();
        PLAYERS[0] = new ConnectPlayer(1, Color.RED);
        PLAYERS[1] = new ConnectPlayer(2, Color.YELLOW);
        run();
    }

    @Override
    public void run() {
        super.run();

        addMouseEvent(EventType.MRELEASED, (e) -> myFirst(this), 2);
        gamePanel.addEntity(new ConnectChip(this, new Point(175, 275), Color.RED, PLAYERS[round % 2]));
        // gamePanel.addEntity(new ConnectChip(this, new Point(120, 51), Color.ORANGE));
        // gamePanel.addEntity(new ConnectChip(this, new Point(51, 120), Color.ORANGE));
        addKeyEvent(EventType.KPRESSED, e -> nextTurn(this, (KeyEvent) e), 1, KeyEvent.VK_ENTER);
        addActionEvent(e -> startGame(this, (ActionEvent) e), 1, start);
        addActionEvent(e -> requestFocus(this, (ActionEvent) e), 3);
        addActionEvent(e -> restartGame(this, (ActionEvent) e), 2, restart);

        gamePanel.repaint();
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new BorderLayout(), circlePoints, NUM_ROWS, NUM_COLS);

         top = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        top.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.ipadx = 5;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        JLabel one = new JLabel("Player1");
        Font playerOneFont = new Font("Arial", Font.BOLD, 30);
        one.setFont(playerOneFont);
        one.setForeground(Color.YELLOW);
        top.add(one, gbc);
     

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        String titleLabel = "Connect4!!";
        Font titleFont = new Font("Arial", Font.BOLD, 50);
        JLabel two = new JLabel(titleLabel);
        two.setFont(titleFont);
        two.setForeground(Color.RED);
        top.add(two, gbc);

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.weighty= 0.5;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        Font playerTwoFont = new Font("Arial", Font.BOLD, 30);
        JLabel three = new JLabel("Player2");
        three.setFont(playerTwoFont);
        three.setForeground(Color.BLUE);
        top.add(three, gbc);
    
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.BELOW_BASELINE;
        start = new JButton("START");
        top.add(start, gbc);

        gamePanel.add(top, BorderLayout.NORTH);
        gbc.gridy = 2;
       top.add(restart, gbc);
       restart.setVisible(false);
    }

    public void myFirst(Connect4 game) {
        System.out.println(game.first++ + ",");
    }

    public void startGame(Connect4 game, ActionEvent e) {
       gameStarted = true;
       System.out.println("GAME STARTED");
       
       GridBagLayout layout  = (GridBagLayout) top.getLayout();
       GridBagConstraints gbc = layout.getConstraints(start);

    
       top.remove(start);
       top.add(new JLabel("I WANNA DIE"), gbc);
       top.revalidate();
       top.validate();
       restart.setVisible(true);
    }


    public void restartGame(Connect4 game, ActionEvent e) {
        game.round = 1;
        game.gamePanel.entities.clear();
    }
    public void requestFocus(Connect4 game, ActionEvent e) {
        game.gamePanel.requestFocus();
    }

    public void nextTurn(Connect4 game, KeyEvent e) {
        System.out.println(e.getKeyCode());
        round++;
        System.out.println("Activated");
    }
}
