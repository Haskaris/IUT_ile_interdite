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
    private final int tableau [] [] = { {0,1,2,3,4,5},{0,1,2,3,4,5} };
    
      
    private Tuile tuile1 = new Tuile("Le pont des abimes", 0, 2);
    private Tuile tuile2 = new Tuile("La porte de bronze", 0, 3);
    private Tuile tuile3 = new Tuile ("La caverne des ombres", 1, 1);
    private Tuile tuile4 = new Tuile ("La porte de fer", 1, 2);
    private Tuile tuile5 = new Tuile ("La porte d'or", 1,  3);
    private Tuile tuile6 = new Tuile ("Les falaises de l'oubli", 1, 4);
    private Tuile tuile7 = new Tuile ("Le palais de corail", 2, 0);
    private Tuile tuile8 = new Tuile ("La porte d'argent", 2, 1);
    private Tuile tuile9 = new Tuile ("Les dunes de l'illusion", 2, 2);
    private Tuile tuile10 = new Tuile ("Heliport", 2, 3);
    private Tuile tuile11 = new Tuile ("La porte de cuivre", 2, 4);
    private Tuile tuile12 = new Tuile ("Les jardins des hurlements", 2, 5);
    private Tuile tuile13 = new Tuile ("La forêt pourpre", 3, 0);
    private Tuile tuile14 = new Tuile ("Le lagon perdu", 3, 1);
    private Tuile tuile15 = new Tuile ("Le marais brumeux", 3, 2);
    private Tuile tuile16 = new Tuile ("Observatoire", 3, 3);
    private Tuile tuile17 = new Tuile ("Le rocher fantome", 3, 4);
    private Tuile tuile18 = new Tuile ("La caverne du brasier", 3, 5);
    private Tuile tuile19 = new Tuile ("Le temple du soleil", 4, 1);
    private Tuile tuile20 = new Tuile ("Le temple de la lune", 4, 2);
    private Tuile tuile21 = new Tuile ("Le palais des marrées", 4, 3);
    private Tuile tuile22 = new Tuile ("Le val du crepuscule", 4, 4);
    private Tuile tuile23 = new Tuile ("La tour du guets", 5, 2);
    private Tuile tuile24 = new Tuile ("Le jardin des murmures", 5, 3);
    
    
      Grille(){ 
        setTuiles();
        
        
        
    }
      
      public void setTuiles(){
      }
    
}
    
    

