package kiemdoder.marsRoverNav;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Mars {

    private enum States {ReadArea, ReadRover, DriveRover}

    public static boolean driveRovers(final String instructions) {
        Rover currentRover = new Rover(new Coordinates(0, 0), Direction.North);
        List<Rover> rovers = new ArrayList<>();
        Area plateau = new Area(0, 0);

        States state = States.ReadArea;
        String[] lines = instructions.lines().toArray(String[]::new);
        
        if (lines.length < 3 || (lines.length >=3 && (lines.length % 2 == 0))) {
            System.out.println("Invalid instructions text");
            return false;
        }
        
        for (String line : lines) {
            if (state == States.ReadArea) {
                Optional<Area> area = Area.parse(line);
                if (area.isPresent()) {
                    plateau = area.get();
                    state = States.ReadRover;
                } else {
                    System.out.println("Invalid area: " + line);
                    return false;
                }
            } else if (state == States.ReadRover) {
                Optional<Rover> rover = Rover.parse(line);
                if (rover.isPresent()) {
                    currentRover = rover.get();
                    rovers.add(currentRover);
                    state = States.DriveRover;
                } else {
                    System.out.println("Invalid rover: " + line);
                    return false;
                }
            } else if (NavigationInstructions.isInstructionsTextFormat(line)){
                NavigationInstructions navigationInstructions = new NavigationInstructions(line);
                navigationInstructions.driveRover(currentRover, plateau, rovers);
            } else {
                System.out.println("Invalid navigation instructions: " + line);
                return false;
            }
        };

        return true;
    }
}
