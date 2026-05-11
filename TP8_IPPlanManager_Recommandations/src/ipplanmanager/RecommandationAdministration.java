package ipplanmanager;

public class RecommandationAdministration implements RegleRecommandation {

    @Override
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ADMIN") || nom.contains("ADMINISTRATION")) {
            return new Recommandation(
                    "Acces VLAN Administration restreint",
                    "ELEVEE",
                    "Le VLAN " + vlan.getNom()
                    + " doit etre accessible aux administrateurs reseau uniquement."
            );
        }
        return null;
    }
}  
