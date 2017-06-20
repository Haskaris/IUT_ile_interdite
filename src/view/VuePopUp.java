/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.cartesOrange.CarteDosOrange;
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
        this.window = new JFrame();
        window.setSize(700, 500);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        panelGrille = new JPanel(new BorderLayout());
        panelMenu = new JPanel(new GridLayout(1,7)); // Panel des boutons d'actions
        JLabel message = new JLabel("Tu as trop de cartes connard");
        this.window.add(panelGrille);
        panelGrille.add(message, BorderLayout.NORTH);
        panelGrille.add(panelMenu, BorderLayout.CENTER);
        for (int i = 0; i <= main.size(); i++) {
            btnTuiles[i] = new JButton();
            panelMenu.add(btnTuiles[i]);
            
            btnTuiles[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message msg = new Message(TypesMessage.ACTION_Retour);
                    for (int i = 0; i <= main.size(); i++) {
                        if (e.getSource().equals(btnTuiles[i])) {
                            o.enleverCarteSurplus(main.get(i));
                        }
                    }

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
