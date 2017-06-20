/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.cartesOrange.CarteDosOrange;
import model.Etat;
import model.Grille; 
import model.Tuile;

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

    public ArrayList<Tuile> getTuilesPossibles(boolean depl){         //Renvoie une collection de tuiles adjacentes pour le déplacement ou l'asséchage
        int posX = getPosition().getX();
        int posY = getPosition().getY();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        
            if (depl == true) {                                                 //Si la recherche de tuile est pour un déplacement
                if ((posX > 2 && (posY == 0 || posY == 5)) || (posX > 1 && (posY == 1 || posY == 4)) || (posX > 0 && (posY == 2 || posY == 3))) {                                                //Si le joueur n'est pas sur la bordure de gauche
                    if (tuiles[posX-1][posY].getEtat() == Etat.assechee || tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    } 
                }
                if ((posX < 3 && (posY == 0 || posY == 5)) || (posX < 4 && (posY == 1 || posY == 4)) || (posX < 5 && (posY == 2 || posY == 3))) {                                                //Si le joueur n'est pas sur la bordure de droite
                    if (tuiles[posX+1][posY].getEtat() == Etat.assechee || tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if ((posY < 5 && (posX == 2 || posX == 3)) || (posY < 4 && (posX == 1 || posX == 4)) || (posY < 3 && (posX == 0 || posX == 5))) {                                                //Si le joueur n'est pas sur la bordure du bas
                    if (tuiles[posX][posY+1].getEtat() == Etat.assechee || tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if ((posY > 0 && (posX == 2 || posX == 3)) || (posY > 1 && (posX == 1 || posX == 4)) || (posY > 2 && (posX == 0 || posX == 5))) {                                                //Si le joueur n'est pas sur la bordure du haut
                    if (tuiles[posX][posY-1].getEtat() == Etat.assechee || tuiles[posX][posY-1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY-1]);
                    }
                }
            } else if (depl == false) {                                         //Si la recherche de tuile est pour un assechement
                if ((posX > 2 && (posY == 0 || posY == 5)) || (posX > 1 && (posY == 1 || posY == 4)) || (posX > 0 && (posY == 2 || posY == 3))) {                                                //Si le joueur n'est pas sur la bordure de gauche
                    if (tuiles[posX-1][posY].getEtat() == Etat.inondee){
                        tuilesPossibles.add(tuiles[posX-1][posY]);
                    }
                }
                if ((posX < 3 && (posY == 0 || posY == 5)) || (posX < 4 && (posY == 1 || posY == 4)) || (posX < 5 && (posY == 2 || posY == 3))) {                                                //Si le joueur n'est pas sur la bordure de droite
                    if (tuiles[posX+1][posY].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX+1][posY]);
                    }
                }
                if ((posY < 5 && (posX == 2 || posX == 3)) || (posY < 4 && (posX == 1 || posX == 4)) || (posY < 3 && (posX == 0 && posX == 5))) {                                                //Si le joueur n'est pas sur la bordure du bas
                    if (tuiles[posX][posY+1].getEtat() == Etat.inondee) {
                        tuilesPossibles.add(tuiles[posX][posY+1]);
                    }
                }
                if ((posY > 0 && (posX == 2 || posX == 3)) || (posY > 1 && (posX == 1 || posX == 4)) || (posY > 2 && (posX == 0 && posX == 5))) {                                                //Si le joueur n'est pas sur la bordure du haut
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
    
    public void deplacementAssechage(int x, int y, boolean depl) {       //Gère le déplacement ou l'asséchement

        
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
                        grille.trouverTuile(x, y).setEtat(Etat.assechee);                        
                        actionEff = true;
                        System.out.println("La tuile " + position.getX() +", " +position.getY() + " à été asséchée");
                    }
                }
                if (actionEff == false){
                    System.out.println("La tuile " + position.getX() +", " +position.getY() + " n'à pas été asséchée");
                }
            }
    }
    
     public void donnerCarte(Aventurier courant ,ArrayList<CarteDosOrange> cartes, Aventurier joueur){               // le joueur courant donne une/plusieurs carte(s) a un joueur choisi
         for (CarteDosOrange liste : courant.getMain()){ 
          for (CarteDosOrange carte : cartes){
            if (carte == liste){                                                    // la carte se trouve bien dans la main du joueur courant
                System.out.println("La carte est bien dans la main du joueur.");
                if (courant.getPosition() == joueur.getPosition()){                 // les deux joueurs sont bien sur la même case
                    System.out.println("Les deux joueurs sont bien sur la même case.");
                    courant.removeCarteMain(carte);                                 // echange de la carte.
                    joueur.addCarteMain(carte);
                    System.out.println("La carte tresor : " + carte.getTresor() + " à bien été donner au joueur : "+ joueur.getNom());
                }
              }
            }
          }
        }
     
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
    
    
   
    
   
}
