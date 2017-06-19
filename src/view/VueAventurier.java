package view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import model.aventurier.Aventurier;
import util.Message;
import static util.TypesMessage.*;
import util.Utils.Pion;

 
public class VueAventurier  {
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnDeplacer;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private final JTextField position;
    private static Controlleur.Controller controlleur;
    private String nomJoueur;
    
    public VueAventurier (String nomJoueur, String nomAventurier, Color couleur, Controlleur.Controller c){

        this.nomJoueur = nomJoueur;
        
        this.window = new JFrame();
        window.setSize(350, 200);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
        
        controlleur = c;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnDeplacer = new JButton("Deplacer") ;                                  //Création des boutons
        this.btnAssecher = new JButton( "Assecher");
        this.btnAutreAction = new JButton("Voir Deplacements") ;
        this.btnTerminerTour = new JButton("Voir assechements") ;
        
        this.panelBoutons.add(btnDeplacer);                                        //Ajout des boutons
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);
                            
        btnTerminerTour.addActionListener(new ActionListener() {                //Ajout d'un listener sur chacun des boutons
            @Override                                                           //Pour pouvoir executer une action
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(ACTION_Fin);
                c.traiterMessage(msg);          //Permet de voir les assechements possible
            }
        });
        
        btnDeplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(ACTION_Deplacer);
                c.traiterMessage(msg);
                c.traiterAction(msg, getJoueur(), position.getText(), true);//Permet de se déplacer à la poisition
            }                                                                   //écrite dans le champs texte
        });
        
        btnAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(ACTION_Assecher);
                c.traiterMessage(msg);
                c.traiterAction(msg, getJoueur(), position.getText(), false);//Permet d'assecher la tuile indiqué
            }                                                                   //par la position écrite dans le champs texte
        });
        
        btnAutreAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(ACTION_Autre);
                c.traiterMessage(msg);
                c.afficherTuilesPossibles(nomJoueur, true);           //Permet de voir les déplacements possible
            }
        });
        
        
        this.window.setVisible(true);
        mainPanel.repaint();
        
    }  

     public JButton getBtnAutreAction() {
        System.out.println("boutonAutreAction");
        return btnAutreAction;
    }

    public void setPosition(String pos) {
        this.position.setText(pos);
    }

    public JButton getBtnDeplacer() {
        System.out.println("boutonDeplacer");
        return btnDeplacer;
    }
    
    public JButton getBtnAssecher() {
        System.out.println("boutonAssecher");
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        System.out.println("boutonFinTour");
        return btnTerminerTour;
    }
 
    public String getJoueur() {
        return nomJoueur;
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    public void cacher(){
        window.setVisible(false);
    }
    
     public static void main(String [] args) {
        // Instanciation de la fenêtre 
       // VueAventurier vueAventurier = new VueAventurier ("Manon", "Explorateur",Pion.ROUGE.getCouleur() );
        
        
        
        
        
        //
    }
}

 


