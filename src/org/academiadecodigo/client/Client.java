package org.academiadecodigo.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ricardo on 10-11-2016.
 */
public class Client {

    public static void main(String[] args) {

        try {
            Socket clientSocket = new Socket(InetAddress.getByName("localhost"), 3000);
            System.out.println("Connected to server");
            Scanner scanner = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

            while(true){
                String c = scanner.nextLine();
                printWriter.println(c);
                printWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
