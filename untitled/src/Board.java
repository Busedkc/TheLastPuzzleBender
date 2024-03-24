
//Importing the necessary libraries
import lib.StdDraw; // the StdDraw class in the lib package is used for drawings 
import java.awt.Color; // used for coloring the board
import java.awt.Point; // used for the positions of the tiles and the empty cell

//Class representing the game
class Board {
	//Constants for colors and line thickness
    private static final Color backgroundColor = new Color(145, 234, 255);
    private static final Color boxColor = new Color(31, 160, 239);
    private static final double lineThickness = 0.02;
    //2D array to store tiles 
    private final Tile[][] tiles = new Tile[3][3];
    //Variables to track the position of the empty cell 
    private int emptyCellRow, emptyCellCol;

    //Constructor to initialize the board with a given state 
    public Board(int[][] state) {
    	//Loop through the state to create tiles and find the empty cell
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                if (state[row][col] != 0)
                    tiles[row][col] = new Tile(state[row][col]);
                else {
                    emptyCellRow = row;
                    emptyCellCol = col;
                }
            }
    }

    //Method to move tiles based on the given movement
    public boolean move(Movement movement) {
        return switch (movement) {
            case D -> moveDown();
            case U -> moveUp();
            case R -> moveRight();
            case L -> moveLeft();
        };
    }

    //Methods to move the empty cell in different directions
    boolean moveRight() {
        if (emptyCellCol == 2)
            return false;
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol + 1];
        tiles[emptyCellRow][emptyCellCol + 1] = null;
        emptyCellCol++;
        return true;
    }

    boolean moveLeft() {
        if (emptyCellCol == 0)
            return false;
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow][emptyCellCol - 1];
        tiles[emptyCellRow][emptyCellCol - 1] = null;
        emptyCellCol--;
        return true;
    }

    boolean moveUp() {
        if (emptyCellRow == 0)
            return false;
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow - 1][emptyCellCol];
        tiles[emptyCellRow - 1][emptyCellCol] = null;
        emptyCellRow--;
        return true;
    }

    boolean moveDown() {
        if (emptyCellRow == 2)
            return false;
        tiles[emptyCellRow][emptyCellCol] = tiles[emptyCellRow + 1][emptyCellCol];
        tiles[emptyCellRow + 1][emptyCellCol] = null;
        emptyCellRow++;
        return true;
    }

    //Method to draw the board
    public void draw() {
        StdDraw.clear(backgroundColor);
        //Loop through tiles to draw them
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                if (tiles[row][col] == null)
                    continue;
                //Get position of the tile and draw it 
                Point tilePosition = getTilePosition(row, col);
                tiles[row][col].draw(tilePosition.x, tilePosition.y);
            }
        
        //Draw the board boundaries
        StdDraw.setPenColor(boxColor);
        StdDraw.setPenRadius(lineThickness);
        StdDraw.square(2, 2, 1.5);
        StdDraw.setPenRadius();
    }

    //Method to get position of a tile
    private Point getTilePosition(int rowIndex, int columnIndex) {
        int posX = columnIndex + 1, posY = 3 - rowIndex;
        return new Point(posX, posY);
    }
}
