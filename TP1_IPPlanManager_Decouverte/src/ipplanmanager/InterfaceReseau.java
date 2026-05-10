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
 * Représente une interface réseau d'un équipement.
 * Contient un nom, une adresse IP (objet AdresseIP) et un état.
 */
public class InterfaceReseau {

    String     nom;
    AdresseIP  adresseIP;
    boolean    active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        this.nom       = nom;
        this.adresseIP = adresseIP;
        this.active    = false;   // inactive par défaut
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
