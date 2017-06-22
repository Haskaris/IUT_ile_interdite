/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.Tuile;
import util.Utils;
import util.Utils.Pion;

/**
 *
 * @author reyneu
 */
public class Pilote extends Aventurier {
    
    private boolean pouvoirUtilise = false;
    
    public Pilote(String nom) {
        super(nom);
    }
    
    public boolean isPouvoirUtilise() {
        return pouvoirUtilise;
    }
    
    public void setPouvoirUtilise(boolean pouvoirUtilise) {
        this.pouvoirUtilise = pouvoirUtilise;
    }
    
    @Override
    public ArrayList<Tuile> getTuilesPossibles(boolean depl){
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        if (depl) {
            if (isPouvoirUtilise() == false){
                for(int i = 0; i < tuiles.length; i++){
                    for(int j = 0; j < tuiles[i].length; j++){
                        if (tuiles[i][j] != this.position && tuiles[i][j].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[i][j].getEtat() == Utils.EtatTuile.INONDEE) {
                            tuilesPossibles.add(tuiles[i][j]);
                        }
                    }
                }
            } else if (isPouvoirUtilise() == true) {
                tuilesPossibles = super.getTuilesPossibles(depl);
            }
        } else {
            tuilesPossibles = super.getTuilesPossibles(depl);
        }
        return tuilesPossibles;
    }
    
    @Override
    public void deplacementAssechage(int x, int y, boolean depl) {
        Tuile tuileTemp = grille.trouverTuile(x, y);
        ArrayList<Tuile> tuilesPossibles = new ArrayList <>();
        
        tuilesPossibles = getTuilesPossibles(depl);
        
        boolean actionEff = false;
        
            if (depl == true) {                                                 //Si la fonction est utilisée pour un déplacement
                if (super.getTuilesPossibles(true).contains(tuileTemp)) {
                    setPouvoirUtilise(false);
                    System.out.println("Pouvoir non utilisé");
                } else {
                    setPouvoirUtilise(true);
                    System.out.println("Pouvoir utilisé");
                }
                for (Tuile tuile : tuilesPossibles) {
                    if (tuile.getX() == x & tuile.getY() == y) {
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        actionEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);
                    }
                }
                if (actionEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            } else if (depl == false) {
                
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);                        
                        actionEff = true;
                        System.out.println("La tuile " + position.getX() +", " +position.getY() + " à été asséchée");
                    }
                }
                
                if (actionEff == false){
                    System.out.println("La tuile " + position.getX() +", " +position.getY() + " n'à pas été asséchée");
                }
            }
    }
    
    @Override
    public Pion getPion(){
        return Pion.BLEU;
    }
}
