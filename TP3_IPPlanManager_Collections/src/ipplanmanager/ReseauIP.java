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
 * Représente un réseau IP avec encapsulation complète.
 * Le masque CIDR est contrôlé : doit être compris entre 0 et 32.
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

    // --- Getters ---
    public String getAdresseReseau() { return adresseReseau; }
    public int    getMasqueCidr()    { return masqueCidr;    }
    public String getDescription()   { return description;   }

    // --- Setters avec validation ---
    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            System.out.println("Erreur : adresse réseau invalide. Valeur par défaut : 0.0.0.0");
            this.adresseReseau = "0.0.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }

    public void setMasqueCidr(int masqueCidr) {
        if (masqueCidr < 0 || masqueCidr > 32) {
            System.out.println("Erreur : masque CIDR invalide (" + masqueCidr + "). Valeur par défaut : 24");
            this.masqueCidr = 24;
        } else {
            this.masqueCidr = masqueCidr;
        }
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }

    public void afficher() {
        System.out.println("Réseau      : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
    }
}