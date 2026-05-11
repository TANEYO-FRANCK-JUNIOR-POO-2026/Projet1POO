/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
         System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        MoteurVLSM moteur = new MoteurVLSM();

      
        // SCÉNARIO 1 — Fourni par le TP
     
        System.out.println("===== TP5 - Moteur VLSM =====");
        System.out.println("--- Scénario 1 : Infrastructure IRT-LAB ---");

        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE",      120));
        besoins1.add(new BesoinReseau("WIFI",            80));
        besoins1.add(new BesoinReseau("ADMINISTRATION",  50));
        besoins1.add(new BesoinReseau("SERVEURS",        20));
        besoins1.add(new BesoinReseau("DIRECTION",       10));

        System.out.println("\nBesoins exprimés :");
        for (BesoinReseau b : besoins1) b.afficher();

        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);
        System.out.println("\nPlan d'adressage proposé :");
        for (ResultatVLSM r : resultats1) r.afficher();

      
        // SCÉNARIO 2 — Petite entreprise (section 15)
      
        System.out.println("\n--- Scénario 2 : Petite entreprise ---");

        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMIN",         25));
        besoins2.add(new BesoinReseau("COMPTABILITE",  12));
        besoins2.add(new BesoinReseau("WIFI_INVITES",  40));
        besoins2.add(new BesoinReseau("SERVEURS",       8));

        System.out.println("\nBesoins exprimés :");
        for (BesoinReseau b : besoins2) b.afficher();

        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("10.0.0.0", besoins2);
        System.out.println("\nPlan d'adressage proposé :");
        for (ResultatVLSM r : resultats2) r.afficher();

      
        // SCÉNARIO 3 — Campus (section 15)
       
        System.out.println("\n--- Scénario 3 : Campus universitaire ---");

        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("ETUDIANTS",      500));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC",    200));
        besoins3.add(new BesoinReseau("PERSONNEL",      120));
        besoins3.add(new BesoinReseau("LABORATOIRE",     60));
        besoins3.add(new BesoinReseau("ADMINISTRATION",  40));

        System.out.println("\nBesoins exprimés :");
        for (BesoinReseau b : besoins3) b.afficher();

        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("172.16.0.0", besoins3);
        System.out.println("\nPlan d'adressage proposé :");
        for (ResultatVLSM r : resultats3) r.afficher();
    }
}
