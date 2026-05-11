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
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart,
                                               ArrayList<BesoinReseau> besoins) {

        ArrayList<ResultatVLSM> resultats = new ArrayList<ResultatVLSM>();

        // Tri décroissant — copie locale pour ne pas modifier la liste originale
        ArrayList<BesoinReseau> besoinsTries = new ArrayList<BesoinReseau>(besoins);

        Collections.sort(besoinsTries, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (BesoinReseau besoin : besoinsTries) {
            int    cidr          = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int    capacite      = CalculateurReseau.calculerNombreHotes(cidr);
            String masque        = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);

            resultats.add(new ResultatVLSM(
                besoin.getNom(), adresseReseau, cidr, masque, capacite
            ));

            adresseCourante += CalculateurReseau.calculerTailleBloc(cidr);
        }

        return resultats;
    }
}