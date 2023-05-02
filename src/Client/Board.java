package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel implements MouseListener {

    public static final int BOARD_WIDTH = 3;
    private final Image imgCross;
    private final Image imgCircle;
    private final char[][] board;
    public MyTimer circleTimer;
    public MyTimer crossTimer;
    private int turn;
    private boolean isGameOver;
    public Board() {
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
        imgCross = Toolkit.getDefaultToolkit().getImage("src/cross.png");
        imgCircle = Toolkit.getDefaultToolkit().getImage("src/circle.png");
        addMouseListener(this);
        board = new char[BOARD_WIDTH][BOARD_WIDTH];
        circleTimer = new MyTimer();
        crossTimer = new MyTimer();
        circleTimer.start();
        initializeBoard();
    }

    public void paint(Graphics g) {
        g.clearRect(0,0,850,600);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Circle time: " + circleTimer.getText(), 650, 200);
        g.drawString("Cross time: " + crossTimer.getText(), 650, 400);
        drawLines(g);
    }

    public void drawLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
    }



    private void initializeBoard() {
        isGameOver = false;
        for (int i=0; i<BOARD_WIDTH; ++i){
            for(int j=0 ;j<BOARD_WIDTH; ++j) {
                board[i][j]=' ';
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        Point square = whichSquare(p);
        if(square.x == 3 || square.y == 3)
            return;

    }


    private Point whichSquare(Point p) {
        return new Point(countXorY(p.x),countXorY(p.y));
    }

    private int countXorY(int x) {
        if(200 > x)
            return 0;
        else if(x < 400)
            return 1;
        else if(x < 600)
            return 2;
        else
            return 3;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
