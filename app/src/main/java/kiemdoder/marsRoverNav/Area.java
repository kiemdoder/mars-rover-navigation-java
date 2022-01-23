package kiemdoder.marsRoverNav;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Area {
    private final int width;
    private final int height;

    private static final Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static boolean isAreaTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

    public static Optional<Area> parse(String s) {
        final Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            final int w = Integer.parseInt(matcher.group(1));
            final int h = Integer.parseInt(matcher.group(2));
            return Optional.of(new Area(w, h));
        }

        return Optional.empty();
    }

    public boolean coordinatesOutside(Coordinates coordinates) {
        final int x = coordinates.getX();
        final int y = coordinates.getY();

        return x < 0 || y < 0 || x >= width || y >= height;
    }
}
