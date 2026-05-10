/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */
/**
 * Classe principale du TP1.
 * Crée et affiche des adresses IP, interfaces, réseaux et équipements.
 * Inclut le travail supplémentaire demandé à l'étudiant.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Découverte des premières classes du projet");
        System.out.println();
        
             //  ADRESSES IP
        
        AdresseIP ipRouteur  = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur  = new AdresseIP("192.168.1.10");
        AdresseIP ipClient   = new AdresseIP("192.168.1.50");

        //  Adresses ajoutées par l'étudiant 
        AdresseIP ipSwitch   = new AdresseIP("192.168.1.2");
        AdresseIP ipAP       = new AdresseIP("192.168.1.3");
        AdresseIP ipClient2  = new AdresseIP("192.168.1.51");


        //  INTERFACES RÉSEAU
        
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur);
        InterfaceReseau interfaceClient  = new InterfaceReseau("wlan0", ipClient);

        //  Interfaces ajoutées par l'étudiant 
        
        InterfaceReseau interfaceSwitch  = new InterfaceReseau("fa0/1", ipSwitch);
        InterfaceReseau interfaceAP      = new InterfaceReseau("wlan0", ipAP);
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth0",  ipClient2);

        // Interface sans adresse IP (cas demandé)
        
        InterfaceReseau interfaceSansIP  = new InterfaceReseau("eth1", null);

        // Activation sélective
        
        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceSwitch.activer();
        interfaceAP.activer();
        
        // interfaceClient   → reste inactive (cas demandé)
        // interfaceClient2  → reste inactive
        // interfaceSansIP   → reste inactive

        // ============================================================
        //  ÉQUIPEMENTS
        Equipement routeur  = new Equipement("R1_EDGE",   "Routeur",           interfaceRouteur);
        Equipement serveur  = new Equipement("SRV_DNS",   "Serveur",           interfaceServeur);
        Equipement client   = new Equipement("PC_ADMIN",  "Poste client",      interfaceClient);

        // --- Équipements ajoutés par l'étudiant ---
        Equipement switch1  = new Equipement("SW1_CORE",  "Switch",            interfaceSwitch);
        Equipement ap1      = new Equipement("AP1_WIFI",  "Point d'accès WiFi",interfaceAP);
        Equipement client2  = new Equipement("PC_USER1",  "Poste client",      interfaceClient2);
        // Équipement avec interface sans IP
        Equipement serveur2 = new Equipement("SRV_DHCP",  "Serveur",           interfaceSansIP);

        //  RÉSEAUX

        ReseauIP reseauPrincipal = new ReseauIP(
                "192.168.1.0", 24, "Réseau principal du laboratoire IRT"
        );

        // Deuxième réseau (ajouté par l'étudiant)
        ReseauIP reseauWAN = new ReseauIP(
                "10.0.0.0", 8, "Réseau WAN de l'entreprise"
        );

        //  AFFICHAGE
        
        System.out.println("----- Réseaux créés -----");
        reseauPrincipal.afficher();
        System.out.println();
        reseauWAN.afficher();

        System.out.println();
        System.out.println("----- Équipements créés -----");

        System.out.println(); routeur.afficher();
        System.out.println(); serveur.afficher();
        System.out.println(); client.afficher();
        System.out.println(); switch1.afficher();
        System.out.println(); ap1.afficher();
        System.out.println(); client2.afficher();
        System.out.println(); serveur2.afficher();
    }
}
