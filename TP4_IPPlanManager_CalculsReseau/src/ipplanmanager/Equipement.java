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

public class Equipement {

    private String                     nom;
    private String                     type;
    private ArrayList<InterfaceReseau> interfaces;

    public Equipement(String nom, String type) {
        setNom(nom);
        setType(type);
        interfaces = new ArrayList<>();
    }

    public String getNom()  { return nom;  }
    public String getType() { return type; }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.isEmpty()) ? "equipement_inconnu" : nom;
    }

    public void setType(String type) {
        this.type = (type == null || type.isEmpty()) ? "type_inconnu" : type;
    }

    public void ajouterInterface(InterfaceReseau iface) {
        interfaces.add(iface);
    }

    public void afficherInterfaces() {
        for (InterfaceReseau iface : interfaces) {
            iface.afficher();
            System.out.println();
        }
    }

    public void afficher() {
        System.out.println("Nom                 : " + nom);
        System.out.println("Type                : " + type);
        System.out.println("Nombre d'interfaces : " + interfaces.size());
        afficherInterfaces();
    }
}
