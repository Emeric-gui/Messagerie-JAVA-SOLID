package com.company.coteClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Fenetre_principale extends JFrame{

    //Attributs
    private final JPanel mainContainer;
    private final JPanel centralContainer;
    private final JPanel westContainer;
    private final JPanel southContainer;

    private String persCo;

    private JTextField zoneEcritureTexte;
    private JTextArea zoneAffichageMsg;

    private Font policeTexte;
    private Font policeTitre;
    private Font policePersCo;

    private int nbUtilisateurs;

    /* --------------------------------------------------*/
    //Constructeur
    public Fenetre_principale(){
        /**
         * Instanciation des attributs
         *
         */
        mainContainer = new JPanel();
        centralContainer = new JPanel();
        southContainer = new JPanel();
        westContainer = new JPanel();

        //Appel des différentes fonctions pour configurer la fenetre
        setParametreFenetre();
        setPoliceEcriture();

        /**
         ** CONTAINERS **
         */
        setWestContainer();
        setSouthContainer();
        setMain_et_CentralContainers();

        /**
         *Affichage fenetre avec container principal
         */
        //ajout du container à la fenetre
        this.setContentPane(mainContainer);
        this.setVisible(false);

        //ajout d'un evenement pour étudier la fenetre (sa fermeture, etc.)
        this.addWindowListener(new FenetreListener());

    }

    //Pour configurer les paramètres de la fenetre
    private void setParametreFenetre(){
        //Paramétrage de la fenetre
        this.setTitle("Nomad");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        nbUtilisateurs = 20;
    }

    //Pour configurer les polices d'ecriture
    private void setPoliceEcriture(){

        //police d'écriture de texte
        this.policeTexte = new Font("Tahoma", Font.BOLD, 11);

        //police d'écriture de titre
        this.policeTitre = new Font("Tahoma", Font.BOLD, 20);

        //police d'écriture de personnes connectés
        this.policePersCo = new Font("Tahoma", Font.BOLD, 14);
    }

    //Pour configurer le container ouest et ses elements
    private void setWestContainer(){

        /**
         * Partie OUEST
         *
         */
        JLabel labelTitrePersonne = new JLabel("Personnes Connectees");


        //Configuration de la zone ouest de la fenetre --> Contiendra les pseudos de tous les utilisateurs connectés
        westContainer.setBackground(Color.lightGray);
        GridLayout layoutGrilleWest = new GridLayout(nbUtilisateurs, 1, 0,1);
        westContainer.setLayout(layoutGrilleWest);

        //Définition du texte de titre des personnes connectées
        labelTitrePersonne.setFont(policeTitre);
        labelTitrePersonne.setForeground(Color.orange);
        labelTitrePersonne.setHorizontalAlignment(SwingConstants.CENTER);

        westContainer.add(labelTitrePersonne);

    }
    //Pour configurer le container sud et ses elements
    private void setSouthContainer(){
        /**
         * Partie SUD
         *
         */
        JButton boutonEnvoi = new JButton("Envoyer");
        zoneEcritureTexte = new JTextField();

        //container zone sud de la fenetre
        southContainer.setBackground(Color.lightGray);
        GridLayout layoutGrille = new GridLayout(1,2);
        southContainer.setLayout(layoutGrille);

        //Configuration de la grille
        southContainer.add(zoneEcritureTexte);
        southContainer.add(boutonEnvoi);

        /**
         * Configuration evenements
         */
        //Configuration evenement si appui entrée ou appui bouton
        boutonEnvoi.addActionListener(new TexteListener());
        zoneEcritureTexte.addActionListener(new TexteListener());

    }
    //Pour configurer les containers central et main et leurs elements
    private void setMain_et_CentralContainers(){
        BorderLayout layoutBorder = new BorderLayout();

        //Configuration de la zone d'affichage de texte
        zoneAffichageMsg = new JTextArea(50, 75);
        JScrollPane scrollPane = new JScrollPane(zoneAffichageMsg);
        zoneAffichageMsg.setEditable(false);
        zoneAffichageMsg.setLineWrap(true);
        zoneAffichageMsg.setWrapStyleWord(true);
        zoneAffichageMsg.setFont(policeTexte);


        //Definition du texte d'accueil
        JLabel labelTexteMenu = new JLabel("NOMAD");
        labelTexteMenu.setFont(policeTitre);
        labelTexteMenu.setForeground(Color.orange);
        labelTexteMenu.setHorizontalAlignment(SwingConstants.CENTER);

        /**
         * Partie Centrale
         *
         */
        centralContainer.setBackground(Color.WHITE);
        centralContainer.add(scrollPane);

        /**
         * Partie Principale
         *
         */
        //container de la fenetre
        mainContainer.setBackground(Color.white);
        mainContainer.setLayout(layoutBorder);

        //ajout des petits container dans le container de la fenetre
        mainContainer.add(centralContainer, BorderLayout.CENTER);
        mainContainer.add(labelTexteMenu, BorderLayout.NORTH);
        mainContainer.add(westContainer, BorderLayout.WEST);
        mainContainer.add(southContainer, BorderLayout.SOUTH);

    }


    /* --------------------------------------------------*/


    //Fontion pour ajouter une personne sur la zone d'affichage des personnes connectés
    public void ajoutPersonneCo(String name){
        persCo = name;
        addPersIntoAff(name);
    }

    /**
     * Appel quand connexion d'une nouvelle personne sur le serveur
     *
     */
    private void addPersIntoAff(String name){
        String delimitationDebut = " ";
        String delimitationFin = "   ";
        JLabel premCo = new JLabel("-"+ delimitationDebut +name+ delimitationFin);
        premCo.setForeground(Color.RED);
        premCo.setFont(this.policePersCo);
        premCo.setHorizontalAlignment(SwingConstants.CENTER);
        this.westContainer.add(premCo);

    }

    //ajoute le message dans la zone prévu à cet effet
    public void ajoutMessage(String expediteur, String messsage){
        zoneAffichageMsg.append(expediteur+" : "+messsage+"\n");
    }


    //classe pour écouter le texte qu'ecrit l'utilisateur
    class TexteListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            String msg = zoneEcritureTexte.getText();
            if(!msg.equals("")){//Envoi d'un message si le texte n'est pas vide
                zoneAffichageMsg.append(persCo+" (Moi) : "+msg+"\n");
                zoneEcritureTexte.setText("");
                try{
                    throw  new ExceptionEnvoiMsg(msg, "String");
                }catch(ExceptionEnvoiMsg e){
                    //
                }

            }
        }
    }

    //classe pour écouter la fenetre de l'utilisateur
    class FenetreListener implements WindowListener{
        /*
            Si l'utilisateur clique sur la croix rouge = windowClosing
            alors on fait la deconnexion du client
        */
        @Override
        public void windowClosing(WindowEvent e) {
            //Fermer le socket ici
            try{
                throw  new ExceptionEnvoiMsg(":deconnexion", "String");
            }catch(ExceptionEnvoiMsg en){
                //
            }finally {
                //fermer la fenetre
                System.exit(0);
            }

        }

        @Override
        public void windowOpened(WindowEvent e) {
            //pas d'action précise a effectuer
        }

        @Override
        public void windowClosed(WindowEvent e) {
            //pas d'actions précises à effectuer
        }

        @Override
        public void windowIconified(WindowEvent e) {
            //pas d'actions précises à effectuer
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
//            changeAffListPersCo();
        }

        @Override
        public void windowActivated(WindowEvent e) {
//            changeAffListPersCo();
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            //pas d'action précise a effectuer
        }
    }
}


