# TP5 - Moteur VLSM

## Objectif
Developper un moteur VLSM capable de proposer automatiquement
un plan d'adressage IP a partir des besoins exprimes
par l'utilisateur.

## Notions etudiees
- VLSM (Variable Length Subnet Mask)
- Tri de collections avec Collections.sort() et Comparator
- Classe de service metier MoteurVLSM
- Calcul automatique de CIDR a partir d'un nombre d'hotes
- Conversion adresse IP en entier et inverse
- Generation automatique de sous-reseaux consecutifs
- Calcul de la premiere et derniere adresse utilisable

## Classes creees

| Classe | Role |
|---|---|
| BesoinReseau | Represente une demande utilisateur |
| ResultatVLSM | Represente le resultat calcule par le moteur |
| MoteurVLSM | Moteur de calcul VLSM |
| CalculateurReseau | Enrichi avec calculerCidrPourHotes(), convertirIpEnEntier(), convertirEntierEnIp(), calculerTailleBloc() |

## Scenarios testes

### Scenario 1 - Infrastructure IRT-LAB
Adresse de depart : 192.168.1.0
| Besoin | Hotes | CIDR | Capacite | Plage |
|---|---|---|---|---|
| TECHNIQUE | 120 | /25 | 126 | 192.168.1.1 - 192.168.1.126 |
| WIFI | 80 | /25 | 126 | 192.168.1.129 - 192.168.1.254 |
| ADMINISTRATION | 50 | /26 | 62 | 192.168.2.1 - 192.168.2.62 |
| SERVEURS | 20 | /27 | 30 | 192.168.2.65 - 192.168.2.94 |
| DIRECTION | 10 | /28 | 14 | 192.168.2.97 - 192.168.2.110 |

### Scenario 2 - Petite entreprise
Adresse de depart : 10.0.0.0
| Besoin | Hotes | CIDR | Capacite |
|---|---|---|---|
| WIFI_INVITES | 40 | /26 | 62 |
| ADMIN | 25 | /27 | 30 |
| COMPTABILITE | 12 | /28 | 14 |
| SERVEURS | 8 | /28 | 14 |

### Scenario 3 - Campus universitaire
Adresse de depart : 172.16.0.0
| Besoin | Hotes | CIDR | Capacite |
|---|---|---|---|
| ETUDIANTS | 500 | /23 | 510 |
| WIFI_PUBLIC | 200 | /24 | 254 |
| PERSONNEL | 120 | /25 | 126 |
| LABORATOIRE | 60 | /26 | 62 |
| ADMINISTRATION | 40 | /26 | 62 |

## Difficultes rencontrees
- Comprendre la conversion d'une adresse IP en entier 32 bits.
- Appliquer correctement le tri decroissant avec Comparator.
- S'assurer que chaque scenario utilise sa propre copie
  de la liste pour eviter que le tri affecte les suivants.

## Reponses aux questions

1. Le VLSM attribue a chaque sous-reseau exactement la taille
   necessaire evitant le gaspillage d'adresses IP.

2. En placant les plus grands besoins en premier on garantit
   qu'ils disposent de blocs contigus suffisamment larges.

3. BesoinReseau est une demande utilisateur. ResultatVLSM
   est ce que le moteur produit. On separe l'entree du calcul.

4. MoteurVLSM applique une logique metier complete : tri,
   calcul de CIDR, conversion IP-entier, progression
   des adresses.

5. Pour additionner et comparer des plages reseau
   mathematiquement. Java ne peut pas calculer
   "192.168.1.0 + 128" sur une chaine.

6. calculerCidrPourHotes() cherche le plus petit CIDR dont
   la capacite couvre le besoin demande.

7. L'adresse reseau et broadcast ont des roles de signalisation.
   Les attribuer a des machines provoquerait des conflits.

8. Le moteur VLSM transforme IPPlan-Manager en vrai outil
   de planification automatisant le travail de l'administrateur.
