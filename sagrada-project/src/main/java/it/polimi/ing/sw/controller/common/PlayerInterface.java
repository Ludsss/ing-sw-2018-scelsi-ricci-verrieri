package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Color;

import java.io.Serializable;
import java.util.ArrayList;

public interface PlayerInterface extends Serializable {
    int getNumOfToken();

    PrivateObjectiveCardInterface getPrivateObjective();

    SchemeInterface getScheme();

    int getOrderInRound();

    String getNickname();

    int getScore();

    Color getColor();

    ArrayList<SchemeInterface> getSchemesToChoose();
}