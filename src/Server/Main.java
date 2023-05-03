package Server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(9001);
            JOptionPane.showMessageDialog(null, "Server created.");
        }catch (IOException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Could not create server.");
            return;
        }

        Socket client;
        JOptionPane.showMessageDialog(null, "Waiting for a player");
        try {
            client = server.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("gracz dołączył");

        JFrame frame = new JFrame("Tic Tac Toe - server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        Board panel = new Board();
        frame.add(panel);
        frame.setVisible(true);


        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true); //dodanie możliwości wysyłania wiadomości do klienta
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int a =5;
        while(true) {
            try {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        out.println((char)panel.board[i][j]);
                        //System.out.println(panel.board[i][j]);
                    }
                }
                String receive = in.readLine();
                out.println(panel.turn);
                receive = in.readLine();
                String receive2 = in.readLine();
                panel.insertChar(Integer.parseInt(receive), Integer.parseInt(receive2));

            }catch (IOException e) {
                //throw new RuntimeException(e);
            }
            a++;

        }
    }
}