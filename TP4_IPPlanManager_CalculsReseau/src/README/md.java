# TP4 - Calculs réseau automatiques

## Objectif du TP
Enrichir IPPlan-Manager avec une logique de calcul réseau automatisée :
nombre d'hôtes, classe réseau, masque décimal, détection des plages privées.

## Classes créées / modifiées
- CalculateurReseau (nouvelle — classe utilitaire statique)
- ReseauIP (affichage enrichi)
- SousReseau, Equipement, InfrastructureReseau (TP3 réutilisées)
- Main (tests des calculs)

## Questions de compréhension

1. Pourquoi a-t-on créé une classe utilitaire ?
Parce que les calculs réseau ne représentent pas un objet métier réel. Les
regrouper dans une classe séparée (CalculateurReseau) évite la duplication
du code, centralise les traitements et facilite la maintenance. Si la formule
de calcul change, une seule classe est à modifier.

2. Quel est le rôle du mot-clé static ?
Une méthode statique appartient à la classe elle-même et non à une instance.
On peut donc l'appeler sans créer d'objet :
CalculateurReseau.calculerNombreHotes(24);
C'est idéal pour les classes utilitaires où les méthodes sont des fonctions
pures qui ne dépendent d'aucun état interne.

3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?
Un outil IPAM (IP Address Management) doit automatiser la gestion des
adresses IP. Sans calculs automatiques, l'administrateur doit effectuer
manuellement les conversions de masques, compter les hôtes disponibles
et vérifier les plages — sources d'erreurs dans de grandes infrastructures.

4. Quelle est l'utilité du CIDR ?
Le CIDR (Classless Inter-Domain Routing) permet d'exprimer le masque de
sous-réseau sous forme compacte (/24, /26…). Il évite les gaspillages
d'adresses liés aux classes A/B/C fixes et offre une découpe flexible
de l'espace d'adressage.

5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?
Le masque définit le nombre de bits réservés à la partie hôte. Plus le
CIDR est grand (ex : /30), moins de bits restent pour les hôtes (2 hôtes
utilisables). La formule 2^(32 - CIDR) - 2 traduit mathématiquement cette
relation.

6. Pourquoi certaines adresses IP sont-elles privées ?
Les adresses privées (RFC 1918 : 10.x, 172.16–31.x, 192.168.x) sont
réservées aux réseaux internes. Elles ne sont pas routables sur Internet,
ce qui permet à des millions d'entreprises de réutiliser les mêmes plages
en interne. La translation NAT assure la communication avec l'extérieur.

7. Pourquoi séparer logique métier et logique de calcul améliore-t-il le projet ?
La séparation des responsabilités (principe SoC) rend le code plus lisible,
testable et maintenable. ReseauIP gère les données d'un réseau ;
CalculateurReseau gère les algorithmes. On peut modifier l'un sans toucher
à l'autre.

8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?
Dans une grande infrastructure (des centaines de sous-réseaux), calculer
manuellement les masques, les plages d'hôtes et les adresses de broadcast
est long, fastidieux et source d'erreurs critiques (conflits d'adresses,
mauvaise segmentation). L'automatisation garantit la fiabilité et la rapidité
des déploiements.
