package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {

    public void genererRapportComplet(
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<VLAN> vlans,
            ArrayList<Recommandation> recommandations,
            String cheminFichier) throws IOException {

        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("===== RAPPORT TECHNIQUE IPPLAN-MANAGER =====\n\n");

        writer.write("1. Besoins exprimes par l'utilisateur\n");
        for (int i = 0; i < besoins.size(); i++) {
            BesoinReseau b = besoins.get(i);
            writer.write("- " + b.getNom() + " : "
                    + b.getNombreHotes() + " hotes\n");
        }

        writer.write("\n2. Plan d'adressage propose\n");
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            writer.write("- " + r.getNomBesoin()
                    + " : " + r.getAdresseReseau()
                    + "/" + r.getCidr()
                    + " | Demandes : " + r.getNombreHotesDemandes()
                    + " | Capacite : " + r.getCapacite()
                    + " | Marge : " + r.getMarge() + "\n");
        }

        writer.write("\n3. VLANs proposes\n");
        for (int i = 0; i < vlans.size(); i++) {
            VLAN vlan = vlans.get(i);
            writer.write("- VLAN " + vlan.getId()
                    + " | " + vlan.getNom());
            if (vlan.getReseauAssocie() != null) {
                writer.write(" | "
                        + vlan.getReseauAssocie().getAdresseReseau()
                        + "/" + vlan.getReseauAssocie().getCidr());
            }
            writer.write("\n");
        }

        writer.write("\n4. Recommandations techniques\n");
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (int i = 0; i < recommandations.size(); i++) {
                Recommandation rec = recommandations.get(i);
                writer.write("- [" + rec.getPriorite() + "] "
                        + rec.getTitre()
                        + " : " + rec.getMessage() + "\n");
            }
        }

        writer.close();
        System.out.println("Rapport genere : " + cheminFichier);
    }
}  
