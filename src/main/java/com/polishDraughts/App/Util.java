package com.polishDraughts.App;

import java.util.Dictionary;
import java.util.Hashtable;

public class Util {
    static int pawnCount;
    static int rowsCountPerPlayer;
    static int boardSize;
    static char[] letterIndexes;
    static final int MAX_BOARD_SIZE = 20;
    static final int MIN_BOARD_SIZE = 10;

    static Dictionary<String, Integer> rowsDictionary = new Hashtable<String, Integer>();
    static Dictionary<String, Integer> colsDictionary = new Hashtable<String, Integer>();

    static char[] getAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    }


    static void setBoardDetails(int size) {
        boardSize = size;
        pawnCount = boardSize * 2;
        rowsCountPerPlayer = pawnCount / (boardSize/2);
        setLetterIndexesArray(boardSize);
        UI.setUiComponents(boardSize);

        createCoordinatesDictionaries();
    }


    static void createCoordinatesDictionaries() {
        System.out.println(letterIndexes);
        for (int i = 0; i < letterIndexes.length; i++) {
            String s = String.valueOf(letterIndexes[i]);
            colsDictionary.put(s, i);
        }
        for (int i = 0; i < letterIndexes.length; i++) {
            int rowPosition = i + 1;
            String s = String.valueOf(rowPosition);
            rowsDictionary.put(s, i);
        }
    }

    static void setLetterIndexesArray (int size) {
        char[] alphabet = getAlphabet();
        letterIndexes = new char[size];
        for (int i = 0; i < size; i++) {
            letterIndexes[i] = alphabet[i];
        }
    }
    static void printLettersLine() {
        String line = "";
        for (int i = 0; i < boardSize; i++) {
            if (i != boardSize-1) {
                line += letterIndexes[i] + "   ";
            } else {
                line += letterIndexes[i] + "  ";
            }
        }
        System.out.println("\t  " + line);
        System.out.println("\t"+UI.hr);
    }
    static void printNumIndexString(int index) {
        System.out.print(String.format("%d\t| ", index+1));
    }


    static String getFieldLetter (Pawn field) {
        String letter = " ";
        if (field != null) {
            letter = field.toString();
        }
        return letter;
    }

    static void displayWrongPawnChoiceInfo(InputGetter input) {
        if (!Game.INSTANCE.isCurrentPlayersPawnOnField(input.getPawnOnInitField())) {
            System.out.println("You've chosen field with not your pawn");
        } else if (input.getPossibleMoves().size() == 0) {
            System.out.println("No possible moves for this field");
        }
    }
    static void displayWrongMoveChoiceInfo() {
            System.out.println("You've chosen field you cannot move to with this pawn");
    }

    static void displayTurnInfo(String type) {
        String turnStr = Game.INSTANCE.isWhiteTurn()?"White turn": "Black turn";
        String typeStr = String.format("|| coordinates for %s: ", type.toUpperCase());
        System.out.println(turnStr +"   " +typeStr);


    }
}
