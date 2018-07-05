package it.polimi.ing.sw.controller.network.RMI;

import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia per chiamare i metodi remoti in RMI.
 * In caso di connessione RMI, sarà il "controller" lato View.
 */
public interface PlayerControllerInterfaceRMI extends Remote, PlayerControllerInterface {

    void joinMatch() throws RemoteException;

    void checkAllReady() throws RemoteException;

    void setChosenScheme(int id) throws RemoteException;

    void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws RemoteException;

    void endTurn() throws RemoteException;

    void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws RemoteException;

    void stopPlayer() throws RemoteException;

    void login(String nickname) throws RemoteException;

    void startingMyTurn() throws RemoteException;
}
