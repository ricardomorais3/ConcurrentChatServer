package org.academiadecodigo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ricardo on 10-11-2016.
 */
public class Client {


    public static void main(String[] args) {
        Client client = new Client();
        client.runClient();
    }

    private void runClient() {
        try {
            Socket clientSocket = new Socket(InetAddress.getByName("localhost"), 3000);
            System.out.println("Connected to server");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream()/*, true*/);

            Thread listener = new Thread(new Listener(clientSocket));
            listener.start();

            while (true) {
                String c = bufferedReader.readLine();
                printWriter.println(c);
                printWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Listener implements Runnable {

        private Socket clientSocket;

        public Listener(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message;

                while (true) {
                    if ((message = bufferedReader.readLine()) != null) {
                        System.out.println(message);
                    } else {
                        break;
                    }
                }

                System.out.println("Lost connection with the Server");
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
