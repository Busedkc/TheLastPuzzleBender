import java.awt.*;
import java.util.*;
import java.util.List;

//Class representing a node in the puzzle solving algorithm
class PuzzleNode implements Comparable<PuzzleNode> {
    //Data fields
	int[][] state;
    int g; // cost from start node to current node
    int h; // heuristic value (Manhattan distance)
    int f; // estimated total cost (f = g + h)
    PuzzleNode parent;

    //Constructor
    public PuzzleNode(int[][] state, int g, int h, PuzzleNode parent) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.parent = parent;
    }

    //Method to compare puzzle nodes based on their total cost 
    @Override
    public int compareTo(PuzzleNode other) {
        return Integer.compare(this.f, other.f);
    }
    
    @Override
    public String toString() {
        return "Cost Count: " + g + "\nHeuristic Count: " + h + "\nCost + Heuristic Count: " + f;
    }
}


//Class containing the logic to solve the 8 puzzle 
public class EightPuzzleSolver {
    //Constants for the goal state
    private static final int[][] GOAL_STATE = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};


    //Method to solve the puzzle and return the list of movements
    public static List<Movement> solvePuzzle(int[][] startState) {
    	//Initialize data structures for A* algorithm
        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>();
        Map<String, Integer> gScores = new HashMap<>();
        Map<String, PuzzleNode> closedSet = new HashMap<>();

        //Calculate heuristics for initial state
        int heuristic = calculateManhattanDistance(startState);
        int misplacedTilesHeuristic = calculateMisplacedTiles(startState); 
        PuzzleNode startNode = new PuzzleNode(startState, 0, heuristic + misplacedTilesHeuristic, null); 
        
        openSet.add(startNode);
        gScores.put(Arrays.deepToString(startState), 0);

        //A* algorithm loop 
        while (!openSet.isEmpty()) {
            PuzzleNode current = openSet.poll();
            System.out.println(current); // Printing the contents of the PuzzleNode at each step
            if (Arrays.deepEquals(current.state, GOAL_STATE)) {
                // Goal reached, reconstruct path
                List<PuzzleNode> path = new ArrayList<>();
                List<Movement> movements = new ArrayList<>();

                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);

                //Generate movements based on the path
                for (int i = 0; i < path.size() - 1; i++) {
                	   int[][] currentState = path.get(i).state;
                       int[][] nextState = path.get(i + 1).state;
                       movements.add(getMovement(currentState, nextState));
                   }
                   
                   //Print states and movements 
                   for (PuzzleNode node : path) {
                       printState(node.state);
                       System.out.println(node); // Printing the contents of the PuzzleNode at each step
                       System.out.println();
                   }
                      
                   return movements;
               }

               closedSet.put(Arrays.deepToString(current.state), current);

               // Generate successors
               for (Movement movement : Movement.values()) {
                   int newX = movement.getDeltaX() + findZeroX(current.state);
                   int newY = movement.getDeltaY() + findZeroY(current.state);

                   if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                       int[][] nextState = Arrays.stream(current.state).map(int[]::clone).toArray(int[][]::new);
                       int temp = nextState[newX][newY];
                       nextState[newX][newY] = 0;
                       nextState[findZeroX(current.state)][findZeroY(current.state)] = temp;

                       int newGScore = current.g + 1;
                       int newHScore = calculateManhattanDistance(nextState) + calculateMisplacedTiles(nextState); // Add Misplaced Tiles heuristic
                       int newFScore = newGScore + newHScore;

                       PuzzleNode successor = new PuzzleNode(nextState, newGScore, newHScore, current);

                       String stateString = Arrays.deepToString(successor.state);
                       if (closedSet.containsKey(stateString) && closedSet.get(stateString).f <= newFScore) {
                           continue;
                       }
                       
                       if (!gScores.containsKey(stateString) || newGScore < gScores.get(stateString)) {
                           openSet.add(successor);
                           gScores.put(stateString, newGScore);
                       }
                   }
               }
           }

           
           return null;
       }
    
      //Method to calculate Misplaced Tiles heuristic
      private static int calculateMisplacedTiles(int[][] state) {
    	  int misplacedTiles = 0;
    	  for (int i = 0; i < state.length; i++) {
    		  for (int j = 0; j < state[0].length; j++) {
    			  // Check if the current tile is not in its correct position (excluding the empty tile)
    			  if (state[i][j] != 0 && state[i][j] != i * state.length + j + 1) {
    				  misplacedTiles++;
                }
            }
        }
        return misplacedTiles;
    }


       //Method to calculate Manhattan distance heuristic
       private static int calculateManhattanDistance(int[][] state) {
           int distance = 0;
           for (int i = 0; i < state.length; i++) {
               for (int j = 0; j < state[0].length; j++) {
                   int value = state[i][j];
                   if (value != 0) {
                       int goalX = (value - 1) / 3;
                       int goalY = (value - 1) % 3;
                       distance += Math.abs(i - goalX) + Math.abs(j - goalY);
                   }
               }
           }
           return distance;
       }

       //Methods to find the position of the empty cell 
       private static int findZeroX(int[][] state) {
           for (int i = 0; i < state.length; i++) {
               for (int j = 0; j < state[0].length; j++) {
                   if (state[i][j] == 0) {
                       return i;
                   }
               }
           }
           return -1;
       }

       private static int findZeroY(int[][] state) {
           for (int i = 0; i < state.length; i++) {
               for (int j = 0; j < state[0].length; j++) {
                   if (state[i][j] == 0) {
                       return j;
                   }
               }
           }
           return -1;
       }

       //Methods to print the state of the puzzle
       private static void printState(int[][] state) {
           for (int[] rows : state) {
               for (int value : rows) {
                   System.out.print(value + " ");
               }
               System.out.println();
           }
       }

       //Method to determine the movement from one state to another
       private static Movement getMovement(int[][] currentState, int[][] nextState) {
           Point from = new Point(findZeroX(currentState), findZeroY(currentState));
           Point to = new Point(findZeroX(nextState), findZeroY(nextState));
           return Movement.findMovement(from, to);
       }
   }




