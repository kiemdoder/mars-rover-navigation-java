package kiemdoder.marsRoverNav;

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

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void turn(TurnDirection turnDirection) {
        direction = direction.turn(turnDirection);
    }

    public boolean move(Area area) { //TODO: other rovers?
        final Coordinates nextCoordinates = coordinates.move(direction);
        if (area.coordinatesOutside(nextCoordinates)) {
            return false;
        }
        coordinates = nextCoordinates;
        return true;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "coordinates=" + coordinates +
                ", direction=" + direction +
                '}';
    }

}
