package com.company.coteServeur;

public interface ClientHandler extends Runnable {
    
    //Thread
    @Override
    void run();

    //Messages
    void recevoirMessage();

    void envoiMessage(Object data);

    void transmissionClients(Object objet);

    //Infos autres clients
    
    boolean etatConnexion();

    int recevoirIdentifiant();

}
