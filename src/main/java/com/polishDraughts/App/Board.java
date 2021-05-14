package com.polishDraughts.App;

public class Board {
    private Pawn[][] fields;
    private int size;

    public Board(int size) {
        this.fields = new Pawn[size][size];
        this.size = size;
        this.setWhites();
        this.setBlack();
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
        int size = (int)(this.size * 0.4);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < this.fields[i].length; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        setSinglePawn(i, j, true);
                    }
                } else {
                    if(j % 2 != 0) {
                        setSinglePawn(i, j, true);
                    }
                }
            }
        }
    }

    private void setBlack() {
        int size = (int)(this.size * 0.4);

        for (int i = this.fields.length - 1; i >= (this.fields.length - size); i--) {
            for (int j = 0; j < this.fields[i].length; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        setSinglePawn(i, j, false);
                    }
                } else {
                    if(j % 2 != 0) {
                        setSinglePawn(i, j, false);
                    }
                }

            }
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


