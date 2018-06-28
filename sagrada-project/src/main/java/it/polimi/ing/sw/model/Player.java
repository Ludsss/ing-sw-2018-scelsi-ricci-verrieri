package it.polimi.ing.sw.model;
import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.model.objectiveCard.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable{
    private String nickname;
    private int orderInRound;    // ordine che viene assegnato al giocatore nel primo round. Questo numero identifica il giocatore ed è molto più comodo gestire tutta la parte di passaggio da un giocatore all'altro utilizzando questo
    private int numOfToken;
    private int score;
    private boolean isLogged;
    private boolean isOnline; //true quando sta giocando (posso ricevere i comandi) false altrimenti
    private Scheme scheme;
    private PrivateObjectiveCard privateObjective;
    private Color color;
    private ArrayList<Scheme> schemesToChoose;
    private PlayerState state;


    public Player (String nickname){
        this.nickname=nickname;
        this.isOnline=true;
        this.state=PlayerState.INIZIALIZED;
    };

    public boolean isOnline() {
        return isOnline;
    }

    public int getNumOfToken() {
        return numOfToken;
    }

    public PlayerState getState(){
        return this.state;
    }

    public int getOrderInRound() {
        return orderInRound;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Scheme> getSchemesToChoose() {
        return schemesToChoose;
    }

    public void setOrderInRound(int i){
        orderInRound=i;
    }

    public PrivateObjectiveCard getPrivateObjective() {
        return privateObjective;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScore(int score) {
        this.score = score;
    }

   public boolean isLogged(){
        return this.isLogged;
   }

    public void setNumOfToken(int numOfToken) {
        this.numOfToken = numOfToken;
    }

    public void setPrivateObjective(PrivateObjectiveCard objectiveCard) {
        privateObjective = objectiveCard;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
        this.setState(PlayerState.READYTOPLAY);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSchemesToChoose(ArrayList<Scheme> schemesToChoose) {
        this.schemesToChoose = schemesToChoose;

    }

    public void setState(PlayerState state){
        this.state=state;
    }


    public void setLogged(boolean isLogged){
        this.isLogged=isLogged;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public void setOnline(){
        this.isOnline=true;
    }

    public void setOffline(){
        this.isOnline=false;
    }

}