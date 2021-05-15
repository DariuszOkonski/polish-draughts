package com.polishDraughts.App;

public class Util {
    static int pawnCount;
    static int rowsCountPerPlayer;
    static int boardSize;
    static char[] letterIndexes;
    static int MAX_BOARD_SIZE = 20;



    static char[] getAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    }

    static void setBoardDetails(int size) {
        boardSize = size;
        pawnCount = boardSize * 2;
        rowsCountPerPlayer = pawnCount / (boardSize/2);
        setLetterIndexesArray(boardSize);
        UI.setUiComponents(boardSize);
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
}
