package concourspetanque.views;

import petanque.controllers.PartieC;
import petanque.controllers.JoueursC;
import petanque.controllers.EquipeC;
import concourspetanque.controllers.tools.Utils;
import petanque.models.Partie;
import petanque.models.Joueur;
import petanque.models.Equipe;

import java.util.List;

public class League {
    List<Equipe> teams;
    /**
     * Main method for executing different steps of the program
     */
    public void start() {
        JoueursC playersController = new JoueursC();
        playersController.printPlayers();

        TeamsController teamsController = new TeamsController(playersController.getPlayers());
        teamsController.printTeams();

        // Jouer les matchs
        PartieC matchsController = new PartieC(teamsController.getTeams());
        matchsController.printMatchs(teamsController.getTeams().size());

        // Ajouter les matchs joués à chaque team
        teamsController.updateTeamsMatchs(matchsController.getMatchs());

        // Afficher les résultats
        printFinalResults(teamsController.getTeams());
    }

    private void printFinalResults(List<Equipe> teams) {
        Utils.printLine(40);
        System.out.println("\nFINAL RESULTS");
        // Affiche les en-têtes du tableau
        Utils.printLine(130);
        //headers
        System.out.printf("%-6s", "TEAM");
        System.out.printf("%-39s", "NOMS");
        System.out.printf("%-11s", "PARTIE 1");
        System.out.printf("%-11s", "PARTIE 2");
        System.out.printf("%-11s", "PARTIE 3");
        System.out.printf("%-11s", "PARTIE 4");
        System.out.printf("%6s", "+");
        System.out.printf("%6s", "-");
        System.out.printf("%8s", "TOTAL");
        System.out.printf("%11s", "VICTOIRES");
        System.out.printf("%10s", "PLACE");
        Utils.printLine(130);
        // Boucle sur les différentes équipes et affiche les informations ligne par ligne

        //scores
        for (Equipe team: teams) {
            // Affiche Equipe ID
            System.out.printf("%-6s", (team.getId() + 1));
            // Affiche Joueurs
            for(int i = 0 ; i < 3 ; i++) {
                try {
                    // Affiche nom du joueur s'il existe
                    Joueur player = team.getPlayers().get(i);
                    System.out.printf("%-13s", player.getLastName());
                } catch (Exception e) {
                    // Réserve un espace vide sinon
                    System.out.printf("%-13s", "");
                }
            }
            // Affiche Parties
            for(Partie match : team.getPlayedMatchs()) {
                System.out.printf("%-11s", match.getOpponent1score() + " / " + match.getOpponent2score());
            }
            // Affiche Points et Total
            System.out.printf("%6s", team.getPositivePoints());
            System.out.printf("%6s", team.getNegativePoints());
            System.out.printf("%8s", team.getPositivePoints() + team.getNegativePoints());
            // Affiche Victoire et position
            System.out.printf("%11s", team.getVictories());
            System.out.printf("%10s", "PLACE");
            System.out.println("");
        }
    }
}
