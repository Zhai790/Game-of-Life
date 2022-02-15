# Game-of-Life
The game occurs on a 2D grid 
Each square has possibility of containing a cell
2 possible cell states: dead or alive
  - Dead cells automatically removed from grid
  - Live cells = *
Cell state dependent on interaction with neighbouring (adjacent) cells
  - >3 neighbours = cell dies due to overpopulation
  - 2-3 neighbours = cell survives to new generation
  - <3 neighbours = cell dies due to underpopulation
  - 3 live neighbours = new cell produced
