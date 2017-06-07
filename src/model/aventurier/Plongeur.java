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
public class Plongeur extends Aventurier{
    
    public Plongeur(String nom) {
        super(nom);
    }
    
    @Override
    public ArrayList<Tuile> getTuilesPossibles(){           //Retourne une collection de tuiles sur lesquelles le déplacement est possible pour le plongeur
        int posX = getPosition().getX();
        int posY = getPosition().getY();
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        tuilesPossibles = super.getTuilesPossibles();
        
        if (tuiles[posX][posY-1].getEtat() == Etat.submergee){
            tuilesPossibles.add(tuiles[posX-1][posY]);
        }
        else if (tuiles[posX][posY+1].getEtat() == Etat.submergee) {
            tuilesPossibles.add(tuiles[posX+1][posY]);
        }
        else if (tuiles[posX-1][posY].getEtat() == Etat.submergee) {
            tuilesPossibles.add(tuiles[posX][posY+1]);
        }
        else if (tuiles[posX+1][posY].getEtat() == Etat.submergee) {
            tuilesPossibles.add(tuiles[posX][posY-1]);
        }
        return tuilesPossibles;
    }
    
}
