package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public class RecommandationWifiInvite implements RegleRecommandation {
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("WIFI")) {
            return new Recommandation(
                    "Isolation du WiFi", "ELEVEE",
                    "Le VLAN " + vlan.getNom()
                    + " doit etre isole des VLANs internes sensibles.");
        }
        return null;
    }
}  
