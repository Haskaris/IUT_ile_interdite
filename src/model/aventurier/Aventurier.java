/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import model.CarteDosOrange;
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

   
   
    
}
