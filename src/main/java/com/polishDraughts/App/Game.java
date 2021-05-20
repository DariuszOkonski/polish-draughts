// CURRENT STATE TODO - CHECK FOR SHOOTING IN ALL DIRECTIONS - BLACK CANNOT SHOT WHITE
//
package com.polishDraughts.App;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final static Game INSTANCE = new Game();

    private boolean whiteTurn = false;
    public final static int BOARD_SIZE = 10;

    private boolean playerHasHit = false;
    private Pawn pawnAfterHitting;

    private Game() {
        Util.setBoardDetails(BOARD_SIZE);

    }

    void init() {
        List<Coordinates> multipleShots = null;
        while(true) {
//            Board.INSTANCE.print();
            if (multipleShots == null)
                InputGetter.processMove();

            if (!playerHasHit) {
                Game.INSTANCE.changeTurn();
                continue;
            }
            multipleShots = getPossibleShots(getPawnAfterHitting());
            if (multipleShots.size() == 0) {
                playerHasHit = false;
                pawnAfterHitting = null;
                Game.INSTANCE.changeTurn();
                multipleShots = null;
            } else {
                InputGetter inputGetter = new InputGetter(pawnAfterHitting, multipleShots);
                InputGetter.processMove(inputGetter);
            }
        }
    }


    public boolean isPlayerHasHit() {
        return playerHasHit;
    }

    public void setPlayerHasHit(boolean playerHasHit) {
        this.playerHasHit = playerHasHit;
    }

    public Pawn getPawnAfterHitting() {
        return pawnAfterHitting;
    }

    public void setPawnAfterHitting(Pawn pawnAfterHitting) {
        this.pawnAfterHitting = pawnAfterHitting;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }


    private void mainMenu(){

    }

    public List<Coordinates> getPossibleMoves(Coordinates[] possibleMoves) {
        List<Coordinates> possibleCoordinatesToMove = new ArrayList<>();
//        check if positions are in array range
        for (int i = 0; i < possibleMoves.length; i++) {
            boolean isInArrayRange =possibleMoves[i].getY() >= 0 && possibleMoves[i].getY() < Board.INSTANCE.getSize();

            if(isInArrayRange) {
                Pawn objectOnField = Board.INSTANCE.getField(possibleMoves[i]);
                boolean fieldIsEmpty = objectOnField == null;
                if(fieldIsEmpty)
                    possibleCoordinatesToMove.add(possibleMoves[i]);
            }
        }
        return possibleCoordinatesToMove;
    }
    public List<Coordinates> getPossibleMoves(Coordinates[] possibleMoves, boolean hitsOnly) {
        List<Coordinates> possibleCoordinatesToMove = new ArrayList<>();
//        check if positions are in array range
        for (int i = 0; i < possibleMoves.length; i++) {
            boolean isInArrayRange = possibleMoves[i].getY() >= 0 && possibleMoves[i].getY() < Board.INSTANCE.getSize();

            if(isInArrayRange) {
                Pawn objectOnField = Board.INSTANCE.getField(possibleMoves[i]);
                boolean fieldIsEmpty = objectOnField == null;
                if(!fieldIsEmpty && !isCurrentPlayersPawnOnField(objectOnField))
                    possibleCoordinatesToMove.add(objectOnField.getPosition());
            }
        }
        return possibleCoordinatesToMove;
    }

    public void checkForHit(Coordinates startPosition, Coordinates newPosition) {
        Coordinates coordsDelta = startPosition.getDelta(newPosition);


        boolean moveDistanceLongerThanOneField =
                Math.abs(coordsDelta.getX()) > 1
                && Math.abs(coordsDelta.getY()) > 1;

        if (moveDistanceLongerThanOneField) {
            checkPassedFieldsForHit(startPosition, coordsDelta);
        }
    }
    private void checkPassedFieldsForHit(Coordinates startPosition, Coordinates coordsDelta) {

        Coordinates checkedCoords = startPosition;

        for (int i=1; i<Math.abs(coordsDelta.getX()); i++) {
            int deltaX = coordsDelta.getX() > 0 ? 1 : -1;
            int deltaY = coordsDelta.getY() > 0 ? 1 : -1;
            checkedCoords.incrementX(deltaX);
            checkedCoords.incrementY(deltaY);
            Pawn fieldsObj = Board.INSTANCE.getField(checkedCoords);

            if (fieldsObj != null) {
                Board.INSTANCE.clearField(checkedCoords);
                playerHasHit = true;
            }
        }
    }
     List<Coordinates> getPossibleShots(Pawn positionAfterHit) {

        Coordinates[] unvalidatedCoords = positionAfterHit
                .getPosition()
                .getBasicMoves();
        List<Coordinates> validCoords = getPossibleMoves(unvalidatedCoords, true);
        List<Coordinates> validMultipleShots = getPossibleShotsCoords(positionAfterHit, validCoords);
        return validMultipleShots;
    }

    private List<Coordinates> getPossibleShotsCoords(Pawn positionAfterHit, List<Coordinates> validCoords) {
        List<Coordinates> shotsCoords = new ArrayList<>();

        for (Coordinates move : validCoords) {
            Pawn pawnOnField = Board.INSTANCE.getField(move);

            if (pawnOnField == null)
                continue;

            if (!isCurrentPlayersPawnOnField(pawnOnField))
                addHitCoordsIfValid(shotsCoords, positionAfterHit, pawnOnField);
                addHitCoordsIfValid(shotsCoords, positionAfterHit, pawnOnField);
        }
        return shotsCoords;
    }

    private void addHitCoordsIfValid(List<Coordinates> possibleMoves,
                                     Pawn attackingPawn,
                                     Pawn attackedPawn) {

        Coordinates hittingFieldCoordinates =
                Coordinates.getHittingCoords(
                        attackingPawn.getPosition(),
                        attackedPawn.getPosition());

        Pawn objectAfterHitPawn = Board.INSTANCE.getField(hittingFieldCoordinates);
        if (objectAfterHitPawn==null)
            possibleMoves.add(hittingFieldCoordinates);
    }

    public boolean isCurrentPlayersPawnOnField(Pawn field) {
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
