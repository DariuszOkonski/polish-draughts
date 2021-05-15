package com.polishDraughts.App;

public class Board {
    private Pawn[][] fields;
    private int size;


    public Board(int size) {
        this.fields = new Pawn[size][size];
        this.size = size;

        Util.setBoardDetails(size);

        this.setWhites();
        this.setBlack();
    }

    public void print() {

        Util.printLettersLine();
        for (int i = 0; i < size; i++) {
            Util.printNumIndexString(i);

            for (int j = 0;  j < size; j++) {
                String letter = Util.getFieldLetter(fields[i][j]);
                if (j == size-1) {
                    System.out.print(letter + UI.rightSideSeparator);
                } else {
                    System.out.print(letter + UI.insideSeparator);
                }
            }
            System.out.println();
            System.out.println("\t"+UI.hr);
        }
    }

    private void setWhites() {
        boolean previousRowStartedWithPawn = false;

        for (int i = 0; i < Util.rowsCountPerPlayer; i++) {
            for (int j = 0;  j < this.size; j++) {
                if (previousRowStartedWithPawn && j % 2 != 0) {
                    setSinglePawn(i, j, true);
                } else if (!previousRowStartedWithPawn && j % 2 == 0) {
                    setSinglePawn(i, j, true);
                }
            }
            previousRowStartedWithPawn = !previousRowStartedWithPawn;
        }
    }

    private void setBlack() {

        int startingPoint = size - Util.rowsCountPerPlayer;
        boolean previousRowStartedWithPawn = false;

        for (int i = startingPoint; i < size; i++) {
            for (int j = 0;  j < this.size; j++) {
                if (previousRowStartedWithPawn && j % 2 != 0) {
                    setSinglePawn(i, j, false);
                } else if (!previousRowStartedWithPawn && j % 2 == 0) {
                    setSinglePawn(i, j, false);
                }
            }
            previousRowStartedWithPawn = !previousRowStartedWithPawn;
        }
    }

    private void setSinglePawn(int i, int j, boolean isWhite) {
        Coordinates coordinates = new Coordinates(i, j);
        this.fields[i][j] = new Pawn(coordinates, isWhite);
    }


    public Pawn[][] getFields() {
        return fields;
    }
}


