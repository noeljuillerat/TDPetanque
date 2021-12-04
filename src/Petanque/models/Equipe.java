package petanque.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 33611
 */
public class Equipe {
    private List<Joueur> joueurs = new ArrayList<>();
    private List<Partie> partieLancees = new ArrayList<>();
    private int score = 0;
    private int partiesGagnees = 0;
    private int goalAverage = 0;
    private int id;
    
    public Equipe(List<Joueur> joueurs, int id) {
        this.joueurs = joueurs;
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPartiesGagnees() {
        return partiesGagnees;
    }

    public void setPartiesGagnees(int partiesGagnees) {
        this.partiesGagnees = partiesGagnees;
    }

    public int getGoalAverage() {
        return goalAverage;
    }

    public void setGoalAverage(int goalAverage) {
        this.goalAverage = goalAverage;
    }
    
    public void addVictory() {
        this.partiesGagnees += 1;
    }

    public void addPoints(int points) {
        this.score += points;
    }
    public void removePoints(int points) {
        this.score -= points;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public void addJoueur(Joueur joueur) {
        this.joueurs.add(joueur);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Partie> getPartieLancees() {
        return partieLancees;
    }

    public void setPartieLancees(List<Partie> partieLancees) {
        this.partieLancees = partieLancees;
    }

    @Override
    public String toString() {
        return "Equipe "+ (id+1) + " [joueurs=" + joueurs + "]";
    }
    
}
