# TP3 - Collections et gestion d'infrastructure reseau

## Objectif
Utiliser les collections ArrayList pour gerer plusieurs interfaces
reseau, equipements et sous-reseaux dans une infrastructure complete.

## Notions etudiees
- Collections ArrayList
- Ajout et parcours d'elements
- Methode rechercherEquipement()
- Relations entre objets (Equipement, InterfaceReseau, SousReseau)
- Architecture d'infrastructure reseau

## Classes implementees

| Classe | Role |
|---|---|
| AdresseIP | Stocke une adresse IPv4 |
| ReseauIP | Stocke un reseau avec CIDR |
| InterfaceReseau | Interface reseau d'un equipement |
| Equipement | Equipement reseau avec ses interfaces |
| SousReseau | Sous-reseau nomme |
| InfrastructureReseau | Conteneur global de l'infrastructure |
| CalculateurReseau | Calculs reseau (masque, capacite, classe) |
| Main | Point d'entree |

## Scenarios testes

### Scenario 1 - Infrastructure IRT-LAB
Adresse principale : 192.168.1.0/24
Sous-reseaux : LAN-Principal, LAN-Serveurs
Equipements : Routeur-Principal, Switch-Coeur, PC-Admin

### Scenario 2 - Universite
Adresse principale : 10.0.1.0/24
Sous-reseaux : ETUDIANTS, ENSEIGNANTS, SERVEURS
Equipements : Routeur-Campus, Serveur-Web

## Resultats obtenus

### Scenario 1
Infrastructure : IRT-LAB

SOUS-RESEAUX :
- LAN-Principal : 192.168.1.0/24 | Masque : 255.255.255.0   | Capacite : 254 hotes
- LAN-Serveurs  : 192.168.2.0/26 | Masque : 255.255.255.192 | Capacite : 62 hotes

EQUIPEMENTS :
- Routeur-Principal (Routeur) | 2 interfaces | eth0:192.168.1.1, eth1:192.168.2.1
- Switch-Coeur (Switch)       | 1 interface  | fa0/1:192.168.1.2
- PC-Admin (Poste)            | 1 interface  | eth0:192.168.1.10

RECHERCHE :
- Switch-Coeur   : trouve
- Firewall       : introuvable

### Scenario 2
Infrastructure : Universite

SOUS-RESEAUX :
- ETUDIANTS  : 10.0.1.0/24 | Masque : 255.255.255.0   | Capacite : 254 hotes
- ENSEIGNANTS: 10.0.2.0/25 | Masque : 255.255.255.128  | Capacite : 126 hotes
- SERVEURS   : 10.0.3.0/27 | Masque : 255.255.255.224  | Capacite : 30 hotes

EQUIPEMENTS :
- Routeur-Campus (Routeur) | 3 interfaces
- Serveur-Web (Serveur)    | 1 interface
- Total : 2 equipements

## Difficultes rencontrees
- Comprendre la relation entre Equipement et InterfaceReseau.
- Utiliser correctement ArrayList avec des objets personnalises.
- Implementer rechercherEquipement() avec equalsIgnoreCase().
- Corriger l'erreur NoClassDefFoundError avec Clean and Build.

## Reponses aux questions

1. ArrayList permet de stocker un nombre variable d'elements sans
   connaitre leur nombre a l'avance. Un tableau a une taille fixe
   definie a la creation.

2. Un equipement reseau reel possede plusieurs ports physiques.
   ArrayList<InterfaceReseau> permet de modeliser cette realite
   sans limiter le nombre d'interfaces.

3. rechercherEquipement() parcourt la liste avec une boucle for
   et compare les noms avec equalsIgnoreCase() pour ignorer
   la casse. Elle retourne une confirmation ou un message
   d'introuvable.

4. InfrastructureReseau contient deux ArrayList : une pour les
   equipements et une pour les sous-reseaux. Elle orchestre
   l'ensemble de l'infrastructure reseau.

5. La methode afficher() de chaque classe permet d'afficher
   les informations de l'objet de maniere structuree dans
   la console.

6. equalsIgnoreCase() compare deux chaines sans tenir compte
   des majuscules et minuscules. Cela evite des erreurs de
   recherche dues a la casse.

7. ajouterInterface() verifie que l'objet n'est pas null avant
   de l'ajouter a la liste. Cela evite des NullPointerException
   lors du parcours de la liste.

8. La separation en classes distinctes respecte le principe
   de responsabilite unique. Chaque classe gere uniquement
   ses propres donnees et comportements.
