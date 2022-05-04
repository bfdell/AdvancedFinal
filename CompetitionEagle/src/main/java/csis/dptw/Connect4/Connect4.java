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
    public static final int WIN_AMOUNT = 3;

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
    int currentChipCol;
    int currentDstRow;

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
                if (currentChipCol > -1 && currentChipCol < 6) {
                    currentChip.position.x = circlePoints[0][++currentChipCol].x;
                    System.out.println(currentChipCol);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("LEFT");
                if (currentChipCol > 0 && currentChipCol < 7) {
                    currentChip.position.x = circlePoints[0][--currentChipCol].x;
                    System.out.println(currentChipCol);
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
            currentDstRow = -1;
            while (currentDstRow < NUM_ROWS - 1 && !spaces[currentDstRow + 1][currentChipCol]) {
                System.out.println(currentDstRow);
                currentDstRow++;
            }
            if (currentDstRow != -1) {
                spaces[currentDstRow][currentChipCol] = true;
                takenSpots[currentDstRow][currentChipCol] = currentPlayer;
                Point dest = circlePoints[currentDstRow][currentChipCol];
                System.out.println("=--------------------" + currentDstRow + " " + currentChipCol);
                currentChip.moving = true;
                if (currentChip != null) {
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
        System.out.println("WIN STATUS IS HERE:_____________________->-> " + gameOver());
        previousChip = currentChip;
        nextTurn();
        addNewChip();
        System.out.println("returned");
    }

    public void addNewChip() {
        currentChip = new ConnectChip(this, (Point) startingChipLocation.clone(),
                PLAYERS[round % 2]);
        addEntity(currentChip);
        currentChipCol = 3;
    }

    private void requestFocus(ActionEvent e) {
        gamePanel.requestFocus();
    }

    public boolean matrixFilled() {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[row].length; col++) {
                if (!spaces[row][col]) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean gameOver() {
        if (matrixFilled()) {
            return true;
        }
        if (fourInARow(currentDstRow, currentChipCol)) {
            return true;
        }
        return false;

    }

    public boolean fourInARow(int row, int col) {
        //horizontal left
        //horizontal right
        //vertical left
        //vertical right
        //diagonal left
        //diagonal right
        // return checkHorizontal(row, col) || checkVertical(row, col) || checkRightDiagonal(row, col)
        //         || checkLeftDiagonal(row, col);
        return checkHorizontal(row, col) || checkVertical(row, col);
    }

    public boolean checkHorizontal(int row, int col) {
        // for (int row1 = 0; row1 < spaces.length; row1++) {

        //     for (int col1 = 0; col1 < spaces[row1].length - 3; col1++) {
        //         int current = takenSpots[row1][col1].PLAYER_NUM;
        //         if (current == takenSpots[row1][col1 + 1].PLAYER_NUM && current == takenSpots[row1][col1 + 2].PLAYER_NUM
        //                 && current == takenSpots[row1][col1 + 3].PLAYER_NUM) {
        //             return true;
        //         }
        //     }

        // }
        // return false;

        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        int leftCol = col + 1;
        while (col + WIN_AMOUNT < NUM_COLS && leftCol < col + WIN_AMOUNT) {
            if (takenSpots[row][leftCol] == null || takenSpots[row][leftCol].PLAYER_NUM != currentSpotPlayer) {
                return false;
            }
            leftCol++;
        }

        currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        int rightCol = col - 1;
        while (col - WIN_AMOUNT > 0 && rightCol > col - WIN_AMOUNT) {
            if (takenSpots[row][rightCol] == null || takenSpots[row][rightCol].PLAYER_NUM != currentSpotPlayer) {
                return false;
            }
            rightCol--;
        }
        return true;
    }

    public boolean checkVertical(int row, int col) {
        // for (row = 0; row < spaces.length - 3; row++) {
        //     for (col = 0; col < spaces[row].length; col++) {
        //         int current = takenSpots[row][col].PLAYER_NUM;
        //         if (current == takenSpots[row + 1][col].PLAYER_NUM && current == takenSpots[row + 2][col].PLAYER_NUM
        //                 && current == takenSpots[row + 3][col].PLAYER_NUM) {
        //             return true;
        //         }
        //     }

        // }
        // return false;

        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        int topRow = row + 1;
        while (row + WIN_AMOUNT < NUM_COLS && topRow < row + WIN_AMOUNT) {
            if (takenSpots[topRow][col] == null || takenSpots[topRow][col].PLAYER_NUM != currentSpotPlayer) {
                return false;
            }
            topRow++;
        }

        currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        int bottomRow = row - 1;
        while (row - WIN_AMOUNT > 0 && bottomRow > row - WIN_AMOUNT) {
            if (takenSpots[bottomRow][col] == null || takenSpots[bottomRow][col].PLAYER_NUM != currentSpotPlayer) {
                return false;
            }
            bottomRow--;
        }
        return true;
    }

    public boolean checkRightDiagonal(int row, int col) {
        // for (row = 0; row < spaces.length - 3; row++) {
        //     for (col = 0; col < spaces[row].length - 3; col++) {
        //         int current = takenSpots[row][col].PLAYER_NUM;
        //         if (current == takenSpots[row + 1][col + 1].PLAYER_NUM
        //                 && current == takenSpots[row + 2][col + 2].PLAYER_NUM
        //                 && current == takenSpots[row + 3][col + 3].PLAYER_NUM) {
        //             return true;
        //         }
        //     }
        // }
        // return false;
        return true;
    }

    public boolean checkLeftDiagonal(int row, int col) {
        // for (int row1 = 0; row1 < spaces.length - 3; row1++) {
        //     for (int col1 = 3; col1 < spaces[row1].length; col1++) {
        //         int current = takenSpots[row1][col1].PLAYER_NUM;
        //         if (current == takenSpots[row1 + 1][col1 - 1].PLAYER_NUM
        //                 && current == takenSpots[row1 + 2][col1 - 2].PLAYER_NUM
        //                 && current == takenSpots[row1 + 3][col1 - 3].PLAYER_NUM) {
        //             return true;
        //         }
        //     }
        // }
        // return false;
        return true;
    }
}
