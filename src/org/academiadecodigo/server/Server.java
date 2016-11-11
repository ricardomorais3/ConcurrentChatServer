package org.academiadecodigo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by ricardo on 10-11-2016.
 */
public class Server {

    private ArrayList<ClientHandler> arrayList;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            Socket clientSocket;



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
