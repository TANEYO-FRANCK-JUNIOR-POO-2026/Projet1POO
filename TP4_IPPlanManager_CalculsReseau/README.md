
# TP4 - Calculs reseau automatiques

## Objectif
Introduire une classe utilitaire de calculs reseau dans
IPPlan-Manager. Automatiser le calcul du nombre d'hotes,
la classe reseau, le masque decimal et la detection
des plages privees.

## Notions etudiees
- Methodes statiques (static)
- Classe utilitaire CalculateurReseau
- Calcul du nombre d'hotes : 2^(32-CIDR) - 2
- Classes reseau A, B, C
- Conversion CIDR vers masque decimal
- Detection des plages privees RFC 1918

## Classes creees ou modifiees

| Classe | Modification |
|---|---|
| CalculateurReseau | Nouvelle classe utilitaire avec toutes les methodes de calcul |
| ReseauIP | Methode afficher() enrichie avec les calculs automatiques |

## Methodes de CalculateurReseau

| Methode | Role |
|---|---|
| calculerNombreHotes(cidr) | Calcule le nombre d'hotes utilisables |
| obtenirClasseReseau(ip) | Retourne la classe A, B ou C |
| obtenirMasqueDecimal(cidr) | Convertit CIDR en masque decimal |
| estReseauPrive(ip) | Verifie si l'adresse est privee RFC 1918 |

## Reseaux testes

| Reseau | CIDR | Classe | Masque | Capacite | Prive |
|---|---|---|---|---|---|
| 192.168.1.0 | /24 | C | 255.255.255.0 | 254 | Oui |
| 172.16.0.0 | /16 | B | 255.255.0.0 | 65534 | Oui |
| 10.0.0.0 | /8 | A | 255.0.0.0 | 16777214 | Oui |
| 192.168.5.0 | /27 | C | 255.255.255.224 | 30 | Oui |
| 8.8.8.0 | /24 | A | 255.255.255.0 | 254 | Non |

## Test estReseauPrive()
- 10.5.0.1 -> privee : true
- 172.20.1.1 -> privee : true
- 192.168.10.5 -> privee : true
- 8.8.8.8 -> privee : false
- 203.0.113.1 -> privee : false

## Resultats obtenus
- Calculs automatiques corrects pour tous les reseaux
- Classes reseau detectees correctement
- Masques decimaux affiches correctement
- Plages privees RFC 1918 detectees correctement

## Difficultes rencontrees
- Comprendre la formule 2^(32-CIDR) - 2.
- Comprendre pourquoi le -2 exclut reseau et broadcast.
- Implementer correctement les plages privees RFC 1918.

## Reponses aux questions

1. Pour centraliser et isoler la logique de calcul reseau.
   Si une formule change on la modifie en un seul endroit.
   C'est le principe de responsabilite unique.

2. static signifie que la methode appartient a la classe
   elle-meme. On l'appelle directement sans creer d'objet.
   C'est adapte aux methodes utilitaires.

3. Un outil IPAM doit automatiser la gestion des adresses IP.
   Sans calculs automatiques tout serait manuel et source
   d'erreurs.

4. Le CIDR est une notation compacte indiquant le nombre de
   bits reseau. Il permet de definir precisement la taille
   d'un reseau et de le subdiviser.

5. Plus le prefixe CIDR est grand, moins il reste de bits
   pour les hotes. Le -2 exclut l'adresse reseau et broadcast.

6. Les plages privees RFC 1918 sont reservees aux reseaux
   locaux et non routables sur Internet. Le NAT assure
   la communication avec l'exterieur.

7. Les classes metier restent simples. Les modifications
   de calculs n'affectent que CalculateurReseau. La
   maintenance est beaucoup plus facile.

8. Dans une grande infrastructure calculer manuellement
   les masques est lent et source d'erreurs. L'automatisation
   garantit la coherence et reduit les risques de conflits.