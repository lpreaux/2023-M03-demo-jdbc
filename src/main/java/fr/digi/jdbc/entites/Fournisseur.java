package fr.digi.jdbc.entites;

public class Fournisseur {
    private int ID;
    private String nom;

    public Fournisseur(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "ID=" + ID +
                ", nom='" + nom + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
