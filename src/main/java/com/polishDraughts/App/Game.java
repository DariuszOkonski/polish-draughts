package com.polishDraughts.App;

public class Game {
    public final static Game INSTANCE = new Game();
    public static boolean whiteTurn = false;
    public final static int BOARD_SIZE = 12;

    private Game() {
        Util.setBoardDetails(BOARD_SIZE);

    }

    private void mainMenu(){

    }

    private void changeTurn() {
        whiteTurn = !whiteTurn;
    }
    //    public static int BOARD_SIZE = 12;
//    public Coordinates[]
}
