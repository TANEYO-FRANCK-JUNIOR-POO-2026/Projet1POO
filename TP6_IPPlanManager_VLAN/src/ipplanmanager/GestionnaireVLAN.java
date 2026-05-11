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

public class GestionnaireVLAN {

    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<VLAN>();
    }

    public void ajouterVLAN(VLAN vlan) {
        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }

    // Travail supplémentaire — section 16
    public void afficherVLANsCritiques() {
        System.out.println("===== VLANs critiques (capacité > 100 hôtes) =====");
        boolean trouve = false;
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null
                    && vlan.getReseauAssocie().getCapacite() > 100) {
                System.out.println("VLAN critique détecté :");
                System.out.println("VLAN " + vlan.getId()
                        + " - " + vlan.getNom()
                        + " - " + vlan.getReseauAssocie().getCapacite()
                        + " hôtes");
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun VLAN critique détecté.");
        }
    }

    // Travail demandé — VLAN avec la plus grande capacité
    public VLAN obtenirVLANMaxCapacite() {
        VLAN max = null;
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null) {
                if (max == null || vlan.getReseauAssocie().getCapacite()
                        > max.getReseauAssocie().getCapacite()) {
                    max = vlan;
                }
            }
        }
        return max;
    }
}
