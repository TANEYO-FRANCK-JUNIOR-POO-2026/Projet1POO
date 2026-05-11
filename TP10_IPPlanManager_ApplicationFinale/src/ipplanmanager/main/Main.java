package ipplanmanager.main;

import ipplanmanager.service.ApplicationIPPlanManager;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Encodage non supporte");
        }
        ApplicationIPPlanManager application =
                new ApplicationIPPlanManager();
        application.demarrer();
    }
} 
