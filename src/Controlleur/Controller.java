package Controlleur;

import java.util.ArrayList;
import java.util.Collections;
import model.cartesOrange.CarteDosOrange;
import model.cartesOrange.CarteHelicoptere;
import model.cartesOrange.CarteMonteeDesEaux;
import model.cartesOrange.CarteSacDeSable;
import model.cartesOrange.CarteTresor;
import model.aventurier.Aventurier;
import model.aventurier.Explorateur;
import model.aventurier.Ingenieur;
import model.aventurier.Messager;
import model.aventurier.Navigateur;
import model.aventurier.Pilote;
import model.aventurier.Plongeur;
import model.CarteInondation;
import model.Echelle;
import model.Grille;
import model.Tresor;
import model.Tuile;
import util.Message;
import util.Parameters;
import util.TypesMessage;
import util.Utils;
import util.Utils.Pion;
import static util.Utils.afficherInformation;
import view.*;

public class Controller implements Observateur {

    //Initialisation des attributs
    private static VueBienvenue bienvenue;
    private static VueParamJeu paramJeu;
    private static VueRegles regles;
    private static VueJeu jeu;
    private static VuePopUp popUp;
    
    private static int nbJoueurs = 2;
    private static int nbAction = 0;
    private static String nomJ1;
    private static String nomJ2;
    private static String nomJ3;
    private static String nomJ4;
    private static int difficulte;                                              // à changer pour l'échelle
    private static Echelle echelle;
    private static boolean tresorRecup;
    
    private static int nbJ = 0;
    private static Grille grilleJeu;
    private static ArrayList<Aventurier> joueurs;
    private static Aventurier av1, av2, av3, av4, joueurC;
    private static ArrayList<CarteDosOrange> piocheOrange;
    private static ArrayList<CarteDosOrange> defausseOrange;
    private static ArrayList<Tresor> tresors;
    private static ArrayList<Tresor> tresorsGagnés;
    private static ArrayList<Tresor> tresorsCompare;
    private static ArrayList<CarteInondation> piocheInondation;
    private static ArrayList<CarteInondation> defausseInondation;
    
    public static void main(String[] args) {
        new Controller();
    }
    
    public Controller() {
        grilleJeu = new Grille();
        joueurs = new ArrayList<>();
        bienvenue = new VueBienvenue(this);
        paramJeu = new VueParamJeu(this);
        regles = new VueRegles(this);
        jeu = new VueJeu(this, grilleJeu);
        setGrilleJeu(grilleJeu);
        defausseOrange = new ArrayList<>();
        defausseInondation = new ArrayList<>();
        créerTresors();
        remplirPiocheOrange();
        remplirPiocheInondation();
        bienvenue.afficher();
    }

    private static void setTresorRecup(boolean bool) {
        tresorRecup = bool;
    }
    
    public static void setGrilleJeu(Grille GrilleJeu) {                         //Fonction permettant de lier les grilles (joueurs - controlleur) 
        grilleJeu = GrilleJeu;
        for (Aventurier av : joueurs) {
            av.setGrille(GrilleJeu);
        }
        jeu.setGrille(GrilleJeu);
    }

    @Override
    public void traiterMessage(Message msg) {                                   //Permet de traiter l'information des boutons avec l'ihm
        if (msg.getTypeMessage() == TypesMessage.ACTION_Jouer) {
            bienvenue.fermer();
            paramJeu.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Retour) {
            paramJeu.fermer();
            regles.fermer();
            bienvenue.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Regles) {
            bienvenue.fermer();
            regles.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Quitter) {
            bienvenue.fermer();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Deplacer) {
            if (jeu.getDeplApp()) {
                if (nbAction < 3) {
                    jeu.afficherPossible(joueurC.getTuilesPossibles(true));     //Affichage des tuiles où le deplacement est possible
                    setGrilleJeu(joueurC.getGrilleAv());
                    
                }
            } else {
                jeu.repaint();
            }
        }else if (msg.getTypeMessage() == TypesMessage.ACTION_DonnerCarte) {
            afficherDonCartePossible();
            //joueurC.donnerCarte(joueurC, piocheOrange, joueurC);
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Assecher) {      //Affichage des tuiles où l'assechement est possible
            if (jeu.getAss()) {
                if (jeu.getDeplApp()) {
                    jeu.repaint();
                    jeu.setDeplApp(false);
                }
                setGrilleJeu(joueurC.getGrilleAv());
                jeu.afficherPossible(joueurC.getTuilesPossibles(false));
            } else {
                jeu.repaint();
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Fin) {
            if (nbJ == nbJoueurs-1) {
                nbJ = 0;
            } else {
                nbJ++;
            }
            jeu.setDeplApp(false);
            jeu.setAssApp(false);
            piocherDeuxCartesOrange();
            piocherCartesInondation();
            setGrilleJeu(joueurC.getGrilleAv());
            afficherMain();
            System.out.println("N° courant : " + nbJ);
            tourDeJeu();
            
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_CHOIX_CARTE) {
             popUp.fermer();
             enleverCarteSurplus(joueurC.getMain().get(msg.getNumBtn()));
             if (joueurC.getMain().size() > 5) {
                    popUp = new VuePopUp(this, joueurC.getMain());
                    popUp.afficher();
             }
             Boolean bool;
            jeu.resetMainIHM();
            for (Aventurier av: joueurs){
                if (av == joueurC){
                    bool = true;
               } 
                else {
                    bool = false;
                }
                jeu.afficherMain(av.getMain(), bool, av.getNom(), av.getPion());
            }
            jeu.repaint();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_PrendreTresors) {
            gagnerTresor();
            ArrayList<CarteDosOrange> carteTemp = new ArrayList<>();
            for (CarteDosOrange carte : joueurC.getMain()){
                int i = 0;
                if (carte.getTresor() != null && 
                    carte.getTresor().getNomTresor() == "La Pierre sacrée") {
                    carteTemp.add(carte);
                    defausseOrange.add(carte);
                    i++;
                }
                if (i == 4) {
                    break;
                }
            }
            for (CarteDosOrange carte : joueurC.getMain()){
                int i = 0;
                if (carte.getTresor() != null && 
                    carte.getTresor().getNomTresor() == "La Statue du zéphyr") {
                    carteTemp.add(carte);
                    defausseOrange.add(carte);
                    i++;
                }
                if (i == 4) {
                    break;
                }
            }
            for (CarteDosOrange carte : joueurC.getMain()){
                int i = 0;
                if (carte.getTresor() != null && 
                    carte.getTresor().getNomTresor() == "Le Cristal ardent") {
                    carteTemp.add(carte);
                    defausseOrange.add(carte);
                    i++;
                }
                if (i == 4) {
                    break;
                }
            }
            for (CarteDosOrange carte : joueurC.getMain()){
                int i = 0;
                if (carte.getTresor() != null && 
                    carte.getTresor().getNomTresor() == "Le Calice de l'onde") {
                    carteTemp.add(carte);
                    defausseOrange.add(carte);
                    i++;
                }
                if (i == 4) {
                    break;
                }
            }
            for (CarteDosOrange carte : carteTemp) {
                joueurC.removeCarteMain(carte);
            }
            nbAction++;
        }
    }
    
    @Override                                                                   //Envoie les paramètres de jeu
    public void envoyerDonnees(int nbJoueurs, String nomJ1, String nomJ2, String nomJ3, String nomJ4, int difficulte) {
        this.nbJoueurs = nbJoueurs;
        this.nomJ1 = nomJ1;
        this.nomJ2 = nomJ2;
        
        if (nbJoueurs >= 3) {
            this.nomJ3 = nomJ3;
            if (nbJoueurs == 4) {
                this.nomJ4 = nomJ4;
            }
        }
        echelle = new Echelle(difficulte);
        
        initJoueurs(nbJoueurs, nomJ1, nomJ2, nomJ3, nomJ4);                     //Initialise le role des joueurs
        
        joueurs.add(av1);
        joueurs.add(av2);
        
        if (nbJoueurs >= 3) {
            joueurs.add(av3);
            if (nbJoueurs == 4) {
                joueurs.add(av4);
            }
        }
        setGrilleJeu(grilleJeu);
        paramJeu.fermer();
        initInondationDebut();
        distributionCartesOrangeDebut();
        jeu.afficher();
        tourDeJeu();
    }
    
    public void initJoueurs(int nbJoueurs, String nomJ1, String nomJ2, String nomJ3, String nomJ4) {
        String nom;
        String nomPos = "";
        int random;
        int randomSauv1 = -1;
        int randomSauv2 = -1;
        int randomSauv3 = -1;
        int min = 0;
        int max = 5;
        Aventurier role = new Explorateur("");
        for (int i = 0; i < nbJoueurs; i++) {
            random = getRandom(min, max);
            if (i == 0) {
                nom = nomJ1;
            } else if (i == 1) {
                nom = nomJ2;
            } else if (i == 2) {
                nom = nomJ3;
            } else {
                nom = nomJ4;
            }
            while ((random == randomSauv1 || random == randomSauv2 || random == randomSauv3)){
                random = getRandom(min, max);
            }
            if (random == 0) {
                role = new Explorateur(nom);
                nomPos = "La porte de cuivre";
            } else if (random == 1) {
                role = new Ingenieur(nom);
                nomPos = "La porte de bronze";
            } else if (random == 2) {
                role = new Messager(nom);
                nomPos = "La porte d'argent";
            } else if (random == 3) {
                role = new Navigateur(nom);
                nomPos = "La porte d'or";
            } else if (random == 4) {
                role = new Pilote(nom);
                nomPos = "Heliport";
            } else if (random == 5) {
                role = new Plongeur(nom);
                nomPos = "La porte de fer";
            }
            if (i == 0) {
                av1 = role;
                av1.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av1);
                randomSauv1 = random;
                
            } else if (i == 1) {
                av2 = role;
                av2.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av2);
                randomSauv2 = random;
            } else if (i == 2) {
                av3 = role;
                av3.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av3);
                randomSauv3 = random;
            } else {
                av4 = role;
                av4.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av4);
            }
            setGrilleJeu(grilleJeu);
        }
    }
    
    public ArrayList<Aventurier> getJoueurs() {                                 //Permet d'obtenir la liste des joueurs
        return joueurs;
    }

    @Override                                                                   //Effectue un déplacement
    public void traiterAction(String nomJ, int x, int y, boolean depl) {
        System.out.println("Deplacement voulu : " + x + "-" + y);
        getAventurier(nomJ, joueurs).deplacementAssechage(x, y, depl);          //Deplace le joueur sur la position souhaitée
        setGrilleJeu(getAventurier(nomJ, joueurs).getGrilleAv());               //Met à jour les grilles du jeu
        nbAction++;
        System.out.println("Ici on a fait avec un boolean " + depl);
        jeu.repaint();
        if (nbAction < 3) {
            jeu.afficherPossible(joueurC.getTuilesPossibles(depl));
            gagnerTresorPossible();
            if (tresorRecup) {
                jeu.tresorPossible();
            }
        } else {
            jeu.finTourObligatoire();
        }
    }

    public void afficherTuilesPossibles(String nomJ, boolean depl) {            //Affiche les tuiles de déplacement possible
        afficherJoueurs(nomJ);                                                  //Affiche les joueurs présent sur la tuile concernée
        ArrayList<Tuile> tuilesPossibles = getAventurier(nomJ, joueurs).getTuilesPossibles(depl);//Affiche l'état de la tuile concernée
        for (Tuile tuile : tuilesPossibles) {
            System.out.println(tuile.getNom());
            ArrayList<Aventurier> joueurs = tuile.getJoueurs();
            System.out.println("Joueurs présents: ");
            for (Aventurier av : joueurs) {
                System.out.println(" -" + av.getNom());
            }
            System.out.println(tuile.getEtat());
            System.out.println(tuile.getX() + " - " + tuile.getY());
            System.out.println("------");
        }
    }

    public void afficherJoueurs(String nomJ) {                                  //Affiche les joueurs présent sur une tuile
        Aventurier aventurier = getAventurier(nomJ, joueurs);
        Tuile positionAventurier = aventurier.getPosition();
        ArrayList<Aventurier> listeJoueurs = positionAventurier.getJoueurs();
        System.out.println("Joueurs présents sur votre position: ");
        for (Aventurier av : listeJoueurs) {
            System.out.println(" -" + av.getNom());
        }
    }

    private Aventurier getAventurier(String nomAv, ArrayList<Aventurier> aventuriers) {
        int i = 0;
        while (nomAv != aventuriers.get(i).getNom() && i < aventuriers.size()) {
            System.out.println("i size :" + aventuriers.get(i).getNom());
            i++;
            System.out.println("i après :" +i);
        }
        
        if (nomAv == aventuriers.get(i).getNom()) {
            return aventuriers.get(i);
        } else {
            return null;
        }
    }
    
    @Override
    public ArrayList<String> getJoueurTuile(Tuile tuile) {         //Retourn une collection des Aventurier présent sur une tuile
        ArrayList<Aventurier> Aventurier = tuile.getJoueurs();
        ArrayList<String> Joueurs = new ArrayList<>();
        
        for (Aventurier j : Aventurier){
            if (j.getClass() == Explorateur.class){
                Joueurs.add("Expl");
            }
            if (j.getClass() == Ingenieur.class){
                Joueurs.add("Inge");
            }
            if (j.getClass() == Messager.class){
                Joueurs.add("Mess");
            }
            if (j.getClass() == Navigateur.class){
                Joueurs.add("Navi");
            }
            if (j.getClass() == Pilote.class){
                Joueurs.add("Pilo");
            }
            if (j.getClass() == Plongeur.class){
                Joueurs.add("Plon");
            }
        }
        return Joueurs;
    }
    
    public Aventurier getJoueurCourant(int jc) {
        return joueurs.get(jc);
    } // renvoi le joueur courant
    
    private static ArrayList<CarteDosOrange> melangerCartesOranges(ArrayList<CarteDosOrange> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    } // melange une liste de carte orange
    
    private static ArrayList<CarteInondation> melangerCartesInondation(ArrayList<CarteInondation> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }   //melange une liste de carte inondation
    
    private void créerTresors() {
        tresors = new ArrayList<>();
        tresorsGagnés = new ArrayList<>();
        tresorsCompare = new ArrayList<>();
        Tresor tresor1 = new Tresor("La Pierre sacrée");        // création des 4 tresors du jeu
        Tresor tresor2 = new Tresor("La Statue du zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal ardent");
        Tresor tresor4 = new Tresor("Le Calice de l'onde");
        tresors.add(tresor1);                                   // ajout des tresors dans la liste des tresors
        tresors.add(tresor2);
        tresors.add(tresor3);
        tresors.add(tresor4);
        tresorsCompare.add(tresor1);
        tresorsCompare.add(tresor2);
        tresorsCompare.add(tresor3);
        tresorsCompare.add(tresor4);
       for (int i = 0 ; i <= 5 ; i ++){
           for (int j = 0 ; j <= 5 ; j++){
               if (grilleJeu.getGrille()[i][j].getNom() == "Le temple du soleil" || grilleJeu.getGrille()[i][j].getNom() == "Le temple de la lune"){
                   grilleJeu.getGrille()[i][j].setTresor(tresors.get(0));
               } else if (grilleJeu.getGrille()[i][j].getNom() == "Le jardin des hurlements" || grilleJeu.getGrille()[i][j].getNom() == "Le jardin des murmures"){
                   grilleJeu.getGrille()[i][j].setTresor(tresors.get(1));
               } else if (grilleJeu.getGrille()[i][j].getNom() == "La caverne du brasier" || grilleJeu.getGrille()[i][j].getNom() == "La caverne des ombres"){
                   grilleJeu.getGrille()[i][j].setTresor(tresors.get(2));
               } else if (grilleJeu.getGrille()[i][j].getNom() == "Le palais de corail" || grilleJeu.getGrille()[i][j].getNom() == "Le palais des marrées"){
                   grilleJeu.getGrille()[i][j].setTresor(tresors.get(3));
               }
           }
       }
        setGrilleJeu(grilleJeu);
        
        
    }   // création des 4 tresors dans la liste "tresors"
    
    private void remplirPiocheOrange() {                          //Création de la pioche remplie de la totalité des cartes dos orange.
        piocheOrange = new ArrayList<>();
        for (int i =0; i < 5; i++){                             // ajout des 20 cartes tresors correspondant aux 4 tresors (5 carte pour chaque tresor)
            piocheOrange.add(new CarteTresor(tresors.get(0)));
            piocheOrange.add(new CarteTresor(tresors.get(1)));
            piocheOrange.add(new CarteTresor(tresors.get(2)));
            piocheOrange.add(new CarteTresor(tresors.get(3)));
        }
        for (int i = 0; i < 3; i++){                            // ajout des 3 cartes Montee des Eaux et 3 cartes Helicoptere
            piocheOrange.add(new CarteMonteeDesEaux());
            piocheOrange.add(new CarteHelicoptere());
        }
        for (int i = 0; i < 2; i++){                            // ajout des 2 cartes sac de sable
            piocheOrange.add(new CarteSacDeSable());
        }
    }   // création de la pioche des cartes oranges
    
    private void remplirPiocheInondation() {                      // création de la pioche de cartes inondation
        piocheInondation = new ArrayList<>();
        Tuile[][] grille = grilleJeu.getGrille();
        for (int i = 0; i <= 5 ; i ++){
            for (int j = 0 ; j<= 5; j++){
                if (grille[i][j].getNom() != "null" ){
                piocheInondation.add(new CarteInondation(grille[i][j]));        // pour chaque tuile de la grille, il éxiste une carte inondation correspondante
                }
            }
        }
        piocheInondation = melangerCartesInondation(piocheInondation);          // on melange la pioche des cartes inondation car les cartes etaitent triées dans l'ordre des tuiles    
    }   // création de la pioche des cartes inondation

    public int getRandom(int min , int max){                        
        return min + (int)(Math.random() * ((max - min) + 1));
    }  // renvoi un nombre aléatoire entre min et max
    
    public void distributionCartesOrangeDebut() {                   // distribution des cartes à tous les joueurs au début du jeu          
        
        for (int i = 0; i < nbJoueurs; i++){                        // boucle le me nombre de fois qu'il y à de joueurs
            for (int j = 1 ; j < 3; j++){                           // donne 2 cartes à chaque joueur
                int numRandom = getRandom(0, piocheOrange.size()-1);  
                if (piocheOrange.get(numRandom).getClass().equals(CarteMonteeDesEaux.class)){ // si la carte potentiellemnent donnée est une carte montée de eaux alors on re-boucle 
                    j--;   
                } else {
                    getJoueurCourant(i).addCarteMain(piocheOrange.get(numRandom));     // ajout de la carte dans la main du joueur
                    piocheOrange.remove(numRandom);                             // suppression de la carte de la pioche
                  }
            }
        }
    } // distribution des cartes a tous les joueurs
    
    public void afficherDonCartePossible() {
        System.out.println("Voici les cartes que vous pouvez donner : ");
        for (CarteDosOrange liste : joueurC.getMain()){
            if (liste.getClass().equals(CarteTresor.class)){
                System.out.print(" - Carte tresor : ");
                System.out.println(liste.getTresor().getNomTresor());
            }
        }
        
        System.out.println("------");
    }       // affiche si un don de carte est possible entre joueurs
    
    public Tresor gagnerTresorPossible(){
        int cartesTresorPierre = 0;
        int cartesTresorStatue = 0;
        int cartesTresorCristal = 0;
        int cartesTresorCalice = 0;
        
        if (!tresorsGagnés.contains(tresorsCompare.get(0))) {
            if (joueurC.getPosition().getNom() == "Le temple du soleil" || joueurC.getPosition().getNom() == "Le temple de la lune" ) { // si le joueur se trouve sur une case pour recuperer le tresor de la pierre sacrée     
                for (CarteDosOrange carte : joueurC.getMain()){
                    if (carte.getTresor() != null &&
                            carte.getTresor().getNomTresor() == "La Pierre sacrée"){ // on compte combien de carte tresor de la pierre sacrée il a dans la main
                        cartesTresorPierre++;
                    }
                }
                if (cartesTresorPierre > 3) {                                   // si il en en 4 ou plus , il peut gagner le tresor
                    setTresorRecup(true);

                    return tresors.get(0);
                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(1))) {
            if (joueurC.getPosition().getNom() == "Le jardin des hurlements" || joueurC.getPosition().getNom() == "Le jardin des murmures" ) { // si le joueur se trouve sur une case pour recuperer le tresor de la statue du zephyr
                for (CarteDosOrange carte : joueurC.getMain()){
                    if (carte.getTresor() != null && 
                            carte.getTresor().getNomTresor() == "La Statue du zephyr"){ // on compte combien de carte tresor de la statue du zephyr il a dans la main
                        cartesTresorStatue++;
                    }
                }
                if (cartesTresorStatue > 3) {                                    // si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(1);

                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(2))) {
            if (joueurC.getPosition().getNom() == "La caverne du brasier" || joueurC.getPosition().getNom() == "La caverne des ombres" ) { // si le joueur se trouve sur une case pour recuperer le tresor du cristal ardent
                for (CarteDosOrange carte : joueurC.getMain()){
                    if (carte.getTresor() != null && 
                            carte.getTresor().getNomTresor() == "Le cristal ardent"){ // on compte combien de carte tresor ddu cristal ardent il a dans la main
                        cartesTresorCristal++;
                    }
                }
                if (cartesTresorCristal > 3) {                                  // si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(2);

                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(3))) {
            if (joueurC.getPosition().getNom() == "Le palais de corail" || joueurC.getPosition().getNom() == "Le palais des marrées" ) { // si le joueur se trouve sur une case pour recuperer le tresor du calice de l'onde
                for (CarteDosOrange carte : joueurC.getMain()){
                    if (carte.getTresor() != null && 
                            carte.getTresor().getNomTresor() == "Le Calice de l'onde"){  // on compte combien de carte tresor du calcie de l'onde il a dans la main
                        cartesTresorCalice++;
                    }
                }
                if (cartesTresorCalice > 3) {                                       // si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(3);
                }
            }
        }
    setTresorRecup(false);    
    return null;
    }       // renvoi un tresor qui peut être gagné actuellement par le joueur courant
    
    public void gagnerTresor(){
            tresorsGagnés.add(gagnerTresorPossible());                              // on l'ajoute a la liste des trésors gagnés;
            
            tresors.remove(gagnerTresorPossible());
        }                   // ajout du tresor possible dans la liste des tresors récupérés
       
    public void piocherDeuxCartesOrange() {
         for (int i = 1; i < 3; i++){
            if (piocheOrange.size() == 0){                                 // a chaque fois on verifie si la pioche est vide, et si elle l'est
                for (CarteDosOrange carte : melangerCartesOranges(defausseOrange)){               // on parcours toute la defausse melangée
                    piocheOrange.add(carte);                                                      // on rempli la pioche.
                }
            defausseOrange.clear();                                                                 // on vide la defausse  
            }
            int numRandom = getRandom(0, piocheOrange.size()-1);                                 // au hasard
            if (piocheOrange.get(numRandom).getClass().equals(CarteMonteeDesEaux.class)) {     // si la carte est une carte montées des eaux
                defausseOrange.add(piocheOrange.get(numRandom));                               // on ajoute la carte dans la defausse orange
                piocheOrange.remove(piocheOrange.get(numRandom));                              //  on la supprime de la pioche
                echelle.incrementerCran();                                                     // on incrémente l'echelle
                System.out.println("Vous avez pioché une carte montée des eaux");
            } else {                                                                             // sinon
                joueurC.addCarteMain(piocheOrange.get(numRandom));                               // on ajoute la carte dans la main du joueur courant
                if (piocheOrange.get(numRandom).getClass().equals(CarteTresor.class)) {
                    System.out.println("Vous avez pioché une carte trésor : " + piocheOrange.get(numRandom).getTresor().getNomTresor());
                } else if ((piocheOrange.get(numRandom).getClass().equals(CarteHelicoptere.class)) || (piocheOrange.get(numRandom).getClass().equals(CarteSacDeSable.class))){
                    System.out.println("Vous avez pioché une " + piocheOrange.get(numRandom).getClass().getSimpleName());
                    piocheOrange.remove(piocheOrange.get(numRandom));                                // on la supprime de la pioche
                }
            }   
        }
    }// pioche 2 cartes oranges, les ajoutes dans la main du joueur courant (+ rempli la pioche si vide) + si carte piochées = montées des eaux, alors augmente le cran de l'echelle 
   
    public void piocherCartesInondation() {
        System.out.println(echelle.getNiveauEau());
        for (int i = 0 ; i < echelle.getNiveauEau(); i++){                  // on pioche le nombre de cartes = niveau d'eau
            if (piocheInondation.size() == 0){                              // si pioche vide
                for (CarteInondation carte : melangerCartesInondation(defausseInondation)) {     // on parcours toute la defausse melangée
                    piocheInondation.add(carte);                                                // on rempli la pioche
                }
            defausseInondation.clear();                                                         // on vide la defausse
            }
            int numRandom = getRandom(0, piocheInondation.size()-1);                              // au hasard
            
            if (piocheInondation.get(numRandom).getTuile().getEtat() == Utils.EtatTuile.ASSECHEE) {         // on regarde l'etat de la tuile correspondant à la carte choisie au hasard 
                piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.INONDEE);               // si la tuile est asséchée elle devient inondée
                defausseInondation.add(piocheInondation.get(numRandom));                            // puis on ajoute la carte dans la defausse
                piocheInondation.remove(piocheInondation.get(numRandom));                           // et on retire la carte de la pioche
            } else if (piocheInondation.get(numRandom).getTuile().getEtat() == Utils.EtatTuile.INONDEE) {
                piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.COULEE);             // si la tuile est inondée elle devient submergée
                piocheInondation.remove(piocheInondation.get(numRandom));                           // et on retire la carte inondation du jeu
            }
        }
    } // pioche nb de cartes inondation = niveau d'eau de l'echelle , gere pioche vide + change l'etat des tuiles
    
    public void initInondationDebut(){
        for (int i = 0 ; i < 6; i++){                  // on pioche 6 cartes
            int numRandom = getRandom(0, piocheInondation.size()-1);                              // au hasard
            
                piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.INONDEE);                   // la tuile  devient inondée
                defausseInondation.add(piocheInondation.get(numRandom));                            // puis on ajoute la carte dans la defausse
                piocheInondation.remove(piocheInondation.get(numRandom));                           // et on retire la carte de la pioche
             
        }
    }
    
    public void gestionFinJeu() {
        if (grilleJeu.trouverTuile("Heliport").getEtat() == Utils.EtatTuile.COULEE){           // fermer le jeu si l'héliport est submergé
            jeu.fermer();                                                        // si oui , fin du jeu
            popUp.fermer();                
            afficherInformation("Jeu terminé, Heliport submergé");                        
        }
        if (echelle.getNiveauEau()>5){                                                  // fermer le jeu si le niveau d'eau est critique
            jeu.fermer();                                                     // si oui , fin du jeu
            popUp.fermer();
            afficherInformation("Jeu terminé, Ile totalement sous les eaux");
        }
        
                
        Boolean bool = false;
        for (Tresor tresor: tresors){                                                               // pour chaque tresor
            for (Tuile tuile : grilleJeu.getTuilesTresor(tresors.get(tresors.indexOf(tresor)))){        // et pour chaque tuile permettant de récupérer ce tresor
                if (bool && tuile.getEtat() == Utils.EtatTuile.COULEE){                                     // verifier si la deuxieme tuile est submergée
                    jeu.fermer();                                                       // si oui , fin du jeu
                    popUp.fermer();
                    afficherInformation("Jeu terminé, trésor :" + tresor.getNomTresor() + " n'est plus récupérable");
                }
                if (tuile.getEtat() == Utils.EtatTuile.COULEE){                                             // vérifier si la 1° est submergée
                    bool = true;
                }
            }
            bool = false;
        }
        
        for (Aventurier av: joueurs){                                                           // pour chaque aventurier
            if (av.getPosition().getEtat() == Utils.EtatTuile.COULEE){                                  // si la case sur laquelle il se trouve est submergée
                if (av.getTuilesPossibles(true).size() == 0){                                   // et si aucun deplacement n'est possible
                    jeu.fermer();                                                               // fin du jeu
                    popUp.fermer();
                    afficherInformation("Jeu terminé, Un joueur est mort dans les abysses.");
                }
            }
        }
        // pas fini du tout
       
    } // gestion de la fin du jeu (lose ou win) (win pas encore fait)
    
    public void afficherMain(){
        System.out.println("Voici vos cartes :");
        for (CarteDosOrange carte : joueurC.getMain()){
            if (carte.getClass().equals(CarteTresor.class)){
                System.out.print(" - Carte tresor : ");
                System.out.println(carte.getTresor().getNomTresor());
            } else if (carte.getClass().equals(CarteHelicoptere.class)){
                System.out.println(" - Carte Helicoptère");
            } else if (carte.getClass().equals(CarteSacDeSable.class)){
                System.out.println(" - Carte Sac de sable");
            }
        }
    }
    
    @Override
    public void enleverCarteSurplus(CarteDosOrange carte){
        String nomCarte;
            if (carte.getClass().equals(CarteTresor.class)){
                nomCarte =carte.getTresor().getNomTresor() ;
            
            } else {
                nomCarte = carte.getClass().getSimpleName() ;
            
            }
        System.out.println("La carte : " + nomCarte  + " à été supprimée de la main");
        defausseOrange.add(carte);                          // ajoute la carte a la defausse
        joueurC.removeCarteMain(carte);                     // retire la carte de la main du joueur
    }  // ajoute la carte à la defausse orange et retire la carte de la main du joueur
    
    public void utiliserCarteSacDeSable(Tuile tuile){
        if (grilleJeu.trouverTuile(tuile.getNom()).getEtat() == Utils.EtatTuile.INONDEE){
            grilleJeu.trouverTuile(tuile.getNom()).setEtat(Utils.EtatTuile.ASSECHEE);
        } else {
            afficherInformation("La tuile ne peut pas être asséchée");
        }
    
    }  // la tuile donnée devient assechée
    
    public void utiliserCarteHelicoptere(Tuile tuile){
        if (tuile.getEtat() != Utils.EtatTuile.COULEE){                         // vérifie si la tuile cible est une tuile submergée
            for(Aventurier av : joueurC.getPosition().getJoueurs()){            // si non 
                tuile.addJoueur(av);                                            // alors on déplace tous les joueurs qui sont sur la me case qe le joueur courant sur la case cible
            }
        } else {
            afficherInformation("La tuile cible est submergée, utilisation hélicoptere impossible");
        
        }
    
    } // deplace tous les joueurs d'une case sur la tuile donnée
    
    public void tourDeJeu() {
        Pilote avP = new Pilote("");
        nbAction = 0;
        joueurC = getJoueurCourant(nbJ);
        System.out.println(joueurC.getNom());
              
        jeu.changeJoueurCourant(joueurC.getNom(), joueurC.getPion());
        if (joueurC.getMain().size() > 5) {
            popUp = new VuePopUp(this, joueurC.getMain());
            popUp.afficher();
        }
        
        if (joueurC.getClass().getSimpleName().equals("Pilote")) {
            avP = (Pilote) joueurC;
            avP.setPouvoirUtilise(false);
            joueurC = avP;
            System.out.println("Pouvoir remis à 0");
        }
        
        gagnerTresorPossible();
        if (tresorRecup) {
            jeu.tresorPossible();
        }
        
        jeu.debutTour();
        jeu.repaint();
        
        //afficherMain();
        Boolean bool;
        jeu.resetMainIHM();
        for (Aventurier av: joueurs){
            if (av == joueurC){
                bool = true;
           } 
            else {
                bool = false;
            }
            jeu.afficherMain(av.getMain(), bool, av.getNom(), av.getPion());
        }
        
        gestionFinJeu();
        System.out.println("Made by JACQUETCorp + Ugo le stagiaire ©");
        
    }
}
