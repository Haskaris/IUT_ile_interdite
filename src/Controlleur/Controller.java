/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFrame;
import model.CarteDosOrange;
import model.CarteHelicoptere;
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
    private static Controller c;
    private static int nbJoueurs = 2;
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
    

    public static void main(String[] args) {
        
        c = new Controller();
        
        joueurs = new ArrayList<>();
        av1 = new Pilote(nomJ1);
        av2 = new Navigateur(nomJ2);
        joueurs.add(av1);
        joueurs.add(av2);
        
        bienvenue = new VueBienvenue(c);
        paramJeu = new VueParamJeu(c);
        regles = new VueRegles(c);
        vueAv1 = new VueAventurier(nomJ1, "av1", Color.blue, c);
        vueAv2 = new VueAventurier(nomJ2, "av2", Color.green, c);
        
        if (nbJoueurs >= 3) {
            av3 = new Plongeur(nomJ3);
            joueurs.add(av3);
            vueAv3 = new VueAventurier(nomJ3, "av3", Color.yellow, c);
            vueAv3.cacher();
            if (nbJoueurs == 4) {
                av4 = new Ingenieur(nomJ4);
                joueurs.add(av4);
                vueAv4 = new VueAventurier(nomJ4, "av4", Color.pink, c);
                vueAv4.cacher();
            }
        }
        
        setGrilleJeu(new Grille());  
        jeu = new VueJeu(c, grilleJeu);//Initialisation de la grille
        jeu.afficher();

        av1.setPosition(grilleJeu.trouverTuile(2, 2));                          //Initialisation de la position (temporaire) des joueurs
        av2.setPosition(grilleJeu.trouverTuile(3, 3));
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
            jeu.afficher();
            //paramJeu.afficher();
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
            joueurC.getTuilesPossibles(true);
            afficherTuilesPossibles(joueurC.getNom(), true);
            joueurC.deplacementAssechage(joueurC.getNom(), true);
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_DonnerCarte) {
            //////////////////////////////////////////////
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Assecher) {
            joueurC.getTuilesPossibles(false);
            afficherTuilesPossibles(joueurC.getNom(), false);
            joueurC.deplacementAssechage(joueurC.getNom(), false);
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Fin) {
            if (nbJ == nbJoueurs-1) {
                nbJ = 0;
            } else {
                nbJ++;
            }
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
        paramJeu.fermer();
        //tourDeJeu();
    }
    
    public ArrayList<Aventurier> getJoueurs() {                                 //Permet d'obtenir la liste des joueurs
        return joueurs;
    }

    @Override                                                                   //Effectue un déplacement
    public void traiterAction(Message msg, String nomJ, String positionDemandee, boolean depl) {
        System.out.println(nomJ);
        getAventurier(nomJ, joueurs).deplacementAssechage(positionDemandee, depl);  //Deplace le joueur sur la position souhaitée
        setGrilleJeu(getAventurier(nomJ, joueurs).getGrilleAv());               //Met à jour les grilles du jeu
        afficherTuilesPossibles(nomJ, depl);
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
        while (nomAv != aventuriers.get(i).getNom()) {
            i++;
        }
        if (nomAv == aventuriers.get(i).getNom()) {
            return aventuriers.get(i);
        } else {
            return null;
        }
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
    

    public void tourDeJeu(){///////////////////////////////////////////////////////////////////////////////////////
        int nbAction = 0;
        joueurC = getJoueurCourant(nbJ);
        VueAventurier vueCourante = vueAvC(nbJ);
        
        vueCourante.afficher();
        
        
        
        
        
    }
}
