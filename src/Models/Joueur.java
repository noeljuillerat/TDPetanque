package Models;
/**
 *
 * @author 33611
 */
public class Joueur {
    private int id;
    private String prenom;    
    private String nom;
    private int age;
    
    public Joueur(String prenom, String nom, int age, int id) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + '}';
    } 
}
