package fr.digi.jdbc.entites;

public class Fournisseur extends BaseEntity {
    private String nom;

    public Fournisseur(int ID, String nom) {
        super(ID);
        this.nom = nom;
    }

    public Fournisseur(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "ID=" + ID +
                ", nom='" + nom + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
