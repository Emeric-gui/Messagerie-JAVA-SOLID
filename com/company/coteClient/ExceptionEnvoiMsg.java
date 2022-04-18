package com.company.coteClient;

/*
    Exception qui permet l'envoi de message

    Va etre retourné à l'application client
*/

public class ExceptionEnvoiMsg  extends Exception{
    public ExceptionEnvoiMsg(Object message, String type){
        ApplicationClient.envoiMessage(message, type);
    }
}
