Tomodoro & Bongo Dino
Application de productivité avec timer Pomodoro et mini-jeu

Tomodoro est une application de gestion du temps basée sur la technique Pomodoro (25min travail + 5min pause), enrichie d'un mini-jeu Bongo Dino pour rendre les pauses ludiques et motivantes.

Fonctionnalités
Timer Pomodoro
Cycles automatiques Focus (25min) ↔ Break (5min)

Configuration des durées (1-60min travail, 1-30min pause)

Interface moderne dark theme avec animations fluides

Notifications de transition entre phases

Bongo Dino (Mini-jeu)
Jeu rythmique activé pendant les pauses

Sauvegarde des scores et high-scores par utilisateur

Intégration transparente avec le cycle Pomodoro

Gestion des utilisateurs
Authentification (email/mot de passe)

Historique des sessions de travail

Statistiques combinées (temps productif + scores jeu)

Architecture
text
Clean Architecture (Domain-Driven Design)
┌─────────────────┐    ┌──────────────────┐
│   adapter.in    │───▶│   application    │───▶
│  (REST/Swing)   │    │   (Use Cases)   │    
└─────────────────┘    └──────────────────┘    
        ▲                      ▲                
        │                      │                
┌─────────────────┐    ┌──────────────────┘
│  Domain Model   │◄───│  adapter.out     │
│ (Entités/Règles)│    │ (JPA/Swing UI)   │
└─────────────────┘    └──────────────────┘
Packages
domain.model : Entités métier (User, TomodoroSession, BongoGameSession)

domain.service : Règles métier et invariants

application.service : Orchestration des cas d'usage

adapter.in.swing : Interface utilisateur (Pomodoro UI + Bongo Dino)

adapter.out.persistence : Sauvegarde

# Livrables
Document	Statut	Description
DOMAIN_MODEL.md	-->	Modèle de domaine complet + Mermaid
PACKAGE_STRUCTURE.md --> Clean Architecture + règles de dépendances
DECISIONS.md --> 8 choix techniques justifiés
Installation & Lancement

# Prérequis
bash
Java 17+
Maven 3.8+
Git
Clone & Build
bash
git clone https://github.com/<username>/SI-JAVA-PROJECT-ESIEE.git
cd SI-JAVA-PROJECT-ESIEE
mvn clean compile
Lancer l'application
bash
# Interface complète (Pomodoro + Bongo Dino)
mvn spring-boot:run

# Ou directement via Swing (MVP)
java -cp target/classes com.tomodorobongodino.StudyWithMePomodoro
Accès : http://localhost:8080 (REST) | Swing : Lance directement

Modèle de domaine
text
classDiagram
    User ||--o{ TomodoroSession : possède
    User ||--o{ BongoGameSession : joue
    User ||--|| BongoHighScore : highscore
    User ||--o{ Task : crée
    Task ||--o{ TomodoroSession : associé
Entités principales : User, TomodoroSession, BongoGameSession, BongoHighScore

Décisions techniques (ADR)
Décision	Justification
Instant pour timestamps	UTC + thread-safe
TomodoroSessionType enum	Type-safety
Clean Architecture	Domain indépendant
Swing pour MVP	Pas de framework web lourd
Roadmap
text
Phase 1 : Domain Model + Pomodoro Swing
Phase 2 : Bongo Dino mini-jeu (canvas rythmique)
Phase 3 : Persistance JPA/H2 + authentification
Phase 4 : REST API + Stats dashboard
Phase 5 : Déploiement Heroku/Docker
Contribution
bash
# Branche feature
git checkout -b feature/bongo-dino-game

# Commit & PR
git commit -m "feat: add bongo dino rhythm game"
git push origin feature/bongo-dino-game

# Licence

Auteurs
Étudiant ESIEE Paris
Conforme TP SI-JAVA-PROJECT-ESIEE
