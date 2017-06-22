package Controlleur;

import java.util.ArrayList;
import java.util.Collections;
import model.cartesOrange.CarteDosOrange;
import model.cartesOrange.CarteHelicoptere;
import model.cartesOrange.CarteMonteeDesEaux;
import model.cartesOrange.CarteSacDeSable;
import model.cartesOrange.CarteTresor;
import model.aventurier.Aventurier;
import model.aventurier.Explorateur;
import model.aventurier.Ingenieur;
import model.aventurier.Messager;
import model.aventurier.Navigateur;
import model.aventurier.Pilote;
import model.aventurier.Plongeur;
import model.CarteInondation;
import model.Echelle;
import model.Grille;
import model.Tresor;
import model.Tuile;
import util.Message;
import util.Parameters;
import util.TypesMessage;
import util.Utils;
import util.Utils.EtatTuile;
import util.Utils.Pion;
import static util.Utils.afficherInformation;
import view.*;

public class Controller implements Observateur {

    //Initialisation des attributs
    private static VueBienvenue bienvenue;
    private static VueParamJeu paramJeu;
    private static VueRegles regles;
    private static VueJeu jeu;
    private static VuePopUp popUp;
    private static VueAQuiDonner vueDonnerCarte;

    private static int nbAction = 0;
    private static int numJC = 0;
    private static int assechementInge = 0;
    private static int actionNavi = 0;
    private static int xTemp, yTemp, difficulte, nbJoueurs;
    
    private static Echelle echelle;
    
    private static Grille grilleJeu;
    
    private static boolean utilisationCH = false;
    private static boolean afficheCH = false;
    private static boolean utilisationCSS = false;
    private static boolean tresorRecup, CHErreur = false, CSSErreur = false;
    
    private static String nomJ1, nomJ2, nomJ3, nomJ4;
    private String nomJoueurDonne;
    
    private CarteDosOrange carteDonne;
    
    private static Aventurier av1, av2, av3, av4, joueurC;
    
    private static ArrayList<Aventurier> joueurs;
    private static ArrayList<CarteDosOrange> piocheOrange, defausseOrange;
    private static ArrayList<CarteInondation> piocheInondation, defausseInondation;
    private static ArrayList<Tresor> tresors, tresorsGagnés, tresorsCompare;
    private static ArrayList<Tuile> tuilesPossibles = new ArrayList<>();

    public static void main(String[] args) {
        new Controller();
    }

    public Controller() {
        grilleJeu = new Grille();
        joueurs = new ArrayList<>();
        bienvenue = new VueBienvenue(this);
        paramJeu = new VueParamJeu(this);
        regles = new VueRegles(this);
        jeu = new VueJeu(this, grilleJeu);
        setGrilleJeu(grilleJeu);
        defausseOrange = new ArrayList<>();
        defausseInondation = new ArrayList<>();
        créerTresors();
        remplirPiocheOrange();
        remplirPiocheInondation();
        bienvenue.afficher();
        
        ArrayList<String> nomJoueurs = new ArrayList<>();

        vueDonnerCarte = new VueAQuiDonner(this, nomJoueurs);
    }                                         //Initialise le controlleur

    public static void setGrilleJeu(Grille GrilleJeu) {                         
        grilleJeu = GrilleJeu;
        for (Aventurier av : joueurs) {
            av.setGrille(GrilleJeu);
        }
        jeu.setGrille(GrilleJeu);
    }            //Permet de lier toute les grilles

    @Override
    public void traiterMessage(Message msg) {                                   
        if (msg.getTypeMessage() == TypesMessage.ACTION_Jouer) {                //Appuie sur le bouton jouer
            bienvenue.fermer();
            paramJeu.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Retour) {        //Appuie sur le bouton retour
            paramJeu.fermer();
            regles.fermer();
            bienvenue.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_RetourSecond) {  //Appuie sur le bouton retour en jeu
            vueDonnerCarte.fermer();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_AnnulerDefausse) {
            popUp.fermer();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Regles) {        //Appuie sur le bouton règles
            bienvenue.fermer();
            regles.afficher();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Quitter) {       //Appuie sur le bouton quitter
            bienvenue.fermer();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Deplacer) {      //Appuie sur le bouton déplacer en jeu
            if (jeu.getDeplApp()) {                                             //Si le bouton n'a pas été appuyé avant
                if (jeu.getAss()) {                                             //Si le bouton pressé précédement était le bouton de l'assechage
                    jeu.repaint();
                    jeu.setAssApp(false);
                }
                jeu.afficherPossible(joueurC.getTuilesPossibles(true));         //Affichage les tuiles où le deplacement est possible
                //setGrilleJeu(joueurC.getGrilleAv());
            } else {                                                            //Si le bouton a déjà été pressé
                jeu.repaint();
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_DonnerCarte) {   //Appuie sur le bouton donner carte en jeu
            if (msg.getCarte() == null) {                                       //Provenance: vueDonnerCarte(choix joueur)
                nomJoueurDonne = msg.getString();
            } else {                                                            //Provenance: vueJeu(choix carte)
                carteDonne = msg.getCarte();
            }
            ArrayList<String> nomJoueurs = new ArrayList<>();                   //Contient la liste des noms des joueurs pour lesquels le don est possible
            for (Aventurier av : joueurs) {
                if (!av.equals(joueurC) && joueurC.getPosition().equals(av.getPosition())) { //N'est pas le joueur courant et se trouvent sur la même position
                    nomJoueurs.add(av.getNom());
                } else if (!av.equals(joueurC) && joueurC.getClass().equals(Messager.class)) { //Le joueur courant est un messager. On ajoute tout le monde
                    nomJoueurs.add(av.getNom());
                }
            }
            vueDonnerCarte.repaint(nomJoueurs);                                 //Affichage des joueurs possibles
            vueDonnerCarte.afficher();
            if (vueDonnerCarte == null) {
                vueDonnerCarte = new VueAQuiDonner(this, nomJoueurs);
            }
            if (getAventurier(nomJoueurDonne, joueurs) != null && nbAction < 4) {                           //Si on a le joueur à qui donner
                Boolean bool = joueurC.donnerCarte(carteDonne, getAventurier(nomJoueurDonne, joueurs));     //On donne la carte
                if (!bool) {                                                                                //Si don raté
                    afficherInformation("La carte n'a pas été donnée: \n " + "  " + nomJoueurDonne + " n'a pas assez de place "); //Message d'erreur
                    vueDonnerCarte.fermer();
                } else {                                                                                    //Si don réussi
                    vueDonnerCarte.fermer();                                                                //Fermeture vueDonnerCarte
                    afficherMainJoueur();                                                                   //Actualisation de la main
                    jeu.repaint();
                    nbAction++;                                                                             //Action utilisée
                    if (nbAction >= 3) {                                                                    //Désactivation des boutons d'actions
                        jeu.finTourObligatoire();
                    }
                }
                nomJoueurDonne = null;                                                                      //RAZ des variables
                carteDonne = null;
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Assecher) {      //Appuie sur le bouton assecher en jeu
            if (jeu.getAss()) {                                                 //Si le bouton n'a pas été appuyé avant
                if (jeu.getDeplApp()) {                                         //Si le bouton pressé précédement était le bouton de déplacement
                    jeu.repaint();
                    jeu.setDeplApp(false);
                }
                //setGrilleJeu(joueurC.getGrilleAv());
                jeu.afficherPossible(joueurC.getTuilesPossibles(false));        //Affichage les tuiles où l'assechement est possible
            } else {
                jeu.repaint();
            }
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_Fin) {           //Appuie sur le bouton fin du tour en jeu
            if (numJC == nbJoueurs-1) {                                         //Changement du numéro de joueur courant
                numJC = 0;
            } else {
                numJC++;
            }
            jeu.setDeplApp(false);                                              //Réinitialisation des boutons
            jeu.setAssApp(false);
            piocherDeuxCartesOrange();                                          //Pioche les cartes
            piocherCartesInondation();
            setGrilleJeu(joueurC.getGrilleAv());                                //Met à jour la grille
            tourDeJeu();                                                        //Change de tour
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_ChoixCarteASupprimer) { //Appuie sur une carte lors de la demande de suppression de carte
            jeu.debutTour();
            popUp.fermer();
            enleverCarteSurplus(joueurC.getMain().get(msg.getNumBtn()));        //Retire la carte de la main du joueur et la rajoute dans la defausse
            if (joueurC.getMain().size() > 5) {                                 //Tant que sa main est superieur à 5
                    jeu.desactivationBtn();
                    popUp = new VuePopUp(this, joueurC.getMain(), false);
                    popUp.afficher();
            }
            afficherMainJoueur();                                               //Met à jour la main du joueur courant dans l'ihm
            jeu.repaint();
        } else if (msg.getTypeMessage() == TypesMessage.ACTION_PrendreTresors) {
            Tresor tresortemp = gagnerTresorPossible();
            gagnerTresor();
            int i = 0;
            ArrayList<CarteDosOrange> carteTemp = new ArrayList<>();
            for(CarteDosOrange carte : joueurC.getMain()){
                System.out.println("tresor de la carte : " + carte.getTresor());
                System.out.println("tresor possible : " + gagnerTresorPossible());
                
                if ((carte.getTresor() == tresortemp) && i < 4){
                    defausseOrange.add(carte);
                    carteTemp.add(carte);
                    i++;
                }
                
            }
            for (CarteDosOrange carte : carteTemp){
                joueurC.removeCarteMain(carte);           
            }
            
            nbAction++;
            jeu.repaint();
        }
        else if(msg.getTypeMessage().equals(TypesMessage.ACTION_Defausse)){
            popUp = new VuePopUp(this, joueurC.getMain(), true);
            popUp.afficher();
        }
        }                    //Permet de traiter l'information des boutons avec l'ihm
    
    @Override                                                                   
    public void traiterAction(String nomJ, int x, int y, boolean depl) {
        
        if (utilisationCH) {                                                    //Si l'utilisation de la fonction est pour l'utilisation de la carte Helicoptere
            if (afficheCH) {                                                    //Si l'affichage des joueurs a déjà été affiché
                tuilesPossibles.remove(grilleJeu.trouverTuile(x, y));
                jeu.repaint();
                jeu.afficherPossible(tuilesPossibles);                          //Affichage des joueurs (boutons cliquables)
                xTemp = x;
                yTemp = y;
                afficheCH = false;
            } else {                                                            //Ou non
                for (Aventurier av : joueurs) {                                 //Selection des aventurier pour une tuile précise
                    if (av.getPosition().equals(grilleJeu.trouverTuile(xTemp, yTemp))) {//Si la position du joueur est égale à la position de la premiere tuile selectionnée
                        grilleJeu.trouverTuile(xTemp, yTemp).supprJoueur(av);   //Suppression du joueur sur la tuile
                        av.setPosition(grilleJeu.trouverTuile(x ,y));           //Mise à jour de la position du joueur
                        grilleJeu.trouverTuile(x, y).addJoueur(av);             //Ajout du joueur pour la tuile
                        setGrilleJeu(av.getGrilleAv());                         //Mise à jour de la grille
                    }
                }
                int i = 0;
                while (i < joueurC.getMain().size()) {                          //Ajout de la carte dans la defausse et suppression de la carte dans la main
                    if (joueurC.getMain().get(i).getClass().getSimpleName().contains("CarteHelicoptere")) {
                        defausseOrange.add(joueurC.getMain().get(i));
                        joueurC.removeCarteMain(joueurC.getMain().get(i));
                        jeu.afficherMain(joueurC.getMain(), true, joueurC.getNom(), joueurC.getClass().getSimpleName(), joueurC.getPion()); //Mise à jour de la main
                        i = 10;
                    }
                i++;
                }
                CHErreur = !CHErreur;
                tuilesPossibles.clear();                                        //RAZ
                utilisationCH = false;
                jeu.repaint();
            }
        } else if (utilisationCSS) {                                            //Si l'utilisation de la fonction est pour l'utilisation d'une carte sac de sable
            grilleJeu.trouverTuile(x, y).setEtat(EtatTuile.ASSECHEE);           //Changement de l'état de la tuile choisie 
            setGrilleJeu(grilleJeu);                                            //Mise à jour de la grille
            int i = 0;
            while (i < joueurC.getMain().size()) {                              //Ajout de la carte dans la defausse et suppression de la carte dans la main
                if (joueurC.getMain().get(i).getClass().getSimpleName().contains("CarteSacDeSable")) {
                    defausseOrange.add(joueurC.getMain().get(i));
                    joueurC.removeCarteMain(joueurC.getMain().get(i));
                    //Mise à jour de la main
                    jeu.afficherMain(joueurC.getMain(), true, joueurC.getNom(), joueurC.getClass().getSimpleName(), joueurC.getPion());
                    i = 10;
                }
                i++;
            }
            CSSErreur = !CSSErreur;
            tuilesPossibles.clear();                                            //RAZ
            utilisationCSS = false;
            jeu.repaint();
        } else {
            getAventurier(nomJ, joueurs).deplacementAssechage(x, y, depl);      //Deplace le joueur sur la position souhaitée
            setGrilleJeu(getAventurier(nomJ, joueurs).getGrilleAv());           //Met à jour les grilles du jeu
            nbAction++;
            jeu.repaint();
            if (nbAction < 3) {
                if ((joueurC.getClass().getSimpleName().equals("Ingenieur")) && assechementInge < 1 && !depl) { //Pouvoir de l'ingenieur
                    assechementInge++;
                    nbAction--;
                }
                if ((joueurC.getClass().getSimpleName().equals("Navigateur")) && actionNavi < 1) {              //Pouvoir du navigateur
                    actionNavi++;
                    nbAction--;
                }
                jeu.afficherPossible(joueurC.getTuilesPossibles(depl));         //Affiche les tuiles possibles pour le prochain mouvement
                gagnerTresorPossible();                                         //Vérifie si un trésor est possible
                if (tresorRecup) {
                    jeu.tresorPossible();
                }
            } else {                                                            //Si le nombre d'action est superieur à 3 mettre fin au tour
                jeu.finTourObligatoire();
            }
        }
    } //Effectue un déplacement ou un assechement

    @Override                                                                   
    public void envoyerDonnees (int nbJoueurs, String nomJ1, String nomJ2, 
            String nomJ3, String nomJ4, int difficulte) {

        this.nbJoueurs = nbJoueurs;                                             //Associe les valeurs reçu de l'ihm au controlleur
        this.nomJ1 = nomJ1;
        this.nomJ2 = nomJ2;
        if (nbJoueurs >= 3) {
            this.nomJ3 = nomJ3;
            if (nbJoueurs == 4) {
                this.nomJ4 = nomJ4;
            }
        }
        
        echelle = new Echelle(difficulte);                                      //Initialisation de l'échelle

        initJoueurs(nomJ1, nomJ2, nomJ3, nomJ4);                                //Initialise le role des joueurs

        joueurs.add(av1);                                                       //Remplie l'arraylist de joueurs
        joueurs.add(av2);
        if (nbJoueurs >= 3) {
            joueurs.add(av3);
            if (nbJoueurs == 4) {
                joueurs.add(av4);
            }
        }

        paramJeu.fermer();                                                      //Lance le jeu
        initInondationDebut();
        distributionCartesOrangeDebut();
        setGrilleJeu(grilleJeu);
        jeu.afficherTresorTuiles(tresors);
        jeu.afficherNiveau(echelle.getNiveauEau());
        jeu.afficher();

        tourDeJeu();
    }         //Envoie les paramètres de jeu et lance le jeu
        
    public void initJoueurs(String nomJ1, String nomJ2, String nomJ3, String nomJ4) {
        String nom;
        String nomPos = "";                                                     //Nom de la position de départ
        int random;
        int randomSauv1 = -1;                                                   //Sauvegarde 1-2-3 du role choisi précédemment
        int randomSauv2 = -1;
        int randomSauv3 = -1;
        int min = 0;
        int max = 5;
        Aventurier role;
        for (int i = 0; i < nbJoueurs; i++) {
            random = getRandom(min, max);                                       //Choisi un nombre aléatoire parmis le nombre de role
            if (i == 0) {                                                       //Crée le role pour le joueur 1-2-3-4 en fonction du nombre de joueurs
                nom = nomJ1;
            } else if (i == 1) {
                nom = nomJ2;
            } else if (i == 2) {
                nom = nomJ3;
            } else {
                nom = nomJ4;
            }
            while ((random == randomSauv1 || random == randomSauv2 || random == randomSauv3)) { //Si le role choisi a déjà été choisi
                random = getRandom(min, max);
            }
            if (random == 0) {                                                  //Assotiation du tirage avec les roles
                role = new Explorateur(nom);
                nomPos = "La porte de cuivre";
            } else if (random == 1) {
                role = new Ingenieur(nom);
                nomPos = "La porte de bronze";
            } else if (random == 2) {
                role = new Messager(nom);
                nomPos = "La porte d'argent";
            } else if (random == 3) {
                role = new Navigateur(nom);
                nomPos = "La porte d'or";
            } else if (random == 4) {
                role = new Pilote(nom);
                nomPos = "Heliport";
            } else {
                role = new Plongeur(nom);
                nomPos = "La porte de fer";
            }
            if (i == 0) {                                                       //Mise à jour des aventuriers en fonction des roles attribués
                av1 = role;
                av1.setPosition(grilleJeu.trouverTuile(nomPos));                //Mise à jour de la position du joueur
                grilleJeu.trouverTuile(nomPos).addJoueur(av1);                  //Mise à jour de la tuile en fonction de la position du joueur
                randomSauv1 = random;

            } else if (i == 1) {
                av2 = role;
                av2.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av2);
                randomSauv2 = random;
            } else if (i == 2) {
                av3 = role;
                av3.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av3);
                randomSauv3 = random;
            } else {
                av4 = role;
                av4.setPosition(grilleJeu.trouverTuile(nomPos));
                grilleJeu.trouverTuile(nomPos).addJoueur(av4);
            }
            setGrilleJeu(grilleJeu);                                            //Mise à jour de la grille
        }
    } //Permet d'atribuer un role aléatoire aux joueurs

    private Aventurier getAventurier(String nomAv, ArrayList<Aventurier> aventuriers) {
        int i = 0;
        Boolean trouve = false;
        while (i < aventuriers.size() && !trouve) {                             //Parcours tout les aventuriers
            if (nomAv == aventuriers.get(i).getNom()) {
                trouve = true;
            } else {
                i++;
            }
        }

        if (trouve) {
            return aventuriers.get(i);                                          //Renvoie l'aventurier trouvé
        } else {
            return null;
        }
    } //Permet de trouver un aventurier dans la liste de joueur

    @Override
    public ArrayList<String> getJoueurTuile(Tuile tuile) {
        ArrayList<Aventurier> Aventurier = tuile.getJoueurs();
        ArrayList<String> Joueurs = new ArrayList<>();

        for (Aventurier j : Aventurier) {
            if (j.getClass() == Explorateur.class) {
                Joueurs.add("Expl");
            }
            if (j.getClass() == Ingenieur.class) {
                Joueurs.add("Inge");
            }
            if (j.getClass() == Messager.class) {
                Joueurs.add("Mess");
            }
            if (j.getClass() == Navigateur.class) {
                Joueurs.add("Navi");
            }
            if (j.getClass() == Pilote.class) {
                Joueurs.add("Pilo");
            }
            if (j.getClass() == Plongeur.class) {
                Joueurs.add("Plon");
            }
        }
        return Joueurs;
    }        //Retourne une collection d'Aventuriers présents sur une tuile

    public Aventurier getJoueurCourant(int jc) {
        return joueurs.get(jc);
    }                   //Renvoi le joueur courant

    private static ArrayList<CarteDosOrange> melangerCartesOranges(ArrayList<CarteDosOrange> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList;
    } //Melange une liste de carte orange

    private static ArrayList<CarteInondation> melangerCartesInondation(ArrayList<CarteInondation> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList;
    } //Melange une liste de carte inondation

    private void créerTresors() {
        tresors = new ArrayList<>();                                            //Initialisation des arraylist concernant les trésors
        tresorsGagnés = new ArrayList<>();
        tresorsCompare = new ArrayList<>();
        
        Tresor tresor1 = new Tresor("La Pierre sacrée");                        //Création des 4 tresors du jeu
        Tresor tresor2 = new Tresor("La Statue du zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal ardent");
        Tresor tresor4 = new Tresor("Le Calice de l'onde");
        
        tresors.add(tresor1);                                                   //Ajout des tresors dans la liste des tresors
        tresors.add(tresor2);
        tresors.add(tresor3);
        tresors.add(tresor4);
        tresorsCompare.add(tresor1);
        tresorsCompare.add(tresor2);
        tresorsCompare.add(tresor3);
        tresorsCompare.add(tresor4);
        
        for (int i = 0; i <= 5; i++) {                                          //Parcours la grille afin d'associer le trésor aux bonnes tuiles
            for (int j = 0; j <= 5; j++) {
                if (grilleJeu.getGrille()[i][j].getNom() == "Le temple du soleil" || grilleJeu.getGrille()[i][j].getNom() == "Le temple de la lune") {
                    grilleJeu.getGrille()[i][j].setTresor(tresors.get(0));
                } else if (grilleJeu.getGrille()[i][j].getNom() == "Le jardin des hurlements" || grilleJeu.getGrille()[i][j].getNom() == "Le jardin des murmures") {
                    grilleJeu.getGrille()[i][j].setTresor(tresors.get(1));
                } else if (grilleJeu.getGrille()[i][j].getNom() == "La caverne du brasier" || grilleJeu.getGrille()[i][j].getNom() == "La caverne des ombres") {
                    grilleJeu.getGrille()[i][j].setTresor(tresors.get(2));
                } else if (grilleJeu.getGrille()[i][j].getNom() == "Le palais de corail" || grilleJeu.getGrille()[i][j].getNom() == "Le palais des marrées") {
                    grilleJeu.getGrille()[i][j].setTresor(tresors.get(3));
                }
            }
        }
        setGrilleJeu(grilleJeu);                                                //Mise à jour de la grille
    }                                 //Création des 4 tresors

    private void remplirPiocheOrange() {
        piocheOrange = new ArrayList<>();
        for (int i = 0; i < 5; i++) {                                           //Ajout des 20 cartes tresors correspondant aux 4 tresors (5 carte pour chaque tresor)
            piocheOrange.add(new CarteTresor(tresors.get(0)));
            piocheOrange.add(new CarteTresor(tresors.get(1)));
            piocheOrange.add(new CarteTresor(tresors.get(2)));
            piocheOrange.add(new CarteTresor(tresors.get(3)));
        }
        for (int i = 0; i < 3; i++) {                            // ajout des 3 cartes Helicoptere
            piocheOrange.add(new CarteHelicoptere());
        }
        for (int i = 0; i < 2; i++) {                            // ajout des 2 cartes sac de sable et montées des eaux
            piocheOrange.add(new CarteMonteeDesEaux());
            piocheOrange.add(new CarteSacDeSable());
        }
        piocheOrange = melangerCartesOranges(piocheOrange);                     //On melange la pioche des cartes inondation car les cartes etaitent triées dans l'ordre des tuiles    
    }                          //Création de la pioche des cartes oranges

    private void remplirPiocheInondation() {
        piocheInondation = new ArrayList<>();
        Tuile[][] grille = grilleJeu.getGrille();
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (grille[i][j].getNom() != "null") {
                    piocheInondation.add(new CarteInondation(grille[i][j]));    //Pour chaque tuile de la grille, il éxiste une carte inondation correspondante
                }
            }
        }
        piocheInondation = melangerCartesInondation(piocheInondation);          //On melange la pioche des cartes inondation car les cartes etaitent triées dans l'ordre des tuiles    
    }                      //Création de la pioche des cartes inondation

    public int getRandom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }                       //Renvoi un nombre aléatoire entre min et max

    public void distributionCartesOrangeDebut() {        
        for (int i = 0; i < nbJoueurs; i++) {                                   //Boucle le me nombre de fois qu'il y a de joueurs
            for (int j = 1; j < 3; j++) {                                       //Donne 2 cartes à chaque joueur
                int numRandom = getRandom(0, piocheOrange.size() - 1);
                if (piocheOrange.get(numRandom).getClass().equals(CarteMonteeDesEaux.class)) { //Si la carte potentiellemnent donnée est une carte montée de eaux alors on re-boucle 
                    j--;
                } else {
                    getJoueurCourant(i).addCarteMain(piocheOrange.get(numRandom)); //Ajout de la carte dans la main du joueur
                    piocheOrange.remove(numRandom);                             //Suppression de la carte de la pioche
                }
            }
        }
    }                 //Distribution des cartes a tous les joueurs au début du jeu

    public Tresor gagnerTresorPossible() {
        int cartesTresorPierre = 0;
        int cartesTresorStatue = 0;
        int cartesTresorCristal = 0;
        int cartesTresorCalice = 0;

        if (!tresorsGagnés.contains(tresorsCompare.get(0))) {                   //Si le trésors 0 est déjà gagné
            if (joueurC.getPosition().getNom() == "Le temple du soleil" || joueurC.getPosition().getNom() == "Le temple de la lune") { //Si le joueur se trouve sur une case pour recuperer le tresor de la pierre sacrée     
                for (CarteDosOrange carte : joueurC.getMain()) {
                    if (carte.getTresor() != null
                            && carte.getTresor().getNomTresor() == "La Pierre sacrée") { //On compte combien de carte tresor de la pierre sacrée il a dans la main
                        cartesTresorPierre++;
                    }
                }
                if (cartesTresorPierre > 3) {                                   //Si il en en 4 ou plus , il peut gagner le tresor
                    setTresorRecup(true);

                    return tresors.get(0);
                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(1))) {                   //Si le trésors 1 est déjà gagné
            if (joueurC.getPosition().getNom() == "Le jardin des hurlements" || joueurC.getPosition().getNom() == "Le jardin des murmures") { //Si le joueur se trouve sur une case pour recuperer le tresor de la statue du zephyr
                for (CarteDosOrange carte : joueurC.getMain()) {
                    if (carte.getTresor() != null
                            && carte.getTresor().getNomTresor() == "La Statue du zéphyr") { //On compte combien de carte tresor de la statue du zephyr il a dans la main
                        cartesTresorStatue++;
                    }
                }
                if (cartesTresorStatue > 3) {                                    //Si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(1);

                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(2))) {                   //Si le trésors 2 est déjà gagné
            if (joueurC.getPosition().getNom() == "La caverne du brasier" || joueurC.getPosition().getNom() == "La caverne des ombres") { //Si le joueur se trouve sur une case pour recuperer le tresor du cristal ardent
                for (CarteDosOrange carte : joueurC.getMain()) {
                    if (carte.getTresor() != null
                            && carte.getTresor().getNomTresor() == "Le Cristal ardent") { //On compte combien de carte tresor ddu cristal ardent il a dans la main
                        cartesTresorCristal++;
                    }
                }
                if (cartesTresorCristal > 3) {                                  //Si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(2);

                }
            }
        }
        if (!tresorsGagnés.contains(tresorsCompare.get(3))) {                   //Si le trésors 3 est déjà gagné
            if (joueurC.getPosition().getNom() == "Le palais de corail" || joueurC.getPosition().getNom() == "Le palais des marrées") { //Si le joueur se trouve sur une case pour recuperer le tresor du calice de l'onde
                for (CarteDosOrange carte : joueurC.getMain()) {
                    if (carte.getTresor() != null
                            && carte.getTresor().getNomTresor() == "Le Calice de l'onde") { //On compte combien de carte tresor du calcie de l'onde il a dans la main
                        cartesTresorCalice++;
                    }
                }
                if (cartesTresorCalice > 3) {                                   //Si il en en 4 ou plus , il peut gagner le  tresor
                    setTresorRecup(true);
                    return tresors.get(3);
                }
            }
        }
        setTresorRecup(false);
        return null;
    }                        //Renvoi un tresor qui peut être gagné actuellement par le joueur courant

    public void gagnerTresor() {
        tresorsGagnés.add(gagnerTresorPossible());                              // on l'ajoute a la liste des trésors gagnés;
        jeu.retirerTresorsGrille(tresorsGagnés);                                // on retire lees trésors autour de la grille (dans la vue jeu)

        tresors.remove(gagnerTresorPossible());
    }                                   //Ajout du tresor possible dans la liste des tresors récupérés

    public void piocherDeuxCartesOrange() {
        for (int i = 1; i < 3; i++) {
            if (piocheOrange.size() == 0) {                                     //A chaque fois on verifie si la pioche est vide, et si elle l'est
                for (CarteDosOrange carte : melangerCartesOranges(defausseOrange)) { //On parcours toute la defausse melangée
                    piocheOrange.add(carte);                                    //On rempli la pioche.
                }
                defausseOrange.clear();                                         //On vide la defausse  
            }
            int numRandom = getRandom(0, piocheOrange.size() - 1);              //Au hasard
            if (piocheOrange.get(numRandom).getClass().equals(CarteMonteeDesEaux.class)) { //Si la carte est une carte montées des eaux
                defausseOrange.add(piocheOrange.get(numRandom));                           //On ajoute la carte dans la defausse orange
                piocheOrange.remove(piocheOrange.get(numRandom));                          //On la supprime de la pioche
                echelle.incrementerCran();                                                 //On incrémente l'echelle
                jeu.afficherNiveau(echelle.getNiveauEau());
                System.out.println("Vous avez pioché une carte montée des eaux");
            } else {                                                                       //Sinon
                joueurC.addCarteMain(piocheOrange.get(numRandom));                         //On ajoute la carte dans la main du joueur courant
                if (piocheOrange.get(numRandom).getClass().equals(CarteTresor.class)) {
                    System.out.println("Vous avez pioché une carte trésor : " + piocheOrange.get(numRandom).getTresor().getNomTresor());
                } else if ((piocheOrange.get(numRandom).getClass().equals(CarteHelicoptere.class)) || (piocheOrange.get(numRandom).getClass().equals(CarteSacDeSable.class))) {
                    System.out.println("Vous avez pioché une " + piocheOrange.get(numRandom).getClass().getSimpleName());
                }
                piocheOrange.remove(piocheOrange.get(numRandom));                          //On la supprime de la pioche
            }
        }
    }                       //Pioche 2 cartes oranges + fait en fonction du tirage

    public void piocherCartesInondation() {
        System.out.println(echelle.getNiveauEau());
        for (int i = 0; i < echelle.getNiveauEau(); i++) {                      //On pioche le nombre de cartes = niveau d'eau
            if (piocheInondation.size() == 0) {                                 //Si pioche vide
                for (CarteInondation carte : melangerCartesInondation(defausseInondation)) { //On parcours toute la defausse melangée
                    piocheInondation.add(carte);                                //On rempli la pioche
                }
                defausseInondation.clear();                                     //On vide la defausse
            }
            int numRandom = getRandom(0, piocheInondation.size() - 1);          //Au hasard

            if (piocheInondation.get(numRandom).getTuile().getEtat() == Utils.EtatTuile.ASSECHEE) { //On regarde l'etat de la tuile correspondant à la carte choisie au hasard 
                piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.INONDEE);        //Si la tuile est asséchée elle devient inondée
                defausseInondation.add(piocheInondation.get(numRandom));                            //Puis on ajoute la carte dans la defausse
                piocheInondation.remove(piocheInondation.get(numRandom));                           //Et on retire la carte de la pioche
            } else if (piocheInondation.get(numRandom).getTuile().getEtat() == Utils.EtatTuile.INONDEE) {
                piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.COULEE);         //Si la tuile est inondée elle devient submergée
                piocheInondation.remove(piocheInondation.get(numRandom));                           //Et on retire la carte inondation du jeu
            }
        }
    }                       //Pioche nb de cartes inondation + fait en fonction du tirage

    public void initInondationDebut() {
        for (int i = 0; i < 6; i++) {                                           //On pioche 6 cartes
            int numRandom = getRandom(0, piocheInondation.size() - 1);          //Au hasard

            piocheInondation.get(numRandom).getTuile().setEtat(Utils.EtatTuile.INONDEE); //La tuile  devient inondée
            defausseInondation.add(piocheInondation.get(numRandom));            //Puis on ajoute la carte dans la defausse
            piocheInondation.remove(piocheInondation.get(numRandom));           //Et on retire la carte de la pioche

        }
    }                           //Initialise l'inondation de l'île

    public void gestionFinJeu() {
        if (grilleJeu.trouverTuile("Heliport").getEtat() == Utils.EtatTuile.COULEE) { //Fermer le jeu si l'héliport est submergé
            popUp.fermer();
            jeu.fermer();                                                       //Si oui , fin du jeu
            afficherInformation("Jeu terminé, Heliport submergé");
        }
        if (echelle.getNiveauEau() > 5) {                                       //Fermer le jeu si le niveau d'eau est critique
            popUp.fermer();
            jeu.fermer();                                                       //Si oui , fin du jeu
            afficherInformation("Jeu terminé, Ile totalement sous les eaux");
        }

        Boolean bool = false;
        for (Tresor tresor : tresors) {                                         //Pour chaque tresor
            for (Tuile tuile : grilleJeu.getTuilesTresor(tresors.get(tresors.indexOf(tresor)))) { //Et pour chaque tuile permettant de récupérer ce tresor
                if (bool && tuile.getEtat() == Utils.EtatTuile.COULEE) {        //Verifier si la deuxieme tuile est submergée
                    popUp.fermer();
                    jeu.fermer();                                               //Si oui , fin du jeu
                    afficherInformation("Jeu terminé, trésor :" + tresor.getNomTresor() + " n'est plus récupérable");
                }
                if (tuile.getEtat() == Utils.EtatTuile.COULEE) {                 //Vérifier si la 1° est submergée
                    bool = true;
                }
            }
            bool = false;
        }

        for (Aventurier av : joueurs) {                                         //Pour chaque aventurier
            if (av.getPosition().getEtat() == Utils.EtatTuile.COULEE) {         //Si la case sur laquelle il se trouve est submergée
                if (av.getTuilesPossibles(true).size() == 0) {                  //Et si aucun deplacement n'est possible             
                    popUp.fermer();                                             // fin du jeu
                    jeu.fermer();
                    afficherInformation("Jeu terminé, Un joueur est mort dans les abysses.");
                }
            }
        }
    }                                 //Gestion du jeu (Défaite ou Victoire)

    @Override
    public void enleverCarteSurplus(CarteDosOrange carte) {
        defausseOrange.add(carte);                                              // ajoute la carte a la defausse
        joueurC.removeCarteMain(carte);                                         // retire la carte de la main du joueur
    }        //Ajoute la carte à la defausse orange et retire la carte de la main du joueur
    
    @Override
    public void utiliserCarteSacDeSable(){
        if (!CSSErreur) {
            tuilesPossibles = grilleJeu.getTuilesInondée();
            jeu.afficherPossible(tuilesPossibles);
            utilisationCSS = true;
            CSSErreur = !CSSErreur;
        } else {
            jeu.repaint();
            CSSErreur = !CSSErreur;
        }
    }                         //Permet l'utilisation de la carte sac de sable

    @Override
    public void utiliserCarteHelicoptere() {
        int fini = 0;
        ArrayList<Tuile> tuilesJoueur = new ArrayList<>();
        if (!CHErreur) {
            for (Tuile[] tuiles : grilleJeu.getGrille()) {                          //Parcours toute la grille
                for (Tuile tuileBis : tuiles) {
                    if (tuileBis.getJoueurs().size() != 0) {
                        tuilesJoueur.add(tuileBis);
                    }
                    if (!tuileBis.getEtat().equals(EtatTuile.COULEE) && tuileBis.getNom() != "null") {
                        tuilesPossibles.add(tuileBis);
                    }
                }
            }
            if (tresorsGagnés.size() == 4) {
                for (Aventurier av : joueurs) {
                    if (av.getPosition() == grilleJeu.trouverTuile("Heliport")) {
                        fini++;
                    }
                }
                if (fini == joueurs.size()) {
                    jeu.fermer();
                    popUp.fermer();                                                 // fin du jeu
                    afficherInformation("Félicitations vous avez réussi à récupérer tout les trésors, vous êtes vraiment trop forts !");
                }
            } else {
                jeu.afficherPossible(tuilesJoueur);
                utilisationCH = true;
                afficheCH = true;
            }
            CHErreur = !CHErreur;
        } else {
            jeu.repaint();
            CHErreur = !CHErreur;
        }
    }                      //Permet l'utilisation de la carte hélicoptère

    public void tourDeJeu() {
        Pilote avP = new Pilote("");                                            //RAZ
        tuilesPossibles.clear();
        nbAction = 0;
        joueurC = getJoueurCourant(numJC);
        System.out.println(joueurC.getNom());

        jeu.changeJoueurCourant(joueurC.getNom(), joueurC.getClass().getSimpleName(), joueurC.getPion());           //Mise à jour de la main en fonction du joueur courant
        if (joueurC.getMain().size() > 5) {
            jeu.desactivationBtn();
            popUp = new VuePopUp(this, joueurC.getMain(), false);
            popUp.afficher();
        }
        else {
            jeu.debutTour();
        }

        if (joueurC.getClass().getSimpleName().equals("Pilote")) {              //Remise à zéro des pouvoirs
            avP = (Pilote) joueurC;
            avP.setPouvoirUtilise(false);
            joueurC = avP;
        }
        actionNavi = 0;
        assechementInge = 0;
        
        gagnerTresorPossible();                                                 //Check si un trésors est récupérable
        if (tresorRecup) {
            jeu.tresorPossible();
        }

        jeu.repaint();
        afficherMainJoueur();

        gestionFinJeu();
    }                                     //Gère le début de chaque tour

    public void afficherMainJoueur() {
        Boolean bool;
        jeu.resetMainIHM();
        for (Aventurier av : joueurs) {                                         //Parcours de tous les joueurs
            if (av == joueurC) {                                                //Si joueur Courant?
                bool = true;
            } else {
                bool = false;
            }
            jeu.afficherMain(av.getMain(), bool, av.getNom(), av.getClass().getSimpleName(), av.getPion());    //Afficher la main de av
        }
        jeu.afficherMain(joueurC.getMain(), true, joueurC.getNom(), joueurC.getClass().getSimpleName(), joueurC.getPion());
    }                            //Affiche la main du joueur
    
    private static void setTresorRecup(boolean bool) {
        tresorRecup = bool;
    }        
    
    @Override
    public void defausserCarte(CarteDosOrange carte) {
        joueurC.removeCarteMain(carte);
        defausseOrange.add(carte);
    }

}
