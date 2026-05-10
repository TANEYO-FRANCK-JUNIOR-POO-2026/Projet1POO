
# TP1 - IPPlan-Manager

## Objectif du TP
Découvrir les premières classes Java du projet IPPlan-Manager et
représenter les entités fondamentales d'un réseau informatique
(adresse IP, réseau, interface, équipement) sous forme d'objets Java.

## Classes créées
- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement
- Main

## Questions de compréhension

1. Pourquoi représenter une adresse IP par une classe plutôt qu'une String ?
Regrouper l'adresse IP dans une classe permet de lui associer des comportements
propres (afficher, valider, calculer…) et de la réutiliser facilement dans
d'autres classes comme InterfaceReseau. Si l'on stocke l'adresse dans une
simple String, ces comportements devraient être réécrits partout dans le
programme, ce qui crée de la duplication et des risques d'erreurs.

2. Quelle est la différence entre une classe et un objet ?
Une classe est un modèle (un plan) qui décrit la structure et le comportement
d'une entité. Un objet est une instance concrète de ce modèle, créée en mémoire
à l'exécution. Par exemple, AdresseIP est la classe ; new AdresseIP("192.168.1.1")
est un objet.

3. Quel est le rôle du constructeur dans une classe Java ?
Le constructeur est une méthode spéciale appelée automatiquement lors de la
création d'un objet. Il initialise les attributs de l'objet avec les valeurs
fournies au moment de l'instanciation.

4. Pourquoi InterfaceReseau contient-elle un objet AdresseIP ?
Une interface réseau réelle possède toujours une adresse IP. En utilisant un
objet AdresseIP (au lieu d'une simple String), on bénéficie de tous les
comportements définis dans cette classe, et on établit une relation logique
entre les deux entités — ce qui reflète fidèlement la réalité réseau.

5. Pourquoi Equipement contient-elle un objet InterfaceReseau ?
Un équipement réseau possède obligatoirement des interfaces pour communiquer.
Contenir un objet InterfaceReseau permet de modéliser ce lien réel et de
déléguer l'affichage et la gestion de l'interface à sa propre classe.

6. Quelle est la limite actuelle de la classe Equipement dans ce TP ?
Un équipement ne peut posséder qu'une seule interface (interfacePrincipale).
Or, dans la réalité, un routeur ou un serveur possède souvent plusieurs
interfaces réseau. Cette limite sera corrigée dans les TPs suivants avec les
collections.

7. Pourquoi cette version ne suffit pas pour produire automatiquement un plan
d'adressage IP ?
L'application ne fait qu'afficher des données saisies manuellement. Elle ne
calcule rien : pas de masques automatiques, pas de plages d'hôtes, pas de
détection de conflits d'adresses, pas de génération de sous-réseaux. Ces
fonctionnalités seront introduites progressivement dans les TPs suivants.