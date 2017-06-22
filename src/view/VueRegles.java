/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controlleur.Observateur;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import util.Message;
import util.TypesMessage;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author reyneu
 */
public class VueRegles {
    private final JFrame window;
    private final Observateur o;
    
    CardLayout cl = new CardLayout();
    JPanel content = new JPanel();
    String[] listContent = {"REGLE_1", "REGLE_2", "REGLE_3", "REGLE_4", "REGLE_5", "REGLE_6", "REGLE_7", "REGLE_8"};
    int indice = 0;
    
    
    public VueRegles(Observateur o) {
        this.o = o;
        this.window = new JFrame();
        window.setSize(705, 980);
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("REGLES DU JEU");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        
        ImageIcon icon1 = createImageIcon("Images/Regle1.png",
                                 "Regle1");
        JLabel labelRegle1 = new JLabel(icon1, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon2 = createImageIcon("Images/Regle2.png",
                                 "Regle1");
        JLabel labelRegle2 = new JLabel(icon2, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon3 = createImageIcon("Images/Regle3.png",
                                 "Regle1");
        JLabel labelRegle3 = new JLabel(icon3, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon4 = createImageIcon("Images/Regle4.png",
                                 "Regle1");
        JLabel labelRegle4 = new JLabel(icon4, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon5 = createImageIcon("Images/Regle5.png",
                                 "Regle1");
        JLabel labelRegle5 = new JLabel(icon5, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon6 = createImageIcon("Images/Regle6.png",
                                 "Regle1");
        JLabel labelRegle6 = new JLabel(icon6, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon7 = createImageIcon("Images/Regle7.png",
                                 "Regle1");
        JLabel labelRegle7 = new JLabel(icon7, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        
        ImageIcon icon8 = createImageIcon("Images/Regle8.png",
                                 "Regle1");
        JLabel labelRegle8 = new JLabel(icon8, JLabel.CENTER);
        mainPanel.add(labelRegle1, BorderLayout.CENTER);
        

        JButton boutonSuivant = new JButton("Page Suivante");
        JButton boutonPrecedent = new JButton("Page Précédente");
        

        boutonSuivant.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            //Via cette instruction, on passe a la page suivante
            cl.next(content);
          }
        });
        
        
        boutonPrecedent.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            //Via cette instruction, on passe a la page precedente
            cl.previous(content);
          }
        });

        content.setLayout(cl);
        
        //On ajoute les règles à la pile
        content.add(labelRegle1, listContent[0]);
        content.add(labelRegle2, listContent[1]);
        content.add(labelRegle3, listContent[2]);
        content.add(labelRegle4, listContent[3]);
        content.add(labelRegle5, listContent[4]);
        content.add(labelRegle6, listContent[5]);
        content.add(labelRegle7, listContent[6]);
        content.add(labelRegle8, listContent[7]);

        mainPanel.add(content, BorderLayout.CENTER);

        
        JPanel panelNorth = new JPanel(new GridLayout(1,3));
        mainPanel.add(panelNorth, BorderLayout.SOUTH);
        JButton btnRetour = new JButton("Retour");
        panelNorth.add(boutonPrecedent);
        panelNorth.add(btnRetour);
        panelNorth.add(boutonSuivant);
        
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypesMessage.ACTION_Retour);
                o.traiterMessage(msg);
            }
        });
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
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
