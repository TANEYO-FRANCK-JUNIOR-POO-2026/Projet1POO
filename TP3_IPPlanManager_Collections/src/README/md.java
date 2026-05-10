# TP3 - IPPlan-Manager : Gestion des réseaux, sous-réseaux et collections d'objets

## Objectif du TP
Faire évoluer le projet IPPlan-Manager pour gérer des ensembles
structurés d'objets liés entre eux : un équipement peut désormais
posséder plusieurs interfaces, une infrastructure contient plusieurs
sous-réseaux et plusieurs équipements. Ce TP introduit la composition
et les collections Java (ArrayList).

## Classes modifiées / créées
- Equipement         → remplace l'interface unique par une ArrayList<InterfaceReseau>
- SousReseau         → nouvelle classe : sous-réseau logique lié à un ReseauIP
- InfrastructureReseau → nouvelle classe centrale : contient équipements et sous-réseaux
- Main               → création d'une infrastructure complète avec plusieurs objets liés

## Nouvelles fonctionnalités
- Méthode ajouterInterface(InterfaceReseau) dans Equipement
- Méthode ajouterEquipement(Equipement) dans InfrastructureReseau
- Méthode ajouterSousReseau(SousReseau) dans InfrastructureReseau
- Méthode rechercherEquipement(String nom) dans InfrastructureReseau :
  parcourt la liste des équipements et affiche celui trouvé,
  ou affiche "Équipement introuvable." si absent.

## Questions de compréhension

1. Qu'est-ce que la composition en Programmation Orientée Objet ?
La composition est une relation entre classes où un objet contient
d'autres objets comme attributs. Par exemple, InfrastructureReseau
contient une liste d'Equipement, et chaque Equipement contient une
liste d'InterfaceReseau. C'est différent de l'héritage : il ne s'agit
pas d'une relation "est un" mais d'une relation "possède un/des".

2. Pourquoi utilise-t-on ArrayList dans ce TP ?
ArrayList est une structure de données dynamique qui permet de stocker
un nombre variable d'objets. Contrairement à un tableau (array) de
taille fixe, une ArrayList peut grandir ou réduire selon les besoins.
Elle offre des méthodes pratiques : add(), size(), get(), et supporte
la boucle for-each.

3. Quelle différence entre une variable simple et une collection ?
Une variable simple ne contient qu'une seule valeur à la fois
(ex : `InterfaceReseau interfacePrincipale`). Une collection comme
ArrayList peut contenir plusieurs objets du même type, accessibles
dynamiquement. La collection est indispensable dès qu'un objet peut
en contenir plusieurs autres en nombre variable.

4. Pourquoi un équipement possède-t-il plusieurs interfaces ?
Dans la réalité, un routeur possède plusieurs interfaces physiques
(eth0, eth1, eth2…) connectées à des réseaux différents. Un serveur
peut avoir une interface de données et une interface de management.
Limiter un équipement à une seule interface serait une modélisation
inexacte de la réalité réseau.

5. Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?
Une entreprise réelle segmente toujours son réseau en plusieurs
sous-réseaux pour des raisons de sécurité, de performance et
d'organisation (ex : VLAN Administration, VLAN Technique, VLAN WiFi).
L'infrastructure doit donc pouvoir gérer tous ces sous-réseaux ensemble.

6. Quel est le rôle de la boucle for-each ?
La boucle for-each (for améliorée) parcourt automatiquement tous les
éléments d'une collection sans gérer d'index manuellement.
Exemple :
    for (Equipement eq : equipements) {
        eq.afficher();
    }
Elle est plus lisible et moins sujette aux erreurs d'index que la
boucle for classique.

7. Pourquoi la classe InfrastructureReseau devient-elle importante ?
Elle joue le rôle de point d'entrée unique de l'application : elle
regroupe, organise et expose tous les objets de l'infrastructure
(équipements et sous-réseaux). C'est le cœur du projet IPPlan-Manager,
qui s'approche maintenant d'un vrai outil de gestion réseau.

8. Pourquoi les collections sont-elles indispensables dans les applications
professionnelles ?
Dans une vraie infrastructure, on ne connaît pas à l'avance le nombre
exact d'équipements, d'interfaces ou de sous-réseaux. Les collections
permettent de gérer dynamiquement ces objets : ajout, suppression,
recherche, parcours. Sans collections, il faudrait déclarer autant
de variables que d'objets, ce qui est impossible à grande échelle.
