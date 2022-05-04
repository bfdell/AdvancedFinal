package csis.dptw.Connect4;

import csis.dptw.engine.*;
import csis.dptw.engine.Event.EventType;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Connect4 extends Game {
    int first = 0;
    ////////////////////////////////////////////////////////////////
    public static final Font TITLE_FONT = new Font("Phosphate", Font.BOLD, 50);
    public static final Font TEXT_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Color TITLE_COLOR = Color.ORANGE;
    public static final String CONNECT4 = "Connect 4!!";
    public static final String PLAYER1 = "Player One";
    public static final String PLAYER2 = "Player Two";
    public static final String TURN = "'s Turn";
    public static final String WINS = "Wins!";
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLS = 7;

    public final int CHIP_PADDING = 12;
    public final int CHIP_RADIUS = 40;
    public final int RING_WIDTH = 8;
    public Point startingChipLocation;
    boolean[][] spaces = new boolean[NUM_ROWS][NUM_COLS];
    Point[][] circlePoints = new Point[NUM_ROWS][NUM_COLS];
    ConnectPlayer[][] takenSpots = new ConnectPlayer[NUM_ROWS][NUM_COLS];

    private JLabel statusLabel;

    private boolean gameStarted = false;
    int round = 0;
    JPanel top;

    JButton restart = new JButton("Restart Game");

    JButton start;
    final ConnectPlayer[] PLAYERS = new ConnectPlayer[2];
    ConnectPlayer currentPlayer;
    ConnectChip currentChip;
    ConnectChip previousChip;
    int currentChipX;

    private static final int CHIP_LANDED_PRIORITY = 1;
    public Connect4() {
        super();
        PLAYERS[1] = new ConnectPlayer(1, Color.RED, PLAYER1);
        PLAYERS[0] = new ConnectPlayer(2, Color.YELLOW, PLAYER2);
        run();
    }

    @Override
    public void run() {
        super.run();
        startingChipLocation = new Point(circlePoints[0][3].x,
                circlePoints[0][3].y - (RING_WIDTH / 2 + (CHIP_PADDING * 2) + CHIP_RADIUS * 2));
        addKeyEvent(EventType.KPRESSED, this::dropChip, 2, KeyEvent.VK_ENTER);


        addActionEvent(this::startGame, 1, start);
        addActionEvent(this::requestFocus, 3);
        addActionEvent(this::restartGame, 2, restart);
        addKeyEvent(EventType.KPRESSED, this::shiftChip, 1,
                Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));
    }

    public void shiftChip(KeyEvent e) {
        if (!currentChip.moving) {
            System.out.println("MOVED");
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("RIGHT");
                // anel.removeEntity(currentChip);
                if (currentChipX > -1 && currentChipX < 6) {
                    currentChip.position.x = circlePoints[0][++currentChipX].x;
                    System.out.println(currentChipX);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("LEFT");
                if (currentChipX > 0 && currentChipX < 7) {
                    currentChip.position.x = circlePoints[0][--currentChipX].x;
                    System.out.println(currentChipX);
                }
            }
        }
    }

    @Override
    public void initializeMap() {
        gamePanel = new ConnectBoard(new BorderLayout(), circlePoints, CHIP_RADIUS, CHIP_PADDING, RING_WIDTH);

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

        JLabel one = new JLabel(PLAYER1);
        Font playerOneFont = TEXT_FONT;
        one.setFont(playerOneFont);
        one.setForeground(PLAYERS[1].DARKER_COLOR);
        top.add(one, gbc);

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        Font titleFont = TITLE_FONT;
        JLabel two = new JLabel(CONNECT4);
        two.setFont(titleFont);
        two.setForeground(TITLE_COLOR);
        top.add(two, gbc);

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.weighty= 0.5;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        Font playerTwoFont = TEXT_FONT;
        JLabel three = new JLabel(PLAYER2);
        three.setFont(playerTwoFont);
        three.setForeground(PLAYERS[0].DARKER_COLOR);
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

        ////////////////////////////////////////////////////////////////
        // JLabel winLabel = new JLabel();
        statusLabel = new JLabel();
        statusLabel.setFont(TEXT_FONT);
    }

    public void startGame(ActionEvent e) {
        gameStarted = true;
        nextTurn();
        addNewChip();
        System.out.println("GAME STARTED");

        GridBagLayout layout = (GridBagLayout) top.getLayout();
        GridBagConstraints gbc = layout.getConstraints(start);

        top.remove(start);
        top.add(statusLabel, gbc);
        top.revalidate();
        top.validate();
        restart.setVisible(true);
    }

    public void dropChip(KeyEvent e) {
        if (!currentChip.moving) {
            int col = currentChipX;

            int row = -1;
            while (row < NUM_ROWS - 1 && !spaces[row + 1][col]) {
                System.out.println(row);
                row++;
            }
            if (row != -1) {
                spaces[row][col] = true;
                Point dest = circlePoints[row][col];
                currentChip.moving = true;
                if(currentChip != null) {
                    removePropertyEvent(currentChip, "landed", CHIP_LANDED_PRIORITY);
                }
                addPropertyEvent(this::chipFell, 1, currentChip, "landed");
                ChipFalling dropChip = new ChipFalling(currentChip, this, dest);
                dropChip.start();
            }
        }
    }

    public void restartGame(ActionEvent e) {
        gamePanel.entities.clear();
        round = 0;
        spaces = new boolean[NUM_ROWS][NUM_COLS];
        nextTurn();
        addNewChip();
    }

    
    public void nextTurn() {
        round++;
        currentPlayer = PLAYERS[round % 2];
        statusLabel.setForeground(currentPlayer.DARKER_COLOR);
        statusLabel.setText(currentPlayer.NAME + TURN);
    }
    
    public void chipFell(PropertyChangeEvent evt) {
        previousChip = currentChip;
        nextTurn();
        addNewChip();
        System.out.println("returned");
    }
    
    public void addNewChip() {
        currentChip = new ConnectChip(this, (Point) startingChipLocation.clone(),
        PLAYERS[round % 2]);
        addEntity(currentChip);
        currentChipX = 3;
    }
    private void requestFocus(ActionEvent e) {
        gamePanel.requestFocus();
    }

    public boolean matrixFilled(){
        for(int row = 0; row < spaces.length; row++) {
            for(int col = 0; col<spaces[row].length;col++){
              if(!spaces[row][col]) {
                  return false;
              } 
            }

        }
        return true;
    }

    public boolean gameOver(){
        if(matrixFilled()){
            return true;
        }

    }

    public boolean fourInARow(){
        //horizontal left
        //horizontal right
        //vertical left
        //vertical right
        //diagonal left
        //diagonal right
    }

    public boolean checkHorizontal(){
        for(int row = 0; row < spaces.length; row++) {
            for(int col = 0; col<spaces[row].length-3;col++){
                int current = takenSpots[row][col].PLAYER_NUM;
              if(current == takenSpots[row][col+1].PLAYER_NUM && current == takenSpots[row][col+2].PLAYER_NUM && current == takenSpots[row][col+3].PLAYER_NUM) {
                  return true;
              } 
            }

        }
        return false;
    }

    public boolean checkVertical(){
        for(int row = 0; row < spaces.length-3; row++) {
            for(int col = 0; col<spaces[row].length;col++){
                int current = takenSpots[row][col].PLAYER_NUM;
              if(current == takenSpots[row+1][col].PLAYER_NUM && current == takenSpots[row+2][col].PLAYER_NUM && current == takenSpots[row+3][col].PLAYER_NUM) {
                  return true;
              } 
            }

        }
        return false;
    }

    public boolean checkRightDiagonal(){
        for(int row = 0; row < spaces.length-3; row++) {
            for(int col = 0; col<spaces[row].length-3;col++){
                int current = takenSpots[row][col].PLAYER_NUM;
              if(current == takenSpots[row+1][col+1].PLAYER_NUM && current == takenSpots[row+2][col+2].PLAYER_NUM && current == takenSpots[row+3][col+3].PLAYER_NUM) {
                  return true;
              } 
            }

        }
        return false;
    }

    
}
