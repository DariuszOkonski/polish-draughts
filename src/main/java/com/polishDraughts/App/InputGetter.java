package com.polishDraughts.App;

import java.util.*;

public class InputGetter {

    private int[] playerPawnInputData;
    private Coordinates playerPawnCoords;
    private Pawn pawnOnInitField;

    private int[] fieldToMoveInputData;
    private Coordinates fieldsToMoveCoords;
    private Pawn pawnOnFieldToMove;


    private List<Coordinates> possibleMoves;
    private List<Coordinates> possibleShots;


    public InputGetter() {
        setNewCoordinates();
    }

    public InputGetter(Pawn pawnAfterHitting, List<Coordinates> multipleShotsCoords) {
        Board.INSTANCE.print();
        this.playerPawnCoords = pawnAfterHitting.getPosition();
        this.pawnOnInitField = pawnAfterHitting;
        this.possibleMoves = multipleShotsCoords;
    }

    private void setNewCoordinates() {
        Board.INSTANCE.print();
        this.playerPawnInputData = this.getPlayerPawnInputData("pawn");
        this.playerPawnCoords = new Coordinates(this.playerPawnInputData[0], this.playerPawnInputData[1]);
        this.pawnOnInitField = Board.INSTANCE.getField(playerPawnCoords);
    }

    private void setFieldToMoveIn() {
        Board.INSTANCE.print();
        this.fieldToMoveInputData = this.getPlayerPawnInputData("move");
        this.fieldsToMoveCoords = new Coordinates(this.fieldToMoveInputData[0], this.fieldToMoveInputData[1]);
        this.pawnOnFieldToMove = Board.INSTANCE.getField(fieldsToMoveCoords);

    }


    public static void processMove() {
        InputGetter pawnToMove = getValidPawnToMove();
        boolean moveToFieldChosenPossible = false;
        do {
            pawnToMove.setFieldToMoveIn();

            for (Coordinates move : pawnToMove.possibleMoves) {
                moveToFieldChosenPossible =
                        move.getX() == pawnToMove.fieldsToMoveCoords.getX() &&
                        move.getY() == pawnToMove.fieldsToMoveCoords.getY();
                if (moveToFieldChosenPossible) {
                    break;
                }
            }
            if (pawnToMove.fieldsToMoveCoords != null && !moveToFieldChosenPossible)
                Util.displayWrongMoveChoiceInfo();

        } while(!moveToFieldChosenPossible);
        System.out.println("success");
        pawnToMove.pawnOnInitField.movePawn(pawnToMove.fieldsToMoveCoords);
    }

    public static void processMove(InputGetter movingPawn) {
        InputGetter pawnToMove = movingPawn;
        boolean moveToFieldChosenPossible = false;
        do {
            pawnToMove.setFieldToMoveIn();

            for (Coordinates move : pawnToMove.possibleMoves) {
                moveToFieldChosenPossible =
                        move.getX() == pawnToMove.fieldsToMoveCoords.getX() &&
                        move.getY() == pawnToMove.fieldsToMoveCoords.getY();
                if (moveToFieldChosenPossible) {
                    break;
                }
            }
            if (pawnToMove.fieldsToMoveCoords != null && !moveToFieldChosenPossible)
                Util.displayWrongMoveChoiceInfo();

        } while(!moveToFieldChosenPossible);
        System.out.println("success");
        pawnToMove.pawnOnInitField.movePawn(pawnToMove.fieldsToMoveCoords);
    }

    public static InputGetter getValidPawnToMove() {
        InputGetter pawnToMove;
        Coordinates[] possibleCoordinates;

        do {
            pawnToMove = new InputGetter();
            possibleCoordinates = pawnToMove.playerPawnCoords
                    .getBasicMoves(Game.INSTANCE.isWhiteTurn());

            pawnToMove.possibleShots = Game.INSTANCE.getCoordsForMultipleHits(pawnToMove.pawnOnInitField);

            pawnToMove.possibleMoves = Game.INSTANCE
                    .getPossibleMoves(pawnToMove.pawnOnInitField, possibleCoordinates);

            pawnToMove.possibleMoves.addAll(pawnToMove.possibleShots);

            Util.displayWrongPawnChoiceInfo(pawnToMove);
        } while (
                !pawnToMove.isPlayersPawn() || pawnToMove.possibleMoves.size() == 0);

        System.out.println("Correct");
        System.out.println("Choosen pawn:       "+pawnToMove.pawnOnInitField.displayPawn());
        System.out.println("Possible moves:     "+pawnToMove.possibleMoves);
        return pawnToMove;
    }

    public int[] getPlayerPawnInputData(String inputFor) {
        String[] cord = new String[2];
        Util.displayTurnInfo();
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(String.format("Type your %s correct coordinates e.g. 'A1': ", inputFor));
            String input = sc.nextLine().toUpperCase();
            try {
                cord[0] = String.valueOf(input.charAt(0));
                cord[1] = input.substring(1);
            } catch (Exception err) {
                continue;
            }
            System.out.println(Arrays.toString(cord) + "user input");
        } while (!areCoordinatesInRange(cord));

        int[] intArray = {Util.rowsDictionary.get(cord[1]), Util.colsDictionary.get(cord[0])};
        return intArray;
    }



    public static boolean areCoordinatesInRange(String[] inputData) {
        try {
            boolean colCorrect = Util.colsDictionary.get(inputData[0]) != null;
            boolean rowCorrect = Util.rowsDictionary.get(inputData[1]) != null;
            if(colCorrect && rowCorrect)
                return true;
        }
        catch (NullPointerException ex) {}
        return false;
    }

    public boolean isPlayersPawn() {
        return Game.INSTANCE.isCurrentPlayersPawnOnField(pawnOnInitField);
    }


    public Pawn getPawnOnInitField() {
        return pawnOnInitField;
    }

    public List<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }
}
