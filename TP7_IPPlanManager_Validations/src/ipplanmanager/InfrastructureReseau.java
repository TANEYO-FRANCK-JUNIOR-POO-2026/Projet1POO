package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {

    private String                nom;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom         = nom;
        this.sousReseaux = new ArrayList<SousReseau>();
    }

    public void ajouterSousReseau(SousReseau sr) {
        if (sr != null) sousReseaux.add(sr);
    }

    public void afficherSousReseaux() {
        if (sousReseaux.isEmpty()) {
            System.out.println("Aucun sous-reseau enregistre.");
            return;
        }
        for (int i = 0; i < sousReseaux.size(); i++) {
            SousReseau sr = sousReseaux.get(i);
            sr.afficher();
            System.out.println();
        }
    }

    public void afficher() {
        System.out.println("Infrastructure : " + nom);
        System.out.println("\n===== SOUS-RESEAUX =====");
        afficherSousReseaux();
    }
}  
