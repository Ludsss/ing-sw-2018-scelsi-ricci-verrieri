package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Scheme;

import java.util.ArrayList;


public class ColoredDiagonals extends ObjectiveCard {

    Box[][] boxes;

    public ColoredDiagonals() {
        super();
    }

    public int numSameColor1(int row, int column, int length) {
        int num = 0;
        ArrayList<Color> colors = new ArrayList<Color>();
        for (int i = 0; i < length; i++) {
            if (boxes[row - i][column - i].isFull() && boxes[row - i - 1][column - i - 1].isFull()) {
                if (boxes[row - i][column - i].getDice().getDiceColor() == boxes[row - i - 1][column - i - 1].getDice().getDiceColor()) {
                    num++;
                    if (!colors.contains(boxes[row - i][column - i].getDice().getDiceColor()))
                        colors.add(boxes[row - i][column - i].getDice().getDiceColor());
                }
            }
        }
        return num + colors.size();
    }

    public int numSameColor2(int row, int column, int length) {
        int num = 0;
        ArrayList<Color> colors = new ArrayList<Color>();
        for (int i = 0; i < length; i++) {
            if (boxes[row - i][column + i].isFull() && boxes[row - i - 1][column + i + 1].isFull()) {
                if (boxes[row - i][column + i].getDice().getDiceColor() == boxes[row - i - 1][column + i + 1].getDice().getDiceColor()) {
                    num++;
                    if (!colors.contains(boxes[row - i][column + i].getDice().getDiceColor()))
                        colors.add(boxes[row - i][column + i].getDice().getDiceColor());
                }
            }
        }
        return num + colors.size();
    }

    public int calculateScore(Scheme scheme) {
        boxes = scheme.getBoxes();
        int score = numSameColor1(3, 1, 1) + numSameColor1(3, 2, 2) + numSameColor1(3, 3, 3) + numSameColor1(3, 4, 3) + numSameColor1(2, 4, 2) + numSameColor1(1, 4, 1);
        score = score + numSameColor2(1, 0, 1) + numSameColor2(2, 0, 2) + numSameColor2(3, 0, 3) + numSameColor2(3, 1, 3) + numSameColor2(3, 2, 2) + numSameColor2(3, 3, 1);
        return score;
    }

}

