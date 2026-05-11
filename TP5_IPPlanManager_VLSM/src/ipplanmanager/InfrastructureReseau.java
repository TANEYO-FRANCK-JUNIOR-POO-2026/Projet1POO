/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */

import java.util.ArrayList;

public class InfrastructureReseau {

    private String                nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom         = nom;
        this.equipements = new ArrayList<Equipement>();
        this.sousReseaux = new ArrayList<SousReseau>();
    }

    public void ajouterEquipement(Equipement e) {
        if (e != null) equipements.add(e);
    }

    public void ajouterSousReseau(SousReseau sr) {
        if (sr != null) sousReseaux.add(sr);
    }

    public void afficherSousReseaux() {
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-réseau enregistré.");
            return;
        }
        for (int i = 0; i < sousReseaux.size(); i++) {
            SousReseau sr = sousReseaux.get(i);
            sr.afficher();
            System.out.println();
        }
    }

    public void afficherEquipements() {
        if (equipements.isEmpty()) {
            System.out.println("Aucun équipement enregistré.");
            return;
        }
        for (int i = 0; i < equipements.size(); i++) {
            Equipement e = equipements.get(i);
            e.afficher();
            System.out.println();
        }
    }

    public void rechercherEquipement(String nomRecherche) {
        boolean trouve = false;
        for (int i = 0; i < equipements.size(); i++) {
            Equipement e = equipements.get(i);
            if (e.getNom().equalsIgnoreCase(nomRecherche)) {
                System.out.println("Équipement trouvé :");
                e.afficher();
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Équipement introuvable : " + nomRecherche);
        }
    }

    public void afficher() {
        System.out.println("Infrastructure : " + nom);
        System.out.println("\n===== SOUS-RÉSEAUX =====");
        afficherSousReseaux();
        System.out.println("\n===== ÉQUIPEMENTS =====");
        afficherEquipements();
    }
}