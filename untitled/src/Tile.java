import lib.StdDraw; // the StdDraw class in the lib package is used for drawings 

import java.awt.Color; // used for coloring the tile and the number on it
import java.awt.Font; // used for setting the font to show the number on the tile

//Class representing a tile in the puzzle 
public class Tile {
   //Constants for colors, line thickness, and font
   private static final Color tileColor = new Color(15, 76, 129);
   private static final Color numberColor = new Color(31, 160, 239);
   private static final Color boxColor = new Color(31, 160, 239);
   private static final double lineThickness = 0.01;
   private static final Font numberFont = new Font("Arial", Font.BOLD, 50);
   private int number; // the number on the tile (an instance variable)

   // A constructor that creates a tile with a given number
   public Tile(int number) {
      // set the number based on the given value
      this.number = number;
   }

   // A method for drawing the tile centered on a given position
   public void draw(int posX, int posY) {
      // draw the tile as a filled square
      StdDraw.setPenColor(tileColor);
      StdDraw.filledSquare(posX, posY, 0.5);
      // draw the bounding box of the tile as a square
      StdDraw.setPenColor(boxColor);
      StdDraw.setPenRadius(lineThickness);
      StdDraw.square(posX, posY, 0.5);
      StdDraw.setPenRadius(); // reset pen radius to its default value
      // draw the number on the tile
      StdDraw.setPenColor(numberColor);
      StdDraw.setFont(numberFont);
      StdDraw.text(posX, posY, String.valueOf(number));
   }
}