package com.company.coteClient.clients;

import com.company.coteClient.Client;
import com.company.coteClient.ExceptionReceptionMsg;

import java.io.*;
import java.net.Socket;

public class Client_format_texte implements Client {
    //Attributs
    private int id;
    private String name;

    private DataInputStream in;
    private DataOutputStream out;

    private Thread receptionInfiniMsg;

    private boolean etatThread = true;

    /* --------------------------------------------------*/
    //Constructeur par defaut
    public Client_format_texte(){
        this.id = 0;
        this.name = "default_name";
    }

    //Constructeur avec nom
    public Client_format_texte(String nom){
        this.id = 0;
        this.name = nom;
    }

    //Setters & Getters
    public void setIdentifantNom(String name){
        this.name = name;
    }

    public void setid(int id){
        this.id = id;
    }

    public int getid(){
        return this.id;
    }

    public String getIdentifiantNom(){
        return this.name;
    }

    public String toString(){
        return "Votre pseudo est : "+this.name;
    }

    /* --------------------------------------------------*/

    //Fonction qui permet de définir le socket du client
    @Override
    public void setSocket(Socket clientSocket){
        try{
            in = new DataInputStream(clientSocket.getInputStream()); // Mise en place entrée
            out = new DataOutputStream(clientSocket.getOutputStream()); // Mise en place sortie

            out.writeUTF("pseudo:"+ this.name);

            creationThread();//appel pour créer le thread qui permet la réception de message

        }catch (IOException e){
            System.out.println("Impossibilite de mettre en place l'entree et / ou la sortie");
        }
    }

    private void creationThread(){
        receptionInfiniMsg = new Thread(() -> {
            Object msg = null;
            while (etatThread) {
                // récupère les messages reçus
                if ((msg = recevoirMessage()) != null){
                    try{
                        throw new ExceptionReceptionMsg(msg, "String");
                    }catch(ExceptionReceptionMsg e){
                        //
                    }
                }
            }
            closeIOSocket();
        });
    }

    //Pour démarrer le thread au niveau de l'application
    @Override
    public void startThreadClient(){
        this.receptionInfiniMsg.start();
    }
    //Pour arreter le thread au niveau de l'application
    @Override
    public void arretThreadClient(){
        this.etatThread = false;
    }

    //Pour fermer l'entrée et la sortie du socket
    @Override
    public void closeIOSocket(){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Impossible de fermer les entrées et sortie du socket");
        }
    }

    //Pour envoyer un message
    @Override
    public void envoiMessage(Object data){
        try {
            //on regarde si le message à envoyer est une deconnexion ou un message classique
            if(data.toString().equals(":deconnexion")){
                out.writeUTF(this.name+ data);
            }else{
                out.writeUTF(this.name+"#"+ data);
            }
        } catch (IOException e) {
            System.out.println("Impossible d'envoyer le message");
        }
    }

    //Pour recevoir un message
    @Override
    public Object recevoirMessage(){
        Object retour = null;
        try {
            retour = in.readUTF();
        } catch (IOException e) {
            System.out.println("Le thread n'est plus relie au socket");
        }
        return retour;
    }

}