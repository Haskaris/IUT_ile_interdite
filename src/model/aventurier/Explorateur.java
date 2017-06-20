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
            
            if ((posX != 1 && posY != 1) && (posX != 2 && posY != 1) && (posX != 1 && posY != 2)) {
                if (tuiles[posX-1][posY-1].getEtat() == Etat.assechee || tuiles[posX-1][posY-1].getEtat() == Etat.inondee){
                    tuilesPossibles.add(tuiles[posX-1][posY]);
                } 
            }
            if ((posX != 4 && posY != 4) && (posX != 3 && posY != 4) && (posX != 4 && posY != 3)) {
                if (tuiles[posX+1][posY+1].getEtat() == Etat.assechee || tuiles[posX+1][posY+1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX+1][posY]);
                }
            }
            if ((posX != 1 && posY != 4) && (posX != 1 && posY != 3) && (posX != 2 && posY != 4)) {
                if (tuiles[posX-1][posY+1].getEtat() == Etat.assechee || tuiles[posX-1][posY+1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY+1]);
                }
            }
            if ((posX != 4 && posY != 1) && (posX != 3 && posY != 1) && (posX != 4 && posY != 2)) {
                if (tuiles[posX+1][posY-1].getEtat() == Etat.assechee || tuiles[posX+1][posY-1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY-1]);
                }
            }
        } else {
            if ((posX != 1 && posY != 1) && (posX != 2 && posY != 1) && (posX != 1 && posY != 2)) {
                if (tuiles[posX-1][posY-1].getEtat() == Etat.assechee){
                    tuilesPossibles.add(tuiles[posX-1][posY]);
                } 
            }
            if ((posX != 4 && posY != 4) && (posX != 3 && posY != 4) && (posX != 4 && posY != 3)) {
                if (tuiles[posX+1][posY+1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX+1][posY]);
                }
            }
            if ((posX != 1 && posY != 4) && (posX != 1 && posY != 3) && (posX != 2 && posY != 4)) {
                if (tuiles[posX-1][posY+1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY+1]);
                }
            }
            if ((posX != 4 && posY != 1) && (posX != 3 && posY != 1) && (posX != 4 && posY != 2)) {
                if (tuiles[posX+1][posY-1].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY-1]);
                }
            }
        }
        
        return tuilesPossibles;
    }
}
