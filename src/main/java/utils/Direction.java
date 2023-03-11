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
}
