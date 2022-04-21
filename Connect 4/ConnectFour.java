import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class ConnectFour extends MouseAdapter implements Runnable {
    JPanel mainPanel;
    @Override
    public void mouseClicked(MouseEvent e) {

        super.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        super.mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        super.mouseReleased(e);
    }

    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Cup Pong!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 800));
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
        frame.setResizable(false);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new ConnectFour());
    }
}
