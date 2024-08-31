# Mitterie Bot V2

## Installation

### Token 

Mettre le token du bot discord dans le fichier `src/main/resources/token.txt`
Le fichier doit uniquement avoir le token et aucune autre information

### Connexion à la base de données

Faire un fichier .prop à cet endroit : `src/main/resources/db.prop` avec cette structure :

```
driver=org.postgresql.Driver
url=<lien>
nom=<nomutilisateur>
mdp=<password>
```

j'ai laissé psql pcq c'est le goat