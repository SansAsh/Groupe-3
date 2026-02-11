# Domain Model - Tomodoro & Bongo Dino

## Acteurs

- Visiteur : non connecté, peut créer un compte.
- Utilisateur : connecté, utilise Tomodoro, joue à Bongo Dino, consulte ses stats.
- (Optionnel) Admin : supervision / modération (hors MVP).

## Cas d’usage (résumé)

- UC‑01 : En tant que Visiteur, je veux créer un compte afin de sauvegarder mes sessions Tomodoro et scores Bongo Dino. (US‑01)
- UC‑02 : En tant que Utilisateur, je veux me connecter afin de retrouver mon historique de travail et mes statistiques de jeu. (US‑02)
- UC‑03 : En tant que Utilisateur, je veux gérer un minuteur Tomodoro (configurer durées, lancer, pause, stop). (US‑04, US‑05)
- UC‑04 : En tant que Utilisateur, je veux consulter l’historique de mes sessions Tomodoro. (US‑06)
- UC‑05 : En tant que Utilisateur, je veux lancer et jouer au mini‑jeu Bongo Dino pendant les pauses. (US‑08, US‑09)
- UC‑06 : En tant que Utilisateur, je veux consulter mes meilleurs scores Bongo Dino. (US‑11)
- UC‑07 : En tant que Utilisateur, je veux voir un résumé combiné Tomodoro + Bongo Dino. (US‑12)
- UC‑08 (Nice) : En tant que Utilisateur, je veux associer mes sessions à des tâches/projets. (US‑07)
- UC‑09 (Nice) : En tant que Utilisateur, je veux gagner des points/bonus dans Bongo Dino. (US‑10)

## Entités

### User

- Attributs :
  - id : Long
  - email : String (unique)
  - username : String (unique recommandé)
  - passwordHash : String
  - createdAt : Instant
- Règles :
  - email obligatoire, au bon format, unique.
  - passwordHash obligatoire (à partir de la séance sécurité).
  - un utilisateur peut avoir des sessions Tomodoro et des scores Bongo Dino.

### TomodoroSession

Représente une session de travail/pause. [page:2]

- Attributs :
  - id : Long
  - user : User
  - type : TomodoroSessionType (FOCUS ou BREAK)
  - durationMinutes : int
  - startedAt : Instant
  - endedAt : Instant (optionnel si en cours)
  - taskLabel : String (optionnel, étiquette de tâche / projet – pour US‑07)
- Règles :
  - durée > 0.
  - startedAt obligatoire, endedAt après startedAt si présent.
  - toujours liée à un User.

### BongoGameSession

Représente une partie de Bongo Dino. [page:2]

- Attributs :
  - id : Long
  - user : User
  - startedAt : Instant
  - endedAt : Instant
  - score : int
- Règles :
  - score ≥ 0.
  - startedAt obligatoire, endedAt après startedAt.
  - liée à un User.

### BongoHighScore

Optionnel, mais pratique pour le classement. [page:2]

- Attributs :
  - id : Long
  - user : User
  - bestScore : int
  - lastUpdatedAt : Instant
- Règles :
  - bestScore ≥ 0.
  - pour un user donné, au plus un enregistrement de high score.

### Task (Nice to have – pour US‑07)

Si vous modélisez l’association sessions ↔ tâche/projet. [page:2]

- Attributs :
  - id : Long
  - user : User (propriétaire)
  - label : String
  - description : String (optionnel)
- Règles :
  - label obligatoire, longueur maximale définie (ex : 80).

### StatsSummary (concept)

Concept métier pour US‑12 (pas forcément entité persistée, peut être un DTO ou service). [page:2]

- Données calculées :
  - totalFocusMinutes : int
  - totalBreakMinutes : int
  - numberOfBongoGames : int
  - bestBongoScore : int
  - période : jour / semaine / mois.

## Relations (cardinalités)

- User 1..N TomodoroSession  
  Un utilisateur possède plusieurs sessions Tomodoro, une session appartient à un seul utilisateur.

- User 1..N BongoGameSession  
  Un utilisateur peut jouer plusieurs parties de Bongo Dino.

- User 1..1 BongoHighScore (optionnel)  
  Au plus un high score par utilisateur.

- User 1..N Task (si Task utilisée)  
  Un utilisateur crée/possède plusieurs tâches.

- Task 1..N TomodoroSession (optionnel)  
  Une tâche peut être liée à plusieurs sessions Tomodoro, une session est au plus liée à une tâche.

## Enums

- TomodoroSessionType : FOCUS, BREAK
- UserRole (si besoin) : USER, ADMIN

## Règles métier

### User

- email obligatoire, unique.
- un compte ne peut être utilisé que s’il est activé (optionnel).
- suppression d’un user : sessions et scores doivent être gérés (anonymisation ou cascade – à décider plus tard).

### TomodoroSession

- une session FOCUS doit avoir type = FOCUS, une session de pause type = BREAK.
- les durées par défaut (ex : 25 / 5 minutes) peuvent être configurées par l’utilisateur (stockage ailleurs ou dans des settings, à préciser plus tard).
- une session terminée ne peut plus être modifiée (sauf lecture).

### BongoGameSession / BongoHighScore

- fin de partie Bongo : le score est calculé et stocké.
- si score > bestScore, alors mise à jour de BongoHighScore pour l’utilisateur.
- les parties de Bongo sont en principe lancées pendant une pause (BREAK), mais ce lien peut rester logique (pas forcément technique).

## Diagramme de classes (Mermaid)

```mermaid
classDiagram
    class User {
      +Long id
      +String email
      +String username
      +String passwordHash
      +Instant createdAt
    }

    class TomodoroSession {
      +Long id
      +TomodoroSessionType type
      +int durationMinutes
      +Instant startedAt
      +Instant endedAt
      +String taskLabel
    }

    class BongoGameSession {
      +Long id
      +Instant startedAt
      +Instant endedAt
      +int score
    }

    class BongoHighScore {
      +Long id
      +int bestScore
      +Instant lastUpdatedAt
    }

    class Task {
      +Long id
      +String label
      +String description
    }

    enum TomodoroSessionType {
      FOCUS
      BREAK
    }

    User "1" --> "0..*" TomodoroSession : has
    User "1" --> "0..*" BongoGameSession : plays
    User "1" --> "0..1" BongoHighScore : owns
    User "1" --> "0..*" Task : owns
    Task "1" --> "0..*" TomodoroSession : linked


###RAF Lien modèle ↔ backlog