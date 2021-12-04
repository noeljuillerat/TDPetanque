package petanque.models;
import java.util.Random;

/**
 *
 * @author 33611
 */
public class Partie {
    private Equipe rouge;
    private Equipe bleue;
    private Equipe gagnante;
    private int pointsRouge = 0;
    private int pointsBleue = 0;
    

    public Partie(Equipe opponent1, Equipe bleue) {
        this.rouge = opponent1;
        this.bleue = bleue;
        this.gagnante = Round();
    }

    public Equipe getRouge() {
        return rouge;
    }

    public void setRouge(Equipe rouge) {
        this.rouge = rouge;
    }

    public Equipe getBleue() {
        return bleue;
    }

    public void setBleue(Equipe bleue) {
        this.bleue = bleue;
    }

    public int getPointsRouge() {
        return pointsRouge;
    }

    public void setPointsRouge(int pointsRouge) {
        this.pointsRouge = pointsRouge;
    }

    public int getPointsBleue() {
        return pointsBleue;
    }

    public void setPointsBleue(int pointsBleue) {
        this.pointsBleue = pointsBleue;
    }

    public Equipe getGagnante() {
        return gagnante;
    }

    public void setGagnante(Equipe gagnante) {
        this.gagnante = gagnante;
    }
    
    // Donne un nombre aléatoire en 0 et 14 
    public int RandomScore(){
        Random rand = new Random(); 
        //rand.nextInt(max-min) + min;
        int score = rand.nextInt(15);
        return score;
    }
    
    // Donne un score aléatoire au deux equipes qui s'affrontent et retourne le gagnant
       public Equipe Round(){
        pointsRouge = RandomScore();
        pointsBleue = RandomScore();
        while (pointsBleue == pointsRouge) {
            pointsBleue = RandomScore();
        }
        return pointsRouge > pointsBleue ? rouge : bleue;
    }

       @Override
    public String toString() {
        return "Partie{" + "Rouge : " + rouge + "vs Bleue : " + bleue + ", Score des Rouges : " +
                pointsRouge + "vs score des Bleus :" + pointsBleue +
                "gagnant : "+ gagnante.getId() + '}';
    }
   
}

