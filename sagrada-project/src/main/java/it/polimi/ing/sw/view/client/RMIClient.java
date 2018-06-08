<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/client/RMIClient.java
package it.polimi.ing.sw.view.client;

import it.polimi.ing.sw.server.ServerInterface;
import it.polimi.ing.sw.server.exceptions.NotValidNicknameException;
=======
package Progetto.View.Client;

import Progetto.Server.*;
import Progetto.Server.Exceptions.NotValidNicknameException;
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/Client/RMIClient.java

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    String nickname;
    ServerInterface server;

    public RMIClient(String nickname){
        this.nickname=nickname;
    }


    public void connectClient() throws RemoteException, NotBoundException, NotValidNicknameException {
        try {
            Registry registry = LocateRegistry.getRegistry();
            System.out.print("RMI registry bindings: ");
            server = (ServerInterface) registry.lookup("serverInterface");
        }
        catch (RemoteException | NotBoundException e){};
    }

    public void login(String nickname) throws NotValidNicknameException {
        server.login(this.nickname);
    }

}