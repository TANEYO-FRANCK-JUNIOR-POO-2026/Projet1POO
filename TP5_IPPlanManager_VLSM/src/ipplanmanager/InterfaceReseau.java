/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
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

    public String    getNom()       { return nom;       }
    public AdresseIP getAdresseIP() { return adresseIP; }
    public boolean   isActive()     { return active;    }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "interface_inconnue" : nom;
    }

    public void setAdresseIP(AdresseIP adresseIP) {
        this.adresseIP = adresseIP;
    }

    public void activer()    { this.active = true;  }
    public void desactiver() { this.active = false; }

    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        } else {
            System.out.println("Adresse IP non configurée.");
        }
        System.out.println("Etat : " + (active ? "active" : "inactive"));
    }
}
