package kiemdoder.marsRoverNav;

public enum Direction {
    North('N') ,
    East('E'),
    South( 'S'),
    West('W'),
    Unknown('U');

    public final char symbol;

    Direction(char symbol) {
        this.symbol = symbol;
    }

    public static Direction fromSymbol(Character symbol) {
        return switch (symbol) {
            case 'N' -> North;
            case 'E' -> East;
            case 'S' -> South;
            case 'W' -> West;
            default -> Unknown;
        };
    }

    /**
     * Produce a new direction after applying the turn operation to this direction.
     */
    public Direction turn(TurnDirection turnDirection) {
        if (turnDirection == TurnDirection.Right) {
            return switch (this) {
                case North -> Direction.East;
                case East -> Direction.South;
                case South -> Direction.West;
                case West -> Direction.North;
                default -> this;
            };
        } else if (turnDirection == TurnDirection.Left) {
            return switch (this) {
                case North -> Direction.West;
                case East -> Direction.North;
                case South -> Direction.East;
                case West -> Direction.South;
                default -> this;
            };
        } else {
            return this;
        }
    }
}
