/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import util.Message;
import util.TypesMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author reyneu
 */
public class VueParamJeu {
    private final JFrame window;
    private final Observateur Observateur;
    
    
    public VueParamJeu(Observateur o){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("PARAMETRE DU JEU");
        
        Observateur = o;
        
        Border border = new LineBorder(Color.WHITE, 5);
        Border borderB = new LineBorder(Color.GRAY, 5);
        
        
        JPanel mainPanel = new JPanel(new GridLayout(5,1));
        window.add(mainPanel);
        
        JPanel panelNbJoueur = new JPanel(new GridLayout(1,6));
        panelNbJoueur.setBorder(borderB);
        JRadioButton j2 = new JRadioButton("2");
        JRadioButton j3 = new JRadioButton("3");
        JRadioButton j4 = new JRadioButton("4");
        j2.setSelected(true);
        
        panelNbJoueur.add(new JLabel("Nombre de Joueurs:"));
        panelNbJoueur.add(j2);
        panelNbJoueur.add(j3);
        panelNbJoueur.add(j4);
        
        ButtonGroup groupeNb = new ButtonGroup();
        groupeNb.add(j2);
        groupeNb.add(j3);
        groupeNb.add(j4);
        
        JPanel panelNomJoueur = new JPanel(new GridLayout(1,6));
        panelNomJoueur.setBorder(borderB);
        JPanel nom1 = new JPanel(new GridLayout(5,1));
        nom1.setBorder(border);
        JTextField nomJ1 = new JTextField("Joueur 1");
        nom1.add(new JPanel());
        nom1.add(new JPanel());
        nom1.add(nomJ1);
        nom1.add(new JPanel());
        nom1.add(new JPanel());
        
        JPanel nom2 = new JPanel(new GridLayout(5,1));
        nom2.setBorder(border);
        JTextField nomJ2 = new JTextField("Joueur 2");
        nom2.add(new JPanel());
        nom2.add(new JPanel());
        nom2.add(nomJ2);
        nom2.add(new JPanel());
        nom2.add(new JPanel());
        
        
        JPanel nom3 = new JPanel(new GridLayout(5,1));
        nom3.setBorder(border);
        JTextField nomJ3 = new JTextField("Joueur 3");
        nom3.add(new JPanel());
        nom3.add(new JPanel());
        nom3.add(nomJ3);
        nom3.add(new JPanel());
        nom3.add(new JPanel());
        
        
        JPanel nom4 = new JPanel(new GridLayout(5,1));
        nom4.setBorder(border);
        JTextField nomJ4 = new JTextField("Joueur 4");
        nom4.add(new JPanel());
        nom4.add(new JPanel());
        nom4.add(nomJ4);
        nom4.add(new JPanel());
        nom4.add(new JPanel());
        
        panelNomJoueur.add(new JLabel("Noms des Joueurs:"));
        panelNomJoueur.add(nom1);
        panelNomJoueur.add(nom2);
        panelNomJoueur.add(nom3);
        panelNomJoueur.add(nom4);
        
        
        JPanel panelNiveau = new JPanel(new GridLayout(1,6));
        panelNiveau.setBorder(borderB);
        JRadioButton NivLegendaire = new JRadioButton("Legendaire");
        JRadioButton NivElite = new JRadioButton("Elite");
        JRadioButton NivNormal = new JRadioButton("Normal");
        NivNormal.setSelected(true);
        JRadioButton NivNovice = new JRadioButton("Novice");
        
        ButtonGroup groupeNiveau = new ButtonGroup();
        groupeNiveau.add(NivNovice);
        groupeNiveau.add(NivNormal);
        groupeNiveau.add(NivElite);
        groupeNiveau.add(NivLegendaire);
        
        panelNiveau.add(new JLabel("Difficulté:"));
        panelNiveau.add(NivNovice);
        panelNiveau.add(NivNormal);
        panelNiveau.add(NivElite);
        panelNiveau.add(NivLegendaire);
        
        
        JPanel panelBtnFin = new JPanel(new GridLayout(1,5));
        JButton btnValider = new JButton("Valider");
        JButton btnRetour = new JButton("Retour");
        
        panelBtnFin.add(btnValider);
        panelBtnFin.add(new JPanel());
        panelBtnFin.add(new JPanel());
        panelBtnFin.add(new JPanel());
        panelBtnFin.add(btnRetour);
        
        
        mainPanel.add(panelNbJoueur);
        mainPanel.add(panelNomJoueur);
        mainPanel.add(panelNiveau);
        mainPanel.add(new JPanel());
        mainPanel.add(panelBtnFin);
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessage.ACTION_Retour);
                o.traiterMessage(m);
            }
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nbJoueur;
                int niveau;
                if (j2.isSelected()) {
                    nbJoueur = 2;
                } else if (j3.isSelected()) {
                    nbJoueur = 3;
                } else {
                    nbJoueur = 4;
                }
                
                if (NivLegendaire.isSelected()) {
                    niveau = 4;
                } else if (NivElite.isSelected()) {
                    niveau = 3;
                } else if (NivNormal.isSelected()) {
                    niveau = 2;
                } else {
                    niveau = 1;
                }
                
                o.envoyerDonnees(nbJoueur, nomJ1.getText(), nomJ2.getText(), nomJ3.getText(), nomJ4.getText(), niveau); // 0 = nbjoueurs && 0 = difficulté
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
