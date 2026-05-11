# TP6 - VLAN et segmentation logique

## Objectif
Mettre en place la gestion des VLANs et associer automatiquement
les sous-reseaux generes par le moteur VLSM aux VLANs
correspondants.

## Notions etudiees
- Segmentation logique reseau (VLAN)
- Gestionnaires metier (GestionnaireVLAN)
- Collections ArrayList<VLAN>
- Associations entre objets VLAN et ResultatVLSM
- Architecture metier progressive
- IEEE 802.1Q

## Classes creees

| Classe | Role |
|---|---|
| VLAN | Represente un VLAN avec ID, nom, reseau associe |
| GestionnaireVLAN | Gere la collection de VLANs |

## Scenarios testes

### Scenario 1 - Infrastructure IRT-LAB
Adresse : 192.168.1.0
| VLAN | Nom | Reseau | Capacite |
|---|---|---|---|
| 10 | TECHNIQUE | 192.168.1.0/25 | 126 hotes |
| 20 | WIFI | 192.168.1.128/25 | 126 hotes |
| 30 | ADMINISTRATION | 192.168.2.0/26 | 62 hotes |
| 40 | SERVEURS | 192.168.2.64/27 | 30 hotes |

### Scenario 2 - Universite
Adresse : 172.16.0.0
| VLAN | Nom | Reseau | Capacite |
|---|---|---|---|
| 10 | ETUDIANTS | 172.16.0.0/23 | 510 hotes |
| 20 | WIFI_PUBLIC | 172.16.2.0/24 | 254 hotes |
| 30 | ENSEIGNANTS | 172.16.3.0/25 | 126 hotes |
| 40 | LABORATOIRES | 172.16.3.128/26 | 62 hotes |
| 50 | SERVEURS | 172.16.3.192/27 | 30 hotes |

## Tests effectues
- Recherche VLAN 20 -> trouve correctement
- VLANs critiques (> 100 hotes) detectes
- VLAN avec la plus grande capacite identifie

## Resultats obtenus
- VLANs crees automatiquement depuis les resultats VLSM
- Recherche par ID fonctionnelle
- VLANs critiques detectes correctement
- Association VLAN et sous-reseau correcte

## Difficultes rencontrees
- Comprendre la relation entre ResultatVLSM et VLAN.
- Gerer deux gestionnaires independants pour deux scenarios.
- Implementer afficherVLANsCritiques() avec le bon seuil.

## Reponses aux questions

1. Les VLANs segmentent logiquement un reseau physique unique
   en plusieurs reseaux independants reduisant les broadcasts
   et ameliorant la securite.

2. Un VLAN regroupe des machines d'un meme departement sur
   un sous-reseau coherent permettant le routage inter-VLAN
   et l'application de politiques reseau.

3. La separation logique isole les flux entre departements.
   Un poste WiFi invite ne peut pas acceder aux serveurs
   internes sans passer par un routeur securise.

4. GestionnaireVLAN centralise toutes les operations sur
   les VLANs : ajout, recherche, affichage, statistiques.

5. La classe VLAN contient un ResultatVLSM car un VLAN est
   directement lie au sous-reseau calcule par le moteur VLSM.

6. ArrayList permet de gerer un nombre variable de VLANs
   sans connaitre leur nombre a l'avance.

7. Chaque classe doit avoir un role unique et bien defini
   facilitant la maintenance et l'evolution du projet.

8. Le projet possede maintenant des besoins, des calculs,
   des resultats et une segmentation logique caracteristique
   des applications professionnelles.
