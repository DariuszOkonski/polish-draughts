// TODO - first eliminate fields after ally
// - then check enemies and field after enemy
// -
package com.polishDraughts.App;

import java.util.*;

public class CrownedAttack {
    private List<List<Coordinates>> diagonalCords;
    private Pawn crownedPawn;

    private List<Coordinates> leftTop = new ArrayList<>();
    private List<Coordinates> rightTop = new ArrayList<>();
    private List<Coordinates> rightBottom = new ArrayList<>();
    private List<Coordinates> leftBottom = new ArrayList<>();
//    Dictionary<String, List<Coordinates>> leftTopStates = (EMPTY_CD) (ALLY_CD) (ENEMY_CD)
    private List<Coordinates> possibleMoves = new ArrayList<>();
    private Dictionary<String, List<Coordinates>> coordsStates = new Hashtable<String, List<Coordinates>>();
//    Dictionary<String, List<Coordinates>> rightTopStates = new Hashtable<String, List<Coordinates>>();
//    Dictionary<String, List<Coordinates>> rightBottomStates = new Hashtable<String, List<Coordinates>>();
//    Dictionary<String, List<Coordinates>> leftBottomStates = new Hashtable<String, List<Coordinates>>();

    public CrownedAttack(List<List<Coordinates>> diagonalCords, Pawn crownedPawn) {
        this.diagonalCords = diagonalCords;
        leftTop = diagonalCords.get(0);
        rightTop = diagonalCords.get(1);
        rightBottom = diagonalCords.get(2);
        leftBottom = diagonalCords.get(3);
        this.crownedPawn = crownedPawn;
        setEmptyLists();
        groupCoords();
        setPossibleMoves();
//        eliminateFieldsAfterAllies();

    }

    private void setPossibleMoves() {
        List<Coordinates> unavailableFields = new ArrayList<Coordinates>();
        unavailableFields.addAll(getFieldsToRemoveAfterAllies());
        unavailableFields.addAll(getFieldsToRemoveAfterTwoPawnsInRow());
        Set<Coordinates> targetSet = new HashSet<>();
        if (Game.INSTANCE.isPlayerHasHit()) {
            List<Coordinates> captureFields = getCaptureFields();
            targetSet.addAll(captureFields);
        }else{
            targetSet = new HashSet<>(coordsStates.get("empty"));
        }
        targetSet.removeAll(unavailableFields);
        possibleMoves.addAll(targetSet);
    }

    private List<Coordinates> getCaptureFields() {
        List<Coordinates> availableFields = new ArrayList<Coordinates>();
        for (Coordinates enemyCoord : coordsStates.get("enemy")){
            if (leftTop.contains(enemyCoord)){
                availableFields.addAll(getEmptyFieldsList(enemyCoord.getTopLeftCoordinates()));
            }
            else if (rightTop.contains(enemyCoord)){
                availableFields.addAll(getEmptyFieldsList(enemyCoord.getTopRightCoordinates()));
            }
            else if (rightBottom.contains(enemyCoord)){
                availableFields.addAll(getEmptyFieldsList(enemyCoord.getBottomRightCoordinates()));
            }
            else if (leftBottom.contains(enemyCoord)){
                availableFields.addAll(getEmptyFieldsList(enemyCoord.getBottomLeftCoordinates()));
            }
        }
        return availableFields;
    }

    public List<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }

    private List<Coordinates> getFieldsToRemoveAfterAllies() {
        List<Coordinates> checkedCoordinates = new ArrayList<Coordinates>();
        List<Coordinates> emptyFieldsToRemove = new ArrayList<Coordinates>();

        for (Coordinates allyField : coordsStates.get("ally")) {
            if (leftTop.contains(allyField)){
                checkedCoordinates = allyField.getTopLeftCoordinates();
            }else if (rightTop.contains(allyField)){
                checkedCoordinates = allyField.getTopRightCoordinates();
            }else if (rightBottom.contains(allyField)){
                checkedCoordinates = allyField.getBottomRightCoordinates();
            }else if (leftBottom.contains(allyField)){
                checkedCoordinates = allyField.getBottomLeftCoordinates();
            }
            List<Coordinates> emptyFieldsAfterAllyPawn = getEmptyFieldsList(checkedCoordinates);
            emptyFieldsToRemove.addAll(emptyFieldsAfterAllyPawn);
        }
        return emptyFieldsToRemove;
    }

    private List<Coordinates> getEmptyFieldsList(List<Coordinates> coordsList) {
        List<Coordinates> emptyFieldsCoords = new ArrayList<Coordinates>();
        for (Coordinates toRemove : coordsList) {
            if (Board.INSTANCE.getField(toRemove) == null)
                emptyFieldsCoords.add(toRemove);
        }
        return emptyFieldsCoords;
    }


    private List<Coordinates> getFieldsToRemoveAfterTwoPawnsInRow() {
        List<Coordinates> arrayListToRemove = new ArrayList<>();
        for (Coordinates pawnCd : coordsStates.get("enemy")) {
            addFieldsToRemoveIfPawnIsInRow(pawnCd, arrayListToRemove);
        }
        List<Coordinates> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(arrayListToRemove));
        return listWithoutDuplicates;
    }


    private void addFieldsToRemoveIfPawnIsInRow(Coordinates pawnCd, List<Coordinates> listToRemove) {
        List<Coordinates> fieldsAfterTwoInRows = new ArrayList<Coordinates>();
        Coordinates fieldAfterCoord = null;
        if (leftTop.contains(pawnCd)) {
            fieldAfterCoord = pawnCd.getNextTopLeft();
            if (!Board.INSTANCE.isFieldEmpty(fieldAfterCoord))
                fieldsAfterTwoInRows.addAll(fieldAfterCoord.getTopLeftCoordinates());
        }
        else if (rightTop.contains(pawnCd)){
            fieldAfterCoord = pawnCd.getNextTopRight();
            if (!Board.INSTANCE.isFieldEmpty(fieldAfterCoord))
                fieldsAfterTwoInRows.addAll(fieldAfterCoord.getTopRightCoordinates());
        }
        else if (rightBottom.contains(pawnCd)){
            fieldAfterCoord = pawnCd.getNextBottomRight();
            if (!Board.INSTANCE.isFieldEmpty(fieldAfterCoord))
                fieldsAfterTwoInRows.addAll(fieldAfterCoord.getBottomRightCoordinates());
        }
        else if (leftBottom.contains(pawnCd)){
            fieldAfterCoord = pawnCd.getNextBottomLeft();
            if (!Board.INSTANCE.isFieldEmpty(fieldAfterCoord))
                fieldsAfterTwoInRows.addAll(fieldAfterCoord.getBottomLeftCoordinates());
        }
        listToRemove.addAll(getEmptyFieldsList(fieldsAfterTwoInRows));
    }

    private void setEmptyLists () {
        List<Coordinates> empty1 = new ArrayList<Coordinates>();
        List<Coordinates> empty2 = new ArrayList<Coordinates>();
        List<Coordinates> empty3 = new ArrayList<Coordinates>();
//        List<Coordinates> empty4 = new ArrayList<Coordinates>();
        coordsStates.put("empty", empty1);
        coordsStates.put("ally", empty2);
        coordsStates.put("enemy", empty3);
    }
//    private void
    private void groupCoords() {
        for (List<Coordinates> singleRow: diagonalCords) {
            for (Coordinates cord : singleRow) {
                Pawn pawn = Board.INSTANCE.getField(cord);
                if (pawn == null)
                    coordsStates.get("empty").add(cord);
                else if (pawn.getIsWhite() == Game.INSTANCE.isWhiteTurn())
                    coordsStates.get("ally").add(cord);
                else if (pawn.getIsWhite() != Game.INSTANCE.isWhiteTurn())
                    coordsStates.get("enemy").add(cord);
            }
        }
    }
    @Override
    public String toString() {
        return "CrownedAttack{" +
//                "diagonalCords=" + diagonalCords + "\n" +
                ", startCord=" + crownedPawn + "\n" +
                ", leftTop=" + leftTop + "\n" +
                ", rightTop=" + rightTop + "\n" +
                ", possibleMoves=" + possibleMoves + "\n" +
                ", rightBottom=" + rightBottom + "\n" +
                ", leftBottom=" + leftBottom + "\n" +
                ", empty=" + coordsStates.get("empty") + "\n" +
                ", ally=" + coordsStates.get("ally") + "\n" +
                ", enemy=" + coordsStates.get("enemy") + "\n" +
                '}';
    }
}
