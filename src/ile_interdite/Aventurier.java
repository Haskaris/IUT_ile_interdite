/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;

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

    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Tuile position) {
        this.position = position;
    }

    /**
     * @return the main
     */
    public CarteDosOrange getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(CarteDosOrange main) {
        this.main = main;
    }

    
    public ArrayList<Tuile> getTuilesPossibles(){
        ArrayList<Tuile> liste;
        Grille grille = getGrille();
        
       
        
        
        
        
        
        
        
        return liste;
    }


    public Grille getGrille() {
        return grille;
    }
   
   
    
}
