package kiemdoder.marsRoverNav;

import java.util.Objects;

//TODO: Change to new Java record type.
public class Coordinates {
    private final int x;
    private final int y;


    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Create a new set of coordinates after moving from this one.
     * @param direction The direction of movement.
     */
    public Coordinates move(Direction direction) {
        return switch (direction) {
            case North -> new Coordinates(x, y + 1);
            case East -> new Coordinates(x + 1, y);
            case South -> new Coordinates(x, y - 1);
            case West -> new Coordinates(x - 1, y);
            default -> this;
        };
    }
}
