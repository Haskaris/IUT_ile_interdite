/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.Etat;
import model.Tuile;
import util.Utils;
import util.Utils.Pion;

/**
 *
 * @author reyneu
 */
public class Explorateur extends Aventurier {

    public Explorateur(String nom) {
        super(nom);
    }
    
    @Override
    public ArrayList<Tuile> getTuilesPossibles(boolean depl){
        int posX = getPosition().getX();
        int posY = getPosition().getY();
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        tuilesPossibles = super.getTuilesPossibles(depl);
        if (depl) {
            if (posX > 0 && posY > 0) {
                if (tuiles[posX-1][posY-1].getNom() != "null" && 
                        (tuiles[posX-1][posY-1].getEtat() == Etat.assechee || tuiles[posX-1][posY-1].getEtat() == Etat.inondee)){
                    tuilesPossibles.add(tuiles[posX-1][posY-1]);
                }
            }
            if (posX < 5 && posY < 5) {
                if (tuiles[posX+1][posY+1].getNom() != "null" && 
                        (tuiles[posX+1][posY+1].getEtat() == Etat.assechee || tuiles[posX+1][posY+1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX+1][posY+1]);
                }
            }
            if (posX > 0 && posY < 5) {
                if (tuiles[posX-1][posY+1].getNom() != "null" &&
                        (tuiles[posX-1][posY+1].getEtat() == Etat.assechee || tuiles[posX-1][posY+1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX-1][posY+1]);
                }
            }
            if (posX < 5 && posY > 0) {
                if (tuiles[posX+1][posY-1].getNom() != "null" &&
                         (tuiles[posX+1][posY-1].getEtat() == Etat.assechee || tuiles[posX+1][posY-1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX+1][posY-1]);
                }
            }
        } else {
            if (posX > 0 && posY > 0) {
                if (tuiles[posX-1][posY-1].getNom() != "null" && (tuiles[posX-1][posY-1].getEtat() == Etat.assechee)){
                    tuilesPossibles.add(tuiles[posX-1][posY-1]);
                } 
            }
            if (posX < 5 && posY < 5) {
                if (tuiles[posX+1][posY+1].getNom() != "null" && (tuiles[posX+1][posY+1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX+1][posY+1]);
                }
            }
            if (posX > 0 && posY < 5) {
                if (tuiles[posX-1][posY+1].getNom() != "null" && (tuiles[posX-1][posY+1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX-1][posY+1]);
                }
            }
            if (posX < 5 && posY > 0) {
                if (tuiles[posX+1][posY-1].getNom() != "null" && (tuiles[posX+1][posY-1].getEtat() == Etat.inondee)) {
                    tuilesPossibles.add(tuiles[posX+1][posY-1]);
                }
            }
        }
        
        return tuilesPossibles;
    }

    @Override
    public Pion getPion() {
        return Pion.VERT;
    }
}
