package ipplanmanager;

public class AdresseIP {

    private String valeur;

    public AdresseIP(String valeur) {
        setValeur(valeur);
    }

    public String getValeur() { return valeur; }

    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }

    public boolean estAdresseLocale() {
        return valeur.startsWith("192.");
    }

    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }
}  
