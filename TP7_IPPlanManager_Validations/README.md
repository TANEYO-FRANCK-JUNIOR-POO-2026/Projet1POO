# TP7 - Validations avancees et detection des conflits

## Objectif
Ajouter des validations avancees pour detecter les incoherences
dans un plan d'adressage IP et rendre l'application plus robuste
et professionnelle.

## Notions etudiees
- Exceptions personnalisees (extends Exception)
- Mots-cles throw, throws, try/catch
- Validation reseau (format IP, chevauchement, conflit VLAN)
- Classe de service ValidateurPlanAdressage
- Detection de chevauchement entre sous-reseaux
- Detection de conflit entre identifiants VLAN
- Robustesse logicielle

## Classes creees

| Classe | Role |
|---|---|
| AdresseIPInvalideException | Exception pour adresse IP invalide |
| ConflitVLANException | Exception pour doublon d'ID VLAN |
| ChevauchementReseauException | Exception pour chevauchement de reseaux |
| ReseauInsuffisantException | Exception pour reseau trop petit |
| ValidateurPlanAdressage | Classe de service de validation |

## Scenarios testes

### Scenario 1 - Plan normal
Adresse : 192.168.1.0
Besoins : ADMINISTRATION(50), TECHNIQUE(120), WIFI(80), SERVEURS(20)
Resultat : Validation reussie, aucun conflit detecte

### Scenario 2 - Conflit VLAN
Deux VLANs avec identifiant 10
Resultat : ConflitVLANException declenchee

### Scenario 3 - Adresse IP invalide
Adresse testee : 192.168.300.0
Resultat : AdresseIPInvalideException declenchee

### Scenario 4 - Chevauchement de reseaux
Reseaux : 192.168.1.0/25 et 192.168.1.64/26
Resultat : ChevauchementReseauException declenchee

### Scenario 5 - Reseau insuffisant
Reseau : 192.168.1.0/26 (62 hotes)
Besoins cumules : 300 hotes
Resultat : ReseauInsuffisantException declenchee

## Resultats obtenus
- Scenario 1 : Validation terminee, aucun conflit detecte
- Scenario 2 : Erreur VLAN : identifiant 10 deja utilise
- Scenario 3 : Adresse IP invalide : 192.168.300.0
- Scenario 4 : Chevauchement detecte entre RESEAU_A et RESEAU_B
- Scenario 5 : Reseau insuffisant : capacite=62, besoins=378

## Difficultes rencontrees
- Comprendre la syntaxe throws dans la signature des methodes.
- Gerer les exceptions separement avec plusieurs blocs catch.
- Construire la logique de detection de chevauchement
  avec les entiers 32 bits.
- Creer ReseauInsuffisantException et l'integrer correctement.

## Reponses aux questions

1. Sans validations un plan incorrect peut provoquer des conflits
   d'adresses, des routes incoherentes et des pannes reseau.

2. Une erreur simple continue silencieusement. Une exception
   interrompt le flux et force le traitement de l'erreur.

3. Les exceptions personnalisees donnent un nom precis a chaque
   type d'erreur reseau. ConflitVLANException est plus claire
   qu'Exception generique.

4. Le bloc try contient le code risque. Le bloc catch recupere
   l'exception et execute le traitement sans planter l'application.

5. Dans IEEE 802.1Q chaque VLAN est identifie par un numero
   unique. Deux VLANs avec le meme ID provoqueraient des
   confusions sur les switchs.

6. Deux sous-reseaux qui se chevauchent partagent des adresses.
   Des machines pourraient recevoir la meme adresse provoquant
   des conflits et interruptions de service.

7. Comparer des chaines ne permet pas de calculer les bornes
   d'un reseau. La conversion en entier 32 bits permet de
   comparer des intervalles numeriques precisement.

8. ValidateurPlanAdressage a un role unique : verifier la
   coherence. MoteurVLSM calcule les sous-reseaux. Melanger
   ces responsabilites rendrait le code difficile a maintenir.
