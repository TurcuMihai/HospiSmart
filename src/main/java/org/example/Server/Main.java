package org.example.Server;


import org.example.Server.ApplicationServer.ApplicationServer;

/**
 * Clasa main a serverului
 */
public class Main {
    public static void main(String[] args) {
        ApplicationServer applicationServer = new ApplicationServer(8008);
        applicationServer.start();
    }
}