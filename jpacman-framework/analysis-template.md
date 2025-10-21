# Specification-based Testing

## Exercice 1 -> Clyde nextAiMove()
## 1. Goal, inputs and outputs
- Goal: **Trouver la nouvelle position de Clyde dans le niveau**
- Input domain: **This -> clyde (position actuelle), PacMan (position du joueur)**
- Output domain: **direction ou null (optionnel)** aka la nouvelle position de clyde

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions 
- Clyde est proche du joueur (distance < 8)
- Clyde est loin du joueur (distance > 8)
- Clyde est exactement à la distance de pacman (distance = 8)


- Le joueur n'est pas sur le niveau
- Le joueur n'a pas de casse 
- Le joueur est sur les bords du plateau

Obstacle direction partitions:
- Path of Clyde is free
- Path of Clyde is blocked
- Clyde is on pacman
- Two equivalent paths

Not valid maps:
- Multiple clydes on the map
- Multiple player on the map

#### Individual inputs


#### Combinations of input values

### Output partitions

## 4. Identify boundaries
- Clyde est à la distance limite entre fuire et poursuivre Pacman
## 5. Select test cases

Distance < 8:
- T1: Path free => Direction away from Pac-man
- T2: Path blocked => Empty direction
- T3: Multiple moves => Direction away from Pac-man

Distance > 8:
- T1: Path free => Direction away from Pac-man
- T2: Path blocked => Empty direction
- T3: Multiple moves => Direction away from Pac-man

Tester seul les cas limites 