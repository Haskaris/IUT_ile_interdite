/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.Tuile;

/**
 *
 * @author reyneu
 */
public class Pilote extends Aventurier{
    
    private boolean pouvoirUtilise;
    
    public Pilote(String nom) {
        super(nom);
    }

    /**
     * @return the pouvoirUtilise
     */
    public boolean isPouvoirUtilise() {
        return pouvoirUtilise;
    }

    /**
     * @param pouvoirUtilise the pouvoirUtilise to set
     */
    public void setPouvoirUtilise(boolean pouvoirUtilise) {
        this.pouvoirUtilise = pouvoirUtilise;
    }
    
    @Override
    public ArrayList<Tuile> getTuilesPossibles(){          //Retourne une collection de tuiles sur lesquelles le déplacement est possible pour le pilote en fonction de son pouvoir (Utilisé ou Non)
        
        Tuile[][] tuiles = getGrilleAv().getGrille();
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        
        if (isPouvoirUtilise() == false){
            for(int i = 0; i < tuiles.length; i++){
                for(int j = 0; j < tuiles[i].length; i++){
                    tuilesPossibles.add(tuiles[i][j]);  
                }
            }
        }
        
        else {
            tuilesPossibles = super.getTuilesPossibles();
        }
        
        return tuilesPossibles;
    }
}
