package Client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Socket client = null;
        PrintWriter out;
        BufferedReader in;
        try {
            client = new Socket("127.0.0.1", 9001);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Tic Tac Toe - client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        Board panel = new Board();
        frame.add(panel);
        frame.setVisible(true);
        int a = 5;
        while(true) {
            try {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        String temp = in.readLine();
                        panel.board[i][j] = temp.charAt(0);
                    }
                }
                if(a%5000==0)
                    panel.printBoard();
                //System.out.println("mapa otrzymana");
                out.println("Map received");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ++a;


        }
    }
}