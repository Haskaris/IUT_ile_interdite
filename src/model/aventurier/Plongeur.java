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
public class Plongeur extends Aventurier{
    
    public Plongeur(String nom) {
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
            if (posY > 0) {
                if (tuiles[posX][posY-1].getEtat() == Etat.submergee){
                    tuilesPossibles.add(tuiles[posX-1][posY]);
                }
            }
            if (posY < 5) {
                if (tuiles[posX][posY+1].getEtat() == Etat.submergee) {
                    tuilesPossibles.add(tuiles[posX+1][posY]);
                }
            }
            if (posX > 0) {
                if (tuiles[posX-1][posY].getEtat() == Etat.submergee) {
                    tuilesPossibles.add(tuiles[posX][posY+1]);
                }
            }
            if (posX < 5) {
                if (tuiles[posX+1][posY].getEtat() == Etat.submergee) {
                    tuilesPossibles.add(tuiles[posX][posY-1]);
                }
            }
        }
        return tuilesPossibles;
    }

    @Override
    public Pion getPion() {
        return Pion.VIOLET;
    }
    
}
