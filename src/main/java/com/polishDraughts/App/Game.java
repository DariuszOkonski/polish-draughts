// CURRENT STATE TODO - CHECK FOR SHOOTING IN ALL DIRECTIONS - BLACK CANNOT SHOT WHITE
//
package com.polishDraughts.App;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    public final static Game INSTANCE = new Game();

    private boolean whiteTurn = true;
    public final static int BOARD_SIZE = 10;

    private boolean playerHasHit = false;
    private Pawn pawnAfterHitting;
    private CrownedAttack crownedAttack = null;

    private Game() {
        Util.setBoardDetails(BOARD_SIZE);

    }

    void init() {
        List<Coordinates> multipleShots = null;
        while(true) {

            if (multipleShots == null)
                InputGetter.processMove();

            if (!playerHasHit) {
                Game.INSTANCE.changeTurn();
                continue;
            }
            multipleShots = getCoordsForMultipleHits(getPawnAfterHitting());
            System.out.println();
            System.out.println(playerHasHit + " " + " pawn hittin " + pawnAfterHitting + multipleShots);
            System.out.println();
            if (multipleShots.size() == 0) {
                playerHasHit = false;
                pawnAfterHitting = null;
                Game.INSTANCE.changeTurn();
                multipleShots = null;
            } else {
                System.out.println(multipleShots + " MULTIPLE SHOTS");
                InputGetter inputGetter = new InputGetter(pawnAfterHitting, multipleShots);
                InputGetter.processMove(inputGetter);
            }
            System.out.println(multipleShots + " MULTI SHOTS <---------------------" + playerHasHit);
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

    public void getPossibleMovesForCrowned(Pawn pawn) {
           var crownedMoves = pawn.getPosition().getBasicMovesForCrowned();
           this.crownedAttack = new CrownedAttack(crownedMoves, pawn.getPosition());

           System.out.println(Collections.unmodifiableList(crownedMoves));
            System.out.println(this.crownedAttack);
    }

    public List<Coordinates> getPossibleMoves(Pawn startPawnField, Coordinates[] possibleMoves) {
        List<Coordinates> possibleCoordinatesToMove = new ArrayList<>();
//        check if positions are in array range
        for (int i = 0; i < possibleMoves.length; i++) {
            boolean isInArrayRange =
                    (possibleMoves[i].getY() >= 0 && possibleMoves[i].getY() < Board.INSTANCE.getSize()) &&
                            (possibleMoves[i].getX() >=0 && possibleMoves[i].getX() < Board.INSTANCE.getSize());

            if(isInArrayRange) {
                Pawn objectOnField = Board.INSTANCE.getField(possibleMoves[i]);
                boolean fieldIsEmpty = objectOnField == null;

                if(fieldIsEmpty) {
                    possibleCoordinatesToMove.add(possibleMoves[i]);
                    continue;
                }
                boolean opponentPawnOnField = objectOnField.getIsWhite() != isWhiteTurn();
                if (opponentPawnOnField)
                    addHitMoveIfPossible(possibleCoordinatesToMove, startPawnField, objectOnField);
            }
        }
        return possibleCoordinatesToMove;
    }

    public List<Coordinates> getPossibleMoves(Pawn startPawnField, Coordinates[] possibleMoves, boolean hitsOnly) {
        List<Coordinates> possibleCoordinatesToMove = new ArrayList<>();
//        check if positions are in array range
        for (int i = 0; i < possibleMoves.length; i++) {
            boolean isInArrayRange =
                    (possibleMoves[i].getY() >= 0 && possibleMoves[i].getY() < Board.INSTANCE.getSize()) &&
                        (possibleMoves[i].getX() >=0 && possibleMoves[i].getX() < Board.INSTANCE.getSize());

            if(isInArrayRange) {
                Pawn objectOnField = Board.INSTANCE.getField(possibleMoves[i]);
                boolean fieldIsEmpty = objectOnField == null;

                if(!fieldIsEmpty) {

                    boolean opponentPawnOnField = objectOnField.getIsWhite() != isWhiteTurn();
                    if (opponentPawnOnField)
                        possibleCoordinatesToMove.add(objectOnField.getPosition());
                }
            }
        }
        return possibleCoordinatesToMove;
    }

    private void addHitMoveIfPossible (List<Coordinates> possibleMoves,
                                       Pawn attackingPawn,
                                       Pawn attackedPawn) {
        Coordinates hittingFieldCoordinates = Coordinates
                .getHittingMove(
                        attackingPawn.getPosition(),
                        attackedPawn.getPosition());

        Pawn objectAfterHitPawn = Board.INSTANCE.getField(hittingFieldCoordinates);
        System.out.println(hittingFieldCoordinates);
        System.out.println(objectAfterHitPawn);
        if (objectAfterHitPawn==null)
            possibleMoves.add(hittingFieldCoordinates);
    }
    public void checkForHit(Coordinates startPosition, Coordinates newPosition) {
        Coordinates coordsDelta = startPosition.getDelta(newPosition);
        System.out.println(coordsDelta);

        boolean moveDistanceLongerThanOneField =
                Math.abs(coordsDelta.getX()) > 1
                && Math.abs(coordsDelta.getY()) > 1;

        if (moveDistanceLongerThanOneField) {
            checkPassedFieldsForHit(startPosition, coordsDelta);
        }
    }
    private void checkPassedFieldsForHit(Coordinates startPosition, Coordinates coordsDelta) {
        System.out.println("condition absolute");
        Coordinates checkedCoords = startPosition;
        System.out.println(checkedCoords);
        for (int i=1; i<Math.abs(coordsDelta.getX()); i++) {
            int deltaX = coordsDelta.getX() > 0 ? i : -i;
            int deltaY = coordsDelta.getY() > 0 ? i : -i;
            checkedCoords.incrementX(deltaX);
            checkedCoords.incrementY(deltaY);
            Pawn fieldsObj = Board.INSTANCE.getField(checkedCoords);
            System.out.println(checkedCoords);

            if (fieldsObj != null) {
                System.out.println("hitting" + fieldsObj.displayPawn());
                Board.INSTANCE.clearField(checkedCoords);
                playerHasHit = true;
            }
        }
    }
    public List<Coordinates> getCoordsForMultipleHits(Pawn positionAfterHit) {

        Coordinates[] unvalidatedCoords = positionAfterHit
                .getPosition()
                .getBasicMoves();
        List<Coordinates> validCoords = getPossibleMoves(positionAfterHit, unvalidatedCoords, true);
        List<Coordinates> validMultipleShots = getPossibleShotsCoords(positionAfterHit, validCoords);
        return validMultipleShots;
    }

    private List<Coordinates> getPossibleShotsCoords(Pawn positionAfterHit, List<Coordinates> validCoords) {
        List<Coordinates> shotsCoords = new ArrayList<>();
        System.out.println(positionAfterHit.displayPawn() + " position after hit");
        for (Coordinates move : validCoords) {
            Pawn pawnOnField = Board.INSTANCE.getField(move);
            System.out.println(move + " move " + pawnOnField +" field " + " ");
            if (pawnOnField == null)
                continue;

            if (!isCurrentPlayersPawnOnField(pawnOnField))
                addHitMoveIfPossible(shotsCoords, positionAfterHit, pawnOnField);
        }
        return shotsCoords;
    }

    public boolean isCurrentPlayersPawnOnField(Pawn field) {
        try {
            return field.getIsWhite() == whiteTurn;
        } catch (NullPointerException err) {
            return false;
        }
    }

    public void checkIfPawnToCrowned(Pawn pawn) {
        if(!pawn.getIsWhite() && pawn.getPosition().getX() == 0) {
            pawn.setCrowned(true);
        } else if(pawn.getIsWhite() && pawn.getPosition().getX() == Board.INSTANCE.getSize() - 1) {
            pawn.setCrowned(true);
        }
    }

    private void changeTurn() {
        whiteTurn = !whiteTurn;
    }
        //    public static int BOARD_SIZE = 12;
    //    public Coordinates[]
}
