package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void isRoverTextFormat() {
        assertTrue(Rover.isRoverTextFormat("4 33 S"));
        assertFalse(Rover.isRoverTextFormat("4 33S"));
        assertFalse(Rover.isRoverTextFormat("4 3"));
    }

    @Test
    void parse() {
        final Optional<Rover> rover_ = Rover.parse("5 12 N");
        assertTrue(rover_.isPresent());

        final Rover rover = rover_.get();
        assertEquals(rover.getCoordinates(), new Coordinates(5, 12));
        assertEquals(rover.getDirection(), Direction.North);
    }

    @Test
    void turn() {
        final Rover rover = new Rover(new Coordinates(0, 0), Direction.North);
        rover.turn(TurnDirection.Right);
        assertEquals(rover.getDirection(), Direction.East);
        rover.turn(TurnDirection.Right);
        assertEquals(rover.getDirection(), Direction.South);
    }

    @Test
    void move() {
        final Area area = new Area(2, 2);
        final Rover rover = new Rover(new Coordinates(0, 0), Direction.North);
        assertTrue(rover.move(area));
        assertEquals(rover.getCoordinates(), new Coordinates(0, 1));

        rover.turn(TurnDirection.Right);
        assertTrue(rover.move(area));
        assertEquals(rover.getCoordinates(), new Coordinates(1, 1));

        assertFalse(rover.move(area));

        //TODO: test bumping into other rovers
    }
}
