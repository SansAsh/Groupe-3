
***

## 3. Fichier `docs/DECISIONS.md`

```md
# DECISIONS - Tomodoro & Bongo Dino

## D‑01 - Gestion des rôles

- Décision : pas d’entité Role séparée pour le MVP, on utilise un enum `UserRole` ou un simple rôle par défaut `USER`.
- Justification : réduit la complexité pour L2, suffisant pour les US actuelles.
- Impact : si plus tard on veut gérer plusieurs rôles par user, on pourra introduire une entité Role.

## D‑02 - Modélisation des sessions Tomodoro

- Décision : utiliser l’entité `TomodoroSession` avec un type (FOCUS/BREAK) plutôt que deux entités séparées.
- Justification : les informations sont très proches, un champ type suffit.
- Impact : plus simple à requêter pour les stats.

## D‑03 - Historique Bongo Dino

- Décision : stocker chaque partie dans `BongoGameSession` et garder un `BongoHighScore` par utilisateur.
- Justification : permet à la fois le détail et un accès rapide au meilleur score.
- Impact : update du high score à la fin de chaque partie.

## D‑04 - Association sessions ↔ tâches

- Décision : pour le MVP, on garde `taskLabel` dans `TomodoroSession` (String) plutôt qu’une entité Task complète.
- Justification : suffit pour US‑07 (“savoir sur quoi j’ai travaillé”) sans sur-modéliser.
- Impact : si besoin d’une vraie gestion de tâches, on créera plus tard l’entité Task et la relation.

## D‑05 - Types de date/heure

- Décision : utiliser `Instant` pour les dates/horaires.
- Justification : compatible avec l’API Java moderne, évite les ambiguïtés de fuseau horaire côté serveur.
- Impact : nécessite une conversion côté front si affichage local requis.

