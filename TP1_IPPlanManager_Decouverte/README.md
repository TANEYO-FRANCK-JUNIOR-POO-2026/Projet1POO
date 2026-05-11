# TP1 - Decouverte du projet IPPlan-Manager et creation des premieres classes Java

## Objectif
Decouvrir les bases de la Programmation Orientee Objet en Java
en creant les premieres classes du projet IPPlan-Manager.
Comprendre les notions de classe, objet, attribut, constructeur
et methode a travers un contexte reseau concret.

## Notions etudiees
- Definition d'une classe Java
- Creation d'objets avec le mot-cle new
- Attributs et constructeurs
- Methodes d'instance
- Composition entre classes
- Relation entre Equipement, InterfaceReseau et AdresseIP

## Classes creees

| Classe | Role |
|---|---|
| AdresseIP | Represente une adresse IPv4 |
| ReseauIP | Represente un reseau avec adresse et masque CIDR |
| InterfaceReseau | Interface reseau d'un equipement |
| Equipement | Equipement reseau avec interface principale |
| Main | Point d'entree et tests |

## Equipements crees

| Nom | Type | Adresse IP |
|---|---|---|
| R1_EDGE | Routeur | 192.168.1.1 |
| SRV_DNS | Serveur | 192.168.1.10 |
| PC_ADMIN | Poste client | 192.168.1.50 |
| SW_CORE | Switch | 192.168.1.2 |
| AP_WIFI_01 | Point d'acces WiFi | 192.168.2.1 |
| PC_USER01 | Poste client | 192.168.2.50 |
| SRV_BACKUP | Serveur | Sans IP |

## Reseaux crees

| Reseau | CIDR | Description |
|---|---|---|
| 192.168.1.0 | /24 | Reseau principal IRT |
| 192.168.2.0 | /24 | Reseau WiFi IRT |

## Resultats obtenus
- 7 equipements crees et affiches correctement
- 2 reseaux affiches avec leurs descriptions
- Interfaces actives et inactives gerees
- Interface sans adresse IP geree correctement

## Difficultes rencontrees
- Comprendre la relation de composition entre les classes.
- Gerer une interface sans adresse IP avec la valeur null.
- Distinguer la classe de l'objet instancie.

## Reponses aux questions

1. Representer l'adresse IP par une classe lui donne un statut
   d'objet metier. On peut y ajouter des methodes de validation
   ou de calcul sans toucher aux autres classes.

2. Une classe est un modele de construction. Un objet est une
   instance concrete creee a partir de ce modele. Plusieurs
   objets peuvent etre crees a partir d'une meme classe.

3. Le constructeur est une methode speciale appelee lors du new.
   Il initialise les attributs pour que l'objet soit dans un
   etat coherent des sa creation.

4. Une interface reseau est physiquement liee a une adresse IP.
   Utiliser un objet AdresseIP permet d'enrichir cet objet plus
   tard sans modifier InterfaceReseau.

5. Un equipement reseau possede des interfaces physiques.
   La composition reflete fidelement la realite :
   Equipement -> InterfaceReseau -> AdresseIP.

6. La limite est qu'un equipement ne peut avoir qu'une seule
   interface. Un vrai routeur en possede plusieurs.
   Cette limite sera levee au TP3.

7. Il n'y a aucun calcul automatique, aucune validation des
   donnees, aucune gestion de collections et aucune detection
   de conflits d'adresses.
