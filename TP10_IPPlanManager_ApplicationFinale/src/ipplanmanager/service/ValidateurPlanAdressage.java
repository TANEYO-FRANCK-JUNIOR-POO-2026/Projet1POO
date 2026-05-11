package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;

public class ValidateurPlanAdressage {

    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats)
            throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                if (CalculateurReseau.reseauxSeChevauchent(
                        r1.getAdresseReseau(), r1.getCidr(),
                        r2.getAdresseReseau(), r2.getCidr())) {
                    throw new ChevauchementReseauException(
                        "Chevauchement detecte entre "
                        + r1.getNomBesoin()
                        + " et " + r2.getNomBesoin());
                }
            }
        }
    }

    public void verifierAdresses(ArrayList<ResultatVLSM> resultats)
            throws AdresseIPInvalideException {
        for (int i = 0; i < resultats.size(); i++) {
            CalculateurReseau.verifierAdresseIP(
                    resultats.get(i).getAdresseReseau());
        }
    }

    public void afficherValidationReussie() {
        System.out.println("Validation terminee : aucun conflit detecte.");
    }
}  
