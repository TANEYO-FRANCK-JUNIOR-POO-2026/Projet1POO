package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ReseauInsuffisantException;
import ipplanmanager.model.BesoinReseau;
import java.util.ArrayList;

public class CalculateurReseau {

    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) return 1;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) return cidr;
        }
        return -1;
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        return ((masque >>> 24) & 255) + "."
             + ((masque >>> 16) & 255) + "."
             + ((masque >>>  8) & 255) + "."
             +  (masque         & 255);
    }

    public static int convertirIpEnEntier(String ip) {
        String[] p = ip.split("\\.");
        int r = 0;
        for (int i = 0; i < 4; i++) {
            r = r * 256 + Integer.parseInt(p[i]);
        }
        return r;
    }

    public static String convertirEntierEnIp(int valeur) {
        return ((valeur >>> 24) & 255) + "."
             + ((valeur >>> 16) & 255) + "."
             + ((valeur >>>  8) & 255) + "."
             +  (valeur         & 255);
    }

    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        return convertirEntierEnIp(convertirIpEnEntier(adresseReseau) + 1);
    }

    public static String calculerDerniereAdresseUtilisable(String adresseReseau,
                                                            int cidr) {
        return convertirEntierEnIp(
                convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 2);
    }

    public static boolean estAdresseIPValide(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        for (int i = 0; i < parties.length; i++) {
            try {
                int valeur = Integer.parseInt(parties[i]);
                if (valeur < 0 || valeur > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static void verifierAdresseIP(String ip)
            throws AdresseIPInvalideException {
        if (!estAdresseIPValide(ip)) {
            throw new AdresseIPInvalideException(
                    "Adresse IP invalide : " + ip);
        }
    }

    public static int calculerAdresseFin(String adresseReseau, int cidr) {
        return convertirIpEnEntier(adresseReseau) + calculerTailleBloc(cidr) - 1;
    }

    public static boolean reseauxSeChevauchent(String adresse1, int cidr1,
                                                String adresse2, int cidr2) {
        int debut1 = convertirIpEnEntier(adresse1);
        int fin1   = calculerAdresseFin(adresse1, cidr1);
        int debut2 = convertirIpEnEntier(adresse2);
        int fin2   = calculerAdresseFin(adresse2, cidr2);
        return debut1 <= fin2 && debut2 <= fin1;
    }

    public static void verifierCapaciteReseau(String adresseDepart,
            int cidrReseau, ArrayList<BesoinReseau> besoins)
            throws ReseauInsuffisantException {
        int capaciteTotale = calculerNombreHotes(cidrReseau);
        int besoinsTotal   = 0;
        for (int i = 0; i < besoins.size(); i++) {
            besoinsTotal += calculerNombreHotes(
                    calculerCidrPourHotes(besoins.get(i).getNombreHotes()));
        }
        if (besoinsTotal > capaciteTotale) {
            throw new ReseauInsuffisantException(
                "Reseau insuffisant : capacite=" + capaciteTotale
                + " hotes, besoins cumules=" + besoinsTotal + " hotes.");
        }
    }

    public static String obtenirClasseReseau(String adresseIP) {
        try {
            int o1 = Integer.parseInt(adresseIP.split("\\.")[0]);
            if (o1 >= 1   && o1 <= 126) return "Classe A";
            if (o1 >= 128 && o1 <= 191) return "Classe B";
            if (o1 >= 192 && o1 <= 223) return "Classe C";
            return "Classe inconnue";
        } catch (Exception e) {
            return "Adresse invalide";
        }
    }
}  
