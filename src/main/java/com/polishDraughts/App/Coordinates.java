package com.polishDraughts.App;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates[] getBasicMoves(boolean isWhite) {
        Coordinates[] possibleMoves = new Coordinates[2];
        if (isWhite) {
            possibleMoves[0] = new Coordinates(this.x+1, this.y-1);
            possibleMoves[1] = new Coordinates(this.x+1, this.y+1);
        } else {
            possibleMoves[0] = new Coordinates(this.x-1, this.y+1);
            possibleMoves[1] = new Coordinates(this.x-1, this.y-1);
        }
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

