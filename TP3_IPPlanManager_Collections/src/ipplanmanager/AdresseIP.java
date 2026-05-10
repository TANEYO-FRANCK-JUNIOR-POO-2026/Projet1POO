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
 * Représente une adresse IPv4 avec encapsulation complète.
 * Les données sont protégées et validées via des getters/setters.
 */
public class AdresseIP {

    private String valeur;

    public AdresseIP(String valeur) {
        setValeur(valeur);  // passe par le setter pour valider dès la création
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresse IP invalide. Valeur par défaut appliquée : 0.0.0.0");
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }

    /**
     * Travail supplémentaire demandé :
     * Retourne true si l'adresse IP commence par "192."
     */
    public boolean estAdresseLocale() {
        return valeur.startsWith("192.");
    }

    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }
}
