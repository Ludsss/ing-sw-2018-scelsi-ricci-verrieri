package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class Lathekin extends ToolCard {
    final int id=4;

    public Lathekin() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice1, Dice dice2, Player player, Box source1, Box destination1, Box source2, Box destination2) throws ToolCardException{
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("il tuo schema è vuoto!");
        }

        source1.removeDice();
        if(!destination1.isFull() && scheme.checkBox(destination1,dice1) && scheme.checkDiceAdjacent(destination1,dice1)){
            destination1.placeDice(dice1);
        }
        else {
            source1.placeDice(dice1);
        }

        source2.removeDice();
        if(!destination2.isFull() && scheme.checkBox(destination2,dice2) && scheme.checkDiceAdjacent(destination2,dice2)){
            destination2.placeDice(dice2);
            player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
            setNumOfTokens(2);
        }
        else {
            source2.placeDice(dice2);
        }

    }
}