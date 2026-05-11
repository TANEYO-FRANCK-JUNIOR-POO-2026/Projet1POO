# TP3 - Collections et gestion des equipements reseau

## Objectif
Introduire les collections Java (ArrayList) pour permettre
a un equipement de posseder plusieurs interfaces reseau
et a une infrastructure de gerer plusieurs equipements
et sous-reseaux.

## Notions etudiees
- ArrayList et collections dynamiques
- Boucle for-each
- Composition avec collections
- Classe InfrastructureReseau
- Classe SousReseau
- Methode rechercherEquipement()

## Classes creees ou modifiees

| Classe | Modification |
|---|---|
| Equipement | ArrayList<InterfaceReseau> au lieu d'une seule interface |
| SousReseau | Nouvelle classe representant un sous-reseau nomme |
| InfrastructureReseau | Nouvelle classe conteneur avec ArrayList<Equipement> et ArrayList<SousReseau> |

## Infrastructure creee

### Sous-reseaux
| Nom | Reseau | Description |
|---|---|---|
| ADMIN | 192.168.1.0/24 | Reseau Administration |
| TECH | 192.168.2.0/24 | Reseau Technique |
| WIFI | 192.168.3.0/24 | Reseau WiFi |

### Equipements
| Nom | Type | Interfaces |
|---|---|---|
| R1_EDGE | Routeur | eth0, eth1 |
| SW_CORE | Switch | eth0, eth1 (sans IP) |
| SRV_DNS | Serveur | eth0 |
| SRV_DHCP | Serveur | eth0 |
| AP_WIFI_01 | Point d'acces | eth0, wlan0 |
| PC_ADMIN | Poste client | wlan0 |
| PC_USER01 | Poste client | wlan0 |

## Resultats obtenus
- Infrastructure complete avec 3 sous-reseaux et 7 equipements
- Chaque equipement peut avoir plusieurs interfaces
- Recherche par nom fonctionnelle
- Affichage complet de l'infrastructure

## Test rechercherEquipement()
- SRV_DNS -> trouve et affiche correctement
- ROUTEUR_X -> introuvable, message affiche

## Difficultes rencontrees
- Comprendre la difference entre tableau et ArrayList.
- Gerer une interface sans adresse IP avec null.
- Comprendre la boucle for-each sur une ArrayList.

## Reponses aux questions

1. La composition est une relation "a-un". Un Equipement
   a des InterfaceReseau. Elle traduit en code la structure
   reelle du reseau.

2. ArrayList est une liste dynamique qui s'adapte au nombre
   d'objets ajoutes. Contrairement a un tableau de taille fixe,
   elle permet d'ajouter et supprimer des objets librement.

3. Une variable simple stocke un seul objet. Une collection
   peut en stocker plusieurs avec des methodes pour les
   manipuler collectivement.

4. Un vrai routeur possede plusieurs ports physiques pour se
   connecter a plusieurs reseaux simultanement. Limiter a
   une seule interface etait irrealiste.

5. Une infrastructure d'entreprise est segmentee en plusieurs
   sous-reseaux pour des raisons de securite et d'organisation.

6. La boucle for-each parcourt automatiquement tous les elements
   sans gerer d'index. Elle est plus lisible et moins sujette
   aux erreurs.

7. InfrastructureReseau est le conteneur central qui agregue
   tous les objets. Sans elle les equipements seraient
   eparpilles sans structure coherente.

8. Les applications professionnelles manipulent des dizaines
   d'objets dont le nombre varie. Les collections permettent
   de les gerer dynamiquement et efficacement.
