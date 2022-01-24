package kiemdoder.marsRoverNav;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NavigationInstructionsTest {

    @Test
    void isInstructionsTextFormat() {
        assertTrue(NavigationInstructions.isInstructionsTextFormat("RLMMLRM"));
        assertFalse(NavigationInstructions.isInstructionsTextFormat("RLMXMLRM"));
    }

    @Test
    void driveRover() {
        Rover rover = new Rover(new Coordinates(0, 0), Direction.North);
        NavigationInstructions navigationInstructions = new NavigationInstructions("MRMLM");
        navigationInstructions.driveRover(rover, new Area(2, 3), Collections.emptyList());
        assertEquals(rover.getCoordinates(), new Coordinates(1, 2));
    }

    @Test
    void driveRoverWithCollision() {
        Rover rover = new Rover(new Coordinates(0, 0), Direction.North);
        NavigationInstructions navigationInstructions = new NavigationInstructions("MRMLMRMLM");
        assertFalse(navigationInstructions.driveRover(rover, new Area(2, 3), List.of(new Rover(new Coordinates(2, 2), Direction.North))));
        assertEquals(rover.getCoordinates(), new Coordinates(1, 2));

    }
}
