# TheLastPuzzleBender #
The code solves the puzzle using the A* method and visualizes each step.


# Design Overview and Code Explanation #
The program is divided into several classes, each responsible for different aspects of the puzzle-solving process. The main classes include Board, PuzzleNode, EightPuzzleSolver, Main, and Tile.

# 1. Board Class: 
The Board class manages the game board, facilitating initialization, tile movement, drawing, and empty cell tracking. It utilizes a 2D array of Tile objects to represent the board state, with a separate tracking of the empty cell's position. Methods are provided for tile movement in different directions (moveRight, moveLeft, moveUp, moveDown), along with private helper methods (getTilePosition) to calculate tile positions. Additionally, the class utilizes the StdDraw library to visually represent the board.

# 2. Tile Class:
The Tile class represents a single tile on the game board. Each tile has a number and can draw itself on the board. This class is responsible for rendering tiles with appropriate colors and numbers using StdDraw. It has a constructor to set the tile number. The draw method renders the tile with appropriate colors and numbers using StdDraw. I have not added anything to this class.

# 3. PuzzleNode and EightPuzzleSolver Class: 
The PuzzleNode class represents a node in the search tree used by the A* algorithm. It contains the state of the puzzle, the cost from the start node (g), the heuristic value (h), the total estimated cost (f), and a reference to the parent node. This class also implements the Comparable interface to allow comparison based on the total cost. The EightPuzzleSolver class contains the logic for solving the 8-puzzle problem using the A* algorithm with Manhattan distance and Misplaced Tiles heuristics. It includes methods to calculate heuristics, generate successor states, and perform the A* search. This class interfaces with the PuzzleNode class to represent states and solutions. Methods include solvePuzzle, calculateManhattanDistance, calculateMisplacedTiles, findZeroX, findZeroY, and getMovement.

# 5. Main Class: 
The Main class serves as the entry point for the program. It initializes the game board, generates a random initial state, solves the puzzle using the EightPuzzleSolver class, and visualizes the solution by animating the movements on the board.

# Movement:
This code defines a Movement enum that specifies the direction of the movement and how many units to move in the direction of the movement. The enum represents four different movements (UP, DOWN, LEFT, RIGHT) and the amount of change of each movement on the X and Y axis. It also provides the findMovement method to determine the direction of movement between two points.

# Data Structures #
The game board is represented using a 2D array of Tile objects in the Board class. States of the puzzle are represented using 2D arrays of integers. A priority queue (PriorityQueue) is used in the EightPuzzleSolver class to store open nodes during the A* search. Hash maps (HashMap) store g-scores and closed nodes for efficient lookup.

# Conclusion #
In conclusion, the Eight Puzzle Solver is a well-designed Java program that effectively solves the classic 8-puzzle problem using the A* algorithm with Manhattan distance and Misplaced Tiles heuristics. The program demonstrates object-oriented principles, efficient data structures, and effective use of external libraries for visualization. It provides a clear separation of concerns between classes, making the code modular and maintainable.

