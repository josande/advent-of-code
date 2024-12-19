package utils;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    public Direction left() {
        switch (this) {
            case NORTH -> {return Direction.WEST; }
            case EAST  -> {return Direction.NORTH; }
            case SOUTH -> {return Direction.EAST; }
            case WEST  -> {return Direction.SOUTH; }
            default -> throw new IllegalStateException("Unknown direction");
        }
    }
    public Direction right() {
        switch (this) {
            case NORTH -> {return Direction.EAST; }
            case EAST  -> {return Direction.SOUTH; }
            case SOUTH -> {return Direction.WEST; }
            case WEST  -> {return Direction.NORTH; }
            default -> throw new IllegalStateException("Unknown direction");
        }
    }
    public Direction reverse() {
        switch (this) {
            case NORTH -> {
                return Direction.SOUTH;
            }
            case EAST -> {
                return Direction.WEST;
            }
            case SOUTH -> {
                return Direction.NORTH;
            }
            case WEST -> {
                return Direction.EAST;
            }
            default -> throw new IllegalStateException("Unknown direction");
        }
    }
    public static Direction of(Character c) {
        return switch (c) {
            case '^', 'U', 'u', 'N', 'n' -> Direction.NORTH;
            case '>', 'R', 'r', 'E', 'e' -> Direction.EAST;
            case 'v', 'D', 'd', 'S', 's' -> Direction.SOUTH;
            case '<', 'L', 'l', 'W', 'w' -> Direction.WEST;
            default -> throw new IllegalStateException("Unknown character for direction!");
        };
    }
}
