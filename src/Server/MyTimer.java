package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimer extends JLabel {
    private int seconds = 0;
    private Timer timer;

    public MyTimer() {
        setText("00:00");
        timer = new Timer(1000, e -> {
            seconds++;
            int min = seconds / 60;
            int sec = seconds % 60;
            setText(String.format("%02d:%02d", min, sec));
        });
        timer.stop();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        seconds = 0;
        setText("00:00");
    }
}