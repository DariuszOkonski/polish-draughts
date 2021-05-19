package com.polishDraughts.App;

public class Board {
    public final static Board INSTANCE = new Board(true);
    private Pawn[][] fields;

    private int size;


    private Board() {
        this.fields = new Pawn[Game.INSTANCE.BOARD_SIZE][Game.INSTANCE.BOARD_SIZE];
        this.size = Game.INSTANCE.BOARD_SIZE;

//        Util.setBoardDetails(BOARD_SIZE);

        this.setWhites();
        this.setBlack();
    }

    public Board(boolean test) {
        this.fields = new Pawn[10][10];
        this.size = 10;
        setSinglePawn(3, 3, true);
        setSinglePawn(4, 4, false);

//        setSinglePawn(5, 5, true);

        setSinglePawn(4, 2, true);
        setSinglePawn(2, 2, true);
        setSinglePawn(6, 6, false);
        setSinglePawn(1, 3, false);
        setSinglePawn(8, 8, true);
    }
    public int getSize() {
        return size;
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

    public Pawn getField(Coordinates coordinates) {
        return this.getFields()[coordinates.getX()][coordinates.getY()];
    }

    public void clearField(Coordinates coordinates) {
        fields[coordinates.getX()][coordinates.getY()] = null;
    }



    public Pawn[][] getFields() {
        return fields;
    }
}


