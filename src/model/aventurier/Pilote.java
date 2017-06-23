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
                 if (!super.getTuilesPossibles(true).contains(tuileTemp)){
                    setPouvoirUtilise(true);
                }
                for (Tuile tuile : tuilesPossibles) {
                    if (tuile.getX() == x & tuile.getY() == y) {
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        actionEff = true;
                    }
                }
            } else if (depl == false) {
                
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);                        
                        actionEff = true;
                    }
                }
                
            }
    }
    
    @Override
    public Pion getPion(){
        return Pion.BLEU;
    }
}
