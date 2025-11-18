-- Script SQL pour créer la base de données et un utilisateur
-- Ce script est optionnel car l'application crée automatiquement la base

-- Créer la base de données si elle n'existe pas
CREATE DATABASE IF NOT EXISTS gestion_foyer 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Utiliser la base de données
USE gestion_foyer;

-- Créer un utilisateur (optionnel)
-- CREATE USER IF NOT EXISTS 'foyer_user'@'localhost' IDENTIFIED BY 'foyer_password';
-- GRANT ALL PRIVILEGES ON gestion_foyer.* TO 'foyer_user'@'localhost';
-- FLUSH PRIVILEGES;

-- Vérifier les tables créées (après le premier lancement de l'application)
-- SHOW TABLES;

-- Exemples de requêtes pour vérifier les données
-- SELECT * FROM universite;
-- SELECT * FROM foyer;
-- SELECT * FROM bloc;
-- SELECT * FROM chambre;
-- SELECT * FROM etudiant;
-- SELECT * FROM reservation;