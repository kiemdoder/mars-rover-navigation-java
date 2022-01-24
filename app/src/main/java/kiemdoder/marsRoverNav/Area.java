package kiemdoder.marsRoverNav;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Area {
    private final int width;
    private final int height;

    private static final Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

    public Area(int maxX, int maxY) {
        this.width = maxX + 1;
        this.height = maxY + 1;
    }

    public static boolean isAreaTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

    public static Optional<Area> parse(String s) {
        final Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            final int maxX = Integer.parseInt(matcher.group(1));
            final int maxY = Integer.parseInt(matcher.group(2));
            return Optional.of(new Area(maxX, maxY));
        }

        return Optional.empty();
    }

    public boolean coordinatesOutside(Coordinates coordinates) {
        final int x = coordinates.getX();
        final int y = coordinates.getY();

        return x < 0 || y < 0 || x >= width || y >= height;
    }
}
