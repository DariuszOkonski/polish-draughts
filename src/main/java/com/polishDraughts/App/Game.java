package com.polishDraughts.App;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public final static Game INSTANCE = new Game();

    private boolean whiteTurn = true;
    public final static int BOARD_SIZE = 10;

    private Game() {
        Util.setBoardDetails(BOARD_SIZE);

    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    private void mainMenu(){

    }

    public List<Coordinates> getPossibleMove(Pawn field, Coordinates[] possibleMoves) {
        List<Coordinates> possibleCoordinatesToMove = new ArrayList<>();

//        check if positions are in array range
        for (int i = 0; i < possibleMoves.length; i++) {
            boolean isInArrayRange =possibleMoves[i].getY() >= 0 && possibleMoves[i].getY() < Board.INSTANCE.getSize();
            boolean isPosittionOccupied = false;

            if(isInArrayRange)
                isPosittionOccupied = Board.INSTANCE.getField(possibleMoves[i]) == null;

            if(isInArrayRange && isPosittionOccupied) {
                possibleCoordinatesToMove.add(possibleMoves[i]);
            }
        }

        return possibleCoordinatesToMove;
    }

    public boolean checkIfFieldWithSameColor(Pawn field) {
        try {
            return field.getIsWhite() == whiteTurn;
        } catch (NullPointerException err) {
            return false;
        }
    }

    private void changeTurn() {
        whiteTurn = !whiteTurn;
    }
        //    public static int BOARD_SIZE = 12;
    //    public Coordinates[]
    }
