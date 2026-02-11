Backlog Produit – Tomodoro & Bongo Dino
Acteurs
Visiteur : non connecté, découvre l’application et crée un compte
​

Utilisateur : connecté, utilise Tomodoro et Bongo Dino, consulte ses stats
​

Admin : supervision / modération (bonus, hors périmètre MVP)
​

Modules / Features
A. Authentification
​

B. Gestion des sessions Tomodoro
​

C. Mini‑jeu Bongo Dino
​

D. Statistiques & Gamification
​

User Stories (MoSCoW + estimation)
ID	Module	User Story	Priorité	Estim (S/M/L)
US‑01	Auth	En tant que Visiteur, je veux créer un compte afin de sauvegarder mes sessions Tomodoro et mes scores Bongo Dino.	Must	M
US‑02	Auth	En tant que Utilisateur, je veux me connecter afin de retrouver mon historique de travail et mes statistiques de jeu.	Must	M
US‑03	Auth	En tant que Utilisateur, je veux me déconnecter afin de sécuriser mon compte sur les postes partagés.	Should	S
US‑04	Tomodoro	En tant que Utilisateur, je veux configurer la durée de mes sessions de travail et de pause afin d’adapter Tomodoro à mon rythme de travail.	Must	M
US‑05	Tomodoro	En tant que Utilisateur, je veux lancer, mettre en pause et arrêter un minuteur Tomodoro afin de gérer précisément mes cycles de concentration.	Must	M
US‑06	Tomodoro	En tant que Utilisateur, je veux voir l’historique de mes sessions Tomodoro (nombre de sessions, durée totale de focus) afin de suivre ma progression dans le temps.	Should	M
US‑07	Tomodoro	En tant que Utilisateur, je veux associer chaque session Tomodoro à une tâche ou un projet afin de savoir sur quoi j’ai réellement travaillé.	Nice	M
US‑08	Bongo	En tant que Utilisateur, je veux lancer le mini‑jeu Bongo Dino pendant les pauses afin de me détendre sans quitter l’application Tomodoro.	Must	M
US‑09	Bongo	En tant que Utilisateur, je veux contrôler Bongo Dino avec des actions simples (clics ou touches) afin de jouer rapidement sans apprentissage complexe.	Should	S
US‑10	Bongo	En tant que Utilisateur, je veux gagner des points ou des bonus dans Bongo Dino en fonction de mes performances afin de rendre les pauses plus motivantes.	Nice	M
US‑11	Bongo	En tant que Utilisateur, je veux voir un tableau de mes meilleurs scores Bongo Dino afin de me challenger et suivre mon amélioration.	Nice	M
US‑12	Stats	En tant que Utilisateur, je veux voir un résumé combiné de mes sessions Tomodoro (temps de focus) et de mes scores Bongo Dino afin de visualiser l’équilibre entre travail et pauses ludiques.	Should	M
MVP recommandé : toutes les stories Must (US‑01, US‑02, US‑04, US‑05, US‑08), puis les principales Should (US‑03, US‑06, US‑09, US‑12) si le temps le permet.
​

Critères d’acceptation (exemples)
US‑01 – Création de compte
Given je suis Visiteur sur la page d’inscription, When je saisis un email valide et un mot de passe conforme, Then mon compte est créé et je suis redirigé vers la page de connexion.
​

Given un compte existe déjà avec cet email, When je tente de m’inscrire avec le même email, Then un message d’erreur « Email déjà utilisé » s’affiche et aucun nouveau compte n’est créé.
​

Given je saisis un mot de passe trop court, When je valide le formulaire, Then un message d’erreur indique que le mot de passe ne respecte pas les règles de sécurité.
​

US‑02 – Connexion
Given je possède un compte valide, When je saisis un email et un mot de passe corrects, Then je suis connecté et redirigé vers mon tableau de bord Tomodoro / Bongo Dino.
​

Given je saisis un mauvais mot de passe, When je valide le formulaire, Then un message d’erreur « Identifiants invalides » s’affiche et je reste déconnecté.
​

Given je suis connecté, When je rafraîchis la page, Then ma session reste active tant que je ne me suis pas déconnecté.
​

US‑03 – Déconnexion
Given je suis connecté, When je clique sur « Déconnexion », Then ma session est détruite et je suis redirigé vers la page de connexion ou d’accueil.
​

Given je me suis déconnecté, When j’essaie d’accéder à une page réservée aux Utilisateurs, Then je suis redirigé vers la page de connexion.
​

US‑04 – Configuration des durées Tomodoro
Given je suis connecté, When j’ouvre l’écran de configuration Tomodoro, Then je peux définir la durée d’une session de travail et d’une pause (par exemple 25/5 minutes).
​

Given j’ai sauvegardé une configuration, When je relance l’application ou me reconnecte, Then mes durées personnalisées sont réutilisées par défaut.
​

Given je saisis une valeur invalide (ex : négative ou trop élevée), When je sauvegarde, Then un message d’erreur m’empêche d’enregistrer cette configuration.
​

US‑05 – Lancer / pause / arrêt du minuteur
Given je suis sur l’écran Tomodoro, When je clique sur « Démarrer », Then un compte à rebours commence avec la durée de travail configurée.
​

Given un minuteur est en cours, When je clique sur « Pause », Then le compte à rebours se met en pause et peut reprendre là où il s’est arrêté.
​

Given un minuteur est en cours ou en pause, When je clique sur « Arrêter », Then la session actuelle se termine et le temps n’est plus comptabilisé.
​

US‑06 – Historique des sessions Tomodoro
Given j’ai réalisé plusieurs sessions Tomodoro, When j’ouvre la page d’historique, Then je vois le nombre total de sessions et la durée totale de focus.
​

Given j’ai réalisé au moins une session aujourd’hui, When je consulte l’historique, Then je peux identifier les sessions du jour et éventuellement filtrer par période (jour/semaine).
​

US‑07 – Association session ↔ tâche/projet
Given je configure une nouvelle session, When je choisis une tâche ou un projet dans une liste, Then la session Tomodoro est enregistrée avec cette tâche/projet.
​

Given plusieurs sessions sont associées à un même projet, When je consulte les détails du projet, Then je vois la somme de temps de focus passé dessus.
​

US‑08 – Lancer Bongo Dino pendant les pauses
Given un minuteur Tomodoro vient de terminer une session de travail, When la pause commence, Then un bouton ou un lien me permet de lancer Bongo Dino dans la même application.
​

Given je lance Bongo Dino depuis l’app, When je termine ma partie, Then je peux revenir à l’écran Tomodoro sans me reconnecter.
​

US‑09 – Contrôles simples de Bongo Dino
Given le jeu Bongo Dino est lancé, When j’appuie sur la touche ou le clic prévu (ex : espace ou clic gauche), Then le personnage réagit immédiatement (saut, action).
​

Given un nouvel utilisateur arrive sur le jeu, When il voit l’écran d’accueil, Then les contrôles basiques sont expliqués de manière claire en une phrase ou une petite zone d’aide.
​

US‑10 – Points / bonus dans Bongo Dino
Given je joue une partie de Bongo Dino, When j’évite des obstacles ou réussis des actions, Then mon score augmente selon des règles définies.
​

Given je termine une partie, When mon score est calculé, Then le score final s’affiche et peut être enregistré s’il fait partie de mes meilleurs résultats.
​

US‑11 – Tableau des meilleurs scores
Given j’ai joué plusieurs parties, When j’ouvre l’écran des meilleurs scores, Then je vois une liste ordonnée de mes meilleurs scores (ex : top 10).
​

Given un nouveau score est supérieur au plus faible du top, When je termine la partie, Then le classement est mis à jour automatiquement.
​

US‑12 – Résumé combiné Tomodoro + Bongo Dino
Given j’ai des sessions Tomodoro et des parties Bongo Dino enregistrées, When j’ouvre la page de statistiques, Then je vois au minimum mon temps total de focus et mon score moyen ou meilleur score Bongo Dino.
​

Given j’utilise l’application régulièrement, When je consulte ce résumé, Then je peux visualiser sur une période (par exemple la journée) le temps de travail vs le temps de jeu.
​