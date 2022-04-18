package com.company.coteClient;

/*
    Exception qui permet la transmission du message du client à l'application du client

    Va être retourné à l'application client
*/

public class ExceptionReceptionMsg extends Exception{
    public ExceptionReceptionMsg(Object data, String type){
        ApplicationClient.ajoutMessageToFenetre(data, type);
    }
}
