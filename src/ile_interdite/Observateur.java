/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.Observable;

/**
 *
 * @author reyneu
 */
public interface Observateur {
    public void traiterMessage(Message msg);
    public void getDonnees(int nbJoueurs, String nomJ1, String nomJ2, String nomJ3, String nomJ4, int difficulte);
}
