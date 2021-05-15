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

        while (!moveIsCorrect){
            inputData = getInputData();
            moveIsCorrect = isInputValid(inputData);
        }

        int col = Util.colsDictionary.get(inputData[0]);
        int row = Util.rowsDictionary.get(inputData[1]);

        Coordinates coordinates = new Coordinates(col, row);

        System.out.println("======================");
        System.out.println(Board.INSTANCE.getField(coordinates));
        System.out.println(Board.INSTANCE.getField(coordinates).getClass());
    }

    public static boolean isInputValid(String[] inputData) {
        boolean colCorrect = Util.colsDictionary.get(inputData[0]) != null;
        boolean rowCorrect = Util.rowsDictionary.get(inputData[1]) != null;

        if(colCorrect && rowCorrect)
            return true;
        return false;
    }
}
