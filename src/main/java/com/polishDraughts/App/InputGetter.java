package com.polishDraughts.App;

import java.util.*;

public class InputGetter {
    public static String[] getInputData() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Type col: ");
        String col = sc.nextLine().toUpperCase();
        System.out.println("Type row: ");
        String row = sc.nextLine();

        String[] cord = {col, row};

        return cord;
    }

    public static void processMove() {
        boolean moveIsCorrect = false;
        String[] inputData = null;
        boolean fieldIsEmpty = false;
        boolean fieldWithSameColor = false;

        System.out.println(Game.INSTANCE.isWhiteTurn() ? "white turn":"black turn");
        while (!moveIsCorrect || !fieldIsEmpty || fieldWithSameColor){

            inputData = getInputData();
            moveIsCorrect = isInputValid(inputData);

            if(!moveIsCorrect) {
                System.out.println("Wrong coordinates, try again.");
            }

            System.out.println(moveIsCorrect + " move in range");
            if (moveIsCorrect) {
                int col = Util.colsDictionary.get(inputData[0]);
                int row = Util.rowsDictionary.get(inputData[1]);

                Coordinates coordinates = new Coordinates(row, col);

//                System.out.println(coordinates.toString() + " field empty");
                Pawn field = Board.INSTANCE.getField(coordinates);
                fieldWithSameColor = Game.INSTANCE.checkIfFieldWithSameColor(field);

                System.out.println("field =================================");
                System.out.println(fieldWithSameColor);
                System.out.println(field);
//                System.out.println(field.getIsWhite());


                System.out.println(field==null ? "empty" : field.displayPawn());
                fieldIsEmpty = field == null;

                Coordinates[] possibleMoves = coordinates.getBasicMoves(Game.INSTANCE.isWhiteTurn());

                List<Coordinates> possibleMovesList = Game.INSTANCE.getPossibleMove(field, possibleMoves);

//                System.out.println("POsible movelist ==============================");
//                System.out.println(possibleMovesList);
            }
        }


        System.out.println("Correct");
    }

    public static boolean isInputValid(String[] inputData) {
        boolean colCorrect = Util.colsDictionary.get(inputData[0]) != null;
        boolean rowCorrect = Util.rowsDictionary.get(inputData[1]) != null;

        if(colCorrect && rowCorrect)
            return true;
        return false;
    }
}
