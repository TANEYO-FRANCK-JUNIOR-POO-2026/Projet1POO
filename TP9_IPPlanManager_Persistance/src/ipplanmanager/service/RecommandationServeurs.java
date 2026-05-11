package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationServeurs implements RegleRecommandation {
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("SERVEUR")) {
            return new Recommandation(
                    "Protection du VLAN Serveurs", "ELEVEE",
                    "Le VLAN " + vlan.getNom()
                    + " doit etre protege par des ACL et surveille en priorite.");
        }
        return null;
    }
}  
