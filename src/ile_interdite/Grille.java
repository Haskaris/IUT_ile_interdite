
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
            tab [0][0] = tuileNulle;        // 1° ligne
            tab [0][1] = tuileNulle;
            tab [0][2] = tuile1;
            tab [0][3] = tuile2;
            tab [0][4] = tuileNulle;
            tab [0][5] = tuileNulle;
            
            tab [0][0] = tuileNulle;        // 2° ligne
            tab [1][1] = tuile3;
            tab [2][2] = tuile4;
            tab [3][3] = tuile5;
            tab [4][4] = tuile6;
            tab [5][5] = tuileNulle;
            
            tab [2][0] = tuile7;            // 3 ° ligne
            tab [2][1] = tuile8;
            tab [2][2] = tuile9;
            tab [2][3] = tuile10;
            tab [2][4] = tuile11;
            tab [2][5] = tuile12;
            
            tab [3][0] = tuile13;            // 4 ° ligne
            tab [3][1] = tuile14;
            tab [3][2] = tuile15;
            tab [3][3] = tuile16;
            tab [3][4] = tuile17;
            tab [3][5] = tuile18;
          
            
            tab [4][0] = tuileNulle;            // 5 ° ligne
            tab [4][1] = tuile19;
            tab [4][2] = tuile20;
            tab [4][3] = tuile21;
            tab [4][4] = tuile22;
            tab [4][5] = tuileNulle;
            
            tab [5][0] = tuileNulle;            // 6 ° ligne
            tab [5][1] = tuileNulle;
            tab [5][2] = tuile23;
            tab [5][3] = tuile24;
            tab [5][4] = tuileNulle;
            tab [5][5] = tuileNulle;
        
        
      }

    /**
     * @return the tab
     */
    public Tuile[][] getTab() {
        return tab;
    }
      
    
}
    
    

