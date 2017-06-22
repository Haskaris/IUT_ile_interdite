/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.util.ArrayList;
import java.util.Observable;
import model.Tuile;
import model.cartesOrange.CarteDosOrange;
import util.Message;

/**
 *
 * @author reyneu
 */
public interface Observateur {
    public void traiterMessage(Message msg);
    public void traiterAction(String str, int x, int y, boolean depl);
    public void envoyerDonnees(int nbJoueurs, String nomJ1, String nomJ2, String nomJ3, String nomJ4, int difficulte);
    public ArrayList<String> getJoueurTuile(Tuile tuile);
    public void enleverCarteSurplus(CarteDosOrange carte);
    public void utiliserCarteHelicoptere();
    public void utiliserCarteSacDeSable();
    public void defausserCarte(CarteDosOrange carte);
}
