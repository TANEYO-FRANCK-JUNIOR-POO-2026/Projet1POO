package ipplanmanager;

public class CalculateurReseau {

    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) return 1;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        return ((masque >>> 24) & 255) + "."
             + ((masque >>> 16) & 255) + "."
             + ((masque >>>  8) & 255) + "."
             +  (masque         & 255);
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
