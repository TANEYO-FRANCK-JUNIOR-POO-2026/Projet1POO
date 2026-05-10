/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */

/**
 * ReseauIP enrichie : l'affichage inclut désormais les calculs automatiques
 * fournis par CalculateurReseau (classe, masque, capacité, type privé/public).
 */
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

    public void setAdresseReseau(String adresseReseau) {
        this.adresseReseau = (adresseReseau == null || adresseReseau.isEmpty())
                ? "0.0.0.0" : adresseReseau;
    }

    public void setMasqueCidr(int masqueCidr) {
        this.masqueCidr = (masqueCidr < 0 || masqueCidr > 32) ? 24 : masqueCidr;
    }

    public void setDescription(String description) {
        this.description = (description == null || description.isEmpty())
                ? "Aucune description" : description;
    }

    /** Affichage enrichi avec calculs automatiques. */
    public void afficher() {
        System.out.println("Réseau          : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description     : " + description);
        System.out.println("Classe réseau   : "
                + CalculateurReseau.obtenirClasseReseau(adresseReseau));
        System.out.println("Masque décimal  : "
                + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));
        System.out.println("Capacité max    : "
                + CalculateurReseau.calculerNombreHotes(masqueCidr) + " hôtes");
        System.out.println("Type            : "
                + (CalculateurReseau.estReseauPrive(adresseReseau)
                   ? "Réseau privé (RFC 1918)" : "Réseau public"));
    }
}
