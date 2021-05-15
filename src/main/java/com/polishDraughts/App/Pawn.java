package com.polishDraughts.App;

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
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

}

