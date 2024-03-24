import lib.StdDraw;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setScale(0.5, 3.5);
        StdDraw.enableDoubleBuffering();

        // Generate a random start state for the puzzle
        int[][] startState = generateRandomStartState();
        Board board = new Board(startState);
        List<Movement> movements = null; // Initialize movements to null

        // Draw the initial state of the puzzle
        System.out.println("Initial State:");
        printState(startState);
        board.draw();
        StdDraw.show();

        // Perform calculations upon first run
        movements = EightPuzzleSolver.solvePuzzle(startState);
        if (movements == null) {
            System.out.println("No solution found.");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to start solving the puzzle...");
            scanner.nextLine(); // Wait for user to press Enter

            // Animation loop to visualize movements
            for (Movement movement : movements) {
                board.move(movement);
                board.draw();
                StdDraw.pause(100);
                StdDraw.show();
            }

            // Print movements and step count
            StringBuilder movementString = new StringBuilder();
            int stepCount = 0;
            for (Movement movement : movements) {
                movementString.append(movement.name());
                stepCount++;
            }
            System.out.println("Movements: " + movementString.toString());
            System.out.println("Number of steps: " + stepCount);
        }
    }

    private static int[][] generateRandomStartState() {
        int[] flatStartState = randomShuffling(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
        int[][] startState = new int[3][3];
        for (int i = 0; i < startState.length; i++) {
            System.arraycopy(flatStartState, 3 * i, startState[i], 0, startState[i].length);
        }
        return startState;
    }

    private static int[] randomShuffling(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = (int) (Math.random() * array.length);
            if (i != randIndex) {
                int temp = array[i];
                array[i] = array[randIndex];
                array[randIndex] = temp;
            }
        }
        return array;
    }

    // Method to print the state of the puzzle
    private static void printState(int[][] state) {
        for (int[] rows : state) {
            for (int value : rows) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
