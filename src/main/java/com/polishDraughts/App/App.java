package com.polishDraughts.App;

public class App {
    public static void main(String[] args) {
        Board.INSTANCE.print();
        InputGetter.processMove();
    }
}

