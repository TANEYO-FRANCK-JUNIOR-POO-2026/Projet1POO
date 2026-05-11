/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */
public class BesoinReseau {
    private String nom;
    private int nombreHotes;

    public BesoinReseau(String nom, int nombreHotes) {
        setNom(nom);
        setNombreHotes(nombreHotes);
    }

    public String getNom() { return nom; }
    public int getNombreHotes() { return nombreHotes; }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "besoin_inconnu" : nom;
    }

    public void setNombreHotes(int nombreHotes) {
        this.nombreHotes = (nombreHotes <= 0) ? 1 : nombreHotes;
    }

    public void afficher() {
        System.out.println("Besoin : " + nom + " | Hôtes demandés : " + nombreHotes);
    }
}
