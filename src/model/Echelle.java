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
public class Echelle {
    private int niveauEau;
    private int cran;
    
    public void incrementerCran() {
        setCran(getCran()+1);
    }

    /**
     * @return the cran
     */
    public int getCran() {
        return cran;
    }

    /**
     * @param cran the cran to set
     */
    public void setCran(int cran) {
        this.cran = cran;
    }
    
}
