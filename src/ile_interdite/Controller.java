/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author perrier5
 */
public class Controller implements Observateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("");
        
    }
    @Override
    public void traiterMessage(Message m) {
        if (msg.type == TypesMessage.ACTION_Jouer) {
            
        } else if (msg.type == TypesMessage.ACTION_Quitter) {
            
        } else if (msg.type == TypesMessage.ACTION_Regles) {
            
        }
    }
    
}
