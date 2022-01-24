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

    public static boolean isRoverTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

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
