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

/**
 * Représente l'infrastructure réseau complète.
 * Contient les collections d'équipements et de sous-réseaux.
 * Inclut le travail supplémentaire : rechercherEquipement().
 */
public class InfrastructureReseau {

    private String                 nom;
    private ArrayList<Equipement>  equipements;
    private ArrayList<SousReseau>  sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom       = nom;
        equipements    = new ArrayList<>();
        sousReseaux    = new ArrayList<>();
    }

    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }

    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }

    public void afficherEquipements() {
        for (Equipement eq : equipements) {
            eq.afficher();
            System.out.println();
        }
    }

    public void afficherSousReseaux() {
        for (SousReseau sr : sousReseaux) {
            sr.afficher();
            System.out.println();
        }
    }

    /**
     * Travail supplémentaire :
     * Recherche un équipement par son nom et affiche ses informations.
     * Affiche "Équipement introuvable." si non trouvé.
     */
    public void rechercherEquipement(String nomRecherche) {
        System.out.println(">>> Recherche : " + nomRecherche);
        boolean trouve = false;
        for (Equipement eq : equipements) {
            if (eq.getNom().equalsIgnoreCase(nomRecherche)) {
                eq.afficher();
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Équipement introuvable.");
        }
    }

    public void afficher() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("  Infrastructure : " + nom);
        System.out.println("╚══════════════════════════════════════╝");

        System.out.println();
        System.out.println("===== SOUS-RÉSEAUX =====");
        afficherSousReseaux();

        System.out.println();
        System.out.println("===== ÉQUIPEMENTS =====");
        afficherEquipements();
    }
}
