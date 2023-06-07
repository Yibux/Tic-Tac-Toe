package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerChoose extends JPanel implements MouseListener {
    public int playerChoose = 0;
    public static final int OFFSET = 50;
    public PlayerChoose() {
        addMouseListener(this);
    }
    public void paint(Graphics g) {
        g.clearRect(0,0,500,300);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 300);
        g.setColor(Color.black);
        //g.fillRect();
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Wybierz jedną opcję",50,10 + OFFSET);
        g.fillRect(50,100+ OFFSET,150,50);
        g.fillRect(300,100+ OFFSET,150,50);
        g.setColor(Color.WHITE);
        g.drawString("Z komputerem",60,140+ OFFSET);
        g.drawString("Z drugim graczem",310,140+ OFFSET);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(p.x >= 50 && p.x <200 && p.y >= 100+ OFFSET && p.y <= 150+ OFFSET)
            playerChoose = 1;
        else if(p.x >= 300 && p.x <= 450 && p.y >= 100+ OFFSET && p.y <= 150+ OFFSET)
            playerChoose = 2;
        System.out.println(playerChoose);
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
