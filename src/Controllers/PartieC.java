package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Models.Partie;
import Models.Equipe;
import ParametreEquipe.Concours8Equipes;
import ParametreEquipe.Concours6Equipes;
import ParametreEquipe.Concours10Equipes;
import ParametreEquipe.Concours12Equipes;
import Interfaces.ConcoursParties;

public class PartieC {
    List<Partie> parties;

    public PartieC(List<Equipe> equipes) {
        this.parties = jeux(equipes);
    }

    public List<Partie> getMatchs() {
        return parties;
    }

    // Log la constitutions des équipes
    public void partiesView(int equipeLength) {
       // Calcule le nb de rounds pendant cette partie
        int roundParties = equipeLength / 2;
        int round = 1;
        
        System.out.println("\n____________" + "\nGrille des parties");
        for(int i = 0 ; i < this.parties.size() ; i++) {

            if (i % roundParties == 0) {
                System.out.println("\nRound " + round);
                round++;
            }
            System.out.println("Equipe " + (parties.get(i).getRouge().getId() + 1) + " vs "+ "équipe " + (parties.get(i).getBleue().getId() + 1) );
            System.out.println("Score : " + parties.get(i).getPointsRouge() + " - " +  parties.get(i).getPointsBleue() + "\n" );              
        }
    }  
    
    public List<Partie> jeux(List<Equipe> equipes) {
        ConcoursParties roundsSetup = getTeamsConfrontationSetup(equipes.size());
        // Jouer les 4 rounds
        List<Partie> parties = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            Map<String, int[]> roundOpponents = getRoundOpponents(i, roundsSetup);
            List<Partie> roundMatchs = playRound(roundOpponents, equipes);
            parties.addAll(roundMatchs);
        }
        return parties;
    }

    private List<Partie> playRound(Map<String,int[]> ad, List<Equipe> equipes) {
        List<Partie> parties = new ArrayList<>();
        // Jouer les différents parties du round
        for (int i = 0 ; i < ad.size() ; i++) {
            String id = String.valueOf(i + 1);
            Partie partie = jeux(ad, id, equipes);
            parties.add(partie);
        }
        return parties;
    }

    private Partie jeux(Map<String,int[]> adv, String id, List<Equipe> equipes) {
        int[] adversaire = adv.get(id);
        int rouge = adversaire[0] - 1;
        int bleu = adversaire[1] - 1;
        // Que la partie commence ...
        Partie partie = new Partie(equipes.get(rouge), equipes.get(bleu));
        return partie;
    }

  
    private Map<String,int[]> getRoundOpponents(int round, ConcoursParties constructionParties) {
        Map<String, int[]> ad;
        switch (round) {
            case 0:
                ad = constructionParties.roundOne();
                break;
            case 1:
                ad = constructionParties.roundTwo();
                break;
            case 2:
                ad = constructionParties.roundThree();
                break;
            default:
                ad = constructionParties.roundFour();
        }
        return ad;
    }
    
      private ConcoursParties getTeamsConfrontationSetup(int length) {

        ConcoursParties constructionParties;
        switch (length) {
            case 6:
                constructionParties = new Concours6Equipes();
                break;
            case 8:
                constructionParties = new Concours8Equipes();
                break;
            case 10:
                constructionParties = new Concours10Equipes();
                break;
            default:
                constructionParties = new Concours12Equipes();
        }
        return constructionParties;
    }
}
