/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import ile_interdite.Controller;
import ile_interdite.Message;
import ile_interdite.Observateur;



/**
 *
 * @author reyneu
 */
public class BienvTest implements Observateur  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller c = new Controller();
        VueBienvenue bienvenue = new VueBienvenue(c);
        //bienvenue.afficher(true);
        
    }

    @Override
    public void traiterMessage(Message msg) {
        System.out.println("slt");
    }
    
}
