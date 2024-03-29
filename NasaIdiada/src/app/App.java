package app;
import java.util.Scanner;

import app.exceptions.PlateauSizeFormatException;
import app.exceptions.RoverInstructionsFormatException;
import app.exceptions.RoverOutOfPlateauException;
import app.exceptions.RoverPositionFormatException;


public class App {
    public static void main(String[] args) throws PlateauSizeFormatException, RoverPositionFormatException, RoverInstructionsFormatException, RoverOutOfPlateauException {
        
        Scanner scanner = new Scanner(System.in);

            // Input for plateau size
            System.out.print("Enter plateau size (format: maxX maxY): ");
            int maxX = readInt(scanner);
            int maxY = readInt(scanner);

            if (maxX <= 0 || maxY <= 0) {
                throw new PlateauSizeFormatException("Plateau size must be positive integers.");
            }

            Plateau plateau = new Plateau(maxX, maxY);
            plateau.displayPlateau();

            boolean setupAnotherRover = true;

            while (setupAnotherRover) {
                try {
                    // Input for rover
                    System.out.print("Enter rover position (format: x, y, direction (N,S,E,W)): ");
                    int x = readInt(scanner);
                    int y = readInt(scanner);
                    char direction = readDirection(scanner);
    
                    Rover rover = new Rover(x, y, direction, plateau);
                    plateau.placeRover(rover);
    
                    // Display the plateau with the rover
                    plateau.displayPlateau();
    
                    // Input for instructions
                    System.out.print("Enter instructions for the rover (format: L, R, M): ");
                    String instructions = scanner.next().toUpperCase();
                    rover.executeInstructions(instructions);
    
                    // Display the plateau after the rover moves
                    plateau.initializeGrid();
                    plateau.placeRover(rover);
                    plateau.displayPlateau();
                    System.out.println("The final rover position is: " + rover.getPosition());
    
                    // Ask if there is another rover to set up
                    System.out.print("Do you want to set up another rover? (yes/no): ");
                    String response = scanner.next().toLowerCase();
                    setupAnotherRover = response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
                    plateau.initializeGrid();
                } catch (RoverPositionFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
    
            scanner.close();
        }
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter the right format.");
            }
        }
    }

    private static char readDirection(Scanner scanner) {
        while (true) {
            String input = scanner.next().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'N' || input.charAt(0) == 'S' || input.charAt(0) == 'W' || input.charAt(0) == 'E')) {
                return input.charAt(0);
            } else {
                System.out.println("Invalid direction. Please enter N, S, W, or E.");
            }
        }
    }
}
