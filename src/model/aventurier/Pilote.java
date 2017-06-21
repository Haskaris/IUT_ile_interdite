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
                        if (tuiles[i][j].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[i][j].getEtat() == Utils.EtatTuile.INONDEE) {
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
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList <>();
        ArrayList<Tuile> tuilesPossiblesAss = new ArrayList <>();
        
        tuilesPossibles = getTuilesPossibles(depl);
        //tuilesPossiblesAss = super.getTuilesPossibles(depl);
        
        boolean actionEff = false;
        
            if (depl == true) {                                                 //Si la fonction est utilisée pour un déplacement
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
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
                /*
                int posX = getPosition().getX();
                int posY = getPosition().getY();
                Tuile[][] tuiles = getGrilleAv().getGrille();
                if (posX > 0) {
                    if (tuiles[posX-1][posY].getNom() != "null" &&
                            tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossiblesAss.add(tuiles[posX-1][posY]);
                    }
                }
                if (posX < 5) {
                    if (tuiles[posX+1][posY].getNom() != "null" &&
                            tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossiblesAss.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY < 5) {
                    if (tuiles[posX][posY+1].getNom() != "null" &&
                            tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossiblesAss.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY > 0) {
                    if (tuiles[posX][posY-1].getNom() != "null" &&
                            tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                        tuilesPossiblesAss.add(tuiles[posX][posY-1]);
                    }
                }
                if (tuiles[posX][posY].getEtat() == Etat.inondee) {
                    tuilesPossiblesAss.add(tuiles[posX][posY]);
                } */
                
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
