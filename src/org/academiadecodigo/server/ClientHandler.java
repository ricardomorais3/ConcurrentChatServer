package org.academiadecodigo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ricardo on 10-11-2016.
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private Server server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String clientName;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String c;

            while (true) {

                if ((c = bufferedReader.readLine()) != null) {
                    server.sendAll(c);
                } else {
                    break;
                }
            }
            System.out.println("Lost connection with "+Thread.currentThread().getName());
            clientSocket.close();
            server.getClientHandlerList().remove(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {

            printWriter = new PrintWriter(clientSocket.getOutputStream()/*, true*/);
            printWriter.println(message);
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
