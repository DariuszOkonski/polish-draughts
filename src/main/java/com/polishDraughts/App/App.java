package com.polishDraughts.App;

public class App {
    public static void main(String[] args) {
        System.out.println(Board.INSTANCE);
        InputGetter.processMove();

        Board.INSTANCE.print();
    }
}

