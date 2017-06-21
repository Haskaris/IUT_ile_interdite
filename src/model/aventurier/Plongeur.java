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
        
        tuilesPossibles = super.getTuilesPossibles(depl);
        
        int k = 0, j = 1, w = 1;
        
        if (depl) {
            
            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour les placer dans tuilesTraversable                                
                while (j != 0) {
                    k = w - j;
                    j = 0;
                    
                    System.out.println("k : " + k);
                    
                    for (int i = k; i < w; i++) {
                        System.out.println("i: " + i);
                        System.out.println(tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getEtat());
                        System.out.println(tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getEtat());
                        System.out.println(tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getEtat());
                        System.out.println(tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getEtat());
                        
                        if (tuilesTraversable[i].getX() > 0) {
                            if ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getNom() != "null") && 
                                    ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                w++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1];
                            }
                        }

                        if (tuilesTraversable[i].getX() < 5) {
                            if ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getNom() != "null") && 
                                    ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1];
                            }
                        }

                        if (tuilesTraversable[i].getY() > 0) {
                            if ((tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getNom() != "null") && 
                                    ((tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()];
                            }
                        }

                        if (tuilesTraversable[i].getY() < 5) {
                            if ((tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getNom() != "null") && 
                                    ((tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.INONDEE)))) {
                                j++;
                                tuilesTraversable[w] = tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()];
                            }
                        }
                    }
                }
            
            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour placer les tuiles assechée ou innodée dans tuilesPossibles
            for (int i = 0; i < w; i++){
                if (tuilesTraversable[i].getX() > 0) {
                    if ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getNom() != "null") && 
                            ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                        tuilesPossibles.add(tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()-1]);
                    }
                }

                if (tuilesTraversable[i].getX() < 5) {
                    if ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getNom() != "null") && 
                            ((tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                        tuilesPossibles.add(tuiles[tuilesTraversable[i].getY()][tuilesTraversable[i].getX()+1]);
                    }
                }

                if (tuilesTraversable[i].getY() > 0) {
                    if ((tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getNom() != "null") && 
                            ((tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.INONDEE)))) {
                        tuilesPossibles.add(tuiles[tuilesTraversable[i].getY()-1][tuilesTraversable[i].getX()]);
                    }
                }

                if (tuilesTraversable[i].getY() < 5) {
                    if ((tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getNom() != "null") && 
                            ((tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()].getEtat().equals(EtatTuile.INONDEE)))) {
                        tuilesPossibles.add(tuiles[tuilesTraversable[i].getY()+1][tuilesTraversable[i].getX()]);
                    }
                }
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
    
}
