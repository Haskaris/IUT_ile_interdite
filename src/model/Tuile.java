/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.aventurier.Aventurier;


/**
 *
 * @author reyneu
 */
public class Tuile {
    private String nom;
    private int x;
    private int y;
    private Etat etat;
    private ArrayList<Aventurier> joueurs;
    
    Tuile(String nom, int x, int y){
        setNom(nom);
        setX(x);
        setY(y);
        setEtat(Etat.assechee);
        joueurs = new ArrayList<>();
    }
    
    Tuile(String nom) {
        setNom(nom);
        setEtat(Etat.assechee);
        joueurs = new ArrayList<>();
        setX(0);
        setY(0);
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public void setEtat(Etat etat){
        this.etat = etat;
    }
    
    public Etat getEtat(){
        return etat;
    }
    
    public void addJoueurs(Aventurier joueur){
        this.joueurs.add(joueur);
    }
    
    public ArrayList<Aventurier> getJoueurs(){
        return joueurs;
    }
    
}
