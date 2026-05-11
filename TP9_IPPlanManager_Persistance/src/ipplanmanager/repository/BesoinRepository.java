package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BesoinRepository {

    public ArrayList<BesoinReseau> chargerBesoins(String cheminFichier)
            throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<BesoinReseau>();
        BufferedReader reader = new BufferedReader(
                new FileReader(cheminFichier));

        String ligne = reader.readLine(); // ignore l'entete

        while ((ligne = reader.readLine()) != null) {
            String[] colonnes = ligne.split(";");
            if (colonnes.length == 2) {
                String nom   = colonnes[0].trim();
                int    hotes = Integer.parseInt(colonnes[1].trim());
                besoins.add(new BesoinReseau(nom, hotes));
            }
        }
        reader.close();
        return besoins;
    }

    // Travail supplementaire — section 17
    public void sauvegarderBesoins(ArrayList<BesoinReseau> besoins,
                                    String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;Hotes\n");
        for (int i = 0; i < besoins.size(); i++) {
            BesoinReseau b = besoins.get(i);
            writer.write(b.getNom() + ";" + b.getNombreHotes() + "\n");
        }
        writer.close();
        System.out.println("Besoins sauvegardes : " + cheminFichier);
    }
}  
