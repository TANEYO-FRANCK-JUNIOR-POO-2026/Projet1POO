package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;
import java.util.ArrayList;

public class MoteurRecommandation {

    private ArrayList<RegleRecommandation> regles;

    public MoteurRecommandation() {
        regles = new ArrayList<RegleRecommandation>();
    }

    public void ajouterRegle(RegleRecommandation regle) {
        regles.add(regle);
    }

    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recommandations =
                new ArrayList<Recommandation>();
        for (int i = 0; i < vlans.size(); i++) {
            VLAN vlan = vlans.get(i);
            for (int j = 0; j < regles.size(); j++) {
                Recommandation r = regles.get(j).analyser(vlan);
                if (r != null) recommandations.add(r);
            }
        }
        return recommandations;
    }

    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        if (recommandations.isEmpty()) {
            System.out.println("Aucune recommandation particuliere.");
            return;
        }
        for (int i = 0; i < recommandations.size(); i++) {
            recommandations.get(i).afficher();
        }
    }
}  
