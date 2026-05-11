# TP9 - Persistance et organisation professionnelle

## Objectif
Ajouter la lecture et l'ecriture de fichiers afin de sauvegarder
les besoins, les plans, les VLANs, les recommandations et
les rapports. Reorganiser le projet avec des packages
professionnels.

## Notions etudiees
- Persistance des donnees
- Fichiers CSV (lecture et ecriture)
- FileWriter, BufferedReader, FileReader
- Package repository
- Package service
- Architecture professionnelle multi-packages

## Organisation des packages

| Package | Role |
|---|---|
| ipplanmanager.model | Classes metier |
| ipplanmanager.service | Traitements metier |
| ipplanmanager.repository | Lecture et ecriture fichiers |
| ipplanmanager.exception | Exceptions personnalisees |
| ipplanmanager.main | Point d'entree |

## Classes creees

| Classe | Package | Role |
|---|---|---|
| FichierPlanRepository | repository | Sauvegarde CSV des resultats |
| BesoinRepository | repository | Lecture et sauvegarde des besoins |
| RapportService | service | Generation du rapport complet |

## Fichiers utilises

### Fichiers d'entree
| Fichier | Contenu |
|---|---|
| exports/besoins.csv | Besoins Universite |
| exports/besoins_pme.csv | Besoins PME |

### Fichiers generes
| Fichier | Contenu |
|---|---|
| exports/plan_adressage.csv | Plan VLSM Universite |
| exports/vlans.csv | VLANs Universite |
| exports/recommandations.txt | Recommandations Universite |
| exports/rapport_complet.txt | Rapport complet Universite |
| exports/rapport_pme.txt | Rapport complet PME |
| exports/besoins_pme_sauvegarde.csv | Besoins PME sauvegardes |

## Scenarios testes

### Scenario 1 - Universite (depuis besoins.csv)
Adresse : 10.10.0.0
Besoins charges depuis fichier CSV :
ETUDIANTS(500), WIFI_INVITES(200), ENSEIGNANTS(120),
LABORATOIRES(60), SERVEURS(30)

### Scenario 2 - PME (depuis besoins_pme.csv)
Adresse : 192.168.1.0
Besoins charges depuis fichier CSV :
ADMINISTRATION(50), COMPTABILITE(20), WIFI_INVITES(80),
SERVEURS(15), VOIP(40)

## Contenu plan_adressage.csv

Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge
ETUDIANTS;10.10.0.0;23;255.255.254.0;500;510;10
WIFI_INVITES;10.10.2.0;24;255.255.255.0;200;254;54
ENSEIGNANTS;10.10.3.0;25;255.255.255.128;120;126;6
LABORATOIRES;10.10.3.128;26;255.255.255.192;60;62;2
SERVEURS;10.10.3.192;27;255.255.255.224;30;30;0

## Difficultes rencontrees
- Corriger les imports apres reorganisation en packages.
- Gerer les chemins relatifs des fichiers CSV.
- S'assurer que le dossier exports existe avant l'execution.
- Comprendre la difference entre FileWriter et BufferedReader.

## Reponses aux questions

1. La persistance signifie que les donnees survivent a l'arret
   du programme. Elles sont sauvegardees dans un support durable
   comme un fichier ou une base de donnees.

2. Une application professionnelle doit conserver ses resultats
   pour les partager, les auditer et les reutiliser dans
   des rapports techniques.

3. Un fichier CSV est structure en colonnes separees exploitable
   par Excel. Un rapport texte est lisible directement par
   un humain sans outil special.

4. Le package repository regroupe les classes qui lisent et
   ecrivent des donnees isolant l'acces aux donnees du reste
   de l'application.

5. Le package service regroupe les traitements metier evitant
   de melanger les calculs avec la gestion des fichiers.

6. Mettre tout dans Main rendrait le code illisible et impossible
   a maintenir. Chaque classe doit avoir une responsabilite unique.

7. Le fichier besoins.csv permet de changer les exigences sans
   modifier le code Java rendant l'application flexible et
   reutilisable pour differents projets reseau.

8. Les packages separent clairement les responsabilites. Un
   developpeur peut modifier le repository sans toucher
   au service facilitant la maintenance et les tests.
