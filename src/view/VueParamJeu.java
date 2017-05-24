/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import ile_interdite.Controller;
import ile_interdite.Message;
import ile_interdite.TypesMessage;
import java.awt.BorderLayout;
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

/**
 *
 * @author reyneu
 */
public class VueParamJeu {
    private final JFrame window;
    private final Controller controller;
    
    
    public VueParamJeu(Controller c){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("PARAMETRE DU JEU");
        
        controller = c;
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        JPanel panelTitre = new JPanel();
        mainPanel.add(panelTitre, BorderLayout.NORTH);
        
        JLabel titre = new JLabel("Paramètres du jeu");
        panelTitre.add(titre);
        
        JPanel panelCentre = new JPanel(new GridLayout(5,5));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        JRadioButton J2 = new JRadioButton("2");
        JRadioButton J3 = new JRadioButton("3");
        JRadioButton J4 = new JRadioButton("4");
        J4.setSelected(true);
        
        ButtonGroup groupeNb = new ButtonGroup();
        groupeNb.add(J2);
        groupeNb.add(J3);
        groupeNb.add(J4);
        
        JTextField NomJ1 = new JTextField("Joueur 1");
        JTextField NomJ2 = new JTextField("Joueur 2");
        JTextField NomJ3 = new JTextField("Joueur 3");
        JTextField NomJ4 = new JTextField("Joueur 4");
        JRadioButton NivLegendaire = new JRadioButton("Legendaire");
        JRadioButton NivElite = new JRadioButton("Elite");
        JRadioButton NivNormal = new JRadioButton("Normal");
        NivNormal.setSelected(true);
        JRadioButton NivNovice = new JRadioButton("Novice");
        
        ButtonGroup groupeNiveau = new ButtonGroup();
        groupeNiveau.add(NivLegendaire);
        groupeNiveau.add(NivElite);
        groupeNiveau.add(NivNormal);
        groupeNiveau.add(NivNovice);
        
        JButton btnValider = new JButton("Valider");
        JButton btnRetour = new JButton("Retour");
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypesMessage.ACTION_Retour);
                c.traiterMessage(m);
            }
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypesMessage.ACTION_Valider);
                c.traiterMessage(m);
            }
        });
        
        
        for (int i=1; i<=25; i++){
            switch(i){
                
                case 1:
                    panelCentre.add(new JLabel("Nombre de Joueurs:"));
                break;
                case 2:
                    panelCentre.add(J2);
                break;
                case 3:
                    panelCentre.add(J3);
                break;
                case 4:
                    panelCentre.add(J4);
                break;
                case 6:
                    panelCentre.add(new JLabel("Noms des Joueurs:"));
                break;
                case 7:
                    panelCentre.add(NomJ1);
                break;
                case 8:
                    panelCentre.add(NomJ2);
                break;
                case 9:
                    panelCentre.add(NomJ3);
                break;
                case 10:
                    panelCentre.add(NomJ4);
                break;
                case 11:
                    panelCentre.add(new JLabel("Difficulté:"));
                break;
                case 12:
                    panelCentre.add(NivLegendaire);
                break;
                case 13:
                    panelCentre.add(NivElite);
                break;
                case 14:
                    panelCentre.add(NivNormal);
                break;
                case 15:
                    panelCentre.add(NivNovice);
                break;
                
                case 21:
                    panelCentre.add(btnValider);
                break;
                case 25:
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
