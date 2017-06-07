/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.Etat;
import model.Tuile;

/**
 *
 * @author reyneu
 */
public class Pilote extends Aventurier{
    
    private boolean pouvoirUtilise = false;
    
    public Pilote(String nom) {
        super(nom);
    }

    /**
     * @return the pouvoirUtilise
     */
    public boolean isPouvoirUtilise() {
        return pouvoirUtilise;
    }

    /**
     * @param pouvoirUtilise the pouvoirUtilise to set
     */
    public void setPouvoirUtilise(boolean pouvoirUtilise) {
        this.pouvoirUtilise = pouvoirUtilise;
    }
    
    @Override
    public ArrayList<Tuile> getTuilesPossibles(boolean depl){
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        if (isPouvoirUtilise() == false){
            for(int i = 0; i < tuiles.length; i++){
                for(int j = 0; j < tuiles[i].length; j++){
                    if (tuiles[i][j].getEtat() == Etat.assechee || tuiles[i][j].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[i][j]);
                    }
                }
            }
        } else if (isPouvoirUtilise() == true) {
            tuilesPossibles = super.getTuilesPossibles(depl);
        }
        
        return tuilesPossibles;
    }
    
    @Override
    public void deplacementAssechage(String tuileChoix, boolean depl) {
        char charX = tuileChoix.charAt(0);
        char charY = tuileChoix.charAt(2);                    // récupération de x et y
    
        int x = Character.getNumericValue(charX);
        int y = Character.getNumericValue(charY);
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList <>();
        
        tuilesPossibles = getTuilesPossibles(depl);
        
        boolean deplacementEff = false;
        
            if (depl == true) {
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        if ((grille.trouverTuile(x, y) != grille.trouverTuile(position.getX(), position.getY() + 1)) 
                        && (grille.trouverTuile(x, y) != grille.trouverTuile(position.getX(), position.getY() - 1))
                        && (grille.trouverTuile(x, y) != grille.trouverTuile(position.getX() + 1, position.getY()))
                        && (grille.trouverTuile(x, y) != grille.trouverTuile(position.getX() - 1, position.getY()))) {
                            setPouvoirUtilise(true);
                        } 
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        deplacementEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);
                    }
                }
                if (deplacementEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            } else if (depl == false) {
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        deplacementEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);

                    }
                }
                if (deplacementEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            }
    }
}
