package ipplanmanager;

public class ReseauIP {

    private String adresseReseau;
    private int    masqueCidr;
    private String description;

    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        setAdresseReseau(adresseReseau);
        setMasqueCidr(masqueCidr);
        setDescription(description);
    }

    public String getAdresseReseau() { return adresseReseau; }
    public int    getMasqueCidr()    { return masqueCidr;    }
    public String getDescription()   { return description;   }

    public void setAdresseReseau(String a) {
        this.adresseReseau = (a == null || a.isEmpty()) ? "0.0.0.0" : a;
    }

    public void setMasqueCidr(int m) {
        this.masqueCidr = (m < 0 || m > 32) ? 24 : m;
    }

    public void setDescription(String d) {
        this.description = (d == null || d.isEmpty()) ? "Aucune description" : d;
    }

    public void afficher() {
        System.out.println("Reseau : "      + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
        System.out.println("Masque : "      + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));
        System.out.println("Capacite : "    + CalculateurReseau.calculerNombreHotes(masqueCidr) + " hotes");
    }
}  
