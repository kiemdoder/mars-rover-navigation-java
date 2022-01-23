package kiemdoder.marsRoverNav;

import java.util.Set;
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

    public boolean driveRover(Rover rover, Area area, Set<Rover> otherRovers) {
        for (char c : instructions.toCharArray()) {
            switch (c) {
                case 'R', 'L':
                    rover.turn(TurnDirection.fromSymbol(c));
                    break;

                case 'M':
                    rover.move(area);
            }
        }

        return true;
    }
}
