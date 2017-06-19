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
public class CarteTresor extends CarteDosOrange {
    private Tresor tresor;
    
    public CarteTresor(Tresor tresor){
        setTresor(tresor);
    
    }

    /**
     * @param nomTresor the nomTresor to set
     */
    public void setTresor(Tresor nomTresor) {
        this.tresor = nomTresor;
    }

    /**
     * @return the tresor
     */
    public Tresor getTresor() {
        return tresor;
    }
    
    
    
}
