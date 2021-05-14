package com.polishDraughts.App;

public class Board {
    private Pawn[][] fields;

    public Board(int size) {
        this.fields = new Pawn[size][size];
        this.setWhites();
    }

    public void print() {
        for (Pawn[] row : fields) {
            for (Pawn field : row) {
                System.out.print(field + " - ");
            }
            System.out.println();
        }
    }

    private void setWhites() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < this.fields[i].length; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        Coordinates coordinates = new Coordinates(0, i);
                        this.fields[i][j] = new Pawn(coordinates, true);
                    }
                } else {
                    if(j % 2 != 0) {
                        Coordinates coordinates = new Coordinates(0, i);
                        this.fields[i][j] = new Pawn(coordinates, true);
                    }
                }
            }
        }
    }

    public Pawn[][] getFields() {
        return fields;
    }
}


