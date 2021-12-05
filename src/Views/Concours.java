package Views;

import Controllers.PartieC;
import Controllers.JoueursC;
import Controllers.EquipeC;
import Models.Equipe;

import java.util.List;

public class Concours {
    List<Equipe> equipes;
    /**
     * Main method for executing different steps of the program
     */
   
    private void afficheResultats(List<Equipe> equipes) {
        System.out.println("\nRésulats");
           
        //scores
        for (Equipe equipe: equipes) {         
            System.out.println("Equipe " + (equipe.getId() + 1) + " : partie gagnées " + equipe.getPartiesGagnees());
        }
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
