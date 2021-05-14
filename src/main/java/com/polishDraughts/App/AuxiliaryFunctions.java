package com.polishDraughts.App;

public class AuxiliaryFunctions
{
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int checkIfBoardSizeIsCorrect(int boardSize)
    {
        int enteredValue = boardSize;

        while (enteredValue < 10 || enteredValue > 20)
        {
            System.out.println("the size of the board should be from 10 to 20, please correct board sie ");
            int newEnteredValue = GetInput.GetBoardSize();
            enteredValue = newEnteredValue;
        }
        return enteredValue;
    }

}
