
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;

/**
 *
 * @author reyneu
 */


public class Grille {
    
    
    private final int ligne = 6;
    private final int colonne = 6;
    private Tuile tab [][] = new Tuile[6][6];
    
    private Tuile tuileNulle = new Tuile("null");
    private Tuile tuile1 = new Tuile("Le pont des abimes");
    private Tuile tuile2 = new Tuile("La porte de bronze");
    private Tuile tuile3 = new Tuile ("La caverne des ombres");
    private Tuile tuile4 = new Tuile ("La porte de fer");
    private Tuile tuile5 = new Tuile ("La porte d'or");
    private Tuile tuile6 = new Tuile ("Les falaises de l'oubli");
    private Tuile tuile7 = new Tuile ("Le palais de corail");
    private Tuile tuile8 = new Tuile ("La porte d'argent");
    private Tuile tuile9 = new Tuile ("Les dunes de l'illusion");
    private Tuile tuile10 = new Tuile ("Heliport");
    private Tuile tuile11 = new Tuile ("La porte de cuivre");
    private Tuile tuile12 = new Tuile ("Les jardins des hurlements");
    private Tuile tuile13 = new Tuile ("La forêt pourpre");
    private Tuile tuile14 = new Tuile ("Le lagon perdu");
    private Tuile tuile15 = new Tuile ("Le marais brumeux");
    private Tuile tuile16 = new Tuile ("Observatoire");
    private Tuile tuile17 = new Tuile ("Le rocher fantome");
    private Tuile tuile18 = new Tuile ("La caverne du brasier");
    private Tuile tuile19 = new Tuile ("Le temple du soleil");
    private Tuile tuile20 = new Tuile ("Le temple de la lune");
    private Tuile tuile21 = new Tuile ("Le palais des marrées");
    private Tuile tuile22 = new Tuile ("Le val du crepuscule");
    private Tuile tuile23 = new Tuile ("La tour du guets");
    private Tuile tuile24 = new Tuile ("Le jardin des murmures");
    
    
      Grille(){
        setTab();
        
    }
      
      
      public void setTab(){
        tab [0][0] = tuileNulle;
      }
      
    
}
    
    

