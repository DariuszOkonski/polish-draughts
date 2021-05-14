package com.polishDraughts.App;
import static com.polishDraughts.App.PrintColors.*;


public class Messages {

    static void AskForBoardSize()
    {
        System.out.println(TEXT_CYAN + "Enter a value from 10 to 20, which will be the number of vertical and horizontal spaces on the board: " + TEXT_RESET);
    }

    static void ShowChoosenBoardSize(int boardSize)
    {
        System.out.println(TEXT_CYAN + "The size of the board is " + boardSize + " * " + boardSize + " fields, HAVE FUN!" + TEXT_RESET);

    }


}
