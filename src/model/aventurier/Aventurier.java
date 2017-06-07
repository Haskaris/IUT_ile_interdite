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
    protected Grille grille;
    protected Tuile position;
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
                if (posX != 0) {
                    if (tuiles[posX-1][posY].getEtat() == Etat.assechee || tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    } 
                }
                if (posX != 5) {
                    if (tuiles[posX+1][posY].getEtat() == Etat.assechee || tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY != 5) {
                    if (tuiles[posX][posY+1].getEtat() == Etat.assechee || tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY != 0) {
                    if (tuiles[posX][posY-1].getEtat() == Etat.assechee || tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
            } else if (depl == false) {
                if (posX != 0) {
                    if (tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    }
                }
                if (posX != 5) {
                    if (tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY != 5) {
                    if (tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY != 0) {
                    if (tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
                if (tuiles[posX][posY].getEtat() == Etat.inondee) {
                    tuilesPossibles.add(tuiles[posX][posY]);
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
        
        boolean actionEff = false;
        
            if (depl == true) {
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(position.getX(), position.getY()).supprJoueur(this);
                        this.setPosition(grille.trouverTuile(x ,y));
                        grille.trouverTuile(x, y).addJoueur(this);
                        actionEff = true;
                        System.out.println("Joueur déplacé en " + x + ", " + y);
                    }
                }
                if (actionEff == false){
                    System.out.println("Joueur non deplacé, il reste en " + position.getX() +", " +position.getY());
                }
            } else if (depl == false) {
                
                if (grille.trouverTuile(position.getX(), position.getY()).getEtat() == Etat.inondee){   //Ajout de la tuile ou le joueur se trouve dans la collection de tuile assechable si la tuile est innondée
                tuilesPossibles.add(grille.trouverTuile(position.getX(), position.getY()));
                }
                
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        
                        grille.trouverTuile(x, y).setEtat(Etat.assechee);                        
                        System.out.println("La tuile" + position.getX() +", " +position.getY() + " à été asséchée");
                    }
                }
                if (actionEff == false){
                    System.out.println("La tuile" + position.getX() +", " +position.getY() + " n'à pas été asséchée");
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
