package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsTest {

    @Test
    void invalidInstructions() {
        String instructions1 = """
                2 3
                0 0 N
                """;
        assertFalse(Mars.driveRovers(instructions1));

        String instructions2 = """
                10 10
                0 0 N
                MM
                0 5 N
                MLM
                """;
        assertFalse(Mars.driveRovers(instructions1));
    }

    @Test
    void driveRovers() {
    }
}
