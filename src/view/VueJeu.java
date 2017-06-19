/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import Controlleur.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jacquett
 */
public class VueJeu {
    private final JFrame window;
    private static Controller controller;
    
    VueJeu(Controller c, Grille grille){
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        controller = c;
        
        JPanel mainPanel = new JPanel(new GridLayout(6, 6));
        window.add(mainPanel);
        
        Tuile[][] grilleTab = grille.getGrille();
        
        
        Tuile tuile = null;
        Etat etat = null;
        String nomTuile;
        
        JButton tuile1 = new JButton();
        JButton tuile2 = new JButton();
        JButton tuile3 = new JButton();
        JButton tuile4 = new JButton();
        JButton tuile5 = new JButton();
        JButton tuile6 = new JButton();
        JButton tuile7 = new JButton();
        JButton tuile8 = new JButton();
        JButton tuile9 = new JButton();
        JButton tuile10 = new JButton();
        JButton tuile11 = new JButton();
        JButton tuile12 = new JButton();
        JButton tuile13 = new JButton();
        JButton tuile14 = new JButton();
        JButton tuile15 = new JButton();
        JButton tuile16 = new JButton();
        JButton tuile17 = new JButton();
        JButton tuile18 = new JButton();
        JButton tuile19 = new JButton();
        JButton tuile20 = new JButton();
        JButton tuile21 = new JButton();
        JButton tuile22 = new JButton();
        JButton tuile23 = new JButton();
        JButton tuile24 = new JButton();
        
        int k = 1;
        
        for (int i = 0; i<=6 ; i++){
            for (int j = 0; j<= 6; j++){
                    nomTuile = grilleTab[i][j].getNom();
                    etat = grilleTab[i][j].getEtat();
                    
                    switch (k){
                        case 3:
                             tuile1.setText(nomTuile);
                             mainPanel.add(tuile1);
                        break;
                        case 4:
                            tuile2.setText(nomTuile);
                            mainPanel.add(tuile2);
                        break;
                        case 8:
                            tuile3.setText(nomTuile);
                            mainPanel.add(tuile3);
                        break;
                        case 9:
                            tuile4.setText(nomTuile);
                            mainPanel.add(tuile4);
                        break;
                        case 10:
                            tuile5.setText(nomTuile);
                            mainPanel.add(tuile5);
                        break;
                        case 11:
                            tuile6.setText(nomTuile);
                            mainPanel.add(tuile6);
                        break;
                        case 13:
                            tuile7.setText(nomTuile);
                            mainPanel.add(tuile7);
                        break;
                        case 14:
                            tuile8.setText(nomTuile);
                            mainPanel.add(tuile8);
                        break;
                        case 15:
                            tuile9.setText(nomTuile);
                            mainPanel.add(tuile9);
                        break;
                        case 16:
                            tuile10.setText(nomTuile);
                            mainPanel.add(tuile10);
                        break;
                        case 17:
                            tuile11.setText(nomTuile);
                            mainPanel.add(tuile11);
                        break;
                        case 18:
                            tuile12.setText(nomTuile);
                            mainPanel.add(tuile12);
                        break;
                        case 19:
                            tuile13.setText(nomTuile);
                            mainPanel.add(tuile13);
                        break;
                        case 20:
                            tuile14.setText(nomTuile);
                            mainPanel.add(tuile14);
                        break;
                        case 21:
                            tuile15.setText(nomTuile);
                            mainPanel.add(tuile15);
                        break;
                        case 22:
                            tuile16.setText(nomTuile);
                            mainPanel.add(tuile16);
                        break;
                        case 23:
                            tuile17.setText(nomTuile);
                            mainPanel.add(tuile17);
                        break;
                        case 24:
                            tuile18.setText(nomTuile);
                            mainPanel.add(tuile18);
                        break;
                        case 26:
                            tuile19.setText(nomTuile);
                            mainPanel.add(tuile19);
                        break;
                        case 27:
                            tuile20.setText(nomTuile);
                            mainPanel.add(tuile20);
                        break;
                        case 28:
                            tuile21.setText(nomTuile);
                            mainPanel.add(tuile21);
                        break;
                        case 29:
                            tuile22.setText(nomTuile);
                            mainPanel.add(tuile22);
                        break;
                        case 33:
                            tuile23.setText(nomTuile);
                            mainPanel.add(tuile23);
                        break;
                        case 34:
                            tuile24.setText(nomTuile);
                            mainPanel.add(tuile24);
                        break;
                        default:
                            mainPanel.add(new JPanel());
                        
                    }
                    k++;
                    
            }

        }
        
       

        }
     public void afficher(){
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
        
    }

