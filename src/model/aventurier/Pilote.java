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
public class Pilote extends Aventurier {
    
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
    public void deplacementAssechage(int x, int y, boolean depl) {
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList <>();
        
        tuilesPossibles = getTuilesPossibles(depl);
        
        boolean actionEff = false;
        
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
                        actionEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);
                    }
                }
                if (actionEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            } else if (depl == false) {
                
                int posX = getPosition().getX();
                int posY = getPosition().getY();
                Tuile[][] tuiles = getGrilleAv().getGrille();
                tuilesPossibles.clear();
                if (posX != 0) {
                    if (tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    }
                }
                if (posX != 5) {
                    if (tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY != 5) {
                    if (tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY != 0) {
                    if (tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
                if (tuiles[posX][posY].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY]);
                } 
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(x, y).setEtat(Etat.assechee);                        
                        actionEff = true;
                        System.out.println("La tuile " + position.getX() +", " +position.getY() + " à été asséchée");
                    }
                }
                if (actionEff == false){
                    System.out.println("La tuile " + position.getX() +", " +position.getY() + " n'à pas été asséchée");
                }
            }
    }
}
