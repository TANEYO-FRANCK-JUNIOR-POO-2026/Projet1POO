package ipplanmanager;

public class SousReseau {

    private String   nom;
    private ReseauIP reseau;

    public SousReseau(String nom, ReseauIP reseau) {
        setNom(nom);
        this.reseau = reseau;
    }

    public String   getNom()    { return nom;    }
    public ReseauIP getReseau() { return reseau; }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "sousreseau_inconnu" : nom;
    }

    public void setReseau(ReseauIP r) { this.reseau = r; }

    public void afficher() {
        System.out.println("Sous-reseau : " + nom);
        if (reseau != null) {
            reseau.afficher();
        } else {
            System.out.println("Aucun reseau associe.");
        }
    }
}  
