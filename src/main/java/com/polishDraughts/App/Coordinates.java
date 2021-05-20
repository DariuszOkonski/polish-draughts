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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void incrementX(int x) {
        this.x += x;
    }

    public void incrementY(int y) {
        this.y += y;
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

    public Coordinates[] getBasicMoves() {
        Coordinates[] possibleMoves = new Coordinates[4];
        possibleMoves[0] = new Coordinates(this.x+1, this.y-1);
        possibleMoves[1] = new Coordinates(this.x+1, this.y+1);
        possibleMoves[2] = new Coordinates(this.x-1, this.y+1);
        possibleMoves[3] = new Coordinates(this.x-1, this.y-1);
        return possibleMoves;
    }

    public Coordinates getDelta(Coordinates newPosition) {
        return new Coordinates(newPosition.getX() - x, newPosition.getY() - y);
    }

    public static Coordinates getHittingCoords(Coordinates attackingPawnPosition, Coordinates attackedPawnPosition) {
//        int directionX =  attackedPawnPosition.getX() - attackingPawnPosition.getX();
//        int directionY =  attackedPawnPosition.getY() - attackingPawnPosition.getY();
        Coordinates deltaCoordinates = attackingPawnPosition.getDelta(attackedPawnPosition);
        int hitX = attackedPawnPosition.getX() + deltaCoordinates.getX();
        int hitY = attackedPawnPosition.getY() + deltaCoordinates.getY();
        return new Coordinates(hitX, hitY);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

