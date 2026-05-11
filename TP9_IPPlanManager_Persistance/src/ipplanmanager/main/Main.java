package ipplanmanager.main;

import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import ipplanmanager.service.GestionnaireVLAN;
import ipplanmanager.service.MoteurRecommandation;
import ipplanmanager.service.MoteurVLSM;
import ipplanmanager.service.RapportService;
import ipplanmanager.service.RecommandationAdministration;
import ipplanmanager.service.RecommandationGrandVLAN;
import ipplanmanager.service.RecommandationMargeAdresse;
import ipplanmanager.service.RecommandationServeurs;
import ipplanmanager.service.RecommandationWifiInvite;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Encodage non supporte");
        }

        System.out.println("===== IPPlan-Manager : TP9 - Persistance =====");

        String fichierBesoins         = "exports/besoins.csv";
        String fichierBesoinsPme      = "exports/besoins_pme.csv";
        String fichierPlan            = "exports/plan_adressage.csv";
        String fichierVlans           = "exports/vlans.csv";
        String fichierRecommandations = "exports/recommandations.txt";
        String fichierRapport         = "exports/rapport_complet.txt";
        String fichierRapportPme      = "exports/rapport_pme.txt";

        BesoinRepository     besoinRepo  = new BesoinRepository();
        FichierPlanRepository planRepo   = new FichierPlanRepository();
        RapportService        rapportSvc = new RapportService();

        // ================================================
        // SCENARIO 1 — Universite (depuis besoins.csv)
        // ================================================
        System.out.println("\n--- Scenario 1 : Universite ---");
        try {
            ArrayList<BesoinReseau> besoins =
                    besoinRepo.chargerBesoins(fichierBesoins);

            System.out.println("Besoins charges depuis " + fichierBesoins);
            for (int i = 0; i < besoins.size(); i++) {
                besoins.get(i).afficher();
            }

            MoteurVLSM moteur = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultats =
                    moteur.genererPlan("10.10.0.0", besoins);

            GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
            int numeroVLAN = 10;
            for (int i = 0; i < resultats.size(); i++) {
                ResultatVLSM r = resultats.get(i);
                gestionnaire.ajouterVLAN(new VLAN(numeroVLAN,
                        r.getNomBesoin(), r, "VLAN " + r.getNomBesoin()));
                numeroVLAN += 10;
            }

            System.out.println("\nPlan genere :");
            for (int i = 0; i < resultats.size(); i++) {
                resultats.get(i).afficher();
            }

            MoteurRecommandation moteurRec = new MoteurRecommandation();
            moteurRec.ajouterRegle(new RecommandationWifiInvite());
            moteurRec.ajouterRegle(new RecommandationServeurs());
            moteurRec.ajouterRegle(new RecommandationGrandVLAN());
            moteurRec.ajouterRegle(new RecommandationAdministration());
            moteurRec.ajouterRegle(new RecommandationMargeAdresse());

            ArrayList<Recommandation> recommandations =
                    moteurRec.analyserVLANs(gestionnaire.getVlans());

            System.out.println("\nRecommandations :");
            moteurRec.afficherRecommandations(recommandations);

            planRepo.sauvegarderPlanCSV(resultats, fichierPlan);
            planRepo.sauvegarderVLANsCSV(gestionnaire.getVlans(), fichierVlans);
            planRepo.sauvegarderRecommandations(recommandations, fichierRecommandations);
            rapportSvc.genererRapportComplet(besoins, resultats,
                    gestionnaire.getVlans(), recommandations, fichierRapport);

        } catch (IOException e) {
            System.out.println("Erreur fichier : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        // ================================================
        // SCENARIO 2 — PME (depuis besoins_pme.csv)
        // ================================================
        System.out.println("\n--- Scenario 2 : PME ---");
        try {
            ArrayList<BesoinReseau> besoinsPme =
                    besoinRepo.chargerBesoins(fichierBesoinsPme);

            System.out.println("Besoins charges depuis " + fichierBesoinsPme);
            for (int i = 0; i < besoinsPme.size(); i++) {
                besoinsPme.get(i).afficher();
            }

            MoteurVLSM moteur = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultatsPme =
                    moteur.genererPlan("192.168.1.0", besoinsPme);

            GestionnaireVLAN gestionnaireP = new GestionnaireVLAN();
            int numeroVLAN = 10;
            for (int i = 0; i < resultatsPme.size(); i++) {
                ResultatVLSM r = resultatsPme.get(i);
                gestionnaireP.ajouterVLAN(new VLAN(numeroVLAN,
                        r.getNomBesoin(), r, "VLAN " + r.getNomBesoin()));
                numeroVLAN += 10;
            }

            System.out.println("\nPlan genere :");
            for (int i = 0; i < resultatsPme.size(); i++) {
                resultatsPme.get(i).afficher();
            }

            MoteurRecommandation moteurRec = new MoteurRecommandation();
            moteurRec.ajouterRegle(new RecommandationWifiInvite());
            moteurRec.ajouterRegle(new RecommandationServeurs());
            moteurRec.ajouterRegle(new RecommandationGrandVLAN());
            moteurRec.ajouterRegle(new RecommandationAdministration());
            moteurRec.ajouterRegle(new RecommandationMargeAdresse());

            ArrayList<Recommandation> recommandationsPme =
                    moteurRec.analyserVLANs(gestionnaireP.getVlans());

            System.out.println("\nRecommandations :");
            moteurRec.afficherRecommandations(recommandationsPme);

            // Sauvegarde besoins PME (travail supplementaire section 17)
            besoinRepo.sauvegarderBesoins(besoinsPme,
                    "exports/besoins_pme_sauvegarde.csv");

            rapportSvc.genererRapportComplet(besoinsPme, resultatsPme,
                    gestionnaireP.getVlans(), recommandationsPme, fichierRapportPme);

        } catch (IOException e) {
            System.out.println("Erreur fichier : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("\nTraitement termine. Fichiers dans exports/");
    }
} 
