/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import Controlleur.Observateur;
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
import java.util.ArrayList;

/**
 *
 * @author jacquett
 */
public class VueJeu {

    private final JFrame window;
    private static Controller controller;
    private JButton[][] btnTuiles = new JButton[6][6];                // TABLEAU DOUBKLE DIMENSION SAMER
    private Grille grille;
    private String nomJoueurCourant;
    private boolean depl;
    private int positionDemandee;
    private int x, y;

    public VueJeu(Observateur o, Grille grille) {
        setGrille(grille);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(dim.width, dim.height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        window.setTitle("ILE INTERDITE");

        this.o = o;

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panelGrille = new JPanel(new GridLayout(6, 6));
        JPanel panelMenu = new JPanel(new GridLayout(4, 2));

        window.add(mainPanel);
        mainPanel.add(panelGrille, BorderLayout.CENTER);
        mainPanel.add(panelMenu, BorderLayout.EAST);

        Tuile[][] grilleTab = grille.getGrille();

        Tuile tuile = null;
        Color etatCouleur = null;
        String nomTuile;

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++){
                btnTuiles[i][j] = new JButton();
                btnTuiles[i][j].setEnabled(false);                
            }

        }

        JLabel labelJC = new JLabel(getNom() + " joue!");

        int k = 0;

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (i == 0 && j == 0) {
                    panelGrille.add(labelJC);
                } else {
                    nomTuile = grilleTab[i][j].getNom();
                    if (grilleTab[i][j].getEtat() == Etat.assechee) {
                        etatCouleur = Color.DARK_GRAY;
                    } else if (grilleTab[i][j].getEtat() == Etat.inondee) {
                        etatCouleur = Color.ORANGE;
                    } else {
                        etatCouleur = Color.BLUE;
                    }
                    if (grilleTab[i][j].getNom().equals("null")) {
                        panelGrille.add(new JPanel());
                    } else {
                        btnTuiles[i][j].setText(nomTuile);
                        btnTuiles[i][j].setBackground(etatCouleur);
                        btnTuiles[i][j].setForeground(Color.WHITE);
                        panelGrille.add(btnTuiles[i][j]);
                    }
                }

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
                setDepl(false);
                Message msg = new Message(TypesMessage.ACTION_Assecher);
                o.traiterMessage(msg);
            }
        });

        btnDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDepl(true);
                Message msg = new Message(TypesMessage.ACTION_Deplacer);
                o.traiterMessage(msg);
            }
        });

        btnDonnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                o.traiterMessage(msg);
            }
        });

        btnFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Fin);
                o.traiterMessage(msg);
            }
        });

        for (int i=0;i<5;i++){
            x = i;
            for (int j=0;j<5;j++){
                y = j;
                setPositionDemandee(i);
                btnTuiles[i][j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.traiterAction(nomJoueurCourant, x, y, depl);
                }
            });
            }
            
        }

    }

    public void afficher() {
        window.setVisible(true);
    }
    
    public void afficherPossible(Integer[][] tab){
        for (int i =0; i<5; i++){
            for (int j = 0; j<5; j++){

                btnTuiles[i][j].setEnabled(true);
            }
           
        }
    }

    public void fermer() {
        window.dispose();
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void setNom(String nom) {
        this.nomJoueurCourant = nom;
    }

    public String getNom() {
        return nomJoueurCourant;
    }
    
    private void setDepl(boolean bool){
        depl = bool;
    }
    private void setPositionDemandee(int i){
        this.positionDemandee = i;
    }
}
