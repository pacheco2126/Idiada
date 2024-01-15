public class Rover {
    // properties
    private int x;
    private int y;
    private char direction;

    //constructor 
    public Rover(int x, int y, char direction) throws RoverPositionFormatException {
        validatePositionFormat(x, y, direction);
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    private void validatePositionFormat(int x, int y, char direction) throws RoverPositionFormatException {
        if (!(direction == 'N' || direction == 'S' || direction == 'W' || direction == 'E')) {
            throw new RoverPositionFormatException("Invalid direction. Direction must be N, S, W, or E.");
        }
        // You can add additional validation logic as needed
    }

    //routine to make the rover moves within the grid
    public void move(char instruction) {
        switch (Character.toUpperCase(instruction)) {
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            case 'M':
                moveForward();
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

    private void moveForward() {
        switch (direction) {
            case 'N':
                y++;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y--;
                break;
            case 'W':
                x--;
                break;
        }
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }

    public void executeInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            move(instruction);
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
