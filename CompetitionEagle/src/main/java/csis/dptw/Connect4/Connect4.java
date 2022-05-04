package csis.dptw.Connect4;

import csis.dptw.engine.*;
import csis.dptw.engine.Event.EventType;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brian Dell
 * @version Spring 2022
 */
public class Connect4 extends Game {
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
    public static final int WIN_AMOUNT = 4;
    public static final String RESTART = "Restart Game";
    public static final String PLAY_AGAIN = "Play Again";
    private static final int CHIP_LANDED_PRIORITY = 1;
    
    private JButton restartButton = new JButton(RESTART);
    private JButton startButton = new JButton("START");
    private JLabel statusLabel = new JLabel();
    private JPanel titlePanel =  new JPanel();

    private final ConnectPlayer[] PLAYERS = new ConnectPlayer[2];
    private boolean gameStarted = false;
    private int currentRound = 0;
    private ConnectPlayer currentPlayer;
    private ConnectChip currentChip;
    private ConnectChip previousChip;
    private int currentChipCol;
    private int currentDstRow;
    public Point startingChipLocation;
    Point[][] circlePoints = new Point[NUM_ROWS][NUM_COLS];
    ConnectPlayer[][] takenSpots = new ConnectPlayer[NUM_ROWS][NUM_COLS];

   

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

        addActionEvent(this::startGame, 1, startButton);
        addActionEvent(this::requestFocus, 3);
        addActionEvent(this::restartGame, 2, restartButton);
        addKeyEvent(EventType.KPRESSED, this::shiftChip, 1,
                Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));
        addKeyEvent(EventType.KPRESSED, this::dropChip, 2, KeyEvent.VK_ENTER);
    }

    public void shiftChip(KeyEvent e) {
        if (!currentChip.moving) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (currentChipCol > -1 && currentChipCol < 6) {
                    currentChip.position.x = circlePoints[0][++currentChipCol].x;
                    System.out.println(currentChipCol);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
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

        GridBagLayout layout = new GridBagLayout();
        titlePanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        JLabel one = new JLabel(PLAYER1);
        Font playerOneFont = TEXT_FONT;
        one.setFont(playerOneFont);
        one.setForeground(PLAYERS[1].DARKER_COLOR);
        titlePanel.add(one, gbc);

        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        Font titleFont = TITLE_FONT;
        JLabel two = new JLabel(CONNECT4);
        two.setFont(titleFont);
        two.setForeground(TITLE_COLOR);
        titlePanel.add(two, gbc);

        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        Font playerTwoFont = TEXT_FONT;
        JLabel three = new JLabel(PLAYER2);
        three.setFont(playerTwoFont);
        three.setForeground(PLAYERS[0].DARKER_COLOR);
        titlePanel.add(three, gbc);

        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.BELOW_BASELINE;
        titlePanel.add(startButton, gbc);

        gamePanel.add(titlePanel, BorderLayout.NORTH);
        gbc.gridy = 2;
        titlePanel.add(restartButton, gbc);
        restartButton.setVisible(false);

        statusLabel.setFont(TEXT_FONT);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (gameStarted) {
            super.keyPressed(event);
        }
    }

    public void startGame(ActionEvent e) {
        gameStarted = true;
        nextTurn();
        addNewChip();
        System.out.println("GAME STARTED");

        GridBagLayout layout = (GridBagLayout) titlePanel.getLayout();
        GridBagConstraints gbc = layout.getConstraints(startButton);

        titlePanel.remove(startButton);
        titlePanel.add(statusLabel, gbc);
        titlePanel.revalidate();
        titlePanel.validate();
        restartButton.setVisible(true);
    }

    public void dropChip(KeyEvent e) {
        if (!currentChip.moving) {
            currentDstRow = -1;
            while (currentDstRow < NUM_ROWS - 1 && takenSpots[currentDstRow + 1][currentChipCol] == null) {
                System.out.println(currentDstRow);
                currentDstRow++;
            }
            if (currentDstRow != -1) {
                takenSpots[currentDstRow][currentChipCol] = currentPlayer;
                Point dest = circlePoints[currentDstRow][currentChipCol];
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
        currentRound = 0;
        takenSpots = new ConnectPlayer[NUM_ROWS][NUM_COLS];
        nextTurn();
        addNewChip();
    }

    public void nextTurn() {
        currentRound++;
        currentPlayer = PLAYERS[currentRound % 2];
        statusLabel.setForeground(currentPlayer.DARKER_COLOR);
        statusLabel.setText(currentPlayer.NAME + TURN);
    }

    public void chipFell(PropertyChangeEvent evt) {
        System.out.println("IS GAME OVER: " + gameOver());
        previousChip = currentChip;
        nextTurn();
        addNewChip();
    }

    public void addNewChip() {
        currentChip = new ConnectChip(this, (Point) startingChipLocation.clone(),
                PLAYERS[currentRound % 2]);
        addEntity(currentChip);
        currentChipCol = 3;
    }

    private void requestFocus(ActionEvent e) {
        gamePanel.requestFocus();
    }

    public void playAgain() {
        restartButton.setText(PLAY_AGAIN);
    }

    public boolean matrixFilled() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (takenSpots[row][col] == null) {
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
        // System.out.println(BL_TR_Diagonal(row, col));
        // System.out.println(TL_BR_Diagonal(row, col));
        System.out.println(checkVertical(row, col));
        // System.out.println(checkHorizontal(row, col));
        // return tr != null;
        return true;
    }

    private LinkedList<Point> BL_TR_Diagonal(int row, int col) {
        //INITIALIZES CURRENTSPOTPLAYER, AND WINLIST WITH CHIP THAT JUST DROPPED
        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        LinkedList<Point> winList = new LinkedList<Point>();
        winList.add(circlePoints[row][col]);

        ////////////////////////////////////
        //DEBUG LIST 
        LinkedList<String> debugList = new LinkedList<String>();
        debugList.add(col + ", " + row + "|");
        //////////////////////////////////////

        //CHECKS THAT MAKE SURE A CONNECTION OF 4 IS EVEN POSSIBLE
        if ((row <= 1 && col <= 1) || (row >= NUM_ROWS - 1 && col >= NUM_COLS - 1)) {
            return winList;
        }

        //ADDS ALL TOP RIGHT DIAGONALS THAT MATCH PLAYER TO END OF LINKED LIST
        int topRow = row - 1;
        int rightCol = col + 1;
        while (topRow > -1 && rightCol < NUM_COLS) {
            if (takenSpots[topRow][rightCol] == null || takenSpots[topRow][rightCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.add(circlePoints[topRow][rightCol]);
                ///////////////////////////////////
                debugList.add(rightCol + ", " + topRow + "|");
                ///////////////////////////////////
                topRow--;
                rightCol++;
            }
        }

        //ADDS ALL BOTTOM LEFT DIAGONALS THAT MATCH PLAYER TO BEGINNING OF LINKED LIST
        int bottomRow = row + 1;
        int leftCol = col - 1;
        while (bottomRow < NUM_ROWS && leftCol > -1) {
            if (takenSpots[bottomRow][leftCol] == null
                    || takenSpots[bottomRow][leftCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.push(circlePoints[bottomRow][leftCol]);
                ///////////////////////////////////
                debugList.push(leftCol + ", " + bottomRow + "|");
                //////////////////////////////////
                bottomRow++;
                leftCol--;
            }
        }

        //Prints out debug list of connections in x coordinate order
        System.out.println(debugList.toString());
        return winList;
    }

    private LinkedList<Point> TL_BR_Diagonal(int row, int col) {
        //INITIALIZES CURRENTSPOTPLAYER, AND WINLIST WITH CHIP THAT JUST DROPPED
        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        LinkedList<Point> winList = new LinkedList<Point>();
        winList.add(circlePoints[row][col]);

        ////////////////////////////////////
        //DEBUG LIST 
        LinkedList<String> debugList = new LinkedList<String>();
        debugList.add(col + ", " + row + "|");
        //////////////////////////////////////

        //CHECKS THAT MAKE SURE A CONNECTION OF 4 IS EVEN POSSIBLE
        if ((row <= 1 && col >= NUM_COLS - 1) || (row >= NUM_ROWS - 1 && col <= 1)) {
            return winList;
        }

        //ADDS ALL TOP LEFT DIAGONALS THAT MATCH PLAYER TO BEGINNING OF LINKED LIST
        int topRow = row - 1;
        int leftCol = col - 1;
        while (topRow > -1 && leftCol > -1) {
            if (takenSpots[topRow][leftCol] == null || takenSpots[topRow][leftCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.push(circlePoints[topRow][leftCol]);
                ///////////////////////////////////
                debugList.push(leftCol + ", " + topRow + "|");
                ///////////////////////////////////
                topRow--;
                leftCol--;
            }
        }

        //ADDS ALL BOTTOM LEFT DIAGONALS THAT MATCH PLAYER TO END OF LINKED LIST
        int bottomRow = row + 1;
        int rightCol = col + 1;
        while (bottomRow < NUM_ROWS && rightCol < NUM_COLS) {
            if (takenSpots[bottomRow][rightCol] == null
                    || takenSpots[bottomRow][rightCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.add(circlePoints[bottomRow][rightCol]);
                ///////////////////////////////////
                debugList.add(rightCol + ", " + bottomRow + "|");
                //////////////////////////////////
                bottomRow++;
                rightCol++;
            }
        }

        //Prints out debug list of connections in x coordinate order
        System.out.println(debugList.toString());
        return winList;
    }

    private LinkedList<Point> checkVertical(int row, int col) {
        //INITIALIZES CURRENTSPOTPLAYER, AND WINLIST WITH CHIP THAT JUST DROPPED
        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        LinkedList<Point> winList = new LinkedList<Point>();
        winList.add(circlePoints[row][col]);

        ////////////////////////////////////
        //DEBUG LIST 
        LinkedList<String> debugList = new LinkedList<String>();
        debugList.add(col + ", " + row + "|");
        //////////////////////////////////////

        //ADDS ALL TOP CHIPS THAT MATCH PLAYER TO BEGINNING OF LINKED LIST
        // int topRow = row - 1;
        // while (topRow > -1) {
        //     if (takenSpots[topRow][col] == null || takenSpots[topRow][col].PLAYER_NUM != currentSpotPlayer) {
        //         break;
        //     } else {
        //         winList.push(circlePoints[topRow][col]);
        //         ///////////////////////////////////
        //         debugList.push(col + ", " + topRow + "|");
        //         ///////////////////////////////////
        //         topRow--;
        //     }
        // }

        //NO NEED TO CHECK TOP BECAUSE ITS IMPOSSIBLE
        //ADDS ALL BOTTOM CHIPS THAT MATCH PLAYER TO END OF LINKED LIST
        int bottomRow = row + 1;
        while (bottomRow < NUM_ROWS) {
            if (takenSpots[bottomRow][col] == null
                    || takenSpots[bottomRow][col].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.add(circlePoints[bottomRow][col]);
                ///////////////////////////////////
                debugList.add(col + ", " + bottomRow + "|");
                //////////////////////////////////
                bottomRow++;
            }
        }

        //Prints out debug list of connections in top to bottom coordinate order
        System.out.println(debugList.toString());
        return winList;
    }

    private LinkedList<Point> checkHorizontal(int row, int col) {
        //INITIALIZES CURRENTSPOTPLAYER, AND WINLIST WITH CHIP THAT JUST DROPPED
        int currentSpotPlayer = takenSpots[row][col].PLAYER_NUM;
        LinkedList<Point> winList = new LinkedList<Point>();
        winList.add(circlePoints[row][col]);

        ////////////////////////////////////
        //DEBUG LIST 
        LinkedList<String> debugList = new LinkedList<String>();
        debugList.add(col + ", " + row + "|");
        //////////////////////////////////////

        //CHECKS THAT MAKE SURE A CONNECTION OF 4 IS EVEN POSSIBLE
        if ((row <= 1 && col >= NUM_COLS - 1) || (row >= NUM_ROWS - 1 && col <= 1)) {
            return winList;
        }

        //ADDS ALL LEFT CHIPS MATCH PLAYER TO BEGINNING OF LINKED LIST
        int leftCol = col - 1;
        while (leftCol > -1) {
            if (takenSpots[row][leftCol] == null || takenSpots[row][leftCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.push(circlePoints[row][leftCol]);
                ///////////////////////////////////
                debugList.push(leftCol + ", " + row + "|");
                ///////////////////////////////////
                leftCol--;
            }
        }

        //ADDS ALL RIGHT CHIPS THAT MATCH PLAYER TO END OF LINKED LIST
        int rightCol = col + 1;
        while (rightCol < NUM_COLS) {
            if (takenSpots[row][rightCol] == null
                    || takenSpots[row][rightCol].PLAYER_NUM != currentSpotPlayer) {
                break;
            } else {
                winList.add(circlePoints[row][rightCol]);
                ///////////////////////////////////
                debugList.add(rightCol + ", " + row + "|");
                //////////////////////////////////
                rightCol++;
            }
        }

        //Prints out debug list of connections in x coordinate order
        System.out.println(debugList.toString());
        return winList;
    }
}
