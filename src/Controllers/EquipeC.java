package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.Partie;
import Models.Joueur;
import Models.Equipe;

public class EquipeC {
    private List<Equipe> equipes;
    
    public EquipeC(List<Joueur> joueurs) {
        this.equipes = GenerationEquipes(joueurs);
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }
    
    public void equipesView() {
        System.out.println("\n_______________" + "\nEquipes");
        
        for(Equipe equipe : this.equipes) { 
            System.out.printf("Equipe " + (equipe.getId() + 1) + "\n");
            for(Joueur j: equipe.getJoueurs()) {
                System.out.printf(j.getPrenom() + " " + j.getNom() + " (" + j.getAge() + " ans)\n");
            }
            System.out.println("\n");
        }
    }

     private List<Equipe> addJoueurs(List<Joueur> joueurs, int NbEquipes){
        List<Equipe> equipes = new ArrayList<>();
        // Equipes avec 2 joueurs 
        for (int i = 0; i < NbEquipes; i++) {
            List<Joueur> selectedPlayers = selectPlayers(joueurs);
            selectedPlayers.forEach(j -> joueurs.remove(j)); 
            equipes.add(new Equipe(selectedPlayers, i));
        }
        // Ajoute les joueurs restant dans des équipes
        if (joueurs.size()>0) {
            equipes = addRemainingPlayers(joueurs, equipes);
        }
        return equipes;
    }

    private List<Joueur> selectPlayers(List<Joueur> joueurs) {
        List<Joueur> selectedPlayers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Joueur j = joueurs.get(numberGenerator(0, joueurs.size()));
            selectedPlayers.add(j);
            joueurs.remove(j); 
        }
        return selectedPlayers;
    }
    
    private List<Equipe> GenerationEquipes(List<Joueur> joueurs){
        // Comportement de la génération en fonction du nombre de participants
        List<Equipe> equipes = new ArrayList<>();
        // PLus de 24 participants
        if(joueurs.size()>24){
            //soit 12 équipes
            equipes = addJoueurs(joueurs,12);
        // plus de 20    
        }else if(joueurs.size()>20){
            // soit 10 équipes
             equipes = addJoueurs(joueurs,10);
             //plus de 16
        }else if(joueurs.size()>16){
            // soit 8 équipes
             equipes = addJoueurs(joueurs,8);
             //et entre 12 et 15 joueurs
        }else{
            // soit 6 équipes
             equipes = addJoueurs(joueurs,6);
        }
        return equipes;    
    }

    private List<Equipe> addRemainingPlayers(List<Joueur> remainingPlayers, List<Equipe> equipes){
        // S'il reste des joueurs sans équipes, les mettre dans des équipes duos
        while (remainingPlayers.size() > 0) {
            int equipeRandom = numberGenerator(0, equipes.size());
            int teamSize = equipes.get(equipeRandom).getJoueurs().size();
            if (teamSize > 2) {
                continue;
            }
            equipes.get(equipeRandom).addJoueur(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
        return equipes;
    }
    
    public static int numberGenerator(int min, int max) {
           Random rand = new Random();
           return rand.nextInt(max-min) + min;
       }

    public void updateTeamsMatchs(List<Partie> parties) {
        for(Partie partie : parties) {
            // Ajoute les matchs à la liste des parties joués pour chaque équipe
            int teamOneId = partie.getRouge().getId();
            int teamTwoId = partie.getBleue().getId();
            this.equipes.get(teamOneId).getPartieLancees().add(partie);
            this.equipes.get(teamTwoId).getPartieLancees().add(partie);
            
            // Ajoute les points (positifs et négatifs) aux équipes
            int teamOnePoints = partie.getPointsRouge();
            int teamTwoPoints = partie.getPointsBleue();
            this.equipes.get(teamOneId).addPoints(teamOnePoints);
            this.equipes.get(teamOneId).removePoints(teamTwoPoints);
            this.equipes.get(teamTwoId).addPoints(teamTwoPoints);
            this.equipes.get(teamTwoId).removePoints(teamOnePoints);
            
            // Compte le nombre de parties gagnées
            int winnerId = partie.getGagnante().getId();
            this.equipes.get(winnerId).addVictory();
        }
        // Pour chaque équipe calcule goalAverage
        for (Equipe equipe : equipes) {
            equipe.setGoalAverage(equipe.getScore());
        }

    }
}
