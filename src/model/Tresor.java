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
public class Tresor {
    private String nomTresor;

    public Tresor(String nomT){
        setNomTresor(nomT);
    }
    
    public String getNomTresor() {
        return nomTresor;
    }

    private void setNomTresor(String nomTresor) {
        this.nomTresor = nomTresor;
    }
}
