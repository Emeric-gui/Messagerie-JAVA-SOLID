package com.company.coteClient;

/*
    Exception qui permet la création d'un client

    Va etre retourné à l'application client
*/

import com.company.coteClient.clients.Client_format_texte;

public class ExceptionCreationClient extends Exception{
    public ExceptionCreationClient(String nom){
        ApplicationClient.defineClient(new Client_format_texte(nom));
    }
}