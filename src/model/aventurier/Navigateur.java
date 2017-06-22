/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventurier;

import java.util.ArrayList;
import model.Tuile;
import util.Utils;
import util.Utils.EtatTuile;
import util.Utils.Pion;

/**
 *
 * @author reyneu
 */
public class Navigateur extends Aventurier{
    
    
    public Navigateur(String nom) {
        super(nom);
    }
    
    @Override
    public Pion getPion() {
        return Pion.JAUNE;
    }
    
}
