/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import ile_interdite.Controller;
import ile_interdite.Message;
import ile_interdite.TypesMessage;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class VueBienvenue {
    private final JFrame window;
    private final Controller controller;
    
    
    public VueBienvenue(Controller c){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        controller = c;
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 5));
        window.add(mainPanel);
        
        
        
        JButton btnJouer = new JButton("Jouer");
        mainPanel.add(btnJouer);
        
        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypesMessage.ACTION_Jouer);
                c.traiterMessage(m);
            }
        });
        
        JButton btnRegle = new JButton("Règles");
        mainPanel.add(btnRegle);
        
        btnRegle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypesMessage.ACTION_Regles);
                c.traiterMessage(m);
            }
        });
        
        JButton btnQuitter = new JButton("Quitter");
        mainPanel.add(btnQuitter);
        
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypesMessage.ACTION_Quitter);
                c.traiterMessage(m);
            }
        });
        
    }
    
    public void fermer() {
        window.dispose();
    }
    
}
