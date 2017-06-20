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
import model.cartesOrange.CarteDosOrange;
import util.Utils.Pion;

/**
 *
 * @author jacquett
 */
public class VueJeu {

    private final JFrame window;
    private static Observateur observateur;
    
    private JButton[][] btnTuiles = new JButton[6][6];
    private JPanel[][] panTuiles = new JPanel[6][6];
    private JPanel[][] panJTuiles = new JPanel[6][6];
    private JPanel panExpl = new JPanel();
    private JPanel panInge = new JPanel();
    private JPanel panMessa = new JPanel();
    private JPanel panNavi = new JPanel();
    private JPanel panPilo = new JPanel();
    private JPanel panPlon = new JPanel();
    
    private JButton btnAssechement = new JButton("Assecher");                       // 
    private JButton btnDeplacement = new JButton("Deplacer");
    private JButton btnDonnerCarte = new JButton("Donner une Carte");
    private JButton btnPrendreTresor = new JButton("Prendre un trésor");
    private JButton btnFinTour = new JButton("Fin Du Tour");
    
    private Grille grille;
    private String nomJoueurCourant;
    private boolean depl;
    private int x, y;
    private JLabel labelJC;
    private JPanel panelGrille, panelPrincipal, panelMenu, panel1, panel2, panel2Centre, panel3, panelMain;
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

        panelPrincipal = new JPanel(new BorderLayout());
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

        window.add(panelPrincipal);
        panelPrincipal.add(panelGrille, BorderLayout.CENTER);
        panelPrincipal.add(panelMenu, BorderLayout.EAST);

        setGrille(gr);
        Tuile[][] grilleTab = grille.getGrille();                               // Récupération du tableau de la grille

        Color etatCouleur = null;
        String nomTuile;
        Border border = new LineBorder(Color.GRAY, 5);
        Border borderJ = new LineBorder(Color.BLACK, 1);
        
        panExpl.setBackground(util.Utils.Pion.VERT.getCouleur());         //initialisation des panels de joueurs
        panInge.setBackground(util.Utils.Pion.ROUGE.getCouleur());
        panMessa.setBackground(util.Utils.Pion.ORANGE.getCouleur());
        panNavi.setBackground(util.Utils.Pion.JAUNE.getCouleur());
        panPilo.setBackground(util.Utils.Pion.BLEU.getCouleur());
        panPlon.setBackground(util.Utils.Pion.VIOLET.getCouleur());
        
        for (int i = 0; i <= 5; i++) {                                          // Initialisation des boutons
            //x = i;
            for (int j = 0; j <= 5; j++){
                //y = j;
                btnTuiles[i][j] = new JButton();
                panTuiles[i][j] = new JPanel(new BorderLayout());
                panJTuiles[i][j] = new JPanel(new GridLayout(1,4));
                
                //btnTuiles[i][j].setEnabled(false);
                panTuiles[i][j].add(panJTuiles[i][j], BorderLayout.SOUTH);
                panTuiles[i][j].add(btnTuiles[i][j], BorderLayout.CENTER);
                panTuiles[i][j].setBorder(border);
                panJTuiles[i][j].setBorder(borderJ);
                
                btnTuiles[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Message msg = new Message(TypesMessage.ACTION_Action);
                        //msg.x = x;
                        //msg.y = y;
                        //observateur.traiterMessage(msg);
                        for (int i = 0; i <= 5; i++) {
                            for (int j = 0; j <= 5; j++) {
                                if (e.getSource().equals(btnTuiles[i][j])) {
                                    x = i;
                                    y = j;
                                    btnTuiles[i][j].setEnabled(false);
                                }
                            }
                        }
                        observateur.traiterAction(nomJoueurCourant, x, y, depl);
                    }
                });
            }

        }

        labelJC = new JLabel(getNom());                              // Affichage du joueur courant


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
                        panelGrille.add(panTuiles[i][j]);
                        
                        afficheJoueurGrille(i, j);
                        
                    }
                }
            }
        }
        
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

        /*for (int i=0;i<=5;i++){
            x = i;
            for (int j=0;j<=5;j++){
                y = j;
                
                btnTuiles[i][j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    observateur.traiterAction(nomJoueurCourant, x, y, depl);
                }
            });
            }
            
        }*/
    }
    
    public void afficher() {
        window.setVisible(true);
    }
    
    
    public void afficherMain(ArrayList<CarteDosOrange> main){
        panelMain = new JPanel(new GridLayout(1, 5));
        panelPrincipal.add(panelMain, BorderLayout.SOUTH);
        
        JButton carte1 = new JButton();
        JButton carte2 = new JButton();
        JButton carte3 = new JButton();
        JButton carte4= new JButton();
        JButton carte5 = new JButton();
        
        
        for (CarteDosOrange carte: main){
            
        }
 
        
    }   
    
    public void afficherPossible(ArrayList<Tuile> tuilesPossibles){
        for (Tuile tuile: tuilesPossibles){
                btnTuiles[tuile.getX()][tuile.getY()].setBackground(Color.YELLOW);
                btnTuiles[tuile.getX()][tuile.getY()].setEnabled(true);
        }
    }
    
    public void finTourObligatoire(){
        btnAssechement.setBackground(Color.GRAY);
        btnDeplacement.setBackground(Color.GRAY);
        btnDonnerCarte.setBackground(Color.GRAY);
        btnPrendreTresor.setBackground(Color.GRAY);
        
        btnAssechement.setEnabled(false);
        btnDeplacement.setEnabled(false);
        btnDonnerCarte.setEnabled(false);
        btnPrendreTresor.setEnabled(false);
    }
    
    public void debutTour(){
        btnAssechement.setBackground(Color.LIGHT_GRAY);
        btnDeplacement.setBackground(Color.LIGHT_GRAY);
        btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
        btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
        
        btnAssechement.setEnabled(true);
        btnDeplacement.setEnabled(true);
        btnDonnerCarte.setEnabled(true);
        btnPrendreTresor.setEnabled(true);
    }

    public void fermer() {
        window.dispose();
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    private void setNom(String nom) {
        this.nomJoueurCourant = nom;
        repaint();
    }
    
    public void changeJoueurCourant(String nomJC, Pion pion){
        setNom(nomJC);
        labelJC.setText(nomJC);
        labelJC.setForeground(pion.getCouleur());
        
    }
    
    public void afficheJoueurGrille(int i, int j){ 
                
                panJTuiles[i][j].removeAll();
                int k = 0;

                if (observateur.getJoueurTuile(grille.getGrille()[i][j]).size() != 0){
                    for (String joueur : observateur.getJoueurTuile(grille.getGrille()[i][j])){    //parcours des joueurs présent sur la tuile
                        if (joueur == "Expl"){
                            panJTuiles[i][j].add(panExpl);
                        } else if (joueur == "Inge"){
                            panJTuiles[i][j].add(panInge);
                        } else if (joueur == "Mess"){
                            panJTuiles[i][j].add(panMessa);
                        } else if (joueur == "Navi"){
                            panJTuiles[i][j].add(panNavi);
                        } else if (joueur == "Pilo"){
                            panJTuiles[i][j].add(panPilo);
                        } else if (joueur == "Plon"){
                            panJTuiles[i][j].add(panPlon);
                        }
                        k++;
                        System.out.println("/// " +joueur);
                    }
                }
                for (int l = k; l < 5; l++){                //Ajout de panel vide pour combler panJTuiles si il ni a pas les 4 aventurier sur la tuile
                    JPanel panDefaut = new JPanel();
                    panDefaut.setBackground(Color.LIGHT_GRAY);
                    panJTuiles[i][j].add(panDefaut);
                }
            }
    
    
    public void repaint() {
        
        for (int i = 0; i <= 5; i++) {                                          // Affichage de la grille
            for (int j = 0; j <= 5; j++) {
                
                
                if (grille.getGrille()[i][j].getEtat() == Etat.assechee) {
                    btnTuiles[i][j].setBackground(Color.DARK_GRAY);
                    btnTuiles[i][j].setEnabled(false);
                }
                        
                afficheJoueurGrille(i, j);
                        
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
    
}
