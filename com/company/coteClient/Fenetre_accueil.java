package com.company.coteClient;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Fenetre_accueil extends JFrame{

    //Attributs
    private JPanel mainContainer;

    private Font policeTexte;
    private Font policeTitre;

    private final JTextField zoneTexte = new JTextField();

    private JLabel labelPreTexte;
    private JLabel labelTexteAccueil;

    /* --------------------------------------------------*/
    //Constructeur
    public Fenetre_accueil(){

        //Appel des différentes fonction qui definissent la fenetre
        setInfosFenetre();
        setPolices();
        setTextes();
        setContainers();

        //Configuration evenement si appui entrée ou appui bouton
        zoneTexte.addActionListener(new TexteListener());

        //ajout du container à la fenetre
        this.setContentPane(mainContainer);
        this.setVisible(true);
    }

    //Pour configurer les informations de la fenetre
    private void setInfosFenetre(){//fonction 1 du constructeur
        this.setTitle("Nomad");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    //Pour configurer les polices d'ecriture
    private void setPolices(){//fonction 2 pour le constructeur
        //police d'écriture de texte
        this.policeTexte = new Font("Tahoma", Font.BOLD, 11);

        //police d'écriture de titre
        this.policeTitre = new Font("Tahoma", Font.BOLD, 20);
    }

    //Pour configurer les textes afficher sur la fenetre
    private void setTextes(){
        //definition du texte Pseudonyme
        labelPreTexte = new JLabel("Pseudonyme : ");
        labelPreTexte.setFont(policeTexte);
        labelPreTexte.setForeground(Color.BLACK);
        labelPreTexte.setHorizontalAlignment(JLabel.CENTER);

        //Definition du texte d'accueil
        labelTexteAccueil = new JLabel("NOMAD");
        labelTexteAccueil.setFont(policeTitre);
        labelTexteAccueil.setForeground(Color.orange);
        labelTexteAccueil.setHorizontalAlignment(JLabel.CENTER);
    }

    //Pour configurer les containers de la fenetre
    private void setContainers(){
        //container central
        JPanel centralContainer = new JPanel();
        centralContainer.setBackground(Color.lightGray);
        GridLayout layoutGrille = new GridLayout(1,2);
        centralContainer.setLayout(layoutGrille);

        //Configuration de la grille
        centralContainer.add(labelPreTexte);
        centralContainer.add(zoneTexte);


        //container de la fenetre
        mainContainer = new JPanel();
        mainContainer.setBackground(Color.white);
        BorderLayout layoutBorder = new BorderLayout();
        mainContainer.setLayout(layoutBorder);
        mainContainer.add(labelTexteAccueil, BorderLayout.NORTH);
        mainContainer.add(centralContainer, BorderLayout.CENTER);

        //Placement zone sud --> Pour bouton
        JPanel south = new JPanel();
        JButton boutonValidation = new JButton("Connexion");
        south.add(boutonValidation);
        mainContainer.add(south, BorderLayout.SOUTH);
        boutonValidation.addActionListener(new TexteListener());
    }

    /* --------------------------------------------------*/


    //Classe qui ecoute la zone de texte
    class TexteListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            String result = zoneTexte.getText();
            try{
                if(!result.equals("")){//creation d'une exception si la zone de texte n'est pas nul -> Creation du client
                    throw new ExceptionCreationClient(result);
                }else{
                    System.out.println("Resultat vide, ne rien faire");
                }
            }catch(ExceptionCreationClient e){
                //
            }
        }
    }
}
