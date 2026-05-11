# TP2 - Encapsulation et validation des donnees

## Objectif
Appliquer le principe d'encapsulation en rendant tous les
attributs prives et en ajoutant des getters, setters et
validations dans chaque classe du projet IPPlan-Manager.

## Notions etudiees
- Modificateur d'acces private
- Getters et setters
- Validation des donnees dans les setters
- Principe d'encapsulation
- Protection des attributs
- Methode estAdresseLocale()

## Classes modifiees

| Classe | Modifications |
|---|---|
| AdresseIP | Attribut private, getter, setter avec validation, estAdresseLocale() |
| ReseauIP | Attributs private, getters, setters avec validation masque CIDR |
| InterfaceReseau | Attributs private, getters, setters |
| Equipement | Attributs private, getters, setters |

## Validations implementees

| Classe | Validation |
|---|---|
| AdresseIP | Valeur null ou vide -> 0.0.0.0 |
| ReseauIP | Masque CIDR hors [0-32] -> /24 par defaut |
| ReseauIP | Adresse null ou vide -> 0.0.0.0 |
| InterfaceReseau | Nom null ou vide -> interface_inconnue |
| Equipement | Nom null ou vide -> equipement_inconnu |
| Equipement | Type null ou vide -> Type inconnu |

## Scenarios testes

### Test validations AdresseIP
- AdresseIP("192.168.1.1") -> valide
- AdresseIP("") -> 0.0.0.0
- AdresseIP(null) -> 0.0.0.0

### Test estAdresseLocale()
- 192.168.1.1 -> locale : true
- 10.0.0.1 -> locale : false
- 172.16.5.10 -> locale : false

### Test validations ReseauIP
- ReseauIP("", 55, "") -> tout corrige par defaut
- Correction via setters apres creation

## Resultats obtenus
- Tous les attributs sont prives et proteges
- Les validations empechent les donnees incorrectes
- Les setters corrigent automatiquement les valeurs invalides
- La methode estAdresseLocale() fonctionne correctement

## Difficultes rencontrees
- Comprendre pourquoi appeler setValeur() dans le constructeur.
- Distinguer getter et setter et leur role respectif.
- Implementer des validations coherentes sans bloquer le programme.

## Reponses aux questions

1. L'encapsulation protege les donnees internes d'une classe.
   Elle empeche l'acces direct aux attributs depuis l'exterieur
   et garantit que les donnees restent coherentes.

2. Un getter retourne la valeur d'un attribut prive.
   Un setter modifie la valeur avec validation.
   Ensemble ils controlent l'acces aux donnees.

3. setMasqueCidr(55) detecte que 55 est hors [0-32] et
   applique la valeur par defaut /24.

4. Car dans la realite une interface reseau est physiquement
   liee a une adresse IP. La composition permet d'enrichir
   AdresseIP sans modifier InterfaceReseau.

5. Sans setters valides, n'importe quelle valeur pourrait
   etre stockee, provoquant des calculs incorrects et
   des plans d'adressage incoherents.

6. private signifie que l'attribut est accessible uniquement
   depuis la classe elle-meme. public le rend accessible
   depuis n'importe quelle autre classe.

7. Car il est reutilise dans le constructeur pour centraliser
   la logique de validation en un seul endroit.

8. L'encapsulation garantit qu'un objet reste dans un etat
   coherent pendant toute sa duree de vie dans l'application.
