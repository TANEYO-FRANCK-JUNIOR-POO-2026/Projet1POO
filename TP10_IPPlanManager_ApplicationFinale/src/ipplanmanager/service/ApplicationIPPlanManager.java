package ipplanmanager.service;

import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.repository.FichierPlanRepository;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {

    private ConsoleService          console;
    private MoteurVLSM              moteurVLSM;
    private GestionnaireVLAN        gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation    moteurRecommandation;
    private FichierPlanRepository   fichierRepository;
    private RapportService          rapportService;
    private BesoinRepository        besoinRepository;

    public ApplicationIPPlanManager() {
        console              = new ConsoleService();
        moteurVLSM           = new MoteurVLSM();
        gestionnaireVLAN     = new GestionnaireVLAN();
        validateur           = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository    = new FichierPlanRepository();
        rapportService       = new RapportService();
        besoinRepository     = new BesoinRepository();

        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommandationAdministration());
        moteurRecommandation.ajouterRegle(new RecommandationMargeAdresse());
    }

    public void demarrer() {
        System.out.println("===== IPPlan-Manager : Application Finale =====");
        boolean continuer = true;

        while (continuer) {
            console.afficherMenu();
            String choix = console.saisirTexte("");

            switch (choix.trim()) {
                case "1":
                    executerGenerationComplete(null);
                    break;
                case "2":
                    String chemin = console.saisirTexte(
                            "Chemin du fichier CSV : ");
                    executerGenerationComplete(chemin);
                    break;
                case "3":
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Entrez 1, 2 ou 3.");
            }
        }
    }

    private void executerGenerationComplete(String cheminCSV) {

        // Reinitialiser le gestionnaire a chaque execution
        gestionnaireVLAN = new GestionnaireVLAN();

        try {
            ArrayList<BesoinReseau> besoins;
            String nomProjet;
            String adresseDepart;

            if (cheminCSV != null && !cheminCSV.isEmpty()) {
                // Chargement depuis fichier CSV
                besoins      = besoinRepository.chargerBesoins(cheminCSV);
                nomProjet    = console.saisirTexte("Nom du projet reseau : ");
                adresseDepart = console.saisirTexte(
                        "Adresse reseau de depart : ");
                System.out.println("Besoins charges depuis : " + cheminCSV);
            } else {
                // Saisie manuelle
                nomProjet    = console.saisirTexte("Nom du projet reseau : ");
                adresseDepart = console.saisirTexte(
                        "Adresse reseau de depart : ");
                besoins      = console.saisirBesoins();
            }

            // Validation adresse de depart
            CalculateurReseau.verifierAdresseIP(adresseDepart);

            // Generation du plan VLSM
            ArrayList<ResultatVLSM> resultats =
                    moteurVLSM.genererPlan(adresseDepart, besoins);

            // Validation du plan
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();

            // Generation des VLANs
            genererVLANs(resultats);

            // Affichage des resultats
            afficherResultats(besoins, resultats);

            // Recommandations
            ArrayList<Recommandation> recommandations =
                    moteurRecommandation.analyserVLANs(
                            gestionnaireVLAN.getVlans());

            System.out.println("\nRecommandations :");
            moteurRecommandation.afficherRecommandations(recommandations);

            // Sauvegarde
            sauvegarderResultats(nomProjet, besoins,
                    resultats, recommandations);

        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur fichier : " + e.getMessage());
        }
    }

    private void genererVLANs(ArrayList<ResultatVLSM> resultats)
            throws ConflitVLANException {
        int numeroVLAN = 10;
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r = resultats.get(i);
            VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(),
                    r, "VLAN " + r.getNomBesoin());
            gestionnaireVLAN.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
    }

    private void afficherResultats(ArrayList<BesoinReseau> besoins,
                                   ArrayList<ResultatVLSM> resultats) {
        System.out.println("\nPlan d'adressage propose :");
        for (int i = 0; i < resultats.size(); i++) {
            resultats.get(i).afficher();
        }
        System.out.println("\nVLANs generes :");
        gestionnaireVLAN.afficherTousLesVLANs();
    }

    private void sauvegarderResultats(
            String nomProjet,
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<Recommandation> recommandations) throws IOException {

        String prefixe = "exports/"
                + nomProjet.replace(" ", "_");

        fichierRepository.sauvegarderPlanCSV(resultats,
                prefixe + "_plan.csv");
        fichierRepository.sauvegarderVLANsCSV(
                gestionnaireVLAN.getVlans(),
                prefixe + "_vlans.csv");
        fichierRepository.sauvegarderRecommandations(recommandations,
                prefixe + "_recommandations.txt");
        rapportService.genererRapportComplet(besoins, resultats,
                gestionnaireVLAN.getVlans(), recommandations,
                prefixe + "_rapport.txt");

        System.out.println("\nFichiers sauvegardes dans exports/ :");
        System.out.println("- " + prefixe + "_plan.csv");
        System.out.println("- " + prefixe + "_vlans.csv");
        System.out.println("- " + prefixe + "_recommandations.txt");
        System.out.println("- " + prefixe + "_rapport.txt");
    }
}