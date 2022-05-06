package csis.dptw.CupPong;

import javax.swing.*;
import javax.swing.text.GapContent;

import csis.dptw.*;
import csis.dptw.engine.Animation;
import csis.dptw.engine.Entity;
import csis.dptw.engine.Game;
import csis.dptw.engine.Event.EventType;

import java.awt.event.*;

import java.awt.*;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.util.LinkedList;

public class CupPong extends Game {
    public static final double POWER_PERCENT = 0.022;
    public static final Point BALL_START = new Point(App.gameDimension.width / 2, 700);
    public Ball currentBall = new Ball(this, BALL_START);
    public static final String PROMPT_MESSAGE = "Press enter to start your shot";
    public static final int DISSAPEAR_TIMER = 500;

    public static final Font LABEL_FONT = new Font("Comic Sans MS", Font.BOLD, 30);
    private JLabel shotLabel = new JLabel(PROMPT_MESSAGE);
    private JButton play = new JButton("Play");
    private JButton mainMenu = new JButton("Main Menu");

    final String CUP = "Cup";
    final String PONG = "Pong";

    DirectionMeter directionMeter;
    Powerbar powerBar;
    LinkedList<Cup> allCups = new LinkedList<Cup>();

    public CupPong() {
        super();
        run();
    }

    @Override
    public void run() {
        super.run();
        addKeyEvent(EventType.KPRESSED, this::adjustShot, 4, KeyEvent.VK_SPACE);
        // addMouseEvent(EventType.MPRESSED, this::clickInCup, 1);
        addActionEvent(this::startGame, 1, play);
        shotLabel.setFont(LABEL_FONT);
    }

    public void adjustShot(KeyEvent e) {
        if (directionMeter == null && powerBar == null) {
            directionMeter = new DirectionMeter(currentBall.position, this);
            directionMeter.start();
        } else if (directionMeter != null && powerBar == null) {
            directionMeter.isDirectonSet = true;
            directionMeter.interrupt();

            powerBar = new Powerbar(this);
            powerBar.start();
        } else if (powerBar != null) {
            powerBar.powerSpecified = true;
            powerBar.interrupt();
            throwBall();
            // if()
            // removePropertyEvent(currentChip, "landed", CHIP_LANDED_PRIORITY);
            addPropertyEvent(this::ballLanded, 1, currentBall, "landed");

            Thread removeTimer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(DISSAPEAR_TIMER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    removeEntity(directionMeter.entity);
                    removeEntity(powerBar.entity);
                    powerBar = null;
                    directionMeter = null;
                    System.out.println("removeing powerbar");
                }
            };
            removeTimer.start();
        }
    }

    public void addCups() {
        int x = 318;
        int y = 200;
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                y += 35;
                x = 342;
            } else if (i == 7) {
                y += 35;
                x = 365;
            } else if (i == 9) {
                y += 35;
                x = 389;
            }
            Cup cup = new Cup(this, new Point(x, y), "CompetitionEagle/src/main/java/csis/dptw/BeFunky-photo.png");
            addEntity(cup);
            allCups.add(cup);
            x += 48;
        }
    }

    @Override
    public void initializeMap() {
        GridBagLayout layout = new GridBagLayout();
        gamePanel = new PongMap(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        shotLabel.setFont(PongMap.TITLE_FONT);

        gbc.weightx = 4;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipady = 250;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel filler = new JLabel("");
        gamePanel.add(filler, gbc);

        gbc.ipady = 25;
        gbc.weightx = 3.75;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel rightMargin = new JLabel("");
        gamePanel.add(rightMargin, gbc);

        gbc.weightx = 3.75;
        gbc.weighty = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel leftMargin = new JLabel("");
        gamePanel.add(leftMargin, gbc);

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipady = 25;
        gbc.anchor = GridBagConstraints.CENTER;
        play.setFont(PongMap.TITLE_FONT);
        gamePanel.add(play, gbc);

        gbc.gridy = 2;
        gbc.ipady = 100;
        JLabel verticalMargin = new JLabel("");
        gamePanel.add(verticalMargin, gbc);

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipady = 25;
        mainMenu.setFont(PongMap.TITLE_FONT);
        gamePanel.add(mainMenu, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.weighty = 3;
        JLabel bottomMargin = new JLabel("");
        gamePanel.add(bottomMargin, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        play.setVisible(true);
        mainMenu.setVisible(true);
    }

    public void clickInCup(MouseEvent e) {
        Point cur = e.getPoint();
        for (Entity cup : gamePanel.entities) {
            if (cup instanceof Cup) {
                if (cup != currentBall && ((Cup) cup).colidesWith(cur)) {
                    System.out.println("col·li·sion");
                    moveCup((Cup) cup);
                }
            }
        }
    }

    public void moveCup(Cup cup) {
        CupAnimation cupAnimation = new CupAnimation(cup, this);
        cupAnimation.start();

        // Thread cancelAnimation = new Thread(){
        // CupAnimation animation = cupAnimation;
        // @Override
        // public void run(){
        // while(!animation.done()) {
        // try {
        // sleep(1);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        // animation.interrupt();
        // animation = null;
        // }
        // };
        // cancelAnimation.start();

    }

    public void startGame(ActionEvent e) {
        ((PongMap) gamePanel).playing = true;
        //////////////// REMOVE ALL BUTTONS AND SHIT
        ((PongMap) gamePanel).remove(play);
        ((PongMap) gamePanel).remove(mainMenu);
        addCups();
        addEntity(currentBall);
        gamePanel.add(shotLabel);
        gamePanel.requestFocus();
    }

    public void throwBall() {
        double dx = (150 * (POWER_PERCENT))
                * (directionMeter.meter.endPoint.getX() - directionMeter.meter.position.x);
        double dy = (150 * (POWER_PERCENT))
                * (directionMeter.meter.endPoint.getY() - directionMeter.meter.position.y);

        double totalDy = (powerBar.bar.currentHeight * (3.666666));

        double slope = dy / dx;
        // Point2D landingPoint = new Point2D.Double(dx + ball.position.x,
        // ball.position.y + dy);
        Point2D landingPoint = new Point2D.Double(dx + currentBall.position.x, currentBall.position.y - totalDy);

        BallAnimation throwBall = new BallAnimation(currentBall, this, landingPoint, slope, totalDy);
        throwBall.start();
    }

    public void ballLanded(PropertyChangeEvent e) {
        removeEntity(currentBall);
        handleCupCollisions();

    }

    public void handleCupCollisions() {
        for (Cup cup : allCups) {
            if (cup.colidesWith(currentBall.getPosition())) {
                moveCup(cup);
            }
        }
    }
}
