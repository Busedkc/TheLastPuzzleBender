
//private static final int[][] MOVES = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

import java.awt.*;

//Enum representing different movements 
public enum Movement {
    D(1, 0),  //down
    U(-1, 0), //up
    R(0, 1),  //right
    L(0, -1); //left

    private final int deltaX;
    private final int deltaY;

    Movement(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    //Method to find the  movement from one point to another
    public static Movement findMovement(Point from, Point to) {
        Point delta = new Point(to.x - from.x, to.y - from.y);
        System.out.println(delta);
        for (Movement movement : Movement.values()) {
            if (movement.getDeltaXY().equals(delta)) {
                return movement;
            }
        }
        return null;
    }

    //Getter methods for delta values
    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    //Method to get delta as a point
    public Point getDeltaXY() {
        return new Point(getDeltaX(), getDeltaY());
    }
}

