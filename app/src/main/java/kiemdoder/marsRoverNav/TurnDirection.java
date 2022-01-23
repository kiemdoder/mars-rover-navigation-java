package kiemdoder.marsRoverNav;

public enum TurnDirection {
    Right,
    Left,
    Unknown;

    public static TurnDirection fromSymbol(Character symbol) {
        return switch (symbol) {
            case 'R' -> Right;
            case 'L' -> Left;
            default -> Unknown;
        };
    }
}
