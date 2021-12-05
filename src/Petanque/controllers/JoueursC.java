package petanque.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import petanque.models.Joueur;

public class JoueursC {
    private List<Joueur> joueurs;
    private int nbJoueurs;  


    public JoueursC() {
        this.joueurs = generatePlayers();
        this.nbJoueurs = this.joueurs.size();
    }
    
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

  public void printPlayers() {
        System.out.println("Il y a " + joueurs.size()+ " joueurs inscrits " );
        System.out.println("Joueurs inscrits : ");
       
        // Boucle sur les joueurs
        for(Joueur joueur : this.joueurs) {
            System.out.println("/nNOM : " + joueur.getNom() + " " + joueur.getPrenom() + ", de " + joueur.getPrenom() + " ans.");
        }
    }  

    private List<Joueur> generatePlayers() {
        // Génére un nombre de joueurs aléatoire
        int GenerateurNbJoueur = numberGenerator(12,36);
        List<Joueur> joueurs = new ArrayList<>();
        
        // Génère un nom aleatoire
        nameGenerator Name = new nameGenerator(6);
        for (int i = 0 ; i < GenerateurNbJoueur ; i++) {
            Joueur newJoueur = new Joueur(Name.getName(), Name.getName(), numberGenerator(7,77), i);
            joueurs.add(newJoueur);
        }
        return joueurs;
    }
    
    public static int numberGenerator(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max-min) + min;
    }
}
