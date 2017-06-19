/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javax.swing.JFrame;
import model.CarteDosOrange;
import model.CarteHelicoptere;
import model.CarteInondation;
import model.CarteMonteeDesEaux;
import model.CarteSacDeSable;
import model.CarteTresor;
import model.aventurier.Aventurier;
import model.Grille;
import util.Message;
import util.TypesMessage;
import model.Grille;
import model.Tresor;
import model.Tuile;
import model.aventurier.*;
import static util.Utils.Pion.BLEU;
import view.*;
//package util;

/**
 *
 * @author perrier5
 */
public class Controller implements Observateur {

    //Initialisation des attributs
    private static VueBienvenue bienvenue;
    private static VueParamJeu paramJeu;
    private static VueRegles regles;
    private static VueJeu jeu;
    private static VueAventurier vueAv1, vueAv2, vueAv3, vueAv4;
    private static int nbJoueurs = 2;
    private static int nbAction = 0;
    private static String nomJ1 = "Ugo";
    private static String nomJ2 = "Mathis";
    private static String nomJ3 = "Andrea";
    private static String nomJ4 = "Thomas";
    private static int difficulte;
    private static int nbJ = 0;
    private static Grille grilleJeu;
    private static ArrayList<Aventurier> joueurs;
    private static Aventurier av1, av2, av3, av4, joueurC;
    private static ArrayList<CarteDosOrange> piocheOrange;
    private static ArrayList<CarteDosOrange> defausseOrange;
    private static ArrayList<Tresor> tresors;
    private static ArrayList<CarteInondation> piocheInondation;
    private static ArrayList<CarteInondation> defausseInondation;
    

    public static void main(String[] args) {
        
        //c = new Controller();
        
        joueurs = new ArrayList<>();
        
        
        setGrilleJeu(new Grille());                                             //Initialisation de la grille
        new Controller();
        bienvenue.afficher();
    }
    
    public Controller() {
        bienvenue = new VueBienvenue(this);
        paramJeu = new VueParamJeu(this);
        regles = new VueRegles(this);
        jeu = new VueJeu(this, grilleJeu);///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public static void setGrilleJeu(Grille GrilleJeu) {                         //Fonction permettant de lier les grilles (joueurs - controlleur) 
        grilleJeu = GrilleJeu;
        for (Aventurier av : joueurs) {
            av.setGrille(GrilleJeu);
        }
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
            /*for (Tuile tuile : joueurC.getTuilesPossibles(true)) {
                //jeu.
                
                ArrayList<Aventurier> joueurs = tuile.getJoueurs();
                System.out.println("Joueurs présents: ");
                for (Aventurier av : joueurs) {
                    System.out.println(" -" + av.getNom());
                }
                System.out.println(tuile.getEtat());
                System.out.println(tuile.getX() + " - " + tuile.getY());
                System.out.println("------");
            }*/
            
            if (nbAction < 3) {                                                 
                jeu.afficherPossible(joueurC.getTuilesPossibles(true));         //Affichage des tuiles où le deplacement est possible
                nbAction++;
                System.out.println("nb act : " + nbAction);
                setGrilleJeu(joueurC.getGrilleAv());
            } else {
                System.out.println("Impossible, toutes les actions sont utilisées");
            
            }
        }else if (msg.getTypeMessage() == TypesMessage.ACTION_DonnerCarte) {
            //////////////////////////////////////////////
            if (nbAction < 3) {
                //afficherTuilesPossibles(joueurC.getNom(), true);
                nbAction++;
                System.out.println("nb act : " + nbAction);
            } else {
                System.out.println("Impossible, toutes les actions sont utilisées");
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Assecher) {      //Affichage des tuiles où l'assechement est possible
            if (nbAction < 3) {
                jeu.afficherPossible(joueurC.getTuilesPossibles(false));
                nbAction++;
                setGrilleJeu(joueurC.getGrilleAv());
                System.out.println("nb act : " + nbAction);
            } else {
                System.out.println("Impossible, toutes les actions sont utilisées");
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Fin) {
            System.out.println("nbj : " + nbJ);
            System.out.println("nombrejoueur : " + nbJoueurs);
            if (nbJ == nbJoueurs-1) {
                nbJ = 0;
            } else {
                nbJ++;
            }
            tourDeJeu();
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
        this.difficulte = difficulte;
        
        initJoueurs(nbJoueurs, nomJ1, nomJ2, nomJ3, nomJ4);                     //Initialise le role des joueurs
        joueurs.add(av1);
        joueurs.add(av2);
        vueAv1 = new VueAventurier(nomJ1, "av1", Color.blue, this);
        vueAv2 = new VueAventurier(nomJ2, "av2", Color.green, this);
        if (nbJoueurs >= 3) {
            av3 = new Plongeur(nomJ3);
            joueurs.add(av3);
            vueAv3 = new VueAventurier(nomJ3, "av3", Color.yellow, this);
            vueAv3.cacher();
            if (nbJoueurs == 4) {
                av4 = new Ingenieur(nomJ4);
                joueurs.add(av4);
                vueAv4.cacher();
                vueAv4 = new VueAventurier(nomJ4, "av4", Color.pink, this);
            }
        }
        setGrilleJeu(grilleJeu);
        vueAv1.cacher();
        vueAv2.cacher();
        paramJeu.fermer();
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
        int max = 6;
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
            while ((random == 0 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) || 
                    (random == 1 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) || 
                    (random == 2 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) || 
                    (random == 3 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) || 
                    (random == 4 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) ||
                    (random == 5 && (random == randomSauv1 || random == randomSauv2 || random == randomSauv3))) {
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
            } else if (i == 1) {
                av2 = role;
                av2.setPosition(grilleJeu.trouverTuile(nomPos));
            } else if (i == 2) {
                av3 = role;
                av3.setPosition(grilleJeu.trouverTuile(nomPos));
            } else {
                av4 = role;
                av4.setPosition(grilleJeu.trouverTuile(nomPos));
            }
            
        }
    }
    
    public ArrayList<Aventurier> getJoueurs() {                                 //Permet d'obtenir la liste des joueurs
        return joueurs;
    }

    @Override                                                                   //Effectue un déplacement
    public void traiterAction(String nomJ, int x, int y, boolean depl) {

        getAventurier(nomJ, joueurs).deplacementAssechage(x, y, depl);  //Deplace le joueur sur la position souhaitée
        setGrilleJeu(getAventurier(nomJ, joueurs).getGrilleAv());               //Met à jour les grilles du jeu
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
        ArrayList<String> Joueurs = null;
        
        for (Aventurier j : Aventurier){
            if (j.getClass() == Explorateur.class){
                Joueurs.add("Expl");
            }
            if (j.getClass() == Ingenieur.class){
                Joueurs.add("Inge");
            }
            if (j.getClass() == Messager.class){
                Joueurs.add("Messa");
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
    
    
    public VueAventurier vueAvC(int nb) {
        if (nb == 0) {
            return vueAv1;
        } else if (nb == 1) {
            return vueAv2;
        } else if (nb == 2) {
            return vueAv3;
        } else {
            return vueAv4;
        }
    }
    
    public Aventurier getJoueurCourant(int jc) {
        return joueurs.get(jc);
    }
    
    public void créerTresors(){
        tresors = new ArrayList<>();
        Tresor tresor1 = new Tresor("La Pierre sacrée");        // création des 4 tresors du jeu
        Tresor tresor2 = new Tresor("La Statue du zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal ardent");
        Tresor tresor4 = new Tresor("Le calice de l'onde");
        tresors.add(tresor1);                                   // ajout des tresors dans la liste des tresors
        tresors.add(tresor2);
        tresors.add(tresor3);
        tresors.add(tresor4);
    }
    
    public void remplirPiocheOrange(){                          //Création de la pioche remplie de la totalité des cartes dos orange.
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
        
        
    }
    
    public void remplirPiocheInondation(){
        Tuile[][] grille = grilleJeu.getGrille();
        for (int i =0; i < 6 ; i ++){
            for (int j =0 ; j< 6; j++){
                piocheInondation.add(new CarteInondation(grille[i][j]));
            }
        }
    }

    public int getRandom(int min , int max){                        // renvoi un nombre aléatoire entre min et max
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    public void distributionCartesOrangeDebut(){                    // distribution des cartes à tous les joueurs au début du jeu          
        
        for (int i = 0; i < nbJoueurs; i++){                        // boucle le me nombre de fois qu'il y à de joueurs
            for (int j = 1 ; j < 3; j++){                           // donne 2 cartes à chaque joueur
                int numRandom = getRandom(0, piocheOrange.size());  
                if (piocheOrange.get(numRandom).getClass().equals(CarteMonteeDesEaux.class)){ // si la carte potentiellemnent donnée est une carte montée de eaux alors on re-boucle 
                    j--;   
                } else {
                    getJoueurCourant(i).addCarteMain(piocheOrange.get(numRandom));     // ajout de la carte dans la main du joueur
                    piocheOrange.remove(numRandom);                             // suppression de la carte de la pioche
                  }
            }
        }
    }

    public void tourDeJeu(){///////////////////////////////////////////////////////////////////////////////////////
        vueAv1.cacher();
        vueAv2.cacher();
        if (nbJoueurs >= 3) {
            vueAv3.cacher();
            if (nbJoueurs == 4) {
                vueAv4.cacher();
            }
        }
        nbAction = 0;
        joueurC = getJoueurCourant(nbJ);
        jeu.setNom(joueurC.getNom());
        VueAventurier vueCourante = vueAvC(nbJ);
        vueCourante.afficher();
        
    }
}
