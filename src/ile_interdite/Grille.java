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
public class Grille {
    private final int ligne = 6;
    private final int colonne = 6;
    private ArrayList<Tuile> tuiles;
    
    Grille(){
        tuiles = new ArrayList<Tuile>();
    }
}
