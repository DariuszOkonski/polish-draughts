package com.polishDraughts.App;

public class Pawn {
    private Coordinates position;
    private boolean isCrowned;
    private boolean isWhite;

    public Pawn(Coordinates position, boolean isWhite) {
        this.position = position;
        this.isCrowned = false;
        this.isWhite = isWhite;
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

    @Override
    public String toString() {
        String singlePawn = "";
        if (isWhite) {
            singlePawn = "W";
        } else {
            singlePawn = "B";
        }

        return singlePawn;
    }
}

