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
 * Représente un équipement réseau encapsulé.
 * Le nom et le type sont protégés contre les valeurs invalides.
 */
public class Equipement {

    private String          nom;
    private String          type;
    private InterfaceReseau interfacePrincipale;

    public Equipement(String nom, String type, InterfaceReseau interfacePrincipale) {
        setNom(nom);
        setType(type);
        this.interfacePrincipale = interfacePrincipale;
    }

    // --- Getters ---
    public String          getNom()                { return nom;                }
    public String          getType()               { return type;               }
    public InterfaceReseau getInterfacePrincipale(){ return interfacePrincipale;}

    // --- Setters avec validation ---
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            System.out.println("Erreur : nom d'équipement invalide. Nom par défaut : equipement_inconnu");
            this.nom = "equipement_inconnu";
        } else {
            this.nom = nom;
        }
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            System.out.println("Erreur : type d'équipement invalide. Type par défaut : Type inconnu");
            this.type = "Type inconnu";
        } else {
            this.type = type;
        }
    }

    public void setInterfacePrincipale(InterfaceReseau interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    public void afficher() {
        System.out.println("Nom  : " + nom);
        System.out.println("Type : " + type);
        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
        } else {
            System.out.println("Aucune interface configurée.");
        }
    }
}
