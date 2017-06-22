/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
 * @author JACQUETCorp
 */
public class VuePopUp {
    private final Observateur o;
    private final JFrame window;
    private final JPanel panelCartes;
    private final JPanel panelPrincipal;
    private JButton[] btnCarte = new JButton[7];
    private JButton btnRetour = new JButton("Annuler");
    
    public VuePopUp(Observateur o, ArrayList<CarteDosOrange> main, boolean defausse) {
        this.o = o;
        this.window = new JFrame();
        window.setSize(1400, 500);
        window.setAlwaysOnTop(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        panelPrincipal = new JPanel(new BorderLayout());
        panelCartes = new JPanel(new GridLayout(1,7)); // Panel des boutons d'actions
        
        
        
        

        this.window.add(panelPrincipal);

        panelPrincipal.add(panelCartes, BorderLayout.CENTER);
        for (int i = 0; i <= main.size()-1; i++) {
            String nomCarte;
            if (main.get(i).getClass().equals(CarteTresor.class)){
                nomCarte =main.get(i).getTresor().getNomTresor() ;
            
            } else {
                nomCarte = main.get(i).getClass().getSimpleName() ;
            
            }
            
            
            btnCarte[i] = new JButton(nomCarte);
            panelCartes.add(btnCarte[i]);
            
            btnCarte[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                   int num = 0;
                   
                   for (int i = 0; i < 6; i++){
                       if (source.equals(btnCarte[i])){
                           num =i;
                       }
                   }
                   Message m = new Message(TypesMessage.ACTION_ChoixCarteASupprimer);
                   m.setNumBtn(num);
                   o.traiterMessage(m);
                }
            });
            
            JLabel message = new JLabel();
            
            if (defausse){
                panelPrincipal.add(btnRetour, BorderLayout.SOUTH);
                message.setText("Choissisez une carte à défausser");
            }
            else {
                message.setText("Vous avez trop de cartes, veuillez en choisir une à défausser.");
            }
            
                    message.setHorizontalAlignment(JLabel.CENTER);
        message.setFont(new Font("Arial",Font.BOLD,20));
                panelPrincipal.add(message, BorderLayout.NORTH);
        }
    }
    
    public void afficher() {
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
    
}
