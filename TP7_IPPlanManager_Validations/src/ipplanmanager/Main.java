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

        System.out.println("===== IPPlan-Manager : TP7 - Validations avancees =====");

       
        // SCENARIO 1 — Plan normal, validation reussie
       
        System.out.println("\n--- Scenario 1 : Plan normal ---");

        ArrayList<BesoinReseau> besoins = new ArrayList<BesoinReseau>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE",      120));
        besoins.add(new BesoinReseau("WIFI",            80));
        besoins.add(new BesoinReseau("SERVEURS",        20));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats =
                moteur.genererPlan("192.168.1.0", besoins);

        System.out.println("\nPlan genere :");
        for (int i = 0; i < resultats.size(); i++) {
            resultats.get(i).afficher();
        }

        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        try {
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur adresse : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur chevauchement : " + e.getMessage());
        }

        // ================================================
        // SCENARIO 2 — Conflit VLAN
        // ================================================
        System.out.println("\n--- Test de conflit VLAN ---");
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        try {
            VLAN vlan10       = new VLAN(10, "ADMINISTRATION",
                    resultats.get(0), "VLAN Administration");
            VLAN vlan20       = new VLAN(20, "TECHNIQUE",
                    resultats.get(1), "VLAN Technique");
            VLAN vlan10Erreur = new VLAN(10, "WIFI",
                    resultats.get(2), "VLAN WiFi avec ID deja utilise");

            gestionnaire.ajouterVLAN(vlan10);
            gestionnaire.ajouterVLAN(vlan20);
            gestionnaire.ajouterVLAN(vlan10Erreur);
            gestionnaire.afficherTousLesVLANs();
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        // ================================================
        // SCENARIO 3 — Adresse IP invalide
        // ================================================
        System.out.println("\n--- Test adresse IP invalide ---");
        try {
            CalculateurReseau.verifierAdresseIP("192.168.300.0");
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }

        // ================================================
        // SCENARIO 4 — Chevauchement manuel
        // ================================================
        System.out.println("\n--- Test chevauchement de reseaux ---");
        ArrayList<ResultatVLSM> reseauxManuel =
                new ArrayList<ResultatVLSM>();
        reseauxManuel.add(new ResultatVLSM("RESEAU_A",
                "192.168.1.0",  25, "255.255.255.128", 126));
        reseauxManuel.add(new ResultatVLSM("RESEAU_B",
                "192.168.1.64", 26, "255.255.255.192", 62));
        try {
            validateur.verifierChevauchements(reseauxManuel);
            validateur.afficherValidationReussie();
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }

        // ================================================
        // SCENARIO 5 — Reseau insuffisant
        // ================================================
        System.out.println("\n--- Test reseau insuffisant ---");
        ArrayList<BesoinReseau> grandsBesoins =
                new ArrayList<BesoinReseau>();
        grandsBesoins.add(new BesoinReseau("SERVICE_A", 100));
        grandsBesoins.add(new BesoinReseau("SERVICE_B", 100));
        grandsBesoins.add(new BesoinReseau("SERVICE_C", 100));
        try {
            CalculateurReseau.verifierCapaciteReseau(
                    "192.168.1.0", 26, grandsBesoins);
            System.out.println("Reseau suffisant.");
        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }
    }
} 
