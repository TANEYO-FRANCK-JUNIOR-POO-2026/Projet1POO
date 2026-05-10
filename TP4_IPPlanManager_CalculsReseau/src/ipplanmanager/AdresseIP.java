/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */

public class AdresseIP {

    private String valeur;

    public AdresseIP(String valeur) {
        setValeur(valeur);
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresse IP invalide. Valeur par défaut : 0.0.0.0");
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
