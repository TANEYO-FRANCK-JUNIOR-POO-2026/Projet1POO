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
 * Classe principale du TP2.
 * Teste les validations, les setters et les cas invalides.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.out.println("===== TP2 : Encapsulation et validation =====");
        System.out.println();

        
        //  TEST DES VALIDATIONS — cas invalides
        
        System.out.println("--- Tests de validation ---");

        AdresseIP ip1   = new AdresseIP("192.168.1.1"); // valide
        AdresseIP ip2   = new AdresseIP("");             // invalide → 0.0.0.0
        AdresseIP ip3   = new AdresseIP(null);           // invalide → 0.0.0.0
        AdresseIP ip4   = new AdresseIP("10.0.0.1");    // valide

        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Réseau principal"); // valide
        ReseauIP reseau2 = new ReseauIP("", 55, "");    // tout invalide → valeurs par défaut

        InterfaceReseau iface1 = new InterfaceReseau("eth0", ip1);  // valide
        InterfaceReseau iface2 = new InterfaceReseau("", ip2);       // nom invalide
        InterfaceReseau iface3 = new InterfaceReseau("wlan0", null); // sans IP

        Equipement routeur  = new Equipement("R1_EDGE", "Routeur", iface1);
        Equipement serveur  = new Equipement("", "", iface2);         // nom+type invalides
        Equipement switch1  = new Equipement("SW1_CORE", "Switch", iface3);
        Equipement ap1      = new Equipement("AP1_WIFI", "Point d'accès WiFi", null); // sans interface

        iface1.activer();

        
        //  TEST DES SETTERS — modification après création
        
        System.out.println();
        System.out.println("--- Test des setters ---");

        serveur.setNom("SRV_BACKUP");         // corrige le nom vide
        serveur.setType("Serveur");            // corrige le type vide
        ip2.setValeur("172.16.0.5");          // corrige l'adresse invalide
        reseau2.setMasqueCidr(30);            // corrige le masque invalide
        reseau2.setAdresseReseau("10.0.0.0"); // corrige l'adresse invalide

        
        //  AFFICHAGE DES RÉSEAUX
        
        System.out.println();
        System.out.println("----- Réseau 1 -----");
        reseau1.afficher();

        System.out.println();
        System.out.println("----- Réseau 2 (après correction par setter) -----");
        reseau2.afficher();

        
        //  AFFICHAGE DES ÉQUIPEMENTS

        System.out.println();
        System.out.println("----- Routeur -----");
        routeur.afficher();

        System.out.println();
        System.out.println("----- Serveur (après correction par setter) -----");
        serveur.afficher();

        System.out.println();
        System.out.println("----- Switch -----");
        switch1.afficher();

        System.out.println();
        System.out.println("----- Point d'accès WiFi -----");
        ap1.afficher();

        
        //  TEST DU TRAVAIL SUPPLÉMENTAIRE : estAdresseLocale()
        
        System.out.println();
        System.out.println("----- Test estAdresseLocale() -----");
        System.out.println(ip1.getValeur() + " → locale : " + ip1.estAdresseLocale()); // true
        System.out.println(ip4.getValeur() + " → locale : " + ip4.estAdresseLocale()); // false
        System.out.println(ip2.getValeur() + " → locale : " + ip2.estAdresseLocale()); // false
    }
}
