package ipplanmanager.model;

public class VLAN {

    private int          id;
    private String       nom;
    private ResultatVLSM reseauAssocie;
    private String       description;

    public VLAN(int id, String nom, ResultatVLSM reseauAssocie,
                String description) {
        setId(id);
        setNom(nom);
        this.reseauAssocie = reseauAssocie;
        setDescription(description);
    }

    public int          getId()            { return id;            }
    public String       getNom()           { return nom;           }
    public ResultatVLSM getReseauAssocie() { return reseauAssocie; }
    public String       getDescription()   { return description;   }

    public void setId(int id) {
        this.id = (id < 1 || id > 4094) ? 1 : id;
    }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "VLAN_INCONNU" : nom;
    }

    public void setReseauAssocie(ResultatVLSM r) { this.reseauAssocie = r; }

    public void setDescription(String description) {
        this.description = (description == null || description.isEmpty())
                ? "Aucune description" : description;
    }

    public void afficher() {
        System.out.println("VLAN ID : "     + id);
        System.out.println("Nom : "         + nom);
        System.out.println("Description : " + description);
        if (reseauAssocie != null) {
            reseauAssocie.afficher();
        } else {
            System.out.println("Aucun reseau associe.");
        }
    }
}  
