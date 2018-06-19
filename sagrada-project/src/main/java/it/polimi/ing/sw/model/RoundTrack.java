package it.polimi.ing.sw.model;


import it.polimi.ing.sw.util.Constants;

public class RoundTrack {

    private DraftPool[] roundTrack;
    private int round = 0;


    public RoundTrack(){
        roundTrack = new DraftPool[Constants.NUM_ROUNDS];
    }


    // aggiunge sul roundTrack tutti i dadi avanzati alla fine del round

    public void addDicesRound(DraftPool draftPool){
        roundTrack[round] = new DraftPool();
        roundTrack[round].addDraftPool(draftPool);
        round++;
    }


    // ritorna tutto il roundTrack

    public DraftPool[] getRoundTrack() {
        return roundTrack;
    }


    // ritorna i dadi avanzati alla fine di un determinato round

    public DraftPool getDicesRound(int round){
        return roundTrack[round-1];
    }


    // ritorna il numero di dadi avanzati alla fine di un determinato round

    public int getNumberOfDices(int round) {
        return roundTrack[round-1].getSize();
    }


    public int getRoundTrackSize() {
        return round;
    }


    // ritorna il numero massimo di dadi avanzati in un round

    public int getMaxNumberOfDices() {
        int num, max = 0;
        for (int i=1; i<=round; i++) {
            num = getNumberOfDices(i);
            if (num > max)
                max = num;
        }

        return max;

    }

}

