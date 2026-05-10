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
 * Classe principale du TP4.
 * Teste les calculs réseau automatiques sur plusieurs CIDR différents.
 */


import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {
       try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Encodage non supporté");
        }
        
        System.out.println("===== TP4 : Calculs réseau automatiques =====");
        System.out.println();

        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");

        
        //  RÉSEAUX — différents CIDR (étudiant : plusieurs cas de test)
      
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0",  24, "Réseau Administration");
        ReseauIP reseauTech  = new ReseauIP("172.16.0.0",   16, "Réseau Technique");
        ReseauIP reseauWifi  = new ReseauIP("10.0.0.0",      8, "Réseau WiFi / WAN");
        ReseauIP reseauDMZ   = new ReseauIP("192.168.1.128",25, "Zone DMZ");  // /25
        ReseauIP reseauMgmt  = new ReseauIP("192.168.1.192",26, "Réseau Management");  // /26
        ReseauIP reseauPublic= new ReseauIP("8.8.0.0",      16, "Réseau public Google DNS");

        SousReseau admin  = new SousReseau("ADMIN",  reseauAdmin);
        SousReseau tech   = new SousReseau("TECH",   reseauTech);
        SousReseau wifi   = new SousReseau("WIFI",   reseauWifi);
        SousReseau dmz    = new SousReseau("DMZ",    reseauDMZ);
        SousReseau mgmt   = new SousReseau("MGMT",   reseauMgmt);
        SousReseau publik  = new SousReseau("PUBLIC", reseauPublic);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        infrastructure.ajouterSousReseau(dmz);
        infrastructure.ajouterSousReseau(mgmt);
        infrastructure.ajouterSousReseau(publik);

        // Quelques équipements pour compléter
        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.1")));
        routeur.ajouterInterface(new InterfaceReseau("eth1", new AdresseIP("172.16.0.1")));

        infrastructure.ajouterEquipement(routeur);

    
        //  AFFICHAGE COMPLET (inclut calculs automatiques)
      
        infrastructure.afficher();

   
        //  TEST DIRECT DE CalculateurReseau
        
        System.out.println("===== TESTS DIRECTS DE CalculateurReseau =====");
        System.out.println();

        int[] cidrTests = {8, 16, 24, 25, 26, 27, 28, 30};
        for (int cidr : cidrTests) {
            System.out.println("CIDR /" + cidr
                    + " → " + CalculateurReseau.calculerNombreHotes(cidr)
                    + " hôtes | masque : "
                    + CalculateurReseau.obtenirMasqueDecimal(cidr));
        }

        System.out.println();
        System.out.println("===== TEST estReseauPrive() =====");
        String[] ips = {"10.1.1.1", "172.20.5.3", "192.168.100.50",
                        "8.8.8.8", "172.15.0.1", "172.32.0.1"};
        for (String ip : ips) {
            System.out.println(ip + " → privé : "
                    + CalculateurReseau.estReseauPrive(ip));
        }
    }
}
