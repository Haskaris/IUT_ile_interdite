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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author jacquett
 */
public class VueJeu {

    private final JFrame window;
    private static Observateur observateur;
    private JButton[][] btnTuiles = new JButton[6][6];            
    private Grille grille;
    private String nomJoueurCourant = "AH";
    private boolean depl;
    private int positionDemandee;
    private int x, y;
    private JLabel labelJC;
    private JPanel panelGrille, mainPanel, panelMenu, panel1, panel2, panel2Centre, panel3;
    private boolean deplApp = false;
    private boolean assApp = false;

    public VueJeu(Observateur o, Grille gr) {
        
        
        // Initialisation de la fenêtre
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(dim.width, dim.height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        window.setTitle("ILE INTERDITE");

        this.observateur = o;

        mainPanel = new JPanel(new BorderLayout());
        panelGrille = new JPanel(new GridLayout(6, 6));                  // Panel de la grille
        
        panelMenu = new JPanel(new GridLayout(3,1));                     // Panel des boutons d'actions
        panel1 = new JPanel();
        panelMenu.add(panel1);
        panel2 = new JPanel(new BorderLayout());
        panel2Centre = new JPanel(new GridLayout(2,2));
        panel2.add(panel2Centre);
        panelMenu.add(panel2);
        panel3 = new JPanel();
        panelMenu.add(panel3);

        window.add(mainPanel);
        mainPanel.add(panelGrille, BorderLayout.CENTER);
        mainPanel.add(panelMenu, BorderLayout.EAST);

        setGrille(gr);
        Tuile[][] grilleTab = grille.getGrille();                               // Récupération du tableau de la grille

        Color etatCouleur = null;
        String nomTuile;
        Border border = new LineBorder(Color.WHITE, 5);
        
        for (int i = 0; i <= 5; i++) {                                          // Initialisation des boutons
            for (int j = 0; j <= 5; j++){
                btnTuiles[i][j] = new JButton();
                btnTuiles[i][j].setEnabled(false);
                btnTuiles[i][j].setBorder(border);
            }

        }

        labelJC = new JLabel(getNom() + " joue!");                              // Affichage du joueur courant


        for (int i = 0; i <= 5; i++) {                                          // Affichage de la grille
            for (int j = 0; j <= 5; j++) {
                if (i == 0 && j == 0) {
                    panelGrille.add(labelJC);                                   // Affichage du joueur courant
                } else {
                    nomTuile = grilleTab[i][j].getNom();                        // Gestion de la couleur du bouton en fonction de l'état de la tuile
                    if (grilleTab[i][j].getEtat() == Etat.assechee) {
                        etatCouleur = Color.DARK_GRAY;
                    } else if (grilleTab[i][j].getEtat() == Etat.inondee) {
                        etatCouleur = Color.ORANGE;
                    } else {
                        etatCouleur = Color.BLUE;
                    }
                    
                    if (grilleTab[i][j].getNom().equals("null")) {              // Tuile vide
                        panelGrille.add(new JPanel());
                    } else {
                        btnTuiles[i][j].setText(nomTuile);                      // Tuile classique
                        btnTuiles[i][j].setBackground(etatCouleur);
                        btnTuiles[i][j].setForeground(Color.WHITE);
                        panelGrille.add(btnTuiles[i][j]);
                    }
                }
            }
        }

        JButton btnAssechement = new JButton("Assecher");                       // 
        JButton btnDeplacement = new JButton("Deplacer");
        JButton btnDonnerCarte = new JButton("Donner une Carte");
        JButton btnPrendreTresor = new JButton("Prendre un trésor");
        JButton btnFinTour = new JButton("Fin Du Tour");
        
        btnAssechement.setBackground(Color.LIGHT_GRAY);
        btnDeplacement.setBackground(Color.LIGHT_GRAY);
        btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
        btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
        btnFinTour.setBackground(Color.LIGHT_GRAY);
        
        panel2Centre.add(btnAssechement);
        panel2Centre.add(btnDeplacement);
        panel2Centre.add(btnDonnerCarte);
        panel2Centre.add(btnPrendreTresor);
        panel2.add(btnFinTour, BorderLayout.SOUTH);

        btnAssechement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDepl(false);
                assApp = !assApp;
                
                btnAssechement.setBackground(Color.GRAY);
                btnDeplacement.setBackground(Color.LIGHT_GRAY);
                btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
                btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
                
                Message msg = new Message(TypesMessage.ACTION_Assecher);
                observateur.traiterMessage(msg);
            }
        });

        btnDeplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDepl(true);
                deplApp = !deplApp;
                
                btnAssechement.setBackground(Color.LIGHT_GRAY);
                btnDeplacement.setBackground(Color.GRAY);
                btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
                btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
                
                Message msg = new Message(TypesMessage.ACTION_Deplacer);
                observateur.traiterMessage(msg);
            }
        });

        btnDonnerCarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                btnAssechement.setBackground(Color.LIGHT_GRAY);
                btnDeplacement.setBackground(Color.LIGHT_GRAY);
                btnDonnerCarte.setBackground(Color.GRAY);
                btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
                
                Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                observateur.traiterMessage(msg);
            }
        });
        
        btnPrendreTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                btnAssechement.setBackground(Color.LIGHT_GRAY);
                btnDeplacement.setBackground(Color.LIGHT_GRAY);
                btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
                btnPrendreTresor.setBackground(Color.GRAY);
                
                Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                observateur.traiterMessage(msg);
            }
        });

        btnFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                btnAssechement.setBackground(Color.LIGHT_GRAY);
                btnDeplacement.setBackground(Color.LIGHT_GRAY);
                btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
                btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
                
                Message msg = new Message(TypesMessage.ACTION_Fin);
                observateur.traiterMessage(msg);
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
                    observateur.traiterAction(nomJoueurCourant, x, y, depl);
                }
            });
            }
            
        }
    }
    
    public void afficher() {
        window.setVisible(true);
    }
    
    public void afficherPossible(ArrayList<Tuile> tuilesPossibles){
        for (Tuile tuile: tuilesPossibles){
                btnTuiles[tuile.getX()][tuile.getY()].setBackground(Color.YELLOW);
                btnTuiles[tuile.getX()][tuile.getY()].setEnabled(true);
           
        }
        
    }

    public void fermer() {
        window.dispose();
    }

    private void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void setNom(String nom) {
        this.nomJoueurCourant = nom;
        repaint(); 
    }
    
    public void repaint() {
        for (int i = 0; i <= 5; i++) {                                          // Affichage de la grille
            for (int j = 0; j <= 5; j++) {
                if (grille.getGrille()[i][j].getEtat() == Etat.assechee) {
                    btnTuiles[i][j].setBackground(Color.DARK_GRAY);
                    btnTuiles[i][j].setEnabled(false);
                }
            }
        }
    }

    public String getNom() {
        return nomJoueurCourant;
    }
    
    public boolean getDepl() {
        return deplApp;
    }
    
    public boolean getAss() {
        return assApp;
    }
    
    public void setDepl(boolean bool){
        depl = bool;
    }
    
    public void setAssApp(boolean bool){
        assApp = bool;
    }
    
    public void setDeplApp(boolean bool) {
        deplApp = bool;
    }
    
    private void setPositionDemandee(int i){
        this.positionDemandee = i;
    }
}
