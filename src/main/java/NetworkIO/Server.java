package NetworkIO;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * A program that continuously accepts client connections and creates a new thread to handle each client.
 *
 * @author Colten Glover
 *
 * @version March 25, 2024, 7:50 PM
 */

public class Server {
    private static final int PORT_NUMBER = 8080;

    public static void main(String[] args) {
        // Socket listening for clients
        ServerSocket serverSocket;

        try {
            // Assign server socket to a port
            serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("NetworkIO.Server started!");
        } catch (Exception e) {
            // TODO: Better handling
            e.printStackTrace();
            return;
        }

        while (true) {
            System.out.println("Waiting for a connection...");
            // Accept connection from client
            try {
                System.out.println("Received connection from client");
                Thread thread = new Thread(new ClientHandler(serverSocket.accept()));
                thread.start();
            } catch (IOException e) {
                // TODO: Better handling
                e.printStackTrace();
            }
        }
    }
}
