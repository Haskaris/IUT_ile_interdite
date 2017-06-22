/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import util.Message;
import util.TypesMessage;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author reyneu
 */
public class VueBienvenue {
    private final JFrame window;
    private final Observateur o;
    
    
    public VueBienvenue(Observateur o){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        this.o = o;
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelCentre = new JPanel(new GridLayout(5, 5));
        mainPanel.add(panelCentre);
        window.add(mainPanel);
        
        
        ImageIcon icon = createImageIcon("IleInterdite.png",
                                 "LOGO");
        JLabel labelTitre = new JLabel(icon, JLabel.CENTER);
        mainPanel.add(labelTitre, BorderLayout.NORTH);
        
        JButton btnJouer = new JButton("Jouer");
        JButton btnRegle = new JButton("Règles");
        JButton btnQuitter = new JButton("Quitter");
        
        for (int i=1; i<=25; i++){
            switch(i){
                
                case 16:
                    panelCentre.add(btnRegle);
                break;
                
                case 18:
                    panelCentre.add(btnJouer);
                break;    
                
                case 20:
                    panelCentre.add(btnQuitter);
                break;
                
                default:
                    panelCentre.add(new JPanel());
                    
            }
        }
                
        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ACTION_Jouer);
                o.traiterMessage(m);
            }
        });
        
        
        btnRegle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ACTION_Regles);
                o.traiterMessage(m);
            }
        });
        
        
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ACTION_Quitter);
                o.traiterMessage(m);
            }
        });
        
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
    
    
        /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
