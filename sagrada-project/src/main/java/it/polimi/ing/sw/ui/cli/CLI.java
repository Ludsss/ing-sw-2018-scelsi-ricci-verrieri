package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.util.ArrayList;
import java.util.Scanner;


public class CLI implements UiUpdate {

    public Scanner scanner = new Scanner(System.in);
    public String inText;


    private View controller;


    public CLI(View controller) {
        this.controller = controller;
    }

    /*public PlayerController getController() {
        if (controller == null)
            controller = new PlayerController(getUI());
        return controller;
    }*/

    public View getController() {
        return controller;
    }

    /*public UiUpdate getUI() {
        if (ui == null) {
            ui = new CLI();
        }
        return ui;
    }*/

    /**
     * Inizio come Client o Server.
     *
     * @param //args
     */
    /*public static void main(String[] args) {
        String serverAddress = Constants.SERVER_ADDRESS;
        int socketPort = Constants.SOCKET_PORT;
        int rmiPort = Constants.RMI_PORT;

        // Controllo se l'utente ha inserito un serverAddress, socketPort o rmiPort. Se li ha inseriti, li utilizzo, altrimenti uso quelli di default
        if (args.length != 0) {
            try {
                serverAddress = args[0];
                socketPort = Integer.parseInt(args[1]);
                rmiPort = Integer.parseInt(args[0]);
            } catch (Exception e) {    //TODO: gestire questa eccezione
                System.exit(0);
            }
        }
        if (Constants.START_AS_SERVER_IF_CLIENT_CONNECTION_FAILS) {
            System.out.print("Digita C per iniziare come Client o S per iniziare come Server (Default: Client)");
            inText = scanner.nextLine().toUpperCase();

            if (inText.equals("S")) {
                mainServer(socketPort, rmiPort);
            } else if (inText.equals("C")) {
                mainClient(serverAddress, socketPort, rmiPort);
            }
            // Default: Client
            else {
                System.out.println("Inizio come Client...");
                mainClient(serverAddress, socketPort, rmiPort);
            }
        } else {
            mainClient(serverAddress, socketPort, rmiPort);
        }
    }

    /**
     * Avvia Client (RMI o Socket).
     *
     */
    /*public void mainClient(String serverAddress, int socketPort, int rmiPort) {
        mainClient(serverAddress, socketPort, rmiPort, null);
    }

    /**
     * Avvia Client (RMI o Socket)
     * @param clientUI
     */

    /*public PlayerController mainClient(String serverAddress, int socketPort, int rmiPort, UiUpdate clientUI) {
        ui = clientUI;

        System.out.print("Digita R per connetterti tramite RMI o S per connetterti tramite Socket (Default: RMI)");
        inText = scanner.nextLine().toUpperCase();

        if (inText.equals("S")) {
            inText = "Socket";
        } else if (inText.equals("R")) {
            inText = "RMI";
        }
        // Default: RMI
        else {
            inText = "RMI";
            System.out.println("Connettendo con RMI...");
        }

        boolean success = false;
        int attempts = Constants.MAX_CONNECTION_ATTEMPTS;
        int sec = Constants.CONNECTION_RETRY_SECONDS * 1000;
        while (!success && attempts > 0) {
            try {
                attempts--;
                PlayerController controller = getController();
                controller.startClient(inText, serverAddress, socketPort, rmiPort);
                success = true;
            } catch (ClientException e) {
                if (attempts > 0) {
                    System.err.println(e.getMessage() + " (" + "Riprovo in " + sec / 1000 + " secondi" + ", " + attempts
                            + " tentativi rimanenti)");
                    try {
                        Thread.sleep(sec);
                    } catch (InterruptedException ie) {
                        // TODO gestire questa eccezione
                    }
                }
            }
        }

        if (success) {
            login();
            if (clientUI == null) {

            }
            else
                return getController();
        } else {
            if (Constants.START_AS_SERVER_IF_CLIENT_CONNECTION_FAILS) {
                System.err.println(
                        "\nImpossibile stabilire una connessione col server, il programma avvierà un server locale");

                mainServer(socketPort, rmiPort);
            } else {
                System.err.println("\nImpossibile stabilire una connessione col server, il programma terminerà a breve");
                System.exit(0);
            }
        }
        return null;
    }

    /**
     * Avvio Server (Client e Server).
     *
     * @param socketPort
     * @param rmiPort
     */
    /*public void mainServer(int socketPort, int rmiPort) {
        try {
            Server server = new Server();
            server.startServer(socketPort, rmiPort);

            System.out.print("\nServer in ascolto a: ");
            System.out.println("127.0.0.1" + " (RMI: " + rmiPort + ", Socket: " + socketPort + ")");
            System.out.println();

        } catch (ServerException e) {
            System.err.println(e.getMessage());
            System.err.println("Errore di avvio del server locale");
            System.exit(0);
        }
    }

    /**
     * Login del Client sul Server.
     */
    public void login(String message) {
        System.out.println(message);
        inText = scanner.nextLine();
        controller.loginPlayerRMI(inText);
    }


    /*public void chooseNetwork(String message) {
        System.out.print(message);
        inText = scanner.nextLine();
        controller.chooseNetwork(inText);
    }*/


    /**
     * Scelta dello schema tra i 4 schemi disponibili da parte di un giocatore
     */
    public void chooseScheme(Match match, String nickname, String message) {
        ArrayList<Scheme> schemes = match.getPlayer(nickname).getSchemesToChoose();
        showSchemesToChoose(schemes);
        new Thread(() -> {
            int num;
            do {
                System.out.println(message);
                num = scanner.nextInt();

            } while (num < 1 || num > 4);
            controller.setChosenScheme(schemes.get(num - 1).getId());   //se per esempio qui c'è un errore, se lo gestisce il PlayerController*/
        }).start();
    }

    public void showSchemesToChoose(ArrayList<Scheme> schemes) {
        for (int i = 0; i < 4; i++) {
            System.out.println("Schema " + (i + 1) + ":");
            ShowScheme show = new ShowScheme(schemes.get(i));
            System.out.println("");
        }
    }


    /**
     * Scelta dell'azione da parte del giocatore
     */
    public void chooseAction(Match match, String nickname) {
        boolean ok;
        System.out.print("Digita: \n- D se vuoi posizionare un dado sul tuo schema; \n- T se vuoi utilizzare una carta utensile; \n- I se vuoi visualizzare le informazioni degli altri giocatori; \n- E se vuoi terminare il tuo turno; \n- Q se vuoi uscire dalla partita.\n");

        do {
            ok = true;
            inText = scanner.nextLine();

            switch (inText.toLowerCase()) {

                case "q": {
                    System.out.println("Sei sicuro che vuoi uscire dalla partita? Digita S per sì o N per no.");
                    if (scanner.nextLine().toLowerCase().equalsIgnoreCase("s")) {
                        // TODO: gestire terminazione corretta del programma!
                        System.out.println("Uscendo dalla partita...");
                        System.exit(0);
                    }
                    break;
                }

                case "d": {
                    handleUseDice(match, false);
                    break;
                }

                case "t": {
                    handleUseToolCard(match);   //TODO: metodi per le carte utensili
                    break;
                }

                case "i": {
                    printOtherPlayersInfo(match, nickname);
                    break;
                }

                case "e": {
                    endTurn();
                    break;
                }

                default: {
                    System.out.println("Scelta non valida");
                    ok = false;
                    break;
                }
            }
        } while (!ok);
    }


    public void notMyTurn() {
        /*System.out.println("Digita Q se vuoi uscire dalla partita");
        do {
            inText = scanner.nextLine();
            if (inText.equals("q")) {
                System.out.println("Sei sicuro che vuoi uscire dalla partita? Digita S per sì o N per no.");
                if (scanner.nextLine().toLowerCase().equalsIgnoreCase("s")) {
                    // TODO: gestire terminazione corretta del programma!
                    System.out.println("Uscendo dalla partita...");
                    System.exit(0);
                }
            }
        } while (!inText.equals("q"));
        */
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Connessione e disconnessione del Client --> da fare probabilmente
    /////////////////////////////////////////////////////////////////////////////////////////

    /*public void clientConnection() {
        PlayerController controller = getController();
        controller.clientConnection();
    }

    public void clientDisconnection() {
        PlayerController controller = getController();
        controller.clientDisconnection();
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta D: posizionare un dado sullo schema
    /////////////////////////////////////////////////////////////////////////////////////////

    public void handleUseDice(Match match, boolean toolCard9) {
        int dice, row, col;
        do {
            System.out.println("Digita l'indice del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
            row = scanner.nextInt();
        } while (row < 1 || row > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
            col = scanner.nextInt();
        } while (col < 1 || col > Constants.NUM_COLS);

        if (toolCard9)
            controller.useToolCard9(dice - 1, row - 1, col - 1);
        else
            controller.useDice(dice - 1, row - 1, col - 1);

    }


    public void retryPlaceDice() {
        int row, col;
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
            row = scanner.nextInt();
        } while (row < 1 || row > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
            col = scanner.nextInt();
        } while (col < 1 || col > Constants.NUM_COLS);

        controller.useDice(-1, row - 1, col - 1);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta T: utilizzare una toolcard
    /////////////////////////////////////////////////////////////////////////////////////////


    public void handleUseToolCard(Match match) {
        int num;
        do {
            System.out.println("Digita il numero della carta utensile che vuoi utilizzare, tra 1 e 3");
            num = scanner.nextInt();
        } while (num < 1 || num > 3);
        int id = match.getToolCards().get(num - 1).getId();
        useToolCard(id, match);
    }


    public void useToolCard(int id, Match match) {
        switch (id) {
            case 1:
                useToolCard1(match);
                break;
            case 2:
            case 3:
            case 4:
                useToolCard234(id, match);
                break;
            case 5:
                useToolCard5(match);
                break;
            case 6:
                useToolCard6(match);
                break;
            case 7:
            case 8:
                controller.useToolCard78(id);
                break;
            case 9:
                useToolCard9(match);
                break;
            case 10:
                useToolCard10(match);
                break;
            case 11:
                useToolCard11(match);
                break;


        }
    }


    public void useToolCard1(Match match) {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita 'a' se vuoi aumentare il numero del dado di 1, 'd' se vuoi diminuirlo");
            inText = scanner.nextLine();
        } while ((inText.toLowerCase() != "a") && (inText.toLowerCase() != "d"));
        controller.useToolCard1(dice - 1, inText);
    }


    public void useToolCard234(int id, Match match) {
        int sourceRow, sourceCol, destRow, destCol;
        do {
            System.out.println("Digita il numero della riga dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_ROWS);
            sourceRow = scanner.nextInt();
        } while (sourceRow < 1 || sourceRow > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_COLS);
            sourceCol = scanner.nextInt();
        } while (sourceCol < 1 || sourceCol > Constants.NUM_COLS);
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_ROWS);
            destRow = scanner.nextInt();
        } while (destRow < 1 || destRow > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_COLS);
            destCol = scanner.nextInt();
        } while (destCol < 1 || destCol > Constants.NUM_COLS);
        controller.useToolCard234(id, sourceRow - 1, sourceCol - 1, destRow - 1, destCol - 1);
    }


    public void useToolCard5(Match match) {
        int dice, round, indexInRound;
        do {
            System.out.println("Digita l'indice del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita il numero di round a cui appartiene il dado con cui vuoi scambiarlo, tra 1 e " + match.getRoundTrack().getRoundTrackSize());
            round = scanner.nextInt();
        } while (round < 1 || round > match.getRoundTrack().getRoundTrackSize());
        do {
            System.out.println("Digita l'indice del dado nel round che hai scelto, tra 0 e " + (match.getRoundTrack().getNumberOfDices(round) - 1));
            indexInRound = scanner.nextInt();
        } while (indexInRound < 1 || indexInRound > Constants.NUM_COLS);
        controller.useToolCard5(dice - 1, round, indexInRound);
    }


    public void useToolCard6(Match match) {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi tirare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard6(dice - 1);
    }


    public void useToolCard9(Match match) {
        handleUseDice(match, true);
    }


    public void useToolCard10(Match match) {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard10(dice - 1);
    }


    public void useToolCard11(Match match) {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi riporre nel sacchetto, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard11(dice - 1);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta I: visualizzare le informazioni degli altri giocatori (nome, schema, segnalini favore)
    /////////////////////////////////////////////////////////////////////////////////////////

    public void printOtherPlayersInfo(Match match, String nickname) {
        ArrayList<Player> otherPlayers = match.getOtherPlayers(nickname);
        for (Player player : otherPlayers) {
            System.out.println(player.getNickname());
            System.out.println(player.getNumOfToken());
            ShowScheme scheme = new ShowScheme(player.getScheme());
            System.out.println("");
            chooseAction(match, nickname);

        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta E: terminare il turno
    /////////////////////////////////////////////////////////////////////////////////////////


    public void endTurn() {
        controller.endTurn();
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi che invoca PlayerController su UiUpdate
    /////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onLogin(String message) {
        login(message);
    }

    @Override
    public void onActionNotValid(String errorCode) {
        System.out.println(errorCode);

    }

    /*@Override
    public void onChooseNetwork (String message) {
        chooseNetwork(message);
    }*/

    @Override
    public void onTurnStart(Match match, String nickname) {
        chooseAction(match, nickname);
    }

    @Override
    public void onPlaceDiceNotValid(NotValidException e) {
        System.out.println(e);
        retryPlaceDice();
    }

    @Override
    public void onTurnEnd() {
        notMyTurn();
    }

    @Override
    public void onGameUpdate(Match match, String nickname) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());
        ShowPublicObjectives pub = new ShowPublicObjectives(match.getPublicObjectives());
        ShowPrivateObjectiveCard priv = new ShowPrivateObjectiveCard(match.getPlayer(nickname).getPrivateObjective());
        ShowToolCards tool = new ShowToolCards(match.getToolCards());
        ShowDraftPool draft = new ShowDraftPool(match.getDraftPool());
        ShowScheme scheme = new ShowScheme(match.getPlayer(nickname).getScheme());
    }

    @Override
    public void onGameEnd(Match match) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());   //TODO: mettere pedine su roundtrack
        for (int i = 0; i < match.getNumPlayers(); i++) {
            System.out.print(i + 1 + ") " + match.getRanking().get(i).getNickname() + " con ");
            System.out.println(match.getRanking().get(i).getScore() + " punti");
        }
    }

    @Override
    public void onSchemeToChoose(Match match, String nickname, String message) {
        chooseScheme(match, nickname, message);
    }

    @Override
    public void onUseToolCard1NotValid(Match match, NotValidException e) {
        System.err.println(e);
        useToolCard1(match);
    }

    @Override
    public void onUseToolCard234NotValid(int id, Match match, NotValidException e) {
        System.err.println(e);
        useToolCard234(id, match);
    }

    @Override
    public void onOtherInfoToolCard4(Match match) {
        System.out.println("Primo dado mosso correttamente, ora muovi il secondo");
        useToolCard234(4, match);
    }

    @Override
    public void onToolCard6(Match match) {
        System.out.println("Ora digita la casella dove posizionare il dado");
        useToolCard6(match);
    }

    @Override
    public void onUseToolCard9NotValid(Match match, NotValidException e) {
        System.err.println(e);
        useToolCard9(match);
    }

    @Override
    public void onOtherInfoToolCard11(Match match) {
        int dice, row, col;
        do {
            System.out.println("Digita il valore del nuovo dado, tra 1 e 6");
            dice = scanner.nextInt();
        } while (dice < 1 || dice > 6);
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
            row = scanner.nextInt();
        } while (row < 1 || row > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
            col = scanner.nextInt();
        } while (col < 1 || col > Constants.NUM_COLS);

        controller.useToolCard11b(dice - 1, row - 1, col - 1);
    }

    @Override
    public void onUseToolCard11bNotValid(Match match, NotValidException e) {
        System.err.println(e);
        onOtherInfoToolCard11(match);
    }

    @Override
    public void onSuccess(String message) {
        System.out.println(message);
    }

}