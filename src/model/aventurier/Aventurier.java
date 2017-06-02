/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.CarteDosOrange;
import model.Etat;
import model.Grille;
import model.Tuile;

/**
 *
 * @author reyneu
 */
public class Aventurier {
    private String nom;
    private Grille grille;
    private Tuile position;
    private CarteDosOrange main;
    
    Aventurier(String nom){
        setNom(nom);
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getNom(){
        return nom;
    }

   
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public ArrayList<Tuile> getTuilesPossibles(){
        int posX = getPosition().getX();
        int posY = getPosition().getY();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        
        if (tuiles[posX-1][posY].getEtat() == Etat.assechee || tuiles[posX-1][posY].getEtat() == Etat.inondee){
            tuilesPossibles.add(tuiles[posX-1][posY]);
        }
        else if (tuiles[posX+1][posY].getEtat() == Etat.assechee || tuiles[posX+1][posY].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX+1][posY]);
        }
        else if (tuiles[posX][posY+1].getEtat() == Etat.assechee || tuiles[posX][posY+1].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX][posY+1]);
        }
        else if (tuiles[posX][posY-1].getEtat() == Etat.assechee || tuiles[posX][posY-1].getEtat() == Etat.inondee) {
            tuilesPossibles.add(tuiles[posX][posY-1]);
        }
        return tuilesPossibles;
    }
    
    public void deplacement(String deplacement) {
        char x = deplacement.charAt(0);
        char y = deplacement.charAt(2);                    // récupération de x et y
    
        
   
    }

    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @return the grille
     */
    public Grille getGrilleAv() {
        return grille;
    }
  
    
    
    
   
   }
   
    

