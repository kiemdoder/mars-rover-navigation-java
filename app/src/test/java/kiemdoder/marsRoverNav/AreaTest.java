package kiemdoder.marsRoverNav;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AreaTest {

    @Test
    void isAreaTextFormat() {
        assertTrue(Area.isAreaTextFormat("4 12"));
        assertFalse(Area.isAreaTextFormat("4 12 N"));
    }

    @Test
    void parse() {
        final Optional<Area> area_ = Area.parse("31 3");
        assertTrue(area_.isPresent());
    }

    @Test
    void coordinatesOutside() {
        final Area area = new Area(5, 2);
        assertTrue(area.coordinatesOutside(new Coordinates(3, 3)));
        assertTrue(area.coordinatesOutside(new Coordinates(6, 1)));
        assertTrue(area.coordinatesOutside(new Coordinates(3, -1)));
        assertTrue(area.coordinatesOutside(new Coordinates(-1, 1)));

        assertFalse(area.coordinatesOutside(new Coordinates(3, 1)));
        assertFalse(area.coordinatesOutside(new Coordinates(3, 2)));
        assertFalse(area.coordinatesOutside(new Coordinates(5, 2)));
        assertFalse(area.coordinatesOutside(new Coordinates(3, 0)));
        assertFalse(area.coordinatesOutside(new Coordinates(0, 2)));
    }
}
