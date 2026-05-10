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
 * Classe utilitaire contenant les méthodes statiques de calcul réseau.
 * Ne représente pas un objet métier mais des traitements techniques.
 */
public class CalculateurReseau {

    /**
     * Calcule le nombre maximal d'hôtes pour un CIDR donné.
     * Formule : 2^(32 - CIDR) - 2
     */
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    /**
     * Détermine la classe réseau (A, B, C) selon le premier octet.
     */
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties   = adresseIP.split("\\.");
        int premierOctet   = Integer.parseInt(parties[0]);

        if (premierOctet >= 1   && premierOctet <= 126) return "Classe A";
        if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
        if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
        return "Classe inconnue";
    }

    /**
     * Convertit un CIDR en masque décimal pointé.
     */
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 8:  return "255.0.0.0";
            case 16: return "255.255.0.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            case 30: return "255.255.255.252";
            default: return "Masque non disponible";
        }
    }

    /**
     * Travail supplémentaire :
     * Retourne true si l'adresse IP appartient à une plage privée RFC 1918.
     * Plages vérifiées : 10.x.x.x | 172.16.x.x – 172.31.x.x | 192.168.x.x
     */
    public static boolean estReseauPrive(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int oct1 = Integer.parseInt(parties[0]);
        int oct2 = Integer.parseInt(parties[1]);

        if (oct1 == 10) return true;                                   // 10.0.0.0/8
        if (oct1 == 172 && oct2 >= 16 && oct2 <= 31) return true;     // 172.16.0.0 – 172.31.255.255
        if (oct1 == 192 && oct2 == 168) return true;                   // 192.168.0.0/16
        return false;
    }
}
