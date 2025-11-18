# 🧪 Guide de Test Complet - Gestion Foyer

## 📋 Ordre de Test Recommandé

### Phase 1: Vérification du Démarrage
1. ✅ Lancer l'application
2. ✅ Vérifier la console pour les messages de succès
3. ✅ Accéder à Swagger UI: http://localhost:8089/gestion-foyer/swagger-ui.html
4. ✅ Vérifier que toutes les tables sont créées dans MySQL

### Phase 2: Tests CRUD de Base (Sans Relations)

#### Test 1: Université
```bash
# 1. GET All - Devrait retourner liste vide []
GET http://localhost:8089/gestion-foyer/universite/retrieve-all-universites

# 2. POST - Ajouter une université
POST http://localhost:8089/gestion-foyer/universite/add-universite
Content-Type: application/json

{
  "nomUniversite": "ESPRIT",
  "adresse": "Ghazela, Tunis"
}

# Résultat attendu: Status 200, objet avec idUniversite généré

# 3. GET by ID - Récupérer l'université créée
GET http://localhost:8089/gestion-foyer/universite/retrieve-universite/1

# 4. PUT - Modifier l'université
PUT http://localhost:8089/gestion-foyer/universite/update-universite
Content-Type: application/json

{
  "idUniversite": 1,
  "nomUniversite": "ESPRIT - École Supérieure Privée",
  "adresse": "Ghazela, Ariana, Tunis"
}

# 5. GET All - Vérifier la modification
GET http://localhost:8089/gestion-foyer/universite/retrieve-all-universites
```

#### Test 2: Foyer
```bash
# 1. POST - Ajouter un foyer
POST http://localhost:8089/gestion-foyer/foyer/add-foyer
Content-Type: application/json

{
  "nomFoyer": "Foyer El Manar",
  "capaciteFoyer": 500
}

# 2. GET All Foyers
GET http://localhost:8089/gestion-foyer/foyer/retrieve-all-foyers

# 3. GET by ID
GET http://localhost:8089/gestion-foyer/foyer/retrieve-foyer/1

# 4. PUT - Modifier le foyer
PUT http://localhost:8089/gestion-foyer/foyer/update-foyer
Content-Type: application/json

{
  "idFoyer": 1,
  "nomFoyer": "Foyer El Manar",
  "capaciteFoyer": 600
}
```

#### Test 3: Bloc
```bash
# POST - Ajouter plusieurs blocs
POST http://localhost:8089/gestion-foyer/bloc/add-bloc
Content-Type: application/json

{
  "nomBloc": "Bloc A",
  "capaciteBloc": 150
}

POST http://localhost:8089/gestion-foyer/bloc/add-bloc
Content-Type: application/json

{
  "nomBloc": "Bloc B",
  "capaciteBloc": 200
}

# GET All Blocs
GET http://localhost:8089/gestion-foyer/bloc/retrieve-all-blocs
```

#### Test 4: Chambre
```bash
# POST - Ajouter des chambres
POST http://localhost:8089/gestion-foyer/chambre/add-chambre
Content-Type: application/json

{
  "numeroChambre": 101,
  "typeC": "SIMPLE"
}

POST http://localhost:8089/gestion-foyer/chambre/add-chambre
Content-Type: application/json

{
  "numeroChambre": 102,
  "typeC": "DOUBLE"
}

POST http://localhost:8089/gestion-foyer/chambre/add-chambre
Content-Type: application/json

{
  "numeroChambre": 103,
  "typeC": "TRIPLE"
}

# GET All Chambres
GET http://localhost:8089/gestion-foyer/chambre/retrieve-all-chambres
```

#### Test 5: Étudiant
```bash
# POST - Ajouter plusieurs étudiants
POST http://localhost:8089/gestion-foyer/etudiant/add-etudiants
Content-Type: application/json

[
  {
    "nomEt": "Ben Ali",
    "prenomEt": "Ahmed",
    "cin": 12345678,
    "ecole": "ESPRIT",
    "dateNaissance": "2000-01-15"
  },
  {
    "nomEt": "Trabelsi",
    "prenomEt": "Fatma",
    "cin": 87654321,
    "ecole": "ESPRIT",
    "dateNaissance": "2001-05-20"
  },
  {
    "nomEt": "Sassi",
    "prenomEt": "Mohamed",
    "cin": 11223344,
    "ecole": "ESPRIT",
    "dateNaissance": "1999-12-10"
  }
]

# GET All Étudiants
GET http://localhost:8089/gestion-foyer/etudiant/retrieve-all-etudiants
```

### Phase 3: Tests avec Relations

#### Test 6: Associer Foyer à Université
```bash
# PUT - Mettre à jour l'université avec son foyer
PUT http://localhost:8089/gestion-foyer/universite/update-universite
Content-Type: application/json

{
  "idUniversite": 1,
  "nomUniversite": "ESPRIT",
  "adresse": "Ghazela, Ariana",
  "foyer": {
    "idFoyer": 1
  }
}

# Vérifier l'association
GET http://localhost:8089/gestion-foyer/universite/retrieve-universite/1
```

#### Test 7: Associer Bloc à Foyer
```bash
# PUT - Mettre à jour le bloc avec son foyer
PUT http://localhost:8089/gestion-foyer/bloc/update-bloc
Content-Type: application/json

{
  "idBloc": 1,
  "nomBloc": "Bloc A",
  "capaciteBloc": 150,
  "foyer": {
    "idFoyer": 1
  }
}

# Même chose pour Bloc B
PUT http://localhost:8089/gestion-foyer/bloc/update-bloc
Content-Type: application/json

{
  "idBloc": 2,
  "nomBloc": "Bloc B",
  "capaciteBloc": 200,
  "foyer": {
    "idFoyer": 1
  }
}
```

#### Test 8: Associer Chambre à Bloc
```bash
# PUT - Mettre à jour les chambres avec leur bloc
PUT http://localhost:8089/gestion-foyer/chambre/update-chambre
Content-Type: application/json

{
  "idChambre": 1,
  "numeroChambre": 101,
  "typeC": "SIMPLE",
  "bloc": {
    "idBloc": 1
  }
}

# Chambres 102 et 103 dans Bloc A aussi
PUT http://localhost:8089/gestion-foyer/chambre/update-chambre
Content-Type: application/json

{
  "idChambre": 2,
  "numeroChambre": 102,
  "typeC": "DOUBLE",
  "bloc": {
    "idBloc": 1
  }
}
```

### Phase 4: Tests de Suppression

#### Test 9: Delete Operations
```bash
# 1. Supprimer un étudiant
DELETE http://localhost:8089/gestion-foyer/etudiant/remove-etudiant/3

# 2. Vérifier la suppression
GET http://localhost:8089/gestion-foyer/etudiant/retrieve-all-etudiants

# 3. Supprimer un bloc (attention aux relations!)
DELETE http://localhost:8089/gestion-foyer/bloc/remove-bloc/2

# 4. Supprimer un foyer (cascade sur les blocs!)
DELETE http://localhost:8089/gestion-foyer/foyer/remove-foyer/1
```

### Phase 5: Tests via Swagger UI

#### Accéder à Swagger
1. Ouvrir le navigateur: http://localhost:8089/gestion-foyer/swagger-ui.html
2. Explorer chaque contrôleur
3. Tester les endpoints avec l'interface graphique

#### Avantages de Swagger
- ✅ Interface graphique intuitive
- ✅ Documentation automatique
- ✅ Validation des schémas JSON
- ✅ Essayer directement les requêtes
- ✅ Voir les codes de réponse

### Phase 6: Vérification Base de Données

#### Requêtes SQL de Vérification
```sql
-- Vérifier les tables créées
SHOW TABLES;

-- Compter les enregistrements
SELECT COUNT(*) FROM universite;
SELECT COUNT(*) FROM foyer;
SELECT COUNT(*) FROM bloc;
SELECT COUNT(*) FROM chambre;
SELECT COUNT(*) FROM etudiant;
SELECT COUNT(*) FROM reservation;

-- Vérifier les relations
SELECT u.nom_universite, f.nom_foyer 
FROM universite u 
LEFT JOIN foyer f ON u.foyer_id_foyer = f.id_foyer;

SELECT b.nom_bloc, f.nom_foyer 
FROM bloc b 
JOIN foyer f ON b.foyer_id_foyer = f.id_foyer;

SELECT c.numero_chambre, c.typec, b.nom_bloc 
FROM chambre c 
JOIN bloc b ON c.bloc_id_bloc = b.id_bloc;
```

## ✅ Checklist de Tests

### Tests Fonctionnels
- [ ] Création d'entités simples
- [ ] Récupération de toutes les entités
- [ ] Récupération d'une entité par ID
- [ ] Mise à jour d'entités
- [ ] Suppression d'entités
- [ ] Création de relations OneToOne
- [ ] Création de relations OneToMany
- [ ] Création de relations ManyToOne
- [ ] Création de relations ManyToMany

### Tests de Validation
- [ ] Tester avec des données invalides
- [ ] Tester avec des IDs inexistants
- [ ] Tester les contraintes de clés étrangères
- [ ] Tester les cascades de suppression

### Tests de Performance
- [ ] Ajouter plusieurs entités en masse
- [ ] Récupérer de grandes listes
- [ ] Tester les requêtes avec jointures

## 🐛 Problèmes Courants et Solutions

### Erreur: "No identifier specified for entity"
**Solution**: Vérifier que @Id est présent dans toutes les entités

### Erreur: "could not execute statement"
**Solution**: Vérifier les contraintes de clés étrangères et l'ordre de création

### Erreur: "JSON parse error"
**Solution**: Vérifier le format JSON et les types de données

### Erreur: 404 Not Found
**Solution**: Vérifier l'URL et le context-path (/gestion-foyer)

### Erreur: LazyInitializationException
**Solution**: Ajouter @JsonIgnore sur les relations bidirectionnelles

## 📊 Résultats Attendus

### Après tous les tests
- Base de données avec toutes les tables créées
- Au moins 1 université
- Au moins 1 foyer
- 2+ blocs
- 3+ chambres
- 2+ étudiants
- Toutes les relations correctement établies

## 🎯 Prochaines Étapes

Après avoir validé tous ces tests:
1. Ajouter des méthodes de recherche personnalisées (JPQL)
2. Implémenter la gestion des réservations complète
3. Ajouter la validation des données (@Valid, @NotNull, etc.)
4. Implémenter la gestion des exceptions
5. Ajouter la pagination pour les listes
6. Créer des DTOs pour optimiser les transferts

---
**Bon courage pour vos tests! 🚀**