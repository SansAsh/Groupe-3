Classes principales
PomodoroSession

Attributs :

durationMinutes : int (ex : 25)

remainingSeconds : int

state : PomodoroState (enum : RUNNING, PAUSED, FINISHED)

Méthodes :

start()

pause()

reset()

tick() (appelée chaque seconde pour décrémenter)

TimerService

Attributs :

currentSession : PomodoroSession

Méthodes :

startSession(PomodoroSession)

stopSession()

onTick() (logique d’appel périodique, par exemple avec ScheduledExecutor)

BongoCatAnimation

Attributs :

currentAnimationState : BongoCatState (enum : IDLE, TAPPING, CELEBRATE…)

animationSpeed : double

Méthodes :

playIdle()

playTapping()

playCelebrate()

updateFromSession(PomodoroSession) (change l’animation selon l’état du timer)

UserSettings

Attributs :

workDurationMinutes : int

breakDurationMinutes : int

soundEnabled : boolean

bongoCatEnabled : boolean

Méthodes :

load()

save()

NotificationService

Méthodes :

notifySessionStarted()

notifySessionFinished()

Relations UML (texte)
TimerService 1 — 1 PomodoroSession

Association : TimerService gère une session en cours.

PomodoroSession 1 — 1..1 BongoCatAnimation

Dépendance : BongoCatAnimation.updateFromSession(session) utilise l’état du pomodoro pour changer l’animation.

UserSettings *1 — PomodoroSession

Une configuration utilisateur sert à créer plusieurs sessions (durée travail/pause).

TimerService 1 — 1..1 NotificationService

TimerService appelle NotificationService quand une session commence / se termine.

Enums :

PomodoroState { RUNNING, PAUSED, FINISHED }

BongoCatState { IDLE, TAPPING, CELEBRATE }

Version PlantUML (copier-coller)
Tu peux coller ça dans https://www.plantuml.com/plantuml/ :

text
@startuml

enum PomodoroState {
  RUNNING
  PAUSED
  FINISHED
}

enum BongoCatState {
  IDLE
  TAPPING
  CELEBRATE
}

class PomodoroSession {
  - durationMinutes : int
  - remainingSeconds : int
  - state : PomodoroState
  + start()
  + pause()
  + reset()
  + tick()
}

class TimerService {
  - currentSession : PomodoroSession
  + startSession(PomodoroSession)
  + stopSession()
  + onTick()
}

class BongoCatAnimation {
  - currentAnimationState : BongoCatState
  - animationSpeed : double
  + playIdle()
  + playTapping()
  + playCelebrate()
  + updateFromSession(PomodoroSession)
}

class UserSettings {
  - workDurationMinutes : int
  - breakDurationMinutes : int
  - soundEnabled : boolean
  - bongoCatEnabled : boolean
  + load()
  + save()
}

class NotificationService {
  + notifySessionStarted()
  + notifySessionFinished()
}

TimerService --> PomodoroSession : gère >
PomodoroSession --> BongoCatAnimation : met à jour >
TimerService --> NotificationService : utilise >
UserSettings "1" --> "many" PomodoroSession : configure >
