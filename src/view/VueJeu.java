/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import Controlleur.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Message;
import util.TypesMessage;

/**
 *
 * @author jacquett
 */
public class VueJeu {
    private final JFrame window;
    private static Controller controller;
    
    public VueJeu(Controller c, Grille grille){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(dim.width, dim.height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        controller = c;
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelGrille = new JPanel(new GridLayout(6, 6));
        JPanel panelMenu = new JPanel(new GridLayout(4,2));
        
        window.add(mainPanel);
        mainPanel.add(panelGrille, BorderLayout.CENTER);
        mainPanel.add(panelMenu, BorderLayout.EAST);
        
        Tuile[][] grilleTab = grille.getGrille();
        
        
        Tuile tuile = null;
        Color etatCouleur = null;
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
        
        tuile1.setEnabled(false);
        tuile2.setEnabled(false);
        tuile3.setEnabled(false);
        tuile4.setEnabled(false);
        tuile5.setEnabled(false);
        tuile6.setEnabled(false);
        tuile7.setEnabled(false);
        tuile8.setEnabled(false);
        tuile9.setEnabled(false);
        tuile10.setEnabled(false);
        tuile11.setEnabled(false);
        tuile12.setEnabled(false);
        tuile13.setEnabled(false);
        tuile14.setEnabled(false);
        tuile15.setEnabled(false);
        tuile16.setEnabled(false);
        tuile17.setEnabled(false);
        tuile18.setEnabled(false);
        tuile19.setEnabled(false);
        tuile20.setEnabled(false);
        tuile21.setEnabled(false);
        tuile22.setEnabled(false);
        tuile23.setEnabled(false);
        tuile24.setEnabled(false);
        
        int k = 1;
        
        for (int i = 0; i<=5 ; i++){
            for (int j = 0; j<= 5; j++){
                    nomTuile = grilleTab[i][j].getNom();
                    if (grilleTab[i][j].getEtat() == Etat.assechee){
                        etatCouleur = Color.DARK_GRAY;
                    }
                    else if (grilleTab[i][j].getEtat() == Etat.inondee){
                        etatCouleur = Color.ORANGE;
                    }
                    else {
                        etatCouleur = Color.BLUE;
                    }
                    
                    switch (k){
                        case 3:
                             tuile1.setText(nomTuile);
                             tuile1.setBackground(etatCouleur);
                             tuile1.setForeground(Color.WHITE);
                             panelGrille.add(tuile1);
                        break;
                        case 4:
                            tuile2.setText(nomTuile);
                            tuile2.setBackground(etatCouleur);
                            tuile2.setForeground(Color.WHITE);
                            panelGrille.add(tuile2);
                        break;
                        case 8:
                            tuile3.setText(nomTuile);
                            tuile3.setBackground(etatCouleur);
                            tuile3.setForeground(Color.WHITE);
                            panelGrille.add(tuile3);
                        break;
                        case 9:
                            tuile4.setText(nomTuile);
                            tuile4.setBackground(etatCouleur);
                            tuile4.setForeground(Color.WHITE);
                            panelGrille.add(tuile4);
                        break;
                        case 10:
                            tuile5.setText(nomTuile);
                            tuile5.setBackground(etatCouleur);
                            tuile5.setForeground(Color.WHITE);
                            panelGrille.add(tuile5);
                        break;
                        case 11:
                            tuile6.setText(nomTuile);
                            tuile6.setBackground(etatCouleur);
                            tuile6.setForeground(Color.WHITE);
                            panelGrille.add(tuile6);
                        break;
                        case 13:
                            tuile7.setText(nomTuile);
                            tuile7.setBackground(etatCouleur);
                            tuile7.setForeground(Color.WHITE);
                            panelGrille.add(tuile7);
                        break;
                        case 14:
                            tuile8.setText(nomTuile);
                            tuile8.setBackground(etatCouleur);
                            tuile8.setForeground(Color.WHITE);
                            panelGrille.add(tuile8);
                        break;
                        case 15:
                            tuile9.setText(nomTuile);
                            tuile9.setBackground(etatCouleur);
                            tuile9.setForeground(Color.WHITE);
                            panelGrille.add(tuile9);
                        break;
                        case 16:
                            tuile10.setText(nomTuile);
                            tuile10.setBackground(etatCouleur);
                            tuile10.setForeground(Color.WHITE);
                            panelGrille.add(tuile10);
                        break;
                        case 17:
                            tuile11.setText(nomTuile);
                            tuile11.setBackground(etatCouleur);
                            tuile11.setForeground(Color.WHITE);
                            panelGrille.add(tuile11);
                        break;
                        case 18:
                            tuile12.setText(nomTuile);
                            tuile12.setBackground(etatCouleur);
                            tuile12.setForeground(Color.WHITE);
                            panelGrille.add(tuile12);
                        break;
                        case 19:
                            tuile13.setText(nomTuile);
                            tuile13.setBackground(etatCouleur);
                            tuile13.setForeground(Color.WHITE);
                            panelGrille.add(tuile13);
                        break;
                        case 20:
                            tuile14.setText(nomTuile);
                            tuile14.setBackground(etatCouleur);
                            tuile14.setForeground(Color.WHITE);
                            panelGrille.add(tuile14);
                        break;
                        case 21:
                            tuile15.setText(nomTuile);
                            tuile15.setBackground(etatCouleur);
                            tuile15.setForeground(Color.WHITE);
                            panelGrille.add(tuile15);
                        break;
                        case 22:
                            tuile16.setText(nomTuile);
                            tuile16.setBackground(etatCouleur);
                            tuile16.setForeground(Color.WHITE);
                            panelGrille.add(tuile16);
                        break;
                        case 23:
                            tuile17.setText(nomTuile);
                            tuile17.setBackground(etatCouleur);
                            tuile17.setForeground(Color.WHITE);
                            panelGrille.add(tuile17);
                        break;
                        case 24:
                            tuile18.setText(nomTuile);
                            tuile18.setBackground(etatCouleur);
                            tuile18.setForeground(Color.WHITE);
                            panelGrille.add(tuile18);
                        break;
                        case 26:
                            tuile19.setText(nomTuile);
                            tuile19.setBackground(etatCouleur);
                            tuile19.setForeground(Color.WHITE);
                            panelGrille.add(tuile19);
                        break;
                        case 27:
                            tuile20.setText(nomTuile);
                            tuile20.setBackground(etatCouleur);
                            tuile20.setForeground(Color.WHITE);
                            panelGrille.add(tuile20);
                        break;
                        case 28:
                            tuile21.setText(nomTuile);
                            tuile21.setBackground(etatCouleur);
                            tuile21.setForeground(Color.WHITE);
                            panelGrille.add(tuile21);
                        break;
                        case 29:
                            tuile22.setText(nomTuile);
                            tuile22.setBackground(etatCouleur);
                            tuile22.setForeground(Color.WHITE);
                            panelGrille.add(tuile22);
                        break;
                        case 33:
                            tuile23.setText(nomTuile);
                            tuile23.setBackground(etatCouleur);
                            tuile23.setForeground(Color.WHITE);
                            panelGrille.add(tuile23);
                        break;
                        case 34:
                            tuile24.setText(nomTuile);
                            tuile24.setBackground(etatCouleur);
                            tuile24.setForeground(Color.WHITE);
                            panelGrille.add(tuile24);
                        break;
                        default:
                            panelGrille.add(new JPanel());
                        
                    }
                    k++;
                    
            }
            
            

        }
        
        JButton btnAssechement = new JButton("Assecher");
        JButton btnDeplacement = new JButton("Deplacer");
        JButton btnDonnerCarte = new JButton("DonnerCarte");
        JButton btnFinTour = new JButton("Fin Du Tour");
        panelMenu.add(new JPanel());
        panelMenu.add(new JPanel());
        panelMenu.add(btnAssechement);
        panelMenu.add(btnDeplacement);
        panelMenu.add(btnDonnerCarte);
        panelMenu.add(btnFinTour);
        panelMenu.add(new JPanel());
        panelMenu.add(new JPanel());
        
        
        
        btnAssechement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Assecher);
                c.traiterMessage(msg);
            }
        });
        
        btnDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Deplacer);
                c.traiterMessage(msg);
            }
        });
        
        btnDonnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                c.traiterMessage(msg);
            }
        });
        
        btnFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Fin);
                c.traiterMessage(msg);
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

