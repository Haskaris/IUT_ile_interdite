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
        
        Tuile[][] tuiles = getGrilleAv().getGrille();                           //tableau double dimension pour la grille
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();                   //arrayList a retourner
        
        Tuile[] tuilesTraversable = new Tuile[24];                              //tableau pour les tuile qui peuvent etre traverser (tuiles innondee et coulle)
        tuilesTraversable[0] = tuiles[getPosition().getX()][getPosition().getY()];  //la premiere valeur du tableau tuilesTraversable est la tuile du plongeur
        
        Tuile[] tuilesPossibleTab = new Tuile[24];                              //tableau des tuile possible (permet de verifier qu'on n'y rentre pas deux fois la meme tuile
        
        int maxNonTraite = 0, traite = 1, maxLength = 1, max = 0;
        
        
        if (depl) {     //si c'est un déplacement
            
            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour les placer dans tuilesTraversable                                
                while (traite != 0) {                                           //tant qu'on ajoute des tuiles dans tuilesTraversable
                    maxNonTraite = maxLength - traite;
                    max = maxLength;
                    traite = 0;
                    
                    for (int i = maxNonTraite; i < max; i++) {                  //pour toutes les tuiles non traitée de tuilesTraversable
                        
                        if (tuilesTraversable[i].getY() > 0) {
                            if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getNom() != "null") { 
                                if    ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1]) == false) &&   //si la tuiles n'est pas deja dans le tableau
                                    ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                                traite++;
                                tuilesTraversable[maxLength] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1];          //on ajoute la tuile dans tuilesTraversable
                                maxLength++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getY() < 5) {
                            if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getNom() != "null") {
                                 if   ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1]) == false) &&   //si la tuiles n'est pas deja dans le tableau
                                    ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                                traite++;
                                tuilesTraversable[maxLength] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1];          //on ajoute la tuile dans tuilesTraversable
                                maxLength++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getX() > 0) {
                            if (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getNom() != "null") {
                                 if   ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()]) == false) &&   //si la tuiles n'est pas deja dans le tableau
                                    ((tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                traite++;
                                tuilesTraversable[maxLength] = tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()];          //on ajoute la tuile dans tuilesTraversable
                                maxLength++;
                                }
                            }
                        }

                        if (tuilesTraversable[i].getX() < 5) {
                            if (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getNom() != "null") { 
                                if    ((isDansTableau(tuilesTraversable, tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()]) == false) &&   //si la tuiles n'est pas deja dans le tableau
                                    ((tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.COULEE)) || 
                                    (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                traite++;
                                tuilesTraversable[maxLength] = tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()];          //on ajoute la tuile dans tuilesTraversable
                                maxLength++;
                                }
                            }
                        }
                    }
                }
            
                

            //parcours toutes les tuiles innondée ou submergée accessible par le plongeur pour placer les tuiles assechée ou innodée dans tuilesPossibles
            traite = 0;
            for (int i = 0; i < maxLength; i++){        //on parcours tuilesTraversable
                if (tuilesTraversable[i].getY() > 0) {
                    if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1]) == false) &&       //si la tuiles n'est pas deja dans le tableau
                            ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[traite] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()-1];                 //on ajoute la tuile dans tuilePossibleTab
                                traite++;
                            }
                    }
                }

                if (tuilesTraversable[i].getY() < 5) {
                    if (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1]) == false) &&       //si la tuiles n'est pas deja dans le tableau
                            ((tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[traite] = tuiles[tuilesTraversable[i].getX()][tuilesTraversable[i].getY()+1];                 //on ajoute la tuile dans tuilePossibleTab
                                traite++;
                            }
                    }
                }

                if (tuilesTraversable[i].getX() > 0) {
                    if (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()]) == false) &&       //si la tuiles n'est pas deja dans le tableau
                            ((tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[traite] = tuiles[tuilesTraversable[i].getX()-1][tuilesTraversable[i].getY()];                 //on ajoute la tuile dans tuilePossibleTab
                                traite++;
                            }
                    }
                }

                if (tuilesTraversable[i].getX() < 5) {
                    if (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getNom() != "null") {
                            if    ((isDansTableau(tuilesPossibleTab, tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()]) == false) &&       //si la tuiles n'est pas deja dans le tableau
                            ((tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.ASSECHEE)) || 
                            (tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()].getEtat().equals(EtatTuile.INONDEE)))) {
                                
                                tuilesPossibleTab[traite] = tuiles[tuilesTraversable[i].getX()+1][tuilesTraversable[i].getY()];                 //on ajoute la tuile dans tuilePossibleTab
                                traite++;
                            }
                    }
                }
            }
            
            max = traite;
            for (int i = 0; i < max; i++){      //on parcours tuilePossibleTab
                if (tuilesPossibleTab[i] != tuiles[getPosition().getX()][getPosition().getY()]) {  //si ce n'est pas la tuile du joueur
                    
                tuilesPossibles.add(tuilesPossibleTab[i]);      //on ajoute les tuile de tuilePossibleTab dans tuilesPossible
                
                }
            }
            
        } else {
            tuilesPossibles = super.getTuilesPossibles(depl);       //si c'est un assechement, on recupère les tuile a assecher de base
        }
        return tuilesPossibles;                 //retourne l'arrayList tuilesPossible
    }

    @Override
    public Pion getPion() {
        return Pion.VIOLET;
    }
    
    private boolean isDansTableau(Tuile[] tableau, Tuile tuile){        //vrai si la tuile se trouve dans le tableau
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
