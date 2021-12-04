package petanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.controllers.tools.Utils;
import petanque.models.Partie;
import petanque.models.Joueur;
import petanque.models.Equipe;

public class EquipeC {
    private List<Equipe> equipes;
    
    public EquipeC(List<Joueur> joueurs) {
        this.equipes = GenerationEquipes(joueurs);
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }
    
    public void printTeams() {
        Utils.printLine(40);
        System.out.println("CONSTITUTION DES EQUIPES");
        // En-têtes du tableau
        Utils.printLine(94);
        System.out.printf("%-6s", "Team");
        System.out.printf("%-30s", "Joueur 1");
        System.out.printf("%-30s", "Joueur 2");
        System.out.printf("%-30s", "Joueur 3");
        Utils.printLine(94);
        for(Equipe equipe : this.equipes) {
            System.out.printf("%-6s", (equipe.getId() + 1));
            for(Joueur p: equipe.getJoueurs()) {
                System.out.printf("%-30s", p.getPrenom() + " " + p.getNom() + " (" + p.getId() + ")");
            }
            System.out.println("");
        }
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

    private List<Equipe> addJoueurs(List<Joueur> joueurs, int NbEquipes){
        List<Equipe> equipes = new ArrayList<>();
        // Constitue les équipes avec 2 joueurs aléatoires
        for (int i = 0; i < NbEquipes; i++) {
            List<Joueur> selectedPlayers = selectPlayers(joueurs);
            selectedPlayers.forEach(p -> joueurs.remove(p)); // Retire les deux joueurs sélectionnés de la liste
            equipes.add(new Equipe(selectedPlayers, i));
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (joueurs.size()>0) {
            equipes = addRemainingPlayers(joueurs, equipes);
        }
        return equipes;
    }

    private List<Joueur> selectPlayers(List<Joueur> joueurs) {
        List<Joueur> selectedPlayers = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Joueur p = joueurs.get(RandomGenerators.generateNumberBetween(0, joueurs.size()));
            selectedPlayers.add(p);
            joueurs.remove(p); // retire le joueur sélectionné de la liste locale
        }
        return selectedPlayers;
    }

    private List<Equipe> addRemainingPlayers(List<Joueur> remainingPlayers, List<Equipe> equipes){
        // Tant qu'il reste des joueurs, boucler pour les caser dans des équipes aléatoires de 2 joueurs
        while (remainingPlayers.size() > 0) {
            int randomTeam = RandomGenerators.generateNumberBetween(0, equipes.size());
            int teamSize = equipes.get(randomTeam).getJoueurs().size();
            if (teamSize > 2) {
                continue;
            }
            equipes.get(randomTeam).addJoueur(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
        return equipes;
    }

    public void updateTeamsMatchs(List<Partie> parties) {
        for(Partie partie : parties) {
            // Ajoute les matchs à la liste des parties joués pour chaque équipe
            int teamOneId = partie.getRouge().getId();
            int teamTwoId = partie.getBleue().getId();
            this.equipes.get(teamOneId).getPlayedMatchs().add(partie);
            this.equipes.get(teamTwoId).getPlayedMatchs().add(partie);
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
