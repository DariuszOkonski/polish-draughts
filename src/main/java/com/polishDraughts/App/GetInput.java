package com.polishDraughts.App;
import java.util.*;

public class GetInput
{
    static int GetBoardSize()
    {
        Scanner sc = new Scanner(System.in);
        int boardSize = sc.nextInt();

        return boardSize;
    }
}
