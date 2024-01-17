package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import  org.junit.jupiter.api.Test;

import app.Plateau;
import app.Rover;
import app.exceptions.RoverOutOfPlateauException;
import app.exceptions.RoverPositionFormatException;

public class MyTest {


    @Test
    public void testMoveForward() throws RoverOutOfPlateauException, RoverPositionFormatException {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 2, 'N', plateau);

        rover.move('M');
        assertEquals("1 3 N", rover.getPosition());

        rover.move('M');
        assertEquals("1 4 N", rover.getPosition());
    }

        @Test
    void testRoverPlacementOutOfBounds() {
        Plateau plateau = new Plateau(5, 5);
        assertThrows(RoverOutOfPlateauException.class, () -> {
            Rover rover = new Rover(6, 6, 'N', plateau); // Place the rover out of bounds
            plateau.placeRover(rover);
        });
    }

    @Test
    void testRoverMovementOutOfBounds() {
        Plateau plateau = new Plateau(5, 5);
        assertThrows(RoverOutOfPlateauException.class, () -> {
            Rover rover = new Rover(2, 2, 'N', plateau);
            plateau.placeRover(rover);

            // Move the rover out of bounds
            rover.executeInstructions("MMMMMMMMMMMMMMMMMMMM"); 
        });
    }
    @Test
    void testRoverIncorrectInput() {
        Plateau plateau = new Plateau(5, 5);
        assertThrows(RoverPositionFormatException.class, () -> {
            // Incorrect input for rover position
            Rover rover = new Rover(2, 2, 'X', plateau); // as 'X' is an invalid direction
        });
    }

}

