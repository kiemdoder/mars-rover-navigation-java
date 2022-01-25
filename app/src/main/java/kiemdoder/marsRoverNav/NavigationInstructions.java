package kiemdoder.marsRoverNav;

import java.util.List;
import java.util.regex.Pattern;

/**
 * The navigation instructions for driving a rover.
 */
public class NavigationInstructions {

    private static final Pattern pattern = Pattern.compile("[RLM]+");

    private final String instructions;

    public NavigationInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Check if the text representation of the instructions is valid.
     */
    public static boolean isInstructionsTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

    /**
     * Use this navigation instructions to drive a rover.
     * @param rover The rover that will follow this instructions.
     * @param area The area that the rover can drive in.
     * @param otherRovers The other rovers in the area.
     * @return True if the rover could follow all the instructions and did not get blocked by the edge of the area or by
     * bumping into another rover.
     */
    public boolean driveRover(Rover rover, Area area, List<Rover> otherRovers) {
        for (char c : instructions.toCharArray()) {
            switch (c) {
                case 'R', 'L':
                    rover.turn(TurnDirection.fromSymbol(c));
                    break;

                case 'M':
                    if (!rover.move(area, otherRovers)) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }
}
