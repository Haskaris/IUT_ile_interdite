/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.cartesOrange.CarteDosOrange;
import model.Grille; 
import model.Tuile;
import util.Utils;
import util.Utils.Pion;

/**
 *
 * @author reyneu
 */
public abstract class Aventurier {
    private String nom;
    protected Grille grille;
    protected Tuile position;
    private ArrayList<CarteDosOrange> main;
    
    public Aventurier(String nom){
        main = new ArrayList<>();
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
        
            if (depl == true) {                                                 //Si la recherche de tuile est pour un déplacement
                if (posX > 0) {                                                //Si le joueur n'est pas sur la bordure de gauche
                    if (tuiles[posX-1][posY].getNom() != "null" && 
                            (tuiles[posX-1][posY].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[posX-1][posY].getEtat() == Utils.EtatTuile.INONDEE)){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    } 
                }
                if (posX < 5) {                                                //Si le joueur n'est pas sur la bordure de droite
                    if (tuiles[posX+1][posY].getNom() != "null" && 
                            (tuiles[posX+1][posY].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[posX+1][posY].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY < 5) {                                                //Si le joueur n'est pas sur la bordure du bas
                    if (tuiles[posX][posY+1].getNom() != "null" &&
                            (tuiles[posX][posY+1].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[posX][posY+1].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY > 0) {                                                //Si le joueur n'est pas sur la bordure du haut
                    if (tuiles[posX][posY-1].getNom() != "null" &&
                            (tuiles[posX][posY-1].getEtat() == Utils.EtatTuile.ASSECHEE || tuiles[posX][posY-1].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
            } else if (depl == false) {                                         //Si la recherche de tuile est pour un assechement
                if (posX > 0) {                                                //Si le joueur n'est pas sur la bordure de gauche
                    if (tuiles[posX-1][posY].getNom() != "null" &&
                            (tuiles[posX-1][posY].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    }
                }
                if (posX < 5) {                                                //Si le joueur n'est pas sur la bordure de droite
                    if (tuiles[posX+1][posY].getNom() != "null" &&
                            (tuiles[posX+1][posY].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if (posY < 5) {                                                //Si le joueur n'est pas sur la bordure du bas
                    if (tuiles[posX][posY+1].getNom() != "null" &&
                            (tuiles[posX][posY+1].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if (posY > 0) {                                                //Si le joueur n'est pas sur la bordure du haut
                    if (tuiles[posX][posY-1].getNom() != "null" &&
                            (tuiles[posX][posY-1].getEtat() == Utils.EtatTuile.INONDEE)) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
                if (tuiles[posX][posY].getEtat() == Utils.EtatTuile.INONDEE) {
                    tuilesPossibles.add(tuiles[posX][posY]);
                }   
            }
        return tuilesPossibles;
    } //Renvoie une collection de tuiles adjacentes pour le déplacement ou l'assechement
    
    public void deplacementAssechage(int x, int y, boolean depl) {       

        ArrayList<Tuile> tuilesPossibles = getTuilesPossibles(depl);
        
        boolean actionEff = false;
        
            if (depl == true) {                                                 //Si la fonction est utilisée pour un déplacement
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
            } else if (depl == false) {                                         //Si la fonction est utilisée pour un assechement
                
                for (Tuile tuile : tuilesPossibles){
                    if (tuile.getX() == x & tuile.getY() == y){
                        grille.trouverTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);                        
                        actionEff = true;
                        System.out.println("La tuile " + position.getX() +", " +position.getY() + " à été asséchée");
                    }
                }
                if (actionEff == false){
                    System.out.println("La tuile " + position.getX() +", " +position.getY() + " n'à pas été asséchée");
                }
            }
    } //Gère le déplacement ou l'asséchement
    
    public boolean donnerCarte(CarteDosOrange carteADonner, Aventurier joueur){
        Boolean bool = false;
        for (CarteDosOrange carteMain : this.getMain()){ 
            if(joueur.getMain().size() + 1 < 8){
                    System.out.println("Le receveur à la place dans sa main.");
                    if (carteADonner == carteMain){                                                    // la carte se trouve bien dans la main du joueur courant
                        System.out.println("La carte est bien dans la main du joueur.");
                        if (this.getPosition() == joueur.getPosition()){                 // les deux joueurs sont bien sur la même case
                            System.out.println("Les deux joueurs sont bien sur la même case.");
                                                             // echange de la carte.
                            joueur.addCarteMain(carteADonner);
                            System.out.println("La carte tresor : " + carteADonner.getTresor() + " a bien été donné au joueur : "+ joueur.getNom());
                            bool = true;
                        } else {
                            System.out.println("Les deux joueurs ne sont pas sur la même case.");
                        }
                    } else {
                        System.out.println("La carte n'est pas dans la main du joueur");
                    }
                }  else {
                    System.out.println("Je receveur n'as pas la place de recevoir autant de carte(s)");
                }
        }
        this.removeCarteMain(carteADonner);
        return bool;
    } // le joueur courant donne une/plusieurs carte(s) a un joueur choisi

    public void setPosition(Tuile position) {
        this.position = position;
    }

    public Tuile getPosition() {
        return position;
    }

    public Grille getGrilleAv() {
        return grille;
    }

    public ArrayList<CarteDosOrange> getMain() {
        return main;
    }
    
    public void addCarteMain(CarteDosOrange carte){
        this.main.add(carte);
    
    }
    
    public void removeCarteMain(CarteDosOrange carte){
        this.main.remove(carte);
    }
    
    public abstract Pion getPion(); 
   
    
   
}
