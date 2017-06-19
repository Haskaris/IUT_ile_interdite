package model;

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

    
    
    /**
     * @return the joueur
     */
    public Aventurier getJoueur() {
        return joueur;
    }

    /**
     * @param joueur the joueur to set
     */
    public void setJoueur(Aventurier joueur) {
        this.joueur = joueur;
    }
    
    public abstract Tresor getTresor();
}
