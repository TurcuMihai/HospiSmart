package org.example.Client;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;

/**
 * Main-ul clientului
 */
public class Main {
    public static void main(String[] args) {
        ApplicationClient applicationClient = new ApplicationClient("localhost", 8008);
        MainFrame mainFrame = new MainFrame(applicationClient);
        mainFrame.setVisible(true);
        applicationClient.setLoginMainFrame(mainFrame);
        applicationClient.start();
    }
}