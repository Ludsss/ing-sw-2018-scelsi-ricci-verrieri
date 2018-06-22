package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.*;

import java.rmi.Remote;
import java.util.ArrayList;

//interfaccia remota, implementata da PlayerController, che riceve gli aggiornamenti dal Model
public interface UiUpdate {

    public void onLogin (String message);

    public void onSuccess (String message);

    public void onActionNotValid (String errorCode);

    public void onChooseNetwork (String message);

    public void onTurnStart (Match match, String nickname);

    public void onPlaceDiceNotValid ();

    public void onTurnEnd ();

    public void onGameUpdate (Match match, String nickname);

    public void onGameEnd (Match match);

    public void onSchemeToChoose (Match match, String nickname, String message);

}
