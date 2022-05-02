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

        gamePanel.repaint();
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new BorderLayout(), circlePoints, NUM_ROWS, NUM_COLS);

        // top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        JPanel top = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        top.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel one = new JLabel("Player1");
        Font playerOneFont = new Font("Arial", Font.BOLD, 30);
        one.setFont(playerOneFont);
        one.setForeground(Color.YELLOW);
        top.add(one, gbc);
        layout.addLayoutComponent(one, gbc);
        // one.setAlignmentX(.0f);
        String titleLabel = "Connect4!!";
        Font titleFont = new Font("Monospaced", Font.BOLD, 50);
        JLabel two = new JLabel(titleLabel);
        two.setFont(titleFont);
        two.setForeground(Color.RED);
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty= 0.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        top.add(two, gbc);

        // two.setAlignmentX(0.5f);
        Font playerTwoFont = new Font("Arial", Font.BOLD, 30);
        JLabel three = new JLabel("Player2");
        three.setFont(playerTwoFont);
        three.setForeground(Color.BLUE);
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        top.add(three, gbc);
        // three.setAlignmentX(1.0f);
        // three.setHorizontalTextPosition( SwingConstants.LEFT);
        
        
        // top.add(three);

        gamePanel.add(top, BorderLayout.NORTH);
    }

    public void myFirst(Connect4 game) {
        System.out.println(game.first++ + ",");
    }

    public void startGame(Connect4 game, ActionEvent e) {
       gameStarted = true;
    }

    public void nextTurn(Connect4 game, KeyEvent e) {
        System.out.println(e.getKeyCode());
        game.round++;
        System.out.println("ACtivated");
    }
}
