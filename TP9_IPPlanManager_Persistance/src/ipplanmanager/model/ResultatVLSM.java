package ipplanmanager.model;

import ipplanmanager.service.CalculateurReseau;

public class ResultatVLSM {

    private String nomBesoin;
    private String adresseReseau;
    private int    cidr;
    private String masqueDecimal;
    private int    capacite;
    private int    nombreHotesDemandes;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr,
                        String masqueDecimal, int capacite) {
        this.nomBesoin           = nomBesoin;
        this.adresseReseau       = adresseReseau;
        this.cidr                = cidr;
        this.masqueDecimal       = masqueDecimal;
        this.capacite            = capacite;
        this.nombreHotesDemandes = 0;
        this.premiereAdresseUtilisable =
            CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
        this.derniereAdresseUtilisable =
            CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
    }

    public String getNomBesoin()                 { return nomBesoin;                 }
    public String getAdresseReseau()             { return adresseReseau;             }
    public int    getCidr()                      { return cidr;                      }
    public String getMasqueDecimal()             { return masqueDecimal;             }
    public int    getCapacite()                  { return capacite;                  }
    public int    getNombreHotesDemandes()        { return nombreHotesDemandes;       }
    public String getPremiereAdresseUtilisable() { return premiereAdresseUtilisable; }
    public String getDerniereAdresseUtilisable() { return derniereAdresseUtilisable; }

    public void setNombreHotesDemandes(int n) { this.nombreHotesDemandes = n; }

    public int getMarge() { return capacite - nombreHotesDemandes; }

    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr
            + " | Masque : "   + masqueDecimal
            + " | Plage : "    + premiereAdresseUtilisable
            + " - "            + derniereAdresseUtilisable
            + " | Demandes : " + nombreHotesDemandes
            + " | Capacite : " + capacite
            + " | Marge : "    + getMarge());
    }
}  
