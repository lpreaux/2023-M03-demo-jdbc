package fr.digi.jdbc.entites;

public class Article extends BaseEntity {
    private String ref;

    private String designation;

    private Double prix;

    private Fournisseur fournisseur;

    public Article(String ref, String designation, Double prix, Fournisseur fournisseur) {
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    public Article(int ID, String ref, String designation, Double prix, Fournisseur fournisseur) {
        super(ID);
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Article{" +
                "ID=" + ID +
                ", ref='" + ref + '\'' +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", fournisseur=" + fournisseur +
                '}';
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
