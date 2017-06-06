/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import view.VueBienvenue;
import view.VueParamJeu;
import view.VueRegles;
import javax.swing.JFrame;
import model.aventurier.Aventurier;
import model.Grille;
import util.Message;
import util.TypesMessage;
import model.Grille;
import model.Tuile;
import view.VueAventurier;
//package util;

/**
 *
 * @author perrier5
 */
public class Controller implements Observateur {

    //private final VueBienvenue vuebienvenue; = new VueBienvenue(this);
    //private final VueAventurier vueaventurier = new VueAventurier();
    
    /**
     * @param args the command line arguments
     */
    
        private static VueBienvenue bienvenue;
        private static VueParamJeu paramJeu;
        private static VueRegles regles;
        private static VueAventurier vueAv1, vueAv2, vueAv3, vueAv4;
        private static Controller c;
        private static boolean menu;
        private static int nbJoueurs = 2;
        private static String nomJ1 = "Ugo";
        private static String nomJ2 = "Mathis";
        private static String nomJ3 = "Andrea";
        private static String nomJ4 = "Thomas";
        private static int difficulte;
        private static Grille grilleJeu;
        private static ArrayList<Aventurier> joueurs;
        private static Aventurier av1, av2, av3, av4;
                
    
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        joueurs = new ArrayList<>();
        c= new Controller();
        menu = false;
        bienvenue = new VueBienvenue(c);
        paramJeu = new VueParamJeu(c);
        regles = new VueRegles(c);
        Grille grilleJeu = new Grille();
        av1 = new Aventurier(nomJ1);
        av2 = new Aventurier(nomJ2);
        joueurs.add(av1);
        joueurs.add(av2);
        vueAv1 = new VueAventurier(nomJ1, "av1", Color.blue, c);
        vueAv2 = new VueAventurier(nomJ2, "av2", Color.green, c);
        if (nbJoueurs >= 3) {
            av3 = new Aventurier(nomJ3);
            joueurs.add(av3);
            vueAv3 = new VueAventurier(nomJ3, "av3", Color.yellow, c);
            if (nbJoueurs == 4) {
                av4 = new Aventurier(nomJ4);
                joueurs.add(av4);
                vueAv4 = new VueAventurier(nomJ4, "av4", Color.pink, c);
            }
        }
        setGrilleJeu(grilleJeu);
        
        av1.setPosition(grilleJeu.trouverTuile(2, 2));
        av2.setPosition(grilleJeu.trouverTuile(3, 3));
        
//bienvenue.afficher();
        
        
    }

    public static void setGrilleJeu(Grille GrilleJeu) {
        grilleJeu = GrilleJeu;
        for (Aventurier av : joueurs){        
            av.setGrille(GrilleJeu);
        }
        
    }

    
   

    @Override
    public void traiterMessage(Message msg) {
        if (menu == true) {
            if (msg.getTypeMessage() == TypesMessage.ACTION_Jouer) {
                bienvenue.fermer();
                paramJeu.afficher();
            } else if (msg.getTypeMessage() == TypesMessage.ACTION_Retour) {
                paramJeu.fermer();
                regles.fermer();
                bienvenue.afficher();
            } else if (msg.getTypeMessage() == TypesMessage.ACTION_Valider) {
                menu = false;
                // Lancement de la partie
                paramJeu.fermer();
            } else if (msg.getTypeMessage() == TypesMessage.ACTION_Regles) {
                bienvenue.fermer();
                regles.afficher();
                
            } else if (msg.getTypeMessage() == TypesMessage.ACTION_Quitter) {
                bienvenue.fermer();
            }
        } else if (menu == false) {
            if (msg.getTypeMessage() == TypesMessage.ACTION_Aller){
                //System.out.println(msg.getClass());
                
                //vueAv.getJoueur();
                //joueurs.get(nbJoueurs)
            } else if (msg.getTypeMessage() == TypesMessage.ACTION_Assecher) {
                //
                //
            }
        }
        
    }


    @Override
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
    }

    /**
     * @return the joueurs
     */
    public ArrayList<Aventurier> getJoueurs() {
        return joueurs;
    }

   
    public void setJoueurs(ArrayList<Aventurier> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public void traiterDeplacement(Message msg, String nomJ, String positionDemandee) {
        System.out.println(nomJ);
        getAventurier(nomJ, joueurs).deplacement(positionDemandee);
        setGrilleJeu(getAventurier(nomJ, joueurs).getGrilleAv());
        afficherDeplacementPossible(nomJ);
    }
    
    public void afficherDeplacementPossible(String nomJ) {
        ArrayList<Tuile> tuilesPossibles = getAventurier(nomJ, joueurs).getTuilesPossibles();
        for (Tuile tuile: tuilesPossibles){
            System.out.println(tuile.getNom());
            ArrayList<Aventurier> joueurs = tuile.getJoueurs();
            System.out.println("Joueurs présents: ");
            for (Aventurier av: joueurs){
                System.out.println("  " + av.getNom());
            }
            System.out.println(tuile.getEtat());
            System.out.println(tuile.getX() + " - " + tuile.getY());
            System.out.println("------");
        }
    }
    
    public ArrayList<Aventurier> getAventuriers() {
	return joueurs;
    }

    private Aventurier getAventurier(String nomAv, ArrayList<Aventurier> aventuriers){
        int i = 0;
	while (nomAv != aventuriers.get(i).getNom()){
            i++;
        }
        if (nomAv == aventuriers.get(i).getNom()){
            return aventuriers.get(i);
        }
        else {
            return null;
        }
    }

}
