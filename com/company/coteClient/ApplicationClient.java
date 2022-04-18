package com.company.coteClient;

import java.io.IOException;
import java.net.Socket;

public class ApplicationClient {
    //Attributs
    private static Fenetre_accueil fenetre_accueil;
    private static Client client;
    private static Fenetre_principale fenetre_principale;

    /* --------------------------------------------------*/

    //Constructeur
    public ApplicationClient(){
        //Definition des fenetres de l'application
        fenetre_accueil = new Fenetre_accueil();
        fenetre_principale = new Fenetre_principale();
    }


    //Defini le client de l'application
    public static void defineClient(Client clientFormattexte){
        ApplicationClient.client = clientFormattexte;

        System.out.println("############");
        System.out.println(clientFormattexte.getIdentifiantNom());
        System.out.println("############");

        
        fenetre_principale.ajoutPersonneCo(clientFormattexte.getIdentifiantNom());//ajout du nom dans la zone d'affichage des pseudonymes

        defineConnexion();//pour définir la connexion au serveur

        fenetre_accueil.setVisible(false);
        fenetre_principale.setVisible(true);
    }

    //Défini la connexion au serveur
    public static void defineConnexion(){
        //adresse ip et port de la connexion socket
        String ip = "localhost"; //sinon 127.0.0.1
        int port = 8800;
        Socket clientSocket;
        try {
             clientSocket = new Socket(ip, port);
             //Va définir le socket dans le client directement
             client.setSocket(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Démarre le thread du client
        client.startThreadClient();
    }

    /* --------------------------------------------------*/

    //Transmet le message de la fenetre au client qui va alors le transmettre au serveur
    public static void envoiMessage(Object message, String type){
        if(type.equals("String")){
            client.envoiMessage(message);
            if(message.toString().equals(":deconnexion")){
                client.arretThreadClient();
            }
        }
    }

    //Ajoute les messages dans la zone d'affichage des messages
    public static void ajoutMessageToFenetre(Object data, String type){
        if(type.equals("String")){
            String message = data.toString();
            String[] msgElements = message.split("#");
            fenetre_principale.ajoutMessage(msgElements[0], msgElements[1]);
        }
    }


    public static void main(String[] args){
        //code appelé au lancement de l'application client
        new ApplicationClient();
    }
}
