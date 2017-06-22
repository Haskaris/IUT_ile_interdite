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
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        
        JPanel panelCentre = new JPanel(new GridLayout(6,5));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        JRadioButton j2 = new JRadioButton("2");
        JRadioButton j3 = new JRadioButton("3");
        JRadioButton j4 = new JRadioButton("4");
        j2.setSelected(true);
        
        ButtonGroup groupeNb = new ButtonGroup();
        groupeNb.add(j2);
        groupeNb.add(j3);
        groupeNb.add(j4);
        
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
        
        JButton btnValider = new JButton("Valider");
        JButton btnRetour = new JButton("Retour");
        
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
        
        
        for (int i=1; i<=30; i++){
            switch(i){
                
                case 1:
                    panelCentre.add(new JLabel("Nombre de Joueurs:"));
                break;
                case 2:
                    panelCentre.add(j2);
                break;
                case 3:
                    panelCentre.add(j3);
                break;
                case 4:
                    panelCentre.add(j4);
                break;
                case 6:
                    panelCentre.add(new JLabel("Noms des Joueurs:"));
                break;
                case 7:
                    panelCentre.add(nom1);
                break;
                case 8:
                    panelCentre.add(nom2);
                break;
                case 9:
                    panelCentre.add(nom3);
                break;
                case 10:
                    panelCentre.add(nom4);
                break;
                case 11:
                    panelCentre.add(new JLabel("Difficulté:"));
                break;
                case 12:
                    panelCentre.add(NivNovice);
                break;
                case 13:
                    panelCentre.add(NivNormal);
                break;
                case 14:
                    panelCentre.add(NivElite);
                break;
                case 15:
                    panelCentre.add(NivLegendaire);
                break;
                
                case 26:
                    panelCentre.add(btnValider);
                break;
                case 30:
                    panelCentre.add(btnRetour);
                break;
                
                default:
                    panelCentre.add(new JPanel());
                    
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
