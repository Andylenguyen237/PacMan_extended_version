# PacMan in Multiverse

### Background
The classic arcade game PacMan has been around since the 1980’s. It is a maze action video game
where a player controls the PacMan character through an enclosed maze to eat all the pills (small
white dots) placed in the maze while avoiding ghosts. PacMan was a widespread critical and commercial
success, but die-hard players need something new

### Proposed Extension
* Feature 1: New Types of Monsters 
  * Orion
  * Alien
  * Wizard

* Feature 2: Additional Capabilities of Items
  * When PacMan eats a gold piece, all the monsters get furious and move faster. The monsters
    determine the moving direction once based on their walking approach and move towards that
    direction for 2 cells if they can. Otherwise, determining the new direction again using their own
    walking approach until it can move by 2 cells. After 3 seconds, all the monsters will be back to move
    normally using their own walking approach
  * When PacMan eats an ice cube,
    1. Regardless of being normal or furious, all the monsters are frozen (i.e., stop moving) for 3
    seconds. Then, they will be back to move normally using their own walking approach.
    2. While the monsters are frozen, PacMan can eat a gold piece without making the monsters
    furious

### Software Model and Diagrams (documentation file)
* Proposed optimise model and design that lower coupling and increase cohesion in the game implementation
* 3 diagrams include:
  * Domain Model
  * Static Design Model
  * Dynamic Design Model (State Machine)



