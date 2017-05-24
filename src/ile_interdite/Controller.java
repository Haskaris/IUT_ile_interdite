/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;
import view.VueBienvenue;
import javax.swing.JFrame;
package util;
package view;

/**
 *
 * @author perrier5
 */
public class Controller implements Observateur {

    private final VueBienvenue vuebienvenue = new VueBienvenue(this);
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("MARCHEPAS");
        
    }
    @Override
    public void traiterMessage(Message msg) {
        if (msg.type == TypesMessage.ACTION_Jouer) {
            
        } else if (msg.type == TypesMessage.ACTION_Quitter) {
            vuebienvenue.fermer();
        } else if (msg.type == TypesMessage.ACTION_Regles) {
            
        }
    }
    
}
