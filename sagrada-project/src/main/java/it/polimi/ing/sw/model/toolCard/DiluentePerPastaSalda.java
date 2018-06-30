package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;


public class DiluentePerPastaSalda extends ToolCard {

    private final int id = 11;
    private Dice dice;
    private boolean firstExecutionDone;


    public DiluentePerPastaSalda() {
        super(11);
        firstExecutionDone = false;

    }


    public boolean getFirstExecutionDone() {
        return firstExecutionDone;
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme scheme, Player[] neverUsed2, Bag bag, int indexInDraftPool, int numFacciaUp, int row, int col, int neverUsed3, int neverUsed4) throws NotValidException {
        if (!firstExecutionDone) {
            bag.addDice(draftPool.getDice(indexInDraftPool));
            draftPool.setDice(bag.drawDice(), indexInDraftPool);
            firstExecutionDone = true;
        }
        else {
            dice.setNumFacciaUp(numFacciaUp);
            Box destBox = scheme.getBox(row, col);
            if (destBox.isFull())
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            else {
                if (scheme.checkBox(row, col, dice) && scheme.checkIfHasDiceAdjacent(row, col, dice, 1)) {
                    destBox.placeDice(dice);
                    draftPool.removeDice(dice);
                    firstExecutionDone = false;
                    //incrementNumOfTokens();
                } else
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }


}
