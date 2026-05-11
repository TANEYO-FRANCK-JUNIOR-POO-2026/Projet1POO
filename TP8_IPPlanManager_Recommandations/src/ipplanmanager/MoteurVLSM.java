package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart,
                                               ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<ResultatVLSM>();

        ArrayList<BesoinReseau> besoinsTries =
                new ArrayList<BesoinReseau>(besoins);

        Collections.sort(besoinsTries, new Comparator<BesoinReseau>() {
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        int adresseCourante =
                CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (int i = 0; i < besoinsTries.size(); i++) {
            BesoinReseau besoin  = besoinsTries.get(i);
            int    cidr          = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int    capacite      = CalculateurReseau.calculerNombreHotes(cidr);
            String masque        = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);

            ResultatVLSM resultat = new ResultatVLSM(
                    besoin.getNom(), adresseReseau, cidr, masque, capacite);
            // Stockage du besoin initial pour RecommandationMargeAdresse
            resultat.setNombreHotesDemandes(besoin.getNombreHotes());
            resultats.add(resultat);

            adresseCourante += CalculateurReseau.calculerTailleBloc(cidr);
        }
        return resultats;
    }
}
