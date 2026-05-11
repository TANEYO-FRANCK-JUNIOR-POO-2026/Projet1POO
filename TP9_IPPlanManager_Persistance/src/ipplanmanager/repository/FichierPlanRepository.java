package ipplanmanager.repository;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {

    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats,
                                   String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge\n");
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            writer.write(r.getNomBesoin() + ";"
                    + r.getAdresseReseau() + ";"
                    + r.getCidr() + ";"
                    + r.getMasqueDecimal() + ";"
                    + r.getNombreHotesDemandes() + ";"
                    + r.getCapacite() + ";"
                    + r.getMarge() + "\n");
        }
        writer.close();
        System.out.println("Plan CSV genere : " + cheminFichier);
    }

    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans,
                                     String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
        for (int i = 0; i < vlans.size(); i++) {
            VLAN vlan = vlans.get(i);
            if (vlan.getReseauAssocie() != null) {
                writer.write(vlan.getId() + ";"
                        + vlan.getNom() + ";"
                        + vlan.getReseauAssocie().getAdresseReseau() + ";"
                        + vlan.getReseauAssocie().getCidr() + ";"
                        + vlan.getReseauAssocie().getCapacite() + "\n");
            }
        }
        writer.close();
        System.out.println("VLANs CSV genere : " + cheminFichier);
    }

    public void sauvegarderRecommandations(
            ArrayList<Recommandation> recommandations,
            String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n\n");
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (int i = 0; i < recommandations.size(); i++) {
                Recommandation rec = recommandations.get(i);
                writer.write("[" + rec.getPriorite() + "] "
                        + rec.getTitre() + " : "
                        + rec.getMessage() + "\n");
            }
        }
        writer.close();
        System.out.println("Recommandations generees : " + cheminFichier);
    }
}  
