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

        System.out.println("===== TP3 - Collections =====");

        // ================================================
        // SCENARIO 1 — Infrastructure IRT-LAB
        // ================================================
        System.out.println("\n--- Scenario 1 : Infrastructure IRT-LAB ---");

        InfrastructureReseau infra1 = new InfrastructureReseau("IRT-LAB");

        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Reseau principal");
        ReseauIP reseau2 = new ReseauIP("192.168.2.0", 26, "Reseau serveurs");

        SousReseau sr1 = new SousReseau("LAN-Principal", reseau1);
        SousReseau sr2 = new SousReseau("LAN-Serveurs",  reseau2);

        infra1.ajouterSousReseau(sr1);
        infra1.ajouterSousReseau(sr2);

        Equipement routeur = new Equipement("Routeur-Principal", "Routeur");
        InterfaceReseau ir1 = new InterfaceReseau("eth0",
                new AdresseIP("192.168.1.1"));
        InterfaceReseau ir2 = new InterfaceReseau("eth1",
                new AdresseIP("192.168.2.1"));
        ir1.activer();
        ir2.activer();
        routeur.ajouterInterface(ir1);
        routeur.ajouterInterface(ir2);

        Equipement switch1 = new Equipement("Switch-Coeur", "Switch");
        InterfaceReseau ir3 = new InterfaceReseau("fa0/1",
                new AdresseIP("192.168.1.2"));
        ir3.activer();
        switch1.ajouterInterface(ir3);

        Equipement pc1 = new Equipement("PC-Admin", "Poste");
        InterfaceReseau ir4 = new InterfaceReseau("eth0",
                new AdresseIP("192.168.1.10"));
        ir4.activer();
        pc1.ajouterInterface(ir4);

        infra1.ajouterEquipement(routeur);
        infra1.ajouterEquipement(switch1);
        infra1.ajouterEquipement(pc1);

        infra1.afficher();

        System.out.println("\n--- Recherche d'equipement ---");
        infra1.rechercherEquipement("Switch-Coeur");
        infra1.rechercherEquipement("Firewall");

        // ================================================
        // SCENARIO 2 — Universite
        // ================================================
        System.out.println("\n--- Scenario 2 : Universite ---");

        InfrastructureReseau infra2 = new InfrastructureReseau("Universite");

        ReseauIP reseauEtu  = new ReseauIP("10.0.1.0", 24, "Reseau etudiants");
        ReseauIP reseauProf = new ReseauIP("10.0.2.0", 25, "Reseau enseignants");
        ReseauIP reseauSrv  = new ReseauIP("10.0.3.0", 27, "Reseau serveurs");

        infra2.ajouterSousReseau(new SousReseau("ETUDIANTS",   reseauEtu));
        infra2.ajouterSousReseau(new SousReseau("ENSEIGNANTS", reseauProf));
        infra2.ajouterSousReseau(new SousReseau("SERVEURS",    reseauSrv));

        Equipement routeurU = new Equipement("Routeur-Campus", "Routeur");
        routeurU.ajouterInterface(new InterfaceReseau("eth0",
                new AdresseIP("10.0.1.1")));
        routeurU.ajouterInterface(new InterfaceReseau("eth1",
                new AdresseIP("10.0.2.1")));
        routeurU.ajouterInterface(new InterfaceReseau("eth2",
                new AdresseIP("10.0.3.1")));

        Equipement serveur = new Equipement("Serveur-Web", "Serveur");
        serveur.ajouterInterface(new InterfaceReseau("eth0",
                new AdresseIP("10.0.3.10")));

        infra2.ajouterEquipement(routeurU);
        infra2.ajouterEquipement(serveur);

        infra2.afficher();

        System.out.println("\nNombre total d'equipements : "
                + "2 (Routeur-Campus, Serveur-Web)");
    }
} 
