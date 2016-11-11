package org.academiadecodigo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 10-11-2016.
 */
public class Server {

    private List<ClientHandler> clientHandlerList;

    public Server() {
        this.clientHandlerList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    private void startServer() {

        ClientHandler clientHandler;

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println(serverSocket);
            Socket clientSocket;

            while (true) {

                System.out.println("Waiting for Connection...");

                //Stops the program and waits for a connection
                clientSocket = serverSocket.accept();
                System.out.println("Connected! @" + clientSocket);

                //Create a clientHandler for each new connection and had it to the clientHandlerList
                clientHandler = new ClientHandler(clientSocket, this);
                clientHandlerList.add(clientHandler);

                //Each clientHandler runs on a new thread
                Thread client = new Thread(clientHandler);
                client.setName("Client " + client.getName().substring(client.getName().length() - 1));
                client.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String message) {
        for (ClientHandler clientHandler : clientHandlerList) {
            clientHandler.sendMessage(message);
        }
    }

    public List<ClientHandler> getClientHandlerList() {
        return clientHandlerList;
    }
}
