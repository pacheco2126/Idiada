
public class Plateau {
    private int maxX;
    private int maxY;
    private char[][] grid;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.grid = new char[maxY][maxX];
        initializeGrid();
    }

    public void initializeGrid() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                grid[i][j] = '.';
            }
        }
    }
  

    public void placeRover(Rover rover) throws RoverOutOfPlateauException {
        int x = rover.getX();
        int y = rover.getY();

        if (x < 0 || x >= maxX || y < 0 || y >= maxY) {
            throw new RoverOutOfPlateauException("Rover fell out of the plateau.");
        }

        char roverSymbol = getDirectionSymbol(rover.getDirection());
        grid[y][x] = roverSymbol;
    }
    

    private char getDirectionSymbol(char direction) {
        switch (direction) {
            case 'N':
                return '^';
            case 'E':
                return '>';
            case 'S':
                return 'v';
            case 'W':
                return '<';
            default:
                return '?';
        }
    }

    public void displayPlateau() {
        for (int i = maxY - 1; i >= 0; i--) {
            for (int j = 0; j < maxX; j++) {
                char symbol = grid[i][j];
                    System.out.print(" " + symbol + " ");
            }
            System.out.println();
        }
    }
    

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
