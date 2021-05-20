package com.polishDraughts.App;

import java.util.ArrayList;
import java.util.List;

public class CrownedAttack {
    private List<List<Coordinates>> diagonalCords;
    private Coordinates startCord;

    private List<Coordinates> leftTop = new ArrayList<>();
    private List<Coordinates> rightTop = new ArrayList<>();
    private List<Coordinates> rightBottom = new ArrayList<>();
    private List<Coordinates> leftBottom = new ArrayList<>();

    public CrownedAttack(List<List<Coordinates>> diagonalCords, Coordinates startCord) {
        this.diagonalCords = diagonalCords;
        this.startCord = startCord;
    }

    @Override
    public String toString() {
        return "CrownedAttack{" +
                "diagonalCords=" + diagonalCords + "\n" +
                ", startCord=" + startCord + "\n" +
                ", leftTop=" + leftTop + "\n" +
                ", rightTop=" + rightTop + "\n" +
                ", rightBottom=" + rightBottom + "\n" +
                ", leftBottom=" + leftBottom + "\n" +
                '}';
    }
}
