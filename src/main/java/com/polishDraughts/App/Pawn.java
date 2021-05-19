package com.polishDraughts.App;
import java.util.stream.IntStream;



public class Pawn {

    private Coordinates position;

    private boolean isCrowned;
    private boolean isWhite;
    private String character;

    public Pawn(Coordinates position, boolean isWhite) {
        this.position = position;
        this.isCrowned = false;
        this.isWhite = isWhite;
        this.setCharacter();
        //TODO - to remove
        this.setCrowned(true);
    }

    public void movePawn(Coordinates newPosition) {
        Board.INSTANCE.clearField(position);
        Game.INSTANCE.checkForHit(position, newPosition);
        position = newPosition;

        if (Game.INSTANCE.isPlayerHasHit()) {
            placePawnOnBoard();
            Game.INSTANCE.setPawnAfterHitting(this);
        } else {
            Game.INSTANCE.checkIfPawnToCrowned(this);
            placePawnOnBoard();
        }

//        System.out.println(this.displayPawn() + "after hitting");
    }



    private void placePawnOnBoard() {
        Board.INSTANCE.getFields()[position.getX()][position.getY()] = this;
    }

    private void setCharacter() {
        if(this.isWhite) {
            this.character = "W";
        } else {
            this.character = "B";
        }
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean validateMove() {
        return false;
    }

    public boolean multipleJump() {
        return false;
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    public void setCrowned(boolean crowned) {
        if(this.isWhite) {
            this.character = "O";
        } else {
            this.character = "U";
        }
        isCrowned = crowned;
    }

    @Override
    public String toString() {
        return this.character;
    }

    public String displayPawn() {
        if (this != null)
            return "Pawn{" +
                    "position=" + position +
                    '}';
        return "Empty";
    }


    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

    public Coordinates getPosition() {
        return position;
    }
}

