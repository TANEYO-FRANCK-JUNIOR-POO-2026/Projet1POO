/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Taneyo
 */

public class CalculateurReseau {

    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) return 1;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) {
                return cidr;
            }
        }
        return -1;
    }

    // Nécessaire pour ReseauIP.afficher()
    public static String obtenirClasseReseau(String adresseIP) {
        try {
            String[] parties      = adresseIP.split("\\.");
            int      premierOctet = Integer.parseInt(parties[0]);
            if (premierOctet >= 1   && premierOctet <= 126) return "Classe A";
            if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
            if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
            return "Classe inconnue";
        } catch (Exception e) {
            return "Adresse invalide";
        }
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int o1 = (masque >>> 24) & 255;
        int o2 = (masque >>> 16) & 255;
        int o3 = (masque >>>  8) & 255;
        int o4 =  masque         & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }

    public static int convertirIpEnEntier(String ip) {
        String[] p = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(p[i]);
        }
        return resultat;
    }

    public static String convertirEntierEnIp(int valeur) {
        int o1 = (valeur >>> 24) & 255;
        int o2 = (valeur >>> 16) & 255;
        int o3 = (valeur >>>  8) & 255;
        int o4 =  valeur         & 255;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }

    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + 1);
    }

    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        return convertirEntierEnIp(
                convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 2);
    }

    public static boolean estReseauPrive(String adresseIP) {
        try {
            String[] p  = adresseIP.split("\\.");
            int      o1 = Integer.parseInt(p[0]);
            int      o2 = Integer.parseInt(p[1]);
            if (o1 == 10)                           return true;
            if (o1 == 172 && o2 >= 16 && o2 <= 31) return true;
            if (o1 == 192 && o2 == 168)             return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
