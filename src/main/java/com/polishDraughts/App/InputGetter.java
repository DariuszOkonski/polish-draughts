package com.polishDraughts.App;

import java.util.*;

public class InputGetter {

    private Coordinates coordinates;
    private int[] inputData;
    private Pawn pawnOnField;

    public InputGetter() {
        this.inputData = this.getInputData();
        this.coordinates = new Coordinates(this.inputData[0], this.inputData[1]);
        this.pawnOnField = Board.INSTANCE.getField(coordinates);
    }

    public int[] getInputData() {
        String[] cord = new String[2];
        do {
            Scanner sc = new Scanner(System.in);

            System.out.println("Type col: ");
            String col = sc.nextLine().toUpperCase();
            System.out.println("Type row: ");
            String row = sc.nextLine();

            cord[0] = col;
            cord[1] = row;
        } while (!areCoordinatesInRange(cord));

        int[] intArray = {Util.rowsDictionary.get(cord[1]), Util.colsDictionary.get(cord[0])};

        return intArray;
    }

    public static boolean validateCorrectMoveConditions(String[] inputData) {
        boolean isPlayersPawn = false;
        boolean coordinatesInRange = false;

        coordinatesInRange = areCoordinatesInRange(inputData);
//        isPlayersPawn = Board.INSTANCE.getField()


        return true;
    }

    public static void processMove() {
        InputGetter inputGetter = new InputGetter();

        System.out.println("INPUT GETTER INSTAnCE ====================");
        System.out.println(Arrays.toString(inputGetter.inputData));
        System.out.println(inputGetter.coordinates);
        System.out.println(inputGetter.pawnOnField);

        System.out.println("===========================================");

        System.out.println(Game.INSTANCE.isWhiteTurn() ? "white turn":"black turn");


        //        while (!moveIsInRange){

//            inputData = getInputData();













//            moveIsCorrect = isInputValid(inputData);
//
//            if(!moveIsCorrect) {
//                System.out.println("Wrong coordinates, try again.");
//            }
//
//            System.out.println(moveIsCorrect + " move in range");
//            if (moveIsCorrect) {
//                int col = Util.colsDictionary.get(inputData[0]);
//                int row = Util.rowsDictionary.get(inputData[1]);
//
//                Coordinates coordinates = new Coordinates(row, col);
//
////                System.out.println(coordinates.toString() + " field empty");
//                Pawn field = Board.INSTANCE.getField(coordinates);
//                fieldWithSameColor = Game.INSTANCE.checkIfFieldWithSameColor(field);
//
//                System.out.println("field =================================");
//                System.out.println(fieldWithSameColor);
//                System.out.println(field);
////                System.out.println(field.getIsWhite());
//
//
//                System.out.println(field==null ? "empty" : field.displayPawn());
//                fieldIsEmpty = field == null;
//
//                Coordinates[] possibleMoves = coordinates.getBasicMoves(Game.INSTANCE.isWhiteTurn());
//
//                List<Coordinates> possibleMovesList = Game.INSTANCE.getPossibleMove(field, possibleMoves);
//
////                System.out.println("POsible movelist ==============================");
////                System.out.println(possibleMovesList);
//            }
//        }


        System.out.println("Correct");
    }

    public static boolean areCoordinatesInRange(String[] inputData) {
        boolean colCorrect = Util.colsDictionary.get(inputData[0]) != null;
        boolean rowCorrect = Util.rowsDictionary.get(inputData[1]) != null;

        if(colCorrect && rowCorrect)
            return true;
        return false;
    }
}
