# Démo JDBC — formation Diginamic

> Dépôt pédagogique conservé comme support de cours. Il ne s'agit pas d'une application destinée à la production.

Exercices Java réalisés en 2023 pour pratiquer l'accès à MariaDB avec JDBC, le pattern DAO et la journalisation.

## Contenu

- connexion JDBC centralisée ;
- entités `Article` et `Fournisseur` ;
- interfaces DAO et implémentations JDBC ;
- classes exécutables pour tester les opérations SQL.

## Exécution

Le projet utilise Java 17 et Maven. Adaptez les paramètres locaux de `src/main/resources/database.properties`, puis lancez la classe d'exercice souhaitée depuis l'IDE ou après :

```bash
mvn compile
```

N'utilisez pas d'identifiants de production dans ce dépôt de démonstration.
