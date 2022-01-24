package kiemdoder.marsRoverNav;

import java.util.List;
import java.util.regex.Pattern;

public class NavigationInstructions {

    private static final Pattern pattern = Pattern.compile("[RLM]+");

    private final String instructions;

    public NavigationInstructions(String instructions) {
        this.instructions = instructions;
    }

    public static boolean isInstructionsTextFormat(String s) {
        return pattern.matcher(s).matches();
    }

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
