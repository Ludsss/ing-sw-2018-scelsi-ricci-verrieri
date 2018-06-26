package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe che gestisce gli input dei client e implementa i metodi di PlayerInterface (sulla rete)

public class PlayerController extends UnicastRemoteObject implements PlayerInterface, Remote{
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayer remotePlayer;
    //stato del giocatore
    private PlayerState state;
    //tengo traccia del nickname nel caso lo stronzo si riconnettesse
    private String nickname;

    public PlayerController(Match match, RemotePlayer remotePlayer, Player player) throws RemoteException {
        super();
        this.match=match;
        this.remotePlayer=remotePlayer;
        this.player=player;
        this.nickname=player.getNickname();
        //this.state=player.getState();
    }

    /*public void setState(PlayerState state){
        this.state=player.getState();
    }*/

    public PlayerState getState() {
        return this.player.getState();
    }

    public RemotePlayer getRemotePlayer() {
        return this.remotePlayer;
    }

    public String getNickname(){
        return this.nickname;
    }

    @Override
    public void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException {
        if(player.getState().equals(PlayerState.OFFLINE)){
            //gestisci lo stronzo che ritorna in partita
        }
        else if(player.getState().equals(PlayerState.INIZIALIZED)) {
            match.joinMatch();

        }
    }

    @Override
    public void checkAllReady() throws RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.READYTOPLAY)){
            match.checkAllReady();
        }
        else throw new NotValidPlayException("non va bene !");
    }


    @Override
    public void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException, NotValidException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        if (player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
            match.chooseScheme(this.player,id);
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        }
        else throw new NotValidPlayException("non puoi fare questa mossa ora!" + player.getState().toString());
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        switch (player.getState()){
            case USEDDICE: throw new NotValidPlayException("hai già usato un dado in questo turno!");
            case FINISHTURN: throw new NotValidPlayException("non puoi più fare mosse, passa il turno");
            case USEDTOOLCARD: match.useDiceEndTurn(player,indexOfDiceInDraftPool,row,col);
            case TURNSTARTED:  {
                System.out.println("giocatore: "+ nickname+ "\n stato andata:"+ player.getState().toString());
                match.useDice(player, indexOfDiceInDraftPool, row, col);
                System.out.println("giocatore: "+ nickname+ "\n stato ritorno:"+ player.getState().toString());
                break;
            }
            default: throw new NotValidPlayException("non puoi questa mossa ora");
        }
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
    }

    //quando passo il turno poi sono pronto a giocare il prossimo turno--> tanto non sarò attivo quindi non potrò chiamare
    //i metodi. Oppure facciamo un altro stato per essere più sicuri e quando vieni notificato isPlaying passi allo stato READYTOPLAY???
    @Override
    public void endTurn() throws NetworkException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.READYTOPLAY)|| player.getState().equals(PlayerState.INIZIALIZED ) || player.getState().equals(PlayerState.OFFLINE)){
            throw new NotValidPlayException("finisci il turno caro!");
        }
        else {
            match.changePlayer();
            System.out.println("turno finito");
        }
    }

    @Override
    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
            match.useToolCard1(player,indexInDraftPool,operation);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
            match.useToolCard1(player ,indexInDraftPool,operation);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard234(player,id,sourceRow,sourceCol,destRow,destCol);
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());

        }
        else if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard234(player,id,sourceRow,sourceCol,destRow,destCol);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard5(player,indexInDraftPool, round, indexInRound);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard5(player,indexInDraftPool, round, indexInRound);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.READYTOPLAY)){
            match.useToolCard6(player,indexInDraftPool);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard6(player,indexInDraftPool);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }


    @Override
    public void useToolCard78(int id) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.READYTOPLAY)){
            match.useToolCard78(player,id);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard78(player,id);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }


}
