package ipplanmanager;

import java.util.ArrayList;

public class Equipement {

    private String                     nom;
    private String                     type;
    private ArrayList<InterfaceReseau> interfaces;

    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        this.interfaces = new ArrayList<InterfaceReseau>();
    }

    public String getNom()  { return nom;  }
    public String getType() { return type; }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "equipement_inconnu" : nom;
    }

    public void setType(String type) {
        this.type = (type == null || type.isEmpty()) ? "type_inconnu" : type;
    }

    public void ajouterInterface(InterfaceReseau ir) {
        if (ir != null) interfaces.add(ir);
    }

    public void afficherInterfaces() {
        if (interfaces.isEmpty()) {
            System.out.println("Aucune interface configuree.");
            return;
        }
        for (int i = 0; i < interfaces.size(); i++) {
            System.out.println("-- Interface " + (i + 1) + " --");
            interfaces.get(i).afficher();
        }
    }

    public void afficher() {
        System.out.println("Nom  : " + nom);
        System.out.println("Type : " + type);
        System.out.println("Nombre d'interfaces : " + interfaces.size());
        afficherInterfaces();
    }
}  
