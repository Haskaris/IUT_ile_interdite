
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author reyneu
 */


public class Grille {
    
    
    private final int ligne = 5;
    private final int colonne = 5;
    private Tuile[][] grille = new Tuile[6][6];
    
    private ArrayList<Tuile> tuilesInondees;
    
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
    
    
      public Grille(){
        setGrille();//Erreur
        tuilesInondees = new ArrayList<>();
        
    }
      
      
      public void setGrille(){
            tuileNulle.setEtat(Etat.nulle);
            grille [0][0] = tuileNulle;        // 1° ligne
            grille [0][1] = tuileNulle;
            grille [0][2] = tuile1;
            tuile2.setEtat(Etat.inondee);
            grille [0][3] = tuile2;
            grille [0][4] = tuileNulle;
            grille [0][5] = tuileNulle;//Erreur
            
            grille [1][0] = tuileNulle;        // 2° ligne
            grille [1][1] = tuile3;
            grille [1][2] = tuile4;
            grille [1][3] = tuile5;
            grille [1][4] = tuile6;
            grille [1][5] = tuileNulle;
            
            grille [2][0] = tuile7;            // 3 ° ligne
            grille [2][1] = tuile8;
            tuile9.setEtat(Etat.submergee);
            grille [2][2] = tuile9;
            grille [2][3] = tuile10;
            grille [2][4] = tuile11;
            grille [2][5] = tuile12;
            
            grille [3][0] = tuile13;            // 4 ° ligne
            tuile14.setEtat(Etat.inondee);
            grille [3][1] = tuile14;
            tuile15.setEtat(Etat.submergee);
            grille [3][2] = tuile15;
            tuile16.setEtat(Etat.inondee);
            grille [3][3] = tuile16;
            tuile17.setEtat(Etat.submergee);
            grille [3][4] = tuile17;
            tuile18.setEtat(Etat.inondee);
            grille [3][5] = tuile18;
          
            
            grille [4][0] = tuileNulle;            // 5 ° ligne
            grille [4][1] = tuile19;
            tuile20.setEtat(Etat.submergee);
            grille [4][2] = tuile20;
            grille [4][3] = tuile21;
            grille [4][4] = tuile22;
            grille [4][5] = tuileNulle;
            
            grille [5][0] = tuileNulle;            // 6 ° ligne
            grille [5][1] = tuileNulle;
            grille [5][2] = tuile23;
            tuile14.setEtat(Etat.inondee);
            grille [5][3] = tuile24;
            grille [5][4] = tuileNulle;
            grille [5][5] = tuileNulle;
            
            for (int i = 0 ; i <= 5 ; i++){
                for (int j =0 ; j <= 5 ; j++){
                    if (grille[i][j] != tuileNulle){
                        grille[i][j].setX(i);
                        grille[i][j].setY(j);
                    }
                }
            }
            
         
      }

    public Tuile[][] getGrille() {
        return grille;
    }

    
    public ArrayList<Tuile> getTuilesInondée() {
        for (int j = 0 ; j<6;j++){
            for (int i = 0; i < 6; i++){                //Parcours grille
                if (grille[i][j].getEtat() == Etat.inondee){
                    tuilesInondees.add(grille[i][j]);   // ajout de la tuil inondée dans la liste des tuiles inondées
                
                }
            
            }
        
        }
         
        return tuilesInondees;
    }
    
    public Tuile trouverTuile(int x, int y){
        Tuile[][] tuiles = getGrille();
        return tuiles[x][y];
    }
      
}
    
    

