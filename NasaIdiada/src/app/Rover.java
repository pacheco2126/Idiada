package app;

import app.exceptions.RoverInstructionsFormatException;
import app.exceptions.RoverOutOfPlateauException;
import app.exceptions.RoverPositionFormatException;


public class Rover {
    // properties
    private int x;
    private int y;
    private char direction;
    private Plateau plateau;

    //constructor 
    public Rover(int x, int y, char direction, Plateau plateau) throws RoverPositionFormatException {
        validateDirection(direction);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;
    }

    //routine to make the rover moves within the grid
    public void move(char instruction) throws RoverOutOfPlateauException {
        switch (Character.toUpperCase(instruction)) {
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            case 'M':
                moveForward(plateau);
                break;
            default:
                // Ignore invalid instructions
                break;
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'E':
                direction = 'N';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'W':
                direction = 'S';
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }

    private void moveForward(Plateau plateau) throws RoverOutOfPlateauException {
        switch (direction) {
            case 'N':
                if (y + 1 <= plateau.getMaxY()) {
                    y++;
                } else {
                    throw new RoverOutOfPlateauException("Rover fell out of the plateau.");
                }
                break;
            case 'E':
                if (x + 1 <= plateau.getMaxX()) {
                    x++;
                } else {
                    throw new RoverOutOfPlateauException("Rover fell out of the plateau.");
                }
                break;
            case 'S':
                if (y - 1 >= 0) {
                    y--;
                } else {
                    throw new RoverOutOfPlateauException("Rover fell out of the plateau.");
                }
                break;
            case 'W':
                if (x - 1 >= 0) {
                    x--;
                } else {
                    throw new RoverOutOfPlateauException("Rover fell out of the plateau.");
                }
                break;
        }
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }

    public void executeInstructions(String instructions) throws RoverInstructionsFormatException, RoverOutOfPlateauException {
        for (char instruction : instructions.toCharArray()) {
            if (instruction != 'L' && instruction != 'R' && instruction != 'M') {
                throw new RoverInstructionsFormatException("Invalid instruction. Instructions must be 'L', 'R', or 'M'.");
            }
            move(instruction);
        }
    }

    private void validateDirection(char direction) throws RoverPositionFormatException {
        if (!(direction == 'N' || direction == 'S' || direction == 'W' || direction == 'E')) {
            throw new RoverPositionFormatException("Invalid direction. Direction must be N, S, W, or E.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDirection() {
        return direction;
    }
}
