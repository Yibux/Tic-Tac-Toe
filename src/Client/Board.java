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
    private char turn;
    private char isGameOver;
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
        turn = 'O';
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
        drawImages(g);
    }

    public void drawLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
    }

    public void drawImages(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == 'X')
                    g.drawImage(imgCross, j*200+15, i*200+15, this);
                else if(board[i][j] == 'O')
                    g.drawImage(imgCircle, j*200+15, i*200+15, this);
            }
        }
    }



    private void initializeBoard() {
        isGameOver = 0;
        for (int i=0; i<BOARD_WIDTH; ++i){
            for(int j=0 ;j<BOARD_WIDTH; ++j) {
                board[i][j]=' ';
            }
        }
        circleTimer.reset();
        crossTimer.reset();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        Point square = whichSquare(p);
        if(square.x == 3 || square.y == 3)
            return;
        if(board[square.y][square.x] == ' ') {
            if(turn == 'O'){
                board[square.y][square.x] = 'O';
                turn = 'X';
                circleTimer.stop();
                crossTimer.start();
            } else {
                board[square.y][square.x] = 'X';
                turn = 'O';
                crossTimer.stop();
                circleTimer.start();
            }
        }
        if(whoWon()) {
            JOptionPane.showMessageDialog(this, isGameOver + " wygrał!", "Wygrana", JOptionPane.INFORMATION_MESSAGE);
            askToPlayAgain();
        }
    }

    private void askToPlayAgain() {
        int reply = JOptionPane.showConfirmDialog(this, "Chcesz zagrać ponownie?", "Zagraj jeszcze raz", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            initializeBoard();
        } else {
            System.exit(0);
        }
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

    private boolean whoWon() {
        if(isBoardFull()) {
            isGameOver = ' ';
            return true;
        }
        for (int row = 0; row < BOARD_WIDTH; row++) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                // Ktoś wygrał w poziomie
                System.out.println(board[row][0] + " wygrał!");
                isGameOver = board[row][0];
                return true;
            }
        }

        // Sprawdzanie wygranej w pionie
        for (int col = 0; col < BOARD_WIDTH; col++) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                // Ktoś wygrał w pionie
                System.out.println(board[0][col] + " wygrał!");
                isGameOver = board[0][col];
                return true;
            }
        }

        // Sprawdzanie wygranej na przekątnych
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            // Ktoś wygrał na przekątnej \
            isGameOver = board[0][0];
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            // Ktoś wygrał na przekątnej /
            isGameOver = board[0][2];
            return true;
        }

        return false;
    }

    private boolean isBoardFull () {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if(board[i][j] == ' ')
                    return false;
            }
        }
        return true;
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
