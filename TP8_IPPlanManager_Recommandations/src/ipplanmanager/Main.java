package ipplanmanager;

import java.util.ArrayList;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Encodage non supporte");
        }

        System.out.println("===== IPPlan-Manager : TP8 - Recommandations =====");

       
        // SCENARIO 1 — Fourni par le TP
      
        System.out.println("\n--- Scenario 1 : Universite ---");

        ArrayList<BesoinReseau> besoins1 = new ArrayList<BesoinReseau>();
        besoins1.add(new BesoinReseau("ETUDIANTS",    500));
        besoins1.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins1.add(new BesoinReseau("ENSEIGNANTS",  120));
        besoins1.add(new BesoinReseau("LABORATOIRES",  60));
        besoins1.add(new BesoinReseau("SERVEURS",      30));

        MoteurVLSM moteurVLSM = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 =
                moteurVLSM.genererPlan("10.10.0.0", besoins1);

        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        try {
            for (int i = 0; i < resultats1.size(); i++) {
                ResultatVLSM r = resultats1.get(i);
                VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(),
                        r, "VLAN " + r.getNomBesoin());
                gestionnaire1.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("\nPlan VLAN genere :");
        gestionnaire1.afficherTousLesVLANs();

        MoteurRecommandation moteur1 = new MoteurRecommandation();
        moteur1.ajouterRegle(new RecommandationWifiInvite());
        moteur1.ajouterRegle(new RecommandationServeurs());
        moteur1.ajouterRegle(new RecommandationGrandVLAN());
        moteur1.ajouterRegle(new RecommandationAdministration());
        moteur1.ajouterRegle(new RecommandationMargeAdresse());

        ArrayList<Recommandation> recommandations1 =
                moteur1.analyserVLANs(gestionnaire1.getVlans());
        System.out.println("Recommandations proposees :");
        moteur1.afficherRecommandations(recommandations1);

       
        // SCENARIO 2 — Travail demande (section 18)
       
        System.out.println("\n--- Scenario 2 : Entreprise ---");

        ArrayList<BesoinReseau> besoins2 = new ArrayList<BesoinReseau>();
        besoins2.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins2.add(new BesoinReseau("WIFI_INVITES",  120));
        besoins2.add(new BesoinReseau("SERVEURS",       20));
        besoins2.add(new BesoinReseau("CAMERAS",        80));
        besoins2.add(new BesoinReseau("VOIP",           60));

        ArrayList<ResultatVLSM> resultats2 =
                moteurVLSM.genererPlan("172.16.0.0", besoins2);

        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        int numeroVLAN2 = 10;
        try {
            for (int i = 0; i < resultats2.size(); i++) {
                ResultatVLSM r = resultats2.get(i);
                VLAN vlan = new VLAN(numeroVLAN2, r.getNomBesoin(),
                        r, "VLAN " + r.getNomBesoin());
                gestionnaire2.ajouterVLAN(vlan);
                numeroVLAN2 += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("\nPlan VLAN genere :");
        gestionnaire2.afficherTousLesVLANs();

        MoteurRecommandation moteur2 = new MoteurRecommandation();
        moteur2.ajouterRegle(new RecommandationWifiInvite());
        moteur2.ajouterRegle(new RecommandationServeurs());
        moteur2.ajouterRegle(new RecommandationGrandVLAN());
        moteur2.ajouterRegle(new RecommandationAdministration());
        moteur2.ajouterRegle(new RecommandationMargeAdresse());

        ArrayList<Recommandation> recommandations2 =
                moteur2.analyserVLANs(gestionnaire2.getVlans());
        System.out.println("Recommandations proposees :");
        moteur2.afficherRecommandations(recommandations2);
    }
} 
