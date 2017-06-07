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
        
        if (tuiles[posX-1][posY-1].getEtat() == Etat.assechee || tuiles[posX-1][posY-1].getEtat() == Etat.inondee){
            tuilesPossibles.add(tuiles[posX-1][posY]);
        }
        else if (tuiles[posX+1][posY+1].getEtat() == Etat.assechee || tuiles[posX+1][posY+1].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX+1][posY]);
        }
        else if (tuiles[posX-1][posY+1].getEtat() == Etat.assechee || tuiles[posX-1][posY+1].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX][posY+1]);
        }
        else if (tuiles[posX+1][posY-1].getEtat() == Etat.assechee || tuiles[posX+1][posY-1].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX][posY-1]);
        }
        return tuilesPossibles;
    }
}
