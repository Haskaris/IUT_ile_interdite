
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
    private Tuile tuile3 = new Tuile("La caverne des ombres");
    private Tuile tuile4 = new Tuile("La porte de fer");
    private Tuile tuile5 = new Tuile("La porte d'or");
    private Tuile tuile6 = new Tuile("Les falaises de l'oubli");
    private Tuile tuile7 = new Tuile("Le palais de corail");
    private Tuile tuile8 = new Tuile("La porte d'argent");
    private Tuile tuile9 = new Tuile("Les dunes de l'illusion");
    private Tuile tuile10 = new Tuile("Heliport");
    private Tuile tuile11 = new Tuile("La porte de cuivre");
    private Tuile tuile12 = new Tuile("Le jardin des hurlements");
    private Tuile tuile13 = new Tuile("La forêt pourpre");
    private Tuile tuile14 = new Tuile("Le lagon perdu");
    private Tuile tuile15 = new Tuile("Le marais brumeux");
    private Tuile tuile16 = new Tuile("Observatoire");
    private Tuile tuile17 = new Tuile("Le rocher fantome");
    private Tuile tuile18 = new Tuile("La caverne du brasier");
    private Tuile tuile19 = new Tuile("Le temple du soleil");
    private Tuile tuile20 = new Tuile("Le temple de la lune");
    private Tuile tuile21 = new Tuile("Le palais des marrées");
    private Tuile tuile22 = new Tuile("Le val du crepuscule");
    private Tuile tuile23 = new Tuile("La tour du guets");
    private Tuile tuile24 = new Tuile("Le jardin des murmures");
    
    
    public Grille(){
        setGrille();
        tuilesInondees = new ArrayList<>();
    }
      
    public int getRandom(int min , int max){                        // renvoi un nombre aléatoire entre min et max
        return min + (int)(Math.random() * ((max - min) + 1));
    }
      
    public void setGrille(){
        int k;
        int t = 23;
        ArrayList<Tuile> tuiles = new ArrayList<>();
        tuiles.add(tuile1);
        tuiles.add(tuile2);
        tuiles.add(tuile3);
        tuiles.add(tuile4);
        tuiles.add(tuile5);
        tuiles.add(tuile6);
        tuiles.add(tuile7);
        tuiles.add(tuile8);
        tuiles.add(tuile9);
        tuiles.add(tuile10);
        tuiles.add(tuile11);
        tuiles.add(tuile12);
        tuiles.add(tuile13);
        tuiles.add(tuile14);
        tuiles.add(tuile15);
        tuiles.add(tuile16);
        tuiles.add(tuile17);
        tuiles.add(tuile18);
        tuiles.add(tuile19);
        tuiles.add(tuile20);
        tuiles.add(tuile21);
        tuiles.add(tuile22);
        tuiles.add(tuile23);
        tuiles.add(tuile24);
        
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (i == 0 && j > 1 && j < 4) {
                    k = getRandom(0, t);
                    grille[i][j] = tuiles.get(k);
                    tuiles.remove(k);
                    t--;
                } else if (i == 1 && j > 0 && j < 5) {
                    k = getRandom(0, t);
                    grille[i][j] = tuiles.get(k);
                    tuiles.remove(k);
                    t--;
                } else if (i == 4 && j > 0 && j < 5) {
                    k = getRandom(0, t);
                    grille[i][j] = tuiles.get(k);
                    tuiles.remove(k);
                    t--;
                } else if (i == 5 && j > 1 && j < 4) {
                    k = getRandom(0, t);
                    grille[i][j] = tuiles.get(k);
                    tuiles.remove(k);
                    t--;
                } else if (i == 2 || i == 3) {
                    k = getRandom(0, t);
                    grille[i][j] = tuiles.get(k);
                    tuiles.remove(k);
                    t--;
                } else {
                    grille[i][j] = tuileNulle;
                }
            }
        }
        
        for (int m = 0 ; m <= 5 ; m++){
            for (int n =0 ; n <= 5 ; n++){
                if (grille[m][n] != tuileNulle){
                    grille[m][n].setX(n);                                   //Met le X de la tuile dans la grille à la tuile
                    grille[m][n].setY(m);                                   //Met le Y de la tuile dans la grille à la tuile
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
    
    public Tuile trouverTuile(int x, int y) {
        Tuile[][] tuiles = getGrille();
        return tuiles[x][y];
    }
    
    public Tuile trouverTuile(String nomTuile) {
        for (int i = 0; i <= 5; i ++){
            for (int j = 0 ; j <= 5; j++){
                if (grille[i][j].getNom() == nomTuile){
                    return grille[i][j];
                }
            }
        }
        return grille[0][0];
    }  
    
    public ArrayList<Tuile> getTuilesTresor(Tresor tresor){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for (int i = 0; i <= 5 ; i++){
            for (int j =0; j <=5 ;j++){
                if (getGrille()[i][j].getTresor() == tresor)
                    tuiles.add(getGrille()[i][j]);
            
            }
        }
        
        return tuiles;
    
    
    }
    
    
}
    
    

