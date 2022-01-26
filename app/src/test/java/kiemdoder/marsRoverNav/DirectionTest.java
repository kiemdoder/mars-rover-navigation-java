package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void turn() {
        assertEquals(Direction.North.turn(TurnDirection.Right), Direction.East);
        assertEquals(Direction.North.turn(TurnDirection.Left), Direction.West);
    }
}
