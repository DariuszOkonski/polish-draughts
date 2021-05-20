package com.polishDraughts.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            possibleMoves[0] = getNextBottomLeft();
            possibleMoves[1] = getNextBottomRight();
        } else {
            possibleMoves[0] = getNextTopLeft();
            possibleMoves[1] = getNextTopRight();
        }
        return possibleMoves;
    }

    public Coordinates[] getBasicMoves() {
        Coordinates[] possibleMoves = new Coordinates[4];
        possibleMoves[0] = getNextTopLeft();
        possibleMoves[1] = getNextTopRight();
        possibleMoves[2] = getNextBottomRight();
        possibleMoves[3] = getNextBottomLeft();
        return possibleMoves;
    }

    Coordinates getNextTopLeft(){
        return new Coordinates(getX()-1, getY()-1);
    }
    Coordinates getNextTopRight(){
        return new Coordinates(getX()-1, getY()+1);
    }
    Coordinates getNextBottomRight(){
        return new Coordinates(getX()+1, getY()+1);
    }
    Coordinates getNextBottomLeft(){
        return new Coordinates(getX()+1, getY()-1);
    }

    public List<List<Coordinates>> getBasicMovesForCrowned() {
//        List<Coordinates> movesList = new ArrayList<>();
//        List<Coordinates[]> movesList = new ArrayList<Coordinates[]>();
        List<List<Coordinates>> movesList = new ArrayList<>();

        // top left corner
        movesList.add(getTopLeftCoordinates());

        // top right corner
       movesList.add(getTopRightCoordinates());

        // bottom right corner
       movesList.add(getBottomRightCoordinates());

       // bottom left corner
        movesList.add(getBottomLeftCoordinates());



       return  movesList;
    }

    List<Coordinates> getTopLeftCoordinates() {//top lef corner
        List<Coordinates> movesList = new ArrayList<>();
        Coordinates currentCoordinates = new Coordinates(this.getX(), this.getY());
        boolean nextCoordInArrayRange = true;
        do {
            if (currentCoordinates.x == 0 || currentCoordinates.y == 0){
                nextCoordInArrayRange = false;
                continue;
            }
            movesList.add(currentCoordinates.getNextTopLeft());
            currentCoordinates.x--;
            currentCoordinates.y--;
//            if (currentCoordinates.x >= 0 && currentCoordinates.y >= 0) {
        } while (nextCoordInArrayRange);
        return movesList;
    }

    List<Coordinates> getTopRightCoordinates() {//top lef corner
        List<Coordinates> movesList = new ArrayList<>();
        Coordinates currentCoordinates = new Coordinates(this.getX(), this.getY());
        boolean nextCoordInArrayRange = true;

        do {
            if (currentCoordinates.x == 0 || currentCoordinates.y >= Board.INSTANCE.getSize()-1){
                nextCoordInArrayRange = false;
                continue;
            }
            movesList.add(currentCoordinates.getNextTopRight());
            currentCoordinates.x--;
            currentCoordinates.y++;

        } while (nextCoordInArrayRange);
        return movesList;
    }

    List<Coordinates> getBottomLeftCoordinates() {//top lef corner
        List<Coordinates> movesList = new ArrayList<>();
        Coordinates currentCoordinates = new Coordinates(this.getX(), this.getY());
        boolean nextCoordInArrayRange = true;

        do {
            if (currentCoordinates.x >= Board.INSTANCE.getSize()-1 || currentCoordinates.y == 0){
                nextCoordInArrayRange = false;
                continue;
            }
            movesList.add(currentCoordinates.getNextBottomLeft());
            currentCoordinates.x++;
            currentCoordinates.y--;

        } while (nextCoordInArrayRange);
        return movesList;
    }

    List<Coordinates> getBottomRightCoordinates() {//top lef corner
        List<Coordinates> movesList = new ArrayList<>();
        Coordinates currentCoordinates = new Coordinates(this.getX(), this.getY());
        boolean nextCoordInArrayRange = true;

        do {
            if (currentCoordinates.x >= Board.INSTANCE.getSize()-1 || currentCoordinates.y >= Board.INSTANCE.getSize()-1){
                nextCoordInArrayRange = false;
                continue;
            }
            movesList.add(currentCoordinates.getNextBottomRight());
            currentCoordinates.x++;
            currentCoordinates.y++;
        } while (nextCoordInArrayRange);
        return movesList;
    }














    public Coordinates getDelta(Coordinates newPosition) {
        return new Coordinates(newPosition.getX() - x, newPosition.getY() - y);
    }

    public static Coordinates getHittingMove(Coordinates attackingPawnPosition, Coordinates attackedPawnPosition) {
//        int directionX =  attackedPawnPosition.getX() - attackingPawnPosition.getX();
//        int directionY =  attackedPawnPosition.getY() - attackingPawnPosition.getY();
        Coordinates deltaCoordinates = attackingPawnPosition.getDelta(attackedPawnPosition);
        int hitX = attackedPawnPosition.getX() + deltaCoordinates.getX();
        int hitY = attackedPawnPosition.getY() + deltaCoordinates.getY();
        return new Coordinates(hitX, hitY);
    }



//    @Override
//    public String toString() {
//        return "Coordinates{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }
    public boolean equals(Coordinates c) {
        return this.x == c.getX() && this.y == c.getY();
    }

    @Override
    public String toString() {
        try {
            return "{ " +
                    "" + Util.getAlphabet()[y] +
                    "" + (x + 1) +
                    " }";
        }catch (IndexOutOfBoundsException err) {
            return "No string";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

