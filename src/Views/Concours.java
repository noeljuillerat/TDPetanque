package Views;

import Controllers.PartieC;
import Controllers.JoueursC;
import Controllers.EquipeC;
import Models.Equipe;
import java.util.ArrayList;

import java.util.List;

public class Concours {
    List<Equipe> equipes;
    /**
     * Main method for executing different steps of the program
     */
   
    private void afficheResultats(List<Equipe> equipes) {
        System.out.println("\nRésulats");
        List winner = new ArrayList();
        //Affichage des résultats
        for (Equipe equipe: equipes) {         
            System.out.println("Equipe " + (equipe.getId() + 1) + " : partie gagnées, " + equipe.getPartiesGagnees() + " avec un score de : " + equipe.getScore());
            if(equipe.getId() == 0){
                winner.add(equipe.getId() + 1);
                winner.add(equipe.getPartiesGagnees());
                winner.add(equipe.getScore());
            }else{
                if((int) winner.get(1) < equipe.getPartiesGagnees()){
                    winner.set(0, equipe.getId() + 1);
                    winner.set(1, equipe.getPartiesGagnees());
                    winner.set(2, equipe.getScore());
                }else if((int) winner.get(1) <= equipe.getPartiesGagnees() && (int) winner.get(2) < equipe.getScore()){
                    winner.set(0, equipe.getId() + 1);
                    winner.set(1, equipe.getPartiesGagnees());
                    winner.set(2, equipe.getScore());
                }
            }
        }
        System.out.println("\nLe gagnant de la compétition est l'équipe " + winner.get(0) + " avec " + winner.get(1) + " parties gagnées et un score de " + winner.get(2));
    }
    
    public void start() {
        JoueursC joueursC = new JoueursC();
        joueursC.afficheJoueurs();

        EquipeC equipeC = new EquipeC(joueursC.getJoueurs());
        equipeC.equipesView();

        PartieC partieC = new PartieC(equipeC.getEquipes());
        partieC.partiesView(equipeC.getEquipes().size());

        equipeC.updateTeamsMatchs(partieC.getMatchs());

        afficheResultats(equipeC.getEquipes());
    }
}
