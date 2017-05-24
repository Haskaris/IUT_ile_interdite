/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;
import java.util.Observable;
import view.VueBienvenue;
import view.VueParamJeu;
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
        private static Controller c;
        private static boolean menu;
    
    public static void main(String[] args) {
        // TODO code application logic here
        c= new Controller();
        menu = true;
        bienvenue = new VueBienvenue(c);
        paramJeu = new VueParamJeu(c);
        bienvenue.afficher();
        
        
    }

    @Override
    public void traiterMessage(Message msg) {
        if (menu = true) {
            if (msg.type == TypesMessage.ACTION_Jouer) {
                bienvenue.fermer();
                paramJeu.afficher();
            } else if (msg.type == TypesMessage.ACTION_Retour) {
                paramJeu.fermer();
                bienvenue.afficher();
            } else if (msg.type == TypesMessage.ACTION_Valider) {
                
            } else if (msg.type == TypesMessage.ACTION_Regles) {
                System.out.println("Regles");
            } else if (msg.type == TypesMessage.ACTION_Quitter) {
                bienvenue.fermer();
            }
        }
    }

}