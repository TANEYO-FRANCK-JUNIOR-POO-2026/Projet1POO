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
package ipplanmanager;

/**
 * Classe principale du TP3.
 * Teste les collections, la composition et la recherche d'équipements.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        
        //  INFRASTRUCTURE
        
        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");

        
        //  SOUS-RÉSEAUX
        
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau Administration");
        ReseauIP reseauTech  = new ReseauIP("192.168.2.0", 24, "Réseau Technique");
        ReseauIP reseauWifi  = new ReseauIP("192.168.3.0", 24, "Réseau WiFi");  // 3e sous-réseau (étudiant)

        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech  = new SousReseau("TECH",  reseauTech);
        SousReseau wifi  = new SousReseau("WIFI",  reseauWifi);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);

        
        //  ROUTEUR — plusieurs interfaces
        
        AdresseIP ipR_eth0 = new AdresseIP("192.168.1.1");
        AdresseIP ipR_eth1 = new AdresseIP("192.168.2.1");
        AdresseIP ipR_eth2 = new AdresseIP("10.0.0.254");

        InterfaceReseau rEth0 = new InterfaceReseau("eth0", ipR_eth0);
        InterfaceReseau rEth1 = new InterfaceReseau("eth1", ipR_eth1);
        InterfaceReseau rEth2 = new InterfaceReseau("eth2", ipR_eth2);

        rEth0.activer();
        rEth1.activer();
        rEth2.activer();

        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(rEth0);
        routeur.ajouterInterface(rEth1);
        routeur.ajouterInterface(rEth2);

        
        //  SWITCH — plusieurs interfaces (étudiant)
        
        Equipement switch1 = new Equipement("SW1_CORE", "Switch");
        InterfaceReseau swFa01 = new InterfaceReseau("fa0/1", new AdresseIP("192.168.1.2"));
        InterfaceReseau swFa02 = new InterfaceReseau("fa0/2", new AdresseIP("192.168.2.2"));
        swFa01.activer();
        swFa02.activer();
        switch1.ajouterInterface(swFa01);
        switch1.ajouterInterface(swFa02);

        
        //  SERVEUR — plusieurs interfaces (étudiant)
        
        Equipement serveur = new Equipement("SRV_DNS", "Serveur");
        InterfaceReseau srvEth0 = new InterfaceReseau("eth0", new AdresseIP("192.168.1.10"));
        InterfaceReseau srvEth1 = new InterfaceReseau("eth1", null); // sans IP
        srvEth0.activer();
        serveur.ajouterInterface(srvEth0);
        serveur.ajouterInterface(srvEth1);

        
        //  POINT D'ACCÈS WIFI (étudiant)
        
        Equipement ap1 = new Equipement("AP1_WIFI", "Point d'accès WiFi");
        InterfaceReseau apWlan0 = new InterfaceReseau("wlan0", new AdresseIP("192.168.3.1"));
        apWlan0.activer();
        ap1.ajouterInterface(apWlan0);

        
        //  POSTES CLIENTS (étudiant — 5 équipements supplémentaires)
        
        Equipement pc1 = new Equipement("PC_ADMIN",  "Poste client");
        pc1.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.50")));

        Equipement pc2 = new Equipement("PC_USER1",  "Poste client");
        pc2.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.51")));

        Equipement pc3 = new Equipement("PC_USER2",  "Poste client");
        pc3.ajouterInterface(new InterfaceReseau("wlan0", new AdresseIP("192.168.3.20")));

        Equipement laptop1 = new Equipement("LAPTOP_TECH", "Poste client");
        laptop1.ajouterInterface(new InterfaceReseau("wlan0", new AdresseIP("192.168.2.50")));

        Equipement serveur2 = new Equipement("SRV_DHCP", "Serveur");
        serveur2.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.11")));

        
        //  AJOUT DES ÉQUIPEMENTS À L'INFRASTRUCTURE
        
        infrastructure.ajouterEquipement(routeur);
        infrastructure.ajouterEquipement(switch1);
        infrastructure.ajouterEquipement(serveur);
        infrastructure.ajouterEquipement(ap1);
        infrastructure.ajouterEquipement(pc1);
        infrastructure.ajouterEquipement(pc2);
        infrastructure.ajouterEquipement(pc3);
        infrastructure.ajouterEquipement(laptop1);
        infrastructure.ajouterEquipement(serveur2);

        
        //  AFFICHAGE GÉNÉRAL
        
        infrastructure.afficher();

        
        //  TEST DE rechercherEquipement()
        
        System.out.println("===== RECHERCHE D'ÉQUIPEMENTS =====");
        infrastructure.rechercherEquipement("R1_EDGE");       // trouvé
        System.out.println();
        infrastructure.rechercherEquipement("FIREWALL_EXT");  // introuvable
    }
}
