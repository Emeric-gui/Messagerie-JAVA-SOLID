package com.company.coteServeur.serveurs;

import com.company.coteServeur.ApplicationServeur;
import com.company.coteServeur.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandlerString implements ClientHandler {
    //Attributs
    Socket serverSocket;
    private String name;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean estConnecte;
    private final int id;
    private boolean etatThread = true;
    /* --------------------------------------------------*/

    // Constructor
    public ClientHandlerString(Socket serverSocket, String name, int id) {
        this.id = id;
        this.serverSocket = serverSocket;
        this.name = name;
        try {
            this.in = new DataInputStream(serverSocket.getInputStream()); // Mise en place entrée
            this.out = new DataOutputStream(serverSocket.getOutputStream()); // Mise en place sortie
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.estConnecte = true;
    }

    /* --------------------------------------------------*/

    //Pour lire et redistribuer les messages qui arrive au niveau du serveur
    @Override
    public void recevoirMessage(){
        try {
            String message = in.readUTF();
            System.out.println("messageClient " + message);

            if(message.contains("pseudo:")){//Si le message contient pseudo alors on définit le nom du ClientHandler
                String[] msgClientPseudo = message.split(":");
                this.name = msgClientPseudo[1];
            }else if(message.contains(":deconnexion")){//Si le message est pour prévenir d'une deconnexion
                deconnexionSocket();
            }else{//Transmission du message aux autres clients
                transmissionClients(message);
            }
        } catch (IOException e) {
            System.out.println("Impossibilite de recevoir de messages");
        }

    }


    //Transmet le messages a tous les autres utilisateurs
    @Override
    public void transmissionClients(Object objet){
        if(objet == null){
            System.out.println("Pas de message a transmettre");
        }else{
            //Envoi du message a tous les clients
            for (ClientHandler mc : ApplicationServeur.getActiveClient()) {
                //si le pseudo est connecté le message lui est envoyé
                if (mc.etatConnexion() && (mc.recevoirIdentifiant() != this.id)) {
                    //appel de envoiMessage pour envoyer le message a travers le socket
                    mc.envoiMessage(objet);
                }
            }
        }

    }

    //Envoi le message au client grâce au socket
    public void envoiMessage(Object objet){
        try {
            this.out.writeUTF(objet.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* --------------------------------------------------*/

    //Permet de connaitre l'état d'un client
    @Override
    public boolean etatConnexion(){
        return this.estConnecte;
    }

    //Recupère l'identifiant d'un client
    @Override
    public int recevoirIdentifiant(){
        return this.id;
    }

    //Deconnecte ce ClientHandler du serveur, coupe la connexion entre le serveur et le client
    private void deconnexionSocket(){
        this.estConnecte = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.etatThread = false;
        }
    }

    /* --------------------------------------------------*/

    //Thread pour permettre la récupération de messages
    @Override
    public void run() {
        while (etatThread) {
            recevoirMessage();
        }
        try {
            // fermeture entrée/sortie
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
