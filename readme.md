# Projet de Serveur multithreading

## 0.Version
    Ceci est la version 2 du programme.
    Elle respecte les principes SOLID.
    Elle sera utile pour des améliorations.

## 1.Présentation

    Le projet ci-joint est une application java.
    Elle permet a plusieurs clients de communiquer entre eux.
    Ils peuvent s'envoyer des messages textes.
    
    On utilise une fenetre pour récupérer le pseudonyme du client.

    Une seconde fenetre sert d'affichage pour les messages et d'envoi de messages
    
    Un serveur à part s'occupe de transmettre les messages entre les différents clients

## 2.Outils
    Pas d'outils supplémentaires nécessaires pour faire fonctionner l'application
    Il suffit d'utiliser une IDE qui peut compiler et run un programme java.
    IntelliJ ou Visual Studio Code (avec ajout des bons outils [JRE et JDK]) marchent très bien.
    La version de JDK utilisé est JDK16.
    
    //Attention//
    Préférer l'utilisation d'IntelliJ qui permet la création d'instances multiples.
    Ce qui est requis si vous souhaitez que plusieurs utilisateurs communiquent.
    //Fin Attention//

### A.Utilisation du programme
    En utilisant IntelliJ :
    -Lancer la configuration Main_serveur en premier.
        Cela va démarrer le serveur du programme.
    -si aucune configuration n'est créée, choisissez la fonction main de ApplicationServeur.java
        et indiquez en argument 8800.

    -Choisissez ensuite la configuration Main_client
        Cette configuration ne nécessite pas d'argument.
        --N'oubliez l'option "Allow multiples instances"
    
    -Lancer autant d'instances clientes que voulu

    -Pour chaque instance, choisissez un pseudonyme.
        Une fois choisi, vous êtes redirigé vers la fenetre principale

    -Utilisez la zone de texte en bas de la fenetre pour ecrire un message.
        Appuyez ensuite sur la touche entrée ou le bouton Envoyer
        Le message est ensuite envoyer à toutes les autres instances

    -Vous pouvez maintenant communiquez avec les autres utilisateurs


### B.Fermeture Programme
    Pour fermer le programme, il suffit de cliquer sur la croix rouge de l'application.
    Vous serez alors déconnecté du serveur puis le programme s'arretera naturellement.

    Pour fermer le serveur : appuyer sur le bouton carré rouge d'IntelliJ.
## 3.Auteurs
    Emeric GUICHET
    Loïc DUPUIS 
