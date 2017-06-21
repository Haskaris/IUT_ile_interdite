/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.aventurier.Aventurier;
import util.Utils;


/**
 *
 * @author reyneu
 */
public class Tuile {
    private String nom;
    private int x;
    private int y;
    private Utils.EtatTuile etat;
    private ArrayList<Aventurier> joueurs;
    private Tresor tresor;
    
    Tuile(String nom, int x, int y){
        setNom(nom);
        setX(x);
        setY(y);
        setEtat(Utils.EtatTuile.ASSECHEE);
        joueurs = new ArrayList<>();
    }
    
    Tuile(String nom) {
        setNom(nom);
        setEtat(Utils.EtatTuile.ASSECHEE);
        joueurs = new ArrayList<>();
        setX(0);
        setY(0);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setEtat(Utils.EtatTuile etat){
        this.etat = etat;
    }
    
    public Utils.EtatTuile getEtat(){
        return etat;
    }
    
    public void addJoueur(Aventurier joueur){
        joueurs.add(joueur);
    }
    
    public ArrayList<Aventurier> getJoueurs(){
        return joueurs;
    }
    
    public void supprJoueur(Aventurier joueur) {
        joueurs.remove(joueur);
    }
    
    public Tresor getTresor() {
        return tresor;
    }
    
    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    
}
