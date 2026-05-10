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
 * Représente un sous-réseau logique, associé à un objet ReseauIP.
 */
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
        this.nom = (nom == null || nom.isEmpty()) ? "Sous-reseau_inconnu" : nom;
    }

    public void setReseau(ReseauIP reseau) { this.reseau = reseau; }

    public void afficher() {
        System.out.println("Sous-réseau : " + nom);
        if (reseau != null) {
            reseau.afficher();
        }
    }
}
