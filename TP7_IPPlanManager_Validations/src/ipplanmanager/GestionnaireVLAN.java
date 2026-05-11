package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {

    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<VLAN>();
    }

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) return;
        for (int i = 0; i < vlans.size(); i++) {
            if (vlans.get(i).getId() == vlan.getId()) {
                throw new ConflitVLANException(
                    "Conflit VLAN : l'identifiant "
                    + vlan.getId() + " est deja utilise.");
            }
        }
        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (int i = 0; i < vlans.size(); i++) {
            vlans.get(i).afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (int i = 0; i < vlans.size(); i++) {
            if (vlans.get(i).getId() == id) return vlans.get(i);
        }
        return null;
    }

    public int obtenirNombreVLANs() { return vlans.size(); }

    public void afficherVLANsCritiques() {
        for (int i = 0; i < vlans.size(); i++) {
            VLAN vlan = vlans.get(i);
            if (vlan.getReseauAssocie() != null
                    && vlan.getReseauAssocie().getCapacite() > 100) {
                System.out.println("VLAN critique detecte :");
                System.out.println("VLAN " + vlan.getId()
                        + " - " + vlan.getNom()
                        + " - " + vlan.getReseauAssocie().getCapacite()
                        + " hotes");
            }
        }
    }
}  
