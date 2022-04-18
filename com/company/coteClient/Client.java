package com.company.coteClient;

import java.net.Socket;

public interface Client {

    //Identifiant

    String getIdentifiantNom();

    //Socket

    void setSocket(Socket clientSocket);

    void closeIOSocket();

    //Messages

    void envoiMessage(Object data);

    Object recevoirMessage();

    //Threads

    void arretThreadClient();

    void startThreadClient();
}
