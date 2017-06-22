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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Message;
import util.TypesMessage;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import model.cartesOrange.CarteDosOrange;
import model.cartesOrange.CarteTresor;
import util.Utils;
import util.Utils.Pion;

/**
 *
 * @author jacquett
 */
public class VueJeu {

    // Attribut fenêtre
    private final JFrame window;
    private static Observateur observateur;

    // Attribut panneaux
    private final JPanel[][] panTuiles = new JPanel[6][6];
    private final JPanel[][] panJTuiles = new JPanel[6][6];
    private final JPanel panExpl = new JPanel();
    private final JPanel panInge = new JPanel();
    private final JPanel panMessa = new JPanel();
    private final JPanel panNavi = new JPanel();
    private final JPanel panPilo = new JPanel();
    private final JPanel panPlon = new JPanel();
    private final JPanel panelGrille, panelPrincipal, panelMenu, panelBtnAction, panelBtn, panelTop, panelBottom, panelMain, panelLateral, panelTopMenu, panelEchelle;
    private final JPanel panelNiveau2, panelNiveau3, panelNiveau4, panelNiveau5, panelMort, panelCentrerEchelle;
    private JLabel labelNiveau2, labelNiveau3, labelNiveau4, labelNiveau5, labelMort;

    private final JPanel[][] panTresor = new JPanel[6][6];              // panel qui affiche les tresors sur les tuiles

    // Attributs boutons
    private JButton btnAssechement = new JButton("Assecher");                       // 
    private JButton btnDeplacement = new JButton("Deplacer");
    private JButton btnDonnerCarte = new JButton("Donner une Carte");
    private JButton btnPrendreTresor = new JButton("Prendre un trésor");
    private final JButton btnFinTour = new JButton("Fin Du Tour");
    private JButton btnDefausser = new JButton("Défausser");
    private JButton[] cartesMain = new JButton[5];
    private JButton[][] btnTuiles = new JButton[6][6];

    // Attribut utils
    private Grille grille;
    private String nomJoueurCourant;
    private boolean depl;
    private int x, y, j;
    private final JLabel labelJC;
    private boolean deplApp = false;
    private boolean assApp = false;
    private int tailleMain;

    public VueJeu(Observateur o, Grille gr) {

        // Initialisation de la fenêtre
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(dim.width, dim.height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        window.setTitle("ILE INTERDITE");

        this.observateur = o;

        // Initialisation des panneaux 
        Border border = new LineBorder(Color.GRAY, 5);
        Border borderJ = new LineBorder(Color.BLACK, 1);
        Border borderM = new LineBorder(Color.LIGHT_GRAY, 5);

        panelPrincipal = new JPanel(new BorderLayout());

        panelGrille = new JPanel(new GridLayout(6, 6));                         // Contient la grille
        panelTop = new JPanel(new BorderLayout());
        panelPrincipal.add(panelTop, BorderLayout.CENTER);
        panelTop.add(panelGrille, BorderLayout.CENTER);

        panelBottom = new JPanel(new BorderLayout());                           // Contient les mains
        panelPrincipal.add(panelBottom, BorderLayout.SOUTH);
                         // Panel des boutons + panneaux vides

        panelTopMenu = new JPanel(new BorderLayout());
        
        
        panelEchelle = new JPanel(new GridLayout(5, 1));
        panelCentrerEchelle = new JPanel(new GridLayout(1,7));
        panelCentrerEchelle.add(new JPanel());
        panelCentrerEchelle.add(new JPanel());
        panelCentrerEchelle.add(new JPanel());
        panelCentrerEchelle.add(panelEchelle);
        panelCentrerEchelle.add(new JPanel());
        panelCentrerEchelle.add(new JPanel());
        panelCentrerEchelle.add(new JPanel());
        panelTopMenu.add(panelCentrerEchelle, BorderLayout.WEST);

        panelNiveau2 = new JPanel(new BorderLayout());
        panelNiveau3 = new JPanel(new BorderLayout());
        panelNiveau4 = new JPanel(new BorderLayout());
        panelNiveau5 = new JPanel(new BorderLayout());
        panelMort = new JPanel(new BorderLayout());

        
        ImageIcon icon = createImageIcon("Images/dead.png", "GAME OVER");
        labelMort = new JLabel(icon, JLabel.CENTER);

        labelNiveau2 = new JLabel("2");
        labelNiveau2.setHorizontalAlignment(JLabel.CENTER);
        labelNiveau3 = new JLabel("3");
        labelNiveau3.setHorizontalAlignment(JLabel.CENTER);
        labelNiveau4 = new JLabel("4");
        labelNiveau4.setHorizontalAlignment(JLabel.CENTER);
        labelNiveau5 = new JLabel("5");
        labelNiveau5.setHorizontalAlignment(JLabel.CENTER);

        
        panelNiveau2.setBackground(Color.LIGHT_GRAY);
        panelNiveau2.setBorder(borderJ);
        panelNiveau3.setBackground(Color.LIGHT_GRAY);
        panelNiveau3.setBorder(borderJ);
        panelNiveau4.setBackground(Color.LIGHT_GRAY);
        panelNiveau4.setBorder(borderJ);
        panelNiveau5.setBackground(Color.LIGHT_GRAY);
        panelNiveau5.setBorder(borderJ);
        panelMort.setBackground(Color.LIGHT_GRAY);
        panelMort.setBorder(borderJ);

        panelNiveau2.add(labelNiveau2, BorderLayout.CENTER);
        panelNiveau3.add(labelNiveau3, BorderLayout.CENTER);
        panelNiveau4.add(labelNiveau4, BorderLayout.CENTER);
        panelNiveau5.add(labelNiveau5, BorderLayout.CENTER);
        panelMort.add(labelMort, BorderLayout.CENTER);

        panelEchelle.add(panelMort);
        panelEchelle.add(panelNiveau5);
        panelEchelle.add(panelNiveau4);
        panelEchelle.add(panelNiveau3);
        panelEchelle.add(panelNiveau2);

        
        panelBtn = new JPanel(new BorderLayout());                              // Panel des boutons d'actions
        panelMenu = new JPanel(new GridLayout(3, 1));
        panelMenu.add(panelTopMenu);
        
        panelMenu.add(panelBtn);
        panelMenu.add(new JPanel());
        panelTop.add(panelMenu, BorderLayout.EAST);                              // Contient grille et menu

        panelBtnAction = new JPanel(new GridLayout(2, 2));                       // Panel boutons d'actions (sauf Fin Tour)
        panelBtn.add(panelBtnAction, BorderLayout.CENTER);

        labelJC = new JLabel(getNom());                                         // Initialisation du joueur courant
        panelBottom.add(labelJC, BorderLayout.NORTH);                           // Affichage de nom de joueur courant 
        panelBottom.add(new JPanel());

        panelMain = new JPanel(new GridLayout(1, 5));                            // Panel de la main du joueur principal
        panelMain.setBorder(borderM);
        panelBottom.add(panelMain, BorderLayout.CENTER);

        panelLateral = new JPanel(new GridLayout(1, 5));                         // panel des mains des autres joueurs
        panelLateral.setBorder(borderM);
        panelBottom.add(panelLateral, BorderLayout.EAST);

        // Affichage esthétique des boutons d'actions
        btnAssechement.setBackground(Color.LIGHT_GRAY);
        btnDeplacement.setBackground(Color.LIGHT_GRAY);
        btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
        btnPrendreTresor.setBackground(Color.GRAY);
        btnFinTour.setBackground(Color.LIGHT_GRAY);
        btnDefausser.setBackground(Color.LIGHT_GRAY);

        panelBtnAction.add(btnAssechement);
        panelBtnAction.add(btnDeplacement);
        panelBtnAction.add(btnDonnerCarte);
        panelBtnAction.add(btnPrendreTresor);
        panelBtn.add(btnFinTour, BorderLayout.SOUTH);
        panelBtn.add(btnDefausser, BorderLayout.NORTH);

        window.add(panelPrincipal);

        setGrille(gr);
        Tuile[][] grilleTab = grille.getGrille();                               // Récupération du tableau de la grille

        //initialisation des panels pions
        panExpl.setBackground(util.Utils.Pion.VERT.getCouleur());
        panInge.setBackground(util.Utils.Pion.ROUGE.getCouleur());
        panMessa.setBackground(util.Utils.Pion.ORANGE.getCouleur());
        panNavi.setBackground(util.Utils.Pion.JAUNE.getCouleur());
        panPilo.setBackground(util.Utils.Pion.BLEU.getCouleur());
        panPlon.setBackground(util.Utils.Pion.VIOLET.getCouleur());

        // Initialisation des boutons tuiles
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                panTuiles[i][j] = new JPanel(new BorderLayout());               // Panneau contenant le bouton et le pion
                btnTuiles[i][j] = new JButton();
                panJTuiles[i][j] = new JPanel(new GridLayout(1, 4));             // Panneau contenant les pions

                panTuiles[i][j].add(panJTuiles[i][j], BorderLayout.SOUTH);      // Affichage esthétique
                panTuiles[i][j].add(btnTuiles[i][j], BorderLayout.CENTER);
                panTuiles[i][j].setBorder(border);
                panJTuiles[i][j].setBorder(borderJ);

                btnTuiles[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i <= 5; i++) {
                            for (int j = 0; j <= 5; j++) {
                                if (e.getSource().equals(btnTuiles[i][j])) {    // Vérification que la source du clic provient du bon bouton
                                    x = i;
                                    y = j;
                                    btnTuiles[i][j].setEnabled(false);          // RAZ du bouton
                                }
                            }
                        }
                        observateur.traiterAction(nomJoueurCourant, x, y, depl);    // Communication au contrôleur (assechement/deplacement) en fonction de depl
                    }
                });
            }

        }

        Color etatCouleur = null;
        String nomTuile;

        // Affichage de la grille
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                nomTuile = grilleTab[i][j].getNom();
                switch (grilleTab[i][j].getEtat()) {                            // Gestion de la couleur du bouton en fonction de l'état de la tuile
                    case ASSECHEE:
                        etatCouleur = Color.DARK_GRAY;
                        break;
                    case INONDEE:
                        etatCouleur = Color.ORANGE;
                        break;
                    default:
                        etatCouleur = Color.BLUE;
                        break;
                }

                if (grilleTab[i][j].getNom().equals("null")) {                  // Tuile vide
                    panelGrille.add(new JPanel());
                } else {                                                        // Tuile classique
                    btnTuiles[i][j].setText(nomTuile);
                    btnTuiles[i][j].setBackground(etatCouleur);
                    btnTuiles[i][j].setForeground(Color.WHITE);
                    panelGrille.add(panTuiles[i][j]);

                    afficheJoueurGrille(i, j);                                   // Affiche les pions

                }
            }
        }
        
        btnDefausser.addActionListener(new ActionListener() {                   //Se Defausser d'une ou plusieurs cartes
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });
        
        // Actions Listeners des boutons d'actions
        btnAssechement.addActionListener(new ActionListener() {                 // Assechement
            @Override
            public void actionPerformed(ActionEvent e) {
                setDepl(false);
                assApp = !assApp;
                
                // Desactivation des boutons de mains
                for (int i = 0; i < tailleMain; i++) {
                    cartesMain[i].setEnabled(false);
                }
                repaint();

                Message msg = new Message(TypesMessage.ACTION_Assecher);
                observateur.traiterMessage(msg);
            }
        });

        btnDeplacement.addActionListener(new ActionListener() {                 // Deplacement
            @Override
            public void actionPerformed(ActionEvent e) {
                setDepl(true);
                deplApp = !deplApp;
                
                // Desactivation des boutons de mains
                for (int i = 0; i < tailleMain; i++) {
                    cartesMain[i].setEnabled(false);
                }
                repaint();

                Message msg = new Message(TypesMessage.ACTION_Deplacer);
                observateur.traiterMessage(msg);
            }
        });

        btnDonnerCarte.addActionListener(new ActionListener() {                 // Donner Carte
            @Override
            public void actionPerformed(ActionEvent e) {

                // Activation des boutons de mains
                for (int i = 0; i < tailleMain; i++) {
                    cartesMain[i].setEnabled(true);
                }
                repaint();

            }
        });

        btnPrendreTresor.addActionListener(new ActionListener() {               // Prendre trésor
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Desactivation des boutons de mains
                for (int i = 0; i < tailleMain; i++) {
                    cartesMain[i].setEnabled(false);
                }
                repaint();

                Message msg = new Message(TypesMessage.ACTION_PrendreTresors);
                observateur.traiterMessage(msg);
            }
        });

        btnFinTour.addActionListener(new ActionListener() {                     // Fin de tour
            public void actionPerformed(ActionEvent e) {
                
                // Desactivation des boutons de mains
                for (int i = 0; i < tailleMain; i++) {
                    cartesMain[i].setEnabled(false);
                }
                repaint();

                Message msg = new Message(TypesMessage.ACTION_Fin);
                observateur.traiterMessage(msg);
            }
        });
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void resetMainIHM() {
        // Suppression des mains des autres joueurs
        panelLateral.removeAll();
    }

    public void afficherMain(ArrayList<CarteDosOrange> main, boolean jc, String nomJ, Pion pion) {
        JPanel panelTmp;
        JLabel labelCarteMainAutre;
        tailleMain = Integer.min(main.size(), cartesMain.length);
        System.out.println("longueur carte Main " + cartesMain.length);
        if (jc) {                                                               //Gestion de la main du joueur courant ou autres joueurs?
            panelTmp = panelMain;
            panelTmp.removeAll();                                               //Actualisation des panels
        } else {
            panelTmp = new JPanel(new GridLayout(6, 1));
            JLabel labelJoueur = new JLabel(nomJ);
            labelJoueur.setForeground(pion.getCouleur());
            panelLateral.add(panelTmp);
            panelTmp.removeAll();                                               //Actualisation des panels
            panelTmp.add(labelJoueur);
        }

        int i = 0;
        j = i;
        while (i < tailleMain) {                                                // Parcours de la main du joueur
            if (jc) {                                                            // Création de boutons
                if (main.get(i).getClass().equals(CarteTresor.class)) {
                    cartesMain[i] = new JButton(main.get(i).getTresor().getNomTresor());
                } else {
                    cartesMain[i] = new JButton(main.get(i).getClass().getSimpleName());
                }
                cartesMain[i].setEnabled(false);
                panelTmp.add(cartesMain[i]);

                // Action Listeners de la main principale
                if (main.get(i).getClass().equals(CarteTresor.class)) {
                    cartesMain[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("/// x: " + x + " / longueur taille main: " + main.size() + " / taille main: " + tailleMain);
                            Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                            msg.setCarte(main.get(j));
                            observateur.traiterMessage(msg);
                            
                            // Desactivation des boutons de mains
                            for (int i = 0; i < tailleMain; i++) {
                                cartesMain[i].setEnabled(false);
                            }
                            
                        }
                    });

                }
                j = i;

                if (main.get(i).getClass().getSimpleName().equals("CarteHelicoptere")) {
                    cartesMain[i].setEnabled(true);
                    cartesMain[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            observateur.utiliserCarteHelicoptere();
                        }
                    });
                } else if (main.get(i).getClass().getSimpleName().equals("CarteSacDeSable")) {
                    cartesMain[i].setEnabled(true);
                    cartesMain[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            observateur.utiliserCarteSacDeSable();// Communication au contrôleur (assechement/deplacement) en fonction de depl
                        }
                    });
                } else {
                    cartesMain[i].setEnabled(false);

                }

            } else {                                                              // Création de label
                if (main.get(i).getClass().equals(CarteTresor.class)) {
                    labelCarteMainAutre = new JLabel(main.get(i).getTresor().getNomTresor());
                } else {
                    labelCarteMainAutre = new JLabel(main.get(i).getClass().getSimpleName());
                }
                panelTmp.add(labelCarteMainAutre);
            }
            i++;

        }
        window.revalidate();

    }

    public void afficherTresors(ArrayList<Tresor> tresors) {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (grille.getGrille()[i][j].getTresor() == tresors.get(0)) {
                    panTresor[i][j] = new JPanel();
                    panTresor[i][j].setBackground(Color.lightGray);
                    panTresor[i][j].setBorder(new LineBorder(Color.white, 2));
                    panTuiles[i][j].add(panTresor[i][j], BorderLayout.NORTH);

                } else if (grille.getGrille()[i][j].getTresor() == tresors.get(1)) {
                    panTresor[i][j] = new JPanel();
                    panTresor[i][j].setBackground(Color.yellow);
                    panTresor[i][j].setBorder(new LineBorder(Color.white, 2));
                    panTuiles[i][j].add(panTresor[i][j], BorderLayout.NORTH);

                } else if (grille.getGrille()[i][j].getTresor() == tresors.get(2)) {
                    panTresor[i][j] = new JPanel();
                    panTresor[i][j].setBackground(Color.red);
                    panTresor[i][j].setBorder(new LineBorder(Color.white, 2));
                    panTuiles[i][j].add(panTresor[i][j], BorderLayout.NORTH);

                } else if (grille.getGrille()[i][j].getTresor() == tresors.get(3)) {
                    panTresor[i][j] = new JPanel();
                    panTresor[i][j].setBackground(Color.cyan);
                    panTresor[i][j].setBorder(new LineBorder(Color.white, 2));
                    panTuiles[i][j].add(panTresor[i][j], BorderLayout.NORTH);

                }
            }

        }

    }

    public void afficherPossible(ArrayList<Tuile> tuilesPossibles) {
        for (Tuile tuile : tuilesPossibles) {                                     // Parcours des tuiles possibles
            btnTuiles[tuile.getX()][tuile.getY()].setBackground(Color.YELLOW);  //Affichage en jaune 
            btnTuiles[tuile.getX()][tuile.getY()].setEnabled(true);         // Activation des boutons
        }
    }

    public void tresorPossible() {
        btnPrendreTresor.setBackground(Color.LIGHT_GRAY);
        btnPrendreTresor.setEnabled(true);
    }

    public void finTourObligatoire() {

        // Affichage de la fin de tour 
        btnAssechement.setBackground(Color.GRAY);
        btnDeplacement.setBackground(Color.GRAY);
        btnDonnerCarte.setBackground(Color.GRAY);
        btnPrendreTresor.setBackground(Color.GRAY);
        btnDefausser.setBackground(Color.GRAY);

        // Désactivation des boutons d'actions sauf Fin de tour
        btnAssechement.setEnabled(false);
        btnDeplacement.setEnabled(false);
        btnDonnerCarte.setEnabled(false);
        btnPrendreTresor.setEnabled(false);
    }

    public void debutTour() {
        // Affichade du début de tour
        btnAssechement.setBackground(Color.LIGHT_GRAY);
        btnDeplacement.setBackground(Color.LIGHT_GRAY);
        btnDonnerCarte.setBackground(Color.LIGHT_GRAY);
        btnPrendreTresor.setBackground(Color.GRAY);
        btnDefausser.setBackground(Color.LIGHT_GRAY);

        // Activation des boutons d'actions
        btnAssechement.setEnabled(true);
        btnDeplacement.setEnabled(true);
        btnDonnerCarte.setEnabled(true);
        btnPrendreTresor.setEnabled(false);
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

    public void changeJoueurCourant(String nomJC, Pion pion) {
        // Mise à jour des différents champs liés au joueur Courant
        setNom(nomJC);
        labelJC.setText(nomJC);
        labelJC.setForeground(pion.getCouleur());

    }

    public void afficheJoueurGrille(int i, int j) {
        panJTuiles[i][j].removeAll();                                           // Suppression des anciens pions
        int k = 0;                                                              // Nombre de pions sur une tuile

        if (observateur.getJoueurTuile(grille.getGrille()[i][j]).size() != 0) { // Si la tuile contient au moins un joueur
            for (String joueur : observateur.getJoueurTuile(grille.getGrille()[i][j])) {    //parcours des joueurs présent sur la tuile
                if (joueur == "Expl") {                                         // Ajout du panel correspondant au type de joueur
                    panJTuiles[i][j].add(panExpl);
                } else if (joueur == "Inge") {
                    panJTuiles[i][j].add(panInge);
                } else if (joueur == "Mess") {
                    panJTuiles[i][j].add(panMessa);
                } else if (joueur == "Navi") {
                    panJTuiles[i][j].add(panNavi);
                } else if (joueur == "Pilo") {
                    panJTuiles[i][j].add(panPilo);
                } else if (joueur == "Plon") {
                    panJTuiles[i][j].add(panPlon);
                }
                k++;
            }
        }

        //Ajout de panel vide pour combler panJTuiles si il ni a pas les 4 aventurier sur la tuile
        for (int l = k; l < 5; l++) {
            JPanel panDefaut = new JPanel();
            panDefaut.setBackground(Color.LIGHT_GRAY);
            panJTuiles[i][j].add(panDefaut);
        }
    }

    public void repaint() {

        // Affichage de la grille
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (grille.getGrille()[i][j].getEtat() == Utils.EtatTuile.ASSECHEE) {
                    btnTuiles[i][j].setBackground(Color.DARK_GRAY);
                    btnTuiles[i][j].setEnabled(false);
                }
                if (grille.getGrille()[i][j].getEtat() == Utils.EtatTuile.INONDEE) {
                    btnTuiles[i][j].setBackground(Color.ORANGE);
                    btnTuiles[i][j].setEnabled(false);
                }
                if (grille.getGrille()[i][j].getEtat() == Utils.EtatTuile.COULEE) {
                    btnTuiles[i][j].setBackground(Color.BLUE);
                    btnTuiles[i][j].setEnabled(false);
                }
                afficheJoueurGrille(i, j);
            }
        }

        window.revalidate();
    }

    public String getNom() {
        return nomJoueurCourant;
    }

    public boolean getDeplApp() {
        return deplApp;
    }

    public boolean getAss() {
        return assApp;
    }

    public boolean getDepl() {
        return depl;
    }

    public void setDepl(boolean bool) {
        depl = bool;
    }

    public void setAssApp(boolean bool) {
        assApp = bool;
    }

    public void setDeplApp(boolean bool) {
        deplApp = bool;
    }

    public void afficherNiveau(int niv) {
        int i = 2;
        while (i != niv) {
            i++;
        }

        panelNiveau2.setBackground(Color.LIGHT_GRAY);
        panelNiveau3.setBackground(Color.LIGHT_GRAY);
        panelNiveau4.setBackground(Color.LIGHT_GRAY);
        panelNiveau5.setBackground(Color.LIGHT_GRAY);

        switch (i) {
            case 2:
                panelNiveau2.setBackground(Color.BLUE);
                panelNiveau3.setBackground(Color.LIGHT_GRAY);
                panelNiveau4.setBackground(Color.LIGHT_GRAY);
                panelNiveau5.setBackground(Color.LIGHT_GRAY);
                break;
            case 3:
                panelNiveau2.setBackground(Color.LIGHT_GRAY);
                panelNiveau3.setBackground(Color.BLUE);
                panelNiveau4.setBackground(Color.LIGHT_GRAY);
                panelNiveau5.setBackground(Color.LIGHT_GRAY);
                break;
            case 4:
                panelNiveau2.setBackground(Color.LIGHT_GRAY);
                panelNiveau3.setBackground(Color.LIGHT_GRAY);
                panelNiveau4.setBackground(Color.BLUE);
                panelNiveau5.setBackground(Color.LIGHT_GRAY);
                break;
            case 5:
                panelNiveau5.setBackground(Color.BLUE);
                panelNiveau2.setBackground(Color.LIGHT_GRAY);
                panelNiveau3.setBackground(Color.LIGHT_GRAY);
                panelNiveau4.setBackground(Color.LIGHT_GRAY);
        }
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
