package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MarsTest {

    @Test
    void invalidInstructions() {
        String instructions1 = """
                2 3
                0 0 N
                """;
        assertFalse(Mars.driveRovers(instructions1).isPresent());

        String instructions2 = """
                10 10
                0 0 N
                MM
                0 5 N
                MLM
                3 3 W
                """;
        assertFalse(Mars.driveRovers(instructions2).isPresent());

        String instructions3 = """
                10 10
                0 0 H
                MM
                """;
        assertFalse(Mars.driveRovers(instructions3).isPresent());

        String instructions4 = """
                10
                0 0 N
                MM
                """;
        assertFalse(Mars.driveRovers(instructions4).isPresent());
    }

    @Test
    void driveRovers() {
        String instructions = """
                5 5
                1 2 N
                LMLMLMLMM
                3 3 E
                MMRMMRMRRM
                """;
        Optional<List<Rover>> rovers = Mars.driveRovers(instructions);
        assertTrue(rovers.isPresent());
        rovers.get().forEach(System.out::println);
        assertEquals(rovers.get().get(0).toString(), "1 3 N");
        assertEquals(rovers.get().get(1).toString(), "5 1 E");

        String instructions2 = """
                3 3
                0 0 N
                MRMLMRMLM
                3 3 S
                MMMLM
                """;
        Optional<List<Rover>> rovers2 = Mars.driveRovers(instructions2);
        assertTrue(rovers2.isPresent());
        rovers2.get().forEach(System.out::println);
        assertEquals(rovers2.get().get(0).toString(), "2 3 N");
        assertEquals(rovers2.get().get(1).toString(), "3 0 E");
    }
}
