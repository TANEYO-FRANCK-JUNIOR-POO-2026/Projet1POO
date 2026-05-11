# TP10 - Application finale IPPlan-Manager

## Objectif
Assembler toutes les fonctionnalites developpees dans les TPs
precedents pour produire une application console complete
et professionnelle de planification d'adressage IP.

## Fonctionnalites realisees
- Menu console interactif
- Saisie utilisateur des besoins reseau
- Chargement des besoins depuis un fichier CSV
- Generation automatique du plan VLSM
- Creation automatique des VLANs
- Validation du plan (adresses IP, chevauchements)
- Recommandations techniques intelligentes
- Sauvegarde CSV des resultats
- Generation de rapport technique complet

## Organisation du projet

| Package | Role |
|---|---|
| ipplanmanager.model | Classes metier : BesoinReseau, ResultatVLSM, VLAN, Recommandation |
| ipplanmanager.service | Traitements : MoteurVLSM, GestionnaireVLAN, MoteurRecommandation, RapportService, ApplicationIPPlanManager |
| ipplanmanager.repository | Lecture/ecriture fichiers : BesoinRepository, FichierPlanRepository |
| ipplanmanager.exception | Exceptions : AdresseIPInvalideException, ConflitVLANException, ChevauchementReseauException |
| ipplanmanager.console | Interaction utilisateur : ConsoleService |
| ipplanmanager.main | Point d'entree : Main |

## Scenarios testes

### Scenario 1 - Campus IRT (saisie manuelle)
Adresse : 10.10.0.0
Besoins : ETUDIANTS(500), WIFI_INVITES(200), ENSEIGNANTS(120),
LABORATOIRES(60), SERVEURS(30)
Fichiers generes : Campus_IRT_plan.csv, Campus_IRT_vlans.csv,
Campus_IRT_recommandations.txt, Campus_IRT_rapport.txt

### Scenario 2 - PME (saisie manuelle)
Adresse : 192.168.1.0
Besoins : ADMINISTRATION(50), COMPTABILITE(20), WIFI_INVITES(80),
SERVEURS(15), VOIP(40)
Fichiers generes : PME_plan.csv, PME_vlans.csv,
PME_recommandations.txt, PME_rapport.txt

### Scenario 3 - Entreprise multi-services (saisie manuelle)
Adresse : 172.16.0.0
Besoins : TECHNIQUE(120), DIRECTION(25), CAMERAS(60),
SUPPORT(35), INVITES(100)
Fichiers generes : Entreprise_plan.csv, Entreprise_vlans.csv,
Entreprise_recommandations.txt, Entreprise_rapport.txt

### Scenario 4 - Universite (chargement CSV)
Fichier charge : exports/besoins.csv
Adresse : 10.10.0.0
Besoins charges automatiquement depuis le fichier CSV
Fichiers generes : Universite_plan.csv, Universite_vlans.csv,
Universite_recommandations.txt, Universite_rapport.txt

## Fichiers generes dans exports/
exports/
├── besoins.csv
├── besoins_pme.csv
├── Campus_IRT_plan.csv
├── Campus_IRT_vlans.csv
├── Campus_IRT_recommandations.txt
├── Campus_IRT_rapport.txt
├── PME_plan.csv
├── PME_vlans.csv
├── PME_recommandations.txt
├── PME_rapport.txt
├── Entreprise_plan.csv
├── Entreprise_vlans.csv
├── Entreprise_recommandations.txt
├── Entreprise_rapport.txt
├── Universite_plan.csv
├── Universite_vlans.csv
├── Universite_recommandations.txt
└── Universite_rapport.txt
## Difficultes rencontrees
- Organiser correctement les imports entre les packages.
- Reinitialiser GestionnaireVLAN entre chaque execution
  pour eviter l'accumulation des VLANs.
- Implementer le menu avec boucle while et switch.
- Gerer les deux modes de saisie dans
  ApplicationIPPlanManager de maniere propre.
- Corriger les chemins relatifs des fichiers CSV
  dans le dossier exports.

## Reponses aux questions

1. Le TP10 assemble toutes les fonctionnalites en une application
   coherente avec menu, saisie, calcul, validation, recommandations,
   sauvegarde et rapport. Les TPs precedents testaient des parties
   isolees. Ici tout fonctionne ensemble.

2. ApplicationIPPlanManager est l'orchestrateur principal.
   Elle coordonne toutes les classes sans que Main ait a connaitre
   les details de chaque traitement. C'est le coeur de l'application.

3. Main doit rester courte car son seul role est de lancer
   l'application. La logique dans Main est difficile a maintenir,
   a tester et a reutiliser dans d'autres contextes.

4. La separation en packages permet a chaque developpeur de
   travailler sur sa couche sans impacter les autres. Elle reflete
   une architecture logicielle professionnelle ou chaque couche
   a une responsabilite unique et bien definie.

5. ConsoleService isole toute la logique de saisie utilisateur.
   Si on remplace la console par une interface graphique,
   on ne touche qu'a ConsoleService sans modifier le reste.

6. Une adresse invalide provoquerait des calculs incorrects
   et des exceptions non controlees. La validation en amont
   garantit la fiabilite et la coherence du plan genere.

7. Les recommandations portent sur les VLANs generes.
   Il faut donc que les VLANs soient crees avant de pouvoir
   les analyser avec le moteur de recommandations.

8. Sans sauvegarde, le plan disparait a chaque execution.
   Les fichiers CSV et les rapports permettent de partager,
   auditer et reutiliser les resultats dans un contexte
   professionnel reel.

9. Le rapport technique est le livrable final destine a
   l'administrateur reseau ou au client. Il resume tout
   le travail de planification de maniere lisible et
   professionnelle, sans necessite de lire le code Java.

10. Ameliorations possibles pour une version future :
    - Interface graphique JavaFX
    - Base de donnees pour stocker les plans
    - Import/export JSON ou XML
    - Historique et comparaison des plans
    - Support IPv6
    - Detection automatique des conflits en temps reel
    - Authentification administrateur
    - Generation de rapports PDF

## Conclusion personnelle
Ce projet IPPlan-Manager a permis de comprendre comment
la Programmation Orientee Objet structure une application
complete et professionnelle. Chaque classe a un role precis.
Les objets collaborent sans se connaitre en detail grace
aux interfaces et au polymorphisme. L'architecture en packages
separe clairement les responsabilites. Le projet montre
qu'un outil reseau professionnel peut etre construit
progressivement avec les principes fondamentaux de la POO :
encapsulation, collections, heritage, interfaces,
polymorphisme, exceptions et persistance des donnees.
