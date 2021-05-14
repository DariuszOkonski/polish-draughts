package com.polishDraughts.App;
import static com.polishDraughts.App.AuxiliaryFunctions.*;
import static com.polishDraughts.App.GetInput.*;
import static com.polishDraughts.App.Messages.*;
import java.util.Arrays;



public class App {
    public static void main(String[] args) {
        AskForBoardSize();
        int boardSize = GetBoardSize();
        ShowChoosenBoardSize(boardSize);
        clearScreen();
        Board board = new Board(boardSize);


//        System.out.println(Arrays.deepToString(board.getFields()));
        board.print();
    }

}
