/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.cartesOrange.CarteDosOrange;
import model.Tuile;
import util.Utils;
import util.Utils.Pion;

/**
 *
 * @author JACQUETCorp
 */
public class Messager extends Aventurier{
    
    public Messager(String nom) {
        super(nom);
    }
    
    @Override
    public boolean donnerCarte( CarteDosOrange carteADonner, Aventurier joueur){               // le joueur courant donne une/plusieurs carte(s) a un joueur choisi
        Boolean bool = false; 
        for (CarteDosOrange carteMain : this.getMain()){ 
            if(joueur.getMain().size() + 1 < 6){
                 
                if (carteADonner == carteMain){                                                    // la carte se trouve bien dans la main du joueur courant
                    
                                                        // echange de la carte.

                        
                        bool = true;
                    } else {
                        
                    }
            } else {
                        
                    }
        }
        
        if (bool){
            this.removeCarteMain(carteADonner); 
            joueur.addCarteMain(carteADonner);
        }

        return bool;
    }

    @Override
    public Pion getPion() {
        return Pion.ORANGE;
    }
}
    
    
    

