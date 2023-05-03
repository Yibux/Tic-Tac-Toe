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
            System.out.println("Nie udało się połączyć z hostem");
            System.exit(1);
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Tic Tac Toe - client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        Board panel = new Board();
        frame.add(panel);
        frame.setVisible(true);
        //int a = 5;
        while(true) {
            try {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        String temp = in.readLine();
                        panel.board[i][j] = temp.charAt(0);
                    }
                }
                out.println("Map received");
                String temp = in.readLine();
                panel.turn = temp.charAt(0);
                //if(a%10000==0)System.out.println(panel.turn);
                out.println(panel.x);
                out.println(panel.y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //++a;


        }
    }
}