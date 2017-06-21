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
    
    
    public Echelle(int cran){
        setCran(cran);
        setNiveauEau();
}
    
    public void incrementerCran() {                 // à la suite d'une montée des eaux 
        setCran(getCran()+1);
    }

    public int getCran() {                          // récupération du niveau de l'échelle
        return cran;
    }

    private void setCran(int cran) {                 // modification du cran (en début de partie) pour la difficulté
        this.cran = cran;
    }

    public int getNiveauEau() {
        if(getCran() < 2){
            return niveauEau = 2;
        } else if (getCran() > 2 && getCran() < 6){
            return niveauEau = 3;
        } else if (getCran() > 6 && getCran() < 8){
            return niveauEau = 4;
        } else if (getCran() > 8 && getCran() < 10){
            return niveauEau = 5;
        } else {
            return niveauEau = 6;
        }
    }
    
    public void setNiveauEau() {
        if(getCran() <= 2){
            this.niveauEau = 2;
        } else if (getCran() > 2 && getCran() < 6){
            this.niveauEau = 3;
        } else if (getCran() >= 6 && getCran() < 8){
            this.niveauEau = 4;
        } else if (getCran() >= 8 && getCran() < 10){
            this.niveauEau = 5;
        } else {
            this.niveauEau = 6;
        }
    }
    
}
