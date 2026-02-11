## 2. Fichier `docs/PACKAGE_STRUCTURE.md`

```md
# Package Structure - Tomodoro & Bongo Dino

## Objectif

Séparer clairement le **domaine**, l’**application**, l’**API** et l’**infrastructure** pour garder un code maintenable et testable. [page:2]

## Structure proposée

```text
com.esiee.tomodoro
  ├── domain
  │   ├── model          # entités métier (User, TomodoroSession, BongoGameSession, BongoHighScore, Task)
  │   ├── enums          # TomodoroSessionType, UserRole (optionnel)
  │   └── exception      # exceptions métier (plus tard)
  │
  ├── application
  │   ├── service        # orchestration des cas d’usage (TomodoroService, BongoService, StatsService, AuthService)
  │   └── port           # interfaces côté domaine/application (ex: TomodoroSessionRepositoryPort) - optionnel
  │
  ├── api
  │   ├── controller     # REST controllers (AuthController, TomodoroController, BongoController, StatsController)
  │   └── dto            # DTO pour requêtes/réponses API
  │
  └── infrastructure
      ├── persistence    # implémentations des repository (JPA), mapping DB (séances suivantes)
      └── config         # configuration (Sécurité, Spring config, etc.)
