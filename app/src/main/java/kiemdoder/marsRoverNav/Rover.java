package kiemdoder.marsRoverNav;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rover {
    private Coordinates coordinates;
    private Direction direction;

    private static final Pattern pattern = Pattern.compile("(\\d+) (\\d+) ([NWSE])");

    public Rover(Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    /**
     * Check if the text representation of the rover is valid.
     */
    public static boolean isRoverTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

    /**
     * Create a rover from its text representation.
     * @return An optional rover object. The optional will be empty if the text representation of the rover was invalid.
     */
    public static Optional<Rover> parse(String s) {
        final Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            final int x = Integer.parseInt(matcher.group(1));
            final int y = Integer.parseInt(matcher.group(2));
            final char dir = matcher.group(3).charAt(0);
            return Optional.of(new Rover(new Coordinates(x, y), Direction.fromSymbol(dir)));
        }

        return Optional.empty();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turn(TurnDirection turnDirection) {
        direction = direction.turn(turnDirection);
    }

    /**
     * Move the rover one step in the direction it is facing. The rover will only move when there is no other rover
     * in the way or when the move would take it outside the area the rover can drive in.
     * @param area The allowable area that the rover can drive in.
     * @param otherRovers A list of all the rovers. This is used to check for collisions with other rovers.
     * @return True if the rover could move. When a collision with another rover would have occurred or the rover
     * would have moved outside the allowable area this method will return false.
     */
    public boolean move(Area area, List<Rover> otherRovers) {
        if (!canMove(area, otherRovers)) {
            return false;
        }

        final Coordinates nextCoordinates = coordinates.move(direction);
        if (area.coordinatesOutside(nextCoordinates)) {
            return false;
        }
        coordinates = nextCoordinates;
        return true;
    }

    /**
     * Check if the rover would be able to move.
     * @param area The allowable area that the rover can drive in.
     * @param otherRovers A list of all the rovers. This is used to check for collisions with other rovers.
     * @return True if the rover is not obstructed by other rovers or on the edge of the area.
     */
    public boolean canMove(Area area, List<Rover> otherRovers) {
        final Coordinates nextCoordinates = coordinates.move(direction);
        if (area.coordinatesOutside(nextCoordinates)) {
            System.out.println("Reached the end of the plateau");
            return false;
        }
        if (otherRovers.stream().noneMatch(rover -> rover.getCoordinates().equals(nextCoordinates))) {
            return true;
        } else {
            System.out.println("Bumped into another rover");
            return false;
        }
    }

    @Override
    public String toString() {
        return coordinates + " " + direction.symbol;
    }
}
