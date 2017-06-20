package model.cartesOrange;

import model.Tresor;
import model.aventurier.Aventurier;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author reyneu
 */
public abstract class CarteDosOrange {
    private Aventurier joueur;

   /*public Aventurier getJoueur() {
        return joueur;
    }

    public void setJoueur(Aventurier joueur) {
        this.joueur = joueur;
    }*/
    
    public abstract Tresor getTresor();
}
