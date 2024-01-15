import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
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
                // Input for rover
                System.out.print("Enter rover position (format: x y direction): ");
                int x = readInt(scanner);
                int y = readInt(scanner);
                char direction = readDirection(scanner);

                Rover rover = new Rover(x, y, direction);
                plateau.placeRover(rover);

                // Display the plateau with the rover
                plateau.displayPlateau();

                // Input for instructions
                System.out.print("Enter instructions for the rover: ");
                String instructions = scanner.next();
                validateInstructions(instructions);
                rover.executeInstructions(instructions);

                // Display the plateau after the rover moves
                plateau.initializeGrid();
                plateau.placeRover(rover);
                plateau.displayPlateau();

                // Ask if there is another rover to set up
                System.out.print("Do you want to set up another rover? (yes/no): ");
                String response = scanner.next().toLowerCase();
                setupAnotherRover = response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
                plateau.initializeGrid();
            }
        } catch (PlateauSizeFormatException | RoverPositionFormatException | RoverInstructionsFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
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

    private static void validateInstructions(String instructions) throws RoverInstructionsFormatException {
        for (char instruction : instructions.toCharArray()) {
            if (instruction != 'L' && instruction != 'R' && instruction != 'M') {
                throw new RoverInstructionsFormatException("Invalid instruction. Instructions must be 'L', 'R', or 'M'.");
            }
        }
    }
}
