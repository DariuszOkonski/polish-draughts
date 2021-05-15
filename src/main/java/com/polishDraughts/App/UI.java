package com.polishDraughts.App;

public class UI {
    static String hr;
    static String insideSeparator;
    static String rightSideSeparator;

    public static void setUiComponents(int boardSize) {
        hr = "-".repeat(boardSize + 3 * boardSize + 1 );
        insideSeparator = " | ";
        rightSideSeparator = " |";
    }
}
