package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class Martelletto extends ToolCard {

    private final int id=7;


    public Martelletto() {
        super();
    }

    @Override
    public void execute(DraftPool draftPool) {
        for(Dice dice: draftPool.getDraftPool()){
            dice.throwDice();
        }
        incrementNumOfTokens();
    }


}
