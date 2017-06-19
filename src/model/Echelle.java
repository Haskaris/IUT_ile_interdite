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
    
    public void incrementerCran() {                 // à la suite d'une montée des eaux 
        setCran(getCran()+1);
    }

    /**
     * @return the cran
     */
    public int getCran() {                          // récupération du niveau de l'échelle
        return cran;
    }

    /**
     * @param cran the cran to set
     */
    public void setCran(int cran) {                 // modification du cran (en début de partie) pour la difficulté
        this.cran = cran;
    }
    
}
