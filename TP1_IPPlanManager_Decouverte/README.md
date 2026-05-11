# TP2 - IPPlan-Manager : Encapsulation et validation des données réseau

## Objectif du TP
Améliorer l'architecture du projet IPPlan-Manager en introduisant
l'encapsulation : protéger les attributs des classes, contrôler
les modifications via des getters/setters et valider les données
réseau afin d'éviter les incohérences et les erreurs de configuration.

## Classes modifiées
- AdresseIP      → attribut privé, getter/setter, validation, estAdresseLocale()
- ReseauIP       → attributs privés, validation du masque CIDR (0–32)
- InterfaceReseau → attribut nom protégé, getter isActive()
- Equipement     → nom et type protégés contre les valeurs nulles/vides
- Main           → test des validations, des cas invalides et des setters

## Nouvelles fonctionnalités
- Valeurs par défaut automatiques en cas de données invalides
  (ex : masque CIDR hors plage → 24, adresse vide → 0.0.0.0)
- Méthode `estAdresseLocale()` dans AdresseIP :
  retourne true si l'adresse commence par "192."

## Questions de compréhension

1. Pourquoi protéger les attributs avec private ?
Sans private, n'importe quelle partie du programme peut modifier
directement un attribut sans aucun contrôle. Par exemple :
equipement.nom = null; ou ip.valeur = "";
Ces modifications peuvent produire des configurations réseau invalides,
des erreurs de calcul ou des comportements imprévisibles. Le mot-clé
private interdit l'accès direct depuis l'extérieur de la classe.

2. Quel est le rôle d'un getter ?
Un getter est une méthode publique qui permet de lire la valeur
d'un attribut privé depuis l'extérieur de la classe, sans exposer
l'attribut directement. Exemple : getValeur() dans AdresseIP.

3. Quel est le rôle d'un setter ?
Un setter est une méthode publique qui permet de modifier un attribut
privé tout en appliquant des règles de validation avant d'accepter
la nouvelle valeur. Si la valeur est invalide, une valeur par défaut
ou un message d'erreur est produit.

4. Pourquoi appeler le setter dans le constructeur ?
Appeler `setValeur(valeur)` dans le constructeur plutôt que
this.valeur = valeur` permet de réutiliser la logique de validation
dès la création de l'objet. Cela évite la duplication du code de
validation et garantit qu'aucun objet n'est créé avec des données
incorrectes.

5. Quelle est la différence entre un attribut public et private ?
Un attribut public est accessible et modifiable depuis n'importe
quelle classe du programme. Un attribut private n'est accessible
que depuis l'intérieur de la classe qui le déclare. L'encapsulation
recommande private pour protéger l'intégrité des données.

6. Pourquoi valider le masque CIDR entre 0 et 32 ?
En IPv4, un masque CIDR valide est compris entre /0 (aucun bit réseau)
et /32 (une seule adresse hôte). Un masque comme /55 est mathématiquement
impossible car une adresse IPv4 ne contient que 32 bits. Accepter une
telle valeur produirait des calculs de plages d'hôtes complètement faux.

7. Pourquoi une interface peut-elle avoir une adresse IP nulle ?
Dans la réalité réseau, une interface peut être physiquement présente
sur un équipement mais ne pas encore avoir d'adresse IP configurée
(interface non initialisée, en attente de configuration DHCP, etc.).
Le code gère ce cas avec un test `if (adresseIP != null)`.

8. En quoi l'encapsulation améliore-t-elle la robustesse du projet ?**
L'encapsulation garantit que les objets ne peuvent jamais se retrouver
dans un état incohérent. Chaque classe contrôle elle-même ses propres
données. Si les règles métier changent (ex : nouvelle règle de validation
du CIDR), une seule méthode est à modifier au lieu de chercher toutes
les affectations directes dans tout le programme.

