/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
public class vueAQuiDonner {

    private Observateur observateur;
    private JFrame window;
    private JLabel question;
    private JPanel panelPrincipal, panelTop, panelCentre;
    private JButton[] btnChoix;
    private String nomChoix;

    public vueAQuiDonner(Observateur o, ArrayList<String> nomJ) {
        setObservateur(o);

        // Initialisation de la fenêtre        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window = new JFrame();
        window.setSize(800, 800);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        window.setTitle("Donner Carte");

        // Panneau principal
        panelPrincipal = new JPanel(new BorderLayout());
        window.add(panelPrincipal);

        // Panneau question
        panelTop = new JPanel();
        question = new JLabel("A qui souhaitez-vous donner votre carte?");
        panelTop.add(question);
        panelPrincipal.add(panelTop, BorderLayout.NORTH);

        // Panneau boutons
        panelCentre = new JPanel(new GridLayout(2, 3));
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);

        // Initialisation et affichage des boutons
        btnChoix = new JButton[nomJ.size()];

        int k = 0;
        for (String nom : nomJ) {
            btnChoix[nomJ.indexOf(nom)] = new JButton(nom);
            panelCentre.add(btnChoix[nomJ.indexOf(nom)]);

            nomChoix = nom;
            btnChoix[nomJ.indexOf(nom)].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();                              // Association de la source du clic avec le bon bouton

                    for (int i = 0; i < nomJ.size(); i++) {
                        if (source.equals(btnChoix[i])) {
                            nomChoix = btnChoix[i].getText();
                        }
                    }
                    Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                    msg.setString(nomChoix);
                    observateur.traiterMessage(msg);

                }
            });
            k++;
        }

        while (k < 4) {                                                         // Comble avec des panneaux vides
            panelCentre.add(new JPanel());
            k++;
        }
        JButton btnRetour = new JButton("Annuler");                             // Bouton annuler don
        panelCentre.add(btnRetour);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_RETOUR_DONNER);
                observateur.traiterMessage(msg);
            }
        });

        panelCentre.add(new JPanel());

    }

    public void setObservateur(Observateur o) {
        this.observateur = o;
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void fermer() {
        window.dispose();
    }

    public void repaint(ArrayList<String> nomJ) {
        panelCentre.removeAll();                                                // Suppression des anciens boutons
        btnChoix = new JButton[nomJ.size()];
        int k = 0;
        for (String nom : nomJ) {
            btnChoix[nomJ.indexOf(nom)] = new JButton(nom);
            panelCentre.add(btnChoix[nomJ.indexOf(nom)]);

            btnChoix[nomJ.indexOf(nom)].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();

                    for (int i = 0; i < nomJ.size(); i++) {
                        if (source.equals(btnChoix[i])) {
                            nomChoix = btnChoix[i].getText();
                        }
                    }
                    Message msg = new Message(TypesMessage.ACTION_DonnerCarte);
                    msg.setString(nomChoix);
                    observateur.traiterMessage(msg);
                }
            });
            k++;
        }

        while (k < 4) {
            panelCentre.add(new JPanel());
            k++;
        }
        JButton btnRetour = new JButton("Annuler");
        panelCentre.add(btnRetour);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_RETOUR_DONNER);
                observateur.traiterMessage(msg);
            }
        });

        panelCentre.add(new JPanel());
        window.revalidate();

    }

}
