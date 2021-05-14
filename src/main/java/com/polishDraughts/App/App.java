package com.polishDraughts.App;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        Board board = new Board(10);

//        System.out.println(Arrays.deepToString(board.getFields()));
        board.print();
    }

}
