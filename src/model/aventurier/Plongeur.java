/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
//import model.Etat;
import model.Tuile;
import util.Utils;
import util.Utils.EtatTuile;
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
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        Tuile[] tuilesTraversable = new Tuile[24];
        tuilesTraversable[0] = tuiles[getPosition().getX()][getPosition().getY()];
        Tuile[] tuilesPossibleTab = new Tuile[24];
        
        tuilesPossibles = super.getTuilesPossibles(depl);
        
        int k = 0, j = 1, w = 1, a = 0;
        
        if (depl) {
            
            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour les placer dans tuilesTraversable                                
                while (j != 0) {
                    k = w - j;
                    a = w;
                    j = 0;
                    
                    for (int i = k; i < a; i++) {
                        
                        if (tuilesTraversable[i].getY() > 0) {
                            if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getNom() != "null") { 
                                if    ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1]) == false) &&
                                    ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1];
                                w++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getY() < 5) {
                            if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getNom() != "null") {
                                 if   ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1]) == false) &&
                                    ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1];
                                w++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getX() > 0) {
                            if (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getNom() != "null") {
                                 if   ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()]) == false) &&
                                    ((tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()];
                                w++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getX() < 5) {
                            if (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getNom() != "null") { 
                                if    ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()]) == false) &&
                                    ((tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()];
                                w++;
                                }
                            }
                        }
                    }
                }
            
                

            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour placer les tuiles assechée ou innodée dans tuilesPossibles
            j = 0;
            for (int i = 0; i < w; i++){
                if (tuilesTraversable[i].getY() > 0) {
                    if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1]) == false) &&
                            ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[j] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1];
                                j++;
                            }
                    }
                }

                if (tuilesTraversable[i].getY() < 5) {
                    if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1]) == false) &&
                            ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[j] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1];
                                j++;
                            }
                    }
                }

                if (tuilesTraversable[i].getX() > 0) {
                    if (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()]) == false) &&
                            ((tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[j] = tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()];
                                j++;
                            }
                    }
                }

                if (tuilesTraversable[i].getX() < 5) {
                    if (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()]) == false) &&
                            ((tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[j] = tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()];
                                j++;
                            }
                    }
                }
            }
            
            a = j;
            tuilesPossibles.clear();
            for (int i = 0; i < a; i++){
                tuilesPossibles.add(tuilesPossibleTab[i]);
            }
            
        } else {
            tuilesPossibles = super.getTuilesPossibles(depl);
        }
        return tuilesPossibles;
    }

    @Override
    public Pion getPion() {
        return Pion.VIOLET;
    }
    
    private boolean isDansTableau(Tuile[] tableau, Tuile tuile){
        boolean bool = false;
        int i = 0;
        
        while (i < tableau.length && bool == false && tableau[i] != null){
            if (tableau[i] == tuile){
                bool = true;
            }
            i++;
        }
        return bool;
    }
    
}
