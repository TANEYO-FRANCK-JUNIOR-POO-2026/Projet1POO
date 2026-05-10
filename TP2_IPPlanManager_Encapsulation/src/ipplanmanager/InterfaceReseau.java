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
 * Représente une interface réseau encapsulée.
 * Le nom est protégé contre les valeurs nulles ou vides.
 */
public class InterfaceReseau {

    private String    nom;
    private AdresseIP adresseIP;
    private boolean   active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        setNom(nom);
        this.adresseIP = adresseIP;
        this.active    = false;
    }

    // --- Getters ---
    public String    getNom()       { return nom;       }
    public AdresseIP getAdresseIP() { return adresseIP; }
    public boolean   isActive()     { return active;    }

    // --- Setters avec validation ---
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            System.out.println("Erreur : nom d'interface invalide. Nom par défaut : interface_inconnue");
            this.nom = "interface_inconnue";
        } else {
            this.nom = nom;
        }
    }

    public void setAdresseIP(AdresseIP adresseIP) {
        this.adresseIP = adresseIP;
    }

    public void activer()    { active = true;  }
    public void desactiver() { active = false; }

    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        } else {
            System.out.println("Adresse IP : non configurée");
        }
        System.out.println("État      : " + (active ? "active" : "inactive"));
    }
}
