package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;


public class DiluentePerPastaSalda extends ToolCard {

    private final int id = 11;
    private Dice dice;


    public DiluentePerPastaSalda() {
        super(11);

    }


    @Override
    public void execute11a(DraftPool draftPool, int indexInDraftPool, Bag bag) {
        bag.addDice(draftPool.getDice(indexInDraftPool));
        draftPool.setDice(bag.drawDice(), indexInDraftPool);
    }

    @Override
    public void execute11b(int numFacciaUp, Scheme scheme, int row, int col) throws NotValidException {
        dice.setNumFacciaUp(numFacciaUp);
        Box destBox = scheme.getBox(row, col);
        if (destBox.isFull())
            throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
        else {
            if (scheme.checkBox(row, col, dice) && scheme.checkIfHasDiceAdjacent(row, col, dice, 1)) {
                destBox.placeDice(dice);
                incrementNumOfTokens();
            } else
                throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
        }
    }

}
