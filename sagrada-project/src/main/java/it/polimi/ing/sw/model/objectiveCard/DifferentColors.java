package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Scheme;


public class DifferentColors extends ObjectiveCard {

    public DifferentColors() {
        super();
    }

    public int calculateScore(Scheme scheme) {
        int i, j;
        int red = 0, yellow = 0, blue = 0, green = 0, purple = 0;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 5; j++) {
                if (scheme.getBox(i, j).isFull()) {
                    switch (scheme.getBox(i, j).getDice().getDiceColor()) {
                        case YELLOW:
                            yellow++;
                            break;
                        case RED:
                            red++;
                            break;
                        case BLUE:
                            blue++;
                            break;
                        case GREEN:
                            green++;
                            break;
                        case PURPLE:
                            purple++;
                            break;
                        default:
                            break;
                    }
                }
            }
        for (i = 0; i < 4; i++)
            if (red == i || yellow == i || blue == i || green == i || purple == i)
                return i * 4;

        return 0;
    }

}
