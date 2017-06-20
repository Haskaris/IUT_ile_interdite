/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.cartesOrange.CarteDosOrange;
import model.Tuile;

/**
 *
 * @author reyneu
 */
public class Messager extends Aventurier{
    
    public Messager(String nom) {
        super(nom);
    }
    
    @Override
    public void donnerCarte(Aventurier courant ,ArrayList<CarteDosOrange> cartes, Aventurier joueur){               // le joueur courant donne une/plusieurs carte(s) a un joueur choisi
         for (CarteDosOrange liste : courant.getMain()){ 
          for (CarteDosOrange carte : cartes){
            if (carte == liste){                                                    // la carte se trouve bien dans la main du joueur courant
                System.out.println("La carte est bien dans la main du joueur.");
                    courant.removeCarteMain(carte);                                 // echange de la carte.
                    joueur.addCarteMain(carte);
                    System.out.println("La carte tresor : " + carte.getTresor() + " à bien été donner au joueur : "+ joueur.getNom());
                }
              }
            }
          }
}
    
    
    

