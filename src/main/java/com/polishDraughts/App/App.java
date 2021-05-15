package com.polishDraughts.App;

public class App {
    public static void main(String[] args) {

        Board board = new Board(12);

//        String[] data = InputGetter.getInputData();
//        System.out.println(InputGetter.isInputValid(data));

        InputGetter.processMove(board);

//        System.out.println(Arrays.deepToString(board.getFields()));
        board.print();
    }
}

