package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {

    @Test
    void move() {
        final Coordinates start = new Coordinates(15, 5);
        assertEquals(start.move(Direction.North), new Coordinates(15, 6));
        assertEquals(start.move(Direction.East), new Coordinates(16, 5));
        assertEquals(start.move(Direction.South), new Coordinates(15, 4));
        assertEquals(start.move(Direction.West), new Coordinates(14, 5));
    }
}
