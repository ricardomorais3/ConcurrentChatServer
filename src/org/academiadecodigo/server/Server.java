package org.academiadecodigo.server;

import org.academiadecodigo.client.Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 10-11-2016.
 */
public class Server {


    public static void main(String[] args) {

        List<ClientHandler> clientHandlerList = new ArrayList<>();
        ClientHandler clientHandler;

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println(serverSocket);
            Socket clientSocket;

            System.out.println("Waiting for Connection");
            clientSocket = serverSocket.accept();
            System.out.println("Connected! @" + clientSocket);
            while (true) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String c = bufferedReader.readLine();
                System.out.println("Li esta frase: " + c);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
