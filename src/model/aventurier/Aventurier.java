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
    
    public Aventurier(String nom){
        setNom(nom);
    }
    
    private void setNom(String nom){
        this.nom = nom;
    }
    
    public String getNom(){
        return nom;
    }

   
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public ArrayList<Tuile> getTuilesPossibles(boolean depl){
        int posX = getPosition().getX();
        int posY = getPosition().getY();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        
        if (depl == true) {
            if (tuiles[posX-1][posY].getEtat() == Etat.assechee || tuiles[posX-1][posY].getEtat() == Etat.inondee){
                tuilesPossibles.add(tuiles[posX-1][posY]);
            }
            if (tuiles[posX+1][posY].getEtat() == Etat.assechee || tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX+1][posY]);
            }
            if (tuiles[posX][posY+1].getEtat() == Etat.assechee || tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX][posY+1]);
            }
            if (tuiles[posX][posY-1].getEtat() == Etat.assechee || tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX][posY-1]);
            }
        } else if (depl == false) {
            if (tuiles[posX-1][posY].getEtat() == Etat.inondee){
                tuilesPossibles.add(tuiles[posX-1][posY]);
            }
            if (tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX+1][posY]);
            }
            if (tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX][posY+1]);
            }
            if (tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX][posY-1]);
            }
            if (tuiles[posX][posY].getEtat() == Etat.inondee) {
                tuilesPossibles.add(tuiles[posX][posY-1]);
            }   
        }
        return tuilesPossibles;
    }
    
    public void deplacementAssechage(String tuileChoix, boolean depl) {
        char charX = tuileChoix.charAt(0);
        char charY = tuileChoix.charAt(2);                    // récupération de x et y
    
        
        int x = Character.getNumericValue(charX);
        int y = Character.getNumericValue(charY);
        
        ArrayList<Tuile> tuilesPossibles = new ArrayList <>();
        
        tuilesPossibles = getTuilesPossibles(depl);
        
        boolean deplacementEff = false;
        
            if (depl == true) {
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        deplacementEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);

                    }
                }
                if (deplacementEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            } else if (depl == false) {
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        deplacementEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);

                    }
                }
                if (deplacementEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            }
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Tuile position) {
        this.position = position;
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
