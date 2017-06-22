/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.cartesOrange.CarteDosOrange;
import model.cartesOrange.CarteTresor;
import util.Message;
import util.TypesMessage;
/**
 *
 * @author perrier5
 */
public class VuePopUp {
    private final Observateur o;
    private final JFrame window;
    private final JPanel panelMenu;
    private final JPanel panelGrille;
    private JButton[] btnTuiles = new JButton[7];
    
    public VuePopUp(Observateur o, ArrayList<CarteDosOrange> main) {
        this.o = o;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(dim.width, dim.height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        panelGrille = new JPanel(new BorderLayout());
        panelMenu = new JPanel(new GridLayout(1,7)); // Panel des boutons d'actions
        JLabel message = new JLabel("Vous avez trop de cartes, veuillez en choisir une a défausser.");
        this.window.add(panelGrille);
        panelGrille.add(message, BorderLayout.NORTH);
        panelGrille.add(panelMenu, BorderLayout.CENTER);
        for (int i = 0; i <= main.size()-1; i++) {
            String nomCarte;
            if (main.get(i).getClass().equals(CarteTresor.class)){
                nomCarte =main.get(i).getTresor().getNomTresor() ;
            
            } else {
                nomCarte = main.get(i).getClass().getSimpleName() ;
            
            }
            
            
            btnTuiles[i] = new JButton(nomCarte);
            panelMenu.add(btnTuiles[i]);
            
            btnTuiles[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                   int num = 0;
                   
                   for (int i = 0; i < 6; i++){
                       if (source.equals(btnTuiles[i])){
                           num =i;
                       }
                   }
                   Message m = new Message(TypesMessage.ACTION_ChoixCarteASupprimer);
                   m.setNumBtn(num);
                   o.traiterMessage(m);
                }
            });
            
        }
    }
    
    public void afficher() {
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
    
}
