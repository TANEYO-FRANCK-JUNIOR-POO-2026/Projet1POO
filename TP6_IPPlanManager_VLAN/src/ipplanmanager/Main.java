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

public class Main {

    public static void main(String[] args) throws Exception {
         System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        
         System.out.println("===== IPPlan-Manager : TP6 - VLANs =====");

        
        // SCÉNARIO 1 — Fourni par le TP
      
        ArrayList<BesoinReseau> besoins1 = new ArrayList<BesoinReseau>();
        besoins1.add(new BesoinReseau("TECHNIQUE",      120));
        besoins1.add(new BesoinReseau("WIFI",            80));
        besoins1.add(new BesoinReseau("ADMINISTRATION",  50));
        besoins1.add(new BesoinReseau("SERVEURS",        20));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 =
                moteur.genererPlan("192.168.1.0", besoins1);

        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats1) {
            VLAN vlan = new VLAN(
                    numeroVLAN,
                    resultat.getNomBesoin(),
                    resultat,
                    "VLAN du service " + resultat.getNomBesoin()
            );
            gestionnaire1.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }

        System.out.println("\n===== VLANS GÉNÉRÉS — Scénario 1 =====\n");
        gestionnaire1.afficherTousLesVLANs();

        System.out.println("===== TEST DE RECHERCHE VLAN =====");
        VLAN vlanRecherche = gestionnaire1.rechercherVLAN(20);
        if (vlanRecherche != null) {
            System.out.println("VLAN trouvé :");
            vlanRecherche.afficher();
        } else {
            System.out.println("VLAN introuvable.");
        }

        gestionnaire1.afficherVLANsCritiques();

        System.out.println("\nVLAN avec la plus grande capacité :");
        VLAN maxVlan = gestionnaire1.obtenirVLANMaxCapacite();
        if (maxVlan != null) maxVlan.afficher();

       
        // SCÉNARIO 2 — Université (section 15)
       
        System.out.println("\n===== SCÉNARIO 2 : Université =====");

        ArrayList<BesoinReseau> besoins2 = new ArrayList<BesoinReseau>();
        besoins2.add(new BesoinReseau("ETUDIANTS",    500));
        besoins2.add(new BesoinReseau("WIFI_PUBLIC",  200));
        besoins2.add(new BesoinReseau("ENSEIGNANTS",  120));
        besoins2.add(new BesoinReseau("LABORATOIRES",  60));
        besoins2.add(new BesoinReseau("SERVEURS",      30));

        ArrayList<ResultatVLSM> resultats2 =
                moteur.genererPlan("172.16.0.0", besoins2);

        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        int numeroVLAN2 = 10;
        for (ResultatVLSM resultat : resultats2) {
            VLAN vlan = new VLAN(
                    numeroVLAN2,
                    resultat.getNomBesoin(),
                    resultat,
                    "VLAN du service " + resultat.getNomBesoin()
            );
            gestionnaire2.ajouterVLAN(vlan);
            numeroVLAN2 += 10;
        }

        System.out.println("\n===== VLANS GÉNÉRÉS — Université =====\n");
        gestionnaire2.afficherTousLesVLANs();

        System.out.println("Nombre total de VLANs : "
                + gestionnaire2.obtenirNombreVLANs());

        gestionnaire2.afficherVLANsCritiques();

        System.out.println("\nVLAN avec la plus grande capacité :");
        VLAN maxVlan2 = gestionnaire2.obtenirVLANMaxCapacite();
        if (maxVlan2 != null) maxVlan2.afficher();
    }
}
