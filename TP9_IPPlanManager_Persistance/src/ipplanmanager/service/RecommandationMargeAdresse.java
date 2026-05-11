package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationMargeAdresse implements RegleRecommandation {
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int capacite = vlan.getReseauAssocie().getCapacite();
            int demandes = vlan.getReseauAssocie().getNombreHotesDemandes();
            int marge    = capacite - demandes;
            if (demandes > 0 && marge < 10) {
                return new Recommandation(
                        "Marge d'adresses insuffisante", "MOYENNE",
                        "Le VLAN " + vlan.getNom()
                        + " a une marge de seulement " + marge
                        + " adresses. Prevoir un sous-reseau plus grand.");
            }
        }
        return null;
    }
}  
