package org.academiadecodigo.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by ricardo on 10-11-2016.
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private Server server;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String c;

            while (true) {

                if ((c = bufferedReader.readLine()) != null) {
                    System.out.println("Li esta frase: " + c);
                    //sendAll();
                } else {
                    break;
                }
            }
            System.out.println("Lost connection with "+Thread.currentThread().getName());
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){

    }
}
