Backlog Produit corrigé – Tomodoro & Bongo Dino
Corrections apportées :

Uniformisation des accents et majuscules (ex. "Utilisateur").

Traduction complète des critères d'acceptation en français avec Étant donné / Quand / Alors.

Table propre, format Markdown optimisé pour Jira/Miro.

Suppression des espaces inutiles, cohérence orthographique.

Acteurs
Visiteur : non connecté, découvre l’application et crée un compte.

Utilisateur : connecté, utilise Tomodoro et Bongo Dino, consulte ses stats.

Admin : supervision / modération (bonus, hors périmètre MVP).
​

Modules / Features
A. Authentification

B. Gestion des sessions Tomodoro

C. Mini-jeu Bongo Dino

D. Statistiques & Gamification

User Stories (MoSCoW + estimation)
ID	Module	User Story	Priorité	Estim (S/M/L)
US-01	Auth	En tant que Visiteur, je veux créer un compte afin de sauvegarder mes sessions Tomodoro et mes scores Bongo Dino.	Must	M
US-02	Auth	En tant qu’Utilisateur, je veux me connecter afin de retrouver mon historique de travail et mes statistiques de jeu.	Must	M
US-03	Auth	En tant qu’Utilisateur, je veux me déconnecter afin de sécuriser mon compte sur les postes partagés.	Should	S
US-04	Tomodoro	En tant qu’Utilisateur, je veux configurer la durée de mes sessions de travail et de pause afin d’adapter Tomodoro à mon rythme de travail.	Must	M
US-05	Tomodoro	En tant qu’Utilisateur, je veux lancer, mettre en pause et arrêter un minuteur Tomodoro afin de gérer précisément mes cycles de concentration.	Must	M
US-06	Tomodoro	En tant qu’Utilisateur, je veux voir l’historique de mes sessions Tomodoro (nombre de sessions, durée totale de focus) afin de suivre ma progression dans le temps.	Should	M
US-07	Tomodoro	En tant qu’Utilisateur, je veux associer chaque session Tomodoro à une tâche ou un projet afin de savoir sur quoi j’ai réellement travaillé.	Nice	M
US-08	Bongo	En tant qu’Utilisateur, je veux lancer le mini-jeu Bongo Dino pendant les pauses afin de me détendre sans quitter l’application Tomodoro.	Must	M
US-09	Bongo	En tant qu’Utilisateur, je veux contrôler Bongo Dino avec des actions simples (clics ou touches) afin de jouer rapidement sans apprentissage complexe.	Should	S
US-10	Bongo	En tant qu’Utilisateur, je veux gagner des points ou des bonus dans Bongo Dino en fonction de mes performances afin de rendre les pauses plus motivantes.	Nice	M
US-11	Bongo	En tant qu’Utilisateur, je veux voir un tableau de mes meilleurs scores Bongo Dino afin de me challenger et suivre mon amélioration.	Nice	M
US-12	Stats	En tant qu’Utilisateur, je veux voir un résumé combiné de mes sessions Tomodoro (temps de focus) et de mes scores Bongo Dino afin de visualiser l’équilibre entre travail et pauses ludiques.	Should	M
MVP recommandé : Toutes les stories Must (US-01, US-02, US-04, US-05, US-08), puis les principales Should (US-03, US-06, US-09, US-12) si le temps le permet.
​

Critères d’acceptation (format Étant donné / Quand / Alors)
US-01 – Création de compte
Étant donné que je suis Visiteur sur la page d’inscription, quand je saisis un email valide et un mot de passe conforme, alors mon compte est créé et je suis redirigé vers la page de connexion.

Étant donné qu’un compte existe déjà avec cet email, quand je tente de m’inscrire avec le même email, alors un message d’erreur « Email déjà utilisé » s’affiche et aucun nouveau compte n’est créé.

Étant donné que je saisis un mot de passe trop court, quand je valide le formulaire, alors un message d’erreur indique que le mot de passe ne respecte pas les règles de sécurité.
​

US-02 – Connexion
Étant donné que je possède un compte valide, quand je saisis un email et un mot de passe corrects, alors je suis connecté et redirigé vers mon tableau de bord Tomodoro / Bongo Dino.

Étant donné que je saisis un mauvais mot de passe, quand je valide le formulaire, alors un message d’erreur « Identifiants invalides » s’affiche et je reste déconnecté.

Étant donné que je suis connecté, quand je rafraîchis la page, alors ma session reste active tant que je ne me suis pas déconnecté.

US-03 – Déconnexion
Étant donné que je suis connecté, quand je clique sur « Déconnexion », alors ma session est détruite et je suis redirigé vers la page de connexion ou d’accueil.

Étant donné que je me suis déconnecté, quand j’essaie d’accéder à une page réservée aux Utilisateurs, alors je suis redirigé vers la page de connexion.

US-04 – Configuration des durées Tomodoro
Étant donné que je suis connecté, quand j’ouvre l’écran de configuration Tomodoro, alors je peux définir la durée d’une session de travail et d’une pause (par exemple 25/5 minutes).

Étant donné que j’ai sauvegardé une configuration, quand je relance l’application ou me reconnecte, alors mes durées personnalisées sont réutilisées par défaut.

Étant donné que je saisis une valeur invalide (ex. : négative ou trop élevée), quand je sauvegarde, alors un message d’erreur m’empêche d’enregistrer cette configuration.

US-05 – Lancer / pause / arrêt du minuteur
Étant donné que je suis sur l’écran Tomodoro, quand je clique sur « Démarrer », alors un compte à rebours commence avec la durée de travail configurée.

Étant donné qu’un minuteur est en cours, quand je clique sur « Pause », alors le compte à rebours se met en pause et peut reprendre là où il s’est arrêté.

Étant donné qu’un minuteur est en cours ou en pause, quand je clique sur « Arrêter », alors la session actuelle se termine et le temps n’est plus comptabilisé.

US-06 – Historique des sessions Tomodoro
Étant donné que j’ai réalisé plusieurs sessions Tomodoro, quand j’ouvre la page d’historique, alors je vois le nombre total de sessions et la durée totale de focus.

Étant donné que j’ai réalisé au moins une session aujourd’hui, quand je consulte l’historique, alors je peux identifier les sessions du jour et éventuellement filtrer par période (jour/semaine).

US-07 – Association session ↔ tâche/projet
Étant donné que je configure une nouvelle session, quand je choisis une tâche ou un projet dans une liste, alors la session Tomodoro est enregistrée avec cette tâche/projet.

Étant donné que plusieurs sessions sont associées à un même projet, quand je consulte les détails du projet, alors je vois la somme de temps de focus passé dessus.

US-08 – Lancer Bongo Dino pendant les pauses
Étant donné qu’un minuteur Tomodoro vient de terminer une session de travail, quand la pause commence, alors un bouton ou un lien me permet de lancer Bongo Dino dans la même application.

Étant donné que je lance Bongo Dino depuis l’app, quand je termine ma partie, alors je peux revenir à l’écran Tomodoro sans me reconnecter.

US-09 – Contrôles simples de Bongo Dino
Étant donné que le jeu Bongo Dino est lancé, quand j’appuie sur la touche ou le clic prévu (ex. : espace ou clic gauche), alors le personnage réagit immédiatement (saut, action).

Étant donné qu’un nouvel utilisateur arrive sur le jeu, quand il voit l’écran d’accueil, alors les contrôles basiques sont expliqués de manière claire en une phrase ou une petite zone d’aide.

US-10 – Points / bonus dans Bongo Dino
Étant donné que je joue une partie de Bongo Dino, quand j’évite des obstacles ou réussis des actions, alors mon score augmente selon des règles définies.

Étant donné que je termine une partie, quand mon score est calculé, alors le score final s’affiche et peut être enregistré s’il fait partie de mes meilleurs résultats.

US-11 – Tableau des meilleurs scores
Étant donné que j’ai joué plusieurs parties, quand j’ouvre l’écran des meilleurs scores, alors je vois une liste ordonnée de mes meilleurs scores (ex. : top 10).

Étant donné qu’un nouveau score est supérieur au plus faible du top, quand je termine la partie, alors le classement est mis à jour automatiquement.

US-12 – Résumé combiné Tomodoro + Bongo Dino
Étant donné que j’ai des sessions Tomodoro et des parties Bongo Dino enregistrées, quand j’ouvre la page de statistiques, alors je vois au minimum mon temps total de focus et mon score moyen ou meilleur score Bongo Dino.

Étant donné que j’utilise l’application régulièrement, quand je consulte ce résumé, alors je peux visualiser sur une période (par exemple la journée) le temps de travail vs le temps de jeu.
