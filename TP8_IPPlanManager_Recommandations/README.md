# TP8 - Moteur de recommandations intelligentes

## Objectif
Ajouter un moteur de recommandations capable d'analyser
un plan VLAN et de proposer automatiquement des conseils
techniques pertinents.

## Notions etudiees
- Interfaces Java (RegleRecommandation)
- Polymorphisme
- Regles metier specialisees
- Moteur de recommandations (MoteurRecommandation)
- Separation des responsabilites
- Extensibilite logicielle

## Classes creees

| Classe | Role |
|---|---|
| Recommandation | Represente un conseil technique |
| RegleRecommandation | Interface definissant le contrat des regles |
| RecommandationWifiInvite | Detection des VLANs WiFi |
| RecommandationServeurs | Detection des VLANs serveurs |
| RecommandationGrandVLAN | Detection des VLANs > 200 hotes |
| RecommandationAdministration | Detection des VLANs administration |
| RecommandationMargeAdresse | Detection des marges insuffisantes |
| MoteurRecommandation | Applique toutes les regles sur tous les VLANs |

## Scenarios testes

### Scenario 1 - Universite
Adresse : 10.10.0.0
Besoins : ETUDIANTS(500), WIFI_INVITES(200), ENSEIGNANTS(120),
LABORATOIRES(60), SERVEURS(30)

Recommandations obtenues :
- [MOYENNE] VLAN de grande taille : ETUDIANTS (510 hotes)
- [ELEVEE] Isolation du WiFi : WIFI_INVITES
- [MOYENNE] VLAN de grande taille : WIFI_INVITES (254 hotes)
- [MOYENNE] Marge insuffisante : ENSEIGNANTS (6 adresses)
- [MOYENNE] Marge insuffisante : LABORATOIRES (2 adresses)
- [ELEVEE] Protection ACL : SERVEURS
- [MOYENNE] Marge insuffisante : SERVEURS (0 adresse)

### Scenario 2 - Entreprise
Adresse : 172.16.0.0
Besoins : ADMINISTRATION(50), WIFI_INVITES(120), SERVEURS(20),
CAMERAS(80), VOIP(60)

Recommandations obtenues :
- [ELEVEE] Isolation du WiFi : WIFI_INVITES
- [MOYENNE] Marge insuffisante : WIFI_INVITES (6 adresses)
- [MOYENNE] Marge insuffisante : VOIP (2 adresses)
- [ELEVEE] Acces restreint : ADMINISTRATION
- [ELEVEE] Protection ACL : SERVEURS

## Resultats obtenus
- Toutes les regles detectent correctement les conditions
- Le moteur applique toutes les regles sur tous les VLANs
- Les recommandations sont claires et pertinentes
- Le programme continue meme si aucune recommandation

## Difficultes rencontrees
- Comprendre la notion d'interface Java et son implementation.
- Comprendre le polymorphisme avec RegleRecommandation.
- Enrichir ResultatVLSM pour stocker le nombre d'hotes
  demandes sans casser les TPs precedents.

## Reponses aux questions

1. Le moteur transforme l'application en assistant technique.
   Il analyse le plan et propose des conseils pour ameliorer
   la securite et l'organisation du reseau.

2. L'interface impose un contrat commun a toutes les regles.
   On peut ajouter de nouvelles regles sans modifier
   le moteur principal.

3. Une interface definit uniquement des methodes sans les
   implementer. Une classe concrete fournit le code complet.
   Une classe peut implementer plusieurs interfaces.

4. analyser() retourne null quand aucune condition n'est
   remplie pour ce VLAN evitant des recommandations inutiles.

5. Le moteur manipule des objets RegleRecommandation sans
   connaitre leur implementation interne. Chaque objet
   execute sa propre logique via la meme methode analyser().

6. Une classe par regle rend le code lisible testable et
   maintenable. Tout dans Main produirait un code illisible
   et impossible a etendre.

7. Un VLAN WiFi invite est accessible a des personnes
   externes. Sans isolation elles pourraient acceder aux
   serveurs internes et donnees sensibles.

8. Un VLAN tres grand genere beaucoup de broadcasts degradant
   les performances et compliquant la supervision et securite.
