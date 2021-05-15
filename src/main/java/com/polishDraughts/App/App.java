package com.polishDraughts.App;

public class App {
    public static void main(String[] args) {

//        Board board = new Board(12);

//        String[] data = InputGetter.getInputData();
//        System.out.println(InputGetter.isInputValid(data));
        System.out.println(Board.INSTANCE);
        InputGetter.processMove();

//        System.out.println(Arrays.deepToString(board.getFields()));
        Board.INSTANCE.print();
    }
}

