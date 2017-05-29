/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import ile_interdite.Controller;
import ile_interdite.Etat;
import ile_interdite.Grille;
import ile_interdite.Tuile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jacquett
 */
public class VueJeu {
    private final JFrame window;
    private final Controller controller;
    
    VueJeu(Controller c, Tuile grille[][]){
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        controller = c;
        
        JPanel mainPanel = new JPanel(new GridLayout(6, 6));
        window.add(mainPanel);
        
        
        
        Tuile tuile = null;
        Etat etat = null;
        String nomTuile;
        
        for (int i = 0; i<=6 ; i++){
            for (int j = 0; j<= 6; j++){
                tuile = grille[i][j];
                if (tuile == null){
                    mainPanel.add(new JPanel());
                }
                else {
                    
                }
            }

        }
        
       

        }
        
        
    }

