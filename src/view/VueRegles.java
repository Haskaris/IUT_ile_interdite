/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Controller;
import util.Message;
import util.TypesMessage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author reyneu
 */
public class VueRegles {
    private final JFrame window;
    private final Controller controller;
    
    
    public VueRegles(Controller c){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("REGLES DU JEU");
        
        controller = c;
        
        JPanel mainPanel = new JPanel();
        window.add(mainPanel);
        
        JButton btnRetour = new JButton("Gros Bouton Retour");
        mainPanel.add(btnRetour);
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Retour);
                
                controller.traiterMessage(msg);
            }
        });
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
}
