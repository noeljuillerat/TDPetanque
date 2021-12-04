package petanque.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import concourspetanque.controllers.tools.Utils;
import petanque.models.Partie;
import petanque.models.Equipe;
import concourspetanque.models.interfaces.LeagueRoundsInterface;
import concourspetanque.models.leagueRounds.EightTeams;
import concourspetanque.models.leagueRounds.SixTeams;
import concourspetanque.models.leagueRounds.TenTeams;
import concourspetanque.models.leagueRounds.TwelveTeams;

public class PartieC {
    List<Partie> parties;

    public PartieC(List<Equipe> equipes) {
        this.parties = playMatchs(equipes);
    }

    public List<Partie> getMatchs() {
        return parties;
    }

    public void printMatchs(int teamsSize) {
        Utils.printLine(40);
        System.out.println("DEROULEMENT DES MATCHS");
        int matchsPerRound = teamsSize / 2;
        int round = 1;
        for(int i = 0 ; i < this.parties.size() ; i++) {
            if (i % matchsPerRound == 0) {
                System.out.println("\nPARTIE " + round + "\n");
                round++;
            }
            System.out.printf("%-8s", "Equipe" + (parties.get(i).getRouge().getId() + 1));
            System.out.printf("%-3s", parties.get(i).getPointsRouge());
            System.out.print(" - ");
            System.out.printf("%3s", parties.get(i).getPointsBleue());
            System.out.printf("%8s", "Equipe" + (parties.get(i).getBleue().getId() + 1));
            System.out.println("");
        }
    }  
    
    public List<Partie> playMatchs(List<Equipe> equipes) {
        LeagueRoundsInterface roundsSetup = getTeamsConfrontationSetup(equipes.size());
        // Jouer les 4 rounds
        List<Partie> parties = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            Map<String, int[]> roundOpponents = getRoundOpponents(i, roundsSetup);
            List<Partie> roundMatchs = playRound(roundOpponents, equipes);
            parties.addAll(roundMatchs);
        }
        return parties;
    }

    private List<Partie> playRound(Map<String,int[]> roundOpponents, List<Equipe> equipes) {
        List<Partie> parties = new ArrayList<>();
        // Jouer les différents parties du round
        for (int i = 0 ; i < roundOpponents.size() ; i++) {
            String matchKey = String.valueOf(i + 1);
            Partie partie = playMatch(roundOpponents, matchKey, equipes);
            parties.add(partie);
        }
        return parties;
    }

    private Partie playMatch(Map<String,int[]> roundOpponents, String matchKey, List<Equipe> equipes) {
        // Récupérer les index des opposants dans la map
        int[] opponents = roundOpponents.get(matchKey);
        // On retire 1 à l'index (= opponents[0] - 1) car List<equipes> commence à 0,
        // et les setup font commencer l'index à 1...
        int teamOneIndex = opponents[0] - 1;
        int teamTwoIndex = opponents[1] - 1;
        // Jouer le match
        Partie match = new Partie(equipes.get(teamOneIndex), equipes.get(teamTwoIndex));
        return match;
    }

    private LeagueRoundsInterface getTeamsConfrontationSetup(int size) {
        // Récupérer le setup des match (selon le nombre d'équipes)
        LeagueRoundsInterface roundsSetup;
        switch (size) {
            case 6:
                roundsSetup = new SixTeams();
                break;
            case 8:
                roundsSetup = new EightTeams();
                break;
            case 10:
                roundsSetup = new TenTeams();
                break;
            default:
                roundsSetup = new TwelveTeams();
        }
        return roundsSetup;
    }

    private Map<String,int[]> getRoundOpponents(int round, LeagueRoundsInterface roundsSetup) {
        // Récupérer la Map correspondant aux opposants du round
        Map<String, int[]> opponentsMap;
        switch (round) {
            case 0:
                opponentsMap = roundsSetup.roundOne();
                break;
            case 1:
                opponentsMap = roundsSetup.roundTwo();
                break;
            case 2:
                opponentsMap = roundsSetup.roundThree();
                break;
            default:
                opponentsMap = roundsSetup.roundFour();
        }
        return opponentsMap;
    }
}
