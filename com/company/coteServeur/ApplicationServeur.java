package com.company.coteServeur;
import com.company.coteServeur.serveurs.ClientHandlerString;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Classe principale du serveur
 */
public class ApplicationServeur{

    // Vector pour stocker les clients actifs
    private static final Vector<ClientHandler> activeClient = new Vector<>();
    // compteur de clients
    static int i = 0;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            Socket server;

            while(true) {
                server = serverSocket.accept();

                System.out.println("Connecté au client " + server.getRemoteSocketAddress());

                // cree un nouveau clientHandler pour le nouveau client
                ClientHandler mtch = new ClientHandlerString(server,"client " + i, i);
                // creation d'un nouveau thread
                Thread t = new Thread(mtch);
                // ajout du client à la liste des clients
                activeClient.add(mtch);

                // invocation de la methode start()
                t.start();
                i++;
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    //Getters
    public static Vector<ClientHandler> getActiveClient(){
        return activeClient;
    }
}

