/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author reyneu
 */
public class CarteInondation {
    private Tuile tuile;

    public CarteInondation(Tuile tuile){
        setTuile(tuile);
    }
    
    public Tuile getTuile() {
        return tuile;
    }
    
    private void setTuile(Tuile nomTuile) {
        this.tuile = nomTuile;
    }

    
}
