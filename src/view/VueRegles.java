/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import ile_interdite.Controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author reyneu
 */
public class VueRegles {
    private final JFrame window;
    private final Controller controller;
    
    
    public VueRegles(Controller c){
        
        this.window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("REGLES DU JEU");
        
        controller = c;
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    
    public void fermer() {
        window.dispose();
    }
}
