/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;
import java.util.ArrayList;
import java.util.Observable;
import view.VueBienvenue;
import view.VueParamJeu;
import view.VueRegles;
import javax.swing.JFrame;
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
        private static Controller c;
        private static boolean menu;
        private static int nbJoueurs;
        private static String nomJ1;
        private static String nomJ2;
        private static String nomJ3;
        private static String nomJ4;
        private static int difficulte;
        private static Grille grilleJeu;
        private static ArrayList<Aventurier> joueurs;
        
        
    
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        c= new Controller();
        menu = true;
        bienvenue = new VueBienvenue(c);
        paramJeu = new VueParamJeu(c);
        regles = new VueRegles(c);
        bienvenue.afficher();
        
        setGrilleJeu(new Grille());
        
        
    }



    public static void setGrilleJeu(Grille GrilleJeu) {
        grilleJeu = GrilleJeu;
        for (Aventurier av : joueurs){
            av.setGrille(GrilleJeu);
    }
        
    }

    
   

    @Override
    public void traiterMessage(Message msg) {
        if (menu = true) {
            if (msg.type == TypesMessage.ACTION_Jouer) {
                bienvenue.fermer();
                paramJeu.afficher();
            } else if (msg.type == TypesMessage.ACTION_Retour) {
                paramJeu.fermer();
                regles.fermer();
                bienvenue.afficher();
            } else if (msg.type == TypesMessage.ACTION_Valider) {
                menu = true;
                // Lancement de la partie
                paramJeu.fermer();
            } else if (msg.type == TypesMessage.ACTION_Regles) {
                bienvenue.fermer();
                regles.afficher();
                
            } else if (msg.type == TypesMessage.ACTION_Quitter) {
                bienvenue.fermer();
            }
        } else if (menu = false) {
            
            setGrilleJeu(new Grille());
        }
        
    }


    @Override
    public void getDonnees(int nbJoueurs, String nomJ1, String nomJ2, String nomJ3, String nomJ4, int difficulte) {
        this.nbJoueurs = nbJoueurs;
        this.nomJ1 = nomJ1;
        this.nomJ2 = nomJ2;
        if (nbJoueurs >= 3) {
            this.nomJ3 = nomJ3;
        }
        if (nbJoueurs >= 4) {
            this.nomJ4 = nomJ4;
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

}