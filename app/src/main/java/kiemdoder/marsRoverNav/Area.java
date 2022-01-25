package kiemdoder.marsRoverNav;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An area where the rovers can drive in.
 */
public class Area {
    private final int width;
    private final int height;

    private static final Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

    public Area(int maxX, int maxY) {
        this.width = maxX + 1;
        this.height = maxY + 1;
    }

    /**
     * Check if the text representation of the area is valid.
     */
    public static boolean isAreaTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

    /**
     * Create an area from text.
     */
    public static Optional<Area> parse(String s) {
        final Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            final int maxX = Integer.parseInt(matcher.group(1));
            final int maxY = Integer.parseInt(matcher.group(2));
            return Optional.of(new Area(maxX, maxY));
        }

        return Optional.empty();
    }

    /**
     * Check if the given coordinates falls outside the area.
     */
    public boolean coordinatesOutside(Coordinates coordinates) {
        final int x = coordinates.getX();
        final int y = coordinates.getY();

        return x < 0 || y < 0 || x >= width || y >= height;
    }
}
