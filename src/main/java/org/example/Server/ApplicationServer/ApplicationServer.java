package org.example.Server.ApplicationServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clasa care creeaza socket-ul aplicatiei si seteaza un port pentru aceasta
 */
public class ApplicationServer {

    private ServerSocket serverSocket;

    private int port;

    /**
     * Constructor ce seteaza portul aplicatiei
     * @param port
     */
    public ApplicationServer(int port) {
        this.port = port;
    }

    /**
     *  Metoda ce asteapta solicitari de conexiuni de la clienti
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected with " + clientSocket.getInetAddress());

                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
